package thormegseleniumdemo.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import thormegseleniumdemo.apiDTO.ReqresDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RESTTests {
	//Generic specification for these tests
	
	RequestSpecification rspec = new RequestSpecBuilder()
			.addHeader("Accept:", "application/json")
			.addHeader("Content-Type", "application/json")
			.build();
	
	private String GET_PAGE_URI = "/api/users?page=";
	private String POST_URI = "/api/users";
	private String PUT_URI = "/api/users/";
	
	private int CODE_SUCCESS = 200;
	private int CODE_CREATED = 201;
	private int CODE_NOCONTENT = 204;
	
	@Test
	public void testCRUD() {
		System.out.println("Starting REST API Tests...");
		//GET
		RestAssured.baseURI = "https://reqres.in";
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

		Response r = RestAssured.given()
				.expect()
				.statusCode(CODE_SUCCESS)
				.when()
				.get(GET_PAGE_URI + 2);
		
		Assert.assertEquals(r.jsonPath().getInt("page"), 2);
		
		//POST
		ReqresDTO post = new ReqresDTO();
		post.setName("morpheus");
		post.setJob("leader");

		ReqresDTO response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(post)
				.expect().statusCode(CODE_CREATED)
				.when()
				.post(POST_URI)
				.as(ReqresDTO.class);
		
		Assert.assertEquals(response.getName(), "morpheus");
		
		//PUT
		response.setJob("zion resident");
		ReqresDTO updatedResponse = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(response)
				.expect().statusCode(CODE_CREATED)
				.when()
				.post(PUT_URI + 2)
				.as(ReqresDTO.class);
		
		Assert.assertEquals(updatedResponse.getJob(), "zion resident");
		
		//Just use same format as PUT_URI here, we're referencing the same objects in the same way.
		//DELETE
		RestAssured.given()
				.expect()
				.statusCode(CODE_NOCONTENT)
				.when()
				.delete(PUT_URI + 2);
	}
}
