package com.nju171250.njuTeacher.security;

import com.nju171250.njuTeacher.mapper.UserMapper;
import com.nju171250.njuTeacher.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByPrimaryKey(s);
        UserDetails userDetails = null;
        if(user != null)
         userDetails = new MyUserDetails(user.getUserId(), "{bcrypt}"+new BCryptPasswordEncoder().encode("njuTeacher"));
        return userDetails;
    }

    public void deleteUserLoginInfo(String s){}
}
