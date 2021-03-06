package com.tt.teach1.service.impl;

import com.tt.teach1.dao.StudentDao;
import com.tt.teach1.pojo.Student;
import com.tt.teach1.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public int updateStudent(Student student) {
        return studentDao.updateStudent(student);
    }

    @Transactional
    public int deleteStudent(Integer stuNo) {
        return studentDao.deleteStudent(stuNo);
    }

    public Student getStuByNo(Integer studentNo) {
        return studentDao.getStuByNo(studentNo);
    }
}
