# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np

# THIS IS YOUR QUESTION 3 FUNCTION
def findSafeNetwork(numServers, loggingDeviceLimits, connectionDetails):
    # Edit this method for your answer to question 3.
    np_limit = np.array(loggingDeviceLimits)
    numOfDevices = np_limit.shape[0]
    np_connect = np.array(connectionDetails)
    sizeofConnect = np_connect.shape[0]
    allConnect = list()
    for i in xrange(sizeofConnect):
        allConnect.append(np_connect[i][0:2])
        allConnect.append(np_connect[i][1:3])
    np_allConnect = np.array(allConnect)
    np_allConnect = np.unique(np_allConnect, axis=0)
    hashmap = {}
    for connections in np_allConnect:
        x = connections[0]
        y = connections[1]
        sum = x+y
        if sum not in hashmap:
            hashmap[sum] = 1
        else:
            hashmap[sum] += 1
    sizeofAllConnect = len(hashmap.keys())
    connected = 0
    for x in xrange(numOfDevices):
        sizeofAllConnect -= loggingDeviceLimits[x]
        connected += loggingDeviceLimits[x]
        if (sizeofAllConnect <= 0):
            if (sizeofAllConnect < 0):
                connected = connected + sizeofAllConnect
        if (sizeofAllConnect == numServers):
            connected = connected - 1
    return connected
