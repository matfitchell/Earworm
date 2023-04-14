import React, {useState} from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router } from 'react-router-dom';
import App from './App';
import Homepage from './Homepage';

const root = ReactDOM.createRoot(document.getElementById('root'));

{/*-----use state for login to sign up idk-----*/}
{/*const [currentForm, setCurrentForm] = useState ('login');*/}

root.render(
    <React.StrictMode>
    <Router>
      <App />
      <Homepage />
    </Router>
  </React.StrictMode>
);


