package com.dao;

import java.util.List;

import com.entity.ApplyPost;

public interface ApplyPostDao {
	public int deleteAllByPostId(int postId);
	public List<ApplyPost> getApplyPostByPostId(int postId);
}
