import math


def multinomial_naive_bayes(train_pis, test_features_matrix, train_theta_t):
    n_tweet = len(test_features_matrix)
    n_feature = len(test_features_matrix[0])
    test_result = []
    for tweet in range(n_tweet):
        score = [0, 0, 0]
        for k in range(3):
            score[k] = math.log(train_pis[k])
            for feature in range(n_feature):
                theta = train_theta_t[feature][k]
                test_feature = test_features_matrix[tweet][feature]
                if theta == 0:
                    if test_feature == 0:
                        score[k] += 0
                    else:
                        score[k] += -math.inf
                else:
                    score[k] += test_feature * math.log(theta)
        best_label = -1
        best_score = -1
        for k in range(3):
            if score[k] > best_score or best_label == -1:
                best_score = score[k]
                best_label = k
        if math.fabs(score[1] - score[2]) < 0.000001:
            best_label = 0
        if best_label == 0:
            test_result.append('neutral')
        elif best_label == 1:
            test_result.append('positive')
        elif best_label == 2:
            test_result.append('negative')
    return test_result


'''
def naive_bayes(train_pis, test_features_matrix, train_theta_t):
    n_tweet = len(test_features_matrix)
    n_feature = len(test_features_matrix[0])
    test_result = []
    for tweet in range(n_tweet):
        score = [0, 0, 0]
        minf = [0, 0, 0]
        for k in range(3):
            score[k] = math.log(train_pis[k])
            for feature in range(n_feature):
                theta = train_theta_t[feature][k]
                test_feature = test_features_matrix[tweet][feature]
                if theta == 0:
                    if test_feature == 0:
                        score[k] += 0
                    else:
                        minf[k] += 1
                else:
                    score[k] += test_feature * math.log(theta)
        best_label = -1
        best_score = -1
        best_minf = n_feature + 5
        for k in range(3):
            if best_label == -1 or (minf[k] < best_minf) or (minf[k] == best_minf and score[k] > best_score):
                best_minf = minf[k]
                best_score = score[k]
                best_label = k
        if minf[1] == minf[2] and math.fabs(score[1] - score[2]) < 0.000001:
            best_label = 0
        if best_label == 0:
            test_result.append('neutral')
        elif best_label == 1:
            test_result.append('positive')
        elif best_label == 2:
            test_result.append('negative')
    return test_result
'''


def bernoulli_naive_bayes(train_pis, test_features_matrix, train_theta_s):
    n_tweet = len(test_features_matrix)
    n_feature = len(test_features_matrix[0])
    test_result = []
    for tweet in range(n_tweet):
        score = [0, 0, 0]
        for k in range(3):
            score[k] = math.log(train_pis[k])
            for feature in range(n_feature):
                theta = train_theta_s[feature][k]
                test_feature = min(test_features_matrix[tweet][feature], 1)
                value = test_feature * theta + (1-test_feature) * (1-theta)
                if value == 0:
                    score[k] += -math.inf
                else:
                    score[k] += math.log(value)
        best_label = -1
        best_score = -1
        for k in range(3):
            if best_label == -1 or score[k] > best_score:
                best_score = score[k]
                best_label = k
        if math.fabs(score[1] - score[2]) < 0.000001:
            best_label = 0
        if best_label == 0:
            test_result.append('neutral')
        elif best_label == 1:
            test_result.append('positive')
        elif best_label == 2:
            test_result.append('negative')
    return test_result


'''
def bernoulli_naive_bayes(train_pis, test_features_matrix, train_theta_s):
    n_tweet = len(test_features_matrix)
    n_feature = len(test_features_matrix[0])
    test_result = []
    for tweet in range(n_tweet):
        score = [0, 0, 0]
        score_log = [0, 0, 0]
        minf = [0, 0, 0]
        for k in range(3):
            score[k] = math.log(train_pis[k])
            for feature in range(n_feature):
                theta = train_theta_s[feature][k]
                test_feature = min(test_features_matrix[tweet][feature], 1)
                value = test_feature * theta + (1-test_feature) * (1-theta)
                if value == 0:
                    minf[k] += 1
                else:
                    score_log[k] += math.log(value)
            score[k] += score_log[k]
        best_label = -1
        best_score = -1
        best_minf = n_feature + 5
        for k in range(3):
            if best_label == -1 or (minf[k] < best_minf) or (minf[k] == best_minf and score[k] > best_score):
                best_minf = minf[k]
                best_score = score[k]
                best_label = k
        if minf[1] == minf[2] and math.fabs(score[1] - score[2]) < 0.000001:
            best_label = 0
        if best_label == 0:
            test_result.append('neutral')
        elif best_label == 1:
            test_result.append('positive')
        elif best_label == 2:
            test_result.append('negative')
    return test_result
'''


def get_class_id(label):
    if label == 'neutral':
        return 0
    elif label == 'positive':
        return 1
    elif label == 'negative':
        return 2
    return -1


def find_accuracy(test_result, test_labels):
    correct = 0
    failure = 0
    counter = [[0, 0, 0], [0, 0, 0], [0, 0, 0]]
    n_tweet = len(test_result)
    for tweet in range(n_tweet):
        prediction = get_class_id(test_result[tweet])
        actual = get_class_id(test_labels[tweet])
        counter[prediction][actual] += 1
        if test_result[tweet] == test_labels[tweet]:
            correct += 1
        else:
            failure += 1
    print('correct: ', correct)
    print('failure: ', failure)
    print('accuracy: ', float(correct) / (correct + failure))
    print('counter: ', counter)
