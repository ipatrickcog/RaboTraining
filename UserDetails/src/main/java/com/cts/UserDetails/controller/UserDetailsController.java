package com.cts.UserDetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cts.UserDetails.model.User;
import com.cts.UserDetails.service.UserDetailsService;

@RestController
public class UserDetailsController {

	@Autowired
	UserDetailsService uds;

	// endpoint for user details
	@GetMapping(value = "/details/{hashedId}")
	public User getUserDetails(@PathVariable("hashedId") String hashedId) {
		return uds.getUserById(hashedId);
	}
}
