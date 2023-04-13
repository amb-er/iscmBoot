package com.armitage.server.ifbc.costcard.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmCookCostCard")  
public class ScmCookCostCard extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_COOKID = "cookId";
    public static final String FN_COOKCODE ="cookCode";
    public static final String FN_COOKNAME ="cookName";
    public static final String FN_COSTPRICE ="costPrice";
    public static final String FN_EFFECTIVEDATE ="effectiveDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_LOCKED ="locked";
    public static final String FN_APPROVALED ="approvaled";
	public static final String FN_RESORGUNITNO = "resOrgUnitNo";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private long cookId;
    private String cookCode;
    private String cookName;
    private BigDecimal costPrice;
    private Date effectiveDate;
    private boolean flag;
    private boolean locked;
    private boolean approvaled;
	private String resOrgUnitNo;
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

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public long getCookId() {
		return cookId;
	}

	public void setCookId(long cookId) {
		this.cookId = cookId;
	}

	public String getCookCode() {
        return cookCode;
    }

    public void setCookCode(String val) {
        this.cookCode = val;
    }
    public String getCookName() {
        return cookName;
    }

    public void setCookName(String val) {
        this.cookName = val;
    }
    public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean val) {
        this.locked = val;
    }
    public boolean isApprovaled() {
		return approvaled;
	}

	public void setApprovaled(boolean approvaled) {
		this.approvaled = approvaled;
	}

	public String getResOrgUnitNo() {
		return resOrgUnitNo;
	}

	public void setResOrgUnitNo(String resOrgUnitNo) {
		this.resOrgUnitNo = resOrgUnitNo;
	}

	public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmCookCostCard(boolean defaultValue){
       if(defaultValue){
    	   this.flag=true;
    	   this.locked=false;
    	   this.approvaled=false;
    	   this.costPrice=BigDecimal.ZERO;
       }
    }
  	public ScmCookCostCard() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_COOKID,
            FN_COOKCODE,
            FN_COOKNAME,
            FN_COSTPRICE,
            FN_FLAG,
            FN_LOCKED,
            FN_APPROVALED,
            FN_RESORGUNITNO,
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
		return new String[] {FN_COOKCODE,FN_EFFECTIVEDATE };
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
        map.put(FN_COOKCODE, 16);
        map.put(FN_COOKNAME, 60);
        map.put(FN_RESORGUNITNO, 32);
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
