package project;

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

public class RequestSpecificationProjectActivity1 {

	RequestSpecification requestSpec;
	ResponseSpecification responseSpec;
	String sSHKey;
	int sSHKeyId;

	@BeforeClass
	public void setUp() {
		requestSpec = new RequestSpecBuilder().
				setBaseUri("https://api.github.com").
				addHeader("Content-Type", "application/json").
				addHeader("Authorization", "token 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").
				build();

		responseSpec = new ResponseSpecBuilder().expectResponseTime(lessThanOrEqualTo(3000L)).build();
	}

	@Test(priority = 1)
	public void postRequest() {
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("title", "TestAPIKey");
		reqBody.put("key", "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIL9tZy1ngX64gONYmt457D6Ph3J1vEfdSwT9sJzOiLXt");
		
		Response response = given().spec(requestSpec).body(reqBody).when().post("/user/keys");
		sSHKeyId = response.then().extract().path("id");
		
		response.then().spec(responseSpec).assertThat().statusCode(201);
	}

	@Test(priority = 2)
	public void getRequest() {
		// send request, get response & assert response
		given().spec(requestSpec).pathParam("keyId", sSHKeyId).
		when().get("/user/keys/{keyId}").
		then().spec(responseSpec).assertThat().statusCode(200);
	}
	
	@Test(priority = 3)
	public void deleteRequest() {
		//send request, get response & assert response
		 given().spec(requestSpec).pathParam("keyId", sSHKeyId).
		 when().delete("/user/keys/{keyId}").
		 //Reporter.log("" + sSHKeyId).
		 then().spec(responseSpec).assertThat().statusCode(204);
	}

}
