package com.nju171250.njuTeacher.controller;

import com.alibaba.fastjson.JSONObject;
import com.nju171250.njuTeacher.mapper.CommentMapper;
import com.nju171250.njuTeacher.mapper.CourseMapper;
import com.nju171250.njuTeacher.mapper.TeacherMapper;
import com.nju171250.njuTeacher.model.CommentExample;
import com.nju171250.njuTeacher.model.Course;
import com.nju171250.njuTeacher.model.CourseExample;
import com.nju171250.njuTeacher.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CommentMapper commentMapper;

    @GetMapping(value = "/getTeacherInfo")
    public JSONObject getTeacherInfo(String teacherId, String openid){
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);

        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId);
        List<Course> list = courseMapper.selectByExample(example);

        if(openid != null) {
            for (Course course : list) {// 设置用户是否已对该课程进行评价
                CommentExample commentExample = new CommentExample();
                CommentExample.Criteria criteria1 = commentExample.createCriteria();
                criteria1.andUserIdEqualTo(openid);
                criteria1.andCourseIdEqualTo(course.getCourseId());
                if(commentMapper.countByExample(commentExample) == 0){// 若返回0，则说明用户对该课程未评价，否则已评价
                    course.setCommented(false);
                }
                else
                    course.setCommented(true);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacher", teacher);
        jsonObject.put("courses", list);

        return jsonObject;
    }
}
