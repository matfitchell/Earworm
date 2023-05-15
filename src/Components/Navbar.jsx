import React, { useContext} from 'react';
import { AuthContext } from '../context/AuthContext';

const Navbar = () => {
  {/* Added this here to get the currentUser from the AuthContext component */}
  const {currentUser} = useContext(AuthContext);
  const {currentUserDoc} = useContext(AuthContext);

  return (
    <div className='navbar'>
      <span className="logo">EarWorm Chat</span>
      <div className="user">
        <img className="userImg" src={currentUserDoc.profilePicture} alt="User"  />
      </div>
      <span>{currentUserDoc.username}</span>
      <button>Close</button>
    </div>
  )
}

export default Navbar