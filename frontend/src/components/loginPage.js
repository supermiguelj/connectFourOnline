import React, { useState } from "react";
import "./loginPage.css"; // Make sure to import the CSS file

function LoginPage() {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log("User Logged In:", formData);
    // Add form submission logic here (e.g., send to backend)
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
      <h3 id="h3Login">
        New to our site? Click the button below to create an account.
      </h3>
      <button
        id="buttonCreate"
        onClick={() => (window.location.href = "/create-account")}
      >
        Create Account
      </button>
    </div>
  );
}

export default LoginPage;
