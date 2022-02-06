package com.wb.exception.beans;

import java.util.List;

import com.wb.exception.constants.ErrorCodes;

public class InvalidAccessException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InvalidAccessException() {
	}

	/**
	 * @param str
	 */
	public InvalidAccessException(String str) {
		super(str);
	}

	/**
	 * @param cause
	 */
	public InvalidAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidAccessException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param errorCodes
	 */
	public InvalidAccessException(List<ErrorCodes> errorCodes) {
		super(errorCodes);
	}

	/**
	 * @param errorCodes
	 * @param message
	 */
	public InvalidAccessException(List<ErrorCodes> errorCodes, String message) {
		super(errorCodes, message);
	}

	/**
	 * @param errorCodes
	 * @param cause
	 */
	public InvalidAccessException(List<ErrorCodes> errorCodes, Throwable cause) {
		super(errorCodes, cause);
	}

	/**
	 * @param errorCodes
	 * @param message
	 * @param cause
	 */
	public InvalidAccessException(List<ErrorCodes> errorCodes, String message, Throwable cause) {
		super(errorCodes, message, cause);
	}

	/**
	 * @param errorCode
	 */
	public InvalidAccessException(ErrorCodes errorCode) {
		super(errorCode);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public InvalidAccessException(ErrorCodes errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public InvalidAccessException(ErrorCodes errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public InvalidAccessException(ErrorCodes errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param message
	 * @param developerMessage
	 */
	public InvalidAccessException(String message, String developerMessage) {
		super(message, developerMessage);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param developerMessage
	 */
	public InvalidAccessException(ErrorCodes errorCode, String message, String developerMessage) {
		super(errorCode, message, developerMessage);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param developerMessage
	 * @param cause
	 */
	public InvalidAccessException(ErrorCodes errorCode, String message, String developerMessage, Throwable cause) {
		super(errorCode, message, developerMessage, cause);
	}

	/**
	 * @param errorCodes
	 * @param message
	 * @param developerMessage
	 */
	public InvalidAccessException(List<ErrorCodes> errorCodes, String message, String developerMessage) {
		super(errorCodes, message, developerMessage);
	}
}