package com.armitage.server.iscm.purchasemanage.purchasesetting.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPurSupplyInfo")  
public class ScmPurSupplyInfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_BEGDATE ="begDate";
    public static final String FN_ENDDATE ="endDate";
    public static final String FN_DIRECTPURCHASE ="directPurchase";
    public static final String FN_ISASSIGNORG ="isAssignOrg";
    public static final String FN_SOURCETYPE ="sourceType";
    public static final String FN_SOURCEDTLID ="sourceDtlId";
    public static final String FN_STATUS ="status";
    public static final String FN_BIZDATE ="bizDate";
    
    private long id;
    private String orgUnitNo;
    private long vendorId;
    private long itemId;
    private Date begDate;
    private Date endDate;
    private boolean directPurchase;
    private boolean isAssignOrg;
    private String sourceType;
    private long sourceDtlId;
    private String status;
    private Date bizDate;
    
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
    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long val) {
        this.vendorId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    
    public Date getBegDate() {
		return begDate;
	}

	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isDirectPurchase() {
		return directPurchase;
	}

	public void setDirectPurchase(boolean directPurchase) {
		this.directPurchase = directPurchase;
	}

	public boolean isAssignOrg() {
		return isAssignOrg;
	}

	public void setAssignOrg(boolean isAssignOrg) {
		this.isAssignOrg = isAssignOrg;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public long getSourceDtlId() {
		return sourceDtlId;
	}

	public void setSourceDtlId(long sourceDtlId) {
		this.sourceDtlId = sourceDtlId;
	}

	public String getStatus() {
        return status;
    }

    public void setStatus(String val) {
        this.status = val;
    }

 
	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public ScmPurSupplyInfo(boolean defaultValue){
       if(defaultValue){
    	   this.status="I";
       }
    }
  	public ScmPurSupplyInfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_VENDORID,
            FN_ITEMID,
            FN_BEGDATE,
            FN_ENDDATE,
            FN_STATUS,
            FN_DIRECTPURCHASE,
            FN_ISASSIGNORG,
            FN_SOURCETYPE,
            FN_SOURCEDTLID,
            FN_BIZDATE
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
        map.put(FN_STATUS, 16);
        map.put(FN_SOURCETYPE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
