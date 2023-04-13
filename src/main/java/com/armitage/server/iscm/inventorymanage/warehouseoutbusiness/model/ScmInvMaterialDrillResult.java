package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmInvMaterialDrillResult extends BaseModel {
	public static final String FN_ITEMID ="itemId";
    public static final String FN_ITEMNO ="itemNo";
    public static final String FN_ITEMNAME ="itemName";
    public static final String FN_REQNO ="reqNo";
    public static final String FN_REQRDETAILID ="reqDetailId";
    public static final String FN_REQIDS ="reqIds";
    public static final String FN_REQSTATUS ="reqStatus";
    public static final String FN_OTNO ="otNo";
    public static final String FN_OTDETAILID ="otDetailId";
    public static final String FN_OTIDS ="otIds";
    public static final String FN_OTSTATUS ="otStatus";
    
    private long itemId;
    private String itemNo;
	private String itemName;
	private String reqNo;
   	private String reqDetailId;
    private String reqIds;
    private String reqStatus;
    private String otNo;
    private String otDetailId;
    private String otIds;
    private String otStatus;
    
    public String getReqNo() {
		return reqNo;
	}
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
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
	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}
	public String getReqDetailId() {
		return reqDetailId;
	}
	public void setReqDetailId(String reqDetailId) {
		this.reqDetailId = reqDetailId;
	}
	public String getReqIds() {
		return reqIds;
	}
	public void setReqIds(String reqIds) {
		this.reqIds = reqIds;
	}
	public String getReqStatus() {
		return reqStatus;
	}
	public void setReqStatus(String reqStatus) {
		this.reqStatus = reqStatus;
	}
	public String getOtNo() {
		return otNo;
	}
	public void setOtNo(String otNo) {
		this.otNo = otNo;
	}
	public String getOtDetailId() {
		return otDetailId;
	}
	public void setOtDetailId(String otDetailId) {
		this.otDetailId = otDetailId;
	}
	public String getOtIds() {
		return otIds;
	}
	public void setOtIds(String otIds) {
		this.otIds = otIds;
	}
	public String getOtStatus() {
		return otStatus;
	}
	public void setOtStatus(String otStatus) {
		this.otStatus = otStatus;
	}
	public ScmInvMaterialDrillResult(boolean defaultValue) {
		if(defaultValue){
	    	   
	       }
	}
	public ScmInvMaterialDrillResult() {
		
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
