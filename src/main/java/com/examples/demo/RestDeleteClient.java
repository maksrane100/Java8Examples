package com.examples.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * RestClient to delete individual user from REST API.
 */
public class RestDeleteClient {

	public static void main(String[] args) {

		final String uri = "http://localhost:4200/users/delete/5ba67f7fa1309d3838f35f4c";

	     
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", "5ba67f7fa1309d3838f35f4c");
	     
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.delete ( uri,  params );
	    
	    System.out.println("User deleted.");
	    
	    final String geturi = "http://localhost:4200/users/edit/5bafe493941e672cdc4547c2";

		restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		System.out.println(result);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			User user = objectMapper.readValue(result, User.class);
			System.out.println(user);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
