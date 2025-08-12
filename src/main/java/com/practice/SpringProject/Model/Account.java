package com.practice.SpringProject.Model;

import java.util.*;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	private String username;
	
	private String email;
	
	private String password; //the value of all the rows in password is "password"(in case i forgot)
	
	private String role;
    
	@OneToMany(mappedBy="account") //one account can have multiple posts
	private List<Posts> posts;
	
	@ManyToMany
	@JoinTable(name="account_authority",joinColumns= {@JoinColumn(name="account_id",referencedColumnName="id")},
										inverseJoinColumns= {@JoinColumn(name="authority_id",referencedColumnName="id")})
	private Set<Authority> authorities=new HashSet<Authority>();
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public List<Posts> getPosts() {
		return posts;
	}

	public void setPosts(List<Posts> posts) {
		this.posts = posts;
	}

	public Account(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}
	public Account() {
		
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<Authority> authorities) {
		this.authorities = authorities;
	}
	
}
