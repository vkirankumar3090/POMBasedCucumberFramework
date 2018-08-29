package com.Trigger;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.Utilities.BaseStep;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.testng.AbstractTestNGCucumberTests;


@CucumberOptions(features = "src/test/resources", glue = {
		"com.StepDefinition", "com.Trigger"}, monochrome = true, plugin = {
		"pretty", "html:target/Report/cucumber",
		"json:target/cucumber/report.json",
		"usage:target/cucumber/cucumber-usage.json",
		"com.cucumber.listener.ExtentCucumberFormatter:" }, tags = {"@web" })
public class SmokeTest extends AbstractTestNGCucumberTests {
	
	Logger log = Logger.getLogger(this.getClass().getSimpleName());
	BaseStep base = new BaseStep();	
	public static String reportFilePath;
	String breakPoint;
	String deviceOS;		
	String appUrl;
	String appiumHost;
	
	@After
	public void takeScreenShotonFailure(Scenario scenario) throws Exception {
		base.takeScreenShotonFailure(scenario, reportFilePath);				
	}

	@BeforeSuite
	public void initialize() {
		try {			
			base.initialize();
			log.info("Property File Initialized");
			if(System.getProperty("deviceOS")!=null){
				breakPoint=System.getProperty("breakPoint");
				deviceOS=System.getProperty("deviceOS");
				appUrl=System.getProperty("appUrl");
				appiumHost=System.getProperty("appiumHost");
				log.info("deviceOS" +deviceOS);
			}else{
				breakPoint=BaseStep.config.getString("breakPoint");
				deviceOS=BaseStep.config.getString("deviceOS");
				appUrl=BaseStep.config.getString("appUrl");
				appiumHost=BaseStep.config.getString("appiumHost");				
			}
		} catch (Exception | Error e) {
			log.info(e.getStackTrace());
		}
	}

	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browser) throws Exception {		
		try {			
			reportFilePath = BaseStep.curdir + File.separator + "output"
					+ File.separator + this.getClass().getSimpleName()
					+ File.separator + deviceOS.toUpperCase();
			File path = new File(reportFilePath);
			if (path.exists()) {
				FileUtils.deleteDirectory(new File(reportFilePath));
			}
			base.reportSetup(reportFilePath + File.separator + "report.html");
			log.info("Extended Cucumber Report Setup Done");			
			log.info("breakPoint " +breakPoint);			
			if (breakPoint.equalsIgnoreCase("MobileApp"))
			{					
				base.LaunchApp(deviceOS,appiumHost);				
			} else {
				base.IntitateBrowser(breakPoint,browser,deviceOS,appiumHost,appUrl);
			}
		} catch (IOException | InterruptedException e) {			
			e.printStackTrace();
		}
	}
	
	@Parameters({"browser"})
	@Before
	public void before(String browser) throws Exception {
		log.info("breakPoint " +breakPoint);			
		if (breakPoint.equalsIgnoreCase("MobileApp"))
		{					
			base.LaunchApp(deviceOS,appiumHost);				
		} else {
			base.IntitateBrowser(breakPoint,browser,deviceOS,appiumHost,appUrl);
		}
	}
	
	@Parameters({"browser"})
	@AfterTest
	public void shutDown(String browser) throws Exception {
		Reporter.loadXMLConfig(new File("src/main/resources/Report.xml"));
		Reporter.setSystemInfo("user", System.getProperty("user.name"));
		Reporter.setSystemInfo("os", System.getProperty("os.name"));
		if (breakPoint.equalsIgnoreCase("Desktop")) {
			Reporter.setSystemInfo("Browser", browser);
		} else {
			Reporter.setSystemInfo("Browser", this.deviceOS);
		}		
		base.closeDriver(breakPoint);				
	}

	@AfterSuite
	public void jiraUpdate() throws Exception{
	
	}
}
