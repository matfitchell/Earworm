// import React from "react";
// import ReactDOM from 'react-dom';


// export default function Login() {

//     const Login = async () => {
//     const client_id = "2100da3530bc4465b471b768a7309a4a";
//     const redirect_uri = "http://localhost:3000/";
//     const api_uri = "https://accounts.spotify.com/authorize";
//     const scope = [
      
//       "user-read-private",
//       "user-read-email",
//       "user-modify-playback-state",
//       "user-read-playback-state",
//       "user-read-currently-playing",
//       "user-read-recently-played",
//       "user-top-read",
//       "playlist-read-private",
//       "playlist-read-collaborative",
//       "playlist-modify-private",
//       "playlist-modify-public",
//       "user-read-playback-position",
//       "user-top-read",
//       "user-read-recently-played",
//       ];

//       const [token, setAccessToken] = useState('');



      
      
//       const handleLogin = async (event) => {
//           event.preventDefault();
      
//           const url = `https://accounts.spotify.com/authorize?client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}&scope=${encodeURIComponent(scopes)}&response_type=token&show_dialog=true`;
      
//           const newWindow = window.open(url, '_blank', 'width=600,height=800');
      
//           const handleWindowClose = () => {
//             const token = newWindow.location.hash.substr(1).split('&')[0].split('=')[1];
//             setAccessToken(token);
//             window.removeEventListener('message', handleWindowClose);
//           };
      
//           window.addEventListener('message', handleWindowClose);
//         };
      
//         return (
//           <div>
//             <button onClick={handleLogin}>Login with Spotify</button>
//             {token && <p>Access token: {token}</p>}
//           </div>
//         );
//       }

  
//     }