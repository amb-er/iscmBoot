package com.armitage.server.ifbc.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmAccountingCycle")  
public class ScmAccountingCycle extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_TYPEID ="typeId";
    public static final String FN_ACCOUNTYEAR ="accountYear";
    public static final String FN_PERIODID ="periodId";
    public static final String FN_PERIODCODE ="periodCode";
    public static final String FN_STARTDATE ="startDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long typeId;
    private int accountYear;
    private long periodId;
    private String periodCode;
    private Date startDate;
    private Date endDate;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
   
    public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}

	public int getAccountYear() {
		return accountYear;
	}

	public void setAccountYear(int accountYear) {
		this.accountYear = accountYear;
	}

	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public String getPeriodCode() {
        return periodCode;
    }

    public void setPeriodCode(String val) {
        this.periodCode = val;
    }
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date val) {
        this.startDate = val;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date val) {
        this.endDate = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmAccountingCycle(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmAccountingCycle() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_TYPEID,
            FN_ACCOUNTYEAR,
            FN_PERIODID,
            FN_PERIODCODE,
            FN_STARTDATE,
            FN_ENDDATE,
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
        map.put(FN_PERIODCODE, 16);
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
