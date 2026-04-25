package com.org.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.opencart.qa.base.BaseTest;
import com.org.opencart.qa.utilies.AppConstants;

public class LoginPageTest extends BaseTest
{

	@Test
	public void loginPageTittleTest()
	{
		Assert.assertEquals(loginpage.getLoginPageTittle(), AppConstants.LOGIN_PAGE_TITTLE);
			
	}
	@Test
	public void loginPageCurrentUrlTest()
	{
		Assert.assertTrue(loginpage.getLoginPageUrl().contains(AppConstants.LOGIN_PAGE_URL));
		
		
	}
	@Test
	public void isForgotPasswordLinkDisplayingTest()
	{
		Assert.assertTrue(loginpage.isForgotPasswordLinkDisplaying());
	}
	@Test(priority=Integer.MAX_VALUE)
	public void doLoginPageTest()
	{
		homepage=loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(homepage.getHomePageTittle(), AppConstants.HOME_PAGE_TITTLE);
	}
}
