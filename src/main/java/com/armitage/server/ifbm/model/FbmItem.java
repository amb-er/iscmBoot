package com.armitage.server.ifbm.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "fbmItem")  
public class FbmItem extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_PID ="pid";
    public static final String FN_CODE ="code";
    public static final String FN_NAME ="name";
    public static final String FN_MNEMONIC ="mnemonic";
    public static final String FN_BACKCOLOR ="backColor";
    public static final String FN_TEXTCOLOR ="textColor";
    public static final String FN_SERVEORDER ="serveOrder";
    public static final String FN_SORT ="sort";
    public static final String FN_PERMIT ="permit";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_ISYNCMODFLAG ="iSyncModFlag";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private long pid;
    private String code;
    private String name;
    private String mnemonic;
    private int backColor;
    private int textColor;
    private int serveOrder;
    private int sort;
    private boolean permit;
    private String remarks;
    private String iSyncModFlag;
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
    public long getPid() {
        return pid;
    }

    public void setPid(long val) {
        this.pid = val;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String val) {
        this.code = val;
    }
    public String getName() {
        return name;
    }

    public void setName(String val) {
        this.name = val;
    }
    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String val) {
        this.mnemonic = val;
    }
    public int getBackColor() {
        return backColor;
    }

    public void setBackColor(int val) {
        this.backColor = val;
    }
    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int val) {
        this.textColor = val;
    }
    public int getServeOrder() {
        return serveOrder;
    }

    public void setServeOrder(int val) {
        this.serveOrder = val;
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
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public FbmItem(boolean defaultValue){
       if(defaultValue){
        pid = -1;
        backColor = -1;
        textColor = -1;
        serveOrder = 0;
        sort = 0;
        permit = true;
        iSyncModFlag = "'SELF'";
       }
    }
  	public FbmItem() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_PID,
            FN_CODE,
            FN_NAME,
            FN_MNEMONIC,
            FN_BACKCOLOR,
            FN_TEXTCOLOR,
            FN_SERVEORDER,
            FN_SORT,
            FN_PERMIT,
            FN_REMARKS,
            FN_ISYNCMODFLAG,
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
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_CODE, 16);
        map.put(FN_NAME, 30);
        map.put(FN_MNEMONIC, 30);
        map.put(FN_REMARKS, 60);
        map.put(FN_ISYNCMODFLAG, 20);
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
