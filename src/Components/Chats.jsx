import { doc, onSnapshot } from "firebase/firestore";
import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from "../context/AuthContext";
import { ChatContext } from "../context/ChatContext";
import { database } from "../firebase";

const Chats = () => {
    const [chats , setChats] = useState([]); 

    const { currentUser } = useContext(AuthContext);
    //const { currentUserDoc} = useContext(AuthContext);
    const { dispatch } = useContext(ChatContext);

    useEffect(() => {
        const getChats = () => {
            //console.log("currentUser.uid: ", currentUser.uid);
            const unsub = onSnapshot(doc(database, "userChats", currentUser.uid), (doc) => {
                //console.log("userChat for this user: ", doc.data());
                setChats(doc.data());
            });

            return () => {
                unsub();
            };
        };

        currentUser.uid && getChats();
    }, [currentUser.uid]);

    
    //console.log("chats/userChats: ", chats);
    //console.log(Object.entries(chats));
    //console.log(Object.entries(chats)?.map((chat) => chat[0]));
    //console.log(Object.entries(chats)?.map((chat) => chat[1]));

    const handleSelect = (u) => {
        dispatch({ type: "CHANGE_USER", payload: u });
    };
    
    return (
        <div className="chats">
          {Object.entries(chats)?.sort((a,b)=>b[1].date - a[1].date).map((chat) => (
            <div className="userChat" key={chat[0]} onClick={() => handleSelect(chat[1].userInfo)} >
              <img className="otherUserImg" src={chat[1].userInfo.profilePicture} alt="No Photo" />
              <div className="userChatInfo">
                <span>{chat[1].userInfo.username}</span>
                <p>{chat[1].lastMessage?.text}</p>
              </div>
            </div>
          ))}
        </div>
      );
   
};

export default Chats;