import './App.css';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import { Link } from "react-router-dom";
import CreatePage from "./components/createPage";
import LoginPage from "./components/loginPage";
import HomePage from "./components/home";

function App() {
  console.log("App is redering");

  return (
    <div>
      <Routes>
        <Route path="/" element={<LoginPage />} /> 
        <Route path="/register" element={<CreatePage />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/home" element={<HomePage />} />
      </Routes>
    </div>
  );
}

export default App;
