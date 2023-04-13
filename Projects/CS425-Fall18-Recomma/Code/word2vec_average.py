from load_wordvectors import load_wordvectors
from extract_ngrams import get_words
from preprocess import get_movie_names
import os
from extract_features import  movie_wordvectors
import numpy as np
from scipy import spatial

def find_average_word_vector(word_vectors):
    #print("inside find_summary_words")
    word_vectors = np.asmatrix(word_vectors)
    shape = word_vectors.shape
    word_vectors = np.sum(word_vectors, axis=0)
    word_vectors = word_vectors/shape[0]
    return np.squeeze(np.asarray(word_vectors))



def find_average_word_vectors():
    current_path = os.path.dirname(os.path.abspath(__file__))
    resources_path = os.path.join(current_path, 'resources')

    filenames = os.listdir(resources_path)
    model, model_dict = load_wordvectors(os.path.join(current_path, 'model.pkl'), 'wiki.en.vec')
    average_words_vectors = []
    for count, filename in enumerate(filenames):
        words = get_words(resources_path, filename)
        word_vectors, valid_words = movie_wordvectors(words, model_dict)
        average_words_vector = find_average_word_vector(word_vectors)
        average_words_vectors.append(average_words_vector)

    return average_words_vectors

def average_word_vector_similarity(average_word_vectors):

    cosine_similarity_result = []
    for average_word_vector1 in average_word_vectors:
        cosine_similarity_row = []
        for average_word_vector2 in average_word_vectors:
            cosine_similarity_row.append(1 - spatial.distance.cosine(average_word_vector1, average_word_vector2))
        cosine_similarity_result.append(cosine_similarity_row)

    return cosine_similarity_result

def normalize_results(cosine_similarity_result):
    min = np.amin(cosine_similarity_result)
    max = np.amax(cosine_similarity_result)
    normalized_results = []
    for row in cosine_similarity_result:
        new_row = []
        for value in row:
            new_row.append((value-min)/(max-min))
        normalized_results.append(new_row)

    return normalized_results

def word2_vec_average_similarity(normalized_cosine_similarity, movie_names, movie_name_1, movie_name_2):
    bool = False
    for index, movie_name in enumerate(movie_names):
        if bool == True and (movie_name == movie_name_1 or movie_name == movie_name_2):
            return normalized_cosine_similarity[movie_index][index]

        if bool == False and (movie_name == movie_name_1 or movie_name == movie_name_2):
            movie_index = index
            bool = True


'''current_path = os.path.dirname(os.path.abspath(__file__))
resources_path = os.path.join(current_path, 'resources3')
movie_names = get_movie_names(resources_path, modified_movies=[])


model, model_dict = load_wordvectors(os.path.join(current_path, 'model.pkl'), 'wiki.en.vec')
average_word_vectors = find_average_word_vectors()
cosine_similarity = average_word_vector_similarity(average_word_vectors)
normalized_cosine_similarity = normalize_results(cosine_similarity)
print(normalized_cosine_similarity)
print(word2_vec_average_similarity(normalized_cosine_similarity, movie_names, 'Austin Powers - International Man of Mystery', 'Austin Powers - The Spy Who Shagged Me'))'''