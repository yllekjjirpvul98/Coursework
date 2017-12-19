# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np

# Space for question 1
def findbestprofit(pricesarray, k):
    np_priceArray = np.array(pricesarray)
    size = np_priceArray.shape[0]
    maxProfitArray = list()
    for i in xrange(size):
        for ii in xrange(size):
            if (i != ii and ii > i):
                maxProfit = np_priceArray[ii] - np_priceArray[i]
                maxProfitArray.append(maxProfit)
    np_maxArray = np.array(maxProfitArray)
    print(np_maxArray)
    num = 0
    if (k%2 == 0):
        num = k/2
    else:
        num = (k-1)/2
    if (num > 0):
        maxProfit = np.amax(np_maxArray)
        totalMax = 0
        totalMax += maxProfit
        num = num - 1
        while (num != 0):
            itemToDelete = list()
            itemToDelete.append(maxProfit)
            np_itemToDelete = np.array(itemToDelete)
            np.delete(np_maxArray, np_itemToDelete)
            maxProfit = np.amax(np_maxArray)
            totalMax += maxProfit
            num = num - 1
    else:
        maxProfit = np.amax(np_maxArray)
        totalMax = 0
        totalMax += maxProfit
    return totalMax
        

