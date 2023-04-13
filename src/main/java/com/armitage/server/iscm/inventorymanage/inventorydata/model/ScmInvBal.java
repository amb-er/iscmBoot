package com.armitage.server.iscm.inventorymanage.inventorydata.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;
 

@XmlRootElement(name = "scmInvBal")  
public class ScmInvBal extends BaseModel {
	public static final String FN_ID = "id";
	public static final String FN_PERIODID = "periodId";
	public static final String FN_ACCOUNTYEAR = "accountYear";
	public static final String FN_ACCOUNTPERIOD = "accountPeriod";
	public static final String FN_FINORGUNITNO = "finOrgUnitNo";
	public static final String FN_ORGUNITNO = "orgUnitNo";
	public static final String FN_WAREHOUSEID = "wareHouseId";
	public static final String FN_COSTORGUNITNO = "costOrgUnitNo";
	public static final String FN_COSTCENTER = "costCenter";
	public static final String FN_ITEMID = "itemId";
	public static final String FN_UNIT = "unit";
	public static final String FN_PIEUNIT = "pieUnit";
	public static final String FN_BASEUNIT = "baseUnit";
	public static final String FN_LOT = "lot";
	public static final String FN_STARTQTY = "startQty";
	public static final String FN_STARTBASEQTY = "startBaseQty";
	public static final String FN_STARTPIEQTY = "startPieQty";
	public static final String FN_STARTAMT = "startAmt";
	public static final String FN_STARTTAXAMT = "startTaxAmt";
	public static final String FN_ADDQTY = "addQty";
	public static final String FN_ADDBASEQTY = "addBaseQty";
	public static final String FN_ADDPIEQTY = "addPieQty";
	public static final String FN_ADDAMT = "addAmt";
	public static final String FN_ADDTAXAMT = "addTaxAmt";
	public static final String FN_OUTQTY = "outQty";
	public static final String FN_OUTBASEQTY = "outBaseQty";
	public static final String FN_OUTPIEQTY = "outPieQty";
	public static final String FN_OUTAMT = "outAmt";
	public static final String FN_OUTTAXAMT = "outTaxAmt";
	public static final String FN_ADJQTY = "adjQty";
	public static final String FN_ADJBASEQTY = "adjBaseQty";
	public static final String FN_ADJPIEQTY = "adjPieQty";
	public static final String FN_ADJAMT = "adjAmt";
	public static final String FN_ADJTAXAMT = "adjTaxAmt";
	public static final String FN_ENDQTY = "endQty";
	public static final String FN_ENDBASEQTY = "endBaseQty";
	public static final String FN_ENDPIEQTY = "endPieQty";
	public static final String FN_ENDAMT = "endAmt";
	public static final String FN_ENDTAXAMT = "endTaxAmt";
	
	private long id;
	private long periodId;
	private int accountYear;
	private int accountPeriod;
	private String finOrgUnitNo;
	private String orgUnitNo;
	private long wareHouseId;
	private String costOrgUnitNo;
	private boolean costCenter;
	private long itemId;
	private long unit;
	private long pieUnit;
	private long baseUnit;
	private String lot;
	private BigDecimal startQty;
	private BigDecimal startBaseQty;
	private BigDecimal startPieQty;
	private BigDecimal startAmt;
	private BigDecimal startTaxAmt;
	private BigDecimal addQty;
	private BigDecimal addBaseQty;
	private BigDecimal addPieQty;
	private BigDecimal addAmt;
	private BigDecimal addTaxAmt;
	private BigDecimal outQty;
	private BigDecimal outBaseQty;
	private BigDecimal outPieQty;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal adjQty;
	private BigDecimal adjBaseQty;
	private BigDecimal adjPieQty;
	private BigDecimal adjAmt;
	private BigDecimal adjTaxAmt;
	private BigDecimal endQty;
	private BigDecimal endBaseQty;
	private BigDecimal endPieQty;
	private BigDecimal endAmt;
	private BigDecimal endTaxAmt;

	public long getId() {
		return id;
	}

	public void setId(long val) {
		this.id = val;
	}
	public long getPeriodId() {
		return periodId;
	}

	public void setPeriodId(long val) {
		this.periodId = val;
	}
	public int getAccountYear() {
		return accountYear;
	}

	public void setAccountYear(int val) {
		this.accountYear = val;
	}
	public int getAccountPeriod() {
		return accountPeriod;
	}

	public void setAccountPeriod(int val) {
		this.accountPeriod = val;
	}
	public String getFinOrgUnitNo() {
		return finOrgUnitNo;
	}

