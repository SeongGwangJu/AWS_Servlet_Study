import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import axios from 'axios';
import { Navigate, useNavigate } from 'react-router-dom';


const SInputLayout = css`
    margin-bottom: 15px;
    width: 80%;
    height: 40px;

    & > input {
        width: 100%;
        height: 100%;
        text-align: center;
    }
`;
function Signup(props) {

    const navigate = useNavigate();
    const [ signupUser, setSignupUser] = useState({
        username: "",
        password: "",
        name:"",
        email:""
    });

    const handleInputChange =(e) => {
        console.log(e.target.value);
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        })

        // await setSignupUserState();
        // console.log(signupUser);
    }

    const handleSubmitClick = () => {
        //회원가입 요청, option객체 안의 params 객체
        // params객체는 요청정보를 담아 보낸다.
        const option = {
            params: {
                username: signupUser.username
            }
        }
        //then then then하던걸 async await으로 정리
        const signup = async() => {
            let response = await axios.get("http://localhost:8080/servlet_study_seonggwang/auth/signup/duplicate/username"
            , option);
            // await가 없으면 response는 undefined. =>아래의 중복검증은 의미가 없다.
            // await를 함으로써 비동기를 동기로 바꾼다.
            if(response.data) {
                alert("중복된 아이디입니다");
                return;
            }

            //중복이 아니면 회원가입 진행
            try {
                response = await axios.post(
                    "http://localhost:8080/servlet_study_seonggwang/auth/signup",signupUser);
                if(!response.data) {
                    throw new Error(response);
                }
                alert("회원가입 성공!");
                console.log("▼ response.data");
                console.log(response.data);
                navigate("/signin")
            }catch(error) {
                console.log(error);
            }
            return response;
        }

        signup();

        // // 응답이오면 then아래가 실행
        // axios.get("http://localhost:8080/servlet_study_seonggwang/auth/signup/duplicate/username", option)
        // .then((response) => {
        //     //중복검증 완료-> 회원 add.
        //     axios.post("http://localhost:8080/servlet_study_seonggwang/signupservlet", signupUser)
        //     .then((response) => {
        //         alert(response.data);
        //         navigate("/signin")
        //     })
        // }).catch((error) => {
        //     alert("중복된 아이디입니다.")
        // })
    }
    return (
        <>
            <h1>회원가입</h1>
            <div css={SInputLayout} >
                <input type='text' name="username" placeholder="username" onChange={handleInputChange} />
            </div>
            <div css={SInputLayout} >
                <input type='password' name="password" placeholder="password" onChange={handleInputChange} />
            </div>
            <div css={SInputLayout} >
                <input type='text' name="name" placeholder="name" onChange={handleInputChange} />
            </div>
            <div css={SInputLayout} >
                <input type='text' name="email" placeholder="email" onChange={handleInputChange} />
            </div>
            <div>
                <button onClick={handleSubmitClick}>Submit</button>
            </div>
        </>
    );
}

export default Signup;