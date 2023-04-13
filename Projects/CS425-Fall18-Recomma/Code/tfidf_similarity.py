import os
import re
import string
from gensim import corpora, models, similarities
import pickle

def get_script(base_path, filename):
    with open(os.path.join(base_path, filename), 'r', encoding='utf-8') as myfile:
        data = myfile.read().lower()
        data = re.sub('[' + string.punctuation + ']+', '', data)
        data = re.sub('[\s]+', ' ', data)
        data = data.strip().split(' ')

    return data


def get_script_lists(base_path):
    filenames = os.listdir(base_path)
    script_lists = []
    for count, filename in enumerate(filenames):
        script_lists.append(get_script(base_path, filename))
    return script_lists


def tfidf(script_lists):
    if not os.path.isfile("tf_idf_results.pkl"):
        # create dictionary (index of each element)
        dictionary = corpora.Dictionary(script_lists)
        dictionary.save('/tmp/scripts.dict')  # store the dictionary, for future reference

        # compile corpus (vectors number of times each elements appears)
        raw_corpus = [dictionary.doc2bow(t) for t in script_lists]
        corpora.MmCorpus.serialize('/tmp/scripts.mm', raw_corpus)  # store to disk

        # STEP 2 : similarity between corpuses
        dictionary = corpora.Dictionary.load('/tmp/scripts.dict')
        corpus = corpora.MmCorpus('/tmp/scripts.mm')

        # Transform Text with TF-IDF
        tfidf = models.TfidfModel(corpus)

        # corpus tf-idf
        corpus_tfidf = tfidf[corpus]

        # STEP 3 : Create similarity matrix of all files
        index = similarities.MatrixSimilarity(tfidf[corpus])
        index.save('/tmp/scripts.index')
        index = similarities.MatrixSimilarity.load('/tmp/scripts.index')
        sims = index[corpus_tfidf]
        pickle.dump(sims, open("tf_idf_results.pkl", "wb"))

    else:
        with open('tf_idf_results.pkl', 'rb') as pickle_file:
            sims = pickle.load(pickle_file)

    return sims

def tfidf_similarity(similarity_matrix, movie_names, movie_name_1, movie_name_2):
    bool = False
    for index, movie_name in enumerate(movie_names):
        if bool == True and (movie_name == movie_name_1 or movie_name == movie_name_2):
            return similarity_matrix[movie_index][index]

        if bool == False and (movie_name == movie_name_1 or movie_name == movie_name_2):
            movie_index = index
            bool = True


'''current_path = os.path.dirname(os.path.abspath(__file__))
base_path = os.path.join(current_path, 'resources')
movie_names = preprocess.get_movie_names(base_path, [])
scripts = get_script_lists(base_path)
similarity_matrix = tfidf(scripts)
print(similarity_matrix)
print(tfidf_similarity(similarity_matrix, movie_names, 'Austin Powers - International Man of Mystery', 'Austin Powers - The Spy Who Shagged Me'))'''