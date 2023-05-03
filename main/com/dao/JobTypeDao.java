package com.dao;

import java.util.List;

import com.entity.JobType;

public interface JobTypeDao {
	public List<JobType> getJobTypes();
	public JobType getJobTypeById(int id);
	
}
