package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.math.BigDecimal;

public class ScmPurReceiveEntry2 extends ScmPurReceiveEntry {
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_PURUNITNAME = "purUnitName";
	public static final String FN_PIEUNITNAME = "pieUnitName";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_CONCESSIVERECRATESTR = "concessiveRecRateStr";
	public static final String FN_PURCONVRATE = "purConvRate";
	
	private String itemNo;
	private String itemName;
	private String spec;
	private String purUnitName;
	private String pieUnitName;
	private String baseUnitName;
	private String unitName;
	private BigDecimal convrate; //库存单位转换系数
	private boolean choosed;
	private String taxRateStr;
	private String concessiveRecRateStr;
    private BigDecimal receiveTopRatio;	//超收比例
	private String wareHouseNo;
	private String wareHouseName;
	private String useOrgUnitName;
	private BigDecimal purConvRate; //采购单位转换系数
	private String attachmentName;
	private long attachmentId;
	private String periodValid;
	private int periodValidDays;
	private String pvNo;
	private long poPriceBillId;
	
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public long getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public String getTaxRateStr() {
		return taxRateStr;
	}
	public void setTaxRateStr(String taxRateStr) {
		this.taxRateStr = taxRateStr;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
	
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public String getConcessiveRecRateStr() {
		return concessiveRecRateStr;
	}
	public void setConcessiveRecRateStr(String concessiveRecRateStr) {
		this.concessiveRecRateStr = concessiveRecRateStr;
	}
	
	public BigDecimal getReceiveTopRatio() {
		return receiveTopRatio;
	}
	public void setReceiveTopRatio(BigDecimal receiveTopRatio) {
		this.receiveTopRatio = receiveTopRatio;
	}
	public String getWareHouseNo() {
		return wareHouseNo;
	}
	public void setWareHouseNo(String wareHouseNo) {
		this.wareHouseNo = wareHouseNo;
	}
	public String getWareHouseName() {
		return wareHouseName;
	}
	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
	}
	public String getUseOrgUnitName() {
		return useOrgUnitName;
	}
	public void setUseOrgUnitName(String useOrgUnitName) {
		this.useOrgUnitName = useOrgUnitName;
	}
	public BigDecimal getPurConvRate() {
		return purConvRate;
	}
	public void setPurConvRate(BigDecimal purConvRate) {
		this.purConvRate = purConvRate;
	}
	public String getPeriodValid() {
		return periodValid;
	}
	public void setPeriodValid(String periodValid) {
		this.periodValid = periodValid;
	}
	public int getPeriodValidDays() {
		return periodValidDays;
	}
	public void setPeriodValidDays(int periodValidDays) {
		this.periodValidDays = periodValidDays;
	}
	public String getPvNo() {
		return pvNo;
	}
	public void setPvNo(String pvNo) {
		this.pvNo = pvNo;
	}
	public long getPoPriceBillId() {
		return poPriceBillId;
	}
	public void setPoPriceBillId(long poPriceBillId) {
		this.poPriceBillId = poPriceBillId;
	}
	public ScmPurReceiveEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
			this.taxRateStr="0%";
		}
	}

	public ScmPurReceiveEntry2() {
		super();
	}
}