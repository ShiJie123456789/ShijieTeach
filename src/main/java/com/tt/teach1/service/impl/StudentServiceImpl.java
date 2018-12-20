package com.tt.teach1.service.impl;

import com.tt.teach1.dao.StudentDao;
import com.tt.teach1.pojo.Student;
import com.tt.teach1.service.StudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentDao studentDao;

    public Student doLogin(Student student) {
        return studentDao.doLogin(student);
    }

    public List<Student> getStudentList() {
        return studentDao.getStudentList();
    }
}
