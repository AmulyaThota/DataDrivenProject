package com.w2a.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;

public class LoginAsBankManagerTest extends TestBase {

	@Test
	public void loginAsBankManagerTest() throws InterruptedException, IOException {

		verifyEquals("abc", "xyz");
		Thread.sleep(3000);
		log.debug("inside Login Test");
		click("bmlbtn_XPATH");
		Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("addcustbtn_CSS"))));
		log.debug("Login Successfully executed");
		
		Reporter.log("Login sucessfully executed");
		//Thread.sleep(3000);
		//Assert.fail("Login not sucessfull");

		Thread.sleep(3000);
		
		
	}

}
