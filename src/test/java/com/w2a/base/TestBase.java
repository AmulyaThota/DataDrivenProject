package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utils.ExcelReader;
import com.w2a.utils.ExtentManager;
import com.w2a.utils.TestUtil;


public class TestBase {
	

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties or = new Properties();
	public static FileInputStream fis;
	public static WebDriverWait wait;
	
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() {
		
		if (driver == null) {
			
			//PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\log4j.properties");

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream(
						System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\or.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				or.load(fis);
				log.debug("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

			if (config.getProperty("browser").equals("firefox")) {

				System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
				driver = new FirefoxDriver();

			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
		
			} 

			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicitwait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
		}

	}
	

	
	public void click(String locator) {
		if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		}else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		}else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(or.getProperty(locator))).click();
		}else if (locator.endsWith("_NAME")) {
			driver.findElement(By.name(or.getProperty(locator))).click();
		}else if (locator.endsWith("_LINKTEST")) {
			driver.findElement(By.linkText(or.getProperty(locator))).click();
		}else if (locator.endsWith("_PARTIALLINKTEST")) {
			driver.findElement(By.partialLinkText(or.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "clicking on: "+ locator);
	}
	
	public void type(String locator, String value) {
		if(locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
			
			}else if (locator.endsWith("_XPATH")) {
				driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_ID")) {
				driver.findElement(By.id(or.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_NAME")) {
				driver.findElement(By.name(or.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_LINKTEST")) {
				driver.findElement(By.linkText(or.getProperty(locator))).sendKeys(value);
			}else if (locator.endsWith("_PARTIALLINKTEST")) {
				driver.findElement(By.partialLinkText(or.getProperty(locator))).sendKeys(value);
			}
		
		test.log(LogStatus.INFO, "typing  in: "+locator+" entered values as "+value);
	}
	
	static WebElement dropdown;
	
	public void select(String locator, String value) {
		
		if(locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
			
			}else if (locator.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(or.getProperty(locator)));
			}else if (locator.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(or.getProperty(locator)));
			}else if (locator.endsWith("_NAME")) {
				dropdown = driver.findElement(By.name(or.getProperty(locator)));
			}else if (locator.endsWith("_LINKTEST")) {
				dropdown = driver.findElement(By.linkText(or.getProperty(locator)));
			}else if (locator.endsWith("_PARTIALLINKTEST")) {
				dropdown = driver.findElement(By.partialLinkText(or.getProperty(locator)));
			}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "selecting of ddropdown: "+locator+" value as "+value);
	}
	
	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			
			return true;
			
		}catch (NoSuchElementException e) {
			
			return false;
			
		}
		
		
		
		}
	
	public static void verifyEquals(String expected,String actual) throws IOException {
		
		try {
			Assert.assertEquals(actual, expected);
			
		}catch (Throwable t) {
		TestUtil.caturescreenshot();
		//testng report
		Reporter.log("<br>"+"verification failure: "+t.getMessage()+"<br>");
		Reporter.log("<a  target=\"_blank\"  href="+TestUtil.screenshotName+"><image src ="+TestUtil.screenshotName+"height=200 width =200></img></a>");
		Reporter.log("<br>");
		Reporter.log("<br>");
		
		//extentreport
		
		TestBase.test.log(LogStatus.FAIL, "Verification Failed with exception: "+t.getMessage());
		TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		}
		

		
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
		
		log.debug("test execution completed !!!");
	}

}
