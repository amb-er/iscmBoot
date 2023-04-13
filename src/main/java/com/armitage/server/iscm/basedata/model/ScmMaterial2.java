package com.armitage.server.iscm.basedata.model;

import java.math.BigDecimal;

public class ScmMaterial2 extends ScmMaterial {
	public static final String FN_CLASSCODE = "classCode";
	public static final String FN_CLASSNAME = "className";
	public static final String FN_GROUPID = "groupId";
	public static final String FN_STANDARDId = "standardId";
	public static final String FN_UNITID = "unitId";				//库存计量单位
	public static final String FN_UNIT = "unitNo";				    //库存计量单位编码
	public static final String FN_UNITNAME = "unitName";			//库存计量单位名称
	public static final String FN_PURUNITID = "purUnitId";			//采购资料计量单位Id
	public static final String FN_PURUNIT = "purUnit";				//采购资料计量单位编码
	public static final String FN_PURUNITNAME = "purUnitName";		//采购资料计量单位名称
	public static final String FN_PIEUNITNAME = "pieUnitName";		//件单位名称
	public static final String FN_PURTAXRATE = "purTaxRate";		//采购资料税率
	public static final String FN_PURTAXRATESTR = "purTaxRateStr";	//采购资料税率
	public static final String FN_CSTUNITID = "cstUnitId";				//成本核算计量单位
	public static final String FN_CSTUNITNAME = "cstUnitName";			//成本核算计量单位名称
	public static final String FN_STOCKWAREHOUSEID = "stockWarehouseId";	//stock仓库
	public static final String FN_STOCKORGUNITNO = "stockOrgUnitNo";		//stock组织
	public static final String FN_COSTING = "costing";		//库存方式
	public static final String FN_TYPE = "type";
    public static final String FN_SALETAXRATE ="saleTaxRate";
    public static final String FN_WAREHOUSEID ="wareHouseId";
    public static final String FN_RECENTPRICE ="recentPrice";
    public static final String FN_PRICE ="price";
    public static final String FN_SUMQTY ="sumQty";
    public static final String FN_RECEIVEBOTTOMRATIO="receiveBottomRatio";

    public static final String FN_BUYERID="buyerId";
    public static final String FN_BUYERNAME="buyerName";
    public static final String FN_PURSTATUS="purStatus";
    
    public static final String FN_PRSTATUS="prStatus";
    public static final String FN_POSTATUS="poStatus";
    public static final String FN_POQTY="poQty";
    public static final String FN_PRQTY="prQty";
    public static final String FN_STOCKQTY="stockQty";
    public static final String FN_MAXQTY="maxQty";
	private static final String FN_INVPURQTY="invpurQty";
	private static final String FN_INVMOVEQTY="invmoveQty";
	private static final String FN_INVOTHERQTY="invotherQty";
	private static final String FN_INVSALEOUTQTY="invsaleOutQty";
	private static final String FN_INVMATERIALOUTQTY="invmaterialOutQty";
	private static final String FN_INVMOVEOUTQTY="invmoveOutQty";
	private static final String FN_INVOTHEROUTQTY="invotherOutQty";
	private static final String FN_INVRETURNQTY="invreturnQty";
	private static final String FN_CONVRATE="convrate";
    
    private String classCode;
	private String className;
	private long groupId;
	private long standardId;
	private String baseUnitNo;
	private String baseUnitName;
	private long unitId;
	private String unitNo;
	private String unitName;
	private long purUnitId;
	private String purUnit;
	private String purUnitNo;
	private String purUnitName;
	private String pieUnitName;
	private long cstUnitId;
	private String cstUnitName;
	private BigDecimal purTaxRate;
	private String purStatus; 	//物资采购资料状态
	private String invStatus ;	//物资库存资料状态
	private String purTaxRateStr;
	private long stockWarehouseId;
	private String costOrgUnitNo;
	private String stockOrgUnitNo;
	private String costing;
	private String type;
	private BigDecimal price;	//出库从仓库获取物资时的平均价
	private BigDecimal taxPrice; //出库从仓库获取物资时的平均含税价
	private String invOrgUnitNo;
	private long wareHouseId;
	private String whName;
	private BigDecimal initQty;
	private BigDecimal initPrice;
	private BigDecimal initAmt;
	private BigDecimal inQty;
	private BigDecimal inPrice;
	private BigDecimal inAmt;
	private BigDecimal moveQty;
	private BigDecimal movePrice;
	private BigDecimal moveAmt;
	private BigDecimal outQty;
	private BigDecimal outPrice;
	private BigDecimal outAmt;
	private BigDecimal reqQty;
	private BigDecimal reqPrice;
	private BigDecimal reqAmt;
	private BigDecimal prolossQty;
	private BigDecimal prolossPrice;
	private BigDecimal prolossAmt;
	private BigDecimal badQty;
	private BigDecimal badPrice;
	private BigDecimal badAmt;
	private BigDecimal endQty;
	private BigDecimal endPrice;
	private BigDecimal endAmt;
    private BigDecimal saleTaxRate;
    private BigDecimal receiveTopRatio;	//超收比例
    private String longNo;
    private BigDecimal sumQty;
    private BigDecimal recentPrice;
    private String importItemNo;
    private BigDecimal stockQty;
	private BigDecimal purInWarehsPrice;
	private int purSupplyCycle;
	private String displayUnitName; //显示计量单位名称
	private BigDecimal receiveBottomRatio;//欠收比例
	private String periodValid;
	private int periodValidDays;
	private long scmbaseattachmentId;
	private String scmbaseattachmentName;
	
	private long buyerId;
	private String buyerName;
	
	private BigDecimal prQty;
	private BigDecimal poQty;
	private BigDecimal pvQty;
	private BigDecimal invpurQty;
	private BigDecimal invmoveQty;
	private BigDecimal invotherQty;
	private BigDecimal invsaleOutQty;
	private BigDecimal invmaterialOutQty;
	private BigDecimal invmoveOutQty;
	private BigDecimal invotherOutQty;
	private BigDecimal invreturnQty;
	private BigDecimal maxQty;
	private BigDecimal invpurOutQty;
	private BigDecimal convrate;
	private int lineId;
	
	
	public String getBaseUnitNo() {
		return baseUnitNo;
	}
	public void setBaseUnitNo(String baseUnitNo) {
		this.baseUnitNo = baseUnitNo;
	}
	public String getUnitNo() {
		return unitNo;
	}
	public void setUnitNo(String unitNo) {
		this.unitNo = unitNo;
	}
	public String getPurUnitNo() {
		return purUnitNo;
	}
	public void setPurUnitNo(String purUnitNo) {
		this.purUnitNo = purUnitNo;
	}
	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}
	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}
	public int getLineId() {
		return lineId;
	}
	public void setLineId(int lineId) {
		this.lineId = lineId;
	}
	public BigDecimal getInvpurOutQty() {
		return invpurOutQty;
	}
	public void setInvpurOutQty(BigDecimal invpurOutQty) {
		this.invpurOutQty = invpurOutQty;
	}
	public BigDecimal getPvQty() {
		return pvQty;
	}
	public void setPvQty(BigDecimal pvQty) {
		this.pvQty = pvQty;
	}
	public long getScmbaseattachmentId() {
		return scmbaseattachmentId;
	}
	public void setScmbaseattachmentId(long scmbaseattachmentId) {
		this.scmbaseattachmentId = scmbaseattachmentId;
	}
	public String getScmbaseattachmentName() {
		return scmbaseattachmentName;
	}
	public void setScmbaseattachmentName(String scmbaseattachmentName) {
		this.scmbaseattachmentName = scmbaseattachmentName;
	}
	public String getPeriodValid() {
		return periodValid;
	}
	public void setPeriodValid(String periodValid) {
		this.periodValid = periodValid;
	}
	public int getPeriodValidDays() {
		return periodValidDays;
	}
	public void setPeriodValidDays(int periodValidDays) {
		this.periodValidDays = periodValidDays;
	}
	public BigDecimal getReceiveBottomRatio() {
		return receiveBottomRatio;
	}
	public void setReceiveBottomRatio(BigDecimal receiveBottomRatio) {
		this.receiveBottomRatio = receiveBottomRatio;
	}
	public String getImportItemNo() {
		return importItemNo;
	}
	public void setImportItemNo(String importItemNo) {
		this.importItemNo = importItemNo;
	}
	public BigDecimal getRecentPrice() {
		return recentPrice;
	}
	public void setRecentPrice(BigDecimal recentPrice) {
		this.recentPrice = recentPrice;
	}
	public BigDecimal getSumQty() {
		return sumQty;
	}
	public void setSumQty(BigDecimal sumQty) {
		this.sumQty = sumQty;
	}
	public String getLongNo() {
		return longNo;
	}
	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}
	public long getStockWarehouseId() {
		return stockWarehouseId;
	}
	public void setStockWarehouseId(long stockWarehouseId) {
		this.stockWarehouseId = stockWarehouseId;
	}
	public String getStockOrgUnitNo() {
		return stockOrgUnitNo;
	}
	public void setStockOrgUnitNo(String stockOrgUnitNo) {
		this.stockOrgUnitNo = stockOrgUnitNo;
	}
	public String getPurTaxRateStr() {
		return purTaxRateStr;
	}
	public void setPurTaxRateStr(String purTaxRateStr) {
		this.purTaxRateStr = purTaxRateStr;
	}
	public String getBaseUnitName() {
		return baseUnitName;
	}
	public void setBaseUnitName(String baseUnitName) {
		this.baseUnitName = baseUnitName;
	}
	public long getPurUnitId() {
		return purUnitId;
	}
	public void setPurUnitId(long purUnitId) {
		this.purUnitId = purUnitId;
	}
	public String getPurUnitName() {
		return purUnitName;
	}
	public void setPurUnitName(String purUnitName) {
		this.purUnitName = purUnitName;
	}
	public long getCstUnitId() {
		return cstUnitId;
	}
	public void setCstUnitId(long cstUnitId) {
		this.cstUnitId = cstUnitId;
	}
	public String getCstUnitName() {
		return cstUnitName;
	}
	public void setCstUnitName(String cstUnitName) {
		this.cstUnitName = cstUnitName;
	}
	public BigDecimal getPurTaxRate() {
		return purTaxRate;
	}
	public void setPurTaxRate(BigDecimal purTaxRate) {
		this.purTaxRate = purTaxRate;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	public long getStandardId() {
		return standardId;
	}
	public void setStandardId(long standardId) {
		this.standardId = standardId;
	}
	public long getUnitId() {
		return unitId;
	}
	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public BigDecimal getSaleTaxRate() {
		return saleTaxRate;
	}
	public void setSaleTaxRate(BigDecimal saleTaxRate) {
		this.saleTaxRate = saleTaxRate;
	}
	public String getCosting() {
        return costing;
    }
    public void setCosting(String costing) {
        this.costing = costing;
    }

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPieUnitName() {
		return pieUnitName;
	}
	public void setPieUnitName(String pieUnitName) {
		this.pieUnitName = pieUnitName;
	}
	public String getPurUnit() {
		return purUnit;
	}
	public void setPurUnit(String purUnit) {
		this.purUnit = purUnit;
	}

	public String getInvOrgUnitNo() {
        return invOrgUnitNo;
    }
    public void setInvOrgUnitNo(String invOrgUnitNo) {
        this.invOrgUnitNo = invOrgUnitNo;
    }
    public long getWareHouseId() {
        return wareHouseId;
    }
    public void setWareHouseId(long wareHouseId) {
        this.wareHouseId = wareHouseId;
    }
    public String getWhName() {
        return whName;
    }
    public void setWhName(String whName) {
        this.whName = whName;
    }
    public BigDecimal getInitQty() {
        return initQty;
    }
    public void setInitQty(BigDecimal initQty) {
        this.initQty = initQty;
    }
    public BigDecimal getInitPrice() {
        return initPrice;
    }
    public void setInitPrice(BigDecimal initPrice) {
        this.initPrice = initPrice;
    }
    public BigDecimal getInitAmt() {
        return initAmt;
    }
    public void setInitAmt(BigDecimal initAmt) {
        this.initAmt = initAmt;
    }
    public BigDecimal getInQty() {
        return inQty;
    }
    public void setInQty(BigDecimal inQty) {
        this.inQty = inQty;
    }
    public BigDecimal getInPrice() {
        return inPrice;
    }
    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }
    public BigDecimal getInAmt() {
        return inAmt;
    }
    public void setInAmt(BigDecimal inAmt) {
        this.inAmt = inAmt;
    }
    public BigDecimal getMoveQty() {
        return moveQty;
    }
    public void setMoveQty(BigDecimal moveQty) {
        this.moveQty = moveQty;
    }
    public BigDecimal getMovePrice() {
        return movePrice;
    }
    public void setMovePrice(BigDecimal movePrice) {
        this.movePrice = movePrice;
    }
    public BigDecimal getMoveAmt() {
        return moveAmt;
    }
    public void setMoveAmt(BigDecimal moveAmt) {
        this.moveAmt = moveAmt;
    }
    public BigDecimal getOutQty() {
        return outQty;
    }
    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
    }
    public BigDecimal getOutPrice() {
        return outPrice;
    }
    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }
    public BigDecimal getOutAmt() {
        return outAmt;
    }
    public void setOutAmt(BigDecimal outAmt) {
        this.outAmt = outAmt;
    }
    public BigDecimal getReqQty() {
        return reqQty;
    }
    public void setReqQty(BigDecimal reqQty) {
        this.reqQty = reqQty;
    }
    public BigDecimal getReqPrice() {
        return reqPrice;
    }
    public void setReqPrice(BigDecimal reqPrice) {
        this.reqPrice = reqPrice;
    }
    public BigDecimal getReqAmt() {
        return reqAmt;
    }
    public void setReqAmt(BigDecimal reqAmt) {
        this.reqAmt = reqAmt;
    }
    public BigDecimal getProlossQty() {
        return prolossQty;
    }
    public void setProlossQty(BigDecimal prolossQty) {
        this.prolossQty = prolossQty;
    }
    public BigDecimal getProlossPrice() {
        return prolossPrice;
    }
    public void setProlossPrice(BigDecimal prolossPrice) {
        this.prolossPrice = prolossPrice;
    }
    public BigDecimal getProlossAmt() {
        return prolossAmt;
    }
    public void setProlossAmt(BigDecimal prolossAmt) {
        this.prolossAmt = prolossAmt;
    }
    public BigDecimal getBadQty() {
        return badQty;
    }
    public void setBadQty(BigDecimal badQty) {
        this.badQty = badQty;
    }
    public BigDecimal getBadPrice() {
        return badPrice;
    }
    public void setBadPrice(BigDecimal badPrice) {
        this.badPrice = badPrice;
    }
    public BigDecimal getBadAmt() {
        return badAmt;
    }
    public void setBadAmt(BigDecimal badAmt) {
        this.badAmt = badAmt;
    }
    public BigDecimal getEndQty() {
        return endQty;
    }
    public void setEndQty(BigDecimal endQty) {
        this.endQty = endQty;
    }
    public BigDecimal getEndPrice() {
        return endPrice;
    }
    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }
    public BigDecimal getEndAmt() {
        return endAmt;
    }
    public void setEndAmt(BigDecimal endAmt) {
        this.endAmt = endAmt;
    }
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}
	public BigDecimal getReceiveTopRatio() {
		return receiveTopRatio;
	}
	public void setReceiveTopRatio(BigDecimal receiveTopRatio) {
		this.receiveTopRatio = receiveTopRatio;
	}
	public int getPurSupplyCycle() {
		return purSupplyCycle;
	}
	public void setPurSupplyCycle(int purSupplyCycle) {
		this.purSupplyCycle = purSupplyCycle;
	}
	
	public BigDecimal getStockQty() {
		return stockQty;
	}
	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}
	public BigDecimal getPurInWarehsPrice() {
		return purInWarehsPrice;
	}
	public void setPurInWarehsPrice(BigDecimal purInWarehsPrice) {
		this.purInWarehsPrice = purInWarehsPrice;
	}
	public String getDisplayUnitName() {
		return displayUnitName;
	}
	public void setDisplayUnitName(String displayUnitName) {
		this.displayUnitName = displayUnitName;
	}

	public long getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(long buyerId) {
		this.buyerId = buyerId;
	}
	
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getPurStatus() {
		return purStatus;
	}
	public void setPurStatus(String purStatus) {
		this.purStatus = purStatus;
	}
	public String getInvStatus() {
		return invStatus;
	}
	public void setInvStatus(String invStatus) {
		this.invStatus = invStatus;
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
	public BigDecimal getInvpurQty() {
		return invpurQty;
	}
	public void setInvpurQty(BigDecimal invpurQty) {
		this.invpurQty = invpurQty;
	}
	public BigDecimal getInvmoveQty() {
		return invmoveQty;
	}
	public void setInvmoveQty(BigDecimal invmoveQty) {
		this.invmoveQty = invmoveQty;
	}
	public BigDecimal getInvotherQty() {
		return invotherQty;
	}
	public void setInvotherQty(BigDecimal invotherQty) {
		this.invotherQty = invotherQty;
	}
	public BigDecimal getInvsaleOutQty() {
		return invsaleOutQty;
	}
	public void setInvsaleOutQty(BigDecimal invsaleOutQty) {
		this.invsaleOutQty = invsaleOutQty;
	}
	public BigDecimal getInvmaterialOutQty() {
		return invmaterialOutQty;
	}
	public void setInvmaterialOutQty(BigDecimal invmaterialOutQty) {
		this.invmaterialOutQty = invmaterialOutQty;
	}
	public BigDecimal getInvmoveOutQty() {
		return invmoveOutQty;
	}
	public void setInvmoveOutQty(BigDecimal invmoveOutQty) {
		this.invmoveOutQty = invmoveOutQty;
	}
	public BigDecimal getInvotherOutQty() {
		return invotherOutQty;
	}
	public void setInvotherOutQty(BigDecimal invotherOutQty) {
		this.invotherOutQty = invotherOutQty;
	}
	public BigDecimal getInvreturnQty() {
		return invreturnQty;
	}
	public void setInvreturnQty(BigDecimal invreturnQty) {
		this.invreturnQty = invreturnQty;
	}
	public BigDecimal getMaxQty() {
		return maxQty;
	}
	public void setMaxQty(BigDecimal maxQty) {
		this.maxQty = maxQty;
	}
	public BigDecimal getConvrate() {
		return convrate;
	}
	public void setConvrate(BigDecimal convrate) {
		this.convrate = convrate;
	}
	public ScmMaterial2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.purTaxRate=BigDecimal.ZERO;
			this.purTaxRateStr="0%";
			this.price=BigDecimal.ZERO;
			this.taxPrice=BigDecimal.ZERO;
		}
	}
	
	public ScmMaterial2(){
		super();
	}
}
