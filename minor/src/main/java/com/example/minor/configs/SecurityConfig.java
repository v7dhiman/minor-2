package com.example.minor.configs;

import com.example.minor.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    //authentication + authorization + Password Encoder
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Value("${user.admin.authority}")
    String adminAuthority;

    @Value("${user.student.authority}")
    String studentAuthority;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/student for admin/**").hasAuthority(adminAuthority)
                .antMatchers(HttpMethod.POST, "/student").permitAll()
                .antMatchers("/student/**", "/transaction/**").hasAuthority(studentAuthority)
                .antMatchers("/book/search/**").hasAnyAuthority(studentAuthority, adminAuthority)
                .antMatchers("/book/**").hasAuthority(adminAuthority)
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();

    }
}
