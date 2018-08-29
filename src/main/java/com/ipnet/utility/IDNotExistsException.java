package com.ipnet.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lzb
 * @date 2018/7/22 22:21
 */
@AllArgsConstructor
@ToString
@Getter
@Setter
public class IDNotExistsException extends Exception {

    public IDNotExistsException(String message) {
        super(message);
    }
}
