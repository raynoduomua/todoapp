package com.example.todoapp.infrastructure.repository.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.infrastructure.entity.todo.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	List<Todo> findByUseridAndDone(Long userid, int done);

	List<Todo> findByUseridAndDoneLessThan(Long userid, int done);

}
