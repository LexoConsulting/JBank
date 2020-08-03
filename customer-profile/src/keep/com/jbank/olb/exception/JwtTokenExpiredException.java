package com.jbank.olb.customerProfile.security.exception;

public class JwtTokenExpiredException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JwtTokenExpiredException(String message) {
	    super(message);
	}
}
