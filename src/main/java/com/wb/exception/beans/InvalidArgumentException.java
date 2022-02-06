package com.wb.exception.beans;

import java.util.List;

import com.wb.exception.constants.ErrorCodes;

public class InvalidArgumentException extends ServiceException {

	private static final long serialVersionUID = 1L;

	public InvalidArgumentException() {
		super();
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param developerMessage
	 * @param cause
	 */
	public InvalidArgumentException(ErrorCodes errorCode, String message, String developerMessage, Throwable cause) {
		super(errorCode, message, developerMessage, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param developerMessage
	 */
	public InvalidArgumentException(ErrorCodes errorCode, String message, String developerMessage) {
		super(errorCode, message, developerMessage);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public InvalidArgumentException(ErrorCodes errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public InvalidArgumentException(ErrorCodes errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public InvalidArgumentException(ErrorCodes errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param errorCode
	 */
	public InvalidArgumentException(ErrorCodes errorCode) {
		super(errorCode);
	}

	/**
	 * @param errorCodes
	 * @param message
	 * @param developerMessage
	 */
	public InvalidArgumentException(List<ErrorCodes> errorCodes, String message, String developerMessage) {
		super(errorCodes, message, developerMessage);
	}

	/**
	 * @param errorCodes
	 * @param message
	 * @param cause
	 */
	public InvalidArgumentException(List<ErrorCodes> errorCodes, String message, Throwable cause) {
		super(errorCodes, message, cause);
	}

	/**
	 * @param errorCodes
	 * @param message
	 */
	public InvalidArgumentException(List<ErrorCodes> errorCodes, String message) {
		super(errorCodes, message);
	}

	/**
	 * @param errorCodes
	 * @param cause
	 */
	public InvalidArgumentException(List<ErrorCodes> errorCodes, Throwable cause) {
		super(errorCodes, cause);
	}

	/**
	 * @param errorCodes
	 */
	public InvalidArgumentException(List<ErrorCodes> errorCodes) {
		super(errorCodes);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param str
	 */
	public InvalidArgumentException(String str) {
		super(str);
	}

	/**
	 * @param cause
	 */
	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}
}