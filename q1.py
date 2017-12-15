# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np
import heapq
import random

# Space for question 1
def findbestprofit(pricesarray, k):
    np_maxArray = np.empty()
    np_priceArray = np.array(pricesarray)
    size = np_priceArray.shape[1]
    for i in xrange(size):
        for ii in xrange(size):
            if (i != ii):
                maxProfit = np_priceArray[ii] - np_priceArray[i]
                np_maxArray.append(maxProfit)           
    num = 0
    if (k%2 == 0):
        num = k/2
    else:
        num = (k-1)/2
    if (num > 0):
        maxProfit = np.amax(np_maxArray)
        totalMax = 0
        totalMax += maxProfit
        while (num != 0):
            np_itemToDelete = np.empty()
            np_itemToDelete.append(maxProfit)
            np.delete(np_maxArray, np_itemToDelete)
            maxProfit = np.amax(np_maxArray)
            totalMax += maxProfit
            num = num - 1
        
