package com.ipnet.bl.patentpoolbl;

import com.ipnet.utility.IDNotExistsException;

public interface PoolHelper {
    String receivePoolURL(String poolId) throws IDNotExistsException;
    String receivePoolName(String poolId) throws IDNotExistsException;
    String receivePoolProfile(String poolId) throws IDNotExistsException;
    int receivePoolPatentsNum(String poolId) throws IDNotExistsException;
}
