package com.armitage.server.iscm.purchasemanage.purchasebusiness.model;

import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

public class ScmPurReceiveEntryQuery extends BaseModel {
	public static final String FN_PVID = "pvId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_PURUNIT = "purUnit";
	public static final String FN_BUYERID = "buyerId";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	
	private long pvId;
	private long itemId;
	private long purUnit;
	private String finOrgUnitNo;
	private long buyerId;

	public long getPvId() {
		return pvId;
	}

	public void setPvId(long pvId) {
		this.pvId = pvId;
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

	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String finOrgUnitNo) {
		this.finOrgUnitNo = finOrgUnitNo;
	}

	public long getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
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
