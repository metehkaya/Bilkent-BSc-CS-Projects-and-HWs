from kshingle_similarity import get_kshingle_similarity_matrix
from tfidf_similarity import tfidf, get_script_lists
from preprocess import get_movie_names
import os
import numpy as np
from kshingle_similarity import min_hash_result
from word2vec_average import find_average_word_vectors, average_word_vector_similarity, normalize_results
from get_genres import get_movie_genre
from get_jaccard_sim import get_jaccard_sim
import pickle

def get_result_matrix(a,b,c,d):
    current_path = os.path.dirname(os.path.abspath(__file__))
    base_path = os.path.join(current_path, 'resources')
    movie_names = get_movie_names(base_path, [])

    scripts = get_script_lists(base_path)
    tfidf_similarity_matrix = tfidf(scripts)  # 1 * b

    # kshingle_similars = min_hash_result()
    # kshingle_similarity_matrix = get_kshingle_similarity_matrix(kshingle_similars, movie_names) #2 * a

    if not os.path.isfile("normalized_cosine_similarity_matrix.pkl"):
        average_word_vectors = find_average_word_vectors()
        cosine_similarity = average_word_vector_similarity(average_word_vectors)
        normalized_cosine_similarity_matrix = normalize_results(cosine_similarity)  # 3 * c
        pickle.dump(normalized_cosine_similarity_matrix, open("normalized_cosine_similarity_matrix.pkl", "wb"))
    else:
        with open('normalized_cosine_similarity_matrix.pkl', 'rb') as pickle_file:
            normalized_cosine_similarity_matrix = pickle.load(pickle_file)


    if not os.path.isfile("genre_jacsim_result.pkl"):
        genres = get_all_genres(movie_names)
        genres_similarity = get_genre_jaccard_similarity_matrix(genres)
        pickle.dump(genres_similarity, open("genre_jacsim_result.pkl", "wb"))
    else:
        with open('genre_jacsim_result.pkl', 'rb') as pickle_file:
            genres_similarity = pickle.load(pickle_file)

    tfidf_similarity_matrix = np.asmatrix(tfidf_similarity_matrix)  # 1 * b
    # kshingle_similarity_matrix = np.asmatrix(kshingle_similarity_matrix) #2 * a
    normalized_cosine_similarity_matrix = np.asmatrix(normalized_cosine_similarity_matrix)  # 3 * c
    genres_similarity = np.asmatrix(genres_similarity)  # 4 * d

    # final_matrix = b * tfidf_similarity_matrix + a * kshingle_similarity_matrix + c * normalized_cosine_similarity_matrix + d * genres_similarity
    final_matrix = (a + b) * tfidf_similarity_matrix + c * normalized_cosine_similarity_matrix + d * genres_similarity

    return final_matrix


def get_all_genres(movie_names):
    genres = []
    for movie in movie_names:
        genres.append(get_movie_genre(movie))

    return genres


def get_genre_jaccard_similarity_matrix(genres_list):
    genres_matrix  = []

    for genre1 in genres_list:
        genre_row = []
        for genre2 in genres_list:
            genre_row.append(get_jaccard_sim(genre1, genre2))
        genres_matrix.append(genre_row)

    return genres_matrix
