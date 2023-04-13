
package com.armitage.server.iscm.inventorymanage.inventorysetting.model;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmInvWareHouse")  
public class ScmInvWareHouse extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_WHNO ="whNo";
    public static final String FN_WHNAME ="whName";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_ADDRESS ="address";
    public static final String FN_CONTACTOR ="contactor";
    public static final String FN_TEL ="tel";
    public static final String FN_PYM ="pym";
    public static final String FN_USEPERMISSION ="usePermission";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_STATUS ="status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String whNo;
    private String whName;
    private String orgUnitNo;
    private String address;
    private String contactor;
    private String tel;
    private boolean usePermission;
    private String pym;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String remarks;
    private String status;
    private String controlUnitNo;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getWhNo() {
        return whNo;
    }

    public void setWhNo(String val) {
        this.whNo = val;
    }
    public String getWhName() {
        return whName;
    }

    public void setWhName(String val) {
        this.whName = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String val) {
        this.address = val;
    }
    public String getContactor() {
        return contactor;
    }

    public void setContactor(String val) {
        this.contactor = val;
    }
    public String getTel() {
        return tel;
    }

    public void setTel(String val) {
        this.tel = val;
    }
    public boolean isUsePermission() {
		return usePermission;
	}

	public void setUsePermission(boolean usePermission) {
		this.usePermission = usePermission;
	}

	public String getPym() {
		return pym;
	}

	public void setPym(String pym) {
		this.pym = pym;
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
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String val) {
        this.remarks = val;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String val) {
        this.status = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmInvWareHouse(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmInvWareHouse() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_WHNO,
            FN_WHNAME,
            FN_ORGUNITNO,
            FN_ADDRESS,
            FN_CONTACTOR,
            FN_TEL,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_REMARKS,
            FN_STATUS,
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
		list.add(new String[] { FN_WHNO, FN_CONTROLUNITNO });
		list.add(new String[] { FN_WHNAME, FN_CONTROLUNITNO });
		return list;
	}
	 
	public String[] getRequiredFields() {
		return new String[] {FN_WHNAME };
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
        map.put(FN_WHNO, 16);
        map.put(FN_WHNAME, 80);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_ADDRESS, 200);
        map.put(FN_CONTACTOR, 50);
        map.put(FN_TEL, 20);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_REMARKS, 250);
        map.put(FN_STATUS, 16);
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
