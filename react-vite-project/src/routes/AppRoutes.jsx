import React from 'react';
import {BrowserRouter, Navigate, Route, Routes} from "react-router-dom";
import RoleSelectionForm from "../components/auth/RoleSelectionForm.jsx";

const AppRoutes = () => {
    return (
        <BrowserRouter>
            <Routes>
                <Route path={"/register"} element={<RoleSelectionForm />} />
                <Route path={"*"} element={<Navigate to={"/register"} replace/>}/>
            </Routes>
        </BrowserRouter>
    );
};

export default AppRoutes;