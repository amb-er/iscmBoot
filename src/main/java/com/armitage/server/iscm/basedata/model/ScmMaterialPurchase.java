
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
 
@XmlRootElement(name = "scmMaterialPurchase")  
public class ScmMaterialPurchase extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_GUID ="guId";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_PURUNIT ="purUnit";
    public static final String FN_PURGROUPID ="purGroupId";
    public static final String FN_BUYERID ="buyerId";
    public static final String FN_RECEIVETOPRATIO ="receiveTopRatio";
    public static final String FN_RECEIVEBOTTOMRATIO ="receiveBottomRatio";
    public static final String FN_NEEDINQUIRE ="needInquire";
    public static final String FN_DEFAULTRATE ="defaultRate";
    public static final String FN_SEEKMODE = "seekMode";
    public static final String FN_CREATOR ="creator";
    public static final String FN_CREATEDATE ="createDate";
    public static final String FN_EDITOR ="editor";
    public static final String FN_EDITDATE ="editDate";
    public static final String FN_FREEZEORGUNIT ="freezeOrgUnit";
    public static final String FN_FREEZETIME ="freezeTime";
    public static final String FN_STATUS ="status";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_SUPPLYCYCLE ="supplyCycle";
    
    private long id;
    private String guId;
    private long itemId;
    private String orgUnitNo;
    private long purUnit;
    private long purGroupId;
    private long buyerId;
    private BigDecimal receiveTopRatio;
    private BigDecimal receiveBottomRatio;
    private String needInquire;
    private BigDecimal defaultRate;
    private String seekMode;
    private int supplyCycle;
    private String creator;
    private Date createDate;
    private String editor;
    private Date editDate;
    private String freezeOrgUnit;
    private Date freezeTime;
    private String status;
    private String controlUnitNo;
    
	public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getGuId() {
        return guId;
    }

    public void setGuId(String val) {
        this.guId = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public long getPurUnit() {
        return purUnit;
    }

    public void setPurUnit(long val) {
        this.purUnit = val;
    }
    public long getPurGroupId() {
        return purGroupId;
    }

    public void setPurGroupId(long val) {
        this.purGroupId = val;
    }
    
    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public BigDecimal getReceiveTopRatio() {
        return receiveTopRatio;
    }

    public void setReceiveTopRatio(BigDecimal val) {
        this.receiveTopRatio = val;
    }
    public BigDecimal getReceiveBottomRatio() {
        return receiveBottomRatio;
    }

    public void setReceiveBottomRatio(BigDecimal val) {
        this.receiveBottomRatio = val;
    }
    public String getNeedInquire() {
        return needInquire;
    }

    public void setNeedInquire(String val) {
        this.needInquire = val;
    }
    public BigDecimal getDefaultRate() {
        return defaultRate;
    }

    public void setDefaultRate(BigDecimal val) {
        this.defaultRate = val;
    }
    public String getSeekMode() {
		return seekMode;
	}

	public void setSeekMode(String seekMode) {
		this.seekMode = seekMode;
	}

	public int getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(int supplyCycle) {
		this.supplyCycle = supplyCycle;
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
    public String getFreezeOrgUnit() {
        return freezeOrgUnit;
    }

    public void setFreezeOrgUnit(String val) {
        this.freezeOrgUnit = val;
    }
    public Date getFreezeTime() {
		return freezeTime;
	}

	public void setFreezeTime(Date freezeTime) {
		this.freezeTime = freezeTime;
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

    public ScmMaterialPurchase(boolean defaultValue){
       if(defaultValue){
        defaultRate=BigDecimal.ZERO;
        purGroupId=0;
        receiveTopRatio = BigDecimal.ZERO;
        receiveBottomRatio = BigDecimal.ZERO;
        guId="";
        needInquire="Y";
        supplyCycle=0;
        seekMode="0";
       }
    }
  	public ScmMaterialPurchase() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_GUID,
            FN_ITEMID,
            FN_ORGUNITNO,
            FN_PURUNIT,
            FN_PURGROUPID,
            FN_BUYERID,
            FN_RECEIVETOPRATIO,
            FN_RECEIVEBOTTOMRATIO,
            FN_NEEDINQUIRE,
            FN_DEFAULTRATE,
            FN_SEEKMODE,
            FN_CREATOR,
            FN_CREATEDATE,
            FN_EDITOR,
            FN_EDITDATE,
            FN_FREEZEORGUNIT,
            FN_FREEZETIME,
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
		//list.add(new String[] { FN_CODE, FN_CATEGORY });
		return list;
	}
	 
	public String[] getRequiredFields() {
	    /*DEMO:
		return new String[] {FN_CODE };
        */
		return new String[] {FN_ORGUNITNO,FN_PURUNIT};
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
        map.put(FN_GUID, 40);
        map.put(FN_ORGUNITNO, 32);
        map.put(FN_SEEKMODE, 16);
        map.put(FN_CREATOR, 16);
        map.put(FN_EDITOR, 16);
        map.put(FN_FREEZEORGUNIT, 32);
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
