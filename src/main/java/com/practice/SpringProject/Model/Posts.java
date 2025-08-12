package com.practice.SpringProject.Model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity //entity maps the class to the table in sql
public class Posts {
	
	@Id //primary key mapping
	@GeneratedValue(strategy=GenerationType.SEQUENCE) //generates an appropriate value automatically
	private long id;
	
	private String title;
	
	@Column(columnDefinition="LONGTEXT") // this tells that the datatype of this column is long text
	private String body;
	
	private LocalDateTime createdAt;
	
	@ManyToOne
	@JoinColumn(name="accountId",referencedColumnName="id",nullable=true)//a foreign key column is created
	private Account account;
	
	public Long getId() {
		return Long.valueOf(id);
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Posts() {
		
	}

	public Posts(String title, String body) {
		this.title = title;
		this.body = body;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
