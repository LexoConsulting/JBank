package com.jbank.olb.common.exception;

import java.util.Date;

public class ErrorDetail {
	private Date timestamp;

	private String code;
	private String message;
	private String details;

	public ErrorDetail(String code, String message, String details) {
		this.timestamp = new Date();

		this.code = code;
		this.message = message;
		this.details = details;
	}

	public ErrorDetail(String code, String message) {
		this(code, message, "");
	}

	@Override
	public String toString() {
		return "ErrorDetails [timestamp=" + timestamp + ", code=" + code + ", message=" + message + ", details="
				+ details + "]";
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
}
