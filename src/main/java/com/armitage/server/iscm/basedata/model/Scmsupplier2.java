package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;
import java.util.Date;

public class Scmsupplier2 extends Scmsupplier {
	public static final String FN_VENDORTAXRATE ="vendorTaxRate";
    public static final String FN_TAXPAYERTYPE ="taxpayerType";
	
	private String classCode;
    private String className; 
    private String taxpayerType;
    private BigDecimal vatRate;
    private int groupLevel;
    private String longNo;
    private String roleName;
    private String creatorName;
    private String editorName;
    private String uniqueNo;
    private boolean binded;
    private long platVendorId;
	private String paltOrgUnitNo;
	private String appId;
	//sendOA
	private String contactPerson;
	private String bankAccNo;
	private String bankName;
	private String qualifyType;
	private String vatRateString;
	private String vendorTaxRate;
	private String vendorContactPerson;
	private String vendorBankName;
	private String vendorbankAccNo;
	private String qualifieAuditRemarks;
	private String registerSource;
	
	public long getPlatVendorId() {
		return platVendorId;
	}

	public void setPlatVendorId(long platVendorId) {
		this.platVendorId = platVendorId;
	}

	public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTaxpayerType() {
		return taxpayerType;
	}

	public void setTaxpayerType(String taxpayerType) {
		this.taxpayerType = taxpayerType;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

	public String getLongNo() {
		return longNo;
	}

	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getEditorName() {
		return editorName;
	}

	public void setEditorName(String editorName) {
		this.editorName = editorName;
	}

	public String getUniqueNo() {
		return uniqueNo;
	}

	public void setUniqueNo(String uniqueNo) {
		this.uniqueNo = uniqueNo;
	}

	public boolean isBinded() {
		return binded;
	}

	public void setBinded(boolean binded) {
		this.binded = binded;
	}

	public String getPaltOrgUnitNo() {
		return paltOrgUnitNo;
	}

	public void setPaltOrgUnitNo(String paltOrgUnitNo) {
		this.paltOrgUnitNo = paltOrgUnitNo;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getQualifyType() {
		return qualifyType;
	}

	public void setQualifyType(String qualifyType) {
		this.qualifyType = qualifyType;
	}

	public String getVatRateString() {
		return vatRateString;
	}

	public void setVatRateString(String vatRateString) {
		this.vatRateString = vatRateString;
	}

	public String getVendorTaxRate() {
		return vendorTaxRate;
	}

	public void setVendorTaxRate(String vendorTaxRate) {
		this.vendorTaxRate = vendorTaxRate;
	}

	public String getVendorContactPerson() {
		return vendorContactPerson;
	}

	public void setVendorContactPerson(String vendorContactPerson) {
		this.vendorContactPerson = vendorContactPerson;
	}

	public String getVendorBankName() {
		return vendorBankName;
	}

	public void setVendorBankName(String vendorBankName) {
		this.vendorBankName = vendorBankName;
	}

	public String getVendorbankAccNo() {
		return vendorbankAccNo;
	}

	public void setVendorbankAccNo(String vendorbankAccNo) {
		this.vendorbankAccNo = vendorbankAccNo;
	}

	public String getQualifieAuditRemarks() {
		return qualifieAuditRemarks;
	}

	public void setQualifieAuditRemarks(String qualifieAuditRemarks) {
		this.qualifieAuditRemarks = qualifieAuditRemarks;
	}

	public String getRegisterSource() {
		return registerSource;
	}

	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}

	public Scmsupplier2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.vatRate=BigDecimal.ZERO;
			this.binded=false;
		}
	}

	public Scmsupplier2() {
		super();
	}
}
