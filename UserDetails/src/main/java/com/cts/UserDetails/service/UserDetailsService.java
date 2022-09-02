package com.cts.UserDetails.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.UserDetails.model.User;
import com.cts.UserDetails.repo.UserRepo;

@Service
@Transactional
public class UserDetailsService {

	@Autowired
	UserRepo userRepo;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

	// retrieve the hashed id, unparse it, and return the user details
	public User getUserById(String hid) {
		return userRepo.findByUserId(hid);
	}

	public List<User> getAllUsers() {
		return userRepo.findAll();
	}

	public String addUser(String user) {
		User u = new User();
		JSONObject obj = new JSONObject(user);

		u.setUsername(obj.getString("username"));
		u.setPassword(obj.getString("password"));
		u.setFullname(obj.getString("fullname"));
		u.setGender(obj.getString("gender"));
		u.setEmail(obj.getString("email"));
		u.setAddress(obj.getString("address"));

		try {
			u.setDob(formatter.parse(obj.getString("dob")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		userRepo.save(u);
		return "{}";
	}

	public String editUser(String userDetails) {
		JSONObject obj = new JSONObject(userDetails);
		userRepo.findById((String) obj.optString("id")).map(user -> {
			try {
				// password to be implemented
				user.setUsername(obj.getString("username"));
				user.setFullname(obj.getString("fullname"));
				user.setEmail(obj.getString("email"));
				user.setDob(formatter.parse(obj.getString("dob")));
				user.setGender(obj.getString("gender"));
				user.setAddress(obj.getString("address"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userRepo.save(user);
		});
		return "{}";
	}

	public String deleteUser(String userId) {
		userRepo.deleteById(userId);
		return "{}";
	}

}
