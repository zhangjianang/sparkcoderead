package com.ang.service;

import com.ang.entity.Student;
import com.ang.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by adimn on 2019/8/9.
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper  studentMapper;

    public Student get(int id){
        return studentMapper.Get(id);
    }

    public Integer add(Student student){
        return studentMapper.Ins(student);
    }
}
