package com.org.opencart.qa.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.org.opencart.qa.base.BaseTest;
import com.org.opencart.qa.utilies.AppConstants;

public class HomePageTest extends BaseTest
{
	@BeforeClass
	public void homePageSetUp()
	{
		homepage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	@Test
	public void getHomePageTittleTest() 
	{
		String actTittle=homepage.getHomePageTittle();
		Assert.assertEquals(actTittle, AppConstants.HOME_PAGE_TITTLE);
	}
	
	@Test
	public void logoutLinkExist()
	{
		
		Assert.assertTrue(homepage.isLogOutLinkExist());	
	}
	
	@Test
	public void headerVerification()
	{
		List<String>actHeaders=homepage.getHomePageHeaders();
		Assert.assertEquals(actHeaders,AppConstants.EXPECEPED_HEADER_LIST);
	}
	
	@DataProvider
	public Object[][] getSearchTestData()
	{
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"canon",1},
			{"samsung",2},
			{"vikas",0}
		};
	}
	
	@Test(dataProvider="getSearchTestData")
	public void doSearchTest(String SearchKey,int expResultCount)
	{
		searchresultpage=homepage.doSearch(SearchKey);
		Assert.assertEquals(searchresultpage.getSearchResultsCount(),expResultCount);
	}
	

}
