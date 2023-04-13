import numpy as np
import os
import pickle

def get_baseline_estimates(ratings):
    #print('inside get_baseline_estimates')
    if not os.path.isfile("baseline_estimates.pkl"):
        user_count = ratings.shape[0]
        movie_count = ratings.shape[1]

        avg_user = []
        avg_movie = []

        global_mean = 0

        ratings_count = 0
        for x, row in enumerate(ratings):
            nonzero_ratings = 0
            sum_ratings = 0
            for rating in row:
                if rating != 0:
                    nonzero_ratings += 1
                    sum_ratings += rating

            global_mean += sum_ratings
            ratings_count += nonzero_ratings
            if nonzero_ratings == 0:
                avg_user.append(0)
            else:
                avg_user.append(float(sum_ratings) / nonzero_ratings)

        global_mean /= ratings_count

        for i, column in enumerate(np.transpose(ratings)):
            nonzero_ratings = 0
            sum_ratings = 0
            for rating in column:
                if rating != 0:
                    nonzero_ratings += 1
                    sum_ratings += rating
            if nonzero_ratings == 0:
                avg_movie.append(0)
            else:
                avg_movie.append(float(sum_ratings) / nonzero_ratings)

        baseline_est = np.zeros(ratings.shape)

        for i in range(user_count):
            for x in range(movie_count):
                if ratings[i, x] != 0:
                    baseline_est[i, x] = avg_movie[x] + avg_user[i] - global_mean

        pickle.dump(baseline_est, open("baseline_estimates.pkl", "wb"))
    else:
        with open('baseline_estimates.pkl', 'rb') as pickle_file:
            baseline_est = pickle.load(pickle_file)

    return baseline_est