package com.w2a.testcases;

import java.util.Hashtable;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utils.ExcelReader;
import com.w2a.utils.TestUtil;

public class AddCustomerTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {
		/*
		 * if(runmode.equals("Y")) { throw new
		 * SkipException("skipping the testcase as the runmode for data set is NO "); }
		 */
		
	click("addcustbtn_CSS");	
	type("firstname_CSS",data.get("firstname"));
	type("lastname_CSS",data.get("lastname"));
	type("postcode_CSS",data.get("postcode"));
	click("addbtn_CSS");	
		
	Thread.sleep(3000);
	Alert alert = wait.until(ExpectedConditions.alertIsPresent());	
	
	Assert.assertTrue(alert.getText().contains(data.get("alerttext")));
	
	
	alert.accept();
	//Assert.fail("Customer data not added sucessfully");
	
	Thread.sleep(3000);	
	}
	
	

	  
}