package com.armitage.server.iscm.common.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmAuditDetailHistory")  
public class ScmAuditDetailHistory extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_BILLID = "billId";
	public static final String FN_BILLTYPE = "billType";
	public static final String FN_LINEID = "lineId";
	public static final String FN_OPINION = "opinion";
	public static final String FN_REMARKS = "remarks";
	public static final String FN_OPER = "oper";
	public static final String FN_OPERDATE = "operDate";
	public static final String FN_FLAG = "flag";
	
	private long id;
	private long billId;
	private String billType;
	private int lineId;
	private String opinion;
	private String remarks;
	private String oper;
	private Date operDate;
	private boolean flag;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getBillId() {
		return billId;
	}

	public void setBillId(long val) {
		this.billId = val;
	}
	public String getBillType() {
		return billType;
	}

	public void setBillType(String val) {
		this.billType = val;
	}
	
	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String val) {
		this.opinion = val;
	}
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String val) {
		this.remarks = val;
	}
	public String getOper() {
		return oper;
	}

	public void setOper(String val) {
		this.oper = val;
	}
	public Date getOperDate() {
		return operDate;
	}

	public void setOperDate(Date val) {
		this.operDate = val;
	}
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public ScmAuditDetailHistory(boolean defaultValue) {
		if (defaultValue) {
			this.flag=true;
		}
	}
  	public ScmAuditDetailHistory() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_BILLID,
			FN_BILLTYPE,
			FN_LINEID,
			FN_OPINION,
			FN_REMARKS,
			FN_OPER,
			FN_OPERDATE,
			FN_FLAG,
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
		map.put(FN_BILLTYPE, 32);
		map.put(FN_OPINION, 16);
		map.put(FN_REMARKS, 255);
		map.put(FN_OPER, 16);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
