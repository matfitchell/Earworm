import React, { useState, useRef, useEffect, useContext } from 'react';
import './Homepage.css'
import { useNavigate } from "react-router-dom";
import MatchPopup from './MatchPopup';
import Chatwindow from './Chatwindow';
import { app, database, storage } from "./firebase";
import { getAuth, signOut, onAuthStateChanged } from "firebase/auth";
import { collection, getDocs, doc, updateDoc, getDoc, setDoc, query, where } from "firebase/firestore";
import { uploadBytes, ref, getDownloadURL } from 'firebase/storage';
import axios from 'axios';
import { AuthContext } from './context/AuthContext';

function Homepage() {
    {/* Added this here to get the currentUser from the AuthContext component */}
    const {currentUser} = useContext(AuthContext);
    const {currentUserDoc} = useContext(AuthContext);
    let usersSwiped;
    let auth = getAuth();
    let user = auth.currentUser;
    const navigate = useNavigate();
    const zipCodeData = require('zipcode-city-distance');
    //let userssss;
    //let passes;
    //FULL TRANSPARENCY, IDK HOW WHY. I JUST GOOGLED, STACK OVERFLOW'D AND CHATGPT'D PLEASE HAVE MERCY --NATH :D
    //ALSO, I used my client ID cause I was thinking what if I used a different client ID. 22564e175af6486d82075db9d583c551 
    const [clientId, setClientId] = useState('2100da3530bc4465b471b768a7309a4a');
    //const [redirectUri, setRedirectUri] = useState('http://localhost:3000/Homepage');
    const [redirectUri, setRedirectUri] = useState('https://earworm-1200e.web.app/Homepage');
    const [scopes, setScopes] = useState([
        "user-read-private",
        "user-read-email",
        "user-modify-playback-state",
        "user-read-playback-state",
        "user-read-currently-playing",
        "user-read-recently-played",
        "user-top-read",
        "playlist-read-private",
        "playlist-read-collaborative",
        "playlist-modify-private",
        "playlist-modify-public",
        "user-read-playback-position",
        "user-top-read",
        "user-read-recently-played"
    ]);
  
    const [accessToken, setAccessToken] = useState('');
    const [spotifyUsername, setSpotifyUsername] = useState('');
    const [topSongs, setTopSongs] = useState([]);
    const [topThree, setTopThree] = useState([]);
    const [genres, setGenres] = useState([]);


    useEffect(() => {
      const handleAuthorization = () => {
        const params = new URLSearchParams(window.location.hash.substr(1));
        const token = params.get('access_token');
  
        if (token) {
          setAccessToken(token);
          getSpotifyProfile(token);
        }
      };
      handleAuthorization();
    }, []);
  
    const handleLogin = () => {
      const url = `https://accounts.spotify.com/authorize?client_id=${clientId}&redirect_uri=${encodeURIComponent(redirectUri)}&scope=${encodeURIComponent(scopes.join(' '))}&response_type=token`;
      window.location.href = url;
    };
  
    const getSpotifyProfile = async (token) => {
      try {
        const response = await fetch('https://api.spotify.com/v1/me', {
          headers: {
            'Authorization': `Bearer ${token}`
          }
        });
  
        const data = await response.json();
        const { display_name } = data;
        setSpotifyUsername(display_name);
        console.log(`Spotify username: ${display_name}`);
      } catch (error) {
        console.log(error);
      }
    };
  

    //Check out the console when you pull top songs, pulls results, can't slice properly: 
    // TypeError: _data2.name is undefined
    const getTopSongs = async () => {
      try {
        const response = await fetch('https://api.spotify.com/v1/me/top/tracks', {
          headers: {
            'Authorization': `Bearer ${accessToken}`
          }
        });
        const data = await response.json();
        console.log('Top songs:', data.items);
        setTopSongs(data.items);

        
      } catch (error) {
        console.error(error);
      }
       
 
    };


    // get genres of top 20 songs
    
        const fetchGenres = async () => {
          try {
            const response = await fetch('https://api.spotify.com/v1/me/top/tracks', {
              headers: {
                'Authorization': `Bearer ${accessToken}`
              }
            });

            const data = await response.json();
            console.log(data.items)
            const trackIds = data.items.map(item => item.id);
            const tracksResponse = await fetch(`https://api.spotify.com/v1/tracks/?ids=${trackIds.join(',')}`, {
              headers: {
                'Authorization': `Bearer ${accessToken}`
              }
            });

            const tracksResponseData = await tracksResponse.json();

            console.log("tracks :" , tracksResponseData.tracks);

            const artistIds = tracksResponseData.tracks.map(track => track.artists[0].id);
            const artistsResponse = await fetch(`https://api.spotify.com/v1/artists/?ids=${artistIds.join(',')}`, {
              headers: {
                'Authorization': `Bearer ${accessToken}`
              }
            });

            const artistsResponseData = await artistsResponse.json();

            console.log("Artists", artistsResponseData.artists);

            const genreCounts = artistsResponseData.artists.reduce((acc, curr) => {
              curr.genres.forEach(genre => {
                if (acc[genre]) {
                  acc[genre]++;
                } else {
                  acc[genre] = 1;
                }
              });
              return acc;
            }, {});
    
            const genres = Object.entries(genreCounts).sort((a, b) => b[1] - a[1]).map(entry => ({
              name: entry[0],
              count: entry[1]
            }));

            let topGenre = genres[0]
            setDoc(doc(database, 'musicTaste', auth.currentUser.uid), topGenre)
            setGenres(genres);
          } catch (error) {
            console.error(error);
          }
        };
        
      
    


    const getTopThree = async () => {
        try {
          const response = await fetch('https://api.spotify.com/v1/me/top/tracks?limit=3', {
            headers: {
              'Authorization': `Bearer ${accessToken}`
            }
          });
            const data = await response.json();
            setTopThree(data.items);        
            console.log(data)
            
        } catch (error) {
          console.log(error);
        }
      };

    //Gets all users from firstore and stores them in users
    //Get current user's document to be able to extract their data

    const collectionRef = collection(database, "userInfo"); 
    const [users, setUsers] = useState([]);
    const [Array, setArray] = useState([]);
    const [index, setIndex] = useState(0);
    const [imageUpload, setImageUpload] = useState(null);
    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [email, setEmail] = useState("");
    const [username, setUsername] = useState("");
    const [zipcode, setZipcode] = useState("");
    const [bio, setBio] = useState("");
    const [data, setData] = useState({});
    const [currentIndex, setCurrentIndex] = useState(0);
    const [profilePictureUrl, setProfilePictureUrl] = useState(null);
    const [preferredDist, setPreferredDist] = useState(0);
    
    const addBio = () =>{
        let newBio = { bio: document.getElementById('bio-input').value };
        
        if (user !== null && newBio != null) {
            updateDoc(doc(database, "userInfo", auth.currentUser.uid), newBio)
            .then(docRef => {
                setBio(newBio.bio);
            })
            .catch(error =>{
                console.log(error);
            })
        
        }
        
    }
    const addImageUrl = (url) => {
        let newImageUrl = { profilePicture: url};

        if(user !== null && newImageUrl != null){
            updateDoc(doc(database, "userInfo", auth.currentUser.uid), newImageUrl)
            .then(docRef => {
                
            })
            .catch(error =>{
                console.log(error);
            })
        }
    }
    

    //buttons 
    const [userDefault, setUserDefault] = useState (true);
    const [userProfile, setUserProfile] = useState (false);
    const [userSettings, setUserSettings] = useState (false);
    const [buttonPopup, setButtonPopup] = useState(false);
    const [chatButtonPopup, setChatButtonPopup] = useState(false);
    

    const showProfile = () => {
        setUserDefault (false);
        setUserProfile (true);
        setUserSettings (false);
    }

    const showSettings = () => {
        setUserDefault (false);
        setUserProfile (false);
        setUserSettings (true);
    }

    const showDefault = () => {
        setUserDefault (true);
        setUserProfile (false);
        setUserSettings (false);
    }

    
    const handleInput = (event) => {
        let newInput = {[event.target.name]: event.target.value};

        setData({...data, ...newInput});
    };
    

    const handlelogout = () => {
        signOut(auth);
    }

    //function calcDistance(lat1, lon1, lat2, lon2) {
    //    let dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * 6371;
    //    return dist;
    //}

    // const getData = async () => {
    //   const data = await getDocs(collectionRef);
    //   console.log(data.docs.map((item) => {
    //     return {...item.data(), id: item.id}
    //   }));
    // }
    // const updateData = (id, biog) => {
    //     let dataToUpdate = doc(database, 'userInfo', id)
    //     updateDoc(dataToUpdate, {
    //         bio: biog
    //     })
    //     .then(() => {
    //         alert('Bio added');
    //         getData()
    //     })
    //     .catch((err) => {
    //         alert(err.message);
    //     })
    // }
    function updateArrayIndex(){
      if(users.length == 0) return;
      if(currentIndex>=users.length-1){
        setUsers([]);
      }else{
        setCurrentIndex((prevIndex) => (prevIndex + 1));
      }
    }
    function swipeLeft() {
      if(!users[currentIndex]) return;
      const userSwiped = users[currentIndex];
      setDoc(doc(database, 'userInfo', auth.currentUser.uid, 'passes', userSwiped.id), userSwiped);
      updateArrayIndex();
    }
    function swipeRight() {
      if(!users[currentIndex]) return;
      const userSwiped = users[currentIndex];
      setDoc(doc(database, 'userInfo', auth.currentUser.uid, 'swipes', userSwiped.id), userSwiped);
      updateArrayIndex();
    }

    async function checkIfMatched(){
      if(!users[currentIndex]) return;
      const swipes = await getDocs(collection(database, 'userInfo', users[currentIndex].id, 'swipes')).then(
        (snapshot) => snapshot.docs.map((doc) => doc.id)
      )
      if(swipes.includes(auth.currentUser.uid)){
        setButtonPopup(true);
      }else{
        swipeRight();
      }
    }
    
    

    const uploadImage = () =>{
        if(imageUpload == null) return;

        const imageRef = ref(storage, `images/${auth.currentUser.uid}`);
        uploadBytes(imageRef, imageUpload).then(() => {
             getDownloadURL(imageRef).then((url) => {
                addImageUrl(url);
                setProfilePictureUrl(url);
             })
        })
       
    }

    
      
      

    useEffect(() =>{
        onAuthStateChanged(auth, (data) => {
           if (data) {
             navigate('/Homepage');
             //alert("logged in");
           }else{
            navigate('/');
           }
         });
         
        async function getUsersFromFirestore(){
           
          
            const data = await getDocs(collection(database, "userInfo"));
            const passes = await getDocs(collection(database, 'userInfo', auth.currentUser.uid, 'passes')).then(
              (snapshot) => snapshot.docs.map((doc) => doc.id)
            );

            const swipes = await getDocs(collection(database, 'userInfo', auth.currentUser.uid, 'swipes')).then(
              (snapshot) => snapshot.docs.map((doc) => doc.id)
            )

            //console.log(passes);
            const userssss = data.docs.filter((doc) => doc.id !== auth.currentUser.uid).map((doc) => ({ ...doc.data(), id: doc.id}));
            const docSnap = await getDoc(doc(database, 'userInfo', auth.currentUser.uid));
            //console.log(userssss)
            var tempArray = [];
            //check distance before adding to users array
            let x = 0;
            let testVal = 100;
            for (x = 0; x < userssss.length; x++){
                let myZip = docSnap.data().zipcode.toString();
                let theirZip = userssss[x].zipcode.toString();
                let zipCodeDistance = zipCodeData.zipCodeDistance(myZip, theirZip,'M');
                //console.log(zipCodeDistance);
                if (zipCodeDistance <= testVal && !passes.includes(userssss[x].id) && !swipes.includes(userssss[x].id)){
                    tempArray.push(userssss[x]);
                }
            }
            
            setUsers(tempArray);
        }
        getUsersFromFirestore();
        
    }, []);

    const getCurrentUserInfo = async () => {
            //const userData = database.collection("userInfo").doc(auth.currentUser.uid).get();
            // const docRef = doc(database, "userInfo", auth.currentUser.uid);
            const currentUser = auth.currentUser;
        if (currentUser && currentUser.uid) { // <-- Use a null check instead of optional chaining
                const docSnap = await getDoc(doc(database, 'userInfo', auth.currentUser.uid));
                
                setFirstname(docSnap.data().firstname);
                setLastname(docSnap.data().lastname);
                setEmail(docSnap.data().email);
                setUsername(docSnap.data().username);
                setZipcode(docSnap.data().zipcode);
                
                try{
                    setBio(docSnap.data().bio);
                }catch(error){
                    console.error();
                }

                //getDownloadURL( ref(storage, `images/${auth.currentUser.uid}`)).then((url) => {
                //setProfilePictureUrl(url);
                //});
            }  
        };

        useEffect(() =>{
          getCurrentUserInfo();
        },[auth.currentUser])
    return (
        <div className='b-body'>    {/*-----delete??-----*/}
            <div className='homepageContainer'> {/*-----Home Container-----*/}
                <section className="flexHomepage sidePart"> {/*-----left side-----*/}

                    {/*-----header-----*/}
                    <div className="header-content">
                        <div className="homepage-logo">
                            <img id="homepage-logo" src ="/images/logo.-removebg-preview.png"/>
                        </div>
                        <header className="homepageHeader"><h2>EarWorm</h2></header>
                    </div>

                    {/*-----left side: user info-----*/}
                    <div className="userInfo">
                        <div className="displayPhoto"><img src = {currentUserDoc.profilePicture == null ? '/images/logo..jpg' : currentUserDoc.profilePicture} className="displayImg" alt="No Profile Photo"/></div>
                        <span className="userFirstName"> {currentUserDoc.firstname} </span>
                        <span className="username">{currentUserDoc.username}</span>
                        {/*<span className="currentUserUsername">{currentUserDoc.email}</span>*/}
                    </div>

                    <button onClick={handleLogin}>Link Spotify Account</button>
                    {spotifyUsername && <p>Spotify username: {spotifyUsername}</p>}
      <button onClick={getTopSongs}>Get Top Songs</button>

      <button onClick={getTopThree}> Get Top Three Songs</button>

     

                    <div className="SpotifyLogin">
                            <button className='spotifyBtn' onClick={handleLogin}><img className='spotifyImg' src='images\Spotify_App_Logo.svg.png'/></button>  {/*----className="swipe iconLeft-----*/}
                        </div>

                        <div className="Top Songs">
                            {topThree.map(track => (
                                <div key={track.id}>
                                <h3>{track.name}</h3>
                                <audio src={track.preview_url} controls />
                                </div>
                                ))}
                            </div>
                    
                    <div className = "Genres">
                        <button className='getGenres' onClick={fetchGenres}>Get Genres</button>
                    <h4>Top Three Genres</h4>
                        <ul>
                            {/* {genres.map(genre => (
                            <li key={genre.name}>
                                {genre.name} ({genre.count})
                            </li> */}
                            {genres.slice(0, 3).map(genre => (
                            <li key={genre.name}>
                                {genre.name} ({genre.count})
                            </li>
                            ))}
                        </ul>
                    </div>
                    {/*-----buttons/navigation-----*/}
                    <div className="nav">
                        <button className = "button-home" id='home' onClick={showDefault}>Home</button>
                        <button className = "button-profile" id='profile' onClick={showProfile}>Profile</button>
                        <button className = "button-settings" id='settings' onClick={showSettings}>Settings</button>
                        <button className = "button-logout" id='logout' onClick={handlelogout}>Log Out</button>
                        {/* <button className = "button settings" onClick={getData}>Get Data</button> */}
                    </div>
                </section> {/*-----end of left side-----*/}

                {/*-----right side-----*/}
                <main className="flexHomepage main-content">{/*-----right side container-----*/}

                    {/*-----Match List/Default-----*/}
                    {users.length > 0 ? (userDefault &&
                    <div className="homepage-content profileLayout matchList">
                        <div className="userInfo">
                            <div className="displayPhoto">
                                <img src = {users[currentIndex].profilePicture == null ? '/images/logo..jpg' : users[currentIndex].profilePicture} alt='No Profile Pic' className="displayImg"/>
                            </div>
                            <span className="userFirstName"/> {users[currentIndex].firstname}
                            <span className="username"/>  {users[currentIndex].username}
                        <div className="userLocation"> {users[currentIndex].zipcode}
                        </div>
                        </div>

                        <div className="userPref">
                            <div className="userBio">{users[currentIndex].bio}</div>
                            <div className="TopSongsList">
                                
                            
                            </div>
                            
                        </div>
                        
                        {/*----"swipe" buttons-----*/}
                        <div className="userChoice">
                            <button className='btn' onClick={() => { swipeLeft();}}><img src='/images/close_FILL0_wght400_GRAD0_opsz48.png'/></button>  {/*----className="swipe iconLeft-----*/}
                            <button className='btn' onClick={checkIfMatched}><img src='/images/favorite_FILL0_wght400_GRAD0_opsz48.png'/></button> {/*----className="swipe iconRight"-----*/}
                            <MatchPopup trigger={buttonPopup} setTrigger={setButtonPopup} firstName={users[currentIndex].firstname} nextClick2={swipeRight}>
                            <img src = {users[currentIndex].profilePicture == null ? '/images/logo..jpg' : users[currentIndex].profilePicture} alt='No Profile Pic' className='popup-img' />
                            </MatchPopup>
                        </div>
                        
                        {/*----"chat window" button-----*/}
                        <div className='userChatButton'>
                        {/* open chat button */}
                            <button className='btn' onClick={() => setChatButtonPopup(true)}>Chat</button> {/*----className="swipe iconRight"-----*/}
                            <Chatwindow trigger={chatButtonPopup} setTrigger={setChatButtonPopup} nextClick2={swipeRight}>
                            <img src={users[currentIndex].profilePicture}  className='userImg' />
                            </Chatwindow>
                        </div>
                         
                    </div>
                    ) : (
                        <div className="homepage-content profileLayout matchList">
                        <div className="userInfo">
                            <div className="displayPhoto">
                                <img src = {'/images/logo..jpg'} alt='No Profile Pic' style={{width: 200, height: 200, alignSelf: 'center', borderRadius: 30}}/>
                            </div>
                            <span style={{fontSize: 15}} > No more users in your area <span style={{fontSize: 25}}>&#128546; </span></span>
                            <span className="username" />
                        <div className="userLocation"> 
                        </div>
                        </div>

                        <div className="userPref">
                            <div className="userBio">Try setting a bigger distance</div>
                            <div className="TopSongsList">
                                
                            
                            </div>
                            
                        </div>
                        </div>
                    )}
                    {/*-----end of Match List/Default-----*/}

                    {/*-----User Profile-----*/}
                    {userProfile &&
                    <div className="homepage-content profileLayout showUserProfile">
                        <div className="userInfo">
                            <div className="displayPhoto"><img src = {currentUserDoc.profilePicture == null ? '/images/logo..jpg' : currentUserDoc.profilePicture}  className="displayImg" alt="Profile Photo" /></div>
                            <span className="userFirstName"> {currentUserDoc.firstname} {currentUserDoc.lastname}</span>
                            <span className="username">{currentUserDoc.username}</span>
                            <div className="userLocation"> {currentUserDoc.zipcode}</div>
                        </div>

                        <div className="userPref">
                            <div className="userBio">{bio}</div>
                            <div className="userTopSongs">
                           <button onClick={() => getTopSongs(accessToken)}>Get Top Songs</button>
                                    
                            </div>
                        </div>
                    </div>
                    } {/*-----End of User Profile-----*/}
                    
                    {/*-----User Settings-----*/}
                    {userSettings &&
                    <div className="homepage-content profileLayout userSettings" ng-show="userSettings">User Settings
                        <div ng-show="userSettings">
                            <p>Preferred Matching Distance (in miles): </p>
                            <input type="range" min="1" max="100" value="50" oninput="rangeValue.innerText = this.value" ng-show="userSettings"/>
                            <p id="rangeValue" ng-show="userSettings">50</p>
                        </div>

                        <label for="music-tastes">Choose a Music Preference:</label>
                        <select name="mustic-tastes" id="music-tastes">
                            <option value="a">A</option>
                            <option value="b">B</option>
                            <option value="c">C</option>
                        </select>
                        <div className="bioDiv">
                            <textarea className='bioInput' id='bio-input'  placeholder={bio} type='text' name="bio" />
                            <button className='bioButton' onClick={addBio}>Update Bio</button>
                        </div>
                        <div className='image-upload'>
                            <input type='file' onChange={(event) => {setImageUpload(event.target.files[0])}}></input>
                            <button onClick={uploadImage}>Upload Profile picture</button>
                        </div>
                        
                    </div>
                    } {/*-----End of User Settings-----*/} 

                </main>
            </div>
        </div>
    );
};
export default Homepage;