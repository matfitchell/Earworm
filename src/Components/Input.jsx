import React from 'react';

const Input = () => {
  return (
    <div className='messageInput'>
      <input type='text' placeholder='Type message here...' />
      <div className='send'>
        <img src="/images/attach.png" alt="" />
        <input type="file" style={{display:"none"}} id='file'/>
        <label htmlFor='file'>
          <img src="/images/img.png" alt="" />
        </label>
        <button>Send</button>
      </div>
    </div>
  )
};

export default Input;