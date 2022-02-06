package com.wb.s3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.wb.response.FileUploadResponse;

@Component
public class S3StorageService {

	public FileUploadResponse uploadFile(MultipartFile file, Regions regions, String key, String secret,
			String bucketName) {
		FileUploadResponse response = new FileUploadResponse();
		File fileObj = convertMultiPartFileToFile(file);
		AWSCredentials credentials = new BasicAWSCredentials(key,
				secret);

		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(regions).build();

		String fileName = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + "_" + fileObj.getName();

		final PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, fileObj);
		PutObjectResult putObjectResult = s3client.putObject(putObjectRequest);
		String resourceUrl = ((AmazonS3Client) s3client).getResourceUrl(bucketName, fileName);
		response.setFileName(fileName);
		response.setUrl(resourceUrl);
		response.setOriginalFileName(file.getOriginalFilename());
		return response;
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedFile;
	}

}
