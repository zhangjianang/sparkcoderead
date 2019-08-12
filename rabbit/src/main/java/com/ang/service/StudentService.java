package com.ang.service;

import com.alibaba.fastjson.JSONObject;
import com.ang.entity.Score;
import com.ang.entity.Student;
import com.ang.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Created by adimn on 2019/8/9.
 */
@Service
public class StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    SendService sendService;

    public Student get(int id) {
        return studentMapper.Get(id);
    }

    public Integer add(Student student) throws IOException, TimeoutException {
        sendService.addInfo(JSONObject.toJSONString(student));
        return studentMapper.Ins(student);
    }

    public List<Map> score() {
        return studentMapper.Score();
    }


}
