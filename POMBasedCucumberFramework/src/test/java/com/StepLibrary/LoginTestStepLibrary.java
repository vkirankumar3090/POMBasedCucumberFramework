package com.StepLibrary;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.Objects.LoginTestObjects;
import com.Utilities.CommonUtilities;

public class LoginTestStepLibrary extends CommonUtilities {

	LoginTestObjects login = new LoginTestObjects();
	Logger log = Logger.getLogger(this.getClass().getSimpleName());

	public void web_home_page() throws Throwable {
		driver.get(config.getString("appUrl"));
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.equalsIgnoreCase("My test"),
				"User is not navigated to test home page");
		log.info("Successfully navigated to test home page");
	}

	public void validateUserOnHomePage() {
		Assert.assertEquals(true, false);
		log.info("Login functionality is not implemented");
	}	
}