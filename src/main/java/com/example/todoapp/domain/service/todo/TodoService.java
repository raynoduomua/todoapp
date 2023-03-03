package com.example.todoapp.domain.service.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.todoapp.application.common.security.LoginUser;
import com.example.todoapp.application.common.utils.LocalDateNow;
import com.example.todoapp.application.form_validation.TodoForm;
import com.example.todoapp.infrastructure.entity.todo.Todo;
import com.example.todoapp.infrastructure.repository.todo.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

	private final TodoRepository todoRepository;

	/**
	 * Todo全件取得
	 * @return
	 */
	public List<Todo> searchTodoAll() {
		return this.todoRepository.findAll();
	}

	/**
	 * 選択肢
	 * @param id        選択肢Value
	 * @param loginUser ログイン中のユーザー情報
	 * @return          選択肢Valueで検索したtodoitems
	 */
	public List<Todo> findByUseridAndDone(Integer id, LoginUser loginUser) {

		Todo todo = new Todo();

		if (id == null) {
			// ログイン時用
			todo.setDone(1);
		} else {
			// 選択肢処理用
			todo.setDone(id);
		}

		if (todo.getDone() != 3) {
			return this.todoRepository.findByUseridAndDone(loginUser.getUser().getId(), todo.getDone());
		} else {
			return this.todoRepository.findByUseridAndDoneLessThan(loginUser.getUser().getId(), 3);
		}

	}

	/**
	 * Todo新規登録
	 * @param todoForm  Formクラス
	 * @param loginUser ログイン中のユーザー情報
	 */
	public void saveTodo(TodoForm todoForm, LoginUser loginUser) {

		Todo todo = new Todo();
		//		todo.setUserid(loginUser.getUser().getUsername());
		todo.setUserid(loginUser.getUser().getId());
		todo.setContents(todoForm.getContents());
		todo.setDone(1);
		todo.setCreatedAt(LocalDateNow.getLocalDateNow());
		todo.setUpdatedAt(null);
		todo.setCompletedAt(null);
		todo.setDeletedAt(null);

		this.todoRepository.save(todo);
	}

	/**
	 * Todo完了処理
	 * @param id        todoitems.id
	 * @param loginUser ログイン中のユーザー情報
	 */
	public void doneTodo(Integer id, LoginUser loginUser) {

		Optional<Todo> todOptional = this.todoRepository.findById(Integer.toUnsignedLong(id));
		todOptional.ifPresent(todo -> {
			todo.setDone(2);
			todo.setCompletedAt(LocalDateNow.getLocalDateNow());
			this.todoRepository.save(todo);
		});
	}

	/**
	 * Todo1件取得
	 * @param id todoitems.id
	 * @return   Todo1件
	 */
	public Optional<Todo> findById(Integer id) {
		return this.todoRepository.findById(Integer.toUnsignedLong(id));
	}

	/**
	 * Todo更新
	 * @param todoForm Formクラス
	 */
	public void updateTodo(TodoForm todoForm) {

		Optional<Todo> todOptional = this.todoRepository.findById(Integer.toUnsignedLong(todoForm.getId()));
		todOptional.ifPresent(todo -> {
			todo.setContents(todoForm.getContents());
			todo.setUpdatedAt(LocalDateNow.getLocalDateNow());
			this.todoRepository.save(todo);
		});
	}

	/**
	 * Todo削除
	 * @param id Todo.id
	 */
	public void deleteTodo(Integer id) {

		Optional<Todo> todOptional = this.todoRepository.findById(Integer.toUnsignedLong(id));
		todOptional.ifPresent(todo -> {
			todo.setDone(4);
			todo.setDeletedAt(LocalDateNow.getLocalDateNow());
			this.todoRepository.save(todo);
		});
	}

}
