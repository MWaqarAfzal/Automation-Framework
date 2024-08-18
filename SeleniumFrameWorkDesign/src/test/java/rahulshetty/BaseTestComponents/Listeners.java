package rahulshetty.BaseTestComponents;

import java.io.File;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import rahulshetty.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{

	ExtentReports extent = ExtentReporterNG.getReportObject();
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	@Override
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getMethod().getMethodName());
		//	Assign the Unique thread id to the test
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) 
	{
	    test.log(Status.PASS, "Test Case is passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) 
	{
		//		This will return the error message due to which the test case is fail
		//		test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
		
		
		//		Now call the base class Screenshot Method
		String screenshotPath = null;
		try {
			screenshotPath = getScreenShots(result.getMethod().getMethodName(), driver);
			} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//		Pass that screenshot path to the Report
		//		test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());

	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
	    // not implemented
	  }
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }
	
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }
	
	@Override
	public void onStart(ITestContext context) {
	    // not implemented
	  }
	
	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	  }
}
