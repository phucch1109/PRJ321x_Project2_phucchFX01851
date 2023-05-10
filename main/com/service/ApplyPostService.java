package com.service;

import java.util.List;

import com.entity.ApplyPost;

public interface ApplyPostService {

	
	
	public List<ApplyPost> getApplyPostsByPostId(int postId) ;
	public ApplyPost getApplyPostById(int applyPostId);
	public void approveApplyPost(ApplyPost applyPost);
	public void refuseApplyPost(ApplyPost applyPost);
	
}
