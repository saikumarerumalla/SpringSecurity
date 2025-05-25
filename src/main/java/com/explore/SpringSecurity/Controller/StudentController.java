package com.explore.SpringSecurity.Controller;

import com.explore.SpringSecurity.Entity.Student;
import com.explore.SpringSecurity.Service.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    public StudentService service;

    @GetMapping("/students")
    public List<Student> getStudents(){
        return  service.getStudents();
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        return  service.addStudent(student);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

}
