import axios from "axios";
import React from "react";


export default function getTopSongs(){


const [{ token, getTopSongs }, dispatch] = useStateProvider();



//get the tracks 

useEffect(() => {
    const getTopSongs = async () => {
      const response = await axios.get(
       "https://api.spotify.com/v1/me/top/artists?limit=4",
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
        dispatch({ type: reducerCases.SET_PLAYING, topTrack });
      } else {
        dispatch({ type: reducerCases.SET_PLAYING, topTrack: null });
      }
    };
    getTopSongs();
  }, [token, dispatch]);

//return the element

  return (
    <Container>
      {topTrack && (
        <div className="track">
          <div className="track__image">
            <img src={topTrack.image} alt="topTrack" />
          </div>
          <div className="track__info">
            <h4 className="track__info__track__name">{topTrack.name}</h4>
            <h6 className="track__info__track__artists">
              {topTrack.artists.join(", ")}
            </h6>
          </div>
        </div>
      )}
    </Container>
 
  );


}



const Container = styled.div`
  .track {
    display: flex;
    align-items: center;
    gap: 1rem;
    &__image {
    }
    &__info {
      display: flex;
      flex-direction: column;
      gap: 0.3rem;
      &__track__name {
        color: white;
      }
      &__track__artists {
        color: #b3b3b3;
      }
    }
  }
`;