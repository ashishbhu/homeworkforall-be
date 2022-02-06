package com.wb.constant;

public enum Country {
	INDIA("IN");
	
	
	private String countryCode;

	Country(String countryCode) {
		this.countryCode = countryCode;
	}
	
	public String countryCode() {
		return countryCode;
	}
	
}
