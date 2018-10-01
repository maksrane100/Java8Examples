package com.examples.java8;


/**
 * Creating comma separated string using StringJoiner.
 *
 */
public class StringJoinerExample {

	public static void main(String[] args) {

		StringJoiner str = new StringJoiner(",");

		str.add("Transaction1");
		str.add("11.99$");
		str.add("Foster City");
		str.add("10:44AM");
		System.out.println(str);

	}

}
