import "./App.css";
import React from "react";
import AppRoutes from "./routes/AppRoutes.jsx";
import Navbar from "./components/Navbar.jsx";
function App() {
  return (
  <div>
    <Navbar/>
    <AppRoutes/>
  </div>
  );
}

export default App;
