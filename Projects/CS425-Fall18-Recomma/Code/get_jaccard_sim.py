def get_jaccard_sim( genres1 , genres2 ):
    len1 = len( genres1 )
    len2 = len( genres2 )
    # print( "len1" , len1 )
    # print( "len2" , len2 )
    if len1 == 1 and genres1[0] == 'Genre not found.':
        genres1 = []
    if len2 == 1 and genres2[0] == 'Genre not found.':
        genres2 = []
    len1 = len( genres1 )
    len2 = len( genres2 )
    if len1 == 0 and len2 == 0:
        return -2
    if len1 == 0 or len2 == 0:
        return -3
    num = 0
    den = len1 + len2
    for genre in genres1:
        if genre in genres2:
            num = num + 1
            den = den - 1
    # print( "num" , num )
    # print( "den" , den )
    return num / den