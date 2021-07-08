import React, { useEffect } from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import logo from "../../logo.svg";
import { useHistory } from "react-router";
import signupdoctor from "./signupdoctor.png";
import signupmember from "./signupmember.png";
import "./style.css";
import { faArrowRight } from "@fortawesome/free-solid-svg-icons";

export default function Register() {
  const history = useHistory();
  useEffect(() => {
    document.body.style.overflow = "hidden";
    return () => {};
  }, []);
  const handleHpTouch = () => {
    document.getElementById("signup-doctor").style.display="inherit";
    document.getElementById("signup-doctor").className="signup-doctor zoomInUp"
  }
  const handleHpMouseLeave = () => {
    document.getElementById("signup-doctor").className="signup-doctor fadeOut"
  }
  const handleMbTouch = () => {
    document.getElementById("signup-member").style.display="inherit";
    document.getElementById("signup-member").className="signup-member zoomInUp"
  }
  const handleMbMouseLeave = () => {
    document.getElementById("signup-member").className="signup-member fadeOut"
  }
  const prepareHPReg = () => {history.push("/registerhp")};
  const prepareMembReg = () => {history.push("/registermember")};
  return (
    <div className="reg">
      <img src={logo} id="medicorum" className="medicorum" />
      <div className="signup-container">
        <div className="member-container" onMouseEnter={handleMbTouch} onMouseLeave={handleMbMouseLeave}>
          <p className="memb-message paragraphs"> 
            Join today to get a clearer insignt on the progress of your health from verified healthcare professionals.
            Become part of Medicorum community and contribute to spread health awareness.
          </p>
          <a className="memb-link" onClick={prepareMembReg}>
            Become a member today <FontAwesomeIcon icon={faArrowRight} />
          </a>
        </div>
        <div className="hp-container"  onMouseEnter={handleHpTouch} onMouseLeave={handleHpMouseLeave}>
          <p className="hp-message paragraphs">
            Become more in touch with your clients, and have a better understanding of their health thanks to advanced tools that you will have access to as a healthcare professional.
          </p>
          <a className="hp-link" onClick={prepareHPReg} >
            Register as a healthcare professional{" "}
            <FontAwesomeIcon icon={faArrowRight} />
          </a>
        </div>
      </div>
      <img
        id="signup-doctor"
        src={signupdoctor}
        className="signup-doctor zoomInUp"
      />
      <img
        id="signup-member"
        src={signupmember}
        className="signup-member zoomInUp"
      />
    </div>
  );
}
