import os
import pickle


def get_frequent_word_ids(train_t, vocab, alpha=20):
    n_word = len(train_t)
    all_freq_words = []
    for k in range(3):
        word_pairs = []
        for word in range(n_word):
            word_pairs.append([word, train_t[word][k]])
        sorted_word_pairs = sorted(word_pairs, key=lambda x: x[1])
        i = n_word - 1
        freq_words = []
        while i >= n_word - alpha:
            freq_words.append(vocab[sorted_word_pairs[i][0]])
            i -= 1
        all_freq_words.append(freq_words)
    return all_freq_words


def read_vocab_file():
    if not os.path.isfile("vocabulary.pkl"):
        vocab = []
        with open("question-4-vocab.txt", encoding="utf8") as ins:
            for line in ins:
                length = len(line)
                index = length-2
                while line[index:index+1].isdigit():
                    index -= 1
                vocab.append(line[0:index])
        pickle.dump(vocab, open("vocabulary.pkl", "wb"))
    else:
        with open('vocabulary.pkl', 'rb') as pickle_file:
            vocab = pickle.load(pickle_file)
    return vocab
