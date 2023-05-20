package com.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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
	@Autowired
    ServletContext context;
	
	private Logger logger = Logger.getLogger(getClass().getName());
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
		//check avatar
				if(user.getAvatar()!= null) {
					logger.info("avatar != null");
					byte[] encodeBase64 = Base64.getEncoder().encode(user.getAvatar());
				    String base64Encoded = "";
						try {
							logger.info("running");
							base64Encoded = new String(encodeBase64, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							logger.info("something gone wrong!");
						}
				        model.addAttribute("base64Encoded",base64Encoded); 
				}
		model.addAttribute(user);
		Company company;
		
		//add company detail if role = Recruiter
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
		User unModedUser = userService.findByUserName(user.getUserName());
		user.setRoles(unModedUser.getRoles());
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
	
	private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");
	//hanble upload avatar
	@PostMapping(value = "/uploadAvatar")
	public String uploadAvatar(@RequestParam MultipartFile file, Model model, 
			Authentication authentication,HttpSession session
			) throws IOException {
		
		//validation
		if(file.isEmpty()) {
			model.addAttribute("errorMessage","You must choose a file to upload");
			return showUserProfile(authentication, model);
		}
//		if(file.getSize() > 1000000) {
//			model.addAttribute("errorMessage","File is too big");
//			return showUserProfile(authentication, model);
//		}
		String fileContentType = file.getContentType();
		if(!contentTypes.contains(fileContentType)) { 
			model.addAttribute("errorMessage","You must select image file");
			return showUserProfile(authentication, model);
		}
		
		//saving
		byte[] prototypeFile = file.getBytes();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		user.setAvatar(prototypeFile);
		userService.update(user);
		model.addAttribute("message","avatar has been updated");
		return showUserProfile(authentication, model);
	}
	
	@PostMapping(value = "/uploadCV")
	public String uploadCV(@RequestParam MultipartFile file, Model model, 
			Authentication authentication,HttpSession session
			) throws IOException {
		
		//validation
		if(file.isEmpty()) {
			model.addAttribute("errorMessage","You must choose a file to upload");
			return showUserProfile(authentication, model);
		}
		String fileContentType = file.getContentType();
		
		if(!fileContentType.equals("application/pdf")) { 
			model.addAttribute("errorMessage","You must select pdf file");
			return showUserProfile(authentication, model);
		}
		//saving
		byte[] prototypeFile = file.getBytes();
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		user.setCvFile(prototypeFile);
		userService.update(user);
		model.addAttribute("message","cv has been updated");
		return showUserProfile(authentication, model);
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










