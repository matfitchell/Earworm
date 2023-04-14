import pandas as pd 
import numpy as np 
import matplotlib.pyplot as plt


df = pd.read_csv("featuresFile_buttercheekman.csv", encoding = 'utf8')

i = 0

# Defining Dependent and Independant variable
X = np.array(df[['danceability ', 'energy ', 'key ', 'acousticness ', 'liveness ', 'valence ' , 'duration_ms ']])
Y = df['tempo   '].values

# Plotting the Clusters using matplotlib
plt.rcParams['figure.figsize'] = [14, 7]
plt.rc('font', size=14)

plt.scatter(df['duration_ms '], df['danceability '], label="Dancability")
plt.scatter(df['duration_ms '], df['energy '], label="Energy")
#plt.scatter(df['duration_ms '], df['key '], label="key")
plt.scatter(df['duration_ms '], df['acousticness '], label="Acousticness")
plt.scatter(df['duration_ms '], df['liveness '], label="Liveness")
plt.scatter(df['duration_ms '], df['valence '], label="Valence")

plt.xlabel("Duration")
plt.ylabel("Features")
plt.legend()
plt.show()