package com.Utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.cucumber.listener.ExtentProperties;

import cucumber.api.Scenario;

public class BaseStep{

	public boolean initilizestatus = false;
	public InputStream isConfig;
	public static String curdir;
	@SuppressWarnings("rawtypes")
	public AndroidDriver androidDriver;
	@SuppressWarnings("rawtypes")
	public IOSDriver iOSDriver;
	public WebElement element;
	public static Configuration config = null;
	static AppiumServer server = new AppiumServer();
	public static boolean AUTOEXECTIONSTATUS = true;
	public static RemoteWebDriver driver;
	public static String deviceOS;
	
	Logger Log = Logger.getLogger(this.getClass().getSimpleName());

	public void initialize() {
		curdir = System.getProperty("user.dir");
		System.setProperty("currentDir", curdir);
		PropertyConfigurator.configure("log4j.properties");
		if (!initilizestatus) {
			Log.info("------------------initilizing----------------");
			try {
				ConfigurationFactory factory = new ConfigurationFactory(
						"config/config.xml");
				config = factory.getConfiguration();
			} catch (ConfigurationException e) {
				AUTOEXECTIONSTATUS = false;
				e.printStackTrace();
			}
			initilizestatus = true;
		} else {
			Log.info("Initilization is Already Done");
		}
	}

	@SuppressWarnings("static-access")
	public void LaunchApp(String deviceOS,String host) throws Exception {
		try{
			this.deviceOS=deviceOS;
			server.startServer(host);
			InitiateApp(deviceOS);	
		}catch(Exception e){
			AUTOEXECTIONSTATUS = false;
			server.stopServer();
			e.printStackTrace();
		}				
	}

	@SuppressWarnings("rawtypes")
	public RemoteWebDriver InitiateApp(String deviceOS) throws Exception {
		Log.info("Launching Application");
		String appPath;
		String appiumServerUrl = server.serverUrl;
		if(System.getProperty("appPath")==null){
			appPath = System.getProperty("appPath");
		}else{
			appPath = config.getString("appPath");
		}
		try {
			if (deviceOS.equalsIgnoreCase("Android")) {
				String androidAppPath = appPath+".apk";								 
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("deviceName",
						config.getString("deviceName"));
				capabilities.setCapability("platformName",
						config.getString("platformName"));
				capabilities.setCapability("platformVersion",
						config.getString("PlatformVersion"));
				capabilities.setCapability("appPackage",
						config.getString("appPackage"));
				capabilities.setCapability("appActivity",
						config.getString("appActivity"));
				if(config.getString("emulator").equalsIgnoreCase("true")){
					capabilities
					.setCapability("udid", config.getString("deviceId"));					
				}				
				capabilities.setCapability("newCommandTimeout", 0);
				capabilities.setCapability("unicodeKeyboard", true);
				capabilities.setCapability("resetKeyboard", true);
				if (config.getString("resetMode").equalsIgnoreCase("full")) {
					capabilities.setCapability("fullReset", true);
					capabilities.setCapability("noReset", false);
					capabilities.setCapability("app", androidAppPath);
					capabilities.setCapability("unlockType", "pin");
					capabilities.setCapability("unlockKey", "1111");
					Log.info("APPIUM will start with fullReset Mode");
				} else if (config.getString("resetMode").equalsIgnoreCase("no")) {
					capabilities.setCapability("fullReset", false);
					capabilities.setCapability("noReset", true);
					capabilities.setCapability("unlockType", "pin");
					capabilities.setCapability("unlockKey", "1111");
					Log.info("APPIUM will start with noReset Mode");
				}
				androidDriver = new AndroidDriver(new URL(
						appiumServerUrl), capabilities);
				driver = androidDriver;
				Log.info("Application Launched in Android Device");
			} else if(deviceOS.equalsIgnoreCase("ios")){
				//change the extention to .ipa for real device
				String iosAppPath = appPath+".app";
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setCapability("device",
						deviceOS);
				capabilities.setCapability("deviceName",
						config.getString("deviceName"));
				capabilities.setCapability("platformName",
						config.getString("platformName"));
				capabilities.setCapability("platformVersion",
						config.getString("PlatformVersion"));
				if (config.getString("emulator").equalsIgnoreCase("false")) {
					capabilities
							.setCapability("udid", config.getString("udid"));	
					iosAppPath = System.getProperty("appPath")+".ipa";
				}				
				capabilities.setCapability("newCommandTimeout", 0);
				capabilities.setCapability("--session-override", true);
				capabilities.setCapability("automationName", "XCUITest");
				if (config.getString("resetMode").equalsIgnoreCase("full")) {
					capabilities.setCapability("fullReset", true);
					capabilities.setCapability("noReset", false);
					capabilities.setCapability("app", iosAppPath);
					Log.info("APPIUM will start with fullReset Mode");
				} else if (config.getString("resetMode").equalsIgnoreCase("no")) {
					capabilities.setCapability("fullReset", false);
					capabilities.setCapability("noReset", true);
					capabilities.setCapability("bundleId",
							config.getString("bundleId"));
					Log.info("APPIUM will start with noReset Mode");
				}
				iOSDriver = new IOSDriver(new URL(appiumServerUrl), capabilities);
				driver = iOSDriver;
				Log.info("Application Launched in iOS Device");
			}else{				
				Log.info("Invalid Device OS :"+deviceOS);
			}
		} catch (Exception e) {
			AUTOEXECTIONSTATUS = false;
			throw new Exception("Unable to Launch the app");
		}
		return driver;
	}





