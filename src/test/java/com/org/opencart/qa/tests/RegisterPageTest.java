package com.org.opencart.qa.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.org.opencart.qa.base.BaseTest;
import com.org.opencart.qa.utilies.AppConstants;
import com.org.opencart.qa.utilies.CsvUtil;
import com.org.opencart.qa.utilies.ExcelUtil;

public class RegisterPageTest extends BaseTest
{

	@BeforeClass
	public void regSetUp()
	{
		registerpage = loginpage.navigateToRegister();
	}
	
	@DataProvider
	public Object[][] getUserRegTestData()
	{
		return new Object[][] {
			{"Jammula","Vikas","9704934646","Secure*12345","yes"},
			{"Haritha","Garapati","9502420201","Welcome@123","yes"},
			{"Mokshith","Sri Sai","6305164436","Secure*1234","yes"}
		};
	}
	
	@DataProvider
	public Object[][] getUserRegExcelTestData()
	{
		return ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@DataProvider
	public Object[][] getUserRegCsvTestData()
	{
		return CsvUtil.csvData(AppConstants.REGISTER_SHEET_NAME_CSV);
	}
	@Test(dataProvider="getUserRegCsvTestData")
	public void userRegisterTest(String firstName, String lastName,String telephone,String password,String subscribe)
	{
		Assert.assertTrue(registerpage.userRegisteration(firstName,lastName,telephone,password,subscribe));
	}
}
