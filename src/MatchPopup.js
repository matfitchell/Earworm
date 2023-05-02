import React from 'react'
import "./MatchPopup.css";

function MatchPopup(props) {
  return (props.trigger) ? (
    <div className='popup'>
        <div className='popup-inner'> 
        <h2>You have matched with {props.firstName}!</h2> 
          { props.children }
          
            <button className='message-btn'>Message</button>
            <button className='close-btn' onClick={() => props.setTrigger(false)}>Continue Matching</button>
          
      </div>  
    </div>
  ) : "";
}
export default MatchPopup