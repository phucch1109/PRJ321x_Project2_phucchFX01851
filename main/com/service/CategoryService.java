package com.service;

import java.util.List;

import com.subEntity.TopCategoryResult;

public interface CategoryService {
	public List<TopCategoryResult> getTop4Categories();
}
