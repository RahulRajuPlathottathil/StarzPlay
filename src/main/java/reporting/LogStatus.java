package reporting;

import java.util.List;

import com.aventstack.extentreports.Status;

import base.BaseTest;

public class LogStatus {

	public static void verify(String Field,String Expected, String Actual) {
		String message = String.format("<label>Expected : %s<label></br> <label>Actual : %s</label> %s</label></br>" , Expected,Actual);
		Status status;
		if(Expected.equals(Actual)) 
			 status = Status.PASS;
		else
			status =Status.FAIL;
		
		BaseTest.test.log(status, message);
		

	}

	public static void Verfiy(String Field,int Expected, int Actual) {

	}

	public static void Verify(String Field,List<String> Expected, List<String> Actual) {
		String message = String.format("<b>Field Validation - %s<b> </br><label>Allowed Values : %s<label></br> <label>Actual : %s</label></br>" ,Field, Expected,Actual);
		Status status;
		if(Expected.containsAll(Actual)) 
			 status = Status.PASS;
		else
			status =Status.FAIL;
		
		BaseTest.test.log(status, message);
	}
	public static void Verify(String Field,long Expected, long Actual) {
		String message = String.format("<b>Field Validation - %s<b> </br><label>Allowed Values : %s<label></br> <label>Actual : %s</label></br>" ,Field, Expected,Actual);
		Status status;
		if(Expected==Actual) 
			 status = Status.PASS;
		else
			status =Status.FAIL;
		
		BaseTest.test.log(status, message);
	}
	public static void VerifyActalGreaterThanOrEqualsExpected(String Field,int Expected, int Actual) {
		String message = String.format("<b>Field Validation - %s<b> </br><label>Allowed Values : %s<label></br> <label>Actual : %s</label></br>" ,Field, Expected,Actual);
		Status status;
		if(Expected<=Actual) 
			 status = Status.PASS;
		else
			status =Status.FAIL;
		
		BaseTest.test.log(status, message);
	}
	public static void Verify(String Field,List<Integer> Expected, int Actual) {
		String message = String.format("<b>Field Validation - %s<b> </br><label>Allowed Values : %s<label></br> <label>Actual : %s</label></br>" ,Field, Expected,Actual);
		Status status;
		if(Expected.contains(Actual)) 
			 status = Status.PASS;
		else
			status =Status.FAIL;
		
		BaseTest.test.log(status, message);
	}
}
