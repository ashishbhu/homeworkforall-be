package com.wb.exception.beans;

import java.util.List;

import com.wb.exception.constants.ErrorCodes;

public class InternalServiceException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public InternalServiceException() {
	}

	/**
	 * @param str
	 */
	public InternalServiceException(String str) {
		super(str);
	}

	/**
	 * @param cause
	 */
	public InternalServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InternalServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param errorCodes
	 */
	public InternalServiceException(List<ErrorCodes> errorCodes) {
		super(errorCodes);
	}

	/**
	 * @param errorCodes
	 * @param message
	 */
	public InternalServiceException(List<ErrorCodes> errorCodes, String message) {
		super(errorCodes, message);
	}

	/**
	 * @param errorCodes
	 * @param cause
	 */
	public InternalServiceException(List<ErrorCodes> errorCodes, Throwable cause) {
		super(errorCodes, cause);
	}

	/**
	 * @param errorCodes
	 * @param message
	 * @param cause
	 */
	public InternalServiceException(List<ErrorCodes> errorCodes, String message, Throwable cause) {
		super(errorCodes, message, cause);
	}

	/**
	 * @param errorCode
	 */
	public InternalServiceException(ErrorCodes errorCode) {
		super(errorCode);
	}

	/**
	 * @param errorCode
	 * @param message
	 */
	public InternalServiceException(ErrorCodes errorCode, String message) {
		super(errorCode, message);
	}

	/**
	 * @param errorCode
	 * @param cause
	 */
	public InternalServiceException(ErrorCodes errorCode, Throwable cause) {
		super(errorCode, cause);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param cause
	 */
	public InternalServiceException(ErrorCodes errorCode, String message, Throwable cause) {
		super(errorCode, message, cause);
	}

	/**
	 * @param message
	 * @param developerMessage
	 */
	public InternalServiceException(String message, String developerMessage) {
		super(message, developerMessage);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param developerMessage
	 */
	public InternalServiceException(ErrorCodes errorCode, String message, String developerMessage) {
		super(errorCode, message, developerMessage);
	}

	/**
	 * @param errorCode
	 * @param message
	 * @param developerMessage
	 * @param cause
	 */
	public InternalServiceException(ErrorCodes errorCode, String message, String developerMessage, Throwable cause) {
		super(errorCode, message, developerMessage, cause);
	}

	/**
	 * @param errorCodes
	 * @param message
	 * @param developerMessage
	 */
	public InternalServiceException(List<ErrorCodes> errorCodes, String message, String developerMessage) {
		super(errorCodes, message, developerMessage);
	}
}