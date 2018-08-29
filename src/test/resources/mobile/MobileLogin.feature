#Author : Kiran Kumar Baskar
@mobile
Feature: Login Page
	
	@SmokeTest @RegressionTest
  Scenario: TC_001 Verify the Login Page Components
  	Given Verify user on Login Page
  	Then Verify the components of Login Page
  	|LogoImage|
  	|My ID|
  	|Password|
  	|SIGN IN|
  	
  @SmokeTest @RegressionTest
  Scenario: TC_002 Verify user able to login to the application	
  	Given user enter id "KiranKumar"
  	And password "Testing@123"
  	Then click on the sign in button
  	Then validate user on home page  	
  