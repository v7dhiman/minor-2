package com.example.minor;

import com.example.minor.models.Admin;
import com.example.minor.models.MyUser;
import com.example.minor.repositories.AdminRepository;
import com.example.minor.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MinorApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(MinorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MyUser myUser = MyUser.builder()
				.username("Raj")
				.password(passwordEncoder.encode("raj123"))
				.authority("adm")
				.build();
		myUser = myUserRepository.save(myUser);

		Admin admin = Admin.builder()
				.age(40)
				.name("Raj Shukla")
				.myUser(myUser)
				.build();
		adminRepository.save(admin);
	}
}
