package com.practice.SpringProject.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.practice.SpringProject.Model.Account;
import com.practice.SpringProject.Repository.AccountRepository;
import com.practice.SpringProject.util.constants.Roles;

@Service
public class AccountService implements UserDetailsService {
	
	@Autowired
	private AccountRepository accRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	public Account save(Account account) {
		if(account.getRole()==null) {
		account.setRole(Roles.USER.getRole());
		}
		account.setPassword(passwordEncoder.encode(account.getPassword())); //encodes the password and saves it in the db
		return accRepo.save(account);
	}
	
	public List<Account> getAll(){
		return accRepo.findAll();
	}

	/*this method authenticates the user details for login page*/
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Account> accounts=accRepo.findOneByEmailIgnoreCase(email);
		if(!accounts.isPresent()) {
			throw new UsernameNotFoundException("Account not found");
		}
		Account account=accounts.get();
		
		List<GrantedAuthority> grantAutho=new ArrayList<>();
		grantAutho.add(new SimpleGrantedAuthority(account.getRole()));
		return new User(account.getEmail(),account.getPassword(),grantAutho);
	}
}
