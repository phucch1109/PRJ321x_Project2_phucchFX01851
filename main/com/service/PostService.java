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
}
