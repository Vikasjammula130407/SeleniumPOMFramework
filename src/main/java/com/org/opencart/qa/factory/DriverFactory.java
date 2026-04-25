package com.org.opencart.qa.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.org.opencart.qa.exception.BrowserException;
import com.org.opencart.qa.exception.FrameworkException;

public class DriverFactory
{
  WebDriver driver;
  Properties prop;
  OptionsManager optionsManager;
  public static String highlight;
  public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
  
  public WebDriver initDriver(Properties prop)
  {
	  String browserName=prop.getProperty("browser");
	  System.out.println("The browserName entered is "+browserName);
	  highlight =prop.getProperty("highlight");
	  optionsManager = new OptionsManager(prop);
	  switch(browserName.trim().toLowerCase())
	  {
	  case "chrome" :
	     tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		 // driver = new ChromeDriver(optionsManager.getChromeOptions());
		  break;
	  case "firefox" :
		  tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		  //driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
		  break;
	  case "edge" :
		  tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
		  //driver = new EdgeDriver(optionsManager.getEdgeOptions());
		  break;
	 default :
	    System.out.println("Browser Name which you entered is not valid");
	    throw new BrowserException("Invalid BrowserName");
	  
	  }
	  getDriver().manage().deleteAllCookies();
	  getDriver().manage().window().maximize();
	  getDriver().get(prop.getProperty("url"));
	  
	  return getDriver();
  }
  
  /**
   * This will return one local copy of driver for specific thread
   * @return
   */
  public static WebDriver getDriver()
  {
	  return tlDriver.get();
  }
  /**
   * This method will intizaties the properties
   * @return
   */
  
  public Properties initProp()
  {
	  FileInputStream ip = null;
	  prop=new Properties();
	  String envName = System.getProperty("env");
	  System.out.println("Env Name is " +envName);
	 try
	 {
	  if(envName==null)
	  {
		  System.out.println("As Env Name is NULL so we are running test cases in default envinorment that is QA");
		  ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
	  }
	  else
	  {
		  switch (envName.trim().toLowerCase())
		  {
		  case "qa":
			  ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			  break;
		  case "dev" :
			  ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
			  break;
		  case "prod" :
			  ip = new FileInputStream("./src/test/resources/config/config.properties");
			  break;
		   default :
			   System.out.println("========Invalid Env Name Entered======= "+envName);
			   throw new FrameworkException("Invalid Env Name Entered "+envName);
		  }
	  }
	 }
	 catch (FileNotFoundException e) 
	  {
		e.printStackTrace();
	  } 
	  
	  try
	  {
		prop.load(ip);
	  }
	  catch (IOException e) 
	  {
		e.printStackTrace();
	  }
	return prop;
  }
  
  /**
   * Used for taking screenshot
   */
  
  public static File getScreenshotFile()
  {
	  return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
	  
  }
  
  public static byte[] getScreenshotByte()
  {
	  return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
  }
  
  public static String getScreenshotBase64()
  {
	  return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);
  }
}
