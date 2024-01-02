package apiManager;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Layout;

public class ApiManager {
	
	public static void setUp() {
		RestAssured.baseURI="https://sp-stg-new-api.aws.playco.com/api/v1.1/";
		RequestSpecification requestSpecification =RestAssured.given()
				.header("content-type", "application/json")
				.header("x-profiletype", "EV")
				.header("x-geo-country", "AE")
				.queryParam("lang", "en")
				.queryParam("platform", "web")
				.queryParam("origin", "web-chrome")
				.queryParam("x-geo-country", "AE")
				.queryParam("profileType", "EV")
				.queryParam("parentalControl", 18)
				.queryParam("version", 2);
				
		requestSpecification.log().all();
		
		Response response=requestSpecification.get("layout/home/a78f3fed2aca4074a5286b8e8803ce19-sp");
		Layout lay=response.then().extract().response().as(Layout.class);
		System.out.println(lay.getxToken());
		System.out.println(lay.getxQuery());
		RequestSpecification requestSpecification2 =RestAssured.given()
				.header("content-type", "application/json")
				.header("client-type","website")
				.header("x-profiletype", "EV")
				.header("x-geo-country", "AE")
				.header("x-query", lay.getxQuery())
				.header("x-token", lay.getxToken())
				.queryParam("platform", "web")
				.queryParam("origin", "web-chrome")
				.queryParam("x-geo-country", "AE")
				.queryParam("profileType", "EV")
				.queryParam("modules", "0-100")
				
				.queryParam("version", 2);
		
				
		requestSpecification2.log().all();
		
		Response response2=requestSpecification2.get("modules/home/a78f3fed2aca4074a5286b8e8803ce19-sp");
		response2.then().log().all();
		
		
	}
	
	public static void main(String[] args) {
		//PropertyReader.loadConfiguration();
		setUp();
	}
}
