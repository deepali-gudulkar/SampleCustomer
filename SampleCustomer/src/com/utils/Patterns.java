package com.utils;

/**
 * @author Deepali
 *
 *	Containes regular expressions for fields of Customer
 */
public class Patterns {

	// Regex for pincode
	public static final String REGEX_PINCODE = "^[a-zA-Z0-9]*$|[0-9]+|[a-zA-Z]+";
	
	// Regex for status, name
	public static final String REGEX_ALPHA = "[a-zA-Z]+";
	
	// Regex for numbers
	public static final String REGEX_NUMERIC = "[0-9]+";
}
