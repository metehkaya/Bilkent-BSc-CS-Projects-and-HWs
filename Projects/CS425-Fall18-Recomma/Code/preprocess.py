import os

def get_movie_names(base_path, modified_movies):
    #print("inside get_movie_names.")
    filenames = os.listdir(base_path)
    movie_names = []
    for count, filename in enumerate(filenames):
        if count in modified_movies:
            movie_names.append(filename[7:-4] + '_modified')
        movie_names.append(filename[7:-4])

    new_movie_names = []
    for name in movie_names:
        length = len(name)
        temp = ""
        for i in range(length):
            if name[i] == '_':
                temp += ':'
            else:
                temp += name[i]
        length = len(temp)
        foundComma = False
        suffix = ""
        for i in range(length-1, 0, -1):
            if foundComma:
                break
            else:
                suffix += temp[i]
                if temp[i] == ',':
                    foundComma = True
        if foundComma:
            suffix = suffix[::-1]
            prefix = suffix[2:]
            if prefix == "A" or prefix == "An" or prefix == "The":
                film_name = prefix + " " + temp[0:length - len(suffix)]
            else:
                film_name = temp
        else:
            film_name = temp
        new_movie_names.append(film_name)

    return new_movie_names