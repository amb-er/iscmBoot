package com.armitage.server.iscm.common.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmConfirmRule")  
public class ScmConfirmRule extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_BILLTYPE ="billType";
    public static final String FN_CONFIRMTYPE ="confirmType";
    public static final String FN_BILLSTATUS ="billStatus";
    public static final String FN_SENDPOINT ="sendPoint";
    public static final String FN_NEEDSENDSUPPLIER ="needSendSupplier";
    public static final String FN_AUTOCONFIRM ="autoConfirm";
    public static final String FN_DAYS ="days";
    public static final String FN_AUTOCONFIRMTIME ="autoConfirmTime";
    public static final String FN_COMPAREBY ="compareBy";
    public static final String FN_ALLOWASSISTCONFIRM ="allowAssistConfirm";
    public static final String FN_LASTCONFIRMTIME ="lastConfirmTime";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
    
    private long id;
    private String orgUnitNo;
    private String billType;
    private String confirmType;
    private String billStatus;
    private String sendPoint;
    private boolean needSendSupplier;
    private boolean autoConfirm;
    private int days;
    private String autoConfirmTime;
    private String compareBy;
    private boolean allowAssistConfirm;
    private Date lastConfirmTime;
    private String controlUnitNo;
    private Date updateTimeStamp;

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

	public String getBillType() {
        return billType;
    }

    public void setBillType(String val) {
        this.billType = val;
    }
    public String getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(String val) {
        this.confirmType = val;
    }
    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String val) {
        this.billStatus = val;
    }
    public String getSendPoint() {
        return sendPoint;
    }

    public void setSendPoint(String val) {
        this.sendPoint = val;
    }
    public boolean isNeedSendSupplier() {
        return needSendSupplier;
    }

    public void setNeedSendSupplier(boolean val) {
        this.needSendSupplier = val;
    }
    public boolean isAutoConfirm() {
        return autoConfirm;
    }

    public void setAutoConfirm(boolean val) {
        this.autoConfirm = val;
    }
    public int getDays() {
        return days;
    }

    public void setDays(int val) {
        this.days = val;
    }
    public String getAutoConfirmTime() {
        return autoConfirmTime;
    }

    public void setAutoConfirmTime(String val) {
        this.autoConfirmTime = val;
    }
    public String getCompareBy() {
        return compareBy;
    }

    public void setCompareBy(String val) {
        this.compareBy = val;
    }
    public boolean isAllowAssistConfirm() {
        return allowAssistConfirm;
    }

    public void setAllowAssistConfirm(boolean val) {
        this.allowAssistConfirm = val;
    }
    public Date getLastConfirmTime() {
        return lastConfirmTime;
    }

    public void setLastConfirmTime(Date val) {
        this.lastConfirmTime = val;
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

    public ScmConfirmRule(boolean defaultValue){
       if(defaultValue){
    	   this.needSendSupplier=true;
    	   this.autoConfirm=true;
    	   this.allowAssistConfirm=true;
    	   this.billType="InvPurInWhsBill";
    	   this.confirmType="B";
    	   this.sendPoint="P";
    	   this.compareBy="P";
    	   this.days=7;
    	   this.billStatus="E";
    	   this.autoConfirmTime="8:00";
       }
    }
  	public ScmConfirmRule() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_BILLTYPE,
            FN_CONFIRMTYPE,
            FN_BILLSTATUS,
            FN_SENDPOINT,
            FN_NEEDSENDSUPPLIER,
            FN_AUTOCONFIRM,
            FN_DAYS,
            FN_AUTOCONFIRMTIME,
            FN_COMPAREBY,
            FN_ALLOWASSISTCONFIRM,
            FN_LASTCONFIRMTIME,
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
        map.put(FN_BILLTYPE, 16);
        map.put(FN_CONFIRMTYPE, 16);
        map.put(FN_BILLSTATUS, 16);
        map.put(FN_SENDPOINT, 16);
        map.put(FN_AUTOCONFIRMTIME, 8);
        map.put(FN_COMPAREBY, 16);
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
