import React from 'react';
import Messages from './Messages';
import Input from './Input';

const Chat = () => {
  return (
    <div className='chat'>
        <div className="chatInfo">
          <span>Al-Test</span>
          <div className="chatIcons">
            <img className="chatIconImg" src="/images/cam.png" alt="" />
            <img className="chatIconImg" src="/images/add.png" alt="" />
            <img className="chatIconImg" src="/images/more.png" alt="" />
          </div>
        </div>
        <Messages />
        <Input />
    </div>
  )
};

export default Chat;