package com.nju171250.njuTeacher.controller;

import com.nju171250.njuTeacher.mapper.TeacherMapper;
import com.nju171250.njuTeacher.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {
    @Autowired
    TeacherMapper teacherMapper;

    @GetMapping(value = "/search")
    public List<Teacher> searchTeacher(String input){
        List<Teacher> list = teacherMapper.searchTeacher("%"+input+"%");
        List<String> alreadyHave = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){// 由于搜索join了course所以会出现重复
            Teacher temp = list.get(i);
            if(alreadyHave.contains(temp.getTeacherId())){
                list.remove(i);
                i--;
            }
            else
                alreadyHave.add(temp.getTeacherId());
        }
        return list;
    }

    @GetMapping(value = "/searchPreloading")
    public List<String> searchPreloading(String input){
        return teacherMapper.searchPreloading(input);
    }
}
