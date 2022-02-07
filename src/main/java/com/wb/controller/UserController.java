package com.wb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wb.exception.beans.InvalidArgumentException;
import com.wb.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public Object signup(
			@RequestParam(value = "profileImageFile", required = false) MultipartFile profileImageFile,
			@RequestParam(value = "qualificationCertificateFiles", required = false) List<MultipartFile> qualificationCertificateFiles,
			@RequestParam(value = "nationalIdFiles", required = false) List<MultipartFile> nationalIdFiles,
			@RequestParam(value = "portfolioFiles", required = false) List<MultipartFile> portfolioFiles,
			@RequestParam("request") String request)
			throws InvalidArgumentException, JsonMappingException, JsonProcessingException {
		return userService.signup(request, profileImageFile, qualificationCertificateFiles, nationalIdFiles, portfolioFiles);
	}
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test() {
		return "success";
	}
}
