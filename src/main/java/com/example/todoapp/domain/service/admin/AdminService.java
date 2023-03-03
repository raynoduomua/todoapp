package com.example.todoapp.domain.service.admin;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.todoapp.application.common.utils.LocalDateNow;
import com.example.todoapp.application.form_validation.AdminForm;
import com.example.todoapp.infrastructure.entity.common.Account;
import com.example.todoapp.infrastructure.repository.admin.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminService {

	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * アカウント全件取得
	 * @return アカウント全件
	 */
	public List<Account> findAccountAll() {
		return this.adminRepository.findAll();
	}

	/**
	 * 管理者登録
	 * @param adminForm Formクラス
	 */
	public void saveAdmin(AdminForm adminForm) {

		Account admin = new Account();
		admin.setUsername(adminForm.getUsername());
		admin.setEmail(adminForm.getEmail());
		admin.setPassword(passwordEncoder.encode(adminForm.getPassword()));
		admin.setAge(adminForm.getAge());
		admin.setGender(adminForm.getGender());
		admin.setAdmin(true);
		//		admin.setAuthority(Authority.ADMIN);
		admin.setCreatedAt(LocalDateNow.getLocalDateNow());
		admin.setUpdatedAt(null);
		admin.setDeletedAt(null);

		this.adminRepository.save(admin);
	}

}
