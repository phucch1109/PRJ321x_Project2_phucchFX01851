package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CompanyDao;
import com.entity.Company;

@Service
public class CompanyServiceImpl implements CompanyService{
	
	@Autowired
	CompanyDao companyDao;
	
	@Transactional
	public List<Company> top5companies() {
		return companyDao.top5Companies();
	}
	
	@Transactional
	public List<Company> getCompanies() {
		return companyDao.getCompanies();
	}
	
	@Transactional
	public int addNewCompany(String name) {
		return companyDao.addNewCompany(name);
	}
}
