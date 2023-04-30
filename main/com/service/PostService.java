package com.service;

import java.util.List;

import com.entity.Post;

public interface PostService {
	public List<Post> top5Post();
	public List<Post> getPostByUserId(int userId);
	public List<Post> getPostByUserId(int userId,int index);
	public int getCountByUserId(int userId);
}
