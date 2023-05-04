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
		return applyPostDao.getApplyPostByPostId(postId);
	}
	
}
