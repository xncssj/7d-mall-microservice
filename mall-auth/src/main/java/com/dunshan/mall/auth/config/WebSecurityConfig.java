package com.dunshan.mall.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * SpringSecurity配置
 * Created by dunshan on 2020/6/19.
 */
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyPasswordEncoder myPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .requestMatchers(EndpointRequest.toAnyEndpoint()).permitAll()
                .antMatchers("/rsa/publicKey").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 创建 DelegatingPasswordEncoder，注册自定义 MD5 编码器
        // 这样可以避免 "There is no PasswordEncoder mapped for the id 'null'" 错误
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("MD5", myPasswordEncoder);
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        // 设置默认编码器为我们的自定义编码器
        DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("MD5", encoders);
        // 设置默认编码器，用于处理没有前缀的密码
        delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(myPasswordEncoder);
        return delegatingPasswordEncoder;
    }

}