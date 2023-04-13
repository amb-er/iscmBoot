
package com.armitage.server.ifbc.costcard.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmProductCostCard")  
public class ScmProductCostCard extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_PRODUCTID ="productId";
    public static final String FN_PRODUCTUNIT ="productUnit";
    public static final String FN_PRODUCTQTY ="productQty";
    public static final String FN_EFFECTIVEDATE ="effectiveDate";
    public static final String FN_FLAG ="flag";
    public static final String FN_APPROVALED ="approvaled";
    public static final String FN_CONTROLUNITNO ="controlUnitNo";
    public static final String FN_COSTPRICE ="costPrice";
    
    private long id;
    private String orgUnitNo;
    private long productId;
    private long productUnit;
    private BigDecimal productQty;
    private Date effectiveDate;
    private boolean flag;
    private boolean approvaled;
    private String controlUnitNo;
    private BigDecimal costPrice;

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
    public long getProductId() {
        return productId;
    }

    public void setProductId(long val) {
        this.productId = val;
    }
    public long getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(long val) {
        this.productUnit = val;
    }
    public BigDecimal getProductQty() {
        return productQty;
    }

    public void setProductQty(BigDecimal val) {
        this.productQty = val;
    }
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date val) {
        this.effectiveDate = val;
    }
    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean val) {
        this.flag = val;
    }
    public boolean getApprovaled() {
        return approvaled;
    }

    public void setApprovaled(boolean val) {
        this.approvaled = val;
    }
    public String getControlUnitNo() {
        return controlUnitNo;
    }

    public void setControlUnitNo(String val) {
        this.controlUnitNo = val;
    }

    public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public ScmProductCostCard(boolean defaultValue){
       if(defaultValue){
    	   this.productQty=BigDecimal.ZERO;
    	   this.costPrice=BigDecimal.ZERO;
       }
    }
  	public ScmProductCostCard() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_PRODUCTID,
            FN_PRODUCTUNIT,
            FN_PRODUCTQTY,
            FN_EFFECTIVEDATE,
            FN_FLAG,
            FN_APPROVALED,
            FN_CONTROLUNITNO,
            FN_COSTPRICE,
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
    	return new String[]{
                FN_PRODUCTID,
                FN_PRODUCTQTY,
                FN_PRODUCTUNIT,
                FN_EFFECTIVEDATE,
            };
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
