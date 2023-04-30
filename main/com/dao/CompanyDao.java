package com.dao;

import java.util.List;

import com.entity.Company;

public interface CompanyDao {
	public List<Company> top5Companies();
	
	public List<Company> getCompanies();
	
	public Company getCompanyById(int id);
	
	public int addNewCompany(String name);
	
	public void update(Company company);
}
