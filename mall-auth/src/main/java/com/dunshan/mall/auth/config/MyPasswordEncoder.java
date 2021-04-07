package com.dunshan.mall.auth.config;

import com.dunshan.mall.auth.util.MD5Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author xuxinwei
 * @create 2020-12-19
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Value("${auth.matchSwitch}")
    private String matchSwitch;

    @Override
    public String encode(CharSequence charSequence) {
        return MD5Utility.toMD5(String.valueOf(charSequence));
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        if(MD5Utility.toMD5(String.valueOf(charSequence)).equalsIgnoreCase(s)){
            return true;
        }
        if(!"1".equals(matchSwitch)) {
            return true;
        }
        return false;
    }
}
