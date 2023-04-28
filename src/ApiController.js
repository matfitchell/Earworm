//spotify api integration

export const APIController = (function() {

    //develper app credentials 
    const clientID = "2100da3530bc4465b471b768a7309a4a";
    const clientSecret = "cf9725e022e94dcd98a49f4445b7b585";
    
    //get auth token
    const _getToken = async () => {
  
      const result = await fetch('https://accounts.spotify.com/api/token', {
          method: 'POST',
          headers: {
              'Content-Type' : 'application/x-www-form-urlencoded', 
              'Authorization' : 'Basic ' + btoa(clientID + ':' + clientSecret)
          },
          body: 'grant_type=client_credentials'
      });
  
      const data = await result.json();
      return data.access_token;
  }
  
  // private getter funcs for spotify api
  const _getGenres = async (token) => {
  
      const result = await fetch(`https://api.spotify.com/v1/browse/categories?locale=sv_US`, {
          method: 'GET',
          headers: { 'Authorization' : 'Bearer ' + token}
      });
  
      const data = await result.json();
      return data.categories.items;
  }
  
  const _getPlaylistByGenre = async (token, genreId) => {
  
      const limit = 10;
      
      const result = await fetch(`https://api.spotify.com/v1/browse/categories/${genreId}/playlists?limit=${limit}`, {
          method: 'GET',
          headers: { 'Authorization' : 'Bearer ' + token}
      });
  
      const data = await result.json();
      return data.playlists.items;
  }
  
  const _getTracks = async (token, tracksEndPoint) => {
  
      const limit = 10;
  
      const result = await fetch(`${tracksEndPoint}?limit=${limit}`, {
          method: 'GET',
          headers: { 'Authorization' : 'Bearer ' + token}
      });
  
      const data = await result.json();
      return data.items;
  }
  
  const _getTrack = async (token, trackEndPoint) => {
  
      const result = await fetch(`${trackEndPoint}`, {
          method: 'GET',
          headers: { 'Authorization' : 'Bearer ' + token}
      });
  
      const data = await result.json();
      return data;
  }
  
  const _getTopTracks = async (token, topEndPoint) =>{
  
      const limit = 40;
  
      const result = await fetch (`${topEndPoint}?limit=${limit}`, {
          method: 'GET',
          headers : {'Authorization' : 'Bearer ' + token}
      });
  }
  
  const _getTopThree = async (token, topEndPoint) =>{
  
    const limit = 3;
  
    const result = await fetch (`${topEndPoint}?limit=${limit}`, {
        method: 'GET',
        headers : {'Authorization' : 'Bearer ' + token}
    });
  }
  
  return {
      getToken() {
          return _getToken();
      },
      getGenres(token) {
          return _getGenres(token);
      },
      getPlaylistByGenre(token, genreId) {
          return _getPlaylistByGenre(token, genreId);
      },
      getTracks(token, tracksEndPoint) {
          return _getTracks(token, tracksEndPoint);
      },
      getTrack(token, trackEndPoint) {
          return _getTrack(token, trackEndPoint);
      },
      getTop (token, topEndPoint) {
        return _getTopTracks(token, topEndPoint);
      },
      getTopThreeTracks() {
        return _getTopThree(token, topEndPoint);
      }
  }
  })();
  

  