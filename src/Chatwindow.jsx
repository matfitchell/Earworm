import React from 'react';
import './Chatwindow.css';
import Sidebar from './Components/Sidebar';
import Chat from './Components/Chat';


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