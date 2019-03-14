package com.nju171250.njuTeacher.controller;

import com.alibaba.fastjson.JSONObject;
import com.nju171250.njuTeacher.mapper.CourseMapper;
import com.nju171250.njuTeacher.mapper.TeacherMapper;
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

    @GetMapping(value = "/getTeacherInfo")
    public JSONObject getTeacherInfo(String teacherId){
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);

        CourseExample example = new CourseExample();
        CourseExample.Criteria criteria = example.createCriteria();
        criteria.andTeacherIdEqualTo(teacherId);
        List<Course> list = courseMapper.selectByExample(example);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("teacher", teacher);
        jsonObject.put("courses", list);

        return jsonObject;
    }
}
