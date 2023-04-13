package com.armitage.server.ifbm.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "fbmCookResInfo")  
public class FbmCookResInfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_COOKID ="cookId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_MNEMONIC ="mnemonic";
    public static final String FN_PRICE ="price";
    public static final String FN_MODE ="mode";
    public static final String FN_DEPTORGUNITNO ="deptOrgUnitNo";
    public static final String FN_SORT ="sort";
    public static final String FN_PERMIT ="permit";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_ISYNCMODFLAG ="iSyncModFlag";
    
    private long id;
    private long cookId;
    private String orgUnitNo;
    private String mnemonic;
    private BigDecimal price;
    private String mode;
    private String deptOrgUnitNo;
    private int sort;
    private boolean permit;
    private String remarks;
    private String iSyncModFlag;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getCookId() {
        return cookId;
    }

    public void setCookId(long val) {
        this.cookId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String val) {
        this.mnemonic = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public String getMode() {
        return mode;
    }

    public void setMode(String val) {
        this.mode = val;
    }
    public String getDeptOrgUnitNo() {
        return deptOrgUnitNo;
    }

    public void setDeptOrgUnitNo(String val) {
        this.deptOrgUnitNo = val;
    }
    public int getSort() {
        return sort;
    }

    public void setSort(int val) {
        this.sort = val;
    }
    public boolean isPermit() {
        return permit;
    }

    public void setPermit(boolean val) {
        this.permit = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }
    public String getISyncModFlag() {
        return iSyncModFlag;
    }

    public void setISyncModFlag(String val) {
        this.iSyncModFlag = val;
    }

    public FbmCookResInfo(boolean defaultValue){
       if(defaultValue){
        price = BigDecimal.ZERO;
        mode = "'0'";
        sort = 0;
        permit = true;
        iSyncModFlag = "'SELF'";
       }
    }
  	public FbmCookResInfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_COOKID,
            FN_ORGUNITNO,
            FN_MNEMONIC,
            FN_PRICE,
            FN_MODE,
            FN_DEPTORGUNITNO,
            FN_SORT,
            FN_PERMIT,
            FN_REMARKS,
            FN_ISYNCMODFLAG,
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
        map.put(FN_MNEMONIC, 30);
        map.put(FN_MODE, 1);
        map.put(FN_DEPTORGUNITNO, 32);
        map.put(FN_REMARKS, 60);
        map.put(FN_ISYNCMODFLAG, 20);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
