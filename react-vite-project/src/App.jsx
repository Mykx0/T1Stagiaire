import "./App.css";
import React from "react";
import AppRoutes from "./routes/AppRoutes.jsx";
function App() {
  let token = localStorage.getItem('token')
  return (
  <>
    <AppRoutes/>
  </>
  );
}

export default App;
