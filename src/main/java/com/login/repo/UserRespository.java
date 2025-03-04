package com.login.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.login.model.User;

public interface UserRespository extends JpaRepository<User, Long> {
	Optional<User> findByEmailId(String emailId);
	Optional<User> findByUserName(String userName);
	Optional<User> findByUserId(Long userId);

}
