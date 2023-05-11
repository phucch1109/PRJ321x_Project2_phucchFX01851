package com.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ApplyPostDao;
import com.dao.CategoryDao;
import com.dao.CompanyDao;
import com.dao.JobTypeDao;
import com.dao.PostDao;
import com.entity.Category;
import com.entity.JobType;
import com.entity.Post;
import com.entity.User;
import com.subEntity.PostForm;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	PostDao postDao;
	@Autowired
	ApplyPostDao applyPostDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	JobTypeDao jobTypeDao;
	@Autowired
	CategoryDao categoryDao;
	
	
	
	@Override
	@Transactional
	public List<Post> top5Post() {
		return postDao.top5Post();
	}
	
	@Override
	@Transactional
	public List<Post> getPostByUserId(int userId) {
		return postDao.getPostsByUserId(userId);
	}
	
	@Override
	@Transactional
	public List<Post> getPostByUserId(int userId,int index) {
		return postDao.getPostByUserId(userId, index);
	}
	
	@Override
	@Transactional
	public int getCountByUserId(int userId) {
		return postDao.getCountByUserId(userId);
	}
	
	@Override
	@Transactional
	public int deletePost(int postId) {
		applyPostDao.deleteAllByPostId(postId);
		return postDao.deletePost(postId);
	}
	
	@Override
	@Transactional
	public int addPost(PostForm postForm,User user) {
		Post post = new Post();
		post.setTitle(postForm.getTitle());
		post.setDescription(postForm.getDescription());
		post.setExperience(postForm.getExperience());
		post.setNumberOfRecruit(Integer.parseInt(postForm.getNumberOfRecruit()));
		//format date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed;
		try {
			parsed = format.parse(postForm.getExpireDate());
			Date expireDate = new Date(parsed.getTime());
			post.setExpireDate(expireDate);
			post.setDateCreated(new Date(System.currentTimeMillis()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		post.setCompany(user.getCompany());
		post.setSalary(postForm.getSalary());
		JobType jobType = jobTypeDao.getJobTypeById(postForm.getJobTypeId());
		post.setJobType(jobType);
		Category category = categoryDao.getCategoryById(postForm.getCategoryId());
		post.setCategory(category);
		post.setUser(user);
		return postDao.savePost(post);
	}
	
	@Override
	@Transactional
	public Post getPostById(int postId) {
		return postDao.getPostById(postId);
	}
	
	@Override
	@Transactional
	public void updatePost(PostForm postForm,User user,int postId) {
		Post post = postDao.getPostById(postId);
		post.setTitle(postForm.getTitle());
		post.setDescription(postForm.getDescription());
		post.setExperience(postForm.getExperience());
		post.setNumberOfRecruit(Integer.parseInt(postForm.getNumberOfRecruit()));
		//format date
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsed;
		try {
			parsed = format.parse(postForm.getExpireDate());
			Date expireDate = new Date(parsed.getTime());
			post.setExpireDate(expireDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		post.setCompany(user.getCompany());
		post.setSalary(postForm.getSalary());
		JobType jobType = jobTypeDao.getJobTypeById(postForm.getJobTypeId());
		post.setJobType(jobType);
		Category category = categoryDao.getCategoryById(postForm.getCategoryId());
		post.setCategory(category);
		post.setUser(user);
		postDao.updatePost(post);
	}
	
	@Override
	@Transactional
	public List<Post> getPostsByCompanyName(String name,int index) {
		return postDao.getPostsByCompanyName(name, index);
	}
	
	@Override
	@Transactional
	public int getCountOfLastSearchQuery() {
		return postDao.getCountOfLastSearchQuery();
	}
	
	@Override
	@Transactional
	public List<Post> getPostsByCategory(String name,int index) {
		return postDao.getPostsByCategory(name, index);
	}
	
	@Override
	@Transactional
	public List<Post> getPostsByAddress(String name,int index) {
		return postDao.getPostsByAddress(name, index);
	}
}
