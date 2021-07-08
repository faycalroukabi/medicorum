import React , {useState,useEffect} from "react";
import { findChatMessages, getCurrentUser, getUsers } from "../../../../services/ApiCalls";
import avatar from "./avatar.png"
import "./style.css";

function Launcher(props) {
  const [contacts,setContacts] = useState();
  const [currentUser,setCurrentUser] = useState();
  const [discussions,setDiscussions] = useState();
  useEffect(() => {
    getUsers().then(res=>{
      console.log(res)
      setContacts(res)
    })
    getCurrentUser().then(res=>{
      console.log(res)
      setCurrentUser(res)
    })
  }, []);
  const handleToggle = (contact) => {
    findChatMessages(currentUser.username,contact.username).then(res=>props.handleToggle(res,contact));
  };

  

  return (
    <div>
      <ul id="horizontal-list">
        {contacts && currentUser &&
          contacts.map((contact) => (
            contact.id!=currentUser.id?<li key={contact.id}>
              <img
                className="launcher"
                onClick={()=> handleToggle(contact)}
                src={contact.userProfile.profilePictureUrl}
              />
            </li>:null
          ))}
      </ul>
    </div>
  );
}

export default Launcher;
