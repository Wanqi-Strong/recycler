package com.recycler.entity;

import java.util.Collection;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.persistence.Id;

@Entity
@Table(name="user")
@JsonIgnoreProperties(allowSetters = true, value = {"password","status"})
@DynamicUpdate
public class User implements UserDetails {

  @Id
  private String id;

  @NotBlank(message = "name cannot be null")
  @Size(min=1, max=16,message = "name length should between 1~16")
  private String username;

  @NotBlank(message = "email cannot be null")
  @Email(message = "please use correct email format")
  private String email;
  
  @NotBlank(message = "password cannot be null")
  @Size(min=6, max=18,message = "password length should between 6~18")
  private String password;
  
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

  @Override
  public String toString() {
	return "User [id=" + id + ", name=" + username + ", email=" + email + ", password=" + password + ", status=" + status
			+ "]";
  }


@Override
public Collection<? extends GrantedAuthority> getAuthorities() {
	// TODO Auto-generated method stub
	return null;
}


@Override
public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return false;
}


@Override
public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return false;
}


@Override
public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return false;
}


	@Override
	public boolean isEnabled() {
	return status == 1;
	}
}
