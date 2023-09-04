import logo from './logo.svg';
/** @jsxImportSource @emotion/react */
import { Global, css } from "@emotion/react";
import './App.css';
import MainLayout from './components/MainLayout/MainLayout';
import MainHeader from './components/MainHeader/MainHeader';
import { Route, Routes } from 'react-router-dom';
import Signup from './pages/Signup/Signup';
import Asynchronous from './pages/Asynchronous/Asynchronous';
import Signin from './pages/Signin/Signin';

const SCommon =css`
  box-sizing: border-box;
`;function App() {
  return (
    <>
      <Global styles={SCommon} />
      <MainLayout>
        <Routes>
          <Route path="/" />
          <Route path="/signin" element={<Signin />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/async" element={<Asynchronous />} />
        </Routes>
      </MainLayout>
    </>
  );
}

export default App;
