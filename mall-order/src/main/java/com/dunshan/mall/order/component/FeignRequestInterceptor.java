package com.dunshan.mall.order.component;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * 用于Feign传递请求头的拦截器
 * Created by dunshan on 2020-11-17 11:23
 */
@Log4j2
public class FeignRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    if (name.equals("authorization") || name.equals("user") || name.equals("x") ) {
                        String values = request.getHeader(name);
                        requestTemplate.header(name, values);
                    }

//                    log.info("用于Feign传递请求头的拦截器name:" + name + ", values" + values);
                }
            }
        }
    }
}
