package com.dao;

import java.util.List;

import com.entity.Category;
import com.subEntity.TopCategoryResult;

public interface CategoryDao {
	public List<Category> getCategories();
	public List<TopCategoryResult> getTop4Category();
	public Category getCategoryById(int id) ;
}
