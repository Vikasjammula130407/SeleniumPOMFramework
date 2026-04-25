package com.org.opencart.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.org.opencart.qa.utilies.AppConstants;
import com.org.opencart.qa.utilies.ElementUtilies;

public class SearchResultsPage 
{
	//1.Initial the driver and ElementUtilies
	
	   private WebDriver driver;
	   private ElementUtilies eleutil;
	   

	//2.Create constructor of class with parameter
	   
	   public SearchResultsPage(WebDriver driver)
	   {
		   this.driver=driver;
		   eleutil = new ElementUtilies(driver);
	   }
	  
	 //3.Private final By Locators
	 private final By SearchResultsCount = By.cssSelector("div.product-thumb");
	 
	 //4. public page actions/methods
	 public int getSearchResultsCount()
	 {
		 int resultCount=eleutil.waitForAllElementsPresence(SearchResultsCount, AppConstants.MEDIUM_TIME_OUT).size();
		 System.out.println("Total number of products count for search cert is "+resultCount);
		 return resultCount;
	 }
	 public ProductInformationPage SelectProduct(String ProductName)
	 {
		 System.out.println("Select Product Name:" + ProductName);
		 eleutil.doClick(By.linkText(ProductName));
		 return new ProductInformationPage(driver);
	 }
}
