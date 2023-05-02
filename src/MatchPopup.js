import React from 'react'
import "./MatchPopup.css";

function MatchPopup(props) {
  return (props.trigger) ? (
    <div className='popup'>
        <div className='popup-inner'>
            
            <div className='popup-img'>{ props.children }</div>
            <div className='container'>
                <button className='close-btn' onClick={() => props.setTrigger(false)}>Continue Matching</button>
                </div>
                
        </div>
    </div>
  ) : "";
}

export default MatchPopup