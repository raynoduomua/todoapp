package com.example.todoapp.infrastructure.entity.common;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.todoapp.infrastructure.entity.todo.Todo;

import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {

	// ユーザーID
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	// ユーザー名
	@Column(name = "USERNAME")
	private String username;

	// ログインID
	@Column(name = "EMAIL")
	private String email;

	// パスワード
	@Column(name = "PASSWORD")
	private String password;

	// 年齢
	@Column(name = "AGE")
	private int age;

	// 性別
	@Column(name = "GENDER")
	private int gender;

	// 管理者か一般ユーザーどうか
	@Column(name = "ADMIN")
	private boolean admin;

	// ステータス
	@Column(name = "STATUS")
	private int status;

	// 登録日
	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	// 更新日
	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

	// 削除日
	@Column(name = "DELETED_AT")
	private LocalDate deletedAt;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Todo> todo;
}
