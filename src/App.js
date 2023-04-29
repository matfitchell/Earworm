import { useState, useRef, useEffect} from 'react';
import {redirect} from 'react-router-dom';
import './Homepage';
import './App.css';


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


  // for authentication purposes ?lol
  const userLogIn = (e) =>{
    e.preventDefault();
    const foundUser = users.find((user) => user.username === username && user.password ===password);

    if (foundUser){
      setIsLoggedIn(true);
    } else {
      alert ('Invalid user and password');
    }
  }

  if (isLoggedIn){
    return <redirect to = "./Homepage"/>;
  }

  
  return ( 
    <div className='homeContainer'>   {/*-----Home Container-----*/}
      <div className='flexSide left'> {/*-----left side-----*/}
      
        {/*-----header-----*/}
        <div class="header-content">
          <div class="landing-logo">
            <img id="landing-logo" src ="/images/logo.-removebg-preview.png" alt="test"></img>
          </div>
          <h1 class = "header">EarWorm</h1>
        </div>

        {/*-----user-info-----*/}
        <div className='content-wrapper'>

          {/*-----Log in form-----*/}
          <form onSubmit = {userLogIn} class = "userInfo login">
            <label for = "username">Username: </label>
              <input 
                value = {username
                } 
                onChange = {(e)=> setUsername (e.target.value)} 
                type = "text" 
                id="usern" 
                name="username" 
                required/>
            <label for = "password">Password: </label>
              <input 
                value = {password} 
                onChange = {(e)=> setPassword (e.target.value)} 
                type = "password" 
                id="pass" 
                ame="password" 
                required/>
            
            {/*----buttons-----*/}      
            {hideButtons && 
            <div>
            <button type="submit" class="btn login"> Log In </button>
            <button class = "btn signup" onClick={showSignUp}> Sign Up </button>
            </div>
            }
          </form>

          {/*-----Sign up form-----*/}
          {signupForm &&          
          <form class = "userInfo signUp">
            <label for = "firstName">First Name: </label>
            <input type = "text" id="firstName" name="firstname" required ></input>
            <label for = "lastName">Last Name: </label>
            <input type = "text" id="lastName" name="lastName" required></input>
            <label for = "userEmail">Email: </label>
            <input type = "email" id="userEmail" name="userEmail" required></input>
            <label for = "birthday">Date of Birth: </label>
            <input type = "date" id="birthday" name="birthday"></input>
            
            <button type = "button" class = "btn signup"> Sign Up </button>
            <button type = "button" class = "btn back" onClick={hideSignUp} >Back </button>
          </form>
          }
        </div> {/*-----end content-wrapper-----*/}

        {/*-----footer-----*/}
        <footer class="footer">
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
