package com.tt.teach1.service;

import com.tt.teach1.pojo.Student;

import java.util.List;

public interface StudentService {

    Student doLogin(Student student);

    List<Student> getStudentList();

    int updateStudent(Student student);

    int deleteStudent(Integer stuNo);
}
