package com.practice.SpringProject.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.SpringProject.Model.Authority;
import com.practice.SpringProject.Repository.AuthorityRepository;

@Service
public class AuthorityService {
	
	@Autowired
	private AuthorityRepository authoRepo;
	
	public Authority save(Authority authority) {
		return authoRepo.save(authority);
	}
	
	public Optional<Authority> findById(long id){
		return authoRepo.findById(id);
	}
}
