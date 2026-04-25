package com.org.opencart.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.org.opencart.qa.utilies.AppConstants;
import com.org.opencart.qa.utilies.ElementUtilies;

public class HomePage 
{

	//1.Initial the driver and ElementUtilies
	
	   private WebDriver driver;
	   private ElementUtilies eleutil;
	   

	//2.Create constructor of class with parameter
	   
	   public HomePage(WebDriver driver)
	   {
		   this.driver=driver;
		   eleutil = new ElementUtilies(driver);
	   }
	  
	 //3.Private final By Locators
	   private final By logoutLink = By.linkText("Logout");
	   private final By headers = By.cssSelector("div#content h2");
	   private final By searchTextField = By.name("search");
	   private final By searchSymbol = By.cssSelector("div#search button");
	   
	 //4. Public page actions/methods
	   
	  public boolean isLogOutLinkExist()
	  {
		 return  eleutil.isElementDisplayed(logoutLink);
	  }
	  
	  public String getHomePageTittle() 
	  {
		 String actTittle = eleutil.waitForTitleIs(AppConstants.HOME_PAGE_TITTLE, AppConstants.SHORT_TIME_OUT);
		 System.out.println("Home Page Tittle is "+ actTittle);
		 return actTittle;
	  }
	  
	  public List<String> getHomePageHeaders()
	  {
		  List<WebElement> headersList=eleutil.waitForAllElementsPresence(headers, AppConstants.SHORT_TIME_OUT);
		  List<String> headerValueList = new ArrayList<String>();
		  
		  for(WebElement e:headersList)
		  {
			  String text=e.getText();
			  headerValueList.add(text);
			  
		  }
		  return headerValueList;
	  }
	  
	  public SearchResultsPage doSearch(String SearchKey)
	  {
		  System.out.println("Searching Product is "+SearchKey);
		  eleutil.doSendKeys(searchTextField, SearchKey, AppConstants.SHORT_TIME_OUT);
		  eleutil.doClick(searchSymbol);
		 return  new SearchResultsPage(driver);
	  }
	   
		
}
