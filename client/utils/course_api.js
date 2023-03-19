import axios from "axios";

class CourseAPI {
    static async verifyJWT(idToken){
        const url = "http://localhost:8080/auth/verify"
        const config = {
            headers: {
                "authorization": `Bearer ${idToken}`
            }
        }

        return await axios.post(
            url, 
            {},
            config
        )
    }

    static async getAllCourses(){}

    static async getCourseById(){}

    static async getCoursesByName(name){}

    static async addCourse(course){}

    static async updateCourse(course){}

    static async deleteCourseById(id){}
}

export { CourseAPI }