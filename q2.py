# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np


def findDiversifiedPortfolio(profitA, profitB):
    # Edit this method for your answer to question 2.
    np_profitA = np.array(profitA)
    np_profitB = np.array(profitB)
    sizeA = np_profitA.shape[0]
    sizeB = np_profitB.shape[0]
    sumA = np.empty()
    sumB = np.empty()
    for x in xrange(sizeA):
        for y in xrange(sizeA):
            if (x != y and y > x):
                subset = np_profitA[x:y]
                sumA.append(np.sum(subset))
    for x in xrange (sizeB):
        for y in xrange(sizeB):
            if (x != y and y > x):
                subset = np_profitB[x:y]
                sumB.append(np.sum(subset))
    sizeofsumA = sumA.shape[0]
    sizeofsumB = sumB.shape[0]
    diffArray = np.empty()
    for a in xrange(sizeofsumA):
        for b in xrange(sizeofsumB):
            diff = sumA[a] - sumB[b]
            if (diff < 0):
                diff = -diff
            diffArray.append(diff)
    return np.amin(diffArray)
                
            



