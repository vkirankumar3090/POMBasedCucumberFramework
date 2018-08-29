package com.StepDefinition;

import com.StepLibrary.LoginTestStepLibrary;

import cucumber.api.java.en.Then;

public class LoginTestSteps extends LoginTestStepLibrary {
	
	@Then("^validate user on home page$")
	public void validate_user_on_home_page() throws Throwable {
		validateUserOnHomePage();	    
	}
}