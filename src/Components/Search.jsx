import React, { useContext, useState } from 'react';
import { collection, query, where, getDocs, setDoc, doc, updateDoc, serverTimestamp, getDoc } from "firebase/firestore";
import { database } from "../firebase";
import { AuthContext } from "../context/AuthContext";

const Search = () => {
  const [username, setUsername] = useState("");
  const [user, setUser] = useState(null);
  const [err, setErr] = useState(false);

  const { currentUser } = useContext(AuthContext);
  const { currentUserDoc } = useContext(AuthContext);

  const handleSearch = async () => {
      const q = query(
        collection(database,"userInfo"),
        where("username", "==", username)
      );

      try {
        const querySnapshot = await getDocs(q);
        querySnapshot.forEach((doc) => {
          setUser(doc.data());
        });
      } catch (err) {
        setErr(true);
      }
  };

  const handleKey = (e) => {
    e.code === "Enter" && handleSearch();
  };

  const handleSelect = async () => {
    
    console.log("currentUser.uid: ", currentUser.uid);
    console.log("currentUserDoc: " ,currentUserDoc);
    console.log("user: ", user);
    console.log("user.id: ", user.uid);

    //check whether the group(chats in firestore) exists, if not create
    const combinedId =
      currentUser.uid > user.uid
        ? currentUser.uid + user.uid
        : user.uid + currentUser.uid;
    try {
      const res = await getDoc(doc(database, "chats", combinedId));

      if (!res.exists()) {
        //create a chat in chats collection
        await setDoc(doc(database, "chats", combinedId), { messages: [] });

        //create user chats for current user 
        await updateDoc(doc(database, "userChats", currentUser.uid), {
          [combinedId + ".userInfo"]: {
            uid: user.uid,
            username: user.username,
            profilePicture: user.profilePicture,
          },
          [combinedId + ".date"]: serverTimestamp(),
        });
        //create user chats for user
        await updateDoc(doc(database, "userChats", user.uid), {
          [combinedId + ".userInfo"]: {
            uid: currentUser.uid,
            username: currentUserDoc.username,
            profilePicture: currentUserDoc.profilePicture,
          },
          [combinedId + ".date"]: serverTimestamp(),
        });
      }
    } catch (err) {}

    setUser(null);
    setUsername("");
  };

  return (
    <div className='search'>
      <div className="searchForm">
        <input 
        className="searchInput" 
        type="text" 
        placeholder="Find a user" 
        onKeyDown={handleKey} 
        onChange={(e) => setUsername(e.target.value)} 
        value={(username)}
        />
      </div>
      {err && <span>User Not Found!</span>}
      {user && (
        <div className="userChat" onClick={handleSelect}>
          <img className="otherUserImg" src={user.profilePicture} alt="Chat User" />
          <div className="userChatInfo">
            <span>{user.username}</span>
          </div>
        </div>
      )}
    </div>
  );
};

export default Search;