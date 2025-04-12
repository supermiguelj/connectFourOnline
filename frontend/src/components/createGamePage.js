import React, { useState } from "react";
import "./createGame.css"; // CSS file for styling

function CreateGamePage() {
  const [gameId, setGameId] = useState(null);
  const [message, setMessage] = useState("");

  // Function to create a new game
  const createGame = async () => {
    const token = localStorage.getItem("token");

    if (!token) {
      setMessage("You need to be logged in to create a game.");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/games/create", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`, // Send token with the request
        },
      });

      if (response.ok) {
        const data = await response.json();
        setGameId(data.gameId);
        setMessage(`Game created successfully! Game ID: ${data.gameId}`);
      } else {
        setMessage("Failed to create game. Please try again.");
      }
    } catch (error) {
      console.error("Error creating game:", error);
      setMessage("Something went wrong. Please try again.");
    }
  };

  return (
    <div id="createGameDiv">
      <h1>Create a New Game</h1>
      <button onClick={createGame}>Create Game</button>
      {message && <p>{message}</p>}
      {gameId && (
        <div>
          <h3>Your Game ID: {gameId}</h3>
          <button onClick={() => window.location.href = `/game/${gameId}`}>Start Game</button>
        </div>
      )}
    </div>
  );
}

export default CreateGamePage;
