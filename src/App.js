import React, { useContext } from 'react'
import { useState, useRef, useEffect} from 'react';
import {Navigate, useNavigate} from 'react-router-dom';
import './Homepage';
import './App.css';
import { app, database } from './firebase';
import { getAuth, createUserWithEmailAndPassword, onAuthStateChanged, signInWithEmailAndPassword } from "firebase/auth";
import { collection, addDoc, CollectionReference, setDoc, doc } from 'firebase/firestore';
import { AuthContext } from './context/AuthContext';




function App() {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [users, setUser] = useState ([      
    {username: "mitchman",
    password: "pass1"},{
    username: "coolguy69",
    password: "pass2"}
  ]);

  const [signupForm, setSignupFrom] = useState (false);
  const [hideButtons, setHideButtons] = useState (true);
  
  const [zipCode, setZipcode] = useState('');

  // hide& show div
  const showSignUp = () => {
    setSignupFrom (true); 
    hideBttn();
  } 
  const hideSignUp = () => {
    setSignupFrom (false); 
    showBttn();
  } 
  const hideBttn = () => setHideButtons (false);
  const showBttn = () => setHideButtons (true);


 
  const navigate = useNavigate();
  let auth = getAuth();
  

  useEffect(() => {
    onAuthStateChanged(auth, (data) =>{
      if(data){
        navigate('/Homepage');
        //alert("logged in");
      }
    })
  }, [])

  const [data, setData] = useState({});
  const collectionRef = collection(database, 'userInfo');
  
  const handleInput = (event) => {
    let newInput = { [event.target.name]: event.target.value };

    setData({ ...data, ...newInput });
  };

  const handleSubmit = () => {
    createUserWithEmailAndPassword(auth, data.email, data.password)
      .then((response) => {
        //console.log(response.user);
        setDoc(doc(database, 'userInfo', auth.currentUser.uid), data);
      })
      .catch((err) => {
        alert(err.message);
      });

      
    
  };

  const handleSignIn = () => {
    signInWithEmailAndPassword(auth, data.email, data.password)
      .catch((err) => {
        alert(err.message);
      })
  }
  
  const {currentUser} = useContext(AuthContext);

  return ( 
    <div className='homeContainer'>   {/*-----Home Container-----*/}
      <div className='flexSide left'> {/*-----left side-----*/}
      
        {/*-----header-----*/}
        <div className="header-content">
          <div className="landing-logo">
            <img id="landing-logo" src ="/images/logo.-removebg-preview.png" alt="test"></img>
          </div>
          <h1 className = "header">EarWorm</h1>
        </div>

        {/*-----user-info-----*/}
        <div className='content-wrapper'>

          {/*-----Log in form-----*/}
          <div className = "userInfo login">
            <label htmlFor = "email">Email: </label>
              <input 
                
                onChange={(event) => handleInput(event)} 
                type = "text" 
                id="usern" 
                name="email" 
                placeholder='Email'
                required/>
            <label htmlFor = "password">Password: </label>
              <input 
                 
                onChange={(event) => handleInput(event)} 
                type = "password" 
                id="pass" 
                name="password" 
                placeholder='Password'
                required
                />
            
            {/*----buttons-----*/}      
            {hideButtons && 
            <div>
            <button className="btn login" onClick={handleSignIn}> Log In </button>
            <button className = "btn signup" onClick={showSignUp}> Sign Up </button>
            </div>
            }
          </div>

          {/*-----Sign up form-----*/}
          {signupForm &&          
            <form className = "userInfo signUp">
            <label htmlFor = "firstName">First Name: </label>
            <input type = "text" id="firstName" name="firstname" placeholder='First Name' required onChange={(event) => handleInput(event)}></input>
            <label htmlFor = "lastName">Last Name: </label>
            <input type = "text" id="lastname" name="lastname" placeholder="Last Name" required onChange={(event) => handleInput(event)}></input>
            <label  type="username" htmlFor = "username">Username: </label>
            <input type = "username" id="lastname" name="username" placeholder='Username' required onChange={(event) => handleInput(event)}></input>
            <label htmlFor = "birthday">Date of Birth: </label>
            <input type = "date" id="birthday" name="birthday" onChange={(event) => handleInput(event)}></input>
            <label htmlFor = "zipcode">Zip Code: </label>
            <input onChange={(event) => handleInput(event)} type = "zipcode" id="zipcode" name="zipcode" placeholder='Zipcode' required></input>
            
            <button type = "button" className = "btn signup" onClick={handleSubmit}> Sign Up </button>
            <button type = "button" className = "btn back" onClick={hideSignUp} >Back </button>
          </form>
          }
        </div> {/*-----end content-wrapper-----*/}

        {/*-----footer-----*/}
        <footer className="footer">
          <pre><a href=" ">About</a>  <a href="https://github.com/matfitchell/Earworm" target=" ">Github</a>  <a href=" ">Contact</a>    || By Keyboard Buddies 2022</pre>
        </footer>

        {/*<> {success ?( 
            <div> <h1>nice</h1></div>):(
            //https://www.youtube.com/watch?v=X3qyxo_UTR4
        */}
      </div>{/*-----end flexSide left-----*/}
    </div>//end homeContainer
    //<Routes>
    //<Route path = "/pages/Homepage" element ={<Homepage/>}  />
    //</Routes>
  
    )
}

export default App;

