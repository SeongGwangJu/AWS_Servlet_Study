import React, { useEffect, useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";

function Mypage(props) {
    const [ user, setUser ] = useState({
        username:"",
        password: "",
        name:"",
        email:""
    }, []);

    useEffect();
    return (
        <>
            <h1>마이페이지</h1>
            <p>username: {user.username}</p>
            <p>password: {user.password}</p>
            <p>name: {user.name}</p>
            <p>email: {user.email}</p>
        </>
    );
}

export default Mypage;