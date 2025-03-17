import { useState } from "react";
import "./createPage.css"; // Import the CSS file

function CreatePage() {
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
    console.log("Account Created:", formData);
    // Add form submission logic here (e.g., send to backend)
  };

  return (
    <div id="createDiv">
      <h1 id="h1Create">Please enter your information below to create an account.</h1>
      <form onSubmit={handleSubmit}>
        <label htmlFor="username">Account Username:</label>
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
          <span className="tab1"></span>Account Password:
        </label>
        <input
          type="password"
          id="password"
          name="password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <div className="wrapCreate" id="wrapCreate">
          <button type="submit">Create Account</button>
        </div>
      </form>
      <h3 id="h3Create">Already have an account? Click the button below to log in.</h3>
      <button id="buttonLogin" onClick={() => (window.location.href = "/")}>
        Log in
      </button>
    </div>
  );
}

export default CreatePage;











/* <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="createPage.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=League Spartan' rel='stylesheet'>
    <title>Login Information</title>
</head>

<div id="createDiv">
    <h1 id="h1Create">Please enter your information below to create an account.</h1>
    <form action="">
        <label for="username">Account Username:</label>
        <input type="text" id="username" name="username" required>
        <br>
        <label for="password"><span class="tab1"></span>Account Password:</label>
        <input type="password" id="password" name="password" required>
        <div class="wrapCreate" id="wrapCreate">
            <button type="create">Create Account</button>
        </div>
    </form>
    <h3 id="h3Create">Already have an account? Click the button below to log in.</h3>
    <button id="buttonLogin" onclick="window.location.href = 'loginPage.html'">Log in</button>
</div>

</html>*/