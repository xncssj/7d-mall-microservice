package com.dunshan.mall.auth.config;

import com.dunshan.mall.auth.util.MD5Utility;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author xuxinwei
 * @create 2020-12-19
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    private static final String PREFIX = "{MD5}";
    
    // 添加BCryptPasswordEncoder用于处理后台管理员密码
    private BCryptPasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();

    @Value("${auth.matchSwitch}")
    private String matchSwitch;

    @Override
    public String encode(CharSequence charSequence) {
        // DelegatingPasswordEncoder 会自动添加前缀，这里只返回纯 MD5 值
        return MD5Utility.toMD5(String.valueOf(charSequence));
    }

    @Override
    public boolean matches(CharSequence charSequence, String encodedPassword) {
        // 去掉编码器前缀（如果存在）
        String password = encodedPassword;
        if (password.startsWith(PREFIX)) {
            password = password.substring(PREFIX.length());
        }
        
        // 检查是否是BCrypt加密的密码（后台管理员用户）
        if (password.startsWith("$2a$") || password.startsWith("$2y$") || password.startsWith("$2b$")) {
            return bcryptPasswordEncoder.matches(String.valueOf(charSequence), password);
        }
        
        // 比较MD5密码（前台会员用户）
        if (MD5Utility.toMD5(String.valueOf(charSequence)).equalsIgnoreCase(password)) {
            return true;
        }
        
        // 如果 matchSwitch 不为 1，允许所有密码（用于测试）
        if (!"1".equals(matchSwitch)) {
            return true;
        }
        return false;
    }
}