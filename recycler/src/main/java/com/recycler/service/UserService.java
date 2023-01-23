package com.recycler.service;

import com.recycler.entity.User;

public interface UserService {
	public User verifyUser(User user);
	
	public User addUser(User user);
	
	public Iterable<User> getAllUsers();
	
	public User deleteUser(User user);
}
