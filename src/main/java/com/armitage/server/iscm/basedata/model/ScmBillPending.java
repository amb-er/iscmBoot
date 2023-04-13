package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmBillPending")  
public class ScmBillPending extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_BILLID ="billId";
    public static final String FN_BILLNO ="billNo";
    public static final String FN_BILLTYPE ="billType";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_BILLDATE ="billDate";
    public static final String FN_CHECKER ="checker";
    public static final String FN_CONFIRMOR ="confirmor";
    public static final String FN_SUBMITDATE ="submitDate";
    public static final String FN_COUNTERSIGN ="counterSign";
    public static final String FN_PROCESSED ="processed";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long billId;
    private String billNo;
    private String billType;
    private String orgUnitNo;
    private Date billDate;
    private String checker;
    private String confirmor;
    private Date submitDate;
    private boolean counterSign;
    private boolean processed;
    private String remarks;
    private String controlUnitNo;

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
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String val) {
        this.billNo = val;
    }
    public String getBillType() {
        return billType;
    }

    public void setBillType(String val) {
        this.billType = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date val) {
        this.billDate = val;
    }
    public String getChecker() {
        return checker;
    }

    public void setChecker(String val) {
        this.checker = val;
    }
    public String getConfirmor() {
        return confirmor;
    }

    public void setConfirmor(String val) {
        this.confirmor = val;
    }
    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date val) {
        this.submitDate = val;
    }
    public boolean isCounterSign() {
        return counterSign;
    }

    public void setCounterSign(boolean val) {
        this.counterSign = val;
    }

    public boolean isProcessed() {
		return processed;
	}

	public void setProcessed(boolean processed) {
		this.processed = processed;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getControlUnitNo() {
		return controlUnitNo;
	}

	public void setControlUnitNo(String controlUnitNo) {
		this.controlUnitNo = controlUnitNo;
	}

	public ScmBillPending(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmBillPending() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BILLID,
            FN_BILLNO,
            FN_BILLTYPE,
            FN_ORGUNITNO,
            FN_BILLDATE,
            FN_CHECKER,
            FN_CONFIRMOR,
            FN_SUBMITDATE,
            FN_COUNTERSIGN,
            FN_PROCESSED,
            FN_REMARKS,
            FN_CONTROLUNITNO
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
        map.put(FN_BILLNO, 32);
        map.put(FN_BILLTYPE, 32);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_CHECKER, 16);
        map.put(FN_CONFIRMOR, 16);
        map.put(FN_REMARKS, 250);
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
