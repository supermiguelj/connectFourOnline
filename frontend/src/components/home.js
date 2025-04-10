// src/components/home.js
import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";

const HomePage = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const token = localStorage.getItem("token");

    // If no token, redirect to login
    if (!token) {
      navigate("/login");
    } else {
      // Optional: Token could be validated with the server here if needed
    }
  }, [navigate]);

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <div style={{ paddingTop: "100px", textAlign: "center" }}>
      <h1>Welcome to Connect4!</h1>
      <p>You are successfully logged in.</p>
      <button 
        onClick={handleLogout} 
        style={{
          padding: "10px 20px",
          fontSize: "16px",
          borderRadius: "8px",
          backgroundColor: "#ff4d4f",
          color: "white",
          border: "none",
          cursor: "pointer",
          marginTop: "20px"
        }}
      >
        Logout
      </button>
    </div>
  );
};

export default HomePage;
