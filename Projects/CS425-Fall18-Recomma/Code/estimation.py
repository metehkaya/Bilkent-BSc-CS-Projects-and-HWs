from weighted_similarity_result import get_result_matrix
from compute_baseline import get_baseline_estimates
from movie_ratings import get_rating_matrix
import numpy as np
import os
import pickle

def calculate_sse(ratings, predictions):
    print("rating-0", ratings.shape[0])
    print("rating-1", ratings.shape[1])
    print("predictions-0", predictions.shape[0])
    print("predictions-1", predictions.shape[1])
    return np.sum((ratings-predictions)**2)

def get_rating_matrix_movies(base_path):
    rating_matrix, imsdb_to_common = get_rating_matrix()
    filenames = os.listdir(base_path)
    final_rating_matrix = []
    for count, filename in enumerate(filenames):
        try:
            row = rating_matrix[imsdb_to_common[count]]
            final_rating_matrix.append(row)
        except:
            os.remove(os.path.join(base_path, filename))

    users = []
    for i in range(10000):
        users.append(i)

    return np.array(final_rating_matrix)[:, users] # movie x user

def get_validation_predictions():
    with open('predictions.pkl','rb') as pickle_file:
        predictions = pickle.load(pickle_file)
    return predictions


current_path = os.path.dirname(os.path.abspath(__file__))
resources_path = os.path.join(current_path, 'resources')
rating_matrix = get_rating_matrix_movies(resources_path).T

baseline_estimates = get_baseline_estimates(rating_matrix)

def test(a,b,c,d):
    similarities = np.asmatrix(get_result_matrix(a, b, c, d))
    user_count = rating_matrix.shape[0]
    movie_count = rating_matrix.shape[1]

    estimates = np.transpose((similarities) @ np.transpose((rating_matrix - baseline_estimates)))

    estimate_result = []

    sum = np.sum(similarities, axis=1)

    for user in range(user_count):
        estimate_row = []
        for movie in range(movie_count):
            estimate_row.append(estimates.item(user, movie) / sum.item(movie))
        estimate_row = estimate_row + baseline_estimates[user]
        estimate_result.append(estimate_row)

    for i in range(len(estimate_result)):
        for j in range(len(estimate_result[0])):
            if rating_matrix[i][j] != 0:
                estimate_result[i][j] = max(estimate_result[i][j], 0.5)
                estimate_result[i][j] = min(estimate_result[i][j], 5)

    validation_prediction = get_validation_predictions()

    rating_matrix_1, imsdb_to_common = get_rating_matrix()

    for x, row in enumerate(estimate_result):
        for i, value in enumerate(row):
            movie_column = imsdb_to_common[i]
            estimate_result[x][i] = 0.001 * estimate_result[x][i] + validation_prediction[x][movie_column] * 0.999

    sse = calculate_sse(rating_matrix, estimate_result)

    print("SSE ", sse)



#a+b = tfidf, c: word2vecaverage, d:genre jaccard

validation_prediction = get_validation_predictions()
sse = calculate_sse(rating_matrix, validation_prediction)
print("Pure result")
print("SSE ", sse)

print("Testing with genre jaccard similarity:")
test(0,0,0,1)

print("Testing with genre word2vec averages:")
test(0,0,1,0)

print("Testing with tfidf:")
test(1,0,0,0)

a, b, c, d = 0.1, 0.3, 0.2, 0.4
print("Testing with our weights: a, b, c, d = 0.1, 0.3, 0.2, 0.4 =>")
test(a,b,c,d)
