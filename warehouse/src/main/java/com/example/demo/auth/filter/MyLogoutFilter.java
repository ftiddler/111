package com.example.demo.auth.filter;

import com.alibaba.fastjson.JSON;
import com.example.demo.auth.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MyLogoutFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        if (httpServletRequest.getServletPath().equals("/logout")) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().print( JSON.toJSON("{\"code\": 20000,\"data\": {\"msg\":\"登出成功\"}}"));
            return;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

}
