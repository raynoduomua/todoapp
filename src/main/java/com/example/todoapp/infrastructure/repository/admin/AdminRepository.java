package com.example.todoapp.infrastructure.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.infrastructure.entity.common.Account;

public interface AdminRepository extends JpaRepository<Account, Long> {

}
