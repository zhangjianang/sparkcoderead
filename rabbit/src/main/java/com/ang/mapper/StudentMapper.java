package com.ang.mapper;

import com.ang.entity.Student;
import org.springframework.stereotype.Repository;

/**
 * Created by adimn on 2019/8/9.
 */
@Repository
public interface StudentMapper {
    Student Get(int id);
    Integer Ins(Student student);
}
