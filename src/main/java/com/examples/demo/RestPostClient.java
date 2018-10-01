package com.examples.demo;

import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * RestClient to read individual user from REST API.
 */
public class RestPostClient {

	public static void main(String[] args) {

		final String uri = "http://localhost:4200/users/add";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("firstname", "Amit");
		map.add("lastname", "Patil");
		map.add("username", "amit.patil");
		map.add("password", "amit");
		map.add("email", "Amit.Patil@test.com");
		map.add("gender", "male");
		map.add("age", "24");
		map.add("address.address1", "1200 State Street");
		map.add("address.address2", "Suite # 400");
		map.add("address.city", "Foster City");
		map.add("address.state", "CA");
		map.add("address.zip", "94404");
		map.add("address.country", "US");
		
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			ResponseEntity<String> response = restTemplate.postForEntity( uri, request , String.class );
			System.out.println(response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
