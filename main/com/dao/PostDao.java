package com.dao;

import java.util.List;

import com.entity.Post;

public interface PostDao {
	public List<Post> top5Post() ;
	public List<Post> getPosts();
	public int getTotalPostByCategory(int categoryId);
	
}
