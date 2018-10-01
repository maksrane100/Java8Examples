package com.examples.demo;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * RestClient to read all users from REST API.
 */
public class RestClientToReadAllUsers {

	public static void main(String[] args) {

		final String uri = "http://localhost:4200/users";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		System.out.println(result);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			User[] users = objectMapper.readValue(result, User[].class);
			for (int i = 0; i < users.length; i++) {
				User user = users[i];
				System.out.println(user.get_id() + " " + user.getFirstname() + " " + user.getLastname());
			}
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
