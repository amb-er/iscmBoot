package com.armitage.server.ifbc.rptdata.model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 
@XmlRootElement(name = "scmUseOrgUnitItemSum")  
public class ScmUseOrgUnitItemSum extends BaseModel  {
	 
    public static final String FN_ID ="id";
    public static final String FN_CKECKPERIODID ="ckeckPeriodId";
    public static final String FN_PERIODID ="periodId";
    public static final String FN_ORGUNITNO ="orgUnitNo";
    public static final String FN_ITEMID ="itemId";
    public static final String FN_COSTTYPE ="costType";
    public static final String FN_UNITID ="unitId";
    public static final String FN_STARTQTY ="startQty";
    public static final String FN_STARTAMT ="startAmt";
    public static final String FN_WIQTY ="wiQty";
    public static final String FN_WIAMT ="wiAmt";
    public static final String FN_DIQTY ="diQty";
    public static final String FN_DIAMT ="diAmt";
    public static final String FN_MIQTY ="miQty";
    public static final String FN_MIAMT ="miAmt";
    public static final String FN_MOQTY ="moQty";
    public static final String FN_MOAMT ="moAmt";
    public static final String FN_ILQTY ="ilQty";
    public static final String FN_ILAMT ="ilAmt";
    public static final String FN_DSQTY ="dsQty";
    public static final String FN_DSAMT ="dsAmt";
    public static final String FN_DCQTY ="dcQty";
    public static final String FN_DCAMT ="dcAmt";
    public static final String FN_ENDQTY ="endQty";
    public static final String FN_ENDAMT ="endAmt";
    
    private long id;
    private long ckeckPeriodId;
    private long periodId;
    private String orgUnitNo;
    private long itemId;
    private String costType;
    private long unitId;
    private BigDecimal startQty;
    private BigDecimal startAmt;
    private BigDecimal wiQty;
    private BigDecimal wiAmt;
    private BigDecimal diQty;
    private BigDecimal diAmt;
    private BigDecimal miQty;
    private BigDecimal miAmt;
    private BigDecimal moQty;
    private BigDecimal moAmt;
    private BigDecimal ilQty;
    private BigDecimal ilAmt;
    private BigDecimal dsQty;
    private BigDecimal dsAmt;
    private BigDecimal dcQty;
    private BigDecimal dcAmt;
    private BigDecimal endQty;
    private BigDecimal endAmt;

    public long getId() {
        return id;
    }

    public void setId(long val) {
        this.id = val;
    }
    public long getCkeckPeriodId() {
        return ckeckPeriodId;
    }

