package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.Post;
import com.entity.User;
import com.service.CategoryService;
import com.service.CompanyService;
import com.service.PostService;
import com.service.UserService;

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
		return "postList";
	}
	
	 //showing user's posts
		@RequestMapping("/postList")
		public String deletePost(Authentication authentication,Model model,
				@RequestParam(value="page",defaultValue = "1") int page) {
			String username = authentication.getName();
			User user = userService.findByUserName(username);
			List<Post> posts = postService.getPostByUserId(user.getId(),page);
			int countPost = postService.getCountByUserId(user.getId());
			model.addAttribute("posts",posts);
			model.addAttribute("maxPage",(countPost-1)/5+1);
			model.addAttribute("page",page);
			return "postList";
		}
	
}
