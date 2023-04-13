package com.armitage.server.ifbm.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "fbmDishPrc")  
public class FbmDishPrc extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_DISHID ="dishId";
    public static final String FN_DISHSPECID ="dishSpecId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_MENUID ="menuId";
    public static final String FN_PRICE ="price";
    public static final String FN_COST ="cost";
    public static final String FN_ALLOWREBATE ="allowRebate";
    public static final String FN_ALLOWDEDUCT ="allowDeduct";
    public static final String FN_DEDUCTMODE ="deductMode";
    public static final String FN_DEDUCTVALUE ="deductValue";
    public static final String FN_LOWLIMIT ="lowLimit";
    public static final String FN_FRTPACKAGE ="frtPackage";
    public static final String FN_CRMITEM ="crmItem";
    public static final String FN_IVCODE ="ivCode";
    public static final String FN_PRICE1 ="price1";
    public static final String FN_PRICE2 ="price2";
    public static final String FN_PRICE3 ="price3";
    public static final String FN_PRICE4 ="price4";
    public static final String FN_PRICE5 ="price5";
    public static final String FN_PRICE6 ="price6";
    public static final String FN_RATER ="rater";
    public static final String FN_RATETIME ="rateTime";
    public static final String FN_SORT ="sort";
    public static final String FN_PERMIT ="permit";
    public static final String FN_REMARKS ="remarks";
    public static final String FN_ISYNCMODFLAG ="iSyncModFlag";
    
    private long id;
    private long dishId;
    private long dishSpecId;
    private String orgUnitNo;
    private long menuId;
    private BigDecimal price;
    private BigDecimal cost;
    private boolean allowRebate;
    private boolean allowDeduct;
    private String deductMode;
    private BigDecimal deductValue;
    private boolean lowLimit;
    private String frtPackage;
    private String crmItem;
    private String ivCode;
    private BigDecimal price1;
    private BigDecimal price2;
    private BigDecimal price3;
    private BigDecimal price4;
    private BigDecimal price5;
    private BigDecimal price6;
    private String rater;
    private Date rateTime;
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
    public long getDishId() {
        return dishId;
    }

    public void setDishId(long val) {
        this.dishId = val;
    }
    public long getDishSpecId() {
        return dishSpecId;
    }

    public void setDishSpecId(long val) {
        this.dishSpecId = val;
    }
    public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public long getMenuId() {
        return menuId;
    }

    public void setMenuId(long val) {
        this.menuId = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal val) {
        this.cost = val;
    }
    public boolean isAllowRebate() {
        return allowRebate;
    }

    public void setAllowRebate(boolean val) {
        this.allowRebate = val;
    }
    public boolean isAllowDeduct() {
        return allowDeduct;
    }

    public void setAllowDeduct(boolean val) {
        this.allowDeduct = val;
    }
    public String getDeductMode() {
        return deductMode;
    }

    public void setDeductMode(String val) {
        this.deductMode = val;
    }
    public BigDecimal getDeductValue() {
        return deductValue;
    }

    public void setDeductValue(BigDecimal val) {
        this.deductValue = val;
    }
    public boolean isLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(boolean val) {
        this.lowLimit = val;
    }
    public String getFrtPackage() {
        return frtPackage;
    }

    public void setFrtPackage(String val) {
        this.frtPackage = val;
    }
    public String getCrmItem() {
        return crmItem;
    }

    public void setCrmItem(String val) {
        this.crmItem = val;
    }
    public String getIvCode() {
        return ivCode;
    }

    public void setIvCode(String val) {
        this.ivCode = val;
    }
    public BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(BigDecimal val) {
        this.price1 = val;
    }
    public BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(BigDecimal val) {
        this.price2 = val;
    }
    public BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(BigDecimal val) {
        this.price3 = val;
    }
    public BigDecimal getPrice4() {
        return price4;
    }

    public void setPrice4(BigDecimal val) {
        this.price4 = val;
    }
    public BigDecimal getPrice5() {
        return price5;
    }

    public void setPrice5(BigDecimal val) {
        this.price5 = val;
    }
    public BigDecimal getPrice6() {
        return price6;
    }

    public void setPrice6(BigDecimal val) {
        this.price6 = val;
    }
    public String getRater() {
        return rater;
    }

    public void setRater(String val) {
        this.rater = val;
    }
    public Date getRateTime() {
        return rateTime;
    }

    public void setRateTime(Date val) {
        this.rateTime = val;
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

    public FbmDishPrc(boolean defaultValue){
       if(defaultValue){
        menuId = 0;
        price = BigDecimal.ZERO;
        cost = BigDecimal.ZERO;
        allowRebate = false;
        allowDeduct = false;
        deductMode = "'0'";
        deductValue = BigDecimal.ZERO;
        lowLimit = true;
        price1 = BigDecimal.ZERO;
        price2 = BigDecimal.ZERO;
        price3 = BigDecimal.ZERO;
        price4 = BigDecimal.ZERO;
        price5 = BigDecimal.ZERO;
        price6 = BigDecimal.ZERO;
        sort = 0;
        permit = true;
        iSyncModFlag = "'SELF'";
       }
    }
  	public FbmDishPrc() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_DISHID,
            FN_DISHSPECID,
            FN_ORGUNITNO,
            FN_MENUID,
            FN_PRICE,
            FN_COST,
            FN_ALLOWREBATE,
            FN_ALLOWDEDUCT,
            FN_DEDUCTMODE,
            FN_DEDUCTVALUE,
            FN_LOWLIMIT,
            FN_FRTPACKAGE,
            FN_CRMITEM,
            FN_IVCODE,
            FN_PRICE1,
            FN_PRICE2,
            FN_PRICE3,
            FN_PRICE4,
            FN_PRICE5,
            FN_PRICE6,
            FN_RATER,
            FN_RATETIME,
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
        map.put(FN_DEDUCTMODE, 1);
        map.put(FN_FRTPACKAGE, 8);
        map.put(FN_CRMITEM, 8);
        map.put(FN_IVCODE, 20);
        map.put(FN_RATER, 16);
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
