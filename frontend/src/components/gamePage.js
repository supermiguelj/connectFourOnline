import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import "./gamePage.css"; // CSS file for styling

function GamePage() {
  const { gameId } = useParams(); // Get game ID from the URL
  const [board, setBoard] = useState([]);
  const [currentPlayer, setCurrentPlayer] = useState("");
  const [gameStatus, setGameStatus] = useState("");
  const [message, setMessage] = useState("");
  const [player1, setPlayer1] = useState("");
  const [player2, setPlayer2] = useState("");

  useEffect(() => {
    fetchGameDetails();
  }, [gameId]);

  const fetchGameDetails = async () => {
    const token = localStorage.getItem("token");

    if (!token) {
      setMessage("You need to be logged in to play the game.");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/games/${gameId}`, {
        headers: {
          "Authorization": `Bearer ${token}`,
        },
      });

      const data = await response.json();
      setBoard(data.board);
      setCurrentPlayer(data.currentPlayer);
      setGameStatus(data.status);
      setPlayer1(data.player1);
      setPlayer2(data.player2);
    } catch (error) {
      console.error("Error fetching game details:", error);
      setMessage("Something went wrong. Please try again later.");
    }
  };

  const makeMove = async (col) => {
    const token = localStorage.getItem("token");

    if (!token) {
      setMessage("You need to be logged in to make a move.");
      return;
    }

    try {
      const response = await fetch(`http://localhost:8080/games/${gameId}/move`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Authorization": `Bearer ${token}`,
        },
        body: JSON.stringify({ column: col }),
      });

      if (response.ok) {
        fetchGameDetails(); // Refresh the game state after a move
      } else {
        setMessage("Invalid move, try again.");
      }
    } catch (error) {
      console.error("Error making move:", error);
      setMessage("Something went wrong. Please try again later.");
    }
  };

  return (
    <div id="gameDiv">
      <h1>Game ID: {gameId}</h1>
      <h2>Current Player: {currentPlayer}</h2>
      <h3>Status: {gameStatus}</h3>

      <div className="board">
        {board.map((row, rowIndex) => (
          <div className="row" key={rowIndex}>
            {row.map((cell, colIndex) => (
              <div
                key={colIndex}
                className={`cell ${cell === 1 ? "player1" : cell === 2 ? "player2" : ""}`}
                onClick={() => makeMove(colIndex)}
              ></div>
            ))}
          </div>
        ))}
      </div>

      {message && <p style={{ color: "red" }}>{message}</p>}
    </div>
  );
}

export default GamePage;
