package com.janani.exception;

import java.beans.Expression;
import java.time.LocalDate;

public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private LocalDate date;
	private String message;
	private String details;

	public BusinessException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public BusinessException(LocalDate date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}

	@Override
	public synchronized BusinessException fillInStackTrace() {
		return this;
	}
}
