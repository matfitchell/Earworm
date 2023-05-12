import React from 'react';
import './Chatwindow.css';
import Sidebar from './Components/Sidebar';
import Chat from './Components/Chat';

const Chatwindow = (props) => {
  return props.trigger ? (
    <div className="popup">
        <div className="popup-inner">
            <Sidebar/>
            <Chat/>

            
            <button className="close-chat-btn" onClick={() => {props.setTrigger(false); props.nextClick2()}}>Close</button>
        </div>
    </div>
  ) : (
    ""
  );
}

export default Chatwindow