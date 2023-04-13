package com.armitage.server.iscm.inventorymanage.inventorysetting.model;

import java.math.BigDecimal;

public class ScmInvSupplyRuleEntry2 extends ScmInvSupplyRuleEntry {
	
	public static final String FN_ITEMNO ="itemNo";
	public static final String FN_ITEMNAME ="itemName";
	public static final String FN_CLASSCODE ="classCode";
	public static final String FN_CLASSNAME ="className";
    public static final String FN_PRSTATUS="prStatus";
    public static final String FN_POSTATUS="poStatus";
    public static final String FN_POQTY="poQty";
    public static final String FN_PRQTY="prQty";
    public static final String FN_STOCKQTY="stockQty";
    public static final String FN_MAXQTY="maxQty";
	
	private String itemNo;
	private String itemName;
    private String classCode;
    private String className;
	private String prStatus;
	private String poStatus;
	private BigDecimal prQty;
	private BigDecimal poQty;
	private BigDecimal stockQty;
	private BigDecimal maxQty;
	private String spec;
	private String purUnitName;
	private long purUnitId;
	private String pieUnitName;
	private long pieUnitId;
	
	public String getPieUnitName() {
		return pieUnitName;
	}

	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}

	public long getPieUnitId() {
		return pieUnitId;
	}

	public void setPieUnitId(long pieUnitId) {
		this.pieUnitId = pieUnitId;
	}

	public long getPurUnitId() {
		return purUnitId;
	}

	public void setPurUnitId(long purUnitId) {
		this.purUnitId = purUnitId;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getPurUnitName() {
		return purUnitName;
	}

	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}

	public BigDecimal getMaxQty() {
		return maxQty;
	}

	public void setMaxQty(BigDecimal maxQty) {
		this.maxQty = maxQty;
	}

	public BigDecimal getStockQty() {
		return stockQty;
	}

	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}

	public String getPrStatus() {
		return prStatus;
	}

	public void setPrStatus(String prStatus) {
		this.prStatus = prStatus;
	}

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public BigDecimal getPrQty() {
		return prQty;
	}

	public void setPrQty(BigDecimal prQty) {
		this.prQty = prQty;
	}

	public BigDecimal getPoQty() {
		return poQty;
	}

	public void setPoQty(BigDecimal poQty) {
		this.poQty = poQty;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
    
    public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
}
