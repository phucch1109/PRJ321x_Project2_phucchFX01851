package com.dao;

import java.util.List;

import com.entity.Post;

public interface PostDao {
	public List<Post> top5Post() ;
	public List<Post> getPosts();
	public int getTotalPostByCategory(int categoryId);
	public List<Post> getPostsByUserId(int userId);
	public int getCountByUserId(int userId);
	public List<Post> getPostByUserId(int userId,int index);
	public int deletePost(int postId);
	public int savePost(Post post);
	public Post getPostById(int postId);
	public void updatePost(Post post);
	public List<Post> getPostsByCompanyName(String name,int index);
	public int getCountOfLastSearchQuery();
	public List<Post> getPostsByAddress(String name,int index);
	public List<Post> getPostsByCategory(String name,int index);
	public List<Post> getPostUserApplied(int userId,int index);
}
