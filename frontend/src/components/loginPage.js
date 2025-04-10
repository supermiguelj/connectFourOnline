import React, { useState } from "react";
import "./loginPage.css"; // Make sure to import the CSS file

function LoginPage() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const [errorMessage, setErrorMessage] = useState(""); // State for error messages

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const data = await response.json();
        alert(data.message || "Login Successful!");
        // Redirect to the desired page after login, e.g., home page or dashboard
        window.location.href = "/home";
      } else {
        const errorMsg = await response.json();
        setErrorMessage(errorMsg.NOT_FOUND || errorMsg.UNAUTHORIZED || "Login failed.");
      }
    } catch (error) {
      console.error("Error during login:", error);
      setErrorMessage("Something went wrong. Please try again later.");
    }
  };

  return (
    <div id="loginDiv">
      <h1>Login Information</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          name="username"
          value={formData.username}
          onChange={handleChange}
          required
        />
        <br />
        <label htmlFor="password">
          <span className="tab1"></span>Password:
        </label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <div className="wrapLogin" id="wrapLogin">
          <button type="submit">Login</button>
        </div>
      </form>
      {errorMessage && <p style={{ color: "red" }}>{errorMessage}</p>} {/* Display error message */}
      <h3 id="h3Login">
        New to our site? Click the button below to create an account.
      </h3>
      <button
        id="buttonCreate"
        onClick={() => (window.location.href = "/register")}
      >
        Create Account
      </button>
    </div>
  );
}

export default LoginPage;
