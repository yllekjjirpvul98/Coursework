# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np
import heapq
import random

# Space for question 1
def findbestprofit(pricesarray, k):
    maxProfitArray = []
    for i in range (len(pricearray)):
        for ii in range (len(pricearray)):
            if (i != ii):
                maxProfit = pricearray[ii] - pricearray[i]
                maxProfitArray.append(maxProfit)
             
maxProfit = 0
random.shuffle(maxPriceArray)
num = 0
if (k%2 == 0):
    num = k/2
else:
    num = (k-1)/2
largestNum = heapq.nlargest(num, x)
for num in largestNum:
    maxProfit += num
return maxProfit
