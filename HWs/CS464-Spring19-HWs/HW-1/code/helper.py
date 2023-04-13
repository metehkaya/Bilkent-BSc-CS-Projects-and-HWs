import os
import pickle


def get_label_counter(train_labels):
    pickle_file_name = 'train_n_label.pkl'
    if not os.path.isfile(pickle_file_name):
        train_n_neutral = 0
        train_n_positive = 0
        train_n_negative = 0
        for i in range(len(train_labels)):
            if train_labels[i] == 'neutral':
                train_n_neutral += 1
            elif train_labels[i] == 'positive':
                train_n_positive += 1
            elif train_labels[i] == 'negative':
                train_n_negative += 1
        pickle.dump([train_n_neutral, train_n_positive, train_n_negative], open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_n_neutral, train_n_positive, train_n_negative = pickle.load(pickle_file)
    return [train_n_neutral, train_n_positive, train_n_negative]


def get_train_pi(train_n_neutral, train_n_positive, train_n_negative):
    pickle_file_name = 'train_pi.pkl'
    if not os.path.isfile(pickle_file_name):
        sum = train_n_neutral + train_n_positive + train_n_negative
        train_pi_neutral = float(train_n_neutral) / sum
        train_pi_positive = float(train_n_positive) / sum
        train_pi_negative = float(train_n_negative) / sum
        pickle.dump([train_pi_neutral, train_pi_positive, train_pi_negative], open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_pi_neutral, train_pi_positive, train_pi_negative = pickle.load(pickle_file)
    return [train_pi_neutral, train_pi_positive, train_pi_negative]


def get_train_t(train_labels, train_features_matrix):
    pickle_file_name = 'train_t.pkl'
    if not os.path.isfile(pickle_file_name):
        n_tweet = len(train_labels)
        n_feature = len(train_features_matrix[0])
        train_t_total = [0, 0, 0]
        train_t = []
        for i in range(n_feature):
            train_t.append([0, 0, 0])
        for tweet in range(n_tweet):
            if train_labels[tweet] == 'neutral':
                label_id = 0
            elif train_labels[tweet] == 'positive':
                label_id = 1
            elif train_labels[tweet] == 'negative':
                label_id = 2
            for feature in range(n_feature):
                feature_cnt = train_features_matrix[tweet][feature]
                train_t[feature][label_id] += feature_cnt
                train_t_total[label_id] += feature_cnt
        pickle.dump([train_t, train_t_total], open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_t, train_t_total = pickle.load(pickle_file)
    return [train_t, train_t_total]


def get_train_theta_t(train_t, train_t_total):
    pickle_file_name = 'train_theta_t.pkl'
    if not os.path.isfile(pickle_file_name):
        train_theta_t = []
        n_feature = len(train_t)
        for feature in range(n_feature):
            train_theta_t_neutral = float(train_t[feature][0]) / train_t_total[0]
            train_theta_t_positive = float(train_t[feature][1]) / train_t_total[1]
            train_theta_t_negative = float(train_t[feature][2]) / train_t_total[2]
            train_theta_t.append([train_theta_t_neutral, train_theta_t_positive, train_theta_t_negative])
        pickle.dump(train_theta_t, open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_theta_t = pickle.load(pickle_file)
    return train_theta_t


def get_train_theta_t_map(train_t, train_t_total, alpha=1):
    pickle_file_name = 'train_theta_t_map.pkl'
    if not os.path.isfile(pickle_file_name):
        train_theta_t_map = []
        n_feature = len(train_t)
        for feature in range(n_feature):
            train_theta_t_map_neutral = float(train_t[feature][0] + alpha) / (train_t_total[0] + alpha * n_feature)
            train_theta_t_map_positive = float(train_t[feature][1] + alpha) / (train_t_total[1] + alpha * n_feature)
            train_theta_t_map_negative = float(train_t[feature][2] + alpha) / (train_t_total[2] + alpha * n_feature)
            train_theta_t_map.append([train_theta_t_map_neutral, train_theta_t_map_positive, train_theta_t_map_negative])
        pickle.dump(train_theta_t_map, open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_theta_t_map = pickle.load(pickle_file)
    return train_theta_t_map


def get_train_s(train_labels, train_features_matrix):
    pickle_file_name = 'train_s.pkl'
    if not os.path.isfile(pickle_file_name):
        n_tweet = len(train_labels)
        n_feature = len(train_features_matrix[0])
        train_s = []
        for i in range(n_feature):
            train_s.append([0, 0, 0])
        for tweet in range(n_tweet):
            if train_labels[tweet] == 'neutral':
                label_id = 0
            elif train_labels[tweet] == 'positive':
                label_id = 1
            elif train_labels[tweet] == 'negative':
                label_id = 2
            for feature in range(n_feature):
                feature_cnt = min(train_features_matrix[tweet][feature], 1)
                train_s[feature][label_id] += feature_cnt
        pickle.dump(train_s, open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_s = pickle.load(pickle_file)
    return train_s


def get_train_theta_s(train_s, train_n_neutral, train_n_positive, train_n_negative):
    pickle_file_name = 'train_theta_s.pkl'
    if not os.path.isfile(pickle_file_name):
        train_theta_s = []
        n_feature = len(train_s)
        for feature in range(n_feature):
            train_theta_s_neutral = float(train_s[feature][0]) / train_n_neutral
            train_theta_s_positive = float(train_s[feature][1]) / train_n_positive
            train_theta_s_negative = float(train_s[feature][2]) / train_n_negative
            train_theta_s.append([train_theta_s_neutral, train_theta_s_positive, train_theta_s_negative])
        pickle.dump(train_theta_s, open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            train_theta_s = pickle.load(pickle_file)
    return train_theta_s
