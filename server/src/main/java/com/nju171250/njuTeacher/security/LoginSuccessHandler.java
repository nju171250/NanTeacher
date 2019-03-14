package com.nju171250.njuTeacher.security;

import com.nimbusds.jose.JOSEException;
import com.nju171250.njuTeacher.utils.JWTUtils;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String token = "";
        Map<String, Object> map = new HashMap<>();
        map.put("openid", authentication.getName());
        // 当前时间半小时后过期
        map.put("exp", System.currentTimeMillis() + 30 * 60 * 1000);
        try {
            token = JWTUtils.creatToken(map);
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            System.out.println(((JSONObject)JWTUtils.valid(token).get("data")).get("exp"));
        }catch (JOSEException e){
            e.printStackTrace();
        }catch (ParseException e2){
            e2.printStackTrace();
        }
        httpServletResponse.setHeader("Authorization", token);
    }
}
