package com.armitage.server.iscm.inventorymanage.AllocationApplication.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvMoveReqBillEntry")  
public class ScmInvMoveReqBillEntry extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_REQID = "reqId";
	public static final String FN_LINEID = "lineId";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_UNIT = "unit";
    public static final String FN_PIEUNIT ="pieUnit";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_ISSUEORGUNITNO = "issueOrgUnitNo";
	public static final String FN_ISSUEWAREHOUSEID = "issueWareHouseId";
	public static final String FN_REQWAREHOUSEID = "reqWareHouseId";
	public static final String FN_USEORGUNITNO = "useOrgUnitNo";
	public static final String FN_LOT = "lot";
	public static final String FN_QTY = "qty";
    public static final String FN_PIEQTY ="pieQty";
	public static final String FN_BASEQTY = "baseQty";
	public static final String FN_PRICE = "price";
	public static final String FN_AMT = "amt";
	public static final String FN_ROWSTATUS = "rowStatus";
	public static final String FN_MOVEBASEQTY = "moveBaseQty";
	public static final String FN_SOURCEBILLDTLID = "sourceBillDtlId";
	public static final String FN_REMARKS = "remarks";
	
	private long id;
	private long reqId;
	private int lineId;
	private long itemId;
	private long unit;
    private long pieUnit;
	private long baseUnit;
	private String issueOrgUnitNo;
	private long issueWareHouseId;
	private long reqWareHouseId;
	private String useOrgUnitNo;
	private String lot;
	private BigDecimal qty;
    private BigDecimal pieQty;
	private BigDecimal baseQty;
	private BigDecimal price;
	private BigDecimal amt;
	private String rowStatus;
	private BigDecimal moveBaseQty;
	private long sourceBillDtlId;
	private String remarks;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getReqId() {
		return reqId;
	}

	public void setReqId(long val) {
		this.reqId = val;
	}
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int val) {
		this.lineId = val;
	}
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long val) {
		this.itemId = val;
	}
	public long getUnit() {
		return unit;
	}

	public void setUnit(long val) {
		this.unit = val;
	}
	public long getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(long pieUnit) {
		this.pieUnit = pieUnit;
	}

	public long getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(long val) {
		this.baseUnit = val;
	}
	public String getIssueOrgUnitNo() {
		return issueOrgUnitNo;
	}

	public void setIssueOrgUnitNo(String val) {
		this.issueOrgUnitNo = val;
	}
	public long getIssueWareHouseId() {
		return issueWareHouseId;
	}

	public void setIssueWareHouseId(long val) {
		this.issueWareHouseId = val;
	}
	public long getReqWareHouseId() {
		return reqWareHouseId;
	}

	public void setReqWareHouseId(long val) {
		this.reqWareHouseId = val;
	}
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String val) {
		this.useOrgUnitNo = val;
	}
	public String getLot() {
		return lot;
	}

	public void setLot(String val) {
		this.lot = val;
	}
	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal val) {
		this.qty = val;
	}
	public BigDecimal getPieQty() {
		return pieQty;
	}

	public void setPieQty(BigDecimal pieQty) {
		this.pieQty = pieQty;
	}

	public BigDecimal getBaseQty() {
		return baseQty;
	}

	public void setBaseQty(BigDecimal val) {
		this.baseQty = val;
	}
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal val) {
		this.price = val;
	}
	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal val) {
		this.amt = val;
	}
	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String val) {
		this.rowStatus = val;
	}
	public BigDecimal getMoveBaseQty() {
		return moveBaseQty;
	}

	public void setMoveBaseQty(BigDecimal val) {
		this.moveBaseQty = val;
	}
	public long getSourceBillDtlId() {
		return sourceBillDtlId;
	}

	public void setSourceBillDtlId(long val) {
		this.sourceBillDtlId = val;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	
	public ScmInvMoveReqBillEntry(boolean defaultValue) {
		if (defaultValue) {
			this.issueOrgUnitNo="";
			this.reqWareHouseId=0;
			this.qty=BigDecimal.ZERO;
			this.pieQty=BigDecimal.ZERO;
			this.baseQty=BigDecimal.ZERO;
			this.price=BigDecimal.ZERO;
			this.amt=BigDecimal.ZERO;
			this.moveBaseQty=BigDecimal.ZERO;
			this.sourceBillDtlId=0;
		}
	}
  	public ScmInvMoveReqBillEntry() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_REQID,
			FN_LINEID,
			FN_ITEMID,
			FN_UNIT,
			FN_PIEUNIT,
			FN_BASEUNIT,
			FN_ISSUEORGUNITNO,
			FN_ISSUEWAREHOUSEID,
			FN_REQWAREHOUSEID,
			FN_USEORGUNITNO,
			FN_LOT,
			FN_QTY,
			FN_PIEQTY,
			FN_BASEQTY,
			FN_PRICE,
			FN_AMT,
			FN_ROWSTATUS,
			FN_MOVEBASEQTY,
			FN_SOURCEBILLDTLID,
			FN_REMARKS,
		};
	}
	
	public Map<String, RelationModel> getForeignMap() {
		/*
		DEMO:
		HashMap<String, RelationModel> map = new HashMap<String, RelationModel>();
		map.put(FN_STATUS, new RelationModel(Code.class, Code.FN_CODE)
				.addFilter(Code.FN_CATEGORY, "UserStatus"));
		return map;
		*/
		return null;
	}
	
	public List<String[]> getUniqueKeys() {
		List<String[]> list = new ArrayList<String[]>();
		//list.add(new String[] { FN_CODE, FN_CATEGORY });
		return list;
	}
	 
	public String[] getRequiredFields() {
		/*DEMO:
		return new String[] {FN_CODE };
		*/
		return null;
	}
 
	public Map<String, List<RelationModel>> getReferenceMap() {
		/*
		DEMO:
		HashMap<String, List<RelationModel>> map = new HashMap<String, List<RelationModel>>();

		List<RelationModel> list = new ArrayList<RelationModel>();
		list.add(new RelationModel(Code.class, Code.FN_CODE).addFilter(
				Code.FN_CATEGORY, "UserStatus"));

		map.put(this.FN_STATUS, list);
		return map;*/
		
		return null;
	}
	
	public Map<String, Integer> getDataLengthMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();  
		map.put(FN_ISSUEORGUNITNO, 32);
		map.put(FN_USEORGUNITNO, 32);
		map.put(FN_LOT, 32);
		map.put(FN_ROWSTATUS, 16);
		map.put(FN_REMARKS, 255);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
