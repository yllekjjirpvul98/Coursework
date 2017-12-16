# ONLY EDIT FUNCTIONS MARKED CLEARLY FOR EDITING

import numpy as np

# THIS IS YOUR QUESTION 3 FUNCTION
def findSafeNetwork(numServers, loggingDeviceLimits, connectionDetails):
    # Edit this method for your answer to question 3.
    np_limit = np.array(loggingDeviceLimits)
    numOfDevices = np_limit.shape[0]
    np_connect = np.array(connectionDetails)
    sizeofConnect = np_connect.shape[0]
    np_allConnect = np.empty()
    for i in xrange(sizeofConnect):
        np_allConnect.append(np_collect[i][0:2])
        np_allConnect.append(np_collect[i][1:3])
    np.unique(np_allConnect, axis=0)
    sizeofAllConnect = np_allConnect.shape[0]
    connected = 0
    for x in xrange(numOfDevices):
        sizeofAllConnect -= numOfDevices[x]
        connected += numOfDevices[x]
        if (sizeOfAllConnect <= 0):
            if (sizeOfAllConnect < 0):
                connected = connected + sizeOfAllConnect
        return connected
    sizeofAllConnect = np_allConnect.shape[0]
    allConnect = np.concatenate((np_allConnect[0], np_allConnect[1]))
    
  
