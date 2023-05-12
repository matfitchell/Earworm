import React from 'react';

const Message = () => {
  return (
    <div className='message owner'>
      <div className="messageInfo">
        <img src="/images/1.png" alt="" />
        <span>Just Now</span>
      </div>
      <div className="messageContent">
        <p>Hello There!</p>
        <img src="/images/1.png" alt='' />
      </div>
    </div>
  )
};

export default Message;