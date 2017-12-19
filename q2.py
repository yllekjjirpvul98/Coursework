# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np
import itertools

def findsubsets(S,m):
    subsetArray = list()
    for i in xrange(m):
        subset = list(itertools.combinations(S, i))
        subsetArray.append(subset)
    return subsetArray

def findDiversifiedPortfolio(profitA, profitB):
    # Edit this method for your answer to question 2.
    np_profitA = np.array(profitA)
    np_profitB = np.array(profitB)
    sizeA = np_profitA.shape[0]
    sizeB = np_profitB.shape[0]
    setA = findsubsets(profitA, sizeA+1)
    setB = findsubsets(profitB, sizeB+1)
    np_setA = np.array(setA)
    np_setB = np.array(setB)
    np_sumA = np.empty(sizeA)
    np_sumB = np.empty(sizeB)
    np.copyto(np_sumA, profitA)
    np.copyto(np_sumB, profitB)
    print(np_setB)
    sumA = list()
    sumB = list()
    for i in xrange(len(setA)):
        if (i>1):
            lists = setA[i]
            for x in xrange(len(lists)):
                sum = 0
                tuples = lists[x]
                for y in xrange(len(tuples)):
                    sum += tuples[y]
                sumA.append(sum)
    np_sumA2 = np.array(sumA)
    np_sumA = np.hstack((np_sumA, np_sumA2))
    for i in xrange(len(setB)):
        if (i>1):
            lists = setB[i]
            for x in xrange(len(lists)):
                sum = 0
                tuples = lists[x]
                for y in xrange(len(tuples)):
                    sum += tuples[y]
                sumB.append(sum)
    np_sumB2 = np.array(sumB)
    np_sumB = np.hstack((np_sumB, np_sumB2))
    sizeofsumA = np_sumA.shape[0]
    sizeofsumB = np_sumB.shape[0]
    diffArray = list()
    for a in xrange(sizeofsumA):
        for b in xrange(sizeofsumB):
            diff = np_sumA[a] - np_sumB[b]
            if (diff < 0):
                diff = -diff
            diffArray.append(diff)
    np_diffArray = np.array(diffArray)
    print(np_diffArray)
    return np.amin(np_diffArray)
