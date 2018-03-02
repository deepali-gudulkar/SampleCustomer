package com.utils;

/**
 * @author Deepali
 *
 *         Contains error messages for validation
 */
public class ValidationMessages {

	// Validation error messages for the name of customer
	public static final String MESSAGE_NAME_NULL = "Name should not be null";
	public static final String MESSAGE_NAME_LENGTH = "Name should have atleast 3 characters";
	public static final String MESSAGE_NAME_INVALID = "Invalid Name";

	// Validation error messages for the phone number of customer
	public static final String MESSAGE_PHONE_NULL = "Phone number should not be null";
	public static final String MESSAGE_PHONE_LENGTH = "Phone number should be only 10 digits";
	public static final String MESSAGE_PHONE_INVALID = "Phone number should contain only numbers";

	// Validation error messages for the emailId of customer
	public static final String MESSAGE_EMAIL_NULL = "Email should not be null";
	public static final String MESSAGE_EMAIL_LENGTH = "Size of email must be between 5 to 50 characters";
	public static final String MESSAGE_EMAIL_INVALID = "Email should be valid";

	// Validation error messages for the pincode of customer
	public static final String MESSAGE_PINCODE_INVALID = "Invalid Pincode";
	public static final String MESSAGE_PINCODE_LENGTH = "Pincode should be only 6 characters";
	
	// Validation error messages for the status of customer
	public static final String MESSAGE_STATUS_INVALID = "Invalid Status";

	// Success or Error messages for rest apis
	public static final String MESSAGE_CUSTOMER_ADDED = "Customer was created successfully, id: ";
	public static final String MESSAGE_CUSTOMER_UPDATED = "Customer was updated successfully, id: ";
	public static final String MESSAGE_CUSTOMER_DELETED = "Customer was deleted successfully, id: ";
	public static final String MESSAGE_ID_VALID = "id param must be greater than 0";

}
