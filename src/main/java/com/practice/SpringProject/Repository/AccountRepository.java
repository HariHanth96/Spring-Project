package com.practice.SpringProject.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.practice.SpringProject.Model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
	
	Optional<Account> findOneByEmailIgnoreCase(String email);
}
