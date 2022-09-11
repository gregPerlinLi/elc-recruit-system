package com.gdutelc.recruit.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 关于跨域问题
 * @author TUFSolareyes
 * @date 22/09/10
 */
public class AccessInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers","Accept,Authorization,Content-Type,Referer,User-Agent");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        return true;
    }
}
