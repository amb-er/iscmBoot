package com.armitage.server.iscm.basedata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmSupplierReplyData")  
public class ScmSupplierReplyData extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_CDID = "cdId";
	public static final String FN_BCID = "bcId";
	public static final String FN_CONFIRMBY = "confirmBy";
	public static final String FN_CONFIRMSTATUS = "confirmStatus";
	public static final String FN_MSGCONTENT = "msgContent";
	public static final String FN_MSGSENDTIME = "msgSendTime";
	public static final String FN_READTIME = "readTime";
	public static final String FN_CREATOR = "creator";
	public static final String FN_CREATEDATE = "createDate";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
	
	private long id;
	private long cdId;
	private long bcId;
	private String confirmBy;
	private String confirmStatus;
	private String msgContent;
	private Date msgSendTime;
	private Date readTime;
	private String creator;
	private Date createDate;
	private Date updateTimeStamp;
	private String controlUnitNo;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getCdId() {
		return cdId;
	}

	public void setCdId(long val) {
		this.cdId = val;
	}
	public long getBcId() {
		return bcId;
	}

	public void setBcId(long val) {
		this.bcId = val;
	}
	public String getConfirmBy() {
		return confirmBy;
	}

	public void setConfirmBy(String val) {
		this.confirmBy = val;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String val) {
		this.msgContent = val;
	}
	public Date getMsgSendTime() {
		return msgSendTime;
	}

	public void setMsgSendTime(Date val) {
		this.msgSendTime = val;
	}
	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date val) {
		this.readTime = val;
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
	
	public ScmSupplierReplyData(boolean defaultValue) {
		if (defaultValue) {
		}
	}
  	public ScmSupplierReplyData() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_CDID,
			FN_BCID,
			FN_CONFIRMBY,
			FN_CONFIRMSTATUS,
			FN_MSGCONTENT,
			FN_MSGSENDTIME,
			FN_READTIME,
			FN_CREATOR,
			FN_CREATEDATE,
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
		map.put(FN_CONFIRMBY, 80);
		map.put(FN_CONFIRMSTATUS, 16);
		map.put(FN_MSGCONTENT, 500);
		map.put(FN_CREATOR, 16);
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

