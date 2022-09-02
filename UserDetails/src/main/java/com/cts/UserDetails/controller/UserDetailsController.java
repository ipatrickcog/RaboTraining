package com.cts.UserDetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.UserDetails.model.User;
import com.cts.UserDetails.service.UserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserDetailsController {

	@Autowired
	UserDetailsService uds;

	// endpoint for user details
	@GetMapping(value = "/details/{hashedId}")
	public User getUserDetails(@PathVariable("hashedId") String hashedId) {
		return uds.getUserById(hashedId);
	}

	// add security layer
	@GetMapping(value = "/details/allUsers")
	public List<User> getAllUsers() {
		return uds.getAllUsers();
	}

	@PostMapping(value = "/details/addUser")
	public String addUser(@RequestBody String userDetails) {
		return uds.addUser(userDetails);
	}

	@PutMapping(value = "/details/editUser")
	public String editUser(@RequestBody String userDetails) {
		System.out.println(userDetails);
		return uds.editUser(userDetails);
	}
	
	@DeleteMapping(value = "/details/deleteUser/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		return uds.deleteUser(userId);
	}

}
