package com.example.minor.controllers;

import com.example.minor.models.MyUser;
import com.example.minor.models.Student;
import com.example.minor.request.BookCreateRequest;
import com.example.minor.request.StudentCreateRequest;
import com.example.minor.service.BookService;
import com.example.minor.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class StudentController
{
    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest)
    {
        studentService.create(studentCreateRequest);
    }
    // Only for admins
    @GetMapping("/student")
    public Student getStudent() throws Exception {
        //check the person accessing the API is an admin or not
        // /student/**
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        myUser.getStudent().getId();
        if(myUser.getStudent() == null)
            throw new Exception("User requesting the details is not a student");
        int studentID = myUser.getStudent().getId();
        return studentService.findStudentByStudentId(studentID);
    }
    @GetMapping("/student for admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        //check the person accessing the API is an admin or not
        // /student/**
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(myUser.getAdmin() == null)
        {
            throw new Exception("User requesting the details is not an admin");
        }
        return studentService.findStudentByStudentId(studentId);
    }
}
