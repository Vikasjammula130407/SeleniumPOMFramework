package com.org.opencart.qa.pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.org.opencart.qa.utilies.AppConstants;
import com.org.opencart.qa.utilies.ElementUtilies;

public class ProductInformationPage 
{
	private WebDriver driver;
	   private ElementUtilies eleutil;
	   private HashMap<String,Object> productMap;
	   

	//2.Create constructor of class with parameter
	   
	   public ProductInformationPage(WebDriver driver)
	   {
		   this.driver=driver;
		   eleutil = new ElementUtilies(driver);
	   }
	private final By header = By.cssSelector("div#content h1");
	private final By images = By.cssSelector("ul.thumbnails img");
	private final By quantity = By.name("quantity");
	private final By addToCart = By.id("buttoncart");
	private final By metaData = By.xpath("(//div[@id='content']//ul [@class= 'list-unstyled'])[1]//li");
	private final By pricingData = By.xpath("(//div[@id='content']//ul [@class= 'list-unstyled'])[2]//li");
	
	public String getProductHeader()
	{
		return eleutil.doElementGetText(header);
	}
	
	public int getProductImagesCount()
	{
		int ImagesCount = eleutil.waitForAllElementsVisible(images, AppConstants.SHORT_TIME_OUT).size();
		System.out.println("Total number of images avaliable on page is " +ImagesCount);
		return ImagesCount;
		
	}
	private void getProductMetaData()
	{
		List<WebElement>metaDataList=eleutil.getElements(metaData);
		for(WebElement e:metaDataList)
		{
			String metaText=e.getText();
			String meta[]=metaText.split(":");
			String metaKey=meta[0].trim();
			Object metaValue=meta[1].trim();
			productMap.put(metaKey, metaValue);
			System.out.println(productMap);
		}
	}
	private void getProductPriceData()
	{
		List<WebElement>metaPriceList=eleutil.getElements(pricingData);
		String productPrice=metaPriceList.get(0).getText().trim();
		String productExTaxPrice=metaPriceList.get(1).getText().split(":")[1].trim();
		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", productExTaxPrice);
		System.out.println(productMap);
		
	}
	public HashMap<String,Object> getProductInfoData()
	{
		productMap= new HashMap<String,Object>();
		productMap.put("productname", getProductHeader());
		productMap.put("imagescount", getProductImagesCount());
		getProductMetaData();
		getProductPriceData();
		return productMap;
	}
	
	
}
