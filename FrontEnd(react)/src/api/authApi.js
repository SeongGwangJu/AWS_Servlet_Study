import axios from "axios"

export const serverRootPath = "httpL//localhost:8080/servlet_study_seonggwang/"
export const getUser = async () => {
    const response = await axios.get(`${serverRootPath/user})
}