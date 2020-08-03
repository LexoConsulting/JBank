package com.jbank.olb.customerProfile.security.dto;

public class JwtUserDTO {
	private Long userId;
	private String userName;
	private String role;	// List of Authority, separated by ','
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private String email;
	
	public JwtUserDTO() {
	}
	
	public JwtUserDTO(Long userId, String userName, String role, String firstName, String middleName, String lastName, String email) {
		this.userId = userId;
		this.userName = userName;
		this.role = role;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.email = email;
	}

	@Override
	public String toString() {
		return "JwtUserDTO [userId=" + userId + ", userName=" + userName + ", roles=" + role + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String roles) {
		this.role = roles;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
