package com.nju171250.njuTeacher.security;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OptionsRequestFilter extends  OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if(request.getMethod().equals("OPTIONS")) {
            response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,HEAD");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type,authorization");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
