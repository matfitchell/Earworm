import React from 'react';
import './Chatwindow.css';
import Sidebar from './Components/Sidebar';
import Chat from './Components/Chat';
import { app, database, storage } from "./firebase";
import { getAuth } from "firebase/auth";
import { collection, getDocs, doc, updateDoc, getDoc } from "firebase/firestore";

const Chatwindow = (props) => {
    
    return props.trigger ? (
        <div className="chat-home">
            <div className="chat-container">
                <Sidebar/>
                <Chat/>

                
            
            </div>
            <button className="close-chat-btn" onClick={() => {props.setTrigger(false); props.nextClick2()}}>Close</button>
            
        </div>
    ) : (
        ""
    );
};

export default Chatwindow;