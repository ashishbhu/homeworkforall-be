package com.wb.exception.beans;

import java.io.Serializable;
import java.util.List;

import com.wb.exception.constants.ErrorCodes;

public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 2103811657604281833L;

	private String message;

	private String developerMessage;

	private Class<? extends Throwable> type;

	private List<ErrorCodes> errorCodes;

	public ErrorMessage() {
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the developerMessage
	 */
	public String getDeveloperMessage() {
		return developerMessage;
	}

	/**
	 * @param developerMessage the developerMessage to set
	 */
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	/**
	 * @return the type
	 */
	public Class<? extends Throwable> getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Class<? extends Throwable> type) {
		this.type = type;
	}

	/**
	 * @return the errorCodes
	 */
	public List<ErrorCodes> getErrorCodes() {
		return errorCodes;
	}

	/**
	 * @param errorCodes the errorCodes to set
	 */
	public void setErrorCodes(List<ErrorCodes> errorCodes) {
		this.errorCodes = errorCodes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ErrorMessage [message=" + message + ", developerMessage="
				+ developerMessage + ", type=" + type + ", errorCodes=" + errorCodes
				+ "]";
	}
}