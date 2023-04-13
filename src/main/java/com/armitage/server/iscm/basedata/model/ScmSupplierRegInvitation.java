package com.armitage.server.iscm.basedata.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmSupplierRegInvitation")  
public class ScmSupplierRegInvitation extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_INVITATIONCONTENT ="invitationContent";
    public static final String FN_EXPIRYDATE ="expiryDate";
    public static final String FN_SENDTIME ="sendTime";
    public static final String FN_UESED ="uesed";
    public static final String FN_PLATSUPPLIERD ="platSupplierId";
    public static final String FN_ADDADMIN ="addAdmin";
    public static final String FN_ADMINCODE ="adminCode";
    public static final String FN_ADMINNAME ="adminName";
    public static final String FN_APPROVAL ="approval";
    public static final String FN_APPROVEDBY ="approvedBy";
    public static final String FN_APPROVALDATE ="approvalDate";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private long vendorId;
    private String invitationContent;
    private Date expiryDate;
    private Date sendTime;
    private boolean uesed;
    private long platSupplierId;
    private boolean addAdmin;
    private String adminCode;
    private String adminName;
    private boolean approval;
    private String approvedBy;
    private Date approvalDate;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String controlUnitNo;

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
    
    public String getInvitationContent() {
		return invitationContent;
	}

	public void setInvitationContent(String invitationContent) {
		this.invitationContent = invitationContent;
	}

	public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date val) {
        this.expiryDate = val;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date val) {
        this.sendTime = val;
    }
    public boolean isUesed() {
        return uesed;
    }

    public void setUesed(boolean val) {
        this.uesed = val;
    }
    public long getPlatSupplierId() {
		return platSupplierId;
	}

	public void setPlatSupplierId(long platSupplierId) {
		this.platSupplierId = platSupplierId;
	}

	public boolean isAddAdmin() {
        return addAdmin;
    }

    public void setAddAdmin(boolean val) {
        this.addAdmin = val;
    }
    public String getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(String val) {
        this.adminCode = val;
    }
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String val) {
        this.adminName = val;
    }
    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean val) {
        this.approval = val;
    }
    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String val) {
        this.approvedBy = val;
    }
    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date val) {
        this.approvalDate = val;
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
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmSupplierRegInvitation(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmSupplierRegInvitation() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_VENDORID,
            FN_INVITATIONCONTENT,
            FN_EXPIRYDATE,
            FN_SENDTIME,
            FN_UESED,
            FN_PLATSUPPLIERD,
            FN_ADDADMIN,
            FN_ADMINCODE,
            FN_ADMINNAME,
            FN_APPROVAL,
            FN_APPROVEDBY,
            FN_APPROVALDATE,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
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
        map.put(FN_INVITATIONCONTENT, 1000);
        map.put(FN_ADMINCODE, 32);
        map.put(FN_ADMINNAME, 80);
        map.put(FN_APPROVEDBY, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
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
