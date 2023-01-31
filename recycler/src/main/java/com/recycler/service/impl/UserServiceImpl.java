package com.recycler.service.impl;

import java.util.HashSet;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recycler.base.ERole;
import com.recycler.base.RecyclerException;
import com.recycler.dao.RoleRepository;
import com.recycler.dao.UserRepository;
import com.recycler.entity.Role;
import com.recycler.entity.User;
import com.recycler.entity.UserDetailsImpl;
import com.recycler.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService,UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User verifyUser(User user) {
		User  currentUser= userRepository.findByEmail(user.getEmail());
		if(currentUser == null) {
			throw new RecyclerException("user does not exist");
		}else {
			// compare password
			if(passwordEncoder.matches(user.getPassword(),currentUser.getPassword())) {
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
		// set status
		user.setStatus(1);
		// encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // set role
        user.setRoles(new HashSet<>());
        Role defaultRole = roleRepository.findAllByName(ERole.ROLE_USER).get(0);
        user.getRoles().add(defaultRole);
		userRepository.save(user);
		return userRepository.findByEmail(email);
	}

	@Override
	public Iterable<User> getAllUsers() {
		System.out.println("getAllUsers");
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User deleteUser(User user) {
		User  currentUser= userRepository.findByEmail(user.getEmail());
		if(currentUser == null) {
			throw new RecyclerException("user does not exist");
		}else {
			// update
			currentUser.setStatus(0);
			currentUser.getRoles().clear();
			userRepository.save(currentUser);
			return userRepository.findByEmail(currentUser.getEmail());
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		System.out.println("---loadUserByUsername---");
		User currentUser = userRepository.findByEmail(username);
		if(currentUser == null) {
			throw new RecyclerException("user does not exist");
		}else {
			return UserDetailsImpl.build(currentUser);	
		}
	}
}
