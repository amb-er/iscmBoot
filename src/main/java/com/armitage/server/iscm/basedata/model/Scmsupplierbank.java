package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmsupplierbank")  
public class Scmsupplierbank extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_BANKNAME ="bankName";
    public static final String FN_BANKACCNO ="bankAccNo";
    public static final String FN_BANKADDRESS ="bankAddress";
    
    private long id;
    private long vendorId;
    private String orgUnitNo;
    private String bankName;
    private String bankAccNo;
    private String bankAddress;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long val) {
        this.vendorId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String val) {
        this.bankName = val;
    }
    public String getBankAccNo() {
        return bankAccNo;
    }

    public void setBankAccNo(String val) {
        this.bankAccNo = val;
    }
    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String val) {
        this.bankAddress = val;
    }

    public Scmsupplierbank(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public Scmsupplierbank() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_VENDORID,
            FN_ORGUNITNO,
            FN_BANKNAME,
            FN_BANKACCNO,
            FN_BANKADDRESS,
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
        map.put(FN_BANKNAME, 60);
        map.put(FN_BANKACCNO, 30);
        map.put(FN_BANKADDRESS, 100);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
