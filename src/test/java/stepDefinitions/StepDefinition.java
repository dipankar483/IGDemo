package stepDefinitions;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.List;

import org.junit.Assert;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class StepDefinition {

	private Response response;
	private ValidatableResponse json;
	private RequestSpecification request;

	String URL = "http://demo4032024.mockable.io/apitest";
	

	
	@Given("GETDetails Payload")
	public void getdetails_Payload() {
	    request = given();
		
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String string, String string2) {
	    
		response = request.when().get(URL);
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer statusCode) {
		json = response.then().log().all().assertThat().statusCode(statusCode);
		

	}

	@Then("verify response header")
	public void verify_response_header() {
	   

		//RestAssured.baseURI = "http://demo4032024.mockable.io";
		RequestSpecification httpRequest = RestAssured.given();
		//Response response = httpRequest.get("/apitest");
 
		// Reader header of a give name. In this line we will get Header named Content-Type
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType /* actual value */, "application/json; charset=UTF-8" /* expected value */);
 
		// Reader header of a give name. In this line we will get Header named Server
		String serverType =  response.header("Server");
		Assert.assertEquals(serverType /* actual value */, "Google Frontend" /* expected value */);
		
	}

	@SuppressWarnings("unlikely-arg-type")
	@Then("verify response body")
	public void verify_response_body() {
		
		String key="employeeData";//array key
		Response response= RestAssured.when().get("http://demo4032024.mockable.io/apitest");//your API call which will return Json Object
		List<HashMap<String,Object>>EmployeeList=response.jsonPath().getList(key);
		
		//Now parse value from List
		HashMap<String,Object> idDetail=EmployeeList.get(0);// for first index
		String id=(String)idDetail.get("id");
		System.out.println("The value of id = " +id);
		Assert.assertTrue(id.contains("101209986"));
		
		HashMap<String, Object> dobDetail=EmployeeList.get(0);
		String dob=(String)dobDetail.get("dob");
		System.out.println("The value of dob = " + dob);
		Assert.assertTrue(dob.contains("25-02-1994"));



		HashMap<String, Object> roleDetail=EmployeeList.get(0);
		String role = (String)roleDetail.get("role");
		System.out.println("The value of role = " + role);
		Assert.assertTrue(role.contains("QA Automation Developer"));
		
		String key2="employeeData";//array key
		Response response2= RestAssured.when().get("http://demo4032024.mockable.io/apitest");
		List<HashMap<Integer,Object>>EmployeeList1=response2.jsonPath().getList(key2);
		
		
		HashMap<Integer, Object> ageDetail=EmployeeList1.get(0);
		int age = (Integer) ageDetail.get("age");
		System.out.println("The value of age = " + age);
		Assert.assertEquals(25, age);

		
		String message = RestAssured.when().get("http://demo4032024.mockable.io/apitest")
			    .then().extract().jsonPath()
			    .get("message");
		
		System.out.println("The value of message is = " + message);
		Assert.assertTrue(message.contains("data retrieved successful"));


	}
	

	@Then("validate response body with data")
	public void validate_response_body_with_data() {
	
		List<String> values = RestAssured.when().get("http://demo4032024.mockable.io/apitest")
			    				.then().extract().jsonPath()
			    				.getList("employeeData.company");

		Assert.assertTrue(values.contains("ABC Infotech"));
			
		
	}
	
}
