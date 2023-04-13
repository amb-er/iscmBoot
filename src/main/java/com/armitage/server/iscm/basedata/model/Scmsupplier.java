package com.armitage.server.iscm.basedata.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmsupplier")  
public class Scmsupplier extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_VENDORNO ="vendorNo";
    public static final String FN_VENDORNAME ="vendorName";
    public static final String FN_SIMPLENAME ="simpleName";
    public static final String FN_MNEMONICCODE ="mnemonicCode";
    public static final String FN_CLASSID ="classId";
    public static final String FN_ROLE ="role";
    public static final String FN_ORGUNITNO = "orgUnitNo";
    public static final String FN_INCORPORATOR ="incorporator";
    public static final String FN_TAXNO ="taxNo";
    public static final String FN_ADDRESS ="address";
    public static final String FN_EMAIL ="email";
    public static final String FN_EXTERNALCODE="externalCode";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_FREEZEORGUNITNO ="freezeOrgUnitNo";
    public static final String FN_STATUS = "status";
    public static final String FN_MANAGEORGUNITNO ="manageOrgUnitNo";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_QUALIFICATIONSTATUS ="qualificationStatus";
	public static final String FN_SENDOA ="sendOA";
    public static final String FN_INDUSTRYGROUPID ="industryGroupId";
    public static final String FN_BUSINESSAUTOUPDATE ="businessAutoUpdate";
    public static final String FN_UPDATETIMESTAMP = "updateTimeStamp";
    
    private long id;
    private String vendorNo;
    private String vendorName;
    private String simpleName;
    private String mnemonicCode;
    private long classId;
    private String role;
    private String orgUnitNo;
    private String incorporator;
    private String taxNo;
    private String address;
    private String email;
    private String externalCode;
    private String remarks;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private boolean flag;
    private String freezeOrgUnitNo;
    private String status;
    private String manageOrgUnitNo;
    private String controlUnitNo;
    private String qualificationStatus;
	private boolean sendOA;
    private long industryGroupId;
    private boolean businessAutoUpdate;
    private Date updateTimeStamp;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getVendorNo() {
        return vendorNo;
    }

    public void setVendorNo(String val) {
        this.vendorNo = val;
    }
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String val) {
        this.vendorName = val;
    }
    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String val) {
        this.simpleName = val;
    }
    public String getMnemonicCode() {
        return mnemonicCode;
    }

    public void setMnemonicCode(String val) {
        this.mnemonicCode = val;
    }
    public long getClassId() {
        return classId;
    }

    public void setClassId(long val) {
        this.classId = val;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String val) {
        this.role = val;
    }
    
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getIncorporator() {
        return incorporator;
    }

    public void setIncorporator(String val) {
        this.incorporator = val;
    }
    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String val) {
        this.taxNo = val;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String val) {
        this.address = val;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String val) {
        this.creator = val;
    }
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date val) {
        this.createDate = val;
    }
    public String getEditor() {
        return editor;
    }

    public void setEditor(String val) {
        this.editor = val;
    }
    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date val) {
        this.editDate = val;
    }
    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public String getFreezeOrgUnitNo() {
        return freezeOrgUnitNo;
    }

    public void setFreezeOrgUnitNo(String val) {
        this.freezeOrgUnitNo = val;
    }
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManageOrgUnitNo() {
        return manageOrgUnitNo;
    }

    public void setManageOrgUnitNo(String val) {
        this.manageOrgUnitNo = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public String getExternalCode() {
		return externalCode;
	}

	public void setExternalCode(String externalCode) {
		this.externalCode = externalCode;
	}

    public String getQualificationStatus() {
		return qualificationStatus;
	}

	public void setQualificationStatus(String qualificationStatus) {
		this.qualificationStatus = qualificationStatus;
	}

	public boolean isSendOA() {
		return sendOA;
	}

	public void setSendOA(boolean sendOA) {
		this.sendOA = sendOA;
	}

	public long getIndustryGroupId() {
		return industryGroupId;
	}

	public void setIndustryGroupId(long industryGroupId) {
		this.industryGroupId = industryGroupId;
	}

	public boolean isBusinessAutoUpdate() {
		return businessAutoUpdate;
	}

	public void setBusinessAutoUpdate(boolean businessAutoUpdate) {
		this.businessAutoUpdate = businessAutoUpdate;
	}

	public Date getUpdateTimeStamp() {
		return updateTimeStamp;
	}

	public void setUpdateTimeStamp(Date updateTimeStamp) {
		this.updateTimeStamp = updateTimeStamp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Scmsupplier(boolean defaultValue){
       if(defaultValue){
    	   this.flag=true;
    	   this.businessAutoUpdate=false;
    	   this.sendOA=false;
       }
    }
  	public Scmsupplier() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_VENDORNO,
            FN_VENDORNAME,
            FN_SIMPLENAME,
            FN_MNEMONICCODE,
            FN_CLASSID,
            FN_ROLE,
            FN_ORGUNITNO,
            FN_INCORPORATOR,
            FN_TAXNO,
            FN_ADDRESS,
            FN_EMAIL,
            FN_REMARKS,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_FLAG,
            FN_FREEZEORGUNITNO,
            FN_STATUS,
            FN_MANAGEORGUNITNO,
            FN_CONTROLUNITNO,
			FN_EXTERNALCODE,
            FN_QUALIFICATIONSTATUS,
            FN_INDUSTRYGROUPID,
            FN_BUSINESSAUTOUPDATE,
			FN_SENDOA
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
		return new String[] {FN_VENDORNAME,FN_ROLE};
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
        map.put(FN_VENDORNO, 16);
        map.put(FN_VENDORNAME, 80);
        map.put(FN_SIMPLENAME, 40);
        map.put(FN_MNEMONICCODE, 50);
        map.put(FN_ROLE, 16);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_INCORPORATOR, 50);
        map.put(FN_TAXNO, 30);
        map.put(FN_ADDRESS, 100);
        map.put(FN_EMAIL, 60);
        map.put(FN_EXTERNALCODE, 32);
        map.put(FN_REMARKS, 200);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_FREEZEORGUNITNO, 32);
        map.put(FN_STATUS, 1);
        map.put(FN_MANAGEORGUNITNO, 32);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_QUALIFICATIONSTATUS, 8);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}

