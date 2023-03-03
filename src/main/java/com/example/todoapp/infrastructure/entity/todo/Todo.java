package com.example.todoapp.infrastructure.entity.todo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.todoapp.infrastructure.entity.common.Account;

import lombok.Data;

@Entity
@Data
@Table(name = "todoitems")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID")
	private Long userid;

	@Column(name = "CONTENTS")
	private String contents;

	@Column(name = "DONE")
	private int done;

	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

	@Column(name = "COMPLETED_AT")
	private LocalDate completedAt;

	@Column(name = "DELETED_AT")
	private LocalDate deletedAt;

	@ManyToOne
	@JoinColumn(name = "USER_ID", insertable = false, updatable = false)
	private Account account;
}
