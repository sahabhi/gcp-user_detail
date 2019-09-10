package com.aavis.userdetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aavis.userdetail.model.LoginModel;
import com.aavis.userdetail.model.Result;
import com.aavis.userdetail.model.User;
import com.aavis.userdetail.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public Long add(@RequestBody User user) {
		return userService.createUser(user);

	}

	@GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public Result<User> getUsers() {
		return userService.getUsers("");
	}
	
	@PostMapping(value = "/user/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public User viewPorfile(@RequestBody LoginModel login) {
		return userService.viewProfile(login);
	}

	@PutMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateUser(@PathVariable Long id, @RequestBody User user) {
		userService.updateUser(user, id);
	}

}
