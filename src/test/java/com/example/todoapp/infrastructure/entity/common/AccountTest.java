package com.example.todoapp.infrastructure.entity.common;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.todoapp.application.common.utils.LocalDateNow;

class AccountTest extends Account {

	private Account sutAccount;

	@BeforeEach
	public void setUp() {
		this.sutAccount = new Account();
	}

	@Test
	public void idがnullを返す() {
		assertThat(sutAccount.getId()).isNull();
	}

	@Test
	public void idが1を返す() {
		sutAccount.setId((long) 1);
		Long expected = (long) 1;
		assertThat(sutAccount.getId()).isEqualTo(expected);
	}

	@Test
	public void usernameがnullを返す() {
		assertThat(sutAccount.getUsername()).isNull();
	}

	@Test
	public void usernameがadminを返す() {
		sutAccount.setUsername("admin");
		String expected = "admin";
		assertThat(sutAccount.getUsername()).isEqualTo(expected);
	}

	@Test
	public void emailがnullを返す() {
		assertThat(sutAccount.getEmail()).isNull();
	}

	@Test
	@DisplayName("emailがadmin@admin.jpを返す")
	public void returnEmailisNotNull() {
		sutAccount.setEmail("admin@admin.jp");
		String expected = "admin@admin.jp";
		assertThat(sutAccount.getEmail()).isEqualTo(expected);
	}

	@Test
	public void passwordがnullを返す() {
		assertThat(sutAccount.getPassword()).isNull();
	}

	@Test
	public void passwordがpasswordを返す() {
		sutAccount.setPassword("password");
		String expected = "password";
		assertThat(sutAccount.getPassword()).isEqualTo(expected);
	}

	@Test
	public void ageがZeroを返す() {
		assertThat(sutAccount.getAge()).isZero();
	}

	@Test
	public void ageが20を返す() {
		sutAccount.setAge(20);
		int expected = 20;
		assertThat(sutAccount.getAge()).isEqualTo(expected);
	}

	@Test
	public void genderがZeroを返す() {
		assertThat(sutAccount.getGender()).isZero();
	}

	@Test
	public void genderが1を返す() {
		sutAccount.setGender(1);
		int expected = 1;
		assertThat(sutAccount.getGender()).isEqualTo(expected);
	}

	@Test
	public void adminがtrueを返す() {
		sutAccount.setAdmin(true);
		assertThat(sutAccount.isAdmin()).isTrue();
	}

	@Test
	public void adminがfalseを返す() {
		sutAccount.setAdmin(false);
		assertThat(sutAccount.isAdmin()).isFalse();
	}

	@Test
	public void statusがZeroを返す() {
		assertThat(sutAccount.getStatus()).isZero();
	}

	@Test
	public void statusが1を返す() {
		sutAccount.setStatus(1);
		int expected = 1;
		assertThat(sutAccount.getStatus()).isEqualTo(expected);
	}

	@Test
	public void createdAtがnullを返す() {
		assertThat(sutAccount.getCreatedAt()).isNull();
	}

	@Test
	public void createdAtがpasswordを返す() {
		sutAccount.setCreatedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(sutAccount.getCreatedAt(), expected);
	}

	@AfterEach
	public void tearDown() {
		// インスタンスの解放を必ず行います。
		this.sutAccount = null;
	}

}
