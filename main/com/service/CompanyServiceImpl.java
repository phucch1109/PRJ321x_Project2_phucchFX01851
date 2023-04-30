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
	
	@Override
	@Transactional
	public List<Company> top5companies() {
		return companyDao.top5Companies();
	}
	
	@Override
	@Transactional
	public List<Company> getCompanies() {
		return companyDao.getCompanies();
	}
	
	@Override
	@Transactional
	public int addNewCompany(String name) {
		return companyDao.addNewCompany(name);
	}
	
	@Override
	@Transactional
	public void update(Company company) {
		companyDao.update(company);
	}
	
	
}
