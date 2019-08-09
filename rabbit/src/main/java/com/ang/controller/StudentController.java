package com.ang.controller;

import com.ang.entity.Student;
import com.ang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by adimn on 2019/8/9.
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping("get/{id}")
    public String GetUser(@PathVariable int id) {
        return studentService.get(id).toString();
    }

    @RequestMapping("name/{name}/age/{age}/gender/{gender}")
    public Integer GetUser(@PathVariable String name, @PathVariable String gender, @PathVariable Integer age) {

        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        student.setGender(gender);
        return studentService.add(student);
    }
}
