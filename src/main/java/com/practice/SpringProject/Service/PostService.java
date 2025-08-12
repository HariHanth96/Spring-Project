package com.practice.SpringProject.Service;

import java.time.LocalDateTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.SpringProject.Model.Posts;
import com.practice.SpringProject.Repository.PostRepository;

/*It tells Spring that this class is a service component that contains business logic.

It's a specialized form of @Component, so it's automatically detected during component scanning and 
registered in the Spring application context.*/

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	public Optional<Posts> getById(long id){
		return postRepo.findById(id);//these are built in methods 
	}
	
	public List<Posts> getAll(){
		return postRepo.findAll();
	}
	
	public void delete(Posts post) {
		postRepo.delete(post);
	}
	
	public Posts save(Posts post) { // this method is used to save all the entries into the table
		if(post.getId()==0) {
			post.setCreatedAt(LocalDateTime.now());
		}
		return postRepo.save(post);
	}
	public void printing() {
		System.out.println(postRepo.findByTitle("Hello").getCreatedAt());
	}
}
