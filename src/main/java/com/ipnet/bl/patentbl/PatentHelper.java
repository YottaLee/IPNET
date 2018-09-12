package com.ipnet.bl.patentbl;

import com.ipnet.utility.IDNotExistsException;

public interface PatentHelper {
      String receivePatentName(String patentId) throws IDNotExistsException;
}
