import React from 'react';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import Connexion from "../components/auth/connexion.jsx";

const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path={"/connexion"} element={<Connexion />} />
                <Route path={"*"} element={<Navigate to={"/connexion"} replace/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default AppRoutes;