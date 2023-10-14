package Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ApplicationListners implements	ITestListener {
	public void onTestStart(ITestResult result) {
		System.out.println("TestCase Started Time   :-  "+ApplicationListners.DateFormat());
		System.out.println("On Test Start  :"+result.getName());
		Reporter.log("Test Started Time   :-  "+ApplicationListners.DateFormat());
		Reporter.log("On Test Start  :"+result.getName());
	}
	public void onTestSuccess(ITestResult result) {
		System.out.println("TestCase Success Time   :-  "+ApplicationListners.DateFormat());
		System.out.println("On Test Success  :"+result.getName());
		Reporter.log("Test Started Time   :-  "+ApplicationListners.DateFormat());
		Reporter.log("On Test Success  :"+result.getName());
	}
	public void onTestFailure(ITestResult result) {
		System.out.println("On Test Failure  :"+result.getName());
		Reporter.log("On Test Failure  :"+result.getName());
	}
	public void onTestSkipped(ITestResult result) {
		System.out.println("On Test Skipped  :"+result.getName());
		Reporter.log("On Test Skipped  :"+result.getName());
		
	}
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("On Test Fail Within Success  :"+result.getName());
		Reporter.log("On Test Fail Within Success  :"+result.getName());
		
	}
	public void onStart(ITestContext context) {
		System.out.println("*****ON START *****");
		System.out.println("Test Start Time   :-  "+ApplicationListners.DateFormat());
		Reporter.log("*****ON START ******");
		Reporter.log("Test Started Time   :-  "+ApplicationListners.DateFormat());
	}
	public void onFinish(ITestContext context) {
		System.out.println("*****ON FINISH *****");
		System.out.println("Test Finish Time   :-  "+ApplicationListners.DateFormat());
		Reporter.log("*****ON FINISH ******");
		Reporter.log("Test Finish Time   :-  "+ApplicationListners.DateFormat());		
	}
	
	public static String DateFormat() {
		Date dt=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MMM-dd hh-mm-ss");
		return sdf.format(dt);		
	}

}
