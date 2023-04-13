package com.armitage.server.ifbc.costcard.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
import com.armitage.server.common.util.CalendarUtil;
 
@XmlRootElement(name = "scmCostCard")  
public class ScmCostCard extends BaseModel  {
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_DISHID ="dishId";
    public static final String FN_DISHCODE ="dishCode";
    public static final String FN_DISHNAME ="dishName";
    public static final String FN_FBMITEMCODE ="fbmItemCode";
    public static final String FN_FBMSTATCODE ="fbmStatCode";
    public static final String FN_FBMSTATNAME ="fbmStatName";
    public static final String FN_PRODUCTIONTIME ="productionTime";
    public static final String FN_DISHSPECID ="dishSpecId";
    public static final String FN_SALEPRICE ="salePrice";
    public static final String FN_COSTPRICE ="costPrice";
    public static final String FN_EFFECTIVEDATE ="effectiveDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_WINE ="wine";
    public static final String FN_LOCKED ="locked";
    public static final String FN_APPROVALED ="approvaled";
	public static final String FN_RESORGUNITNO = "resOrgUnitNo";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private long dishId;
    private String dishCode;
    private String dishName;
    private String fbmItemCode;
    private String fbmStatCode;
    private String fbmStatName;
    private int productionTime;
	private long dishSpecId;
    private BigDecimal salePrice;
    private BigDecimal costPrice;
    private Date effectiveDate;
    private boolean flag;
    private boolean wine;
    private boolean locked;
    private boolean approvaled;
	private String resOrgUnitNo;
    private String controlUnitNo;


	public boolean isWine() {
		return wine;
	}

	public void setWine(boolean wine) {
		this.wine = wine;
	}

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
    public long getDishId() {
        return dishId;
    }

	public void setDishId(long dishId) {
		this.dishId = dishId;
    }

    public String getDishCode() {
        return dishCode;
    }

    public void setDishCode(String val) {
        this.dishCode = val;
    }
    public String getDishName() {
        return dishName;
    }

    public void setDishName(String val) {
        this.dishName = val;
    }
    public String getFbmItemCode() {
		return fbmItemCode;
	}

	public void setFbmItemCode(String fbmItemCode) {
		this.fbmItemCode = fbmItemCode;
	}

	public String getFbmStatCode() {
        return fbmStatCode;
    }

	public void setFbmStatCode(String fbmStatCode) {
		this.fbmStatCode = fbmStatCode;
    }

    public String getFbmStatName() {
        return fbmStatName;
    }

	public void setFbmStatName(String fbmStatName) {
		this.fbmStatName = fbmStatName;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int val) {
        this.productionTime = val;
    }
	public long getDishSpecId() {
        return dishSpecId;
    }

	public void setDishSpecId(long dishSpecId) {
		this.dishSpecId = dishSpecId;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal val) {
        this.salePrice = val;
    }
    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal val) {
        this.costPrice = val;
    }
	
    public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public boolean isFlag() {
        return flag;
    }

	public void setFlag(boolean flag) {
		this.flag = flag;
    }

    public boolean isLocked() {
        return locked;
    }

	public void setLocked(boolean locked) {
		this.locked = locked;
    }

    public boolean isApprovaled() {
		return approvaled;
	}

	public void setApprovaled(boolean approvaled) {
		this.approvaled = approvaled;
	}

	public String getResOrgUnitNo() {
		return resOrgUnitNo;
	}

	public void setResOrgUnitNo(String resOrgUnitNo) {
		this.resOrgUnitNo = resOrgUnitNo;
	}

	public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public ScmCostCard(boolean defaultValue){
       if(defaultValue){
			this.salePrice=BigDecimal.ZERO;
			this.costPrice=BigDecimal.ZERO;
			this.flag=true;
			this.locked=false;
			this.approvaled=false;
       }
    }
  	public ScmCostCard() {
	}

	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_DISHID,
            FN_DISHCODE,
            FN_DISHNAME,
            FN_FBMSTATCODE,
            FN_FBMSTATNAME,
            FN_PRODUCTIONTIME,
            FN_DISHSPECID,
            FN_SALEPRICE,
            FN_COSTPRICE,
            FN_FLAG,
            FN_LOCKED,
            FN_APPROVALED,
			FN_RESORGUNITNO,
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
		return new String[] {FN_DISHSPECID,FN_DISHCODE,FN_EFFECTIVEDATE };
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
        map.put(FN_DISHCODE, 16);
        map.put(FN_DISHNAME, 60);
        map.put(FN_FBMSTATCODE, 16);
        map.put(FN_FBMSTATNAME, 30);
		map.put(FN_RESORGUNITNO, 32);
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

