Feature: Validating Place API's'

@AddPlace @Regression
Scenario Outline: Verify if Place is being Succesfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" , "<language>" , "<address>"
	When user calls "addPlaceAPI" with "Post" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"	
	And "scope" in response body is "APP"
	And verify place_Id created maps to "<name>" using getPlaceAPI "getPlaceAPI"
	
Examples:
   |name | language | address|
   |Rino | English | 123 Moon Street|
 #  |DHouse | Spanish | 344 Sun Ct|
 #  |BDoom | Italian | 999 Marsh Ln|
   
@DeletePlace  @Regression 
Scenario: Verify if Delete place functionality is working
   Given Delete place Payload
   When user calls "deletePlaceAPI" with "Post" http request
   Then the API call got success with status code 200
   And "status" in response body is "OK"
   
   