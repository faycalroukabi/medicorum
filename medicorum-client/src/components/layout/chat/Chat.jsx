import React, { useEffect, useState, useRef } from "react";
import "react-chat-widget/lib/styles.css";
import Launcher from "./launcher/Launcher";
import {
  findChatMessages,
  getCurrentUser,
  getConcreteMedicaldata,
  getUsers,
  findChatMessage,
} from "../../../services/ApiCalls";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faHandPointRight,
  faArrowRight,
  faEye,
  faEyeSlash,
} from "@fortawesome/free-solid-svg-icons";

import "./style.css";
import {
  Widget,
  addUserMessage,
  renderCustomComponent,
  toggleWidget,
  dropMessages,
  isWidgetOpened,
  addResponseMessage,
} from "react-chat-widget";
import { message } from "antd";

let stompClient = null;
function Component(props) {
  const handleClick = () =>{
    const cmRequest = {
      patientId: props.user,
    doctorId: props.docUser,
    discussionId: props.discussionId,
    messageText: props.message,
    allowVisibility:true
    }
    console.log(cmRequest)
    getConcreteMedicaldata(cmRequest).then(res=>console.log(res))
  }
  return (
    <div>
      <div className={`rcw-${props.type}`}>
        <div className="rcw-message-text">
          <p>{props.message}</p>
        </div>
      </div>
      <FontAwesomeIcon icon={faEye} onClick={handleClick}/>
    </div>
  );
}

function Chat() {
  const fakeDiscussion = {};
  const [currentUser, setCurrentUser] = useState({});
  const [activeContact, setActiveContact] = useState({});
  const activeContactRef = useRef(activeContact);
  const [messages, setMessages] = useState([]);
  const [discussion, setDiscussion] = useState(new Map());
  useEffect(() => {
    connect();
  }, []);

  const connect = () => {
    const Stomp = require("stompjs");
    var SockJS = require("sockjs-client");
    SockJS = new SockJS("http://localhost:9006/ws");
    stompClient = Stomp.over(SockJS);
    stompClient.connect({}, onConnected, onError);
  };

  const onConnected = () => {
    getCurrentUser().then((res) => {
      setCurrentUser(res);
      stompClient.subscribe(
        "/user/" + res.username + "/queue/messages",
        {}
      );
    });
    console.log(currentUser);
  };

  const onError = (err) => {
    console.log(err);
  };

  const handleNewUserMessage = (msg) => {
    if (msg.trim() !== "") {
      const message = {
        senderId: currentUser.username,
        recipientId: activeContact.username,
        senderName: currentUser.name,
        recipientName: activeContact.name,
        content: msg,
        timestamp: new Date(),
      };
      stompClient.send("/app/chat", {}, JSON.stringify(message));

      const newMessages = [...messages];
      newMessages.push(message);
      setMessages(newMessages);
    }
  };

  const handleToggle = (discussion, active) => {
    dropMessages();
    setActiveContact(active);
    console.log(discussion);
    console.log(activeContactRef.current);
    discussion.map((msg) => {
      if (msg.senderId == currentUser.username) {
        addUserMessage(msg.content);
      } else if (msg.recipientId == currentUser.username) {
        addResponseMessage(msg.content);
        console.log(msg)
        renderCustomComponent(Component, { message: msg.content, type:"response", user:active.username, docUser:currentUser.username, discussionId: msg.discussionId }, true);
      }
    });
    toggleWidget();
  };

  return (
    <div id="menu-outer">
      <div className="table">
        {
          <Widget
            handleNewUserMessage={handleNewUserMessage}
            title={
              !!activeContact.userProfile &&
              activeContact.userProfile.displayName
            }
            launcher={() => <Launcher handleToggle={handleToggle} />}
            profileAvatar={
              !!activeContact.userProfile &&
              activeContact.userProfile.profilePictureUrl
            }
            showCloseButton={true}
          />
        }
      </div>
    </div>
  );
}

export default Chat;
