package com.wb.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.wb.exception.beans.InvalidArgumentException;

public interface UserService {

	Object signup(String request, MultipartFile profileImage, List<MultipartFile> qualificationCertificateFiles,
			List<MultipartFile> nationalIdFiles, List<MultipartFile> portfolioFiles)
			throws InvalidArgumentException, JsonMappingException, JsonProcessingException;

}
