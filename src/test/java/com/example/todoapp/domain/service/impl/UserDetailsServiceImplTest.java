package com.example.todoapp.domain.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.todoapp.infrastructure.entity.common.Account;
import com.example.todoapp.infrastructure.repository.user.UserRepository;

@SpringBootTest
@Transactional
class UserDetailsServiceImplTest {

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Test
	@DisplayName("ユーザー名が存在するとき、ユーザー詳細を取得することを期待する")
	void whenUsernameExists_expectToGetUserDetails() {
		// Arrange(準備する)
		var user = new Account();
		user.setUsername("test");
		user.setEmail("test@testver.jp");
		user.setPassword("password");
		user.setAge(0);
		user.setGender(1);
		user.setAdmin(false);
		//		user.setAuthority(Authority.USER);
		user.setStatus(0);
		userRepository.save(user);

		// Act(実行する)
		var expected = userDetailsServiceImpl.loadUserByUsername("test@testver.jp");

		// Assert(検証する)
		assertEquals(user.getEmail(), expected.getUsername());

	}

	@Test
	@DisplayName("ユーザー名が存在しないとき、例外をスローする")
	void whenUsernameDoesNoExist_throwException() {
		// Act & Assert
		assertThrows(UsernameNotFoundException.class,
				() -> userDetailsServiceImpl.loadUserByUsername("TESTTEST@TEST.JP"));
	}
}
