package com.example.minor.service;

import com.example.minor.models.MyUser;
import com.example.minor.models.Student;
import com.example.minor.repositories.MyUserRepository;
import com.example.minor.repositories.StudentRepository;
import com.example.minor.request.StudentCreateRequest;
import com.example.minor.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StudentService
{
    @Value("${users.student.authority}")
    String studentAuthority;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MyUserDetailsService userDetailsService;
    @Autowired
    private MyUserRepository myUserRepository;

    public void create(StudentCreateRequest studentCreateRequest)
    {
        UserCreateRequest userCreateRequest = studentCreateRequest.toUser();

//        MyUser myUser = MyUser.builder()
//                .authority(studentAuthority)
//                .username(studentCreateRequest.getUsername())
//                .password(studentCreateRequest.getPassword())
//                .build();
        MyUser myUser = userDetailsService.create(userCreateRequest);
        Student student = studentCreateRequest.to();
        student.setMyUser(myUser);
        studentRepository.save(student);
    }

    public Student findStudentByStudentId(int sId)
    {
        return studentRepository.findById(sId).orElse(null);
    }
}
