package com.service;

import java.util.List;

import com.entity.ApplyPost;
import com.entity.Post;
import com.entity.User;

public interface ApplyPostService {

	
	
	public List<ApplyPost> getApplyPostsByPostId(int postId) ;
	public ApplyPost getApplyPostById(int applyPostId);
	public void approveApplyPost(ApplyPost applyPost);
	public void refuseApplyPost(ApplyPost applyPost);
	public void addNewApplyPost(User user,Post post,byte[] file,String description);
}
