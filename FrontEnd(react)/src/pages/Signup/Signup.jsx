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
    }

    const handleSubmitClick = () => {
        //회원가입 요청, option객체 안의 params 객체
        // params객체는 요청정보를 담아 보낸다.
        const option = {
            params: {
                username: signupUser.username
            }
        }
        // 응답이오면 then아래가 실행
        axios.get("http://localhost:8080/servlet_study_seonggwang/auth/signup/duplicate/username", option)
        .then((response) => {
            //중복검증 완료-> 회원 add.
            axios.post("http://localhost:8080/servlet_study_seonggwang/signupservlet", signupUser)
            .then((response) => {
                alert(response.data);
                navigate("/signin")
            })
        }).catch((error) => {
            alert("중복된 아이디입니다.")
        })
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