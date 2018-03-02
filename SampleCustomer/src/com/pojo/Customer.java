package com.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.utils.Patterns;
import com.utils.ValidationMessages;

/**
 * @author Deepali Pojo class for Customer
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement(name = "customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	@NotNull(message = ValidationMessages.MESSAGE_NAME_NULL)
	@Size(min = 3, message = ValidationMessages.MESSAGE_NAME_LENGTH)
	@Pattern(regexp = Patterns.REGEX_ALPHA, message = ValidationMessages.MESSAGE_NAME_INVALID)
	private String name;

	@Column(name = "phone")
	@NotNull(message = ValidationMessages.MESSAGE_PHONE_NULL)
	@Pattern(regexp = Patterns.REGEX_NUMERIC, message = ValidationMessages.MESSAGE_PHONE_INVALID)
	@Length(min = 10, max = 10, message = ValidationMessages.MESSAGE_PHONE_LENGTH)
	private String phone;

	@Column(name = "email")
	@NotNull(message = ValidationMessages.MESSAGE_EMAIL_NULL)
	@Size(min = 5, max = 50, message = ValidationMessages.MESSAGE_EMAIL_LENGTH)
	@Email(message = ValidationMessages.MESSAGE_EMAIL_INVALID)
	private String email;

	@Column(name = "pincode")
	@Pattern(regexp = Patterns.REGEX_PINCODE, message = ValidationMessages.MESSAGE_PINCODE_INVALID)
	@Length(min = 6, max = 6, message = ValidationMessages.MESSAGE_PINCODE_LENGTH)
	private String pincode;

	@Column(name = "status")
	@Pattern(regexp = Patterns.REGEX_ALPHA, message = ValidationMessages.MESSAGE_STATUS_INVALID)
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
