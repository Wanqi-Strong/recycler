package com.recycler.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.recycler.base.RecyclerException;
import com.recycler.dao.UserRepository;
import com.recycler.entity.User;
import com.recycler.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User verifyUser(User user) {
		User  currentUser= userRepository.findByEmail(user.getEmail());
		if(currentUser == null) {
			throw new RecyclerException("user does not exist");
		}else {
			// compare password
	        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
			if(bcp.matches(user.getPassword(),currentUser.getPassword())) {
				return currentUser;
			}else {
				throw new RecyclerException("incorrect password");
			}
		}
	}

	@Override
	public User addUser(User user) {
		// check if email already used
		String email = user.getEmail();
		User registerUser = userRepository.findByEmail(email);
		if(registerUser != null && registerUser.getStatus() == 1) {
			throw new RecyclerException("email already registered");
		}
		// create id
		String idString = UUID.randomUUID().toString().replaceAll("-", "");
		user.setId(idString);
		// create status
		user.setStatus(1);
		// encode password
        BCryptPasswordEncoder bcp = new BCryptPasswordEncoder();
        user.setPassword(bcp.encode(user.getPassword()));
		userRepository.save(user);
		return userRepository.findByEmail(email);
	}

	@Override
	public Iterable<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User deleteUser(User user) {
		User  currentUser= userRepository.findByEmail(user.getEmail());
		if(currentUser == null) {
			throw new RecyclerException("user does not exist");
		}else {
			// update
			currentUser.setStatus(0);
			userRepository.save(currentUser);
			return userRepository.findByEmail(currentUser.getEmail());
		}
	}
}
