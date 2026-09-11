import React from 'react';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import RegisterPage from "../components/auth/RegisterPage.jsx";

const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path={"/register"} element={<RegisterPage />} />
                <Route path={"*"} element={<Navigate to={"/register"} replace/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default AppRoutes;