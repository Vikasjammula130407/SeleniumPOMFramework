package com.org.opencart.qa.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;import org.testng.annotations.Parameters;
import com.aventstack.chaintest.plugins.ChainTestListener;
import com.org.opencart.qa.factory.DriverFactory;
import com.org.opencart.qa.listeners.TestAllureListener;
import com.org.opencart.qa.pages.HomePage;
import com.org.opencart.qa.pages.LoginPage;
import com.org.opencart.qa.pages.ProductInformationPage;
import com.org.opencart.qa.pages.RegisterPage;
import com.org.opencart.qa.pages.SearchResultsPage;

@Listeners({ChainTestListener.class,TestAllureListener.class})
public class BaseTest 
{
	protected WebDriver driver;
	DriverFactory df;
	protected Properties prop;
	protected LoginPage loginpage;
	protected HomePage homepage;
	protected SearchResultsPage searchresultpage;
	protected ProductInformationPage productinformationpage;
	protected RegisterPage registerpage;
	
  @Parameters({"browser"})
  @BeforeTest
  public void setUp(@Optional("chrome") String browserName)
{
	df=new DriverFactory();
	prop = df.initProp();
	if(browserName!=null)
	{
		prop.setProperty("browser", browserName);
	}
	driver =df.initDriver(prop);
	loginpage= new LoginPage(driver);
	
}
  @AfterMethod
  public void attachScreenshot(ITestResult result)
  {
	  if(!result.isSuccess())//only if test case is failure this condition will be pass
	 {
		  ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
	 }
	  
	  //If you wanted to take screenshot every time irrespective to pass or fail uncomment below method
	  
	  //ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
  }
  
  @AfterTest
  public void tearDown()
  {
	  driver.quit();
  }
	
}
