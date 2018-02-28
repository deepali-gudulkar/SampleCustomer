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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Deepali
 *	Pojo class for Customer
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
	@Pattern(regexp = "[a-zA-Z]+", message = "Invalid Name")
	private String name;

	@Column(name = "phone")
	@NotNull
	@Pattern(regexp = "[0-9]+", message = "Invalid Phone Number")
	private Long phone;

	@Column(name = "email")
	@Pattern(regexp = ".+@.+\\.[a-z]+", message = "Invalid Email Address")
	private String email;

	@Column(name = "pincode")
	@Pattern(regexp = "^[a-zA-Z0-9]*$|[0-9]+|[a-zA-Z]+", message = "Invalid Pincode")
	private String pincode;

	@Column(name = "status")
	@Pattern(regexp = "[a-zA-Z]+", message = "Invalid Status")
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

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
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
