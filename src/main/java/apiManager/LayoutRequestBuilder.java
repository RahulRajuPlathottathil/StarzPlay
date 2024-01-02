package apiManager;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;

import base.PropertyReader;
import data.ImageLogic;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Layout;

public class LayoutRequestBuilder {
	private RequestSpecification requestSpecification;
	
	public static LayoutRequestBuilder given() {
		return new LayoutRequestBuilder();
	}

	public LayoutRequestBuilder setBaserURI() {
		RestAssured.baseURI = PropertyReader.getBaseURI();//"https://sp-new-api.aws.playco.com/api/v1.1/";
		requestSpecification=RestAssured.given();

		return this;
	}

	public LayoutRequestBuilder setHeaders(Hashtable<String, String> data) {
		
        Map<String,String> headers = new HashedMap<String, String>();
        headers.put("content-type",data.get("content-type"));
        headers.put("x-profiletype",data.get("x-profiletype"));
        headers.put("x-geo-country",data.get("x-geo-country"));
        this.requestSpecification.headers(headers);
     		return this;
	}
	
	public LayoutRequestBuilder setQueryParameter(Hashtable<String,String> data) {
		Map<String,String> queryParams = new HashedMap<String, String>();
		queryParams.put("lang",data.get("lang"));
		queryParams.put("platform",data.get("platform"));
		queryParams.put("origin",data.get("origin"));
		queryParams.put("parentalControl",data.get("parentalControl"));
		queryParams.put("version",data.get("version"));
		queryParams.put("x-geo-country",data.get("x-geo-country"));
		queryParams.put("profileType",data.get("x-profiletype"));
        this.requestSpecification.queryParams(queryParams);
        ImageLogic.setPlatform(data.get("platform"));
        ImageLogic.setVersion(data.get("version"));
        ImageLogic.setOrigin(data.get("origin"));
		return this;
	
	}
	
	public Layout getLayout(String endPoint) throws IOException {
		Response response=requestSpecification.get(endPoint);//get("layout/home/a78f3fed2aca4074a5286b8e8803ce19-sp");
		Layout lay=response.then().extract().response().as(Layout.class);
		String filePath=GlobalConstants.userDir()+"layout.txt";
		try (FileWriter fileWriter = new FileWriter(filePath);
	             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {

	            // Write content to the text file
	            bufferedWriter.write(response.asString());
	            bufferedWriter.flush();
	            fileWriter.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println(lay.getxToken());
		System.out.println(lay.getxQuery());
		return lay;
	}

}
