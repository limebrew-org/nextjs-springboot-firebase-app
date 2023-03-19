import { auth } from "../utils/firebase";
import { useAuthState } from "react-firebase-hooks/auth";
import { useRouter } from "next/router";
import { CourseAPI } from "../utils/course_api";

export default function Dashboard() {
  const route = useRouter();
  const [user, loading] = useAuthState(auth);
  if (loading) return <h1>Loading</h1>;
  if (!user) route.push("/auth/login");
  if (user){
    console.log("User: ", user.displayName)
    console.log("UserId: ",user.uid)
    console.log("AccessToken: ",user.accessToken)
    CourseAPI.verifyJWT(user.accessToken).then(res => console.log("Data: ",res.data) )
    return (
      <div>
        <h1>Welcome to your dashboard {user.displayName}</h1>
        <button onClick={() => auth.signOut()}>Sign out</button>
      </div>
    );
  }
}
