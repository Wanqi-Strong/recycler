package com.recycler.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recycler.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User,String > {
	User findByEmail(String email);
	User findByUsername(String name);
}
