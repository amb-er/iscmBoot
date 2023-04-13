package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvSaleIssueDetailAdvQuery extends BaseModel{
    public static final String FN_CUSTID ="custId";
	public static final String FN_BIZDATEFROM = "bizDateFrom";
	public static final String FN_BIZDATETO = "bizDateTo";
    public static final String FN_CREATEDATEFROM = "createDateFrom"; //创建日期起
    public static final String FN_CREATEDATETO = "createDateTo"; //创建日期止
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_CLASSID = "classId";
    public static final String FN_ITEMID = "itemId"; 
    public static final String FN_BRANDID = "brandId";
    public static final String FN_LOWPRICE = "lowPrice";
    public static final String FN_CUSTTYPE = "custType"; 
	
    private String custId;
	private Date bizDateFrom;
	private Date bizDateTo;
    private Date createDateFrom;
    private Date createDateTo;
    private String wareHouseId;
    private String itemId;
    private String classId;
    private String brandId;
	private boolean lowPrice;
	private String custType;
    
	public boolean isLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(boolean lowPrice) {
		this.lowPrice = lowPrice;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public Date getBizDateFrom() {
		return bizDateFrom;
	}

	public void setBizDateFrom(Date bizDateFrom) {
		this.bizDateFrom = bizDateFrom;
	}

	public Date getBizDateTo() {
		return bizDateTo;
	}

	public void setBizDateTo(Date bizDateTo) {
		this.bizDateTo = bizDateTo;
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

	public String getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(String wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public static String getFnCustid() {
		return FN_CUSTID;
	}

	public static String getFnBizdatefrom() {
		return FN_BIZDATEFROM;
	}

	public static String getFnBizdateto() {
		return FN_BIZDATETO;
	}

	public static String getFnCreatedatefrom() {
		return FN_CREATEDATEFROM;
	}

	public static String getFnCreatedateto() {
		return FN_CREATEDATETO;
	}

	public static String getFnWarehouseid() {
		return FN_WAREHOUSEID;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
