import React, {useState} from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes, BrowserRouter } from 'react-router-dom';
import App from './App';
import Homepage from './Homepage';



{/*-----use state for login to sign up idk-----*/}
{/*const [currentForm, setCurrentForm] = useState ('login');*/}

ReactDOM.createRoot(document.getElementById('root')).render(
    <BrowserRouter>
      <Routes> 
        <Route path='/' element={<App/>}/>
        <Route path = "/Homepage" element={<Homepage />}/>
      </Routes>
    </BrowserRouter>
);


