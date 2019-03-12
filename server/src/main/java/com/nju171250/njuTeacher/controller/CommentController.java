package com.nju171250.njuTeacher.controller;

import com.nju171250.njuTeacher.mapper.CommentMapper;
import com.nju171250.njuTeacher.model.Comment;
import com.nju171250.njuTeacher.model.CommentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentMapper commentMapper;

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
