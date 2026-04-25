package com.org.opencart.qa.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.org.opencart.qa.utilies.AppConstants;
import com.org.opencart.qa.utilies.ElementUtilies;

public class LoginPage 

{
	//1.Initial the driver and ElementUtilies
	
   private WebDriver driver;
   private ElementUtilies eleutil;
   
   //2.Create constructor of class with parameter
   
   public LoginPage(WebDriver driver)
   {
	   this.driver=driver;
	   eleutil = new ElementUtilies(driver);
   }
	
   
  //3.Create Private By locators
   
   private final By email = By.id("input-email");
   private final By Password = By.id("input-password");
   private final By forgotPassword = By.linkText("Forgotten Password");
   private final By lgnBtn = By.cssSelector("input[type='submit']");
   private final By regesiterLink = By.linkText("Register");
   //4. Create action/methods for page
   public String getLoginPageTittle()
   {
	  String actTitle= eleutil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITTLE, AppConstants.SHORT_TIME_OUT);
	  System.out.println("The tittle of current page is " +actTitle);
	  return actTitle;
	  //Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITTLE);
   }
   public String getLoginPageUrl()
   {
	  String currentPageUrl=eleutil.waitForURLContains(AppConstants.LOGIN_PAGE_URL,AppConstants.SHORT_TIME_OUT);
	  System.out.println("The current page url is " +currentPageUrl);
	  return currentPageUrl;
	   
   }
   public boolean isForgotPasswordLinkDisplaying()
   {
	   return eleutil.waitForElementVisible(forgotPassword, AppConstants.SHORT_TIME_OUT).isDisplayed();
   }
   public HomePage doLogin(String username,String password)
   {
	   eleutil.doSendKeys(email, username, AppConstants.SHORT_TIME_OUT);
	   eleutil.doSendKeys(Password, password);
	   eleutil.doClick(lgnBtn);
	   System.out.println(driver.getTitle());
	   return new HomePage(driver);
	   
   }
   
   public RegisterPage navigateToRegister()
   {
	   eleutil.doClick(regesiterLink);
	   return new RegisterPage(driver);
	   
   }
}
