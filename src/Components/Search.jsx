import React from 'react'

const Search = () => {

  return (
    <div className='search'>
      <div className="searchForm">
        <input className="searchInput" type="text" placeholder="Find a user" />
      </div>
      <div className="userChat">
        <img className="otherUserImg" src="/images/1.png" alt="Chat User" />
        <div className="userChatInfo">
          <span>This User</span>
        </div>
      </div>
    </div>
  )
};

export default Search;