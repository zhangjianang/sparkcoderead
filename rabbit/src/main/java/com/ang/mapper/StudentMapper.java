package com.ang.mapper;

import com.ang.entity.Score;
import com.ang.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by adimn on 2019/8/9.
 */
@Repository
public interface StudentMapper {
    Student Get(int id);
    Integer Ins(Student student);
    List<Map> Score();
}
