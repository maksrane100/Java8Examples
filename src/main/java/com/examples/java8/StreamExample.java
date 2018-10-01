package com.examples.java8;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.web.client.RestTemplate;

import com.examples.demo.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StreamExample {

	public static void main(String[] args) {

		final String uri = "http://localhost:4200/users";

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		// System.out.println(result);
		ObjectMapper objectMapper = new ObjectMapper();
		User[] users = null;
		try {
			users = objectMapper.readValue(result, User[].class);

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

		List<User> list = Arrays.asList(users);

		// Lets filter the users to find out users that live in Foster City

		List<User> filteredList = list.stream().filter((u) -> u.getAddress().getCity().equalsIgnoreCase("Foster City"))
				.collect(Collectors.toList());

		filteredList.forEach((u) -> System.out.println(u.getFirstname() + " " + u.getLastname() + " "
				+ u.getAddress().getAddress1() + " " + u.getAddress().getCity() + " " + u.getAddress().getCountry()));

		filteredList.clear();
		
		// Lets filter the users to find out users that live in a zip code using Predicate

		filteredList = list.stream().filter(userAtZipCode()).collect(Collectors.toList());

		filteredList.forEach((u) -> System.out.println(u.getFirstname() + " " + u.getLastname() + " "
				+ u.getAddress().getAddress1() + " " + u.getAddress().getCity() + " " + u.getAddress().getCountry()));
		

		filteredList.clear();
		
		// Lets filter the users to find out men users using Predicate

		filteredList = list.stream().filter(isMaleUser()).collect(Collectors.toList());

		filteredList.forEach((u) -> System.out.println(u.getFirstname() + " " + u.getLastname() + " "
				+ u.getAddress().getAddress1() + " " + u.getAddress().getCity() + " " + u.getAddress().getCountry()));
		

	}

	public static Predicate<User> userAtZipCode() {
		return u -> u.getAddress().getZip().equalsIgnoreCase("94404");
	}
	
	public static Predicate<User> isMaleUser() {
        return u ->  u.getGender().equalsIgnoreCase("male");
    }

}
