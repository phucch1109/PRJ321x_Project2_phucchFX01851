package com.service;

import java.util.List;

import com.entity.Company;

public interface CompanyService {
	public List<Company> top5companies();
	
	public List<Company> getCompanies();
}
