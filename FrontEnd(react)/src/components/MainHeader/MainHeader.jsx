import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from "@emotion/react";
import { Link } from 'react-router-dom';

const SLayout = css`
    display: flex;
    justify-content: space-between;
    align-items: center;
    border-bottom: 1px solid #dbdbdb;
    width: 100%;
    height: 70px;
`;
function MainHeader(props) {
    return (
        <div css={SLayout}>
            <Link to="/">메인</Link>
            <Link to="/signin">로그인</Link>
            <Link to="/signup">회원가입</Link>
            <Link to="/async">비동기</Link>
            <Link to="/mypage">마이페이지</Link>
            <Link to="/mypage/edit">프로필 편집</Link>
        </div>
    );
}

export default MainHeader;