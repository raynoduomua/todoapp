package com.example.todoapp.infrastructure.entity.common;

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
		assertEquals(null, sutAccount.getId());
	}

	@Test
	public void idが1を返す() {
		sutAccount.setId((long) 1);
		Long expected = (long) 1;
		assertEquals(expected, sutAccount.getId());
	}

	@Test
	public void usernameがnullを返す() {
		assertEquals(null, sutAccount.getUsername());
	}

	@Test
	public void usernameがadminを返す() {
		sutAccount.setUsername("admin");
		String expected = "admin";
		assertEquals(expected, sutAccount.getUsername());
	}

	@Test
	public void emailがnullを返す() {
		assertEquals(null, sutAccount.getEmail());
	}

	@Test
	@DisplayName("emailがadmin@admin.jpを返す")
	public void returnEmailisNotNull() {
		sutAccount.setEmail("admin@admin.jp");
		String expected = "admin@admin.jp";
		assertEquals(expected, sutAccount.getEmail());
	}

	@Test
	public void passwordがnullを返す() {
		assertEquals(null, sutAccount.getPassword());
	}

	@Test
	public void passwordがpasswordを返す() {
		sutAccount.setPassword("password");
		String expected = "password";
		assertEquals(expected, sutAccount.getPassword());
	}

	@Test
	public void ageがZeroを返す() {
		assertEquals(0, sutAccount.getAge());
	}

	@Test
	public void ageが20を返す() {
		sutAccount.setAge(20);
		int expected = 20;
		assertEquals(expected, sutAccount.getAge());
	}

	@Test
	public void genderがZeroを返す() {
		assertEquals(0, sutAccount.getGender());
	}

	@Test
	public void genderが1を返す() {
		sutAccount.setGender(1);
		int expected = 1;
		assertEquals(expected, sutAccount.getGender());
	}

	@Test
	public void adminがtrueを返す() {
		sutAccount.setAdmin(true);
		assertTrue(sutAccount.isAdmin());
	}

	@Test
	public void adminがfalseを返す() {
		sutAccount.setAdmin(false);
		assertFalse(sutAccount.isAdmin());
	}

	@Test
	public void statusがZeroを返す() {
		assertEquals(0, sutAccount.getStatus());
	}

	@Test
	public void statusが1を返す() {
		sutAccount.setStatus(1);
		int expected = 1;
		assertEquals(expected, sutAccount.getStatus());
	}

	@Test
	public void createdAtがnullを返す() {
		assertNull(sutAccount.getCreatedAt());
	}

	@Test
	public void createdAtが本日日付を返す() {
		sutAccount.setCreatedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutAccount.getCreatedAt());
	}

	@Test
	public void updateAtがnullを返す() {
		assertNull(sutAccount.getUpdatedAt());
	}

	@Test
	public void updateAtが本日日付を返す() {
		sutAccount.setUpdatedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutAccount.getUpdatedAt());
	}

	@Test
	public void deleteAtがnullを返す() {
		assertNull(sutAccount.getDeletedAt());
	}

	@Test
	public void deleteAtがpasswordを返す() {
		sutAccount.setDeletedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutAccount.getDeletedAt());
	}

	@AfterEach
	public void tearDown() {
		// インスタンスの解放を必ず行います。
		this.sutAccount = null;
	}

}
