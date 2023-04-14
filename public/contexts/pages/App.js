import { useState } from 'react';
import Homepage from './Homepage';
import './App.css';


function App() {
  const [usern, setUser] = useState('');
  const [pass, setPass] = useState('');
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


  const userLogIn = (e) =>{
    e.preventDefault();
    console.log(usern);
  }

  return (
    <div className='homeContainer'>   {/*-----Home Container-----*/}
      <div className='flexSide left'> {/*-----left side-----*/}

        {/*-----header-----*/}
        <div class="header-content">
          <div class="landing-logo">
            <img id="landing-logo" src ="/images/logo.-removebg-preview.png"></img>
          </div>
          <h1 class = "header">EarWorm</h1>
        </div>

        {/*-----user-info-----*/}
        <div className='content-wrapper'>

          {/*-----Log in form-----*/}
          <form onSubmit = {userLogIn} class = "userInfo login">
            <label for = "username">Username: </label>
            <input value = {usern} onChange = {(e)=> setUser (e.target.value)} type = "text" id="usern" name="username"></input>
            <label for = "password">Password: </label>
            <input value = {pass} onChange = {(e)=> setPass (e.target.value)} type = "password" id="pass" name="password"></input>
            
            {/*----buttons-----*/}      
            {hideButtons && 
            <div>
            <button class = "btn login" type="submit"> Log In </button>
            <button class = "btn signup" onClick={showSignUp}> Sign Up </button>
            </div>
            }
          </form>

          {/*-----Sign up form-----*/}
          {signupForm &&          
          <form class = "userInfo signUp">
            <label for = "firstName">First Name: </label>
            <input type = "text" id="firstName" name="firstname"></input>
            <label for = "lastName">Last Name: </label>
            <input type = "text" id="lastName" name="lastName"></input>
            <label for = "userEmail">Email: </label>
            <input type = "email" id="userEmail" name="userEmail"></input>
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
      </div>{/*-----end flexSide left-----*/}
    </div>//end homeContainer
  );
}
export default App;
