package com.example.minor.request;

import com.example.minor.models.Admin;
import com.example.minor.models.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest
{
    private String username;
    private String password;
    private String authority;
    private Student student;
    private Admin admin;
}
