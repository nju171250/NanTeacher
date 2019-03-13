package com.nju171250.njuTeacher.controller;

import com.nju171250.njuTeacher.mapper.UserMapper;
import com.nju171250.njuTeacher.model.User;
import com.nju171250.njuTeacher.model.UserExample;
import com.nju171250.njuTeacher.model.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/login")
    public Response login(@RequestBody String openid,@RequestBody String password){
        // 若count值为1，说明已经注册过
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUserIdEqualTo(openid);
        if(userMapper.countByExample(userExample) == 1)
            return new Response("登录成功");
        else
            return new Response("登录失败");
    }

//    @PostMapping(value = "/register")
//    public Response register(@RequestBody String openid){
//        // 首先判断是否存在，若存在则注册，否则注册失败
//        User user = userMapper.selectByPrimaryKey(openid);
//        if(user == null) {
//            user = new User();
//            user.setUserId(openid);
//            userMapper.insertSelective(user);
//            return new Response("注册成功");
//        }
//        else
//            return new Response("注册失败");
//    }
}
