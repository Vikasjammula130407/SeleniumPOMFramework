package com.org.opencart.qa.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.org.opencart.qa.base.BaseTest;

public class ProductInformationPageTest extends BaseTest 
{

	@BeforeClass
	public void productInformationSetUp()
	{
		homepage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	@DataProvider
	public Object[][] getProductData()
	{
		return new Object[][] {
			{"macbook","MacBook"},
			{"macbook", "MacBook Air"},
			{"macbook","MacBook Pro"},
			{"iMac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
			{"canon","Canon EOS 5D"}
		};
	}
	@Test(dataProvider="getProductData")
	public void productHeaderTest(String SearchKey,String ProductName)
	{
		searchresultpage=homepage.doSearch(SearchKey);
		productinformationpage=searchresultpage.SelectProduct(ProductName);
		Assert.assertEquals(productinformationpage.getProductHeader(),ProductName);
	}
	@DataProvider
	public Object[][] getProductDataCount()
	{
		return new Object[][] {
			{"macbook","MacBook",5},
			{"macbook", "MacBook Air",4},
			{"macbook","MacBook Pro",4},
			{"iMac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
			{"canon","Canon EOS 5D",3}
		};
	}
	
	@Test(dataProvider="getProductDataCount")
	public void productImagesCountTest(String SearchKey,String ProductName,int ImageCount)
	{
		searchresultpage=homepage.doSearch(SearchKey);
		productinformationpage=searchresultpage.SelectProduct(ProductName);
		Assert.assertEquals(productinformationpage.getProductImagesCount(),ImageCount);
	}
	
	@Test
	public void productInfoDataTest()
	{
		searchresultpage=homepage.doSearch("macbook");
		productinformationpage=searchresultpage.SelectProduct("MacBook Pro");
		Map<String,Object> productInfoDataMap=productinformationpage.getProductInfoData();
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(productInfoDataMap.get("productname"),"MacBook Pro");
		softAssert.assertEquals(productInfoDataMap.get("imagescount"),5);
		
		softAssert.assertEquals(productInfoDataMap.get("Brand"),"Apple");
		softAssert.assertEquals(productInfoDataMap.get("Product Code"),"Product 18");
		softAssert.assertEquals(productInfoDataMap.get("Reward Points"),"800");
		softAssert.assertEquals(productInfoDataMap.get("Availability"),"Out Of Stock");
		
		softAssert.assertEquals(productInfoDataMap.get("productprice"),"$2,000.00");
		softAssert.assertEquals(productInfoDataMap.get("extaxprice"),"$2,000.00");
		softAssert.assertAll();
	}
	
}
