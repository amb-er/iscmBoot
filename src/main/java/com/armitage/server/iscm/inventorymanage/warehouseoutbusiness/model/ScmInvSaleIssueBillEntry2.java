package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;
import java.util.Date;
 
public class ScmInvSaleIssueBillEntry2 extends ScmInvSaleIssueBillEntry{
	public static final String FN_ITEMNO = "itemNo";
	public static final String FN_ITEMNAME = "itemName";
	public static final String FN_SPEC = "spec";
	public static final String FN_GROUPNAME = "groupName";
	public static final String FN_UNITNAME = "unitName";
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_CONVRATE = "convrate";
	public static final String FN_TAXRATESTR = "taxRateStr";
	public static final String FN_SALETAXRATESTR = "saleTaxRateStr";
	public static final String FN_BASEUNITNAME = "baseUnitName";
	public static final String FN_SALEUNITNAME = "saleUnitName";
	public static final String FN_BALANCECUSTNAME = "balanceCustName";
    public static final String FN_INVQTY ="invQty";
    public static final String FN_BIZTYPE ="bizType";
    public static final String FN_CREATOR ="creator";
    public static final String FN_WHNAME = "whName";
    
    private String otNo;
    private String bizType;
    private Date bizDate;
	private Date postDate;
	private String status;
    private String creator;
	private String itemNo;
	private String itemName;
	private String spec;
	private String groupName;
	private String unitName;
	private boolean choosed;
	private BigDecimal convrate;
	private String taxRateStr;
	private String saleTaxRateStr;
	private String baseUnitName;
	private String saleUnitName;
	private String balanceCustName;
	private BigDecimal invQty;
	private String pieUnitName;
	private String whName;
	private String resultCode;
	private String resultText;
	private String longNo;//物资所属类别级别串
	private long groupId;
	private String groupCode;
	private String className;
	private long brandId;
	private String brandName;
	private String custClass;
	
	public String getGroupCode() {
		return groupCode;
	}
	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	public String getLongNo() {
		return longNo;
	}
	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public String getOtNo() {
		return otNo;
	}
	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public Date getBizDate() {
		return bizDate;
	}
	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}
	public String getTaxRateStr() {
        return taxRateStr;
    }
    public void setTaxRateStr(String taxRateStr) {
        this.taxRateStr = taxRateStr;
    }
    public String getSaleTaxRateStr() {
		return saleTaxRateStr;
	}
	public void setSaleTaxRateStr(String saleTaxRateStr) {
		this.saleTaxRateStr = saleTaxRateStr;
	}
	public boolean isChoosed() {
		return choosed;
	}
	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
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
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSaleUnitName() {
        return saleUnitName;
    }
    public void setSaleUnitName(String saleUnitName) {
        this.saleUnitName = saleUnitName;
    }
    
    public String getCustClass() {
		return custClass;
	}
	public void setCustClass(String custClass) {
		this.custClass = custClass;
	}
	public String getBalanceCustName() {
        return balanceCustName;
    }
    public void setBalanceCustName(String balanceCustName) {
        this.balanceCustName = balanceCustName;
    }
    
    public BigDecimal getInvQty() {
        return invQty;
    }
    public void setInvQty(BigDecimal invQty) {
        this.invQty = invQty;
    }
    public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResultText() {
		return resultText;
	}
	public void setResultText(String resultText) {
		this.resultText = resultText;
	}
	public String getWhName() {
		return whName;
	}
	public void setWhName(String whName) {
		this.whName = whName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public long getBrandId() {
		return brandId;
	}
	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public ScmInvSaleIssueBillEntry2(boolean defaultValue) {
		super(defaultValue);
		if (defaultValue) {
			this.convrate=BigDecimal.ZERO;
		}
	}

	public ScmInvSaleIssueBillEntry2() {
		super();
	}
}
