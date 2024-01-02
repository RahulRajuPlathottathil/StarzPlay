package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import reporting.ExpectedValues;

public class BaseTest {
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest childTest;
	static int count=0;
	public static String  methodName;
	@BeforeSuite
	public void setUp() {
		extent= new ExtentReports();
        
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReport//"+ExpectedValues.filename+".html");
        extent.attachReporter(htmlReporter);
             
		
	}
	@AfterSuite
	
		public void Done() {
			 extent.flush();
		}
	

	public static void main(String[] args) {
		//setUp();
	}
	
	@AfterSuite
	public void tearDown() {
		
	}

}
