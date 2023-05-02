import React, {useState} from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes, BrowserRouter } from 'react-router-dom';
import App from './App';
import Homepage from './Homepage';
import Run from './runner';



const root = ReactDOM.createRoot(document.getElementById('root'));
{/*const [currentForm, setCurrentForm] = useState ('login');*/}

root.render(
  <BrowserRouter>
    <Routes> 
      <Route index element={<Run />}/>
      <Route path = "/runner" element={<Run />}/>
    </Routes>
  </BrowserRouter>
);