import React from "react";
import ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import App from "./App";
import CreatePage from "./components/createPage";
import LoginPage from "./components/loginPage";
import HomePage from "./components/home";
import "./index.css";
import logo from './Connect4OnlineLogo.png';


const root = ReactDOM.createRoot(document.getElementById("root"));

function Header() {
    return (
      <div style={{ 
        display: "flex", 
        justifyContent: "center", 
        position: "absolute",
        top: "0", 
        left: "50%", 
        transform: "translateX(-50%)", 
        width: "100%", 
      }}>
        <img src={logo} alt="Connect4OnlineLogo" style={{ maxWidth: "100%", height: "auto" }} />
      </div>
    );
  }
  

root.render(
    <React.StrictMode>
      <BrowserRouter>
        <App />
        <Header />
      </BrowserRouter>
    </React.StrictMode>
  );