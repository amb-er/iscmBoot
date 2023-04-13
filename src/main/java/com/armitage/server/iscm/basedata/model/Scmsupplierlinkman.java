package com.armitage.server.iscm.basedata.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmsupplierlinkman")  
public class Scmsupplierlinkman extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_CONTACTPERSON ="contactPerson";
    public static final String FN_CONTACTPERSONPOST ="contactPersonPost";
    public static final String FN_TEL ="tel";
    public static final String FN_MOBILE ="mobile";
    public static final String FN_FAX ="fax";
    public static final String FN_EMAIL ="email";
    public static final String FN_POSTALCODE ="postalcode";
    public static final String FN_ADDRESS ="address";
    
    private long id;
    private long vendorId;
    private String contactPerson;	
    private String contactPersonPost;
    private String tel;
    private String mobile;
    private String fax;
    private String email;
    private String postalcode;
    private String address;

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
    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String val) {
        this.contactPerson = val;
    }
    public String getContactPersonPost() {
        return contactPersonPost;
    }

    public void setContactPersonPost(String val) {
        this.contactPersonPost = val;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String val) {
        this.tel = val;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String val) {
        this.mobile = val;
    }
    public String getFax() {
        return fax;
    }

    public void setFax(String val) {
        this.fax = val;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String val) {
        this.email = val;
    }
    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String val) {
        this.postalcode = val;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String val) {
        this.address = val;
    }

    public Scmsupplierlinkman(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public Scmsupplierlinkman() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_VENDORID,
            FN_CONTACTPERSON,
            FN_CONTACTPERSONPOST,
            FN_TEL,
            FN_MOBILE,
            FN_FAX,
            FN_EMAIL,
            FN_POSTALCODE,
            FN_ADDRESS,
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
        map.put(FN_CONTACTPERSON, 32);
        map.put(FN_CONTACTPERSONPOST, 40);
        map.put(FN_TEL, 60);
        map.put(FN_MOBILE, 40);
        map.put(FN_FAX, 40);
        map.put(FN_EMAIL, 80);
        map.put(FN_POSTALCODE, 10);
        map.put(FN_ADDRESS, 100);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
