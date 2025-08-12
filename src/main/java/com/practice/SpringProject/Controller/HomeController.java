package com.practice.SpringProject.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.practice.SpringProject.Model.Account;
import com.practice.SpringProject.Model.Posts;
import com.practice.SpringProject.Service.AccountService;
import com.practice.SpringProject.Service.PostService;

@Controller
public class HomeController {
	/*Here the autowired annotation  automatically inject dependencies so the PostService object is created
	 * by spring and is given to postServe*/
	@Autowired 
	private PostService postServe;
	
	/*Model is an interface provided by Spring MVC that is used to pass data 
	from the controller to the view (typically a Thymeleaf or JSP page).*/
	@GetMapping("/home")
	public String home(Model model) {
		List<Posts> posts=postServe.getAll();
		model.addAttribute("posts",posts);
		return "home";
	}
	
	/*when the HTTP requests comes in it searches GetMapping methods 
	 * and finds the suitable method and returns the requested page*/
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		Account account=new Account();
		model.addAttribute("account",account);
		return "register";
	}
	
	@Autowired
	private AccountService accServe;
	
	@PostMapping("/register")
	public String register_user(@ModelAttribute Account account,Model model) {
		accServe.save(account);
		List<Posts> posts=postServe.getAll();
		model.addAttribute("posts",posts);
		return "home";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
}
