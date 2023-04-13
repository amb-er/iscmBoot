package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

public class ScmPurRequireEntryQuery extends BaseModel {
	public static final String FN_PRID = "prId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_PURORGUNITNO = "purOrgUnitNo";
	public static final String FN_RECSTORAGEORGUNITNO = "recStorageOrgUnitNo";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_VENDORID = "vendorId";
	public static final String FN_REQDATE = "reqDate";
	public static final String FN_ORDERDATE = "orderDate";
	
	private long prId;
	private long itemId;
	private long purUnit;
	private String purOrgUnitNo;
	private String recStorageOrgUnitNo;
	private long buyerId;
	private long vendorId;
	private Date reqDate;
	private Date orderDate;

	public long getPrId() {
		return prId;
	}

	public void setPrId(long prId) {
		this.prId = prId;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public long getPurUnit() {
		return purUnit;
	}

	public void setPurUnit(long purUnit) {
		this.purUnit = purUnit;
	}

	public String getPurOrgUnitNo() {
		return purOrgUnitNo;
	}

	public void setPurOrgUnitNo(String purOrgUnitNo) {
		this.purOrgUnitNo = purOrgUnitNo;
	}

	public String getRecStorageOrgUnitNo() {
		return recStorageOrgUnitNo;
	}

	public void setRecStorageOrgUnitNo(String recStorageOrgUnitNo) {
		this.recStorageOrgUnitNo = recStorageOrgUnitNo;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public Date getReqDate() {
		return reqDate;
	}

	public void setReqDate(Date reqDate) {
		this.reqDate = reqDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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

	@Override
	public Map<String, RelationModel> getForeignMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<RelationModel>> getReferenceMap() {
		// TODO Auto-generated method stub
		return null;
	}
}
