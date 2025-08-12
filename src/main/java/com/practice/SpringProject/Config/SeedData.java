 package com.practice.SpringProject.Config;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
/*CommandLineRunner is a functional interface in Spring Boot used to execute code after 
 * the application has fully started and the Spring ApplicationContext is loaded.

It’s typically used to:
Run startup logic
Initialize data
Test features without an API
Log information*/

import com.practice.SpringProject.Model.*;
import com.practice.SpringProject.Service.*;
import com.practice.SpringProject.util.constants.Privilages;
import com.practice.SpringProject.util.constants.Roles;

/*Component annotation in spring marks a java class as a spring managed bean(ie an object of SeedData
 * can't be created by spring and used if @Component is not mentioned*/
/*Even though we are not using the object of SeedData class anywhere else in our application we need to 
 * declare it as component because CommandLineRunner is an interface Spring Boot automatically runs, 
 * but only for Spring Beans(@Component enables spring to create objects for SeedData class).
 */
@Component
public class SeedData implements CommandLineRunner{

	/*run method Is automatically executed once, right after the Spring Boot application starts.
	It’s used to insert seed/demo data into the database when the app starts
	This is made possible by implementing the CommandLineRunner interface*/
	
	@Autowired
	private PostService postServe;
	
	@Autowired
	private AccountService accServe;
	
	@Autowired
	private AuthorityService authoServe;
	
	@Override
	public void run(String... args) throws Exception {
		
		for(Privilages auth:Privilages.values()) {
			Authority authority=new Authority();
			authority.setId(auth.getId());
			authority.setName(auth.getName());
			authoServe.save(authority);
		}
		
		
		List<Account> accounts=accServe.getAll();
		if(accounts.size()==0) {
		Account a1=new Account("Hari","Hari@gmail.com","password");
		Account a2=new Account("Sam","Sam@gmail.com","password");
		Account a3=new Account("Kam","Kam@gmail.com","password");
		a3.setRole(Roles.ADMIN.getRole());
		Account a4=new Account("Ram","Ram@gmail.com","password");
		a4.setRole(Roles.EDITOR.getRole());
		
		Set<Authority> authorities=new HashSet<>();
		authoServe.findById(Privilages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
		authoServe.findById(Privilages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add);
		a4.setAuthorities(authorities);
		accServe.save(a1);
		accServe.save(a2);
		accServe.save(a3);
		accServe.save(a4);
		
		List<Posts> posts=postServe.getAll(); //all the objects(all table rows) in postService are now stored in posts list
		if(posts.size()==0) {
			Posts p1=new Posts("Built a Full-Stack Library Management System with Spring Boot & MySQL",
					"I developed a full-stack Library Management System using Spring Boot and MySQL to streamline "
					+ "the process of managing books, users, and borrow records in a library. The application"
					+ " supports multiple user roles such as admin, librarian, and student, each with appropriate "
					+ "access controls. It allows administrators to add, update, and remove books and users, while"
					+ " students can browse available books, borrow them, and track their return deadlines. ");
			p1.setAccount(a1);
			postServe.save(p1);
			
			Posts p2=new Posts("Hello","World");
			p2.setAccount(a2);
			postServe.save(p2);
		}
		postServe.printing();
		}
		
	}

}