	/**
	 * @author Kiran Kumar Baskar
	 * Description: Launch the Web or Mobile Application based on the following values
	 * Values: breakPoint,operatingSystem,browser,machineOS,appUrl,serverUrl(Appium server or gird url)
	 **/

	@SuppressWarnings("static-access")
	public RemoteWebDriver IntitateBrowser(String breakPoint,String browser,String deviceOS,String appiumHost,String appUrl) throws Exception {		
		this.deviceOS=deviceOS;
		if (breakPoint.equalsIgnoreCase("Desktop")) {
			DesiredCapabilities capabilities = null;
			switch (browser.toLowerCase()) {
			case "firefox":
				Log.info("Starting FireFox Browser");
				capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("browserName", browser);				
				break;
			case "chrome":			
				Log.info("Starting Chrome Browser");
				if ("Windows".equalsIgnoreCase(deviceOS)) {
					System.setProperty("webdriver.chrome.driver", curdir
							+ "/drivers/chromedriver.exe");
					driver = new ChromeDriver();
				} else if ("mac".equalsIgnoreCase(deviceOS)){
					driver = new ChromeDriver();
				}else{
					Log.info("**********Given OS is not supported************");
				}
				break;
			case "ie":	
				if ("windows".equalsIgnoreCase(deviceOS)){
					Log.info("Starting IE Browser");
					System.setProperty("webdriver.ie.driver", curdir
							+ "/drivers/IEDriverServer.exe");
					driver = new InternetExplorerDriver();						
				}else {
					Log.info("**********Given Browser Name is not supported in this machine************");
				}				
				break;
			case "safari":
				if ("mac".equalsIgnoreCase(config.getString("machineOS"))){
					Log.info("Starting Safari Browser");
					driver = new SafariDriver();					
				}else{
					Log.info("**********Given Browser Name is not supported in this machine************");
				}				
				break;
			default:
				Log.info("**********Given Browser Name is Wrong************ "+browser);	
				break;
			}
			Log.info("Started with " + browser + " Browser");
			driver.manage().window().maximize();
			driver.get(appUrl);
		} else if (breakPoint.equalsIgnoreCase(
				"Mobile")) {
			try{
				server.startServer(appiumHost);	
				String appiumServerUrl = server.serverUrl;
				if ("iOS".equalsIgnoreCase(deviceOS)) {				
					initiateBrowser_iOS(appiumServerUrl);
				} else if ("android".equalsIgnoreCase(deviceOS)) {
					initiateBrowser_Android(appiumServerUrl);
				} 
				driver.get(appUrl);
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}catch(Exception e){
				AUTOEXECTIONSTATUS = false;
				server.stopServer();
				e.printStackTrace();
			}				
		}
			
			
		return driver;
	}


