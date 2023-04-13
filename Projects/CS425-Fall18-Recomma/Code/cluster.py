import random
import numpy as np
import plotly
import plotly.plotly.plotly as py
from sklearn.cluster import KMeans
from sklearn.manifold import TSNE
import plotly.graph_objs as go


def find_summary_words(word_vectors, model, valid_words):
    #print("inside find_summary_words")
    centers, predictions = kmeans_movie(word_vectors, 10)
    # predictions are absolutely useless here

    summary_words_vector = []
    summary_words = []
    for center in centers:
        central_word = model.similar_by_vector(vector=center, topn=1)
        summary_words.append(central_word[0][0])
        for dim in center:
            summary_words_vector.append(dim)
        """
        for central_word in central_words:
            if central_word[0] in valid_words:
                summary_words.append(central_word[0])
                summary_words_vector.append(model[central_word[0]])
                break
        """
    return np.array(summary_words_vector)

# for N movies K(#clusters) times 300
def cluster_movies(summary_words_vectors):
    centers, predictions = kmeans_movie(summary_words_vectors, 3)
    # centers have no use whatsoever
    return centers, predictions


def kmeans_movie(train, n_clusters=10):
    #print("inside kmeans_movie")
    x = np.array(train)
    kmeans = KMeans(n_clusters=n_clusters)
    kmeans.fit(x)
    predictions = []
    for i in range(len(x)):
        predict_me = np.array(x[i])
        predict_me = predict_me.reshape(-1, len(predict_me))
        prediction = kmeans.predict(predict_me)
        predictions.append(prediction)
    return kmeans.cluster_centers_, predictions

def plot_predictions(predictions, summary_words_vectors, movie_names, movie_genres):
    X = np.array(summary_words_vectors)
    X_embedded = TSNE(n_components=3).fit_transform(X)
    colors = []
    x = []
    y = []
    z = []
    text=[]
    for i in range(len(X_embedded)):
        x.append(X_embedded[i][0])
        y.append(X_embedded[i][1])
        z.append(X_embedded[i][2])
        text.append(movie_names[i] + " " + str(movie_genres[i]))

    plotly.tools.set_credentials_file(username='eliztekcan', api_key='CpYDoCrtBF2T8k04VaRf')
    data = []

    groups_count = 3
    colors = ['rgb(0,0,255)','rgb(0,255,0)','rgb(255,0,0)']
    '''colors = []

    for i in range(groups_count):
        color_red = random.randint(0, 255)
        color_green = random.randint(0, 255)
        color_blue = random.randint(0, 255)

        colors.append('rgb('+ str(color_red) + ',' 
                            + str(color_green) + ','  
                            + str(color_blue) + ')')'''

    x_groups = [[] for i in range(groups_count)]
    y_groups = [[] for i in range(groups_count)]
    z_groups = [[] for i in range(groups_count)]
    text_groups = [[] for i in range(groups_count)]
    for i in range(len(X_embedded)):
        x_groups[predictions[i][0]].append(X_embedded[i][0])
        y_groups[predictions[i][0]].append(X_embedded[i][1])
        z_groups[predictions[i][0]].append(X_embedded[i][2])
        text_groups[predictions[i][0]].append(movie_names[i] + " " + str(movie_genres[i]))

    for i in range(groups_count):
        data.append(
            go.Scatter3d(
                x=x_groups[i],
                y=y_groups[i],
                z=z_groups[i],
                mode = 'markers',
                text=text_groups[i],
                marker=dict(
                    color=colors[i],
                    size=12,
                    symbol='circle',
                    line=dict(
                        color='rgb(204, 204, 204)',
                        width=1
                    ),
                    opacity=0.9
                )
            )
        )
    #print(data)
    layout = go.Layout(
        margin=dict(
            l=0,
            r=0,
            b=0,
            t=0
        )
    )

    fig_comp = go.Figure(data=data, layout=layout)
    py.plot(fig_comp, filename='ssimple-3d-slcatter')
