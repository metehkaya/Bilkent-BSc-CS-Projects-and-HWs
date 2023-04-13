from read_data import get_inputs

from helper import get_label_counter
from helper import get_train_pi
from helper import get_train_t
from helper import get_train_theta_t
from helper import get_train_theta_t_map
from helper import get_train_s
from helper import get_train_theta_s

from tester import multinomial_naive_bayes
from tester import bernoulli_naive_bayes
from tester import find_accuracy

from observe_common import get_frequent_word_ids
from observe_common import read_vocab_file


train_labels, train_features_matrix, test_labels, test_features_matrix = get_inputs()

train_n_neutral, train_n_positive, train_n_negative = get_label_counter(train_labels)
print('train_n')
print(train_n_neutral, train_n_positive, train_n_negative)

train_pi_neutral, train_pi_positive, train_pi_negative = get_train_pi(train_n_neutral, train_n_positive, train_n_negative)
train_pis = [train_pi_neutral, train_pi_positive, train_pi_negative]
print('train_pis')
print(train_pis)

train_t, train_t_total = get_train_t(train_labels, train_features_matrix)
print('train_t')
print(train_t)
print('train_t_total')
print(train_t_total)

train_theta_t = get_train_theta_t(train_t, train_t_total)
print('train_theta_t')
print(train_theta_t)

train_theta_t_map = get_train_theta_t_map(train_t, train_t_total)
# print('train_theta_t_map')
# print(train_theta_t_map)

train_s = get_train_s(train_labels, train_features_matrix)
print('train_s')
print(train_s)

train_theta_s = get_train_theta_s(train_s, train_n_neutral, train_n_positive, train_n_negative)
print('train_theta_s')
print(train_theta_s)

test_multinomial_naive_bayes = multinomial_naive_bayes(train_pis, test_features_matrix, train_theta_t)
print('test_multinomial_naive_bayes')
print(test_multinomial_naive_bayes)
find_accuracy(test_multinomial_naive_bayes, test_labels)

test_multinomial_naive_bayes_map = multinomial_naive_bayes(train_pis, test_features_matrix, train_theta_t_map)
print('test_multinomial_naive_bayes_map')
print(test_multinomial_naive_bayes_map)
find_accuracy(test_multinomial_naive_bayes_map, test_labels)

test_bernoulli_naive_bayes = bernoulli_naive_bayes(train_pis, test_features_matrix, train_theta_s)
print('test_bernoulli_naive_bayes')
print(test_bernoulli_naive_bayes)
find_accuracy(test_bernoulli_naive_bayes, test_labels)

vocab = read_vocab_file()
print('vocab')
print(vocab)

all_freq_words = get_frequent_word_ids(train_t, vocab)
print('all_freq_word_ids')
print('Neutral:', all_freq_words[0])
print('Positive:', all_freq_words[1])
print('Negative:', all_freq_words[2])
