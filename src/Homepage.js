import React, {useState, useRef, useEffect} from 'react';
import './Homepage.css'
import { useNavigate } from "react-router-dom";
import MatchPopup from './MatchPopup';
import Chatwindow from './Chatwindow';
import { app, database, storage } from "./firebase";
import { getAuth, signOut, onAuthStateChanged } from "firebase/auth";
import { collection, getDocs, doc, updateDoc, getDoc } from "firebase/firestore";
import { uploadBytes, ref, getDownloadURL } from 'firebase/storage';

function Homepage() {
    let auth = getAuth();
    let user = auth.currentUser;
    const navigate = useNavigate();
    //dummy data    
    const dummyData = [{
        userName: "mitchman",
        password: "pass1",
        firstName:"Mitch",
        lastName: "Mercer",
        image: "/images/1.png",
        miles: '4',
        location: "CSUN",
        bio: ""
        },{
        userName: "coolguy69",
        password: "pass2",
        firstName: "Cool",
        lastName: "Guy",
        image: "/images/3.jpg",
        miles: 'too far',
        bio: "let's be friends"
        },{
        userName: "charlesguy",
        password: "pass3",
        firstName: "Nick",
        lastName: "Cannon",
        image: "/images/1.png",
        miles: '80',
        bio: "I am cool"
        },{
        userName: "slushieguy",
        password: "pass4",
        firstName: "Sarah",
        lastName: "Connor",
        image: "/images/2.webp",
        miles: '0.1',
        bio: "Adventurer, foodie, and lover of all things creative. I'm always up for trying new things, whether it's exploring a hidden gem in the city or traveling to a far-off destination. When I'm not out and about, you can find me experimenting in the kitchen or working on my latest art project. Follow along for a glimpse into my life and a healthy dose of inspiration. Let's connect and share our passions!"
        },{
        userName: "lombocchew",
        password: "pass5",
        firstName: "Lewis",
        lastName: "Carrol",
        image: "/images/3.jpg",
        miles: '7',
        bio: "dude"
        },{
        userName: "slapyamammy",
        password: "pass6",
        firstName: "Chris",
        lastName: "Brown",
        image: "/images/1.png",
        miles: '40'
        },{
        userName: "garusvyk",
        password: "pass7",
        firstName: "Leah",
        lastName: "Remini",
        image: "/images/2.webp",
        miles: '15'
        },{
        userName: "loopertwo",
        password: "pass8",
        firstName: "Dawn",
        lastName: "Joy",
        image: "/images/3.jpg",
        miles: '10' 
        },{
        userName: "partypooper2",
        password: "pass9",
        firstName: "Poopy",
        lastName: "Party",
        image: "/images/1.png",
        miles: '45'
        },{
        userName: "railgunner",
        password: "pass10",
        firstName: "Mark",
        lastName: "Twain",
        image: "/images/1.png",
        miles: '5' 
    }];
    
    //FULL TRANSPARENCY, IDK HOW WHY. I JUST GOOGLED, STACK OVERFLOW'D AND CHATGPT'D PLEASE HAVE MERCY --NATH :D
    //ALSO, I used my client ID cause I was thinking what if I used a different client ID. 22564e175af6486d82075db9d583c551 
    const [clientId, setClientId] = useState('2100da3530bc4465b471b768a7309a4a');
    const [redirectUri, setRedirectUri] = useState('http://localhost:3000/Homepage');
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
    const [topThreeTitles, setTopThreeTitles] = useState([]);
    const [topThree, setTopThree] = useState([]);
    


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
        console.log('Top songs:', data);
        setTopSongs(data);
       
      } catch (error) {
        console.log(error);
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
    const nextClick = () => {
        setIndex((prevIndex) => (prevIndex + 1) % users.length);
    };

    const nextClick2 = () => {
        showDefault();
        setIndex((prevIndex) => (prevIndex + 1) % users.length);
    };

    //buttons 
    const [userDefault, setUserDefault] = useState (true);
    const [userProfile, setUserProfile] = useState (false);
    const [userSettings, setUserSettings] = useState (false);
    const [buttonPopup, setButtonPopup] = useState(false);
    const [chatButtonPopup, setChatButtonPopup] = useState(false);
    const [userInbox, setUserInbox] = useState (false);

    const showProfile = () => {
        setUserDefault (false);
        setUserProfile (true);
        setUserSettings (false);
        setUserInbox (false);
    }

    const showSettings = () => {
        setUserDefault (false);
        setUserProfile (false);
        setUserSettings (true);
        setUserInbox (false);
    }

    const showDefault = () => {
        setUserDefault (true);
        setUserProfile (false);
        setUserSettings (false);
        setUserInbox (false);
    }

    const showInbox = () => {
        setUserDefault (false);
        setUserProfile (false);
        setUserSettings (false);
        setUserInbox (true);
    }


    
    const handleInput = (event) => {
        let newInput = {[event.target.name]: event.target.value};

        setData({...data, ...newInput});
    };
    

    const handlelogout = () => {
        signOut(auth);
    }

    function calcDistance(lat1, lon1, lat2, lon2) {
        let dist = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * 6371;
        return dist;
    }

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
    
    function handleNextClick() {
      //setCurrentIndex((prevIndex) => prevIndex + 1);
      setCurrentIndex((prevIndex) => (prevIndex + 1) % users.length);
    }
    
    const uploadImage = () =>{
        if(imageUpload == null) return;

        const imageRef = ref(storage, `images/${auth.currentUser.uid}`);
        uploadBytes(imageRef, imageUpload).then(() => {
             getDownloadURL(imageRef).then((url) => {
                setProfilePictureUrl(url);
             })
        })
       
    }

    useEffect(() =>{
        onAuthStateChanged(auth, (data) => {
           if (data) {
             navigate("/Homepage");
             //alert("logged in");
           }else{
            navigate("/")
           }
         });

        const getCurrentUserInfo = async () =>{
        //const userData = database.collection("userInfo").doc(auth.currentUser.uid).get();
        // const docRef = doc(database, "userInfo", auth.currentUser.uid);
        if(user){
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
            getDownloadURL( ref(storage, `images/${auth.currentUser.uid}`)).then((url) => {
              setProfilePictureUrl(url);
            });
        }  
        };

        async function getUsersFromFirestore(){
            const data = await getDocs(collection(database, "userInfo"));
            const userssss = data.docs.map((doc) => ({ ...doc.data(), id: doc.id}));
            setUsers(userssss);
        }
        getUsersFromFirestore();
        getCurrentUserInfo();
    }, []);

    // console.log(getDownloadURL())
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
                        <div className="displayPhoto"><img src = {profilePictureUrl} className="displayImg"/></div>
                        <span className="userFirstName"> {firstname} </span>
                        <span className="username">{username}</span>
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
                            
                    {/*-----buttons/navigation-----*/}
                    <div className="nav">
                        <button className = "button-home" id='home' onClick={showDefault}>Home</button>
                        <button className = "button-profile" id='profile' onClick={showProfile}>Profile</button>
                        <button className = "button-inbox" id='inbox' onClick={showInbox}>Inbox</button>
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
                                <img src = {dummyData[index].image} className="displayImg"/>
                            </div>
                            <span className="userFirstName"/> {users[currentIndex].firstname}
                            <span className="username"/>  {users[currentIndex].username}
                        <div className="userLocation"> {users[currentIndex].zipcode}
                        </div>
                        </div>

                        <div className="userPref">
                            <div className="userBio">{dummyData[index].bio}</div>
                            <div className="TopSongsList">
                                
                            
                            </div>
                            
                        </div>
                        
                        {/*----"swipe" buttons-----*/}
                        <div className="userChoice">
                            <button className='btn' onClick={handleNextClick}><img src='/images/close_FILL0_wght400_GRAD0_opsz48.png'/></button>  {/*----className="swipe iconLeft-----*/}
                            <button className='btn' onClick={() => setButtonPopup(true)}><img src='/images/favorite_FILL0_wght400_GRAD0_opsz48.png'/></button> {/*----className="swipe iconRight"-----*/}
                            <MatchPopup trigger={buttonPopup} setTrigger={setButtonPopup} firstName={users[currentIndex].firstname} nextClick2={handleNextClick}>
                            <img src={dummyData[index].image} className='popup-img' />
                            </MatchPopup>
                        </div>
                        
                        {/*----"chat window" button-----*/}
                        <div className='userChat'>
                        {/* open chat button */}
                            <button className='btn' onClick={() => setChatButtonPopup(true)}>Chat</button> {/*----className="swipe iconRight"-----*/}
                            <Chatwindow trigger={chatButtonPopup} setTrigger={setChatButtonPopup} firstName={users[currentIndex].firstname} nextClick2={handleNextClick}>
                            </Chatwindow>
                        </div>
                        
                    </div>
                    ) : (
                        <p>Loading...</p>
                    )}
                    {/*-----end of Match List/Default-----*/}

                    {/*-----User Profile-----*/}
                    {userProfile &&
                    <div className="homepage-content profileLayout showUserProfile">
                        <div className="userInfo">
                            <div className="displayPhoto"><img src = {dummyData[0].image} className="displayImg"/></div>
                            <span className="userFirstName"> {firstname} {lastname}</span>
                            <span className="username">{username}</span>
                            <div className="userLocation"> {zipcode}</div>
                        </div>

                        <div className="userPref">
                            <div className="userBio">{dummyData[0].bio}</div>
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

                {userInbox &&
                    <div className="homepage-content profileLayout userSettings">
                        <h3>Messages</h3>
                    </div>
                    }   

                </main>
            </div>
        </div>
    );
};
export default Homepage;