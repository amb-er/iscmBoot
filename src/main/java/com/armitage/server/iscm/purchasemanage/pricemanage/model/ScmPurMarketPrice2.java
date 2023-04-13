package com.armitage.server.iscm.purchasemanage.pricemanage.model;

import java.math.BigDecimal;
import java.util.List;

public class ScmPurMarketPrice2 extends ScmPurMarketPrice{

    public static final String FN_PRICE ="price";
	private String planNo;
	private BigDecimal price;
	private String enquiryGroupName;
	private String enquiryName;
	private String creatorName;
	private String editorName;
	private String checkerName;
	private String statusName;
	private long itemId;
	private List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList;
	
	public String getPlanNo() {
		return planNo;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getEnquiryGroupName() {
		return enquiryGroupName;
	}

	public void setEnquiryGroupName(String enquiryGroupName) {
		this.enquiryGroupName = enquiryGroupName;
	}

	public String getEnquiryName() {
		return enquiryName;
	}

	public void setEnquiryName(String enquiryName) {
		this.enquiryName = enquiryName;
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

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<ScmPurMarketPriceEntry2> getScmPurMarketPriceEntryList() {
		return scmPurMarketPriceEntryList;
	}

	public void setScmPurMarketPriceEntryList(
			List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList) {
		this.scmPurMarketPriceEntryList = scmPurMarketPriceEntryList;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public ScmPurMarketPrice2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ScmPurMarketPrice2(boolean defaultValue) {
		super(defaultValue);
		// TODO Auto-generated constructor stub
	}
	
	
}
