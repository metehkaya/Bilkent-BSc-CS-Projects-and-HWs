import numpy as np
import sys, pickle
from movie_ratings import get_rating_matrix
from compute_baseline import get_baseline_estimates


def compute_gradient(weights, baseline_est, ratings):
    grad_w = 2 * np.transpose((baseline_est + np.transpose(weights @
                                                           np.transpose(ratings - baseline_est))) - ratings) @ (
                         ratings - baseline_est)
    return grad_w


def gradient_descent(weights, baseline_est, ratings, learning_rate=0.001):
    grad_w = compute_gradient(weights, baseline_est, ratings)
    weights = weights - learning_rate * grad_w
    return weights


def train_model(training_ratings, test_ratings,
                baseline_est, epochs=100, learning_rate=0.001):
    print('inside train_model')

    movie_count = ratings.shape[1]

    # initialize weights
    weights = np.zeros((movie_count, movie_count))

    for epoch in range(epochs):
        print('epoch: ', epoch + 1)
        weights = gradient_descent(weights, baseline_est, training_ratings, learning_rate)
        predictions = baseline_est + np.transpose((weights) @ np.transpose((training_ratings - baseline_est)))
        training_sse = calculate_sse(training_ratings, predictions)
        print('training sse: ', training_sse)
        test_sse = calculate_sse(test_ratings, predictions[0:test_ratings.shape[0], 0:test_ratings.shape[1]])
        print('test_sse: ', test_sse)

    return predictions


def adjust_boundaries(matrix, upper_bound=5, lower_bound=0):
    for i, row in enumerate(matrix):
        for j, value in enumerate(row):
            if value > upper_bound:
                predictions[i, j] = upper_bound
            if value < lower_bound:
                predictions[i, j] = lower_bound


def calculate_sse(ratings, predictions):
    sse = np.sum((ratings - predictions) ** 2)
    return sse


def test_train_split(ratings, ratio):
    user_count = ratings.shape[0]
    movie_count = ratings.shape[1]

    user_count_test = int(ratio * user_count)
    movie_count_test = int(ratio * movie_count)

    test_ratings = ratings[0:user_count_test, 0:movie_count_test]

    training_ratings = ratings
    training_ratings[0:user_count_test, 0:movie_count_test] = np.zeros(test_ratings.shape)

    return training_ratings, test_ratings


# ---------------------------------- RUN --------------------------------------#

if len(sys.argv) > 1:
    epochs = int(sys.argv[1])
else:
    epochs = 200

ratings, imsdb_to_common = get_rating_matrix()
ratings = np.transpose(ratings[0:808, 0:10000])

baseline_est = get_baseline_estimates(ratings)
predictions = train_model(ratings, np.zeros((1, 1)), baseline_est,
                          epochs=epochs, learning_rate=0.0001)

predictions_path = 'predictions.pkl'
imsdb_to_common_path = 'imsdb_to_common.pkl'

with open(predictions_path, 'wb') as pickle_file:
    pickle.dump(predictions, pickle_file)

with open(imsdb_to_common_path, 'wb') as pickle_file:
    pickle.dump(imsdb_to_common, pickle_file)


