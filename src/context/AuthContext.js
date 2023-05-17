import React from 'react';
import { createContext, useEffect, useState } from "react";
import { auth } from "../firebase";
import { onAuthStateChanged } from "firebase/auth";
import { doc, getDoc } from "firebase/firestore";
import { database } from "../firebase";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {

    const [currentUser, setCurrentUser] = useState({});
    const [currentUserDoc, setCurrentUserDoc] = useState({});

    useEffect(() => {
      const unsub = onAuthStateChanged(auth, (user) => {
        setCurrentUser(user);
        //console.log(user);
        
        const getCurrentUserInfo = async () => {
          if(user) {
            //console.log("We have a currently signed in user and userInfo doc...");
            const docSnap = await getDoc(doc(database, 'userInfo', user.uid));
            //console.log("docSnap: ", docSnap);
            //console.log("doscSnap.data(): ", docSnap.data());
            setCurrentUserDoc(docSnap.data());
            //console.log("currentUserDoc: ", currentUserDoc);
          }
        };

        getCurrentUserInfo();
        
      });
      
      return () => {
        unsub();
      };
      
    }, []);
    
    return (
      <AuthContext.Provider value={{ currentUser, currentUserDoc }}>
        {children}
      </AuthContext.Provider>
    );
};