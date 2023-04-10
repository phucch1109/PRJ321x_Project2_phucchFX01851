package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CategoryDao;
import com.dao.PostDao;
import com.entity.Category;
import com.subEntity.TopCategoryResult;

@Service
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	PostDao postDao;
	@Autowired
	CategoryDao categoryDao;
	
	@Transactional
	public List<TopCategoryResult> getTop4Categories() {
		//using catogary list as temp output, use "Id" field as total posts value
		List<TopCategoryResult> tempHash = categoryDao.getTop4Category();
		return tempHash;
	}
	
	
	
 }
