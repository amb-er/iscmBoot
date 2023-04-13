package com.armitage.server.iscm.basedata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmSupplierConfirmData")  
public class ScmSupplierConfirmData extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_BCID = "bcId";
	public static final String FN_PLATSUPPLIERID = "platSupplierId";
	public static final String FN_BILLTYPE = "billType";
	public static final String FN_BILLNO = "billNo";
	public static final String FN_CONFIRMINFO = "confirmInfo";
	public static final String FN_STATUS = "status";
	public static final String FN_MSGSENDTIME = "msgSendTime";
	public static final String FN_CONFIRMTIME = "confirmTime";
	public static final String FN_CONFIRMBY = "confirmBy";
	public static final String FN_SOURCE = "source";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
	public static final String FN_EDITOR = "editor";
	public static final String FN_EDITDATE = "editDate";
	public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	
	private long id;
	private String orgUnitNo;
	private long bcId;
	private long platSupplierId;
	private String billType;
	private String billNo;
	private String confirmInfo;
	private String status;
	private Date msgSendTime;
	private Date confirmTime;
	private String confirmBy;
	private String source;
	private String creator;
	private Date createDate;
	private String editor;
	private Date editDate;
	private Date updateTimeStamp;
	private String controlUnitNo;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public long getBcId() {
		return bcId;
	}

	public void setBcId(long val) {
		this.bcId = val;
	}
	public long getPlatSupplierId() {
		return platSupplierId;
	}

	public void setPlatSupplierId(long platSupplierId) {
		this.platSupplierId = platSupplierId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String val) {
		this.billType = val;
	}
	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String val) {
		this.billNo = val;
	}
	public String getConfirmInfo() {
		return confirmInfo;
	}

	public void setConfirmInfo(String val) {
		this.confirmInfo = val;
	}
	public String getStatus() {
		return status;
	}

	public void setStatus(String val) {
		this.status = val;
	}
	public Date getMsgSendTime() {
		return msgSendTime;
	}

	public void setMsgSendTime(Date val) {
		this.msgSendTime = val;
	}
	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date val) {
		this.confirmTime = val;
	}
	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String val) {
		this.confirmBy = val;
	}
	public String getSource() {
		return source;
	}

	public void setSource(String val) {
		this.source = val;
	}
	public String getCreator() {
		return creator;
	}

	public void setCreator(String val) {
		this.creator = val;
	}
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date val) {
		this.createDate = val;
	}
	public String getEditor() {
		return editor;
	}

	public void setEditor(String val) {
		this.editor = val;
	}
	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date val) {
		this.editDate = val;
	}
	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public ScmSupplierConfirmData(boolean defaultValue) {
		if (defaultValue) {
		}
	}
  	public ScmSupplierConfirmData() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_ORGUNITNO,
			FN_BCID,
			FN_PLATSUPPLIERID,
			FN_BILLTYPE,
			FN_BILLNO,
			FN_CONFIRMINFO,
			FN_STATUS,
			FN_MSGSENDTIME,
			FN_CONFIRMTIME,
			FN_CONFIRMBY,
			FN_SOURCE,
			FN_CREATOR,
			FN_CREATEDATE,
			FN_EDITOR,
			FN_EDITDATE,
			FN_UPDATETIMESTAMP,
			FN_CONTROLUNITNO,
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
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_BILLTYPE, 32);
		map.put(FN_BILLNO, 32);
		map.put(FN_CONFIRMINFO, 250);
		map.put(FN_STATUS, 16);
		map.put(FN_CONFIRMBY, 80);
		map.put(FN_SOURCE, 16);
		map.put(FN_CREATOR, 80);
		map.put(FN_EDITOR, 80);
		map.put(FN_CONTROLUNITNO, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
