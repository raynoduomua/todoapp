package com.example.todoapp.infrastructure.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.infrastructure.entity.common.Account;

public interface UserRepository extends JpaRepository<Account, Long> {

	Optional<Account> findByUsername(String username);

	Optional<Account> findByEmail(String email);

}
