package com.wb.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wb.constant.ServiceConstant;
import com.wb.constant.UserType;
import com.wb.exception.beans.InvalidArgumentException;
import com.wb.manager.UserServiceManager;
import com.wb.request.SignupRequest;
import com.wb.response.CommonResponseModel;
import com.wb.service.UserService;
import com.wb.validator.UserServiceValidator;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private UserServiceValidator userServiceValidator;

	@Autowired
	private UserServiceManager userServiceManager;

	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	@Transactional
	@Override
	public Object signup(String request, MultipartFile profileImage,
			List<MultipartFile> qualificationCertificateFiles, List<MultipartFile> nationalIdFiles,
			List<MultipartFile> portfolioFiles)
			throws InvalidArgumentException, JsonMappingException, JsonProcessingException {
		CommonResponseModel responseModel = new CommonResponseModel();
		Map<String, Object> responseMap = new HashMap<>();
		SignupRequest signupRequest = objectMapper.readValue(request, SignupRequest.class);
		userServiceValidator.validateSignupRequest(signupRequest, profileImage, qualificationCertificateFiles,
				nationalIdFiles, portfolioFiles);
		UserType userType = UserType.valueOf(signupRequest.getUserType());
		try {
			userServiceManager.signup(signupRequest, profileImage, qualificationCertificateFiles, nationalIdFiles,
					portfolioFiles);
			String message = "Signup Successfully!";
			if(userType == UserType.WRITER) {
				message = "Signup Successfully. Your account is under review you will got an email when account will activated.";
			} else if(userType == UserType.STUDENT) {
				String token = null;
				responseMap.put(ServiceConstant.TOKEN_LOWER, token);
			}
			responseMap.put("userType", userType);
			responseModel.setMessage(message);
			responseModel.setStatus(ServiceConstant.SUCCESS_LOWER);
			
			responseModel.setData(responseMap);
		} catch (Exception e) {
			e.printStackTrace();
			responseModel.setStatus(ServiceConstant.FAIL_LOWER);
			responseModel.setMessage(e.getMessage());
		}
		return responseModel;
	}

}
