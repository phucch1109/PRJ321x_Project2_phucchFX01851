package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.entity.Company;
import com.entity.Post;
import com.entity.User;
import com.service.CategoryService;
import com.service.CompanyService;
import com.service.PostService;
import com.service.UserService;
import com.subEntity.TopCategoryResult;

@Controller
public class HomeController {
	@Autowired
	CompanyService companyService;
	@Autowired
	PostService postService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	UserService userService;
	
	
	@GetMapping("/")
	public String showHome() {
		
		return "home1";
	}
	
	@GetMapping("/homepage")
	public String homePage(Model model) {
		List<Company> companiesList = companyService.top5companies();
		List<Post> postList = postService.top5Post();
		List<TopCategoryResult> categoryList = categoryService.getTop4Categories();
		model.addAttribute("companies", companiesList);
		model.addAttribute("posts" , postList);		
		model.addAttribute("categories", categoryList);
		return "home";
	}
	
	
	@PostMapping("/profile")
	public String showUserProfile(Authentication authentication,Model model) {
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		model.addAttribute(user);
		return "profile";
				
	}
	
	// add request mapping for /leaders

	@GetMapping("/leaders")
	public String showLeaders() {
		
		return "leaders";
	}
	
	// add request mapping for /systems
	
	@GetMapping("/systems")
	public String showSystems() {
		
		return "systems";
	}
	
	
	
}










