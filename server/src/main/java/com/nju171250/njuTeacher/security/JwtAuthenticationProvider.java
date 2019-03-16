package com.nju171250.njuTeacher.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nimbusds.jose.JOSEException;
import com.nju171250.njuTeacher.utils.JWTUtils;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.NonceExpiredException;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Map;

public class JwtAuthenticationProvider implements AuthenticationProvider {
    private JwtUserDetailService userService;

    public JwtAuthenticationProvider(JwtUserDetailService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // get token
        String token = (String)authentication.getCredentials();
        Map<String, Object> result = null;
        try {
            result = JWTUtils.valid(token);
        }catch (JOSEException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
        if((int)result.get("Result") == 1)
            throw new BadCredentialsException("Bad Token");
        if((int)result.get("Result") == 2)
            throw new NonceExpiredException("Token expires");
        String username = ((JSONObject)result.get("data")).get("openid").toString();
        UserDetails user = userService.loadUserByUsername(username);
        if(user == null || user.getPassword()==null)
            throw new NonceExpiredException("Token expires");
//        String encryptSalt = user.getPassword();// 数据库中的用户密码
//        String tokenPassword = "{bcrypt}" + new BCryptPasswordEncoder().encode(((JSONObject)result.get("data")).get("password").toString());//token读取的密码
//        if(!tokenPassword.equals(encryptSalt))
//            throw new BadCredentialsException("Token Verify Failed");
        //成功后返回认证信息，filter会将认证信息放入SecurityContext
        JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(user, token, user.getAuthorities());
        return jwtToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(JwtAuthenticationToken.class);
    }
}
