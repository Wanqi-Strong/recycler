package com.recycler.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.recycler.base.ERole;
import com.recycler.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	List<Role> findAllByName(ERole name);
}
