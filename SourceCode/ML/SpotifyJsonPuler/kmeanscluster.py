import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import random


dataset = pd.read_csv("featuresFile_buttercheekman.csv")

x = dataset

from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
ct = ColumnTransformer(transformers= [("encoder", OneHotEncoder(), [-14,-13,-12])])
x_train = np.array(ct.fit_transform(x))
 
from sklearn.cluster import KMeans
km = KMeans(n_clusters = 5, init = "k-means++", random_state=5)
y_means = km.fit(x_train)

