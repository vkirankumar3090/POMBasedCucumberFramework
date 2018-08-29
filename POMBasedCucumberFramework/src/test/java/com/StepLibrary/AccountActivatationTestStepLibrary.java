package com.StepLibrary;

import gherkin.formatter.model.DataTableRow;

import java.util.List;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.Objects.AccountActivationObjects;
import com.Utilities.CommonUtilities;

import cucumber.api.DataTable;

public class AccountActivatationTestStepLibrary extends CommonUtilities {

	AccountActivationObjects accact = new AccountActivationObjects();
	Logger log = Logger.getLogger(this.getClass().getSimpleName());



	public void clickSignUpButton(String signUp) throws Exception {
		isElementPresentVerifyClick(accact.Sign_Up);
		log.info("User successfully clicked on " +signUp);
	}

	public  void validateUserOnNewAccountPage(String signUpNewAccount) throws Exception {
		isElementPresentVerification(accact.Continue_Sign_Up);
		log.info("User on Sign Up for New Account Page");
	}

	public void clickContinueSignUpButton(String continueSignUp) throws Exception {
		isElementPresentVerifyClick(accact.Continue_Sign_Up);
		log.info("User successfully clicked on " +continueSignUp);
	}

	public void validateUserOnWelcomeActivationPage(String welcomeActivation) throws Exception {
		isElementPresentVerification(accact.Get_Started);
		String pageTitle = driver.getTitle();
		Assert.assertTrue(pageTitle.equalsIgnoreCase(welcomeActivation),
				"User is not navigated to Welcome to Activation Setup screen");
		log.info("User on Welcome to Activation Setup screen");
	}


	public void validateContentsOfWelcomeActvationPage(DataTable tableText) {
		List<String> text=tableText.asList(String.class);
		text_Contains(accact.Welcome_text, text.get(0));
		text_Contains(accact.Welcome_text, text.get(1));
		text_Contains(accact.Welcome_text, text.get(2));
		text_Contains(accact.Welcome_text, text.get(4));
		text_Contains(accact.Welcome_text, text.get(5));
		log.info("All the text on Welcome Activation Page are validated");
	}

	public void validateButtonsOfWelcomeActvationPage(DataTable tableButton) throws Exception {
		for (DataTableRow row : tableButton.getGherkinRows()) {
			String option = row.getCells().get(0);
			switch (option.toLowerCase()) {
				case "GET STARTED":
					isElementPresentVerification(accact.Get_Started);
					break;
				case "CANCEL":
					isElementPresentVerification(accact.Cancel);
					break;
			}
		}
	}

	public void validateLinksOfWelcomeActvationPage(DataTable tableLink) throws Exception {
		for (DataTableRow row : tableLink.getGherkinRows()) {
			String option = row.getCells().get(0);
			switch (option.toLowerCase()) {
				case "Request one now":
					isElementPresentVerification(accact.Req_One_Link);
					break;
			}
		}
	}

	public void clickGetStartedButton(String getStarted) throws Exception {
		isElementPresentVerifyClick(accact.Get_Started);
		log.info("User successfully clicked on " +getStarted);
	}

	public void validateUserOnTermsNConditionsPage(String termsNConditions) throws Exception {
		text_Assert(accact.Terms_Conditions, termsNConditions);
		log.info("User on Terms and Conditions screen");
	}
}