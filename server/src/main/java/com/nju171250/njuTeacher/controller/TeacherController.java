package com.nju171250.njuTeacher.controller;

import com.nju171250.njuTeacher.mapper.TeacherMapper;
import com.nju171250.njuTeacher.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeacherController {
    @Autowired
    TeacherMapper teacherMapper;

    @GetMapping(value = "/getTeacherInfo")
    public Teacher getTeacherInfo(String teacherId){
        return teacherMapper.selectByPrimaryKey(teacherId);
    }
}
