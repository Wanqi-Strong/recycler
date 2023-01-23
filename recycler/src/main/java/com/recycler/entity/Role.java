package com.recycler.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.recycler.base.ERole;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="role")
public class Role {
	
	@Id
	private int id;
	
	@Enumerated(EnumType.STRING)
	private ERole name;
	
	@JsonBackReference
    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> users;
	
	public Role(ERole name) {
		this.name = name;
	}

	public Role() {
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public Role(ERole name,int id) {
		this.id = id;
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
