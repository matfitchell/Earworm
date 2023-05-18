import React from 'react';
import './Chatwindow.css';
import Sidebar from './Components/Sidebar';
import Chat from './Components/Chat';
import { AuthContext } from "./context/AuthContext";
import { useContext } from 'react';
import { useNavigate } from "react-router-dom";

const Chatwindow = (props) => {
    const { currentUserDoc } = useContext(AuthContext);
    const navigate = useNavigate();

    return (
        <div className='b-body'>    {/*-----delete??-----*/}
    <div className='homepageContainer'> {/*-----Home Container-----*/}
      <section className="flexHomepage sidePart"> {/*-----left side-----*/}
       {/*-----header-----*/}
       <div className="header-content">
          <div className="homepage-logo">
              <img id="homepage-logo" src ="/images/logo.-removebg-preview.png"/>
          </div>
          <header className="homepageHeader"><h2>EarWorm</h2></header>
        </div>

        {/*-----left side: user info-----*/}
        <div className="userInfo">
          <div className="displayPhoto"><img src = {currentUserDoc.profilePicture == null ? '/images/logo..jpg' : currentUserDoc.profilePicture} className="displayImg" alt="No Profile Photo"/></div>
          <span className="userFirstName"> {currentUserDoc.firstname} </span>
          <span className="username">{currentUserDoc.username}</span>
          {/*<span className="currentUserUsername">{currentUserDoc.email}</span>*/}
        </div>

        {/*-----buttons/navigation-----*/}
        <div className="nav">
            <button className = "button-home" id='home' onClick={()=>{navigate("/Homepage")}}>Continue Matching</button>
            
            {/* <button className = "button settings" onClick={getData}>Get Data</button> */}
        </div>
      </section> {/*-----end of left side-----*/}

        
            {/* <button className="close-chat-btn" onClick={() => {props.setTrigger(false);}}>Close</button> */}
            <div className="chat-home">
            <div className="chat-container">
                <Sidebar/>
                <Chat/>

                
            
            </div>
        </div>
        
        </div>
        
        </div>
    );
};

export default Chatwindow;