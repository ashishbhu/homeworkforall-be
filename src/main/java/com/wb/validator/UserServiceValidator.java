package com.wb.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.wb.constant.Country;
import com.wb.constant.ServiceConstant;
import com.wb.constant.UserType;
import com.wb.entity.User;
import com.wb.exception.beans.InvalidArgumentException;
import com.wb.exception.constants.ErrorCodes;
import com.wb.repository.UserRepository;
import com.wb.request.SignupRequest;
import com.wb.utils.Utility;

import java.util.List;

import org.apache.commons.io.FilenameUtils;

@Component
public class UserServiceValidator {
	
	@Autowired
	private UserRepository userRepository;

	public void validateSignupRequest(SignupRequest request, MultipartFile profileImage,
			List<MultipartFile> qualificationCertificateFiles, List<MultipartFile> nationalIdFiles,
			List<MultipartFile> portfolioFiles) throws InvalidArgumentException {
		if (request == null) {
			throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Missing Request Data",
					"Missing Request Data");
		}
		StringBuilder message = new StringBuilder();
		if (StringUtils.isEmpty(request.getEmail()) || !Utility.isValidEmail(request.getEmail())) {
			message.append("Invalid email; ");
		}
		if (StringUtils.isEmpty(request.getPassword())) {
			message.append("Invalid password; ");
		}
		if (StringUtils.isEmpty(request.getUserType()) || !Utility.isValidUserType(request.getUserType())) {
			message.append("Invalid userType; ");
		}
		if (StringUtils.isNotEmpty(request.getUserType()) && request.getUserType().equals(UserType.ADMIN.name())) {
			message.append("userType not allowed; ");
		}

		if (message.length() > 0) {
			throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, message.toString(), message.toString());
		}
		
		User user = userRepository.findByEmail(request.getEmail());
		if(user != null) {
			throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Email already exist", "Email already exist");
		}
		if(StringUtils.isEmpty(request.getUserName())) {
			User userUserName = userRepository.findByUserName(request.getUserName());
			if(userUserName != null) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "User name already exist", "User name already exist");
			}
		}

		if (request.getUserType().equals(UserType.WRITER.name())) {
			if (StringUtils.isEmpty(request.getPhone())) {
				message.append("Invalid phone; ");
			}
			if (StringUtils.isEmpty(request.getCountry()) || !Utility.isValidCountry(request.getCountry())) {
				message.append("Invalid country; ");
			}
			if (StringUtils.isEmpty(request.getQualification())) {
				message.append("Invalid qualification; ");
			}
			if(StringUtils.isEmpty(request.getLinkedinUrl())) {
				message.append("Invalid linkdin url; ");
			}

			if (message.length() > 0) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, message.toString(), message.toString());
			}

			if (!Utility.isValidPhone(request.getPhone(), Country.valueOf(request.getCountry()).countryCode())) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Invalid phone passed",
						"Invalid phone passed");
			}
		}
		if (profileImage != null) {
			String extension = FilenameUtils.getExtension(profileImage.getOriginalFilename());
			if (!Utility.isValidImageFileExtension(extension.toUpperCase())) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "File type not allowed",
						"File type not allowed");
			}
			long sizeInKB = profileImage.getSize() / 1024;
			if (sizeInKB > ServiceConstant.MAX_ALLOWED_IMAGE_FILE_IN_KB) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST,
						"File size not allowed. file size should be less than "
								+ ServiceConstant.MAX_ALLOWED_IMAGE_FILE_IN_KB + 1 + " KB",
						"File size not allowed. file size should be less than "
								+ ServiceConstant.MAX_ALLOWED_IMAGE_FILE_IN_KB + 1 + " KB");
			}

		}
		
		if(qualificationCertificateFiles != null && !qualificationCertificateFiles.isEmpty()) {
			if(qualificationCertificateFiles.size() > 2) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Max 2 file allowed for qualification certificates", "Max 2 file allowed for qualification certificates");
			}
			for (MultipartFile qualificationCertificateFile : qualificationCertificateFiles) {
				String extension = FilenameUtils.getExtension(qualificationCertificateFile.getOriginalFilename());
				if(!Utility.isValidFileExtensions(extension.toUpperCase())) {
					throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Qualification certificate file type with extension "+extension+" not allowed",
							"Qualification certificate file type with extension "+extension+" not allowed");
				}	
				
				long sizeInKB = qualificationCertificateFile.getSize() / 1024;
				if (sizeInKB > ServiceConstant.MAX_ALLOWED_FILE_IN_KB) {
					throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST,
							"File size not allowed. file size should be less than "
									+ ServiceConstant.MAX_ALLOWED_FILE_IN_KB/1024 + 1 + " MB",
							"File size not allowed. file size should be less than "
									+ ServiceConstant.MAX_ALLOWED_FILE_IN_KB/1024 + 1 + " MB");
				}
			}
			
		}
		
		if(nationalIdFiles != null && !nationalIdFiles.isEmpty()) {
			if(nationalIdFiles.size() > 2) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Max 2 file allowed for nationalId", "Max 2 file allowed for nationalId");
			}
			
			for (MultipartFile nationalIdFile : nationalIdFiles) {
				String extension = FilenameUtils.getExtension(nationalIdFile.getOriginalFilename());
				if(!Utility.isValidFileExtensions(extension.toUpperCase())) {
					throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "NationalId file type with extension "+extension+" not allowed",
							"NationalId file type with extension "+extension+" not allowed");
				}	
				
				long sizeInKB = nationalIdFile.getSize() / 1024;
				if (sizeInKB > ServiceConstant.MAX_ALLOWED_FILE_IN_KB) {
					throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST,
							"File size not allowed. file size should be less than "
									+ ServiceConstant.MAX_ALLOWED_FILE_IN_KB/1024 + 1 + " MB",
							"File size not allowed. file size should be less than "
									+ ServiceConstant.MAX_ALLOWED_FILE_IN_KB/1024 + 1 + " MB");
				}
			}
		}
		
		if(portfolioFiles != null && !portfolioFiles.isEmpty()) {
			if(portfolioFiles.size() > 10) {
				throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Max 2 file allowed for portfolio", "Max 2 file allowed for portfolio");
			}
			
			for (MultipartFile portfolio : portfolioFiles) {
				String extension = FilenameUtils.getExtension(portfolio.getOriginalFilename());
				if(!Utility.isValidFileExtensions(extension.toUpperCase())) {
					throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST, "Portfolio file type with extension "+extension+" not allowed",
							"Portfolio file type with extension "+extension+" not allowed");
				}	
				
				long sizeInKB = portfolio.getSize() / 1024;
				if (sizeInKB > ServiceConstant.MAX_ALLOWED_FILE_IN_KB) {
					throw new InvalidArgumentException(ErrorCodes.INVALID_REQUEST,
							"File size not allowed. file size should be less than "
									+ ServiceConstant.MAX_ALLOWED_FILE_IN_KB/1024 + 1 + " MB",
							"File size not allowed. file size should be less than "
									+ ServiceConstant.MAX_ALLOWED_FILE_IN_KB/1024 + 1 + " MB");
				}
			}
		}

	}

}
