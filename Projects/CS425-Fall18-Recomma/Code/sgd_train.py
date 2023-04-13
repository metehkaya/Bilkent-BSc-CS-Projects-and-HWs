import math
from movie_ratings import get_rating_matrix
import numpy as np

def calc_sse( rating , p , q , list_nonzero ):
    prediction = p @ np.transpose(q)
    n = rating.shape[0]
    m = rating.shape[1]
    sse = 0
    for index in range(len(list_nonzero)):
        user = list_nonzero[index][0]
        item = list_nonzero[index][1]
        sse = sse + ((rating.item((user, item)) - prediction.item((user, item))) ** 2)
    return sse

def train_model( rating , rating_training , total ):
    f = 100
    stdiv = 10
    epochs = 1000
    rate1 = 0.001
    rate2 = 0.001
    param1 = 0.00
    param2 = 0.00
    n = rating.shape[0]
    m = rating.shape[1]
    p = np.random.normal( 0, 1.0 / stdiv, (n,f) )
    q = np.random.normal( 0, 1.0 / stdiv, (m,f) )
    list_nonzero = []
    list_sgd = []
    for user in range(n):
        for item in range(m):
            if rating_training.item((user,item)) > 0 or (user + item) % 10 < 10:
                list_sgd.append( ( user , item ) )
            if rating_training.item((user,item)) > 0:
                list_nonzero.append( ( user , item ) )
    print( "Validation Time" )
    for step in range(epochs):
        for index in range( len( list_sgd ) ):
            user = list_sgd[index][0]
            item = list_sgd[index][1]
            value = rating_training.item((user, item))
            error = value - np.dot(p[user], q[item])
            q[item] += rate1 * ( error * p[user] - param2 * q[item] )
            p[user] += rate2 * ( error * q[item] - param1 * p[user] )
        sse = calc_sse(rating, p, q, list_nonzero)
        rmse = math.sqrt(sse) / total
        print(step, sse, rmse)
    return p, q

def read_rating():
    f = open('input2.txt', 'r')
    l = [[int(num) for num in line.split(',')] for line in f]
    rating = np.asarray(l);
    return rating

def calc_nonzero( rating_training ):
    n = rating_training.shape[0]
    m = rating_training.shape[1]
    cnt = 0
    for user in range(n):
        for item in range(m):
            if rating_training.item(( user , item )) > 0:
                cnt = cnt + 1
    return cnt

def finalPrediction( p , q ):
    prediction = p @ np.transpose(q)
    mat = np.matrix(prediction)
    with open('outfile_training.txt', 'wb') as f:
        for line in mat:
            np.savetxt(f, line, fmt='%.2f')

def get_rating_training( rating ):
    alpha = 0
    beta = 0
    n = rating.shape[0]
    m = rating.shape[1]
    boundUser = int( alpha * n )
    boundItem = int( beta * m )
    rating_training = rating
    for user in range(n):
        for item in range(m):
            if user < boundUser and item < boundItem:
                rating_training[user, item] = 0
    return rating_training

rating, imsdb_to_common = get_rating_matrix()
rating = np.transpose(rating)
rating = rating[0:10000 , 0:808]

'''
rating = read_rating()
'''

print(rating.shape)
rating_training = get_rating_training( rating )
total = calc_nonzero( rating_training )
print(total)
p, q = train_model( rating , rating_training , total )
finalPrediction( p , q )