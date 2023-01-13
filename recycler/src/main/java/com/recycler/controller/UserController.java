package com.recycler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recycler.base.Result;
import com.recycler.entity.User;
import com.recycler.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public Result addUser(@RequestBody @Valid User user) {
		return  Result.success(userService.addUser(user));
	}
	
	@PostMapping("/verify")
	public Result verifyUser(@RequestBody User user) {
		return  Result.success(userService.verifyUser(user));
	}
	
	@PostMapping("/all")
	  public Result<Iterable> getAllUsers() {
		return Result.success(userService.getAllUsers());
	 }
	
	@PostMapping("/delete")
	public Result deleteUser(@RequestBody User user){
		return Result.success(userService.deleteUser(user));
	}
}
