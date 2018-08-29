package com.StepDefinition;

import com.StepLibrary.AccountActivatationTestStepLibrary;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class AccountActvationTestSteps extends AccountActivatationTestStepLibrary {


    @And("^User taps on \"([^\"]*)\" button$")
    public void userTapsOnButton(String signUp) throws Exception {
        clickSignUpButton(signUp);
    }


    @Then("^User should be navigated to \"([^\"]*)\" page$")
    public void userShouldBeNavigatedToPage(String signUpNewAccount) throws Exception {
        validateUserOnNewAccountPage(signUpNewAccount);
    }


    @Given("^User is on \"([^\"]*)\" page$")
    public void userIsOnPage(String signUpNewAccount) throws Exception {
        validateUserOnNewAccountPage(signUpNewAccount);
    }

    @When("^User taps on \"([^\"]*)\" button on Sign Up for New Account page$")
    public void userTapsOnButtonOnSignUpForNewAccountPage(String continueSignUp) throws Exception {
        clickContinueSignUpButton(continueSignUp);
    }


    @Then("^User should be redirected to \"([^\"]*)\" page$")
    public void userShouldBeRedirectedToPage(String welcomeActivation) throws Exception {
        validateUserOnWelcomeActivationPage(welcomeActivation);
    }

    @Given("^User is on the \"([^\"]*)\" page$")
    public void userIsOnThePage(String welcomeActivation) throws Exception {
        validateUserOnWelcomeActivationPage(welcomeActivation);
    }

    @And("^User should see the below texts$")
    public void userShouldSeeTheBelowTexts(DataTable tableText) throws Exception {
        validateContentsOfWelcomeActvationPage(tableText);
    }


    @And("^User should see the below buttons$")
    public void userShouldSeeTheBelowButtons(DataTable tableButtons) throws Exception {
        validateButtonsOfWelcomeActvationPage(tableButtons);

    }

    @And("^User should see the below link$")
    public void userShouldSeeTheBelowLink(DataTable tableLink) throws Exception {
        validateLinksOfWelcomeActvationPage(tableLink);
    }

    @When("^User taps on \"([^\"]*)\" button on Welcome to Activation page$")
    public void userTapsOnButtonOnWelcomeToActivationPage(String get_Started) throws Exception {
        clickGetStartedButton(get_Started);
    }

    @Then("^User should be navigated to the \"([^\"]*)\" page$")
    public void userShouldBeNavigatedToThePage(String termsNConditions) throws Exception {
        validateUserOnTermsNConditionsPage(termsNConditions);
    }

    @And("^User has NOT scrolled down till the complete Terms & Conditions$")
    public void userHasNOTScrolledDownTillTheCompleteTermsConditions() throws Throwable {

    }

    @When("^User Checks on the checkbox$")
    public void userChecksOnTheCheckbox() throws Throwable {

    }

    @Then("^User should see an error$")
    public void userShouldSeeAnError() throws Throwable {

    }

    @And("^User should see the checkbox as unchecked$")
    public void userShouldSeeTheCheckboxAsUnchecked() throws Throwable {

    }

    @Then("^User should see an error as \"([^\"]*)\"$")
    public void userShouldSeeAnErrorAs(String arg0) throws Throwable {

    }

    @When("^User scrolls down till the end of Terms & Conditions$")
    public void userScrollsDownTillTheEndOfTermsConditions() throws Throwable {

    }

    @Then("^User should be navigated to \"([^\"]*)\"$")
    public void userShouldBeNavigatedTo(String arg0) throws Throwable {

    }

    @When("^User enters \"([^\"]*)\" activation code$")
    public void userEntersActivationCode(String arg0) throws Throwable {

    }

    @Then("^User should see \"([^\"]*)\" button enabled$")
    public void userShouldSeeButtonEnabled(String arg0) throws Throwable {

    }

    @Then("^User should see an error message as \"([^\"]*)\"$")
    public void userShouldSeeAnErrorMessageAs(String arg0) throws Throwable {

    }

    @And("^User should see the below steps tracker$")
    public void userShouldSeeTheBelowStepsTracker() throws Throwable {

    }

    @And("^User should see the below as link$")
    public void userShouldSeeTheBelowAsLink() throws Throwable {

    }

    @And("^User should see the below as buttons$")
    public void userShouldSeeTheBelowAsButtons() throws Throwable {

    }

    @When("^User taps on \"([^\"]*)\" link$")
    public void userTapsOnLink(String arg0) throws Throwable {

    }

    @Then("^User should see a tooltip with verbiage$")
    public void userShouldSeeATooltipWithVerbiage() throws Throwable {

    }

    @When("^User should enter \"([^\"]*)\" in \"([^\"]*)\" field$")
    public void userShouldEnterInField(String arg0, String arg1) throws Throwable {

    }


}
