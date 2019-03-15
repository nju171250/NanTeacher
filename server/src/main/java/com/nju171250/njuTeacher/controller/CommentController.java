package com.nju171250.njuTeacher.controller;

import com.alibaba.fastjson.JSONObject;
import com.nju171250.njuTeacher.mapper.CommentMapper;
import com.nju171250.njuTeacher.model.Comment;
import com.nju171250.njuTeacher.model.CommentExample;
import com.nju171250.njuTeacher.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    UUIDUtils uuidUtils;

    @PostMapping(value = "/makeComment")
    public void makeComment(@RequestBody JSONObject jsonObject){
        Comment comment = new Comment();
        comment.setCommentId(uuidUtils.getUUID32());
        comment.setCommentContent(jsonObject.getString("content"));
        comment.setCommentStars(jsonObject.getInteger("starNum"));
        comment.setUserId(jsonObject.getString("openid"));
        comment.setCommentTime(new Date());
        comment.setCourseId(jsonObject.getString("courseId"));
        comment.setLikeNum(0);
        commentMapper.insert(comment);
    }

    @GetMapping(value = "/getCommentInfo")
    public List<Comment> getCommentInfo(String teacherId, String courseId){
        // 若courseId为空，则返回老师所有的评价，否则，返回courseId相关的评价
        if(courseId == null || courseId.equals("")){
            return commentMapper.getCommentInfoByTeacherId(teacherId);
        }
        else
            return commentMapper.getCommentInfoByCourseId(courseId);
    }
}
