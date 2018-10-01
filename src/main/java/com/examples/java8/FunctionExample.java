package com.examples.java8;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import org.springframework.web.client.RestTemplate;

import com.examples.demo.Address;
import com.examples.demo.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FunctionExample {

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

		// Lets use lamda expressions to sort the list by using user's firstname

		Collections.sort(list, (u1, u2) -> u1.getFirstname().compareTo(u2.getFirstname()));

		list.forEach((u) -> System.out.println(u.getFirstname() + " " + u.getLastname() + " "
				+ u.getAddress().getAddress1() + " " + u.getAddress().getCity() + " " + u.getAddress().getCountry()));

		// Now use Function that takes input as User and returns it's address

		Function<User, Address> getUserAddress = (User u) -> {
			return u.getAddress();
		};

		Address address = getUserAddress.apply(list.get(0));

		System.out.println(address.getAddress1() + " " + address.getCity() + " " + address.getState() + " "
				+ address.getCountry());

	}

}
