package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ApplyPostDao;
import com.entity.ApplyPost;

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
}
