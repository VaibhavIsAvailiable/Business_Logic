package com.crowdfunding_paradigm.platform.services.user;


import lombok.Data;

@Data
public class UserDTO {

	private int id;

	private String name;

	private String email;

	private String password;

	private String mobileNumber;

	private String about;
	 
	private String role;

}