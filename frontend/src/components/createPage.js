import { useState } from "react";
import "./createPage.css";

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

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/register", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const data = await response.json();
        alert(data.message || "Account created successfully!");
        window.location.href = "/"; // Redirect to login
      } else {
        const errorMsg = await response.text();
        alert(`Registration failed: ${errorMsg}`);
      }
    } catch (error) {
      console.error("Error during registration:", error);
      alert("Something went wrong. Please try again later.");
    }
  };

  return (
    <div id="createDiv">
      <h1 id="h1Create">
        Please enter your information below to create an account.
      </h1>
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
      <h3 id="h3Create">
        Already have an account? Click the button below to log in.
      </h3>
      <button id="buttonLogin" onClick={() => (window.location.href = "/login")}>
        Log in
      </button>
    </div>
  );
}

export default CreatePage;
