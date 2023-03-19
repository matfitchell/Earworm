from itertools import permutations
import json
import os
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sb
import spotipy
from spotipy.oauth2 import SpotifyClientCredentials
import spotipy.util as util
import numpy
from sklearn import preprocessing



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


with open('top50Mitch_data.json', encoding= 'utf8') as f:
  data = json.load(f)

list_of_results = data[0]["items"]
list_of_id = []
list_of_artist_names = []
list_of_artist_uri = []
list_of_song_names = []
list_of_song_uri = []
list_of_durations_ms = []
list_of_explicit = []
list_of_albums = []
list_of_popularity = []
list_of_features = []



for result in list_of_results:
    result["album"]
    this_artists_name = result["artists"][0]["name"]
    list_of_artist_names.append(this_artists_name)
    this_artists_uri = result["artists"][0]["uri"]
    list_of_artist_uri.append(this_artists_uri)
    list_of_songs = result["name"]
    list_of_song_names.append(list_of_songs)
    song_uri = result["uri"]
    list_of_song_uri.append(song_uri)
    list_of_duration = result["duration_ms"]
    list_of_durations_ms.append(list_of_duration)
    song_explicit = result["explicit"]
    list_of_explicit.append(song_explicit)
    this_album = result["album"]["name"]
    list_of_albums.append(this_album)
    song_popularity = result["popularity"]
    list_of_popularity.append(song_popularity)
    song_id = result["id"]
    list_of_id.append(song_id)
    features = sp.audio_features(list_of_id)
    list_of_features.append(features)






all_songs = pd.DataFrame(
   
    {'artist': list_of_artist_names,
     'artist_uri': list_of_artist_uri,
     'song': list_of_song_names,
     'id' : list_of_id,
     'song_uri': list_of_song_uri,
     'duration_ms': list_of_durations_ms,
     'explicit': list_of_explicit,
     'album': list_of_albums,
     'popularity': list_of_popularity,
     'features' : list_of_features
    }
)

all_songs_saved = all_songs.to_csv('top50_songs.csv')


descending_order = all_songs['artist'].value_counts().sort_values(ascending=False).index
ax = sb.countplot(y = all_songs['artist'], order=descending_order)

sb.despine(fig=None, ax=None, top=True, right=True, left=False, trim=False)
sb.set(rc={'figure.figsize':(6,7.2)})

ax.set_ylabel('')    
ax.set_xlabel('')
ax.set_title('Songs per Artist in Top 50', fontsize=12, fontweight='heavy')
sb.set(font_scale = 1.4)
ax.axes.get_xaxis().set_visible(False)
ax.set_frame_on(False)

y = all_songs['artist'].value_counts()
for i, v in enumerate(y):
    ax.text(v + 0.2, i + .16, str(v), color='black', fontweight='light', fontsize=12)
    
plt.savefig('all_songs_songs_per_artist.jpg', bbox_inches="tight")


popularity = all_songs['popularity']
artists = all_songs['artist']

plt.figure(figsize=(10,6))

ax = sb.boxplot(x=popularity, y=artists, data=all_songs)
plt.xlim(20,90)
plt.xlabel('Popularity (0-100)')
plt.ylabel('')
plt.title('Song Popularity by Artist', fontweight='bold', fontsize=12)
plt.savefig('top50_artist_popularity.jpg', bbox_inches="tight")


x = all_songs.values
min_max_scaler = preprocessing.MinMaxScaler()
x_scaled = min_max_scaler.fit_transform(x)
all_songs = pd.DataFrame(x_scaled)

columns = ["energy",  "speechiness", "acousticness", "instrumentalness", "loudness","tempo","danceability",'valence' , "liveness", "time_signature", "key"]

perm = permutations(columns, 3)
output = set(map(lambda x: tuple(sorted(x)),perm))

model = KMeans(random_state=0)
visualizer = KElbowVisualizer(model, k=(2,12), metric='silhouette', timings=False)
visualizer.fit(x_scaled)
score = visualizer.elbow_score_
value = visualizer.elbow_value_

if score>0.4:
    idx = all_songs.columns
    mylist = idx.tolist()
    dict = {
        "features": mylist,
        "score": score,
        "elbow": value
    }
    df2 = df2.append(dict, ignore_index=True)

from sklearn.cluster import KMeans

kmeans = KMeans(init="k-means++",
                n_clusters=4,
                random_state=15,
                max_iter = 500).fit(x_scaled)

df1['kmeans'] = kmeans.labels_
df1.columns = ['energy', 'instrumentalness', 'loudness','kmeans' ]