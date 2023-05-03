package com.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.entity.User;
import com.service.CompanyService;
import com.service.RoleService;
import com.service.UserService;
import com.subEntity.CrmUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {
	
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
	private CompanyService companyService;
    
    private Logger logger = Logger.getLogger(getClass().getName());
    
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	
	
	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {		
		theModel.addAttribute("crmUser", new CrmUser());
		theModel.addAttribute("companies", companyService.getCompanies());
		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
				@RequestBody @Valid @ModelAttribute("crmUser") CrmUser theCrmUser, 
				BindingResult theBindingResult, 
				Model theModel,
				 @RequestParam("roleId") int roleId,
				 @RequestParam(value = "companyId", defaultValue = "-1") int companyId,
				 @RequestParam(value = "companyName", defaultValue = "") String companyName) {
				
		
		String userName = theCrmUser.getUserName();
		logger.info("Processing registration form for: " + userName);
		logger.info(theCrmUser.getEmail() +" " + theCrmUser.getPassword());
		// form validation
		 if (theBindingResult.hasErrors()){
			 theModel.addAttribute("registrationError", "Error, ");
			 //theModel.addAttribute("crmUser", new CrmUser());
			theModel.addAttribute("companies", companyService.getCompanies());
			return "registration-form";		
	        }

		// check the database if user already exists
        User existing = userService.findByUserName(userName);
        if (existing != null){
			theModel.addAttribute("registrationError", "User name already exists.");
			logger.warning("User name already exists.");			
			return showMyLoginPage(theModel);
        }
        
      
        if(companyId == -1 && roleId == 2) {
       	companyId = companyService.addNewCompany(companyName);
        }
        logger.info("companyID :" +companyId);
  //      create user account        						
        userService.save(theCrmUser,roleId,companyId);
        
        logger.info("Successfully created user: " + userName);
        
        return "registration-confirmation";		
	}
}
