package com.koreait.mvc02.dto;

public class Contact {

	// field
	private String phone;
	private String address;
	
	// constructor
	public Contact() {}
	public Contact(String phone, String address) {
		super();
		this.phone = phone;
		this.address = address;
	}
	
	
	// method
	
	
	
	
	
	// Getter and Setter
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
