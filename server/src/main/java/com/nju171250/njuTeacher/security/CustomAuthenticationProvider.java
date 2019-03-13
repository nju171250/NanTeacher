package com.nju171250.njuTeacher.security;

import com.nju171250.njuTeacher.mapper.UserMapper;
import com.nju171250.njuTeacher.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    UserMapper userMapper;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        // 获取系统中的用户信息
        User user = retrieveUser(name);
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
        if(user != null) {
            token.setAuthenticated(true);
        }
        else
            token.setAuthenticated(false);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

    public User retrieveUser(String username){
        User user = userMapper.selectByPrimaryKey(username);
        if(user == null)
            return null;
        return user;
    }
}
