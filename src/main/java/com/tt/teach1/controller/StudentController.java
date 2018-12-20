package com.tt.teach1.controller;

import com.tt.teach1.pojo.Student;
import com.tt.teach1.service.StudentService;
import com.tt.teach1.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController extends BaseController {
    @Resource
    private StudentService studentService;

    //请求：http://localhost:8080/stu/login
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login() {
        return "/student/login";
    }

    //请求：http://localhost:8080/stu/index
    @RequestMapping("/index")
    public String index() {
        String studentName = (String) getSession().getAttribute(SESSION_KEY);
        if (studentName!=null){
            return "/student/index";
        }
        return REDIRECT + "/stu/login";
    }
    //请求：http://localhost:8080/stu/student
    @RequestMapping(value = "/student",method = RequestMethod.GET)
    public String student() {
        return "/student/student";
    }

    @RequestMapping("/logout")
    public String logout() {
        getSession().removeAttribute(SESSION_KEY);
        return REDIRECT + "/stu/login";
    }

    //请求：http://localhost:8080/stu/doLogin
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin() {
        String xuehao = getRequest().getParameter("studentNo");
        Integer studentNo = Integer.parseInt(xuehao);
        String loginPwd = getRequest().getParameter("loginPwd");
        Student student = new Student();
        student.setLoginPwd(loginPwd);
        student.setStudentNo(studentNo);
        Student student1 = studentService.doLogin(student);
        if (student1!=null){
            getSession().setAttribute(SESSION_KEY,student1.getStudentName());
            return FORWARD + "/stu/index";
        }
        return REDIRECT + "/stu/login";
    }

    //请求：http://localhost:8080/stu/getStudentList
    @RequestMapping(value = "/getStudentList",method = RequestMethod.GET)
    @ResponseBody
    public Object getStudentList() {
        List<Student> list = studentService.getStudentList();
        return list;
    }

    @RequestMapping(value = "/deleteStudent",method = RequestMethod.GET)
    public String deleteStudent() {
        return "明儿 123见";
    }
}
