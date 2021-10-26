package com.w2a.testcases;

import java.util.Hashtable;

import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.base.TestBase;
import com.w2a.utils.ExcelReader;
import com.w2a.utils.TestUtil;

public class OpenAccountTest extends TestBase {

	@Test(dataProviderClass=TestUtil.class,dataProvider="dp")
	public void openAccountTest(Hashtable<String, String>data) throws InterruptedException {
		
		
	click("openaccount_CSS");
	select("customername_CSS", data.get("customer"));
	select("currency_CSS", data.get("currency"));
	click("process_CSS");
	
	Thread.sleep(3000);
Alert alert = wait.until(ExpectedConditions.alertIsPresent());	
	
	Assert.assertTrue(alert.getText().contains(data.get("alertinopenact")));
	
	
	alert.accept();
		
	}
	
	
   
		
	   
   

}