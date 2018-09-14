package com.ipnet.bl.patentbl;

import com.ipnet.utility.IDNotExistsException;

public interface PatentHelper {
      String receivePatentName(String patentId) throws IDNotExistsException;
      String receivePatentURL(String patentId) throws IDNotExistsException;
      String receivePatentType(String patentId) throws IDNotExistsException;
      String receivePatentProfile(String patentId) throws IDNotExistsException;
}
