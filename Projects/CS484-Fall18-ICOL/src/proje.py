from PIL import Image
import glob
import numpy as np
import scipy.io as sio
import os
from resnet import resnet50
import torch

# fixed size of final training images
mySize = 224,224

# list of feature vectors of training images
featurevector_list = []

# 0-indexed category id
a=-1

# category names in the "train" folder
filelist = next(os.walk('./train'))[1]

# look for all categories
for file in filelist:
    
    # check directory exception
    a = a + 1
    try:
        os.makedirs(str(a))
    except OSError:
        pass
    
    # 0-indexed picture id of a specific category
    u=0
    
    # look for all images
    for filename in glob.glob('train/' + file + '/*.JPEG'): 
        
        print(filename)
        
        # get the image
        im = Image.open(filename).convert('RGB')

        # before fixing to the final size, determine the side with max length
        width, height = im.size
        maxSidestr = ""
        if( width > height):
            maxSide = width
            minSide = height
            maxSidestr = "w"
        else:
            maxSide = height
            minSide = width
            maxSidestr = "h"   
        
        # init new image
        newimg = Image.new('RGB', (maxSide, maxSide) )
        diff = (maxSide - minSide) / 2

        # if height is longer, generate height X height where some pixels are black
        if maxSidestr == "h":
            for x in range(maxSide):
               for y in range(maxSide):
                   if x < diff or x >= maxSide-diff:
                       newimg.putpixel( (x,y), (0,0,0))
                   else:
                       former_color = im.getpixel( (x-diff, y) )
                       newimg.putpixel( (x,y), former_color)

        # do similar, if width is longer
        if maxSidestr == "w":
            for x in range(maxSide):
               for y in range(maxSide):
                   if y < diff or y >= maxSide-diff:
                       newimg.putpixel( (x,y), (0,0,0))
                   else:
                       former_color = im.getpixel( (x,y-diff) )
                       newimg.putpixel( (x,y), former_color)

        # convert to 224 X 224
        newimg = newimg.resize(mySize, Image.ANTIALIAS)
        
        # convert image to a numpy array
        newimgArray = newimg
        newimgArray = np.asarray(newimgArray, dtype=np.float32)
        newimgArray.setflags(write=1)
        
        # apply normalization for each pixel
        i=0
        while i < mySize[0]:
            j = 0
            while j < mySize[1]:
                if newimgArray[i, j, :] is not [0, 0, 0]:
                    r = ((newimgArray[i, j, 0] / 255) - 0.485) / 0.229
                    g = ((newimgArray[i, j, 1] / 255) - 0.456) / 0.224
                    b = ((newimgArray[i, j, 2] / 255) - 0.406) / 0.225
                    former_color = [r, g, b]
                    newimgArray[i, j, :] = former_color
                    j = j + 1
            i = i + 1

        # feature extraction
        model = resnet50(pretrained=True)
        model.eval()
        reshapedimgArray = np.reshape(newimgArray, [1, 224, 224, 3])
        reshapedimgArray = np.transpose(reshapedimgArray, [0, 3, 1, 2])
        numpyImg = torch.from_numpy(reshapedimgArray)
        feature_vector = model(numpyImg)
        feature_vector = feature_vector.detach().numpy()
        feature_vector = feature_vector[0]

        # save feature vectors
        os.chdir(str(a))
        sio.savemat(str(a)+str(u)+'.mat',{'featurevector':feature_vector},oned_as='row')
        os.chdir("..")

        # add the feature vector of this training image to the list of feature vectors
        featurevector_list.append(feature_vector)
        u=u+1