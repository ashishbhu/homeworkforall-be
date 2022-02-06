package com.wb.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.wb.constant.Country;
import com.wb.constant.UserType;

@Component
public class Utility {
	
	private static List<String> imageFileExtensions = new ArrayList<>();
	
	private static List<String> fileExtensions = new ArrayList<>();
	
	static {
		imageFileExtensions.add("JPEG");
		imageFileExtensions.add("JPG");
		imageFileExtensions.add("PNG");
	}
	static {
		fileExtensions.addAll(imageFileExtensions);
		fileExtensions.add("PDF");
		fileExtensions.add("DOC");
		fileExtensions.add("DOCS");
		fileExtensions.add("XLS");
		fileExtensions.add("XLSX");
	}

	public static boolean isValidEmail(String email) {
		boolean isValid = false;
		try {
			boolean allowLocal = true;
			isValid = EmailValidator.getInstance(allowLocal).isValid(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isValid;
	}

	public static boolean isValidUserType(String userType) {
		boolean isValid = false;
		for (UserType userTypeEnum : UserType.values()) {
			if (userType.equals(userTypeEnum.name())) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	public static boolean isValidPhone(String phone, String countryCode) {
		boolean isValid = true;
		if (!NumberUtils.isDigits(phone)) {
			return false;
		}
		PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
		PhoneNumber pNumber = null;
		try {
			pNumber = phoneUtil.parse(phone, countryCode);
			isValid = phoneUtil.isValidNumber(pNumber);
		} catch (NumberParseException e) {
			isValid = false;
		}
		return isValid;
	}

	public static boolean isValidCountry(String country) {
		boolean isValid = false;
		for (Country countryEnum : Country.values()) {
			if (country.equals(countryEnum.name())) {
				isValid = true;
				break;
			}
		}
		return isValid;
	}

	public static boolean isValidImageFileExtension(String extension) {
		return imageFileExtensions.contains(extension.toUpperCase());
	}
	
	public static boolean isValidFileExtensions(String extension) {
		return fileExtensions.contains(extension);
	}

	public static boolean isValidLinkdinUrl(String linkedinUrl) {
		boolean isValid = false;
		String regex = "^(https|http):\\/\\/[a-z]{2,3}\\.linkedin\\.com\\/.*$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(linkedinUrl);
		if(matcher.matches()) {
			isValid = true;
		}
		return isValid;
	}

}
