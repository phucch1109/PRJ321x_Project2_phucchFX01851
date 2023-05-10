package com.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

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

import com.entity.ApplyPost;
import com.entity.Category;
import com.entity.Company;
import com.entity.JobType;
import com.entity.Post;
import com.entity.User;
import com.service.ApplyPostService;
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
@Autowired
ApplyPostService applyPostService;
private Logger logger = Logger.getLogger(getClass().getName());

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
		return "post/postNew";
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
	
	//show post edit form
	@RequestMapping("/editPostForm")
	public String showEditPost(Authentication authentication,Model model,
			PostForm postForm,
			@RequestParam(value="id") int postId) {
		    String username = authentication.getName();
		    User user = userService.findByUserName(username);
		    Company company = user.getCompany();	
			postForm = new PostForm();
			Post post = postService.getPostById(postId);
			postForm.setTitle(post.getTitle());
			postForm.setDescription(post.getDescription());
			postForm.setExperience(post.getExperience());
			postForm.setNumberOfRecruit(post.getNumberOfRecruit()+"");
			SimpleDateFormat sdf = new SimpleDateFormat(
				    "yyyy-MM-dd");
			postForm.setExpireDate(sdf.format(post.getExpireDate())) ;
			postForm.setSalary(post.getSalary());
			postForm.setCategoryId(post.getCategory().getId());
			postForm.setJobTypeId(post.getJobType().getId());
		logger.info(post.getNumberOfRecruit() + "asdasdsd");
		logger.info(postForm.getNumberOfRecruit());
		List<Category> categories =  categoryService.getCategories();
		List<JobType> jobTypes = jobTypeService.getJobTypes();
		model.addAttribute("postForm",postForm);
		model.addAttribute("categories",categories);
		model.addAttribute("jobTypes",jobTypes);
		model.addAttribute("companyAddress",company.getAddress());
		model.addAttribute("id",postId);
		return "post/postEdit";
	}
	
	@PostMapping("/editPost")
	public String editPost(Authentication authentication,Model model,
			@RequestBody @Valid @ModelAttribute(value="postForm") PostForm postForm,
			BindingResult theBindingResult,
			@RequestParam(value = "id") int postId
			) {
		if(theBindingResult.hasErrors()) {
			return showEditPost(authentication,model,postForm,postId);
		}
		String username = authentication.getName();
		User user = userService.findByUserName(username);	
		postService.updatePost(postForm, user,postId);
		model.addAttribute("message","the post has been updated");
		return showPostsList(authentication, model, 1);
	}
	
	@RequestMapping("/viewPost") 
	public String viewPost(Authentication authentication,Model model,
			@RequestParam(value="id") int postId
			) {				
			    Post post = postService.getPostById(postId);
			    List<ApplyPost> applyPosts = applyPostService.getApplyPostsByPostId(postId);
			model.addAttribute("applyPosts",applyPosts);    
			model.addAttribute("post",post);
			return "post/postDetails";
		}
	
	@RequestMapping("/approveApply")
	public String approveApplyProcess(Authentication authentication,Model model,
			@RequestParam(value="id") int applyPostId) {
		ApplyPost applyPost = applyPostService.getApplyPostById(applyPostId);
		applyPostService.approveApplyPost(applyPost);
		model.addAttribute("message","đơn ứng tuyển đã được chấp nhận");
		return viewPost(authentication, model, applyPost.getPost().getId());
	}
	
	@RequestMapping("/refuseApply")
	public String refuseApplyProcess(Authentication authentication,Model model,
			@RequestParam(value="id") int applyPostId) {
		ApplyPost applyPost = applyPostService.getApplyPostById(applyPostId);
		applyPostService.refuseApplyPost(applyPost);
		model.addAttribute("message","đơn ứng tuyển đã bị từ chối");
		return viewPost(authentication, model, applyPost.getPost().getId());
	}
}

