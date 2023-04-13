package com.armitage.server.ifbc.basedata.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmPriceUpdSet")  
public class ScmPriceUpdSet extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_UPDPRICETYPE ="updPriceType";
    public static final String FN_DAYS ="days";
    public static final String FN_ITEMPRICEUPD ="itemPriceUpd";
    public static final String FN_BASECOSTUPD ="baseCostUpd";
    public static final String FN_PRICEGETTIME ="priceGetTime";
    public static final String FN_GENERATECOSTCONSUMETIME ="generateCostConsumeTime";
    public static final String FN_CALCFBCRPTDATATIME ="calcFbcRptDataTime";
    public static final String FN_FLAG ="flag";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    
    private long id;
    private String orgUnitNo;
    private String updPriceType;
    private int days;
    private String itemPriceUpd;
    private String baseCostUpd;
    private String priceGetTime;
    private String generateCostConsumeTime;
    private String calcFbcRptDataTime;
    private boolean flag;
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
    public String getUpdPriceType() {
        return updPriceType;
    }

    public void setUpdPriceType(String val) {
        this.updPriceType = val;
    }
    public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getItemPriceUpd() {
        return itemPriceUpd;
    }

    public void setItemPriceUpd(String val) {
        this.itemPriceUpd = val;
    }
    public String getBaseCostUpd() {
        return baseCostUpd;
    }

    public void setBaseCostUpd(String val) {
        this.baseCostUpd = val;
    }
    public String getPriceGetTime() {
		return priceGetTime;
	}

	public void setPriceGetTime(String priceGetTime) {
		this.priceGetTime = priceGetTime;
	}

	public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public String getGenerateCostConsumeTime() {
		return generateCostConsumeTime;
	}

	public void setGenerateCostConsumeTime(String generateCostConsumeTime) {
		this.generateCostConsumeTime = generateCostConsumeTime;
	}

	public String getCalcFbcRptDataTime() {
		return calcFbcRptDataTime;
	}

	public void setCalcFbcRptDataTime(String calcFbcRptDataTime) {
		this.calcFbcRptDataTime = calcFbcRptDataTime;
	}

	public ScmPriceUpdSet(boolean defaultValue){
       if(defaultValue){
    	   this.updPriceType="1";
    	   this.flag=true;
    	   this.itemPriceUpd="14:00";
    	   this.baseCostUpd="15:00";
    	   this.priceGetTime="10:00";
    	   this.generateCostConsumeTime="09:00";
    	   this.calcFbcRptDataTime="08:00";
    	   this.days=60;
       }
    }
  	public ScmPriceUpdSet() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_UPDPRICETYPE,
            FN_DAYS,
            FN_ITEMPRICEUPD,
            FN_BASECOSTUPD,
            FN_PRICEGETTIME,
            FN_FLAG,
            FN_CONTROLUNITNO,
            FN_GENERATECOSTCONSUMETIME,
            FN_CALCFBCRPTDATATIME,
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
		return new String[] {
	            FN_UPDPRICETYPE,
	            FN_DAYS,
	            FN_ITEMPRICEUPD,
	            FN_BASECOSTUPD,
	            FN_PRICEGETTIME,
	            FN_GENERATECOSTCONSUMETIME,
	            FN_CALCFBCRPTDATATIME};
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
        map.put(FN_UPDPRICETYPE, 16);
        map.put(FN_ITEMPRICEUPD, 8);
        map.put(FN_BASECOSTUPD, 8);
        map.put(FN_PRICEGETTIME, 8);
        map.put(FN_CONTROLUNITNO, 32);
        map.put(FN_GENERATECOSTCONSUMETIME, 8);
        map.put(FN_CALCFBCRPTDATATIME, 8);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
