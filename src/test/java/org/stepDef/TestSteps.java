package org.stepDef;
import java.io.IOException;

import org.jsonBody.GivenJsonBody;
import org.junit.Assert;
import org.pojo.Root;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.fasterxml.jackson.databind.ObjectMapper;
//import io.cucumber.messages.internal.com.fasterxml.jackson.core.JsonProcessingException;
//import io.cucumber.messages.internal.com.fasterxml.jackson.databind.JsonMappingException;



public class TestSteps {
	
	String json;
	Root rt;
	@Given("Store the response")
	public void store_the_response() {
		json=  new GivenJsonBody().res;
	}

	@When("Deserialaize the resopnse by using pojo class")
	public void deserialaize_the_resopnse_by_using_pojo_class() throws IOException {
	//   ObjectMapper objMap = new ObjectMapper();
	  rt = new ObjectMapper().readValue(json,Root.class);
	  
	}

	@Then("Validate That the team contains only four foreign players")
	public void validate_that_the_team_contains_only_four_foreign_players() {
	    int count =0;
	    for(int i=0; i<rt.getPlayer().size(); i++) {
	    	if(!rt.getPlayer().get(i).getCountry().equals("India")) {
	    		count++;
	    	}
	    }
	    Assert.assertEquals(4, count);
	}

	@Then("Validate that there is ateast one wicket keeper")
	public void validate_that_there_is_ateast_one_wicket_keeper() {
	   boolean wk = false;
	   for(int i=0; i<rt.getPlayer().size(); i++) {
		   if(rt.getPlayer().get(i).getRole().equals("Wicket-keeper")) {
			   wk =true;
			   break;
		   }
	   }
	   Assert.assertTrue(wk);
	}
}
