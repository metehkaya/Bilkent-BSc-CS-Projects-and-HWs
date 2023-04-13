import os
import csv
import pickle


def get_input_file(input_file_name, pickle_file_name, n_cols):
    if not os.path.isfile(pickle_file_name):
        mat = []
        with open(input_file_name) as csv_file:
            csv_reader = csv.reader(csv_file, delimiter=',')
            for row in csv_reader:
                row_data = []
                for col in range(n_cols):
                    if n_cols > 1:
                        row_data.append(int(row[col]))
                    else:
                        mat.append(row[col])
                if n_cols > 1:
                    mat.append(row_data)
        pickle.dump(mat, open(pickle_file_name, "wb"))
    else:
        with open(pickle_file_name, 'rb') as pickle_file:
            mat = pickle.load(pickle_file)
    return mat


def get_inputs():
    train_labels = get_input_file('question-4-train-labels.csv', 'train_labels.pkl', 1)
    train_features_matrix = get_input_file('question-4-train-features.csv', 'train_features_matrix.pkl', 5722)
    test_labels = get_input_file('question-4-test-labels.csv', 'test_labels.pkl', 1)
    test_features_matrix = get_input_file('question-4-test-features.csv', 'test_features_matrix.pkl', 5722)
    return [train_labels, train_features_matrix, test_labels, test_features_matrix]
