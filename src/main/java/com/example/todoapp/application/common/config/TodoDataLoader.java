package com.example.todoapp.application.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.example.todoapp.application.common.utils.LocalDateNow;
import com.example.todoapp.infrastructure.entity.todo.Todo;
import com.example.todoapp.infrastructure.repository.todo.TodoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TodoDataLoader implements ApplicationRunner {

	private final TodoRepository todoRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Todo admintodo1 = new Todo();
		admintodo1.setUserid(Integer.toUnsignedLong(1));
		admintodo1.setContents("買い物");
		admintodo1.setDone(1);
		admintodo1.setCreatedAt(LocalDateNow.getLocalDateNow());
		admintodo1.setUpdatedAt(null);
		admintodo1.setCompletedAt(null);
		admintodo1.setDeletedAt(null);

		Todo admintodo2 = new Todo();
		admintodo2.setUserid(Integer.toUnsignedLong(1));
		admintodo2.setContents("支払い");
		admintodo2.setDone(2);
		admintodo2.setCreatedAt(LocalDateNow.getLocalDateNow());
		admintodo2.setUpdatedAt(null);
		admintodo2.setCompletedAt(null);
		admintodo2.setDeletedAt(null);

		Todo usertodo1 = new Todo();
		usertodo1.setUserid(Integer.toUnsignedLong(2));
		usertodo1.setContents("買い物");
		usertodo1.setDone(1);
		usertodo1.setCreatedAt(LocalDateNow.getLocalDateNow());
		usertodo1.setUpdatedAt(null);
		usertodo1.setCompletedAt(null);
		usertodo1.setDeletedAt(null);

		Todo usertodo2 = new Todo();
		usertodo2.setUserid(Integer.toUnsignedLong(2));
		usertodo2.setContents("支払い");
		usertodo2.setDone(2);
		usertodo2.setCreatedAt(LocalDateNow.getLocalDateNow());
		usertodo2.setUpdatedAt(null);
		usertodo2.setCompletedAt(null);
		usertodo2.setDeletedAt(null);

		//		todoRepository.save(admintodo1);
		//		todoRepository.save(admintodo2);
		//		todoRepository.save(usertodo1);
		//		todoRepository.save(usertodo2);
	}

}
