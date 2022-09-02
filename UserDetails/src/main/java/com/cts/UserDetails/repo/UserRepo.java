package com.cts.UserDetails.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.UserDetails.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	User findByUserId(String userId);
}