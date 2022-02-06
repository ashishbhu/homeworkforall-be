package com.wb.response;

import java.io.Serializable;

public class FileUploadResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String url;

	private String fileName;
	
	private String originalFileName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

}
