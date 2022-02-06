package com.wb.exception.beans;

import java.util.ArrayList;
import java.util.List;

import com.wb.exception.constants.ErrorCodes;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private List<ErrorCodes> errorCodes;

	private String developerMessage;

	public ServiceException() {
	}

	public ServiceException(String str) {
		super(str);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(List<ErrorCodes> errorCodes) {
		super(errorCodes.toString());
		this.errorCodes = errorCodes;
	}

	public ServiceException(List<ErrorCodes> errorCodes, String message) {
		super(message);
		this.errorCodes = errorCodes;
	}

	public ServiceException(List<ErrorCodes> errorCodes, Throwable cause) {
		super(errorCodes.toString(), cause);
		this.errorCodes = errorCodes;
	}

	public ServiceException(List<ErrorCodes> errorCodes, String message, Throwable cause) {
		super(message, cause);
		this.errorCodes = errorCodes;
	}

	public ServiceException(ErrorCodes errorCode) {
		super(errorCode.toString());
		List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
		errorCodes.add(errorCode);
		this.errorCodes = errorCodes;
	}

	public ServiceException(ErrorCodes errorCode, String message) {
		super(message);
		List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
		errorCodes.add(errorCode);
		this.errorCodes = errorCodes;
	}

	public ServiceException(ErrorCodes errorCode, Throwable cause) {
		super(errorCode.toString(), cause);
		List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
		errorCodes.add(errorCode);
		this.errorCodes = errorCodes;
	}

	public ServiceException(ErrorCodes errorCode, String message, Throwable cause) {
		super(message, cause);
		List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
		errorCodes.add(errorCode);
		this.errorCodes = errorCodes;
	}

	public ServiceException(String message, String developerMessage) {
		super(message);
		this.developerMessage = developerMessage;
	}

	public ServiceException(ErrorCodes errorCode, String message, String developerMessage) {
		super(message);
		List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
		errorCodes.add(errorCode);
		this.errorCodes = errorCodes;
		this.developerMessage = developerMessage;
	}

	public ServiceException(ErrorCodes errorCode, String message, String developerMessage, Throwable cause) {
		super(message, cause);
		List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
		errorCodes.add(errorCode);
		this.errorCodes = errorCodes;
		this.developerMessage = developerMessage;
	}

	public ServiceException(List<ErrorCodes> errorCodes, String message, String developerMessage) {
		super(message);
		this.errorCodes = errorCodes;
		this.developerMessage = developerMessage;
	}

	public List<ErrorCodes> getErrorCodes() {
		return errorCodes;
	}

	public void setErrorCodes(List<ErrorCodes> errorCodes) {
		this.errorCodes = errorCodes;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
}