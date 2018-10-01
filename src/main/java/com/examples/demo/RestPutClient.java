package com.examples.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/*
 * RestClient to update individual user from REST API.
 */
public class RestPutClient {

	public static void main(String[] args) {
		final String geturi = "http://localhost:4200/users/edit/5bb12ddcd5d31832a4cf59c6";
		final String uri = "http://localhost:4200/users/update/5bb12ddcd5d31832a4cf59c6";
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		
		MappingJacksonHttpMessageConverter converter = new MappingJacksonHttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		
		ObjectMapper objectMapper = new ObjectMapper();
		try {

			Map<String, String> params = new HashMap<String, String>();
			params.put("id", "5bb12ddcd5d31832a4cf59c6");

			String result = restTemplate.getForObject(geturi, String.class);
			System.out.println(result);
			objectMapper = new ObjectMapper();
			try {
				User user = objectMapper.readValue(result, User.class);
				System.out.println(user.get_id() + " " + user.getFirstname() + " " + user.getLastname());
				user.setAge(32);
				user.setEmail("amit.patil4@test.com");

				restTemplate.put(uri, user, params);

				System.out.println("User information updated.");

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
