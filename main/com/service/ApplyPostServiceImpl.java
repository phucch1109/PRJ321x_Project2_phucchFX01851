package com.service;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApplyPostDao;
import com.entity.ApplyPost;
import com.entity.Post;
import com.entity.User;

@Service
public class ApplyPostServiceImpl implements ApplyPostService{
	@Autowired
	ApplyPostDao applyPostDao;
	
	@Override
	@Transactional
	public List<ApplyPost> getApplyPostsByPostId(int postId) {
		return applyPostDao.getApplyPostsByPostId(postId);
	}
	
	@Override
	@Transactional
	public ApplyPost getApplyPostById(int applyPostId) {
		return applyPostDao.getApplyPostById(applyPostId);
	}
	
	@Override
	@Transactional
	public void approveApplyPost(ApplyPost applyPost) {
		applyPost.setStatus(1);
		applyPostDao.updateApplyPost(applyPost);
	}
	
	@Override
	@Transactional
	public void refuseApplyPost(ApplyPost applyPost) {
		applyPost.setStatus(2);
		applyPostDao.updateApplyPost(applyPost);
	}
	
	@Override
	@Transactional
	public void addNewApplyPost(User user,Post post,byte[] file,String description) {
		ApplyPost applyPost = new ApplyPost();
		applyPost.setUser(user);
		applyPost.setPost(post);
		applyPost.setStatus(0);
		applyPost.setText(description);
		applyPost.setCvFile(file);
		applyPost.setDateCreate(new Date(System.currentTimeMillis()));
		applyPostDao.addnewApplyPost(applyPost);
	}
	
}
