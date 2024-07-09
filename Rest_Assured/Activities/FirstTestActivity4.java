package examples;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class FirstTestActivity4 {
	
	//GET https://petstore.swagger.io/v2/pet/findByStatus?status=alive
	@Test
	public void getRequestWithQueryParam() {
		//send request and save response
		Response response =
				given().
					baseUri("https://petstore.swagger.io/v2/pet").
					header("Content-Type", "application/json").
					queryParam("status", "alive").
				when().
				get("/findByStatus");
		
		//print the response header
		System.out.println(response.getHeaders());
		//print the response body
		System.out.println(response.getBody().asString());
		System.out.println("-----------------------------------");
		System.out.println(response.getBody().asPrettyString());
		
		//extract properties from response
		String petStatus = response.then().extract().path("[0].status");
		
		//TestNG assertion
		Assert.assertEquals(petStatus, "alive");
		
		//RESTAssured assertion
		response.then().statusCode(200).body("[0].status", equalTo("alive"));
		
	}
	
	
	//GET https://petstore.swagger.io/v2/pet/{petId}
	@Test
	public void getRequestWithPathParam() {
		//send request, get response & assert with logging
		given().
			baseUri("https://petstore.swagger.io/v2/pet").
			header("Content-Type", "application/json").
			pathParam("petID", 77232).
			log().all().
		when().
			get("/{petID}").
		then().
			statusCode(200).
			body("status", equalTo("alive")).
			body("name", equalTo("Riley")).
			log().all();
	}
	

}
