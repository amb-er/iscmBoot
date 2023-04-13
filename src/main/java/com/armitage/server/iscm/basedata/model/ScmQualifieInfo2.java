package com.armitage.server.iscm.basedata.model;

import java.util.List;

import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;

public class ScmQualifieInfo2 extends ScmQualifieInfo {

	public static final String FN_VENDORSTATUS = "vendorStatus";
	public static final String FN_TYPENAME = "typeName";
	public static final String FN_NEWVENDORSTATUS = "newVendorStatus";
	public static final String FN_NEWQUALIFICATIONSTATUS = "newQualificationStatus";
	public static final String FN_VENDORQUALIFICATIONSTATUS = "vendorQualificationStatus";
	
	private String vendorStatus;
	private String typeName;
	private String qualifieTypeCode;
	private long attachMentId;
	private String attachMentDocType;
	private String newVendorStatus;
	private String newQualificationStatus;
	private String vendorQualificationStatus;
    private List<ScmBaseAttachment> scmBaseAttachmentList;
	
	public String getVendorStatus() {
        return vendorStatus;
    }

    public void setVendorStatus(String vendorStatus) {
        this.vendorStatus = vendorStatus;
    }
    
	public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
    public String getQualifieTypeCode() {
		return qualifieTypeCode;
	}

	public void setQualifieTypeCode(String qualifieTypeCode) {
		this.qualifieTypeCode = qualifieTypeCode;
	}

	public long getAttachMentId() {
		return attachMentId;
	}

	public void setAttachMentId(long attachMentId) {
		this.attachMentId = attachMentId;
	}

	public String getAttachMentDocType() {
		return attachMentDocType;
	}

	public void setAttachMentDocType(String attachMentDocType) {
		this.attachMentDocType = attachMentDocType;
	}

	public String getNewVendorStatus() {
		return newVendorStatus;
	}

	public void setNewVendorStatus(String newVendorStatus) {
		this.newVendorStatus = newVendorStatus;
	}

	public String getNewQualificationStatus() {
		return newQualificationStatus;
	}

	public void setNewQualificationStatus(String newQualificationStatus) {
		this.newQualificationStatus = newQualificationStatus;
	}

	public String getVendorQualificationStatus() {
		return vendorQualificationStatus;
	}

	public void setVendorQualificationStatus(String vendorQualificationStatus) {
		this.vendorQualificationStatus = vendorQualificationStatus;
	}

	public List<ScmBaseAttachment> getScmBaseAttachmentList() {
		return scmBaseAttachmentList;
	}

	public void setScmBaseAttachmentList(List<ScmBaseAttachment> scmBaseAttachmentList) {
		this.scmBaseAttachmentList = scmBaseAttachmentList;
	}

	public ScmQualifieInfo2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
		}
	}
	
	public ScmQualifieInfo2(){
		super();
	}
}
