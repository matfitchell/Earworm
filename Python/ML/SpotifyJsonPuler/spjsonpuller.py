import os
import json
import spotipy
from spotipy.oauth2 import SpotifyClientCredentials
import spotipy.util as util



cid = "2100da3530bc4465b471b768a7309a4a"
sid = "cf9725e022e94dcd98a49f4445b7b585"



os.environ['SPOTIPY_CLIENT_ID']= cid
os.environ['SPOTIPY_CLIENT_SECRET']= sid
os.environ['SPOTIPY_REDIRECT_URI']='http://localhost:8888/callback'



username = "buttercheekman"
client_credentials_manager = SpotifyClientCredentials(client_id=cid, client_secret=sid) 
sp = spotipy.Spotify(client_credentials_manager=client_credentials_manager)
scope = 'user-top-read'
token = util.prompt_for_user_token(username, scope)

if token:
    sp = spotipy.Spotify(auth=token)
else:
    print("Can't get token for", username)


if token:
    sp = spotipy.Spotify(auth=token)
results = sp.current_user_top_tracks(limit=50,offset=0,time_range='medium_term')
for song in range(50):
    list = []
    list.append(results)
    with open('top50Mitch_data.json', 'w', encoding='utf-8') as f:
        json.dump(list, f, ensure_ascii=False, indent=4)
else:
    print("Can't get token for", username)




