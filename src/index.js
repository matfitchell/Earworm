import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes, BrowserRouter } from 'react-router-dom';
import App from './App';
import Homepage from './Homepage';
import { AuthContextProvider } from './context/AuthContext';



{/*-----use state for login to sign up idk-----*/}
{/*const [currentForm, setCurrentForm] = useState ('login');*/}

ReactDOM.createRoot(document.getElementById('root')).render(
    <AuthContextProvider>
      <BrowserRouter>
        <Routes> 
          <Route path='/' element={<App/>}/>
          <Route path = "/Homepage" element={<Homepage />}/>
        </Routes>
      </BrowserRouter>
    </AuthContextProvider> 
);


