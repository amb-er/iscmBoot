package com.armitage.server.iscm.activityTask.audit.model;

import java.util.List;

public class ScmPurRequireUploadOA {
	private String id;
	private String fdTemplateId;
	private String docSubject;
	private ScmPurRequireUploadOAFormValues formValues;
	private String fdSource;
	private ScmPurRequireUploadOACreator docCreator;
	private String docStatus;
	private ScmPurRequireUploadOAAttachmentValues attachmentValues;
	private String flowParam;
	private String encrypt;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFdTemplateId() {
		return fdTemplateId;
	}
	public void setFdTemplateId(String fdTemplateId) {
		this.fdTemplateId = fdTemplateId;
	}
	public String getDocSubject() {
		return docSubject;
	}
	public void setDocSubject(String docSubject) {
		this.docSubject = docSubject;
	}
	public ScmPurRequireUploadOAFormValues getFormValues() {
		return formValues;
	}
	public void setFormValues(ScmPurRequireUploadOAFormValues formValues) {
		this.formValues = formValues;
	}
	public String getFdSource() {
		return fdSource;
	}
	public void setFdSource(String fdSource) {
		this.fdSource = fdSource;
	}
	public ScmPurRequireUploadOACreator getDocCreator() {
		return docCreator;
	}
	public void setDocCreator(ScmPurRequireUploadOACreator docCreator) {
		this.docCreator = docCreator;
	}
	public String getDocStatus() {
		return docStatus;
	}
	public void setDocStatus(String docStatus) {
		this.docStatus = docStatus;
	}
	public ScmPurRequireUploadOAAttachmentValues getAttachmentValues() {
		return attachmentValues;
	}
	public void setAttachmentValues(ScmPurRequireUploadOAAttachmentValues attachmentValues) {
		this.attachmentValues = attachmentValues;
	}
	public String getFlowParam() {
		return flowParam;
	}
	public void setFlowParam(String flowParam) {
		this.flowParam = flowParam;
	}
	public String getEncrypt() {
		return encrypt;
	}
	public void setEncrypt(String encrypt) {
		this.encrypt = encrypt;
	}
	
	
}
