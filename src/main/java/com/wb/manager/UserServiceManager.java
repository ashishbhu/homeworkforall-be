package com.wb.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.regions.Regions;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wb.constant.Country;
import com.wb.constant.UserType;
import com.wb.entity.NationalId;
import com.wb.entity.Portfolio;
import com.wb.entity.QualificationCertificate;
import com.wb.entity.User;
import com.wb.exception.beans.InternalServiceException;
import com.wb.exception.constants.ErrorCodes;
import com.wb.repository.NationalIdRepository;
import com.wb.repository.PortfolioRepository;
import com.wb.repository.QualificationCertificateRepository;
import com.wb.repository.UserRepository;
import com.wb.request.S3RequestData;
import com.wb.request.SignupRequest;
import com.wb.response.FileUploadResponse;
import com.wb.s3.service.S3StorageService;
import com.wb.utils.RandomString;

@Component
public class UserServiceManager {

	@Value("${aws.s3.key}")
	private String s3BucketKey;

	@Value("${aws.s3.secret}")
	private String s3BuckeySecret;

	@Value("${aws.wb.image.bucket.name}")
	private String s3BucketName;

	@Value("${aws.s3.region}")
	private String s3BucketRegion;

	@Autowired
	private S3StorageService s3StorageService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private QualificationCertificateRepository qualificationCertificateRepository;

	@Autowired
	private NationalIdRepository nationalIdRepository;

	@Autowired
	private PortfolioRepository portfolioRepository;
	
	@Autowired
	private EmailServiceManager emailServiceManager;

	private RandomString randomString = new RandomString();

	private ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);

	public void signup(SignupRequest signupRequest, MultipartFile profileImage,
			List<MultipartFile> qualificationCertificateFiles, List<MultipartFile> nationalIdFiles,
			List<MultipartFile> portfolioFiles) throws JsonProcessingException, InternalServiceException {
		User user = new User();
		user.setCountry(Country.valueOf(signupRequest.getCountry()));
		user.setEmail(signupRequest.getEmail());
		user.setFirstName(signupRequest.getFirstName());
		user.setMiddleName(signupRequest.getMiddleName());
		user.setLastName(signupRequest.getLastName());
		user.setLinkedinUrl(signupRequest.getLinkedinUrl());
		user.setPassword(signupRequest.getPassword());
		user.setPhone(signupRequest.getPhone());
		user.setQualification(signupRequest.getQualification());
		user.setType(UserType.valueOf(signupRequest.getUserType()));

		// need to modify userName logic
		user.setUserName(signupRequest.getEmail().substring(0, signupRequest.getEmail().lastIndexOf("@"))
				+ (randomString.nextString().toUpperCase()));

		FileUploadResponse uploadFile = null;
		Regions regions = Regions.valueOf(s3BucketRegion);
		try {
			uploadFile = s3StorageService.uploadFile(profileImage, regions, s3BucketKey, s3BuckeySecret, s3BucketName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		S3RequestData s3RequestData = new S3RequestData();
		s3RequestData.setBucketName(s3BucketName);
		s3RequestData.setBucketRegion(s3BucketRegion);
		s3RequestData.setKey(s3BucketKey);
		s3RequestData.setSecret(s3BuckeySecret);
		if (uploadFile != null) {
			s3RequestData.setFileName(uploadFile.getFileName());
			s3RequestData.setOriginalFileName(uploadFile.getOriginalFileName());
			user.setProfileImage(uploadFile.getUrl());
		}
		user.setS3RequestDataStr(objectMapper.writeValueAsString(s3RequestData));
		userRepository.save(user);
		if (qualificationCertificateFiles != null) {
			saveQualificationCertificate(user, qualificationCertificateFiles, s3RequestData);
		}
		if (nationalIdFiles != null) {
			saveNationalIdFiles(user, nationalIdFiles, s3RequestData);
		}
		if (portfolioFiles != null) {
			savePortfolioFiles(user, portfolioFiles, s3RequestData);
		}
		
		try {
			emailServiceManager.sendEmail(signupRequest.getEmail(), "Writing Bucket Signup", "You have signed up successfully!. Your Account is under review you will got another email when account will be activated.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void saveQualificationCertificate(User user, List<MultipartFile> qualificationCertificateFiles,
			S3RequestData s3RequestData) throws InternalServiceException {
		try {
			List<QualificationCertificate> certificates = new ArrayList<>();
			Regions regions = Regions.valueOf(s3RequestData.getBucketRegion());
			for (MultipartFile qualificationCertificateFile : qualificationCertificateFiles) {
				FileUploadResponse uploadFile = s3StorageService.uploadFile(qualificationCertificateFile, regions,
						s3RequestData.getKey(), s3RequestData.getSecret(), s3RequestData.getBucketName());
				QualificationCertificate certificate = new QualificationCertificate();
				s3RequestData.setFileName(uploadFile.getFileName());
				s3RequestData.setOriginalFileName(uploadFile.getOriginalFileName());
				certificate.setS3RequestDataStr(objectMapper.writeValueAsString(s3RequestData));
				certificate.setUrl(uploadFile.getUrl());
				certificate.setUser(user);
				certificates.add(certificate);
			}
			qualificationCertificateRepository.saveAll(certificates);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServiceException(ErrorCodes.SOMETHING_WENT_WRONG, e.getMessage());
		}

	}

	private void saveNationalIdFiles(User user, List<MultipartFile> nationalIdFiles, S3RequestData s3RequestData) throws InternalServiceException {
		try {
			List<NationalId> ids = new ArrayList<>();

			Regions regions = Regions.valueOf(s3RequestData.getBucketRegion());
			for (MultipartFile nationalIdFile : nationalIdFiles) {
				FileUploadResponse uploadFile = s3StorageService.uploadFile(nationalIdFile, regions,
						s3RequestData.getKey(), s3RequestData.getSecret(), s3RequestData.getBucketName());
				NationalId id = new NationalId();
				s3RequestData.setFileName(uploadFile.getFileName());
				s3RequestData.setOriginalFileName(uploadFile.getOriginalFileName());
				id.setS3RequestDataStr(objectMapper.writeValueAsString(s3RequestData));
				id.setUrl(uploadFile.getUrl());
				id.setUser(user);
				ids.add(id);
			}
			nationalIdRepository.saveAll(ids);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServiceException(ErrorCodes.SOMETHING_WENT_WRONG, e.getMessage());
		}
	}

	private void savePortfolioFiles(User user, List<MultipartFile> portfolioFiles, S3RequestData s3RequestData) throws InternalServiceException {
		try {
			List<Portfolio> portfolios = new ArrayList<>();

			Regions regions = Regions.valueOf(s3RequestData.getBucketRegion());
			for (MultipartFile portfolioFile : portfolioFiles) {
				FileUploadResponse uploadFile = s3StorageService.uploadFile(portfolioFile, regions,
						s3RequestData.getKey(), s3RequestData.getSecret(), s3RequestData.getBucketName());

				s3RequestData.setFileName(uploadFile.getFileName());
				s3RequestData.setOriginalFileName(uploadFile.getOriginalFileName());
				Portfolio portfolio = new Portfolio();
				portfolio.setS3RequestDataStr(objectMapper.writeValueAsString(s3RequestData));
				portfolio.setUrl(uploadFile.getUrl());
				portfolio.setUser(user);
				portfolios.add(portfolio);
			}
			portfolioRepository.saveAll(portfolios);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InternalServiceException(ErrorCodes.SOMETHING_WENT_WRONG, e.getMessage());
		}
	}
	

}
