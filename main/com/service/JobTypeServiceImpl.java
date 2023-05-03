package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.JobTypeDao;
import com.entity.JobType;
@Service
public class JobTypeServiceImpl implements JobTypeService{
	@Autowired
	JobTypeDao jobTypeDao;
	
	@Override
	@Transactional
	public List<JobType> getJobTypes() {
		return jobTypeDao.getJobTypes();
	}
}
