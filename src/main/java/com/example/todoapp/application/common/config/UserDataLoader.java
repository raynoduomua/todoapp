package com.example.todoapp.application.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.todoapp.application.common.utils.LocalDateNow;
import com.example.todoapp.infrastructure.entity.common.Account;
import com.example.todoapp.infrastructure.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataLoader implements ApplicationRunner {

	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Account admin = new Account();
		admin.setUsername("admin");
		admin.setEmail("admin@admin.jp");
		admin.setPassword(passwordEncoder.encode("password"));
		admin.setAge(20);
		admin.setGender(4);
		admin.setAdmin(true);
		//		admin.setAuthority(Authority.ADMIN);
		admin.setCreatedAt(LocalDateNow.getLocalDateNow());
		admin.setUpdatedAt(null);
		admin.setDeletedAt(null);
		admin.setStatus(0);

		Account user = new Account();
		user.setUsername("user");
		user.setEmail("test@test.jp");
		user.setPassword(passwordEncoder.encode("password"));
		user.setAge(20);
		user.setGender(4);
		user.setAdmin(false);
		//		user.setAuthority(Authority.USER);
		user.setCreatedAt(LocalDateNow.getLocalDateNow());
		user.setUpdatedAt(null);
		user.setDeletedAt(null);
		user.setStatus(0);

		if (userRepository.findByUsername(admin.getUsername()).isEmpty()) {
			userRepository.save(admin);
			userRepository.save(user);
		}

	}

}
