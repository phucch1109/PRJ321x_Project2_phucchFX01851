package com.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;
import javax.validation.metadata.MethodType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	//handle edit form
	@PostMapping(value = "/editPost")
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
		model.addAttribute("message","bài viết " + postForm.getTitle() +" đã được cập nhật");
		return showPostsList(authentication, model, 1);
	}
	
	//handle post detail
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
	
	
	//handle search input from homepage
		@RequestMapping("/search")
		public String searchPosts(Model model,
				@RequestParam(value = "type") int type,
				@RequestParam(value = "searchQuery") String queryString,
				@RequestParam(value="page",defaultValue = "1") int page,
				Authentication authentication) {
			List<Post> posts;
			if(type == 0) posts  = postService.getPostsByCategory(queryString, page);
			else if(type == 1)posts  = postService.getPostsByCompanyName(queryString, page);
			else posts= postService.getPostsByAddress(queryString, page);
			int countPost = postService.getCountOfLastSearchQuery();
			model.addAttribute("searchQuery",queryString);
			model.addAttribute("posts",posts);
			model.addAttribute("maxPage",(countPost-1)/5+1);
			model.addAttribute("page",page);
			model.addAttribute("type",type);
			String username = authentication.getName();
			model.addAttribute("username",username);
			return "post/postSearchResult";
		}
	
	// handle employee apply job 
	@PostMapping(value = "/applyPost")
	public String applyPost(@RequestParam MultipartFile file,
			@RequestParam(value="description") String description,
			@RequestParam(value="cvSubmitType") int cvSubmitType,
			@RequestParam int postId,
			Model model,
			@RequestParam(value = "type") int type,
			@RequestParam(value = "searchQuery") String queryString,
			@RequestParam(value="page",defaultValue = "1") int page,
			Authentication authentication
			) {
		byte[] inputFile = null;
		String username = authentication.getName();
		User user = userService.findByUserName(username);
		Post post = postService.getPostById(postId);
		//set file
		if(cvSubmitType == 1) {
			if(user.getCvFile() == null) {
				model.addAttribute("errorMessage","Bạn phải cập nhập CV trước");
				return searchPosts(model, type, queryString, page, authentication);
			}
			inputFile = user.getCvFile();
		}else {
			if(file.isEmpty()) {
				model.addAttribute("errorMessage","Bạn phải chọn file trước");
				return searchPosts(model, type, queryString, page, authentication);
			}
			try {
				inputFile = file.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		applyPostService.addNewApplyPost(user, post, inputFile, description);
		model.addAttribute("message","applied for the job");
		return searchPosts(model, type, queryString, page, authentication);
	}
	
	
	// handle CV download from post detail
	@RequestMapping(value = "/downloadCV")
		public ResponseEntity<Resource> downloadFile(Authentication authentication,Model model,@RequestParam(value="id") int applypostId) {
        ApplyPost applyPost = applyPostService.getApplyPostById(applypostId);
		byte[] bytes = applyPost.getCvFile();
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Disposition", String.format("attachment; filename=" + applyPost.getUser().getFirstName()+"_"+applyPost.getPost().getTitle()+".pdf" ));    
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(bytes.length).contentType(MediaType.APPLICATION_PDF)
                .body(resource);
		}
	
	 //showing user's posts
		@RequestMapping("/postApplied")
		public String showPostsAppliedList(Authentication authentication,Model model,
				@RequestParam(value="page",defaultValue = "1") int page) {
			String username = authentication.getName();
			User user = userService.findByUserName(username);
			List<Post> posts = postService.getPostUserApplied(user.getId(), page);
			int countPost = postService.getCountByUserId(user.getId());
			model.addAttribute("posts",posts);
			model.addAttribute("maxPage",(countPost-1)/5+1);
			model.addAttribute("page",page);
			model.addAttribute("username",username);
			return "post/postAppliedList";
		}
}

