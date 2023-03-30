from itertools import permutations
import json
import os
import csv
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

features_df = pd.DataFrame(data=features, columns=features[0].keys())


features_df['title'] = list_of_song_names
features_df['artist'] = list_of_artist_names
features_df['id'] = list_of_id
features_df = features_df[['id', 'title', 'artist',
                           'danceability', 'energy', 'key', 'loudness',
                           'acousticness', 'instrumentalness',
                           'liveness', 'valence', 'tempo',
                           'duration_ms', 'time_signature']]


features_df.tail()


features_df.to_csv("featuresFile_" + username + ".csv")

plt.figure(figsize=(20,30))
sb.countplot(features_df['first_artist'])
plt.xticks(rotation=90)

num_bars = []
num_sections = []
num_segments = []

for i in range(0,len(features_df['id'])):
    analysis = sp.audio_analysis(features_df.iloc[i]['id'])
    num_bars.append(len(analysis['bars'])) # beats/time_signature
    num_sections.append(len(analysis['sections']))
    num_segments.append(len(analysis['segments']))



data = pd.read_csv("featuresFile_' + username + '.csv")
X = data.iloc[:,0].values.reshape(-1,1)
Y = data.iloc[:,1].values.reshape(-1,1)
linear_regressor = LinearRegression()
linear_regressor.fit(X, Y)
Y_pred = linear_regressor.predict(X)

plt.scatter(X, Y)
plt.plot (X, Y_pred, color='red')
plt.show()



# descending_order = all_songs['artist'].value_counts().sort_values(ascending=False).index
# ax = sb.countplot(y = all_songs['artist'], order=descending_order)

# sb.despine(fig=None, ax=None, top=True, right=True, left=False, trim=False)
# sb.set(rc={'figure.figsize':(6,7.2)})

# ax.set_ylabel('')    
# ax.set_xlabel('')
# ax.set_title('Songs per Artist in Top 50', fontsize=16, fontweight='heavy')
# sb.set(font_scale = .5)
# ax.axes.get_xaxis().set_visible(False)
# ax.set_frame_on(False)

# y = all_songs['artist'].value_counts()
# for i, v in enumerate(y):
#     ax.text(v + 0.2, i + .16, str(v), color='black', fontweight='light', fontsize=12)
    
# plt.savefig('all_songs_songs_per_artist.jpg', bbox_inches="tight")


# popularity = all_songs['popularity']
# artists = all_songs['artist']

# plt.figure(figsize=(10,6))

# ax = sb.boxplot(x=popularity, y=artists, data=all_songs)
# plt.xlim(20,90)
# plt.xlabel('Popularity (0-100)')
# plt.ylabel('')
# plt.title('Song Popularity by Artist', fontweight='bold', fontsize=16)
# plt.savefig('top50_artist_popularity.jpg', bbox_inches="tight")