	public void setFinOrgUnitNo(String val) {
		this.finOrgUnitNo = val;
	}
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String val) {
		this.orgUnitNo = val;
	}
	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long val) {
		this.wareHouseId = val;
	}
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String val) {
		this.costOrgUnitNo = val;
	}
	
	public boolean isCostCenter() {
		return costCenter;
	}

	public void setCostCenter(boolean costCenter) {
		this.costCenter = costCenter;
	}

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long val) {
		this.itemId = val;
	}
	public long getUnit() {
		return unit;
	}

	public void setUnit(long val) {
		this.unit = val;
	}
	public long getPieUnit() {
		return pieUnit;
	}

	public void setPieUnit(long val) {
		this.pieUnit = val;
	}
	public long getBaseUnit() {
		return baseUnit;
	}

	public void setBaseUnit(long val) {
		this.baseUnit = val;
	}
	public String getLot() {
		return lot;
	}

	public void setLot(String val) {
		this.lot = val;
	}
	public BigDecimal getStartQty() {
		return startQty;
	}

	public void setStartQty(BigDecimal val) {
		this.startQty = val;
	}
	public BigDecimal getStartBaseQty() {
		return startBaseQty;
	}

	public void setStartBaseQty(BigDecimal val) {
		this.startBaseQty = val;
	}
	public BigDecimal getStartPieQty() {
		return startPieQty;
	}

	public void setStartPieQty(BigDecimal val) {
		this.startPieQty = val;
	}
	public BigDecimal getStartAmt() {
		return startAmt;
	}

	public void setStartAmt(BigDecimal val) {
		this.startAmt = val;
	}
	public BigDecimal getStartTaxAmt() {
		return startTaxAmt;
	}

	public void setStartTaxAmt(BigDecimal val) {
		this.startTaxAmt = val;
	}
	public BigDecimal getAddQty() {
		return addQty;
	}

	public void setAddQty(BigDecimal val) {
		this.addQty = val;
	}
	public BigDecimal getAddBaseQty() {
		return addBaseQty;
	}

	public void setAddBaseQty(BigDecimal val) {
		this.addBaseQty = val;
	}
	public BigDecimal getAddPieQty() {
		return addPieQty;
	}

	public void setAddPieQty(BigDecimal val) {
		this.addPieQty = val;
	}
	public BigDecimal getAddAmt() {
		return addAmt;
	}

	public void setAddAmt(BigDecimal val) {
		this.addAmt = val;
	}
	public BigDecimal getAddTaxAmt() {
		return addTaxAmt;
	}

	public void setAddTaxAmt(BigDecimal val) {
		this.addTaxAmt = val;
	}
	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal val) {
		this.outQty = val;
	}
	public BigDecimal getOutBaseQty() {
		return outBaseQty;
	}

	public void setOutBaseQty(BigDecimal val) {
		this.outBaseQty = val;
	}
	public BigDecimal getOutPieQty() {
		return outPieQty;
	}

	public void setOutPieQty(BigDecimal val) {
		this.outPieQty = val;
	}
	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal val) {
		this.outAmt = val;
	}
	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}

	public void setOutTaxAmt(BigDecimal val) {
		this.outTaxAmt = val;
	}
	public BigDecimal getAdjQty() {
		return adjQty;
	}

	public void setAdjQty(BigDecimal val) {
		this.adjQty = val;
	}
	public BigDecimal getAdjBaseQty() {
		return adjBaseQty;
	}

	public void setAdjBaseQty(BigDecimal val) {
		this.adjBaseQty = val;
	}
	public BigDecimal getAdjPieQty() {
		return adjPieQty;
	}

	public void setAdjPieQty(BigDecimal val) {
		this.adjPieQty = val;
	}
	public BigDecimal getAdjAmt() {
		return adjAmt;
	}

	public void setAdjAmt(BigDecimal val) {
		this.adjAmt = val;
	}
	public BigDecimal getAdjTaxAmt() {
		return adjTaxAmt;
	}

	public void setAdjTaxAmt(BigDecimal val) {
		this.adjTaxAmt = val;
	}
	public BigDecimal getEndQty() {
		return endQty;
	}

	public void setEndQty(BigDecimal val) {
		this.endQty = val;
	}
	public BigDecimal getEndBaseQty() {
		return endBaseQty;
	}

	public void setEndBaseQty(BigDecimal val) {
		this.endBaseQty = val;
	}
	public BigDecimal getEndPieQty() {
		return endPieQty;
	}

	public void setEndPieQty(BigDecimal val) {
		this.endPieQty = val;
	}
	public BigDecimal getEndAmt() {
		return endAmt;
	}

	public void setEndAmt(BigDecimal val) {
		this.endAmt = val;
	}
	public BigDecimal getEndTaxAmt() {
		return endTaxAmt;
	}

	public void setEndTaxAmt(BigDecimal val) {
		this.endTaxAmt = val;
	}
	
	public ScmInvBal(boolean defaultValue) {
		if (defaultValue) {
		}
	}
  	public ScmInvBal() {
	}

	public String[] getFieldNames(){
		return new String[]{
			FN_ID,
			FN_PERIODID,
			FN_ACCOUNTYEAR,
			FN_ACCOUNTPERIOD,
			FN_FINORGUNITNO,
			FN_ORGUNITNO,
			FN_WAREHOUSEID,
			FN_COSTORGUNITNO,
			FN_COSTCENTER,
			FN_ITEMID,
			FN_UNIT,
			FN_PIEUNIT,
			FN_BASEUNIT,
			FN_LOT,
			FN_STARTQTY,
			FN_STARTBASEQTY,
			FN_STARTPIEQTY,
			FN_STARTAMT,
			FN_STARTTAXAMT,
			FN_ADDQTY,
			FN_ADDBASEQTY,
			FN_ADDPIEQTY,
			FN_ADDAMT,
			FN_ADDTAXAMT,
			FN_OUTQTY,
			FN_OUTBASEQTY,
			FN_OUTPIEQTY,
			FN_OUTAMT,
			FN_OUTTAXAMT,
			FN_ADJQTY,
			FN_ADJBASEQTY,
			FN_ADJPIEQTY,
			FN_ADJAMT,
			FN_ADJTAXAMT,
			FN_ENDQTY,
			FN_ENDBASEQTY,
			FN_ENDPIEQTY,
			FN_ENDAMT,
			FN_ENDTAXAMT,
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
		map.put(FN_FINORGUNITNO, 32);
		map.put(FN_ORGUNITNO, 32);
		map.put(FN_COSTORGUNITNO, 32);
		map.put(FN_LOT, 32);
		return map; 
	}
	
	public String getPkKey() {
		return FN_ID;
	}
 
	public long getPK() {
		return id;
	}
}
