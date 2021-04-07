package com.dunshan.mall.member.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public synchronized static String toMD5(String plainText) {
        return DigestUtils.md5Hex(plainText);
    }
}
