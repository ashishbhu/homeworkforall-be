package com.wb.exception.constants;

/**
 * 
 * @author dileep
 * @date 18-Oct-2019
 *
 */
public enum ErrorCodes {
	SOMETHING_WENT_WRONG("SOMETHING_WENT_WRONG"), UNAUTHORIZED("UNAUTHORIZED"), INTERNAL_SERVICE_ERROR(
			"INTERNAL_SERVICE_ERROR"), INVALID_REQUEST("INVALID_REQUEST"), INVALID_USER_ID("INVALID_USER_ID"), PAYOUT_CALL_FAILED("PAYOUT_CALL_FAILED"),
	DUPLICATE_TRANSACTION_REQUEST("DUPLICATE_TRANSACTION_REQUEST"), NOT_FOUND("NOT_FOUND"), INVALID_REFUND_AMOUNT("INVALID_REFUND_AMOUNT"),
	SIGNATURE_VERIFICATION_FAILED("SIGNATURE_VERIFICATION_FAILED"), PAYOUT_STATUS_UPDATE_NOT_ALLOWED("PAYOUT_STATUS_UPDATE_NOT_ALLOWED"), 
	BENEFICIARY_PHONE_IS_BLOCKED("BENEFICIARY_PHONE_IS_BLOCKED");

	private String value;

	private ErrorCodes(String value) {
		this.value = value;
	}

	/**
	 * @param message
	 * @return
	 */

	public String getValue() {
		return value;
	}

	public static ErrorCodes getErrorCodeByName(String message) {
		for (ErrorCodes tempErrorCodes : ErrorCodes.values()) {
			if (tempErrorCodes.value.equalsIgnoreCase(message)) {
				return tempErrorCodes;
			}
		}
		throw new IllegalArgumentException("Invalid message passed.");
	}
}