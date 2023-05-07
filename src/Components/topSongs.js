import axios from "axios";
import React, {useEffect} from "react";
import { useStateProvider } from "../utils/StateProvider";
import { reducerCases } from "../utils/Constants";
import styled from "styled-components";


export default function getTopSongs(){


const [ token, getTopSongs] = useStateProvider();



//get the tracks 


useEffect(() => {
    const getTopSongs = async () => {
      const response = await axios.get(
        'https://api.spotify.com/v1/me/top/tracks?limit=4',
                {
          headers: {
            "Content-Type": "application/json",
            Authorization: "Bearer " + token,
          },
        }
      );
      if (response.data !== "") {
        const topTrack = {
          id: response.data.item.id,
          name: response.data.item.name,
          artists: response.data.item.artists.map((artist) => artist.name),

        };
        
    getTopSongs();
      }
    }

  },[]);
}