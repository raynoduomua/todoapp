package com.example.todoapp.infrastructure.entity.todo;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.todoapp.application.common.utils.LocalDateNow;

class TodoTest {

	private Todo sutTodo;

	@BeforeEach
	void setUp() throws Exception {
		this.sutTodo = new Todo();
	}

	@Test
	public void idがnullを返す() {
		assertEquals(null, sutTodo.getId());
	}

	@Test
	public void idが1を返す() {
		sutTodo.setId((long) 1);
		Long expected = (long) 1;
		assertEquals(expected, sutTodo.getId());
	}

	@Test
	public void useridがnullを返す() {
		assertEquals(null, sutTodo.getUserid());
	}

	@Test
	public void useridが1を返す() {
		sutTodo.setUserid((long) 1);
		Long expected = (long) 1;
		assertEquals(expected, sutTodo.getUserid());
	}

	@Test
	public void contentsがnullを返す() {
		assertEquals(null, sutTodo.getContents());
	}

	@Test
	public void contentsがあいうえおを返す() {
		sutTodo.setContents("あいうえお");
		String expected = "あいうえお";
		assertEquals(expected, sutTodo.getContents());
	}

	@Test
	public void doneが0を返す() {
		assertEquals(0, sutTodo.getDone());
	}

	@Test
	public void doneが1を返す() {
		sutTodo.setDone(1);
		int expected = 1;
		assertEquals(expected, sutTodo.getDone());
	}

	@Test
	public void createdAtがnullを返す() {
		assertNull(sutTodo.getCreatedAt());
	}

	@Test
	public void createdAtが本日日付を返す() {
		sutTodo.setCreatedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutTodo.getCreatedAt());
	}

	@Test
	public void updateAtがnullを返す() {
		assertNull(sutTodo.getUpdatedAt());
	}

	@Test
	public void updateAtが本日日付を返す() {
		sutTodo.setUpdatedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutTodo.getUpdatedAt());
	}

	@Test
	public void completedAtがnullを返す() {
		assertNull(sutTodo.getCompletedAt());
	}

	@Test
	public void completedAtが本日日付を返す() {
		sutTodo.setCompletedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutTodo.getCompletedAt());
	}

	@Test
	public void deleteAtがnullを返す() {
		assertNull(sutTodo.getDeletedAt());
	}

	@Test
	public void deleteAtがpasswordを返す() {
		sutTodo.setDeletedAt(LocalDateNow.getLocalDateNow());
		LocalDate expected = LocalDateNow.getLocalDateNow();
		assertEquals(expected, sutTodo.getDeletedAt());
	}

	@AfterEach
	void tearDown() throws Exception {
		this.sutTodo = null;
	}

}
