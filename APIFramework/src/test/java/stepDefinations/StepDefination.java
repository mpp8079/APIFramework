package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils{
	
	 RequestSpecification res;
	 TestDataBuild data = new TestDataBuild();
	 Response response;
	 ResponseSpecification responsespec;	 
	 static String placeId;

	
	 @Given("Add Place Payload with {string} , {string} , {string}")
	 public void add_Place_Payload_with(String name, String language, String address) throws FileNotFoundException  {	
		RestAssured.baseURI = "https://rahulshettyacademy.com";	    
	
	  
		
		responsespec = new ResponseSpecBuilder().expectStatusCode(200).
				expectContentType(ContentType.JSON).build();
	  res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	   
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_Post_http_request(String resouces, String method) {
		
	  APIResources resourceAPI =  APIResources.valueOf(resouces);
	  System.out.println(resourceAPI.getResources());
	  if(method.equalsIgnoreCase("POST"))
		response = res.when().post(resourceAPI.getResources());
	  else if(method.equalsIgnoreCase("GET"))		
		  response = res.when().get(resourceAPI.getResources());
	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
		
	    System.out.println("Test Run");
	   
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String ExpectedValue) {
		
		
		System.out.println("Test Run");
	}
	
	
	@Then("verify place_Id created maps to {string} using getPlaceAPI {string}")
	public void verify_place_Id_created_maps_to_using_getPlaceAPI(String name, String getAPI) throws FileNotFoundException {
		placeId = getJsonPath(response,"place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", placeId);
		user_calls_with_Post_http_request(getAPI,"GET");
		String getName = getJsonPath(response,"name");
		System.out.println(getName);
	}
	
	
	@Given("Delete place Payload")
	public void delete_place_Payload() throws FileNotFoundException {
		
		res =given().spec(requestSpecification()).body(data.deletePlacePayload(placeId));
	   
	}

	
}
