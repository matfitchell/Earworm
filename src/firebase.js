import { initializeApp } from "firebase/app";
import { getFirestore } from 'firebase/firestore';
import { getStorage } from 'firebase/storage';
// import {
//   getAuth,
//   onAuthStateChanged,
// } from "https://www.gstatic.com/firebasejs/9.17.1/firebase-auth.js";
// import {
//   getFirestore,
//   query,
//   orderBy,
//   limit,
//   where,
//   onSnapshot,
//   collection,
//   getDocs,
//   addDoc,
//   Timestamp,
// } from "https://www.gstatic.com/firebasejs/9.1.1/firebase-firestore.js";

const firebaseConfig = {
  apiKey: "AIzaSyAjKhFBmAP69M5VDshgkifTOkuA9RWcack",
  authDomain: "earworm-1200e.firebaseapp.com",
  projectId: "earworm-1200e",
  storageBucket: "earworm-1200e.appspot.com",
  messagingSenderId: "865741931736",
  appId: "1:865741931736:web:305dec6ab8660ba06943ad",
  measurementId: "G-K4YE4XSJ7W",
};

//Initialize Firebase
//const app = initializeApp(firebaseConfig);
//const db = getFirestore(app);
//const auth = getAuth(app);
//const db = getFirestore();

//Detect auth state
// onAuthStateChanged(auth, (user) => {
//   if (user != null) {
//     console.log("Logged in");
//   } else {
//     console.log("No user");
//   }
// });
// /console.log(firebaseApp);

//export { app, collection, getDocs, Timestamp, addDoc, db, auth };
//export { query, orderBy, limit, where, onSnapshot };
export const app = initializeApp(firebaseConfig);
export const database = getFirestore(app);
export const storage = getStorage(app);
