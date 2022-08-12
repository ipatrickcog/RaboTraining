package com.cts.UserDetails.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.UserDetails.model.User;
import com.cts.UserDetails.repo.UserRepo;

@Service
@Transactional
public class UserDetailsService {

	@Autowired
	UserRepo userRepo;

	// retrieve the hashed id, unparse it, and return the user details
	public User getUserById(String hid) {
		return userRepo.findByUserId(hid);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll();
	}

}
