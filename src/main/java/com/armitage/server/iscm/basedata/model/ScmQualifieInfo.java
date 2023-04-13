package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmQualifieInfo")  
public class ScmQualifieInfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_TYPEID ="typeId";
    public static final String FN_SOURCE ="source";
    public static final String FN_QUALIFYAUDIT ="qualifyAudit";
    public static final String FN_REQUIRED ="required";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
	public static final String FN_CHECKER = "checker";
	public static final String FN_CHECKDATE = "checkDate";
	public static final String FN_QUALIFICATIONSTATUS = "qualificationStatus";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_UPDATETIMESTAMP ="updateTimeStamp";
    
    private long id;
    private long vendorId;
    private long typeId;
    private String source;
    private boolean qualifyAudit;
    private boolean required;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String checker;
    private Date checkDate;
    private String qualificationStatus;
    private String remarks;
    private String controlUnitNo;
    private Date updateTimeStamp;

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
    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long val) {
        this.typeId = val;
    }
    public String getSource() {
        return source;
    }

    public void setSource(String val) {
        this.source = val;
    }
    public boolean isQualifyAudit() {
        return qualifyAudit;
    }

    public void setQualifyAudit(boolean val) {
        this.qualifyAudit = val;
    }
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean val) {
        this.required = val;
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

	public String getQualificationStatus() {
		return qualificationStatus;
	}

	public void setQualificationStatus(String qualificationStatus) {
		this.qualificationStatus = qualificationStatus;
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

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }
    public Date getUpdateTimeStamp() {
        return updateTimeStamp;
    }

    public void setUpdateTimeStamp(Date val) {
        this.updateTimeStamp = val;
    }

    public ScmQualifieInfo(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmQualifieInfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_VENDORID,
            FN_TYPEID,
            FN_SOURCE,
            FN_QUALIFYAUDIT,
            FN_REQUIRED,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_CHECKER,
			FN_CHECKDATE,
			FN_QUALIFICATIONSTATUS,
            FN_REMARKS,
            FN_CONTROLUNITNO,
            FN_UPDATETIMESTAMP,
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
        map.put(FN_SOURCE, 16);
        map.put(FN_CREATOR, 80);
        map.put(FN_EDITOR, 80);
        map.put(FN_CHECKER, 80);
        map.put(FN_QUALIFICATIONSTATUS, 16);
        map.put(FN_REMARKS,250);
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
