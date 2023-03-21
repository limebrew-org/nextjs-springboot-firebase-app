import axios from "axios"
import { COURSE_APP_SERVICE_URL } from "./constants"

class CourseService {
	static API_URL = `${COURSE_APP_SERVICE_URL}/api`
	static AUTH_URL = `${COURSE_APP_SERVICE_URL}/auth`

	static async verifyUser(idToken) {
		const url = `${CourseService.AUTH_URL}/user`
		const config = {
			headers: {
				authorization: `Bearer ${idToken}`
			}
		}

		return await axios.post(url, {}, config)
	}

	//? APIs
	static async getAllCourses() {}

	static async getCourseById() {}

	static async getCoursesByName(name) {}

	static async addCourse(course) {}

	static async updateCourse(course) {}

	static async deleteCourseById(id) {}
}

export { CourseService }
