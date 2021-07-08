import React, { useEffect } from "react";
import "./style.css";
import logo from "../../logo.svg";
import loginDoctor from "./logindoctor.png";
import infoDoctor from "./infodoctor.png";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHandPointRight,
  faArrowRight,
  faEye,
  faEyeSlash,
} from "@fortawesome/free-solid-svg-icons";
import { notification } from "antd";
import { useHistory } from "react-router-dom";
import { login } from "../../services/ApiCalls";
import { useState } from "react";


export default function Home() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();
  useEffect(() => {
    document.body.style.overflow = "hidden";
    document.getElementById("eye-slash").style.display = "none";
    return () => {};
  }, []);
  const showPassword = () => {
    if (document.getElementById("user-pwd")) {
      document.getElementById("user-pwd").type = "text";
      document.getElementById("eye").style.display = "none";
      document.getElementById("eye-slash").style.display = "inherit";
    }
  };
  const hidePassword = () => {
    if (document.getElementById("user-pwd")) {
      document.getElementById("user-pwd").type = "password";
      document.getElementById("eye-slash").style.display = "none";
      document.getElementById("eye").style.display = "inherit";
    }
  };

  const prepareRegister = () => {
    if (
      document.getElementById("info-container") &&
      document.getElementById("info-doctor") &&
      document.getElementById("login-container") &&
      document.getElementById("login-doctor") &&
      document.getElementById("info-body") &&
      document.getElementById("medicorum-logo")
    ) {
      document.getElementById("medicorum-logo").className =
        "medicorum-logo moveOutUp";
      document.getElementById("login-doctor").className =
        "login-doctor fadeOut";
      document.getElementById("info-doctor").className = "info-doctor fadeOut";

      document.getElementById("login-container").className =
        "info-container zoomOut";
      document.getElementById("info-body").className = "info-body zoomOut";
      document.getElementById("info-container").className =
        "info-container slideOutRight";

      const interval = setInterval(() => {
        waitForAnimation();
      }, 300);
      const waitForAnimation = () => {
        clearInterval(interval);
        history.push("/register");
      };
    }
  };

  const handleLogin = (e) => {
    e.preventDefault();
    login({username,password})
      .then((response) => {
        console.log(response)
        localStorage.setItem("accessToken", JSON.stringify(response.accessToken));
        history.push("/chat");
      })
      .catch((error) => {
        if (error.status === 401) {
          notification.error({
            message: "Error",
            description: "Username or Password is incorrect. Please try again!",
          });
        } else {
          notification.error({
            message: "Error",
            description:
              error.message || "Sorry! Something went wrong. Please try again!",
          });
        }
      });
  }
  

  return (
    <div className="outer-container">
      <img
        src={logo}
        id="medicorum-logo"
        className="medicorum-logo slideInDown"
      />
      <div id="login-container" className="login-container slideInUp">
        <h1>Already a member?</h1>
        <h2>Sign-in here</h2>
        <form className="fadeIn" onSubmit={handleLogin}>
          <input
            className="user-id fadeIn"
            name="username"
            value={username}
            onChange={e => setUsername(e.target.value)}
            type="text"
            placeholder="E-mail / Username"
          />
          <input
            id="user-pwd"
            className="user-pwd fadeIn"
            name="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            type="password"
            placeholder="Password"
          />
          <button type="submit" className="sign-in fadeIn ripple">
            <FontAwesomeIcon icon={faArrowRight} />
          </button>
        </form>
        <button id="eye" className="eye fadeIn" onClick={showPassword}>
          <FontAwesomeIcon icon={faEye} />
        </button>
        <button
          id="eye-slash"
          className="eye-slash fadeIn"
          onClick={hidePassword}
        >
          <FontAwesomeIcon icon={faEyeSlash} />
        </button>
        <a className="fg-pwd fadeIn">Got a problem signing up?</a>
      </div>
      <div id="info-container" className="info-container slideInUp">
        <div id="info-body">
          <p className="intro paragraphs fadeIn">
            A social network where you get to interact with healthcare
            professionals.
          </p>
          <p className="ssah paragraphs fadeIn">Stay safe & healthy!</p>

          <button className="join-us fadeIn ripple" onClick={prepareRegister}>
            <FontAwesomeIcon icon={faHandPointRight} /> Join us
          </button>

          <a className="lm-medic fadeIn">Learn more about Medicorum.</a>
        </div>
      </div>
      <img
        src={loginDoctor}
        className="login-doctor slideInRight"
        id="login-doctor"
      />
      <img
        src={infoDoctor}
        id="info-doctor"
        className="info-doctor slideInLeft"
      />
    </div>
  );
}
