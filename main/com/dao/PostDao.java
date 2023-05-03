package com.dao;

import java.util.List;

import com.entity.Post;

public interface PostDao {
	public List<Post> top5Post() ;
	public List<Post> getPosts();
	public int getTotalPostByCategory(int categoryId);
	public List<Post> getPostByUserId(int userId);
	public int getCountByUserId(int userId);
	public List<Post> getPostByUserId(int userId,int index);
	public int deletePost(int postId);
	public int savePost(Post post);
}
