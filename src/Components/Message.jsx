import React, { useContext, useEffect, useRef } from "react";
import { AuthContext } from "../context/AuthContext";
import { ChatContext } from "../context/ChatContext";

const Message = ({message}) => {
  //console.log(message);
  //console.log(message.date);
  //console.log(message.date.toDate());
  
  const { currentUser } = useContext(AuthContext);
  const { currentUserDoc } = useContext(AuthContext);
  const { data } = useContext(ChatContext);

  //console.log("currentUser: ",currentUser);
  //console.log("currentUserDoc: ",currentUserDoc);
  //console.log("data: ",data);

  const ref = useRef();
  const messageDate = message.date.toDate().toLocaleString();
  //console.log("messageDate: ", messageDate);

  useEffect(() => {
    ref.current?.scrollIntoView({ behavior: "smooth" });
  }, [message]);

  return (
    <div ref={ref} className={`message ${message.senderId === currentUser.uid && "owner"}`}>
      <div className="messageInfo">
        <img src={message.senderId == currentUser.uid ? currentUserDoc.profilePicture : data.user.profilePicture} alt="" />
        <span>{messageDate}</span>
      </div>
      <div className="messageContent">
        <p>{message.text}</p>
        {message.img && <img src={message.img} alt="" />}
      </div>
    </div>
  )
};

export default Message;