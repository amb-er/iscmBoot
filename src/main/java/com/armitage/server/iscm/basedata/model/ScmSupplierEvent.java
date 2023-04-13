package com.armitage.server.iscm.basedata.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmSupplierEvent")  
public class ScmSupplierEvent extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_EVENTCODE = "eventCode";
	public static final String FN_EVENTTYPE = "eventType";
	public static final String FN_BEGDATE = "begDate";
	public static final String FN_ENDDATE = "endDate";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_WXURL = "wxUrl";
	public static final String FN_FLAG = "flag";
	public static final String FN_CONTROLUNITNO = "controlUnitNo";
	public static final String FN_FILEPATH ="filePath";
	public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
	
	private long id;
	private String eventCode;
	private String eventType;
	private Date begDate;
	private Date endDate;
	private String remarks;
	private String wxUrl;
	private boolean flag;
	private String controlUnitNo;
	private String filePath;
	private Date updateTimeStamp;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String val) {
		this.eventCode = val;
	}
	public String getEventType() {
		return eventType;
	}

	public void setEventType(String val) {
		this.eventType = val;
	}
	public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date val) {
		this.begDate = val;
	}
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date val) {
		this.endDate = val;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	public String getWxUrl() {
		return wxUrl;
	}

	public void setWxUrl(String val) {
		this.wxUrl = val;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String val) {
		this.controlUnitNo = val;
	}
	
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public ScmSupplierEvent(boolean defaultValue) {
		if (defaultValue) {
			this.flag=true;
			this.eventType="1";
			this.wxUrl="ISP-REGISTER";
		}
	}
  	public ScmSupplierEvent() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_EVENTCODE,
			FN_EVENTTYPE,
			FN_BEGDATE,
			FN_ENDDATE,
			FN_REMARKS,
			FN_WXURL,
			FN_FLAG,
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
		list.add(new String[] { FN_EVENTCODE, FN_CONTROLUNITNO });
		return list;
	}
	 
	public String[] getRequiredFields() {
		/*DEMO:
		return new String[] {FN_CODE };
		*/
		return new String[] {FN_EVENTCODE,FN_EVENTTYPE,FN_BEGDATE,FN_ENDDATE};
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
		map.put(FN_EVENTCODE, 16);
		map.put(FN_EVENTTYPE, 16);
		map.put(FN_REMARKS, 255);
		map.put(FN_WXURL, 255);
		map.put(FN_CONTROLUNITNO, 32);
		map.put(FN_FILEPATH, 255);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}

