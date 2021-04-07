package com.dunshan.mall.auth.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Utility {
    public synchronized static String toMD5(String plainText) {
        return DigestUtils.md5Hex(plainText);
    }
}
