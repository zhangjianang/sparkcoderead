package com.ang.controller;

import com.alibaba.fastjson.JSONObject;
import com.ang.entity.Student;
import com.ang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by adimn on 2019/8/9.
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("get/{id}")
    public String getStudent(@PathVariable int id) {
        return studentService.get(id).toString();
    }

    @RequestMapping("name/{name}/age/{age}/gender/{gender}")
    public Integer addStudent(@PathVariable String name, @PathVariable String gender, @PathVariable Integer age) throws IOException, TimeoutException {

        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        student.setGender(gender);
        return studentService.add(student);
    }

    @RequestMapping("/score")
    public String getScores(){
        return JSONObject.toJSONString(studentService.score());
    }
}
