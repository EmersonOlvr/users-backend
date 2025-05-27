package com.emerson.userinfoservice.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HttpRuntimeException extends RuntimeException {

	private static final long serialVersionUID = -3855223399151104729L;
	
	private int httpStatusCode;
	
	public HttpRuntimeException(String message, int httpStatusCode) {
		super(message);
		this.httpStatusCode = httpStatusCode;
	}
	
}
