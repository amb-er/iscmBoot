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
 
@XmlRootElement(name = "scmFbmSellDetail")  
public class ScmFbmSellDetail extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_ACCDATE ="accDate";
    public static final String FN_DISHID ="dishId";
    public static final String FN_DISHCODE ="dishCode";
    public static final String FN_DISHNAME ="dishName";
    public static final String FN_FBMSTATCODE ="fbmStatCode";
    public static final String FN_FBMSTATNAME ="fbmStatName";
    public static final String FN_DISHSPECID ="dishSpecId";
    public static final String FN_SPECCODE ="specCode";
    public static final String FN_SPECNAME ="specName";
    public static final String FN_DEPTCODE ="deptCode";
    public static final String FN_DEPTNAME ="deptName";
    public static final String FN_SALEQTY ="saleQty";
    public static final String FN_STDSALEPRICE ="stdSalePrice";
    public static final String FN_SALEAMT ="saleAmt";
    public static final String FN_REALSALEAMT ="realSaleAmt";
    
    private long id;
    private String orgUnitNo;
    private Date accDate;
    private long dishId;
    private String dishCode;
    private String dishName;
    private String fbmStatCode;
    private String fbmStatName;
	private long dishSpecId;
    private String specCode;
    private String specName;
    private String deptCode;
    private String deptName;
    private BigDecimal saleQty;
    private BigDecimal stdSalePrice;
    private BigDecimal saleAmt;
    private BigDecimal realSaleAmt;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public Date getAccDate() {
        return accDate;
    }

    public void setAccDate(Date val) {
        this.accDate = val;
    }
    public long getDishId() {
        return dishId;
    }

    public void setDishId(long val) {
        this.dishId = val;
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

    public String getFbmStatCode() {
        return fbmStatCode;
    }

    public void setFbmStatCode(String val) {
        this.fbmStatCode = val;
    }
    public String getFbmStatName() {
        return fbmStatName;
    }

    public void setFbmStatName(String val) {
        this.fbmStatName = val;
    }
    public long getDishSpecId() {
		return dishSpecId;
	}

	public void setDishSpecId(long dishSpecId) {
		this.dishSpecId = dishSpecId;
	}

	public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String val) {
        this.specCode = val;
    }
    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String val) {
        this.specName = val;
    }
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String val) {
        this.deptCode = val;
    }
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String val) {
        this.deptName = val;
    }
    public BigDecimal getSaleQty() {
        return saleQty;
    }

    public void setSaleQty(BigDecimal val) {
        this.saleQty = val;
    }
    public BigDecimal getStdSalePrice() {
        return stdSalePrice;
    }

    public void setStdSalePrice(BigDecimal val) {
        this.stdSalePrice = val;
    }
    public BigDecimal getSaleAmt() {
        return saleAmt;
    }

    public void setSaleAmt(BigDecimal val) {
        this.saleAmt = val;
    }

    public BigDecimal getRealSaleAmt() {
		return realSaleAmt;
	}

	public void setRealSaleAmt(BigDecimal realSaleAmt) {
		this.realSaleAmt = realSaleAmt;
	}

	public ScmFbmSellDetail(boolean defaultValue){
       if(defaultValue){
       }
    }
  	public ScmFbmSellDetail() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_ORGUNITNO,
            FN_ACCDATE,
            FN_DISHID,
            FN_DISHCODE,
            FN_DISHNAME,
            FN_FBMSTATCODE,
            FN_FBMSTATNAME,
            FN_DISHSPECID,
            FN_SPECCODE,
            FN_SPECNAME,
            FN_DEPTCODE,
            FN_DEPTNAME,
            FN_SALEQTY,
            FN_STDSALEPRICE,
            FN_SALEAMT,
            FN_REALSALEAMT
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
        map.put(FN_DISHCODE, 16);
        map.put(FN_DISHNAME, 30);
        map.put(FN_FBMSTATCODE, 16);
        map.put(FN_FBMSTATNAME, 30);
        map.put(FN_SPECCODE, 16);
        map.put(FN_SPECNAME, 30);
        map.put(FN_DEPTCODE, 16);
        map.put(FN_DEPTNAME, 30);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
