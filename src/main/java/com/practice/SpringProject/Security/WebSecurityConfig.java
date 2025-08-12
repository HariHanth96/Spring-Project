package com.practice.SpringProject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled=true,securedEnabled=true)
public class WebSecurityConfig {
	private static final String[] WHITELIST= {
			"/home","/register","/login","/css/**","/images/**"
	};
	
	@Bean
	public static PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		 http
         .authorizeHttpRequests(auth -> auth
             .requestMatchers(WHITELIST).permitAll()
             .anyRequest().authenticated()
         )
         .formLogin(form -> form
             .loginPage("/login") // Custom login page (must create /login page)
             .loginProcessingUrl("/login")
             .usernameParameter("email")
             .passwordParameter("password")
             .defaultSuccessUrl("/home",true)
             .failureUrl("/login?error")
             .permitAll()
         )
         .logout(logout -> logout
        		 .logoutUrl("/logout")
        		 .logoutSuccessUrl("/home")
        		 .permitAll());
	
		 
		return http.build();
	}
}
