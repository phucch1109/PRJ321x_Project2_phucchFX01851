package com.service;

import java.util.List;

import com.entity.Post;
import com.entity.User;
import com.subEntity.PostForm;

public interface PostService {
	public List<Post> top5Post();
	public List<Post> getPostByUserId(int userId);
	public List<Post> getPostByUserId(int userId,int index);
	public int getCountByUserId(int userId);
	public int deletePost(int postId);
	public int addPost(PostForm postForm,User user);
	public Post getPostById(int postId);
	public void updatePost(PostForm postForm,User user,int postId);
	public List<Post> getPostsByCompanyName(String name,int index);
	public int getCountOfLastSearchQuery();
	public List<Post> getPostsByAddress(String name,int index);
	public List<Post> getPostsByCategory(String name,int index) ;
	public List<Post> getPostUserApplied(int userId,int index);
}
