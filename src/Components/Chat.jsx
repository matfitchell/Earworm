import React, { useContext } from 'react';
import Messages from './Messages';
import Input from './Input';
import { ChatContext } from '../context/ChatContext';

const Chat = () => {
  const { data } = useContext(ChatContext);  
  return (
    <div className='chat'>
        <div className="chatInfo">
          <span>{data.user?.username}</span>
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