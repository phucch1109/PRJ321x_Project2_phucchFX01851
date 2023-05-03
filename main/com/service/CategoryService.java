package com.service;

import java.util.List;

import com.entity.Category;
import com.subEntity.TopCategoryResult;

public interface CategoryService {
	public List<TopCategoryResult> getTop4Categories();
	public List<Category> getCategories() ;
}
