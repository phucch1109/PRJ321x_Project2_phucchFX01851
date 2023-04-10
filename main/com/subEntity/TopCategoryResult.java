package com.subEntity;

public class TopCategoryResult {
    private String categoryName;
    
    private long countPost;
    
    public TopCategoryResult(String categoryName, long countPost) {
        this.categoryName = categoryName;
        this.countPost = countPost;
    }

    public TopCategoryResult() {
    }

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public long getCountPost() {
		return countPost;
	}

	public void setCountPost(long countPost) {
		this.countPost = countPost;
	}

    // getters and setters 
    
}
