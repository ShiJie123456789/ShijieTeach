package com.tt.teach1.controller;

import com.tt.teach1.pojo.Student;
import com.tt.teach1.service.StudentService;
import com.tt.teach1.utils.BaseController;
import com.tt.teach1.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    @ResponseBody     //返回json数据使用@ResponseBody    返回页面不用写
    public Object getStudentList() {
        List<Student> list = studentService.getStudentList();
        return list;
    }

    //删除
    @RequestMapping(value = "/deleteStudent/{stuNo}",method = RequestMethod.DELETE)
    @ResponseBody
    public Object deleteStudent(@PathVariable Integer stuNo) {
        int result = studentService.deleteStudent(stuNo);
        if (result>0){
            return JsonResult.ok("删除成功",result);
        }
        return JsonResult.ok("删除失败",result);
    }

    //修改
    @RequestMapping(value = "/updateStudent",method = RequestMethod.POST)
    public String updateStudent() {
        //学号，姓名，电话，密码
        String xuehao = getRequest().getParameter("stuNo");
        Integer stuNo = Integer.parseInt(xuehao);
        String stuName = getRequest().getParameter("stuName");
        String stuPwd = getRequest().getParameter("stuPwd");
        String stuPhone = getRequest().getParameter("stuPhone");
        Student student = new Student();
        student.setStudentNo(stuNo);
        student.setLoginPwd(stuPwd);
        student.setStudentName(stuName);
        student.setPhone(stuPhone);
        int result = studentService.updateStudent(student);
        if (result>0){
            return "/student/student";
        }
        return FORWARD+"/stu/student";
    }

    @RequestMapping(value = "/getStuByNo/{studentNo}",method = RequestMethod.GET)
    @ResponseBody
    public Object getStuByNo(@PathVariable Integer studentNo) {
        Student student = studentService.getStuByNo(studentNo);
        if (student!=null){
            return JsonResult.ok("有该学生",student);
        }
        return JsonResult.ok("没有该学生",student);
    }
}
