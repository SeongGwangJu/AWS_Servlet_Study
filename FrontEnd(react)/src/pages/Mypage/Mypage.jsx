import React, { useEffect, useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { getProfile } from '../../api/mypageApi';
import axios from 'axios';

function Mypage(props) {
    const [ profile, setProfile ] = useState({
        username:"",
        password: "",
        name:"",
        email:""
    });

    useEffect(() => {
        const getProfile = async() => {
            try {
                const response = await axios.get(`http://localhost:8080/servlet_study_seonggwang/mypage/profile`, {
                    headers: {
                        Authorization: localStorage.getItem("token")
                    }
                });
                console.log("▼response.data")
                console.log(response.data)
                setProfile(response.data);
            }catch(error) {
                console.log(error);
            }
        }
        getProfile();
    }, []);

    return (
        <>
            <h1>마이페이지</h1>
            <p>username: {profile?.username}</p>
            <p>password: {profile?.password}</p>
            <p>name: {profile?.name}</p>
            <p>email: {profile?.email}</p>
        </>
    );
}

export default Mypage;