package com.armitage.server.quartz.model.scmsupplier;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SupplierPlatQualifieInfoType {
	
	private String authStatus;
	private String fileData;
	private String fileMD5;
	private String fileType;
	private String qualifieTypeCode;
	private String reviewer;
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date reviewTime;
	public String getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(String authStatus) {
		this.authStatus = authStatus;
	}
	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}
	public String getFileMD5() {
		return fileMD5;
	}
	public void setFileMD5(String fileMD5) {
		this.fileMD5 = fileMD5;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getQualifieTypeCode() {
		return qualifieTypeCode;
	}
	public void setQualifieTypeCode(String qualifieTypeCode) {
		this.qualifieTypeCode = qualifieTypeCode;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	public Date getReviewTime() {
		return reviewTime;
	}
	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}
	
}
