import axios from "axios"
import { serverRootPath } from "../constants/apiConfig"

// export const serverRootPath = "http://localhost:8080/servlet_study_seonggwang/";
export const getProfile = async () => {
    const option = {
        headers : {
            "Authorizaion": localStorage.getItem("token")
        }
    }
    try {
        const response = await axios.get(`${serverRootPath/mypage/profile}`, option)
        return response.data;
    }catch(error) {
        console.log(error);
    }

}