package com.metoak.mes.common.interceptor;

import com.metoak.mes.common.threadLocal.LoginUser;
import com.metoak.mes.common.threadLocal.LoginUserHolder;
import com.metoak.mes.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.swagger.models.HttpMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }
        String token = request.getHeader("Authorization");
        Claims claims = JwtUtil.parseToken(token);
        String username = claims.get("username", String.class);
        LoginUserHolder.setLoginUser(new LoginUser(username));

        return true;
    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        LoginUserHolder.clear();
//    }
}