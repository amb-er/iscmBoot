package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmPurPriceAdvQuery  extends BaseModel{
	public static final String FN_PMNO = "pmNo";
	public static final String FN_PRICINGCODE = "pricingCode";
	public static final String FN_CREATEDATEFROM = "createDateFrom"; 
    public static final String FN_CREATEDATETO = "createDateTo"; 
	public static final String FN_PMDATEFROM = "pmDateFrom"; 
    public static final String FN_PMDATETO = "pmDateTo"; 
    public static final String FN_SELVNDRID = "selVndrId";
    public static final String FN_PRICEBIZTYPE = "priceBizType";
	
	private String pmNo;
	private String pricingCode;
	private Date pmDateFrom;
	private Date pmDateTo;
	private Date createDateFrom;
	private Date createDateTo;
	private long selVndrId;
	private String priceBizType;
	
	public String getPmNo() {
		return pmNo;
	}
	public void setPmNo(String pmNo) {
		this.pmNo = pmNo;
	}

	public String getPricingCode() {
		return pricingCode;
	}
	public void setPricingCode(String pricingCode) {
		this.pricingCode = pricingCode;
	}
	public Date getPmDateFrom() {
		return pmDateFrom;
	}
	public void setPmDateFrom(Date pmDateFrom) {
		this.pmDateFrom = pmDateFrom;
	}
	public Date getPmDateTo() {
		return pmDateTo;
	}
	public void setPmDateTo(Date pmDateTo) {
		this.pmDateTo = pmDateTo;
	}
	public Date getCreateDateFrom() {
		return createDateFrom;
	}
	public void setCreateDateFrom(Date createDateFrom) {
		this.createDateFrom = createDateFrom;
	}
	public Date getCreateDateTo() {
		return createDateTo;
	}
	public void setCreateDateTo(Date createDateTo) {
		this.createDateTo = createDateTo;
	}
	
	public long getSelVndrId() {
		return selVndrId;
	}
	public void setSelVndrId(long selVndrId) {
		this.selVndrId = selVndrId;
	}
	public String getPriceBizType() {
		return priceBizType;
	}
	public void setPriceBizType(String priceBizType) {
		this.priceBizType = priceBizType;
	}
	@Override
	public String getPkKey() {
		return null;
	}
	@Override
	public long getPK() {
		return 0;
	}
	@Override
	public String[] getRequiredFields() {
		return null;
	}
	@Override
	public String[] getFieldNames() {
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}
	
}
