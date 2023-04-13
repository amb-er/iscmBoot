package com.armitage.server.ifbc.rptdata.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmDiskStdCostInfo")  
public class ScmDiskStdCostInfo extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_ACCDATE ="accDate";
    public static final String FN_DEPTCODE ="deptCode";
    public static final String FN_DISHID ="dishId";
    public static final String FN_DISHSPECID ="dishSpecId";
    public static final String FN_TYPE ="type";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_UNITID ="unitId";
    public static final String FN_STDQTY ="stdQty";
    public static final String FN_PRICE ="price";
    public static final String FN_STDAMT ="stdAmt";
    
    private long id;
    private String orgUnitNo;
    private Date accDate;
    private String deptCode;
    private long dishId;
    private long dishSpecId;
    private String type;
    private long itemId;
    private long unitId;
    private BigDecimal stdQty;
    private BigDecimal price;
    private BigDecimal stdAmt;

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
    public Date getAccDate() {
        return accDate;
    }

    public void setAccDate(Date val) {
        this.accDate = val;
    }
    public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
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

	public void setDishSpecId(long dishSpecId) {
		this.dishSpecId = dishSpecId;
	}

	public String getType() {
        return type;
    }

    public void setType(String val) {
        this.type = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long val) {
        this.unitId = val;
    }
    public BigDecimal getStdQty() {
        return stdQty;
    }

    public void setStdQty(BigDecimal val) {
        this.stdQty = val;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal val) {
        this.price = val;
    }
    public BigDecimal getStdAmt() {
        return stdAmt;
    }

    public void setStdAmt(BigDecimal val) {
        this.stdAmt = val;
    }

    public ScmDiskStdCostInfo(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmDiskStdCostInfo() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_ACCDATE,
            FN_DEPTCODE,
            FN_DISHID,
            FN_DISHSPECID,
            FN_TYPE,
            FN_ITEMID,
            FN_UNITID,
            FN_STDQTY,
            FN_PRICE,
            FN_STDAMT,
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
        map.put(FN_TYPE, 16);
        map.put(FN_DEPTCODE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
