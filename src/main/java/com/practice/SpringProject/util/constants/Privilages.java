package com.practice.SpringProject.util.constants;

public enum Privilages {
	
	RESET_ANY_USER_PASSWORD(1,"RESET_ANY_USER_PASSWORD"),
	ACCESS_ADMIN_PANEL(2,"ACCESS_ADMIN_PANEL");
	
	private long id;
	private String name;
	
	private Privilages(long id,String name) {
		this.id=id;
		this.name=name;
	}
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	}
