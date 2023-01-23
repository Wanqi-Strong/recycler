package com.recycler.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name="user")
@JsonIgnoreProperties(allowSetters = true, value = {"password","status"})
@DynamicUpdate
public class User {

  @Id
  private String id;

  @NotBlank(message = "name cannot be null")
  @Size(min=1, max=16,message = "name length should between 1~16")
  private String username;

  @NotBlank(message = "email cannot be null")
  @Email(message = "please use correct email format")
  private String email;
  
  @NotBlank(message = "password cannot be null")
//  @Size(min=6, max=18,message = "password length should between 6~18")
  private String password;
  
  @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  @JoinTable(name = "user_role", 
  			joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
  private Set<Role> roles;
  
  @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "user")
  private List<Record> records;
  
  private int status;
  
  public User() {}
  

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }
  

  public String getUsername() {
	return username;
  }

  public void setUsername(String username) {
	this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
	return password;
  }

  public void setPassword(String password) {
	this.password = password;
  }

  public int getStatus() {
	return status;
  }

  public void setStatus(int status) {
	this.status = status;
  }
  
  public Set<Role> getRoles() {
	return roles;
  }

  public void setRoles(Set<Role> roles) {
	this.roles = roles;
  }

  public List<Record> getRecords() {
	return records;
}

public void setRecords(List<Record> records) {
	this.records = records;
}


@Override
  public String toString() {
	return "User [id=" + id + ", name=" + username + ", email=" + email + ", password=" + password + ", status=" + status
			+ "]";
  }
}
