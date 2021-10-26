package com.w2a.listeners; 

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.ScreenCapture;
import com.w2a.base.TestBase;
import com.w2a.utils.TestUtil;

public class CustomListeners extends TestBase implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		test = rep.startTest(result.getName().toUpperCase());
		
		//runmodes-Y
System.out.println(TestUtil.isTestRunnable(result.getName(), excel));
		
/*
 * if(!TestUtil.isTestRunnable(result.getName(), excel)) { throw new
 * SkipException("skipping the test"+result.getName().toUpperCase()
 * +"as the runmode is NO");
 */
			
			
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		TestBase.test.log(LogStatus.PASS, result.getName().toUpperCase()+"PASS");
		TestBase.test.log(LogStatus.PASS, test.addScreenCapture(TestUtil.screenshotName));
		rep.endTest(test);
		rep.flush();
	
	}

	@Override
	public void onTestFailure(ITestResult result) {
	
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			TestUtil.caturescreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TestBase.test.log(LogStatus.FAIL, result.getName().toUpperCase()+"Failed with exception: "+result.getThrowable());
		TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		Reporter.log("Capturing screenshot");
		Reporter.log("<a  target=\"_blank\"  href="+TestUtil.screenshotName+">Screenshot</a>");
		
		rep.endTest(test);
		rep.flush();
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	

	}


