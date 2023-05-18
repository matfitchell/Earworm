import React from "react";
import "./MatchPopup.css";
import Chatwindow from "./Chatwindow";
import { AuthContext } from "./context/AuthContext";
import { useState, useContext } from "react";
import { useNavigate } from "react-router-dom";

function MatchPopup(props) {
  const { currentUserDoc } = useContext(AuthContext);
  const [chatButtonPopup, setChatButtonPopup] = useState(false);
const navigate = useNavigate();
  
  return props.trigger ? (
    <div className="popup">
      <div className="popup-inner">
        <h2>You have matched with {props.firstName}!</h2>
        {props.children}
          <div className='userChatButton'>
            <button className="message-btn" onClick={() => navigate("/chats")}>Message</button>
            
            
        </div>
        <button className="close-btn" onClick={() => {props.setTrigger(false); props.nextClick2()}}>Continue Matching</button>
      </div>
    </div>
  ) : (
    ""
  );
}
export default MatchPopup;
