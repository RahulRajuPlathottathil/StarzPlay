package TestCases;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import org.testng.annotations.Test;


import apiManager.LayoutRequestBuilder;
import apiManager.ModuleRequestBuilder;
import apiManager.World;
import base.BaseTest;
import base.PropertyReader;
import data.ExtractDataFromResponse;
import data.Verifier;
import dataProvider.DataProviderHelper;
import models.Layout;

import models.md.Page;

public class TestHere extends BaseTest {
	String methodName;

	@Test(dataProvider = "StarzPlayDataProvider", dataProviderClass = DataProviderHelper.class)
	public void TestCall(Hashtable<String, String> table) throws IOException {

		this.methodName = table.get("TestCase");
		test = extent.createTest(methodName, "Sample test description");
		System.out.println(table.get("RunMode"));
		Layout layout = LayoutRequestBuilder.given().setBaserURI().setHeaders(table).setQueryParameter(table)
				.getLayout("layout/" + table.get("page") + "/" + PropertyReader.getSessionID());
		table.put("x-query", layout.getxQuery());
		table.put("x-token", layout.getxToken());
		List<Page> res = ModuleRequestBuilder.given().setBaserURI().setHeaders(table).setQueryParameter(table)
				.getModules();

		World.setLayout(layout);
		World.setMovie(res);

		//Verifier.VerifyItemsCount();
		ExtractDataFromResponse.apiScrambler();
	}

}
