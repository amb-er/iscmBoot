package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmSupplierQualifieInfoBillEntry")  
public class ScmSupplierQualifieInfoBillEntry extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_BILLID ="billId";
    public static final String FN_LINEID ="lineId";
    public static final String FN_TYPEID ="typeId";
    public static final String FN_REQUIRED ="required";
    public static final String FN_QUALIFYAUDIT ="qualifyAudit";
    public static final String FN_SOURCEBILLDTLID ="sourceBillDtlId";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_ROWSTATUS = "rowStatus";
    public static final String FN_REMARKS ="remarks";
    
    private long id;
    private long billId;
    private int lineId;
    private long typeId;
    private boolean required;
    private boolean qualifyAudit;
    private long sourceBillDtlId;
    private String checker;
    private Date checkDate;
	private String rowStatus;
    private String remarks;

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
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int val) {
        this.lineId = val;
    }
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long val) {
        this.typeId = val;
    }
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean val) {
        this.required = val;
    }
    public boolean isQualifyAudit() {
        return qualifyAudit;
    }

    public void setQualifyAudit(boolean val) {
        this.qualifyAudit = val;
    }
    public long getSourceBillDtlId() {
        return sourceBillDtlId;
    }

    public void setSourceBillDtlId(long val) {
        this.sourceBillDtlId = val;
    }
    public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getRowStatus() {
		return rowStatus;
	}

	public void setRowStatus(String rowStatus) {
		this.rowStatus = rowStatus;
	}

	public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }

    public ScmSupplierQualifieInfoBillEntry(boolean defaultValue){
       if(defaultValue){
    	   this.rowStatus="I";
       }
    }
  	public ScmSupplierQualifieInfoBillEntry() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_BILLID,
            FN_LINEID,
            FN_TYPEID,
            FN_REQUIRED,
            FN_QUALIFYAUDIT,
            FN_SOURCEBILLDTLID,
            FN_CHECKER,
            FN_CHECKDATE,
            FN_ROWSTATUS,
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
        map.put(FN_ROWSTATUS, 16);
        map.put(FN_CHECKER, 80);
        map.put(FN_REMARKS, 250);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
