package com.nju171250.njuTeacher.security;

import com.nimbusds.jose.JOSEException;
import com.nju171250.njuTeacher.utils.JWTUtils;
import net.minidev.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

public class JwtRefreshSuccessHandler implements AuthenticationSuccessHandler {
    private static final int tokenRefreshInterval = 5 * 60;  //刷新间隔5分钟

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = authentication.getCredentials().toString();
        Map<String, Object> map = null;
        try {
            map = JWTUtils.valid(token);
        }catch (JOSEException e){
            e.printStackTrace();
        }catch (ParseException e){
            e.printStackTrace();
        }
//        System.currentTimeMillis() + 30 * 60 * 1000
        Map<String, Object> old = (JSONObject)map.get("data");
        long timeMillis = (long)((JSONObject)map.get("data")).get("exp");
        if(timeMillis < System.currentTimeMillis() + 25 * 60 * 1000){
            // if shixiaoshijian < 25 min, shua xin token guoqishijian
            old.replace("exp", timeMillis,System.currentTimeMillis() + 30 * 60 * 1000);
            try {
                String newToken = JWTUtils.creatToken(old);
                httpServletResponse.setHeader("Authorization", newToken);
            }catch (JOSEException e){
                e.printStackTrace();
            }
        }
        else
            httpServletResponse.setHeader("Authorization", token);
    }
}