    public void setCkeckPeriodId(long val) {
        this.ckeckPeriodId = val;
    }
    public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long periodId) {
		this.periodId = periodId;
	}

	public String getOrgUnitNo() {
        return orgUnitNo;
    }

    public void setOrgUnitNo(String val) {
        this.orgUnitNo = val;
    }
    public long getItemId() {
        return itemId;
    }

    public void setItemId(long val) {
        this.itemId = val;
    }
    public String getCostType() {
        return costType;
    }

    public void setCostType(String val) {
        this.costType = val;
    }
    public long getUnitId() {
        return unitId;
    }

    public void setUnitId(long val) {
        this.unitId = val;
    }
    public BigDecimal getStartQty() {
        return startQty;
    }

    public void setStartQty(BigDecimal val) {
        this.startQty = val;
    }
    public BigDecimal getStartAmt() {
        return startAmt;
    }

    public void setStartAmt(BigDecimal val) {
        this.startAmt = val;
    }
    public BigDecimal getWiQty() {
        return wiQty;
    }

    public void setWiQty(BigDecimal val) {
        this.wiQty = val;
    }
    public BigDecimal getWiAmt() {
        return wiAmt;
    }

    public void setWiAmt(BigDecimal val) {
        this.wiAmt = val;
    }
    public BigDecimal getDiQty() {
        return diQty;
    }

    public void setDiQty(BigDecimal val) {
        this.diQty = val;
    }
    public BigDecimal getDiAmt() {
        return diAmt;
    }

    public void setDiAmt(BigDecimal val) {
        this.diAmt = val;
    }
    public BigDecimal getMiQty() {
        return miQty;
    }

    public void setMiQty(BigDecimal val) {
        this.miQty = val;
    }
    public BigDecimal getMiAmt() {
        return miAmt;
    }

    public void setMiAmt(BigDecimal val) {
        this.miAmt = val;
    }
    public BigDecimal getMoQty() {
        return moQty;
    }

    public void setMoQty(BigDecimal val) {
        this.moQty = val;
    }
    public BigDecimal getMoAmt() {
        return moAmt;
    }

    public void setMoAmt(BigDecimal val) {
        this.moAmt = val;
    }
    public BigDecimal getIlQty() {
        return ilQty;
    }

    public void setIlQty(BigDecimal val) {
        this.ilQty = val;
    }
    public BigDecimal getIlAmt() {
        return ilAmt;
    }

    public void setIlAmt(BigDecimal val) {
        this.ilAmt = val;
    }
    public BigDecimal getDsQty() {
        return dsQty;
    }

    public void setDsQty(BigDecimal val) {
        this.dsQty = val;
    }
    public BigDecimal getDsAmt() {
        return dsAmt;
    }

    public void setDsAmt(BigDecimal val) {
        this.dsAmt = val;
    }
    public BigDecimal getDcQty() {
        return dcQty;
    }

    public void setDcQty(BigDecimal val) {
        this.dcQty = val;
    }
    public BigDecimal getDcAmt() {
        return dcAmt;
    }

    public void setDcAmt(BigDecimal val) {
        this.dcAmt = val;
    }
    public BigDecimal getEndQty() {
        return endQty;
    }

    public void setEndQty(BigDecimal val) {
        this.endQty = val;
    }
    public BigDecimal getEndAmt() {
        return endAmt;
    }

    public void setEndAmt(BigDecimal val) {
        this.endAmt = val;
    }

    public ScmUseOrgUnitItemSum(boolean defaultValue){
		if (defaultValue) {
			startQty = BigDecimal.ZERO;
			startAmt = BigDecimal.ZERO;
			wiQty = BigDecimal.ZERO;
			wiAmt = BigDecimal.ZERO;
			diQty = BigDecimal.ZERO;
			diAmt = BigDecimal.ZERO;
			miQty = BigDecimal.ZERO;
			miAmt = BigDecimal.ZERO;
			moQty = BigDecimal.ZERO;
			moAmt = BigDecimal.ZERO;
			ilQty = BigDecimal.ZERO;
			ilAmt = BigDecimal.ZERO;
			dsQty = BigDecimal.ZERO;
			dsAmt = BigDecimal.ZERO;
			dcQty = BigDecimal.ZERO;
			dcAmt = BigDecimal.ZERO;
			endQty = BigDecimal.ZERO;
			endAmt = BigDecimal.ZERO;
		}
    }
  	public ScmUseOrgUnitItemSum() {

	}
	public String[] getFieldNames(){
    	return new String[]{
            FN_ID,
            FN_CKECKPERIODID,
            FN_PERIODID,
            FN_ORGUNITNO,
            FN_ITEMID,
            FN_COSTTYPE,
            FN_UNITID,
            FN_STARTQTY,
            FN_STARTAMT,
            FN_WIQTY,
            FN_WIAMT,
            FN_DIQTY,
            FN_DIAMT,
            FN_MIQTY,
            FN_MIAMT,
            FN_MOQTY,
            FN_MOAMT,
            FN_ILQTY,
            FN_ILAMT,
            FN_DSQTY,
            FN_DSAMT,
            FN_DCQTY,
            FN_DCAMT,
            FN_ENDQTY,
            FN_ENDAMT,
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
        map.put(FN_COSTTYPE, 16);
		return map; 
	}
     public String getPkKey() {
		 
		return FN_ID;
	}
 
	public long getPK() {
		 
		return id;
	}


}
