import React, { useState, useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import "../App.css";

function HomePage() {
  const [username, setUsername] = useState("");
  const [message, setMessage] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    // Assuming the token is saved in localStorage after login
    const token = localStorage.getItem("token");
    if (!token) {
      setMessage("You need to be logged in to access the homepage.");
      navigate("/login");
      return;
    }

    const decodedToken = JSON.parse(atob(token.split(".")[1]));
    setUsername(decodedToken.sub);
  }, [navigate]);

  // Function to create a new game
  const createGame = () => {
    navigate("/create-game"); // Redirect to Create Game Page
  };

  // Function to log out the user
  const logout = () => {
    localStorage.removeItem("token"); // Remove the token from localStorage
    navigate("/login"); // Redirect the user to the login page
  };

  return (
    <div id="homePageDiv">
      <h1>Welcome, {username}!</h1>
      <p>Welcome to Connect Four. What would you like to do?</p>
      <button onClick={createGame}>Create a New Game</button>
      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default HomePage;
