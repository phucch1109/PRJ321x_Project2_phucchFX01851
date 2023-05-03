package com.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Category;
import com.entity.Company;
import com.entity.JobType;
import com.entity.Post;
import com.entity.User;
import com.service.CategoryService;
import com.service.CompanyService;
import com.service.JobTypeService;
import com.service.PostService;
import com.service.UserService;
import com.subEntity.PostForm;

@Controller
public class PostController {
@Autowired
CompanyService companyService;
@Autowired
PostService postService;
@Autowired
CategoryService categoryService;
@Autowired
UserService userService;
@Autowired
JobTypeService jobTypeService;


    //showing user's posts
	@RequestMapping("/postList")
	public String showPostsList(Authentication authentication,Model model,
			@RequestParam(value="page",defaultValue = "1") int page) {
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		List<Post> posts = postService.getPostByUserId(user.getId(),page);
		int countPost = postService.getCountByUserId(user.getId());
		model.addAttribute("posts",posts);
		model.addAttribute("maxPage",(countPost-1)/5+1);
		model.addAttribute("page",page);
		return "post/postList";
	}
	
	 //delete user's post
	@RequestMapping("/deletePost")
	public String deletePost(Authentication authentication,Model model,
				@RequestParam(value="id") int id) {
		int result = postService.deletePost(id);
		if(result == 1)model.addAttribute("message","the post has been deleted");
		return showPostsList(authentication, model, 1);
	}
	//show new post form
	@RequestMapping("/newPostForm")
	public String showNewPost(Authentication authentication,Model model,PostForm postForm) {
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		Company company = user.getCompany();
		if( postForm == null) postForm= new PostForm();
		List<Category> categories =  categoryService.getCategories();
		List<JobType> jobTypes = jobTypeService.getJobTypes();
		model.addAttribute("postForm",postForm);
		model.addAttribute("categories",categories);
		model.addAttribute("jobTypes",jobTypes);
		model.addAttribute("companyAddress",company.getAddress());
		return "post/postDetails";
	}
	//add new post process
	@PostMapping("/newPost")
	public String addNewPost(Authentication authentication,Model model,
			@RequestBody @Valid @ModelAttribute(value="postForm") PostForm postForm,
			BindingResult theBindingResult
			) {
		if(theBindingResult.hasErrors()) {
			return showNewPost(authentication,model,postForm);
		}
		String username = authentication.getName();
		User user = userService.findByUserName(username);	
		int result = postService.addPost(postForm, user);
		if(result==0) {
			model.addAttribute("errorMessage","something gone wrong");
			return showNewPost(authentication,model,postForm);
		}
		model.addAttribute("message","new post has been added");
		return showPostsList(authentication, model, 1);
	}
	
}
