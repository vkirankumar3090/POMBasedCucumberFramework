package com.Objects;

import com.Utilities.BaseStep;

public class LoginTestObjects extends BaseStep {

	public final String logo = objectProperty(
			"//img[@class=\"header__content__logo\"]~xpath", "", "");
	public final String help_txt = objectProperty(
			"//span[text()=\"Help\"]~xpath", "", "");
	public final String login_header = objectProperty(
			"//h2[@class='login__header']~xpath", "", "");
	public final String logoimage = objectProperty(
			"//img[@class=\"header__content__logo\"]~xpath", "", "");
	public final String xbutton = objectProperty(
			"//button[@class=\"header__alert__close\"]~xpath", "", "");
	public final String widgetheadingmsg = objectProperty(
			"(//h2[text()=\"Welcome!\"])[3]~xpath", "", "");
	public final String username = objectProperty(
			"(//input[@class=\"text-field__input\"])[5]~xpath", "", "");
	public final String password = objectProperty(
			"(//input[@class=\"text-field__input\"])[6]~xpath", "", "");
	public final String forget_pwd_link = objectProperty(
			"(//p[@class=\"login__actions__text\"])[3]~xpath", "", "");
	public final String loginbutton = objectProperty(
			"(//button[@class=\"btn login__actions__login\"])[3]~xpath", "", "");
	public final String patient_prtl_text = objectProperty(
			"//a[@class=\"login__footer\"]~xpath", "", "");
	public final String newusertext = objectProperty(
			"//h2[text()=\"New User?\"]~xpath", "", "");

}
