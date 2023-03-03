package com.example.todoapp.domain.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.todoapp.application.common.security.LoginUser;
import com.example.todoapp.infrastructure.repository.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		// ユーザ名を検索します（ユーザが存在しない場合は、例外をスローします）
		var user = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(email + " not found"));

		// ユーザ情報を返します
		return new LoginUser(user);

	}
}
