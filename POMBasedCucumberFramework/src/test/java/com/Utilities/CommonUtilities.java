package com.Utilities;

import io.appium.java_client.MobileBy.ByAccessibilityId;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonUtilities extends BaseStep {

	private static String ALPHA_NUMERIC_STRING = "";
	static Integer Ycoordinate = null;
	static Integer Xcoordinate = null;
	static Integer img_Width = null;
	static Integer img_Height = null;
	static String imgLocation;
	static String imgSize;
	static long t1 = 0;
	static long t2 = 0;
	static long timeTaken = 0;
	static WebDriverWait browserWithElementWait = null;

	Logger log = Logger.getLogger(this.getClass().getSimpleName());

	/*
	 * Get X and Y Co-ordination of the element Parmerter: object and driver
	 */

	public Point getCoOrdination(String object) {
		Point points = null;
		WebElement fromElement = getElementByProperty(object);
		points = fromElement.getLocation();
		return points;
	}

	/*
	 * Get X and Y Co-ordination of the element Parmerter: Webelement and Mobile
	 * driver
	 */

	public Point getCoOrdination(WebElement object) {
		Point points = null;
		points = object.getLocation();
		return points;
	}

	/*
	 * Get Height of the element Parmerter: object and driver
	 */

	public int getHeight(String object) {
		int height = 0;
		WebElement fromElement = getElementByProperty(object);
		height = fromElement.getSize().getHeight();
		return height;
	}

	/*
	 * Swipe for mobile application Parmerter: fromObject, toObject and Mobile
	 * driver
	 */

	public void swipeToElement(String fromObject, String toObject) {
		Point fromPoints = getCoOrdination(fromObject);
		int startX = fromPoints.getX();
		int startY = fromPoints.getY();
		Point toPoints = getCoOrdination(toObject);
		int endX = toPoints.getX();
		int endY = toPoints.getY();
		swipeToElement(startX, startY, endX, endY);
	}

	/*
	 * Swipe for mobile application Parmerter: fromElement, toElement and Mobile
	 * driver
	 */

	public void swipeToElement(WebElement fromObject, WebElement toObject) {
		Point fromPoints = getCoOrdination(fromObject);
		int startX = fromPoints.getX();
		int startY = fromPoints.getY();
		Point toPoints = getCoOrdination(toObject);
		int endX = toPoints.getX();
		int endY = toPoints.getY();
		swipeToElement(startX, startY, endX, endY);
	}

	/*
	 * Swipe
	 */

	public void swipeToElement(int startX, int startY, int endX, int endY) {
		if (System.getProperty("deviceOS").equalsIgnoreCase("iOS")) {
			TouchAction tap = new TouchAction(iOSDriver);			
			tap.press(startX, startY).moveTo(endX - startX, endY - startY).release().perform();
		} else {
			TouchAction tap = new TouchAction(androidDriver);
			tap.press(startX, startY).moveTo(endX - startX, endY - startY).release().perform();
		}		
	}

	/*
	 * Method for converting Month Text to integer
	 */

	public String monthNametoDigit(String month) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM");
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputFormat.parse(month));
		SimpleDateFormat outputFormat = new SimpleDateFormat("MM"); // 01-12
		String convMonth = outputFormat.format(cal.getTime());
		return convMonth;
	}

	/*
	 * Method for converting year 4 digit to 2 digit
	 */

	public String year4to2Digit(String year) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		cal.setTime(inputFormat.parse(year));
		SimpleDateFormat outputFormat = new SimpleDateFormat("yy"); // 01-12
		String convYear = outputFormat.format(cal.getTime());
		return convYear;
	}

	/*
	 * Method for tap on the Element using object and no.of finger applicable
	 * for mobile devices
	 */

	public void tap(int fingers, String objectProperty) {
		WebElement ele = getElementByProperty(objectProperty);
		Point point = ele.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		if (System.getProperty("deviceOS").equalsIgnoreCase("iOS")) {
			MultiTouchAction multiTouch = new MultiTouchAction(iOSDriver);
			multiTouch.add(createTap(xcord, ycord));
			System.out.println("Element's Position from left side Is " + xcord
					+ " pixels.");
			System.out.println("Element's Position from top side Is " + (ycord)
					+ " pixels.");
			multiTouch.perform();
		} else {
			MultiTouchAction multiTouch = new MultiTouchAction(androidDriver);
			multiTouch.add(createTap(xcord, ycord));
			System.out.println("Element's Position from left side Is " + xcord
					+ " pixels.");
			System.out.println("Element's Position from top side Is " + (ycord)
					+ " pixels.");
			multiTouch.perform();
		}
	}

	/*
	 * Method for tap on the Element using element and no.of finger applicable
	 * for mobile devices
	 */

	public void tap(int fingers, WebElement element) {
		Point point = element.getLocation();
		int xcord = point.getX();
		int ycord = point.getY();
		if (System.getProperty("deviceOS").equalsIgnoreCase("iOS")) {
			MultiTouchAction multiTouch = new MultiTouchAction(iOSDriver);
			multiTouch.add(createTap(xcord, ycord));
			System.out.println("Element's Position from left side Is " + xcord
					+ " pixels.");
			System.out.println("Element's Position from top side Is " + (ycord)
					+ " pixels.");
			multiTouch.perform();
		} else {
			MultiTouchAction multiTouch = new MultiTouchAction(androidDriver);
			multiTouch.add(createTap(xcord, ycord));
			System.out.println("Element's Position from left side Is " + xcord
					+ " pixels.");
			System.out.println("Element's Position from top side Is " + (ycord)
					+ " pixels.");
			multiTouch.perform();
		}
	}

	/*
	 * Method for Tap using no.of fingers and x and y coordination applicable
	 * for mobile devices
	 */

	public void tap(int fingers, int xcord, int ycord) {
		if (System.getProperty("deviceOS").equalsIgnoreCase("iOS")) {
			MultiTouchAction multiTouch = new MultiTouchAction(iOSDriver);
			multiTouch.add(createTap(xcord, ycord));
			System.out.println("Element's Position from left side Is " + xcord
					+ " pixels.");
			System.out.println("Element's Position from top side Is " + ycord
					+ " pixels.");
			multiTouch.perform();
		} else {
			MultiTouchAction multiTouch = new MultiTouchAction(androidDriver);
			multiTouch.add(createTap(xcord, ycord));
			System.out.println("Element's Position from left side Is " + xcord
					+ " pixels.");
			System.out.println("Element's Position from top side Is " + ycord
					+ " pixels.");
			multiTouch.perform();
		}
	}

	/*
	 * Method for Tap Action Create
	 */

	private TouchAction createTap(int x, int y) {
		if (System.getProperty("deviceOS").equalsIgnoreCase("iOS")) {
			TouchAction tap = new TouchAction(iOSDriver);			
			return tap.press(x, y).release();
		} else {
			TouchAction tap = new TouchAction(androidDriver);
			return tap.press(x, y).release();
		}
	}

	/*
	 * Method for String Formatter
	 */

	public String stringFormat(boolean uppercase, boolean lowecase,
			boolean number, boolean specialChar, boolean emailField) {
		String lowerChar = "abcdefghijklmnopqrstuvwxyz";
		String upperChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numberChar = "0123456789";
		String specialCharer = null;
		if (emailField) {
			specialCharer = "@.";
		} else {
			specialCharer = "! # $ % & ( ) * + , - . / : ; < = > ? @ [ \\ ] _ ` { | } ~)";
		}
		if (uppercase && lowecase && number && specialChar) {
			ALPHA_NUMERIC_STRING = lowerChar + upperChar + numberChar
					+ specialCharer;
		} else if (lowecase && number && specialChar) {
			ALPHA_NUMERIC_STRING = lowerChar + numberChar + specialCharer;
		} else if (uppercase && number && specialChar) {
			ALPHA_NUMERIC_STRING = upperChar + numberChar + specialCharer;
		} else if (uppercase && lowecase && specialChar) {
			ALPHA_NUMERIC_STRING = upperChar + lowerChar + specialCharer;
		} else if (uppercase && lowecase && number) {
			ALPHA_NUMERIC_STRING = upperChar + lowerChar + numberChar;
		} else if (number && specialChar) {
			ALPHA_NUMERIC_STRING = numberChar + specialCharer;
		} else if (lowecase && specialChar) {
			ALPHA_NUMERIC_STRING = lowerChar + specialCharer;
		} else if (lowecase && number) {
			ALPHA_NUMERIC_STRING = lowerChar + numberChar;
		} else if (uppercase && specialChar) {
			ALPHA_NUMERIC_STRING = upperChar + specialCharer;
		} else if (uppercase && number) {
			ALPHA_NUMERIC_STRING = upperChar + numberChar;
		} else if (uppercase && lowecase) {
			ALPHA_NUMERIC_STRING = upperChar + lowerChar;
		} else if (specialChar) {
			ALPHA_NUMERIC_STRING = specialCharer;
		} else if (number) {
			ALPHA_NUMERIC_STRING = numberChar;
		} else if (lowecase) {
			ALPHA_NUMERIC_STRING = lowerChar;
		} else if (uppercase) {
			ALPHA_NUMERIC_STRING = upperChar;
		}
		return ALPHA_NUMERIC_STRING;
	}

	/*
	 * Methods for creating Random Alpha Numeric text
	 */

	public String randomAlphaNumeric(boolean uppercase, boolean lowecase,
			boolean number, boolean specialChar, int count, boolean emailField) {
		stringFormat(uppercase, lowecase, number, specialChar, emailField);
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
			int character = (int) (Math.random() * ALPHA_NUMERIC_STRING
					.length());
			builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
	}

	/*
	 * Method for getting list of random test data
	 */

	public ArrayList<String> testDataList(boolean uppercase, boolean lowecase,
			boolean number, boolean specialChar, int length, boolean emailField) {
		ArrayList<String> testDataList = new ArrayList<>();
		testDataList.add(randomAlphaNumeric(uppercase, lowecase, number,
				specialChar, length - 1, emailField));
		testDataList.add(randomAlphaNumeric(uppercase, lowecase, number,
				specialChar, length, emailField));
		testDataList.add(randomAlphaNumeric(uppercase, lowecase, number,
				specialChar, length + 1, emailField));
		return testDataList;
	}

	/*
	 * Methods for Validating Image Position
	 */

	public void validateImagePosition(String objectProperty,
			String imgLocation, String imgName) {
		WebElement ele = null;
		try {
			ele = getElementByProperty(objectProperty);
			Point point = ele.getLocation();
			String[] imgsp = imgLocation.split("X");
			Xcoordinate = Integer.parseInt(imgsp[0]);
			Ycoordinate = Integer.parseInt(imgsp[1]);
			if (Xcoordinate.equals(point.x) && Ycoordinate.equals(point.y)) {
				System.out
						.println("------------------------------------------------------------------------------");
				System.out.println(imgName
						+ " Image Position :: X & Y coordinate value are Same");
			} else {
				System.out
						.println("------------------------------------------------------------------------------");
				System.out.println("Mismatch of " + imgName
						+ " Image Position X & Y coordinate");
				System.out.println("Actual X and Y Coordinate of Image "
						+ point.x + "," + point.y);
				System.out.println("Expected X and Y Coordinate of Image "
						+ Xcoordinate + "," + Ycoordinate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Methods for Validating Image Size
	 */

	public void validateImageSize(String objectProperty, String imgSize,
			String imgName) {
		WebElement ele = null;
		try {
			ele = getElementByProperty(objectProperty);
			Dimension dimensions = ele.getSize();
			String[] imgsize = imgSize.split("X");
			img_Width = Integer.parseInt(imgsize[0]);
			img_Height = Integer.parseInt(imgsize[1]);
			if (img_Width.equals(dimensions.width)
					&& img_Height.equals(dimensions.height)) {
				System.out.println(imgName
						+ " Image Width & Height value are Same");
			} else {
				System.out.println("Mismatch of " + imgName
						+ " Image Width & Height");
				System.out.println("Actual Width and Height of Image "
						+ dimensions.width + "," + dimensions.height);
				System.out.println("Expected Width and Height of Image "
						+ img_Width + "," + img_Height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Methods for Accessibility Validation
	 */

	public void accessibilityValidation(String objectProperty, String Text,
			String imgName) {
		try {
			WebElement ele = getElementByProperty(objectProperty);
			String alt = ele.getAttribute("alt");
			if (alt.equalsIgnoreCase(Text)) {
				System.out
						.println(imgName
								+ " Image accessibility expected and actual name are Same");
			} else {
				System.out
						.println(imgName
								+ " Image accessibility expected and actual name are not Same");
				System.out.println("Accessibility-Actual : " + alt);
				System.out.println("Accessibility-Expected : " + Text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Methods for Text Validation
	 */

	public void text_Validation(String objectProperty, String Text) {
		try {
			WebElement ele = getElementByProperty(objectProperty);
			String actualText = getText(ele);
			if (actualText.equalsIgnoreCase(Text)) {
				log.info(Text + " is Validated");
			} else {
				Assert.assertEquals(actualText, Text);
				log.info("Text expected and actual text are not Same\nActual Text : "
						+ actualText + "\nExpected Text : " + Text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void text_Contains(String objectProperty, String Text) {
		try {
			WebElement ele = getElementByProperty(objectProperty);
			String actualText = ele.getText();
			if (actualText.contains(Text)) {
				log.info(Text + " is Validated");
			} else {
				Assert.assertEquals(actualText, Text);
				log.info("Text expected and actual text are not Same\nActual Text : "
						+ actualText + "\nExpected Text : " + Text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void text_Contains(String objectProperty, List<String> Text) {
		try {
			WebElement ele = getElementByProperty(objectProperty);
			String actualText = ele.getText();
			if (Text.contains(actualText)) {
				log.info(Text + " is Validated");
			} else {
				Assert.assertEquals(actualText, Text);
				log.info("Text expected and actual text are not Same\nActual Text : "
						+ actualText + "\nExpected Text : " + Text);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void text_Assert(String actText, String expText) {
		try {
			if (actText.equalsIgnoreCase(expText)) {
				log.info(expText + " is Validated");
			} else {
				Assert.assertEquals(actText, expText);
				log.info("Text expected and actual text are not Same\nActual Text : "
						+ actText + "\nExpected Text : " + expText);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}

	/*
	 * Method for text compare using Assert
	 */

	public void text_validation_Assert(String objectProperty, String expText) {
		try {
			WebElement ele = getElementByProperty(objectProperty);
			String actualText = ele.getText();
			Assert.assertEquals(actualText, expText,
					"Text expected and actual text are not Same\nActual Text : "
							+ actualText + "\nExpected Text : " + expText);
			log.info(expText + " is Validated");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getText(String objectProperty) {
		String value = null;
		WebElement ele = getElementByProperty(objectProperty);
		value = ele.getText();
		if (value.equals("null")||value.isEmpty()) {
			value = ele.getAttribute("value");
		}
		return value;
	}
	
	public String getText(WebElement element) {
		String value = null;
		value = element.getText();
		if (value==null||value.isEmpty()) {
			value = element.getAttribute("value");
			if(value==null||value.isEmpty()){
				value = element.getAttribute("name");
			}
		}
		return value;
	}

	public String getAttribute(String objectProperty, String attribute) {
		String value = null;
		WebElement ele = getElementByProperty(objectProperty);
		value = ele.getAttribute(attribute);
		return value;
	}

	public WebElement getElementByProperty(String objectProperty) {
		WebElement ele = null;
		String propertyType = null;
		WebDriverWait browserWithElementWait = null;
		try {
			browserWithElementWait = new WebDriverWait(driver,
						config.getInt("elementWaitInSeconds"));
			propertyType = StringUtils.substringAfter(objectProperty, "~");
			objectProperty = StringUtils.substringBefore(objectProperty, "~");
			switch (propertyType.toUpperCase()) {
			case "CSS":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfElementLocated(By
								.cssSelector(objectProperty)));
				break;
			case "XPATH":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfElementLocated(By.xpath(objectProperty)));
				break;
			case "ID":
				if (deviceOS.equalsIgnoreCase("ios")) {
					ele = browserWithElementWait.until(ExpectedConditions
							.presenceOfElementLocated(ByAccessibilityId.id(objectProperty)));
				} else {
					ele = browserWithElementWait.until(ExpectedConditions
							.presenceOfElementLocated(By.id(objectProperty)));
				}
				break;
			case "NAME":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfElementLocated(By.name(objectProperty)));
				break;
			case "LINKTEXT":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfElementLocated(By.linkText(objectProperty)));
				break;
			default:
				ele = browserWithElementWait
						.until(ExpectedConditions.presenceOfElementLocated(By
								.className(objectProperty)));
				break;
			}
		} catch (Exception e) {

		}
		return ele;
	}

	/*
	 * 
	 */

	public List<WebElement> getElementsByProperty(String objectProperty) {
		List<WebElement> ele = null;
		String propertyType = null;
		WebDriverWait browserWithElementWait = null;
		try {
			browserWithElementWait = new WebDriverWait(driver,
						config.getInt("elementWaitInSeconds"));
			propertyType = StringUtils.substringAfter(objectProperty, "~");
			objectProperty = StringUtils.substringBefore(objectProperty, "~");
			switch (propertyType.toUpperCase()) {
			case "CSS":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.cssSelector(objectProperty)));
				break;
			case "XPATH":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.xpath(objectProperty)));
				break;
			case "ID":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By.id(objectProperty)));
				break;
			case "NAME":
				ele = browserWithElementWait
						.until(ExpectedConditions
								.presenceOfAllElementsLocatedBy(By
										.name(objectProperty)));
				break;
			case "LINKTEXT":
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.linkText(objectProperty)));
				break;
			default:
				ele = browserWithElementWait.until(ExpectedConditions
						.presenceOfAllElementsLocatedBy(By
								.className(objectProperty)));
				break;
			}
		} catch (Exception e) {

		}

		return ele;
	}

	/*
	 * Common Methods for Element Verification
	 */

	public boolean isElementPresentVerification(String objectProperty)
			throws Exception {
		boolean isElementPresent = false;
		WebElement ele = getElementByProperty(objectProperty);
		try {

			if (ele != null) {
				isElementPresent = true;
				t2 = System.currentTimeMillis();
			} else {				
				log.info("Element is not present : " + objectProperty);
				Assert.assertEquals(isElementPresent, true);
			}
			Thread.sleep(1000);
		} catch (Exception e) {

		}
		return isElementPresent;
	}

	public boolean isElementNotPresentVerification(String objectProperty)
			throws Exception {
		boolean isElementNotPresent = false;
		WebElement ele = getElementByProperty(objectProperty);
		try {
			if (ele == null) {
				isElementNotPresent = true;
			} else {
				t2 = System.currentTimeMillis();
			}
			Assert.assertEquals(isElementNotPresent, true,
					"Wrong Element is Displaying");
			log.info("Element is not present : " + objectProperty);
		} catch (Exception e) {

		}
		return isElementNotPresent;
	}

	/*
	 * Methods for Clear and Enter Text in Field
	 */

	public boolean clearAndEnterText(String objectProperty, String Text) {
		boolean isTextEnteredResult = false;
		try {
			if ("-".equals(Text)) {
				// ignore this field
				isTextEnteredResult = true;
			} else {
				WebElement textBox = getElementByProperty(objectProperty);
				textBox.clear();
				Thread.sleep(2000);
				textBox.sendKeys(Text);
				isTextEnteredResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTextEnteredResult;
	}

	/*
	 * Methods for Enter Text in Field
	 */

	public boolean enterText(String objectProperty, String Text) {
		boolean isTextEnteredResult = false;
		try {
			if ("-".equals(Text)) {
				// ignore this field
				isTextEnteredResult = true;
			} else {
				WebElement textBox = getElementByProperty(objectProperty);
				textBox.sendKeys(Text);
				isTextEnteredResult = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			;
		}
		return isTextEnteredResult;
	}

	/*
	 * Methods for Highlight the Elements
	 */
	public static void highlightElement(WebElement element, WebDriver webDriver) {
		for (int i = 0; i < 1; i++) {
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			js.executeScript(
					"arguments[0].setAttribute('style', arguments[1]);",
					element, "color: black; border: 3px solid black;");
		}
	}

	public boolean isElementPresentVerifyClick(String objectProperty) {
		boolean isVerifiedAndClicked = false;
		WebElement ele = null;
		try {
			ele = getElementByProperty(objectProperty);
			if (ele != null) {
				t1 = System.currentTimeMillis();
				ele.click();
				isVerifiedAndClicked = true;
			} else {
				throw new Exception("Object Couldn't be retrieved and clicked");
			}
		} catch (Exception e) {
			ele = null;
		}
		return isVerifiedAndClicked;
	}

	public boolean isElementPresentVerifyClick(WebElement clickElement) {
		boolean isVerifiedAndClicked = false;
		try {
			if (clickElement != null) {
				t1 = System.currentTimeMillis();
				clickElement.click();
				isVerifiedAndClicked = true;
			} else {
				throw new Exception("Object Couldn't be retrieved and clicked");
			}
		} catch (Exception e) {
			clickElement = null;
		}
		return isVerifiedAndClicked;
	}

	/*
	 * Methods for Scroll To Element
	 */

	public  void scrollTo(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", element);
	}

	/*
	 * Methods for Getting Text from XLS
	 */

	@SuppressWarnings("resource")
	public static String getXLSTestData(String FileName, String SheetName,
			String RowId, String column) throws IOException {

		String col1 = null;
		DataFormatter df = new DataFormatter();
		FileInputStream file = new FileInputStream(new File(
				System.getProperty("user.dir") + "/InputData" + File.separator
						+ FileName + ".xls"));
		HSSFWorkbook book = new HSSFWorkbook(file);
		HSSFSheet sheet = book.getSheet(SheetName);

		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int rowIterator = 1; rowIterator <= rowCount; rowIterator++) {
			String row = sheet.getRow(rowIterator).getCell(0)
					.getStringCellValue();
			if (row.equalsIgnoreCase(RowId)) {
				for (int colIterator = 1; colIterator < sheet.getRow(
						rowIterator).getLastCellNum(); colIterator++) {
					String col = sheet.getRow(0).getCell(colIterator)
							.getStringCellValue();
					if (col.equalsIgnoreCase(column)) {
						Cell cellvalue = sheet.getRow(rowIterator).getCell(
								colIterator);
						col1 = df.formatCellValue(cellvalue);
						break;
					}
				}
			}
		}
		return col1;
	}

	public void scrollToElement(String objectProperty) {
		String propertyType = null;
		propertyType = StringUtils.substringAfter(objectProperty, "~");
		objectProperty = StringUtils.substringBefore(objectProperty, "~");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> data = new HashMap<String, String>();
		switch (propertyType.toUpperCase()) {
		case "CSS":
			data.put("element", ((RemoteWebElement) driver.findElement(By
					.cssSelector(objectProperty))).getId());
			break;
		case "XPATH":
			data.put("element", ((RemoteWebElement) driver.findElement(By
					.xpath(objectProperty))).getId());
			break;
		case "ID":
			data.put("element", ((RemoteWebElement) driver.findElement(By
					.id(objectProperty))).getId());
			break;
		case "NAME":
			data.put("element", ((RemoteWebElement) driver.findElement(By
					.name(objectProperty))).getId());
			break;
		case "LINKTEXT":
			data.put("element", ((RemoteWebElement) driver.findElement(By
					.linkText(objectProperty))).getId());
			break;
		default:
			data.put("element", ((RemoteWebElement) driver.findElement(By
					.className(objectProperty))).getId());
			break;
		}
		data.put("direction", "down");
		js.executeScript("mobile: scroll", data);

	}

	public boolean isChecked(String objectProperty, String message) {
		boolean value = false;
		if (System.getProperty("deviceOS").equalsIgnoreCase("ios")) {
			String actual = getText(objectProperty);
			Assert.assertEquals(actual, "selectedCheckBox");
			value = true;
		} else {
			String actual = getAttribute(objectProperty, "checked");
			Assert.assertEquals(actual, "true");
			value = true;
		}
		log.info(message + " check box checked");
		return value;
	}

	public boolean isUnChecked(String objectProperty, String message) {
		boolean value = false;
		if (System.getProperty("deviceOS").equalsIgnoreCase("ios")) {
			String actual = getText(objectProperty);
			Assert.assertEquals(actual, "unSelectedCheckBox");
			value = true;
		} else {
			String actual = getAttribute(objectProperty, "checked");
			Assert.assertEquals(actual, "false");
			value = true;
		}
		log.info(message + " check box Unchecked");
		return value;
	}

//	public void relaunchTheApp() {
//		driver.closeApp();
//		System.out.println("App Closed");
//		driver.launchApp();
//		System.out.println("App ReLaunched");
//	}
	//k1
	public boolean verifyHyperLink(String objectProperty)
			throws Exception {
		boolean isElementPresent = false;
		WebElement ele = getElementByProperty(objectProperty);
		try {

			if("a".equals(ele.getTagName())){
				isElementPresent = true;
				
			} else {
				log.info("Element is not present : " + objectProperty);
			}
			Thread.sleep(1000);
		} catch (Exception e) {

		}
		return isElementPresent;
	}
	
	
		public String clearField(String objectProperty1){
			String value = "";
			WebElement ele = getElementByProperty(objectProperty1);
			value = ele.getText();
			try {
				if (!value.equals("null")||!value.isEmpty()) {
					ele.clear();
				}else {
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
			return value;
		}
		
		public boolean inputFieldValidation(String objectProperty) throws Exception {
			boolean isElementPresent = false;
			WebElement ele = getElementByProperty(objectProperty);
			try {
				if ("input".equals(ele.getTagName())) {
					isElementPresent = true;
					log.info(ele.getTagName()+" : TagName is present");
				} else {
					log.info("Element is not present : " + objectProperty);
				}
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return isElementPresent;
		}

		public void stringLengthCheck1(String objectProperty, int leng) {
			WebElement ele = getElementByProperty(objectProperty);
			log.info("element of the object is : " + ele);
			try {
				String text = getText(objectProperty);
				log.info("String Length is: " + text.trim());
				if (text.length() == leng) {
					log.info("String Length is not ZERO : " + leng);
				} else {
					log.info("String Length is ZERO : " + objectProperty);
				}
			} catch (NoSuchElementException e) {
				log.info("Element Path Not Found : " + objectProperty);
			}
		}

}
