package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dao.UserDao;
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
	
	
	//demo
	@GetMapping("/")
	public String showHome() {
		
		return "home1";
	}
	
	
	//homepage
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
	
	//showing user's profile and company 
	@RequestMapping("/profile")
	public String showUserProfile(Authentication authentication,Model model) {
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		model.addAttribute(user);
		Company company;
		boolean hasRecruiterRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_RECRUITER"));
		if(hasRecruiterRole) {
			company = user.getCompany();
			model.addAttribute(company);
		}
		return "profile";
				
	}
	
	//handle  edit button on profile form
	@PostMapping("/updateProfile")
	public String updateProfile(Authentication authentication,Model model,
			@ModelAttribute(value="user") User user) {
		userService.update(user);
		return showUserProfile(authentication, model);
	}

	//handle  edit button on company form
	@PostMapping("/updateCompany")
	public String updateCompany(Authentication authentication,Model model,
			@ModelAttribute(value="company") Company company) {
		companyService.update(company);
		return showUserProfile(authentication, model);
	}
	
	//handle search input from homepage
	@RequestMapping("/search")
	public String searchPosts(Model model,
			@RequestParam(value = "type") int type,
			@RequestParam(value = "searchQuery") String queryString) {
		
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










