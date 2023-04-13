import os
from create_vocab import create_vocab
from find_similarity import *
from extract_ngrams import get_ngram_lists
from preprocess import get_movie_names

def min_hash_result():

    #similarity_result = dict()
    current_path = os.path.dirname(os.path.abspath(__file__))
    base_path = os.path.join(current_path, 'resources')
    modified_movies = []
    movie_names = get_movie_names(base_path, modified_movies)
    bands = 50
    movie_ngram_lists = get_ngram_lists(base_path, modified_movies, 0)
    movie_ngram_sets = [set(ngram_list) for ngram_list in movie_ngram_lists]
    vocabulary = create_vocab(movie_ngram_lists)
    movie_vectors = hash_ngrams(movie_ngram_sets, vocabulary)
    signature_matrix = min_hash_vectors(movie_vectors, len(vocabulary))
    similars = lsh(signature_matrix, movie_names, bands)

    return similars

def is_similar(similars, movie_name_1, movie_name_2):
    return (movie_name_1, movie_name_2) in similars

def get_kshingle_similarity_matrix(similars, movie_names):
    similarity_matrix = []
    for movie1 in movie_names:
        similarity_row = []
        for movie2 in movie_names:
            if is_similar(similars, movie1, movie2):
                similarity_row.append(1)
            else:
                similarity_row.append(0)
        similarity_matrix.append(similarity_row)
    return similarity_matrix

'''similars = min_hash_result()
current_path = os.path.dirname(os.path.abspath(__file__))
resources_path = os.path.join(current_path, 'resources3')
movie_names = get_movie_names(resources_path, modified_movies=[])
kshingle_similarity_matrix = get_kshingle_similarity_matrix(similars, movie_names)
print(kshingle_similarity_matrix)'''

