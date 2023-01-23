package com.recycler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recycler.base.Result;
import com.recycler.entity.User;
import com.recycler.entity.UserDetailsImpl;
import com.recycler.service.UserService;
import com.recycler.service.impl.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/add")
	public Result addUser(@RequestBody @Valid User user) {
		return  Result.success(userService.addUser(user));
	}
	
	@PostMapping("/login")
	public Result verifyUser(@RequestBody User user) {
//		authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(user.getEmail(), user.getEmail())
//				);
		return  Result.success(userService.verifyUser(user));
		//UserDetails userDetails = userServiceImpl.loadUserByUsername(user.getEmail());
//		if(user!=null) {
//			return  Result.success(userDetails);
//		}else {
//			return Result.error("no user");
//		}
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
