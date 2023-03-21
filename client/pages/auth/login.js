import { FcGoogle } from "react-icons/fc"
import {
	signInWithPopup,
	GoogleAuthProvider
} from "firebase/auth"
import { auth } from "../../utils/firebase"
import { useRouter } from "next/router"
import { useAuthState } from "react-firebase-hooks/auth"
import { useEffect } from "react"

export default function Login() {
	const route = useRouter()
	const [user, loading] = useAuthState(auth)

	//Sign in with google
	const googleProvider = new GoogleAuthProvider()
	const GoogleLogin = async () => {
		try {
			const result = await signInWithPopup(auth, googleProvider)
			console.log(result.user)
			route.push("/dashboard")
		} catch (error) {
			console.log(error)
		}
	}

	useEffect(() => {
		if (user) {
			route.push("/dashboard")
		} else {
			console.log("login")
		}
	}, [user])

	return (
		// <div className="shadow-xl mt-32 p-10 text-gray-700 rounded-lg">
		// 	<h2 className="text-3xl font-medium">Join today</h2>
		// 	<div className="py-4">
		// 		<h3 className="py-4">Sign in with one of the providers</h3>
		// 		<div className="flex flex-col gap-4">
		// 			<button
		// 				onClick={GoogleLogin}
		// 				className="text-white bg-gray-700 p-4 w-full font-medium rounded-lg flex align-middle gap-2 "
		// 			>
		// 				<FcGoogle className="text-2xl" />
		// 				Sign in with Google
		// 			</button>
		// 			<button
		// 				className="text-white bg-gray-700 p-4 w-full font-medium rounded-lg flex align-middle gap-2 "
		// 				onClick={FacebookProvider}
		// 			>
		// 				<AiFillFacebook className="text-2xl text-blue-300" />
		// 				Sign in with Facebook
		// 			</button>
		// 		</div>
		// 	</div>
		// </div>
		
		<div className="flex items-center justify-center" style={{ marginTop: "15vh" }}>
			<div className="flex items-center justify-center px-4 py-10 sm:px-6 lg:px-8 sm:py-16 lg:py-24">
				<div className="xl:w-full xl:max-w-sm 2xl:max-w-md xl:mx-auto">
					<h2 className="text-3xl font-bold leading-tight text-black dark:text-white sm:text-4xl">
						Sign in
					</h2>
					<p className="mt-2 text-base text-gray-600 dark:text-gray-300">
						Don&apos;t have an account?{" "}
						<a
							href="#"
							title=""
							className="font-medium text-indigo-600 transition-all duration-200 hover:text-indigo-700 hover:underline focus:text-indigo-700"
						>
							Create a free account
						</a>
					</p>

					<form action="#" method="POST" className="mt-8">
						<div className="space-y-5">
							<div>
								<label
									htmlFor=""
									className="text-base font-medium text-gray-900 dark:text-gray-200"
								>
									{" "}
									Email address{" "}
								</label>
								<div className="mt-2.5">
									<input
										className="flex h-10 w-full rounded-md border border-gray-300 bg-transparent py-2 px-3 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50 dark:border-gray-700 dark:text-gray-50 dark:focus:ring-gray-400 dark:focus:ring-offset-gray-900"
										type="email"
										placeholder="Email"
									></input>
								</div>
							</div>

							<div>
								<div className="flex items-center justify-between">
									<label
										htmlFor=""
										className="text-base font-medium text-gray-900 dark:text-gray-200"
									>
										{" "}
										Password{" "}
									</label>

									<a
										href="#"
										title=""
										className="text-sm font-medium text-indigo-600 hover:underline hover:text-indigo-700 focus:text-indigo-700"
									>
										{" "}
										Forgot password?{" "}
									</a>
								</div>
								<div className="mt-2.5">
									<input
										className="flex h-10 w-full rounded-md border border-gray-300 bg-transparent py-2 px-3 text-sm placeholder:text-gray-400 focus:outline-none focus:ring-1 focus:ring-gray-400 focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-50 dark:border-gray-700 dark:text-gray-50 dark:focus:ring-gray-400 dark:focus:ring-offset-gray-900"
										type="password"
										placeholder="Password"
									></input>
								</div>
							</div>

							<div>
								<button className="w-full inline-flex items-center justify-center rounded-md bg-indigo-600 px-3.5 py-2.5 text-base font-semibold leading-7 text-white hover:bg-indigo-500">
									Get started
									<svg
										xmlns="http://www.w3.org/2000/svg"
										fill="none"
										viewBox="0 0 24 24"
										strokeWidth={1.5}
										stroke="currentColor"
										className="w-4 h-4 ml-2"
									>
										<path
											strokeLinecap="round"
											strokeLinejoin="round"
											d="M4.5 12h15m0 0l-6.75-6.75M19.5 12l-6.75 6.75"
										/>
									</svg>
								</button>
							</div>
						</div>
					</form>

					<div className="mt-3 space-y-3">
						<button
							onClick={GoogleLogin}
							type="button"
							className="relative inline-flex items-center justify-center w-full px-4 py-4 text-base font-semibold text-gray-700 dark:text-gray-400 transition-all duration-200 bg-white border border-gray-500 rounded-md hover:bg-gray-100 focus:bg-gray-100 hover:text-black focus:text-black focus:outline-none"
						>
							<div className="absolute inset-y-0 left-0 p-4">
								<FcGoogle className="text-2xl" />
							</div>
							Sign in with Google
						</button>
					</div>
				</div>
			</div>
		</div>
	)
}
