import { doc, onSnapshot } from "firebase/firestore";
import React, { useState, useEffect, useContext } from 'react';
import { AuthContext } from "../context/AuthContext";
import { database } from "../firebase";



const Chats = () => {
    const [chats , setChats] = useState([]); 

    const { currentUser } = useContext(AuthContext);
    const { currentUserDoc} = useContext(AuthContext);


    useEffect(() => {
        const getChats = () => {
            console.log("currentUser.uid: ", currentUser.uid);
            const unsub = onSnapshot(doc(database, "userChats", currentUser.uid), (doc) => {
                console.log("userChat for this user: ", doc.data());
                setChats(doc.data());
            });

            return () => {
                unsub();
            };
        };

        currentUser.uid && getChats();
    }, [currentUser.uid]);

    //if(chats){
      //  console.log("chats/userChats: ", chats);
        //console.log(Object.entries(chats));
        //console.log(Object.entries(chats)?.map((chat) => chat[0]));
        //console.log(Object.entries(chats)?.map((chat) => chat[1]));
    //} else {
      //  console.log("no chats/userChats");
    //}
    
    return (
        <div className='chats'>
        <div className="userChat">
                <img className="otherUserImg" src="/images/1.png" alt="Chat User" />
                <div className="userChatInfo">
                    <span>This User</span>
                    <p>Hello There!</p>
                </div>
            </div>
        </div>    
    );
   
};

export default Chats;