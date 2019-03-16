package com.nju171250.njuTeacher.service.impl;

import com.nju171250.njuTeacher.mapper.CommentMapper;
import com.nju171250.njuTeacher.mapper.CourseMapper;
import com.nju171250.njuTeacher.mapper.TeacherMapper;
import com.nju171250.njuTeacher.model.CommentExample;
import com.nju171250.njuTeacher.model.Teacher;
import com.nju171250.njuTeacher.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    CourseMapper courseMapper;

    @Override
    public void computeTeacherScore(String courseId) {
        String teacherId = courseMapper.selectByPrimaryKey(courseId).getTeacherId();
        List<Integer> list = commentMapper.getCommentStarsByTeacherId(teacherId);
        double total = 0;
        for(int i : list){
            total += i;
        }
        double score = total / (double)list.size() * 2.0;
        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherId);
        teacher.setTeacherScore(score);
        teacherMapper.updateByPrimaryKey(teacher);
    }
}
