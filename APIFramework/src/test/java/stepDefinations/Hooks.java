package stepDefinations;

import java.io.FileNotFoundException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before("@DeletePlace")
	public void beforeScenario() throws FileNotFoundException {
		
		StepDefination m = new StepDefination();
		if(StepDefination.placeId==null) {
		m.add_Place_Payload_with("Rina","Mexican", "999 Hope Street");
		m.user_calls_with_Post_http_request("addPlaceAPI", "POST");
		m.verify_place_Id_created_maps_to_using_getPlaceAPI("Rina", "getPlaceAPI");
		}
	}

}
