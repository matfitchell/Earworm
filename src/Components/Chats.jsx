import React from 'react';

const Chats = () => {
  return (
    <div className='chats'>
      <div className="userChat">
            <img className="otherUserImg" src="/images/1.png" alt="Chat User" />
            <div className="userChatInfo">
                <span>This User</span>
                <p>Hello There!</p>
            </div>
        </div>
        Test
        <div className="userChat">
            <img className="otherUserImg" src="/images/1.png" alt="Chat User" />
            <div className="userChatInfo">
                <span>This User</span>
                <p>Hello There!</p>
            </div>
        </div>
        <div className="userChat">
            <img className="otherUserImg" src="/images/1.png" alt="Chat User" />
            <div className="userChatInfo">
                <span>That User</span>
                <p>See you soon!</p>
            </div>
        </div>
        <div className="userChat">
            <img className="otherUserImg" src="/images/1.png" alt="Chat User" />
            <div className="userChatInfo">
                <span>That User</span>
                <p>Let's listen together!</p>
            </div>
        </div>
    </div>
  )
};

export default Chats;