package com.jbank.olb.customerProfile.security.exception;

public class JwtInvalidTokenException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JwtInvalidTokenException(String message, Throwable cause) {
	    super(message, cause);
	}
}
