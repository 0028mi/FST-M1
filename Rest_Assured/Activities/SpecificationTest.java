package examples;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;

import java.util.HashMap;
import java.util.Map;

public class SpecificationTest {
	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	int petId;
	
	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().
			setBaseUri("https://petstore.swagger.io/v2/pet").
			addHeader("Content-Type", "application/json").
			build();
				
		responseSpec = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectResponseTime(lessThanOrEqualTo(3000L)).
				build();
	}
	
	//POST https://petstore.swagger.io/v2/pet
	@Test(priority = 1)
	public void postRequest() {
		//create request body
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("id", 77198);
		reqBody.put("name", "Lucky");
		reqBody.put("status", "alive");
		
		//send request and save response
		Response response = given().spec(requestSpec).body(reqBody).when().post();
		//extract petId
		petId = response.then().extract().path("id");
		// Assertion
		response.then().spec(responseSpec).body("status", equalTo("alive"));
		
	}
	
	//GET https://petstore.swagger.io/v2/pet/{petId}
	@Test(priority = 2)
	public void getRequest() {
		//send request, get response & assert response
		 given().spec(requestSpec).pathParam("petId", petId).
		 when().get("/{petId}").
		 then().spec(responseSpec).body("status", equalTo("alive"));	
	}
	
	
	//DELETE https://petstore.swagger.io/v2/pet/{petId}
	@Test(priority = 3)
	public void deleteRequest() {
		//send request, get response & assert response
		 given().spec(requestSpec).pathParam("petId", petId).
		 when().delete("/{petId}").
		 then().spec(responseSpec).body("message", equalTo("" + petId));
	}
}
