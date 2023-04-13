import string
import os
import numpy as np


def get_imsdb_movies(base_path):
    #print("inside get_imsdb_movies.")

    filenames = os.listdir(base_path)

    # movie_name-> imsdb index
    imsdb_movies = {}
    for index, filename in enumerate(filenames):
        movie_name = filename[7:-4]

        movie_name = movie_name.replace('_', ':')

        comma_index = movie_name.find(',')

        if comma_index != -1:
            movie_name = movie_name[:comma_index]

        imsdb_movies[movie_name] = index

    return imsdb_movies


def get_movielens_movies(file_path):
    #print("inside get_movielens_movies")

    # movie_name -> indices
    all_movies = {}
    with open(file_path, encoding='utf-8') as movie_file:
        for line in movie_file:
            elements = line.strip().split(',')

            index = elements[0]

            if elements[1][-1] != '"':
                movie_name = elements[1][1:]
            else:
                movie_name = elements[1][1:-8]

            if movie_name in all_movies:
                all_movies[movie_name].append(index)
            else:
                all_movies[movie_name] = [index]

    # movielens_index -> movie_name
    unique_movies = {}

    # if a movie has only one index
    # then its a unique movie
    for movie_name in all_movies:
        if len(all_movies[movie_name]) == 1:
            index = all_movies[movie_name][0]
            unique_movies[index] = movie_name

    return unique_movies


def get_common_movies(imsdb_movies, movielens_movies):
    #print("inside get_common_movies")

    common_movies = {}
    imsdb_to_common = {}
    counter = 0

    for movielens_index in movielens_movies.keys():
        movie_name = movielens_movies[movielens_index]
        if movie_name in imsdb_movies:
            imsdb_index = imsdb_movies[movie_name]
            imsdb_to_common[imsdb_index] = counter
            common_movies[movielens_index] = (movie_name, imsdb_index)
            counter = counter + 1

    return common_movies, imsdb_to_common


def get_common_movie_ratings(file_path, common_movies, imsdb_to_common):
    #print('inside get_common_movie_ratings')

    users = {}
    user_index = 0
    ratings = []

    with open(file_path, encoding="utf-8") as rating_file:
        for line in rating_file:
            elements = line.strip().split(',')

            user_id = elements[0]
            movie_id = elements[1]
            rating = elements[2]

            if movie_id in common_movies:
                if user_id not in users:
                    users[user_id] = user_index
                    user_index = user_index + 1

                ratings.append((users[user_id], common_movies[movie_id][1], rating))

    movie_count = len(common_movies)
    user_count = len(users)

    rating_matrix = np.zeros((movie_count, user_count))

    for rating in ratings:
        common_user_id = rating[0]
        common_movie_id = imsdb_to_common[rating[1]]
        user_rating = rating[2]
        rating_matrix[common_movie_id, common_user_id] = user_rating

    return rating_matrix


def get_rating_matrix():
    #print('inside get_rating_matrix')
    current_path = os.path.dirname(os.path.abspath(__file__))
    scripts_path = os.path.join(current_path, 'resources')
    movielens_movie_path = os.path.join(current_path, os.path.join('dataset', 'movie.csv'))
    movielens_rating_path = os.path.join(current_path, os.path.join('dataset', 'rating.csv'))

    imsdb_movies = get_imsdb_movies(scripts_path)
    movielens_movies = get_movielens_movies(movielens_movie_path)
    common_movies, imsdb_to_common = get_common_movies(imsdb_movies, movielens_movies)
    rating_matrix = get_common_movie_ratings(movielens_rating_path, common_movies, imsdb_to_common)

    return rating_matrix, imsdb_to_common
