package com.wb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wb.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);

	User findByUserName(String userName);

	User findByPhone(String phone);

}