	/**
	 * @author Kiran Kumar Baskar
	 * Description: Launch chrome browser in Android device
	 * Values: sdk_location,deviceId should be set in application.properties file and serverUrl(appium server url)
	 **/

	public RemoteWebDriver initiateBrowser_Android(String appiumServerUrl) throws IOException,
			InterruptedException {
		try {
			DesiredCapabilities capabilities=DesiredCapabilities.android();
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,config.getString("deviceName"));
			capabilities.setCapability(MobileCapabilityType.VERSION,config.getString("PlatformVersion"));			 
			driver = new RemoteWebDriver(new URL(appiumServerUrl), capabilities);
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			AUTOEXECTIONSTATUS = false;
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * @author Kiran Kumar Baskar
	 * Description: Launch safari browser in IOS device
	 * Values: PlatformVersion,udid should be set in application.properties file and serverUrl(appium server url)
	 **/

	public RemoteWebDriver initiateBrowser_iOS(String appiumServerUrl) throws IOException,
			InterruptedException {
		try {
			System.out.println("initialising the Ios browser");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
			capabilities.setCapability("platformName", config.getString("platformName"));
			capabilities.setCapability("platformVersion",
					config.getString("PlatformVersion"));
			capabilities.setCapability("deviceName", config.getString("deviceName"));
			if (config.getString("emulator").equalsIgnoreCase("false")) {
				capabilities.setCapability("-U", config.getString("udid"));	
			}			
			capabilities.setCapability("noReset", true);
			capabilities.setCapability("autoAcceptAlerts", true);
			driver = new RemoteWebDriver(new URL(appiumServerUrl), capabilities);
			driver.manage().deleteAllCookies();
		} catch (Exception e) {
			AUTOEXECTIONSTATUS = false;
			e.printStackTrace();
		}
		return driver;
	}


	/**
	 * @author Kiran Kumar Baskar Description: Capture the screen shot and set
	 *         the screen shot in extent report also returns the screenshot
	 *         file. Parameters: Scenario and reportPath(This is used for extent
	 *         report purpose)
	 **/

	public File getScreenShotFile(Scenario scenario, String reportPath)
			throws IOException {
		File screenshot = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);
			String path = reportPath + File.separator
					+ scenario.getName() + ".png";
			FileUtils.copyFile(screenshot, new File(path));
//			Reporter.addScreenCaptureFromPath(path);		
		return screenshot;
	}

	/**
	 * @author Kiran Kumar Baskar Description: Capture the screen shot and embed
	 *         in cucumber and extend report. Parameters: Scenario and
	 *         reportPath(This is used for extent report purpose)
	 **/

	public String takeScreenShotonFailure(Scenario scenario, String reportPath) {
		String stepName = "";
		if (scenario.getStatus().equalsIgnoreCase("failed")) {
			try {
				stepName = reportPath + File.separator
				+ scenario.getName() + ".png";
				File scrFile = getScreenShotFile(scenario, reportPath);
				byte[] data = FileUtils.readFileToByteArray(scrFile);
				scenario.embed(data, "image/png");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Log.info("Screen Shot taken");
		}
		return stepName;
	}

	public void closeDriver(String breakPoint) {
		if(breakPoint.toLowerCase().startsWith("mobile")){
			driver.quit();
			Log.info("Application Closed");
			server.stopServer();
			Log.info(breakPoint+" is Quit");
		}else{
			if (driver != null) {
				driver.quit();
				Log.info("Application Closed");			
			}				
		}		
	}

	public void reportSetup(String filePath) {
		ExtentProperties extentReport = ExtentProperties.INSTANCE;
		extentReport.setReportPath(filePath);
	}

	public String objectProperty(String webProperty,String iosProperty,
			String androidProperty) {
		String value = null;
		if(deviceOS.equalsIgnoreCase(
				"Windows")||deviceOS.equalsIgnoreCase("mac")) {
			value = webProperty;
		}
		else if (deviceOS.equalsIgnoreCase("Android")) {
			value = androidProperty;
		} else {
			value = iosProperty;

		}
		return value;
	}
}
