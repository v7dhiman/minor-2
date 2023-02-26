package com.example.minor.service;

import com.example.minor.models.MyUser;
import com.example.minor.repositories.MyUserCacheRepository;
import com.example.minor.repositories.MyUserRepository;
import com.example.minor.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Value("${user.student.authority}")
    String studentAuthority;

    @Value("${user.admin.authority}")
    String adminAuthority;

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    MyUserCacheRepository myUserCacheRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserCacheRepository.get(username);
        if(myUser == null)
        {
            myUser = myUserRepository.findByUsername(username);
            if(myUser != null)
                myUserCacheRepository.set(myUser);
        }
        return myUser;
    }
    public MyUser create(UserCreateRequest userCreateRequest)
    {
        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null)
            myUserBuilder.authority(studentAuthority);
        else
            myUserBuilder.authority(adminAuthority);

        return myUserRepository.save(myUserBuilder.build());
    }
}
