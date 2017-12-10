# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np

# Space for question 1
def findbestprofit(pricesarray, k):
    maxProfitArray = []
    for i in range (len(pricearray)):
        for ii in range (len(pricearray)):
            if (i != ii):
                maxProfit = pricearray[i] - pricearray[ii]
                maxProfitArray.append(maxProfit)
             
   profitA = max(maxProfitArray)
   for i in range(len(maxProfitArray)):
        if (maxProfitArray[i] == profitA):
            maxProfitArray.pop(i)
            break
   profitB = max(maxProfitArray)

return profitA+profitB
