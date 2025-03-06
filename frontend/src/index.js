import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import logo from './Connect4OnlineLogo.png';

function Background() {
  //return <h1 className = "greeting">Hello world!</h1>;
  return (
    <div
      style={{
        backgroundColor: '#070024',
        width: '100vw',
        height: '100vh',
        position: 'absolute',
        top: 0, 
        left: 0
      }}
      />
  );
}

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

function Login() {
  console.log("Login Information");

  return (
    <div style={{ 
      display: "flex", 
      justifyContent: "center", 
      alignItems: "center",
      position: "absolute",
      top: "30%", 
      left: "50%", 
      transform: "translate(-50%, -50%)", 
      width: "100%", 
      color: "#FFFFFF",
      fontSize: "30px",
      fontWeight: "bold",
      fontFamily: "League Spartan, sans-serif",
      textAlign: "center"
    }}>
      Login Information
    </div>
  );
}


console.log(logo);

export {Header, Login};



const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Background />
    <Header />
    <Login />
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
