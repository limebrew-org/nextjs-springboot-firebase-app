import { auth } from "../utils/firebase"
import { useAuthState } from "react-firebase-hooks/auth"
import { useRouter } from "next/router"
import { CourseService } from "../utils/service"
import { useEffect } from "react"

export default function Dashboard() {
	const route = useRouter()
	const [user, loading] = useAuthState(auth)

	// useEffect(()=>{

	// },[user])

	if (loading) return <h1>Loading</h1>
	if (!user) route.push("/auth/login")
	if (user) {
		console.log("User: ", user.displayName)
		console.log("UserId: ", user.uid)
		console.log("AccessToken: ", user.accessToken)

		//? Call Course Service to verifyJWT
		CourseService.verifyUser(user.accessToken)
		 	.then((res) => console.log("Data: ", res.data))
		 	.catch((error) => console.log("Error: ", error))

		return (
			<div>
				<h1>Welcome to your dashboard {user.displayName}</h1>
				<button onClick={() => auth.signOut()}>Sign out</button>
			</div>
		)
	}
}
