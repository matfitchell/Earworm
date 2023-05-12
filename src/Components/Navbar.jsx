import React from 'react'

const Navbar = () => {
  return (
    <div className='navbar'>
      <span className="logo">EarWorm Chat</span>
      <div className="user">
        <img className="userImg" src="/images/1.png" alt="User"  />
      </div>
      <span>Al-Test</span>
      <button>Close</button>
    </div>
  )
}

export default Navbar