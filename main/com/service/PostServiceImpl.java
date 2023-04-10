package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PostDao;
import com.entity.Post;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao postDao;
	
	@Transactional
	public List<Post> top5Post() {
		return postDao.top5Post();
	}
	
	
}
