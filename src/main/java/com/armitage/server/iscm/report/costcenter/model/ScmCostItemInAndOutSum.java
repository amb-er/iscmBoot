package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

/**
 * 成本中心进出存汇总
 * 
 * @author Fengxq
 *
 */
public class ScmCostItemInAndOutSum extends BaseModel {
	private String broadClassName;
	private String classCode;
	private String className;
	private String longNo;
	private long itemId;
	private long groupId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long unit;
	private String unitName;
	private String costOrgUnitNo;
	private String costOrgUnitName;
	private BigDecimal initQty;
	private BigDecimal initAvgPrice;
	private BigDecimal initAmt;
	private BigDecimal initTax;
	private BigDecimal initTaxAmt;
	private BigDecimal addInQty;
	private BigDecimal addInAvgPrice;
	private BigDecimal addInAmt;
	private BigDecimal addInTax;
	private BigDecimal addInTaxAmt;
	private BigDecimal outQty;
	private BigDecimal outAvgPrice;
	private BigDecimal outAmt;
	private BigDecimal outTax;
	private BigDecimal outTaxAmt;
	private BigDecimal stockQty;
	private BigDecimal stockAvgPrice;
	private BigDecimal stockAmt;
	private BigDecimal stockTax;
	private BigDecimal stockTaxAmt;
	// 采购入库
	private BigDecimal purInQty;
	private BigDecimal purInAvgPrice;
	private BigDecimal purInAmt;
	private BigDecimal purInTax;
	private BigDecimal purInTaxAmt;
	// 领用入库
	private BigDecimal receiveInQty;
	private BigDecimal receiveInAvgPrice;
	private BigDecimal receiveInAmt;
	private BigDecimal receiveInTax;
	private BigDecimal receiveInTaxAmt;
	// 成本转入
	private BigDecimal allotInQty;
	private BigDecimal allotInAvgPrice;
	private BigDecimal allotInAmt;
	private BigDecimal allotInTax;
	private BigDecimal allotInTaxAmt;
	// 成本转出
	private BigDecimal allotOutQty;
	private BigDecimal allotOutAvgPrice;
	private BigDecimal allotOutAmt;
	private BigDecimal allotOutTax;
	private BigDecimal allotOutTaxAmt;
	// 耗用
	private BigDecimal conOutQty;
	private BigDecimal conOutAvgPrice;
	private BigDecimal conOutAmt;
	private BigDecimal conOutTax;
	private BigDecimal conOutTaxAmt;
	// 盘盈盘亏
	private BigDecimal invenOutQty;
	private BigDecimal invenOutAvgPrice;
	private BigDecimal invenOutAmt;
	private BigDecimal invenOutTax;
	private BigDecimal invenOutTaxAmt;
	// 报损
	private BigDecimal breakOutQty;
	private BigDecimal breakOutAvgPrice;
	private BigDecimal breakOutAmt;
	private BigDecimal breakOutTax;
	private BigDecimal breakOutTaxAmt;
	// 销售出库
	private BigDecimal invSaleOutQty;
	private BigDecimal invSaleOutAvgPrice;
	private BigDecimal invSaleOutAmt;
	private BigDecimal invSaleOutTax;
	private BigDecimal invSaleOutTaxAmt;
	//成本用途
	private String costTypeName;
	//库存组织
	private String orgUnitNo;
	private long wareHouseId;
	
	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public long getGroupId() {
		return groupId;
	}

	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}

	public String getLongNo() {
		return longNo;
	}

	public void setLongNo(String longNo) {
		this.longNo = longNo;
	}

	public BigDecimal getPurInQty() {
		return purInQty;
	}

	public void setPurInQty(BigDecimal purInQty) {
		this.purInQty = purInQty;
	}

	public BigDecimal getPurInAmt() {
		return purInAmt;
	}

	public void setPurInAmt(BigDecimal purInAmt) {
		this.purInAmt = purInAmt;
	}

	public BigDecimal getPurInTax() {
		return purInTax;
	}

	public void setPurInTax(BigDecimal purInTax) {
		this.purInTax = purInTax;
	}

	public BigDecimal getPurInTaxAmt() {
		return purInTaxAmt;
	}

	public void setPurInTaxAmt(BigDecimal purInTaxAmt) {
		this.purInTaxAmt = purInTaxAmt;
	}

	public String getBroadClassName() {
		return broadClassName;
	}

	public void setBroadClassName(String broadClassName) {
		this.broadClassName = broadClassName;
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

	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getItemNo() {
		return itemNo;
	}

	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public long getUnit() {
		return unit;
	}

	public void setUnit(long unit) {
		this.unit = unit;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getCostOrgUnitName() {
		return costOrgUnitName;
	}

	public void setCostOrgUnitName(String costOrgUnitName) {
		this.costOrgUnitName = costOrgUnitName;
	}

	public BigDecimal getInitQty() {
		return initQty;
	}

	public void setInitQty(BigDecimal initQty) {
		this.initQty = initQty;
	}

	public BigDecimal getInitAvgPrice() {
		return initAvgPrice;
	}

	public void setInitAvgPrice(BigDecimal initAvgPrice) {
		this.initAvgPrice = initAvgPrice;
	}

	public BigDecimal getInitAmt() {
		return initAmt;
	}

	public void setInitAmt(BigDecimal initAmt) {
		this.initAmt = initAmt;
	}

	public BigDecimal getInitTax() {
		return initTax;
	}

	public void setInitTax(BigDecimal initTax) {
		this.initTax = initTax;
	}

	public BigDecimal getInitTaxAmt() {
		return initTaxAmt;
	}

	public void setInitTaxAmt(BigDecimal initTaxAmt) {
		this.initTaxAmt = initTaxAmt;
	}

	public BigDecimal getAddInQty() {
		return addInQty;
	}

	public void setAddInQty(BigDecimal addInQty) {
		this.addInQty = addInQty;
	}

	public BigDecimal getAddInAvgPrice() {
		return addInAvgPrice;
	}

	public void setAddInAvgPrice(BigDecimal addInAvgPrice) {
		this.addInAvgPrice = addInAvgPrice;
	}

	public BigDecimal getAddInAmt() {
		return addInAmt;
	}

	public void setAddInAmt(BigDecimal addInAmt) {
		this.addInAmt = addInAmt;
	}

	public BigDecimal getAddInTax() {
		return addInTax;
	}

	public void setAddInTax(BigDecimal addInTax) {
		this.addInTax = addInTax;
	}

	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}

	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}

	public BigDecimal getOutQty() {
		return outQty;
	}

	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
	}

	public BigDecimal getOutAvgPrice() {
		return outAvgPrice;
	}

	public void setOutAvgPrice(BigDecimal outAvgPrice) {
		this.outAvgPrice = outAvgPrice;
	}

	public BigDecimal getOutAmt() {
		return outAmt;
	}

	public void setOutAmt(BigDecimal outAmt) {
		this.outAmt = outAmt;
	}

	public BigDecimal getOutTax() {
		return outTax;
	}

	public void setOutTax(BigDecimal outTax) {
		this.outTax = outTax;
	}

	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}

	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}

	public BigDecimal getStockQty() {
		return stockQty;
	}

	public void setStockQty(BigDecimal stockQty) {
		this.stockQty = stockQty;
	}

	public BigDecimal getStockAvgPrice() {
		return stockAvgPrice;
	}

	public void setStockAvgPrice(BigDecimal stockAvgPrice) {
		this.stockAvgPrice = stockAvgPrice;
	}

	public BigDecimal getStockAmt() {
		return stockAmt;
	}

	public void setStockAmt(BigDecimal stockAmt) {
		this.stockAmt = stockAmt;
	}

	public BigDecimal getStockTax() {
		return stockTax;
	}

	public void setStockTax(BigDecimal stockTax) {
		this.stockTax = stockTax;
	}

	public BigDecimal getStockTaxAmt() {
		return stockTaxAmt;
	}

	public void setStockTaxAmt(BigDecimal stockTaxAmt) {
		this.stockTaxAmt = stockTaxAmt;
	}

	public ScmCostItemInAndOutSum() {
		super();
	}

	public ScmCostItemInAndOutSum(boolean flag) {
		super();
		if (flag) {
			this.initQty = BigDecimal.ZERO;
			this.initAmt = BigDecimal.ZERO;
			this.initTax = BigDecimal.ZERO;
			this.initTaxAmt = BigDecimal.ZERO;
			this.addInQty = BigDecimal.ZERO;
			this.addInAmt = BigDecimal.ZERO;
			this.addInTax = BigDecimal.ZERO;
			this.addInTaxAmt = BigDecimal.ZERO;
			this.outQty = BigDecimal.ZERO;
			this.outAmt = BigDecimal.ZERO;
			this.outTax = BigDecimal.ZERO;
			this.outTaxAmt = BigDecimal.ZERO;
			this.stockQty = BigDecimal.ZERO;
			this.stockAmt = BigDecimal.ZERO;
			this.stockTax = BigDecimal.ZERO;
			this.stockTaxAmt = BigDecimal.ZERO;
			this.initAvgPrice = BigDecimal.ZERO;
			this.addInAvgPrice = BigDecimal.ZERO;
			this.outAvgPrice = BigDecimal.ZERO;
			this.stockAvgPrice = BigDecimal.ZERO;
			this.purInQty = BigDecimal.ZERO;
			this.purInAvgPrice = BigDecimal.ZERO;
			this.purInAmt = BigDecimal.ZERO;
			this.purInTax = BigDecimal.ZERO;
			this.purInTaxAmt = BigDecimal.ZERO;
			this.receiveInQty = BigDecimal.ZERO;
			this.receiveInAvgPrice = BigDecimal.ZERO;
			this.receiveInAmt = BigDecimal.ZERO;
			this.receiveInTax = BigDecimal.ZERO;
			this.receiveInTaxAmt = BigDecimal.ZERO;
			this.allotInQty = BigDecimal.ZERO;
			this.allotInAvgPrice = BigDecimal.ZERO;
			this.allotInAmt = BigDecimal.ZERO;
			this.allotInTax = BigDecimal.ZERO;
			this.allotInTaxAmt = BigDecimal.ZERO;
			this.allotOutQty = BigDecimal.ZERO;
			this.allotOutAvgPrice = BigDecimal.ZERO;
			this.allotOutAmt = BigDecimal.ZERO;
			this.allotOutTax = BigDecimal.ZERO;
			this.allotOutTaxAmt = BigDecimal.ZERO;
			this.conOutQty = BigDecimal.ZERO;
			this.conOutAvgPrice = BigDecimal.ZERO;
			this.conOutAmt = BigDecimal.ZERO;
			this.conOutTax = BigDecimal.ZERO;
			this.conOutTaxAmt = BigDecimal.ZERO;
			this.invenOutQty = BigDecimal.ZERO;
			this.invenOutAvgPrice = BigDecimal.ZERO;
			this.invenOutAmt = BigDecimal.ZERO;
			this.invenOutTax = BigDecimal.ZERO;
			this.invenOutTaxAmt = BigDecimal.ZERO;
			this.breakOutQty = BigDecimal.ZERO;
			this.breakOutAvgPrice = BigDecimal.ZERO;
			this.breakOutAmt = BigDecimal.ZERO;
			this.breakOutTax = BigDecimal.ZERO;
			this.breakOutTaxAmt = BigDecimal.ZERO;
			this.invSaleOutQty = BigDecimal.ZERO;
			this.invSaleOutAvgPrice = BigDecimal.ZERO;
			this.invSaleOutAmt = BigDecimal.ZERO;
			this.invSaleOutTax = BigDecimal.ZERO;
			this.invSaleOutTaxAmt = BigDecimal.ZERO;
		}
	}

	@Override
	public String getPkKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPK() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getRequiredFields() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFieldNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String[]> getUniqueKeys() {
		// TODO Auto-generated method stub
		return null;
	}

	public BigDecimal getReceiveInQty() {
		return receiveInQty;
	}

	public void setReceiveInQty(BigDecimal receiveInQty) {
		this.receiveInQty = receiveInQty;
	}

	public BigDecimal getReceiveInAmt() {
		return receiveInAmt;
	}

	public void setReceiveInAmt(BigDecimal receiveInAmt) {
		this.receiveInAmt = receiveInAmt;
	}

	public BigDecimal getReceiveInTax() {
		return receiveInTax;
	}

	public void setReceiveInTax(BigDecimal receiveInTax) {
		this.receiveInTax = receiveInTax;
	}

	public void Receive(BigDecimal receiveInTax) {
		this.receiveInTax = receiveInTax;
	}

	public BigDecimal getReceiveInTaxAmt() {
		return receiveInTaxAmt;
	}

	public void setReceiveInTaxAmt(BigDecimal receiveInTaxAmt) {
		this.receiveInTaxAmt = receiveInTaxAmt;
	}

	public BigDecimal getAllotInQty() {
		return allotInQty;
	}

	public void setAllotInQty(BigDecimal allotInQty) {
		this.allotInQty = allotInQty;
	}

	public BigDecimal getAllotInAmt() {
		return allotInAmt;
	}

	public void setAllotInAmt(BigDecimal allotInAmt) {
		this.allotInAmt = allotInAmt;
	}

	public BigDecimal getAllotInTax() {
		return allotInTax;
	}

	public void setAllotInTax(BigDecimal allotInTax) {
		this.allotInTax = allotInTax;
	}

	public BigDecimal getAllotInTaxAmt() {
		return allotInTaxAmt;
	}

	public void setAllotInTaxAmt(BigDecimal allotInTaxAmt) {
		this.allotInTaxAmt = allotInTaxAmt;
	}

	public BigDecimal getAllotOutQty() {
		return allotOutQty;
	}

	public void setAllotOutQty(BigDecimal allotOutQty) {
		this.allotOutQty = allotOutQty;
	}

	public BigDecimal getAllotOutAmt() {
		return allotOutAmt;
	}

	public void setAllotOutAmt(BigDecimal allotOutAmt) {
		this.allotOutAmt = allotOutAmt;
	}

	public BigDecimal getAllotOutTax() {
		return allotOutTax;
	}

	public void setAllotOutTax(BigDecimal allotOutTax) {
		this.allotOutTax = allotOutTax;
	}

	public BigDecimal getAllotOutTaxAmt() {
		return allotOutTaxAmt;
	}

	public void setAllotOutTaxAmt(BigDecimal allotOutTaxAmt) {
		this.allotOutTaxAmt = allotOutTaxAmt;
	}

	public BigDecimal getConOutQty() {
		return conOutQty;
	}

	public void setConOutQty(BigDecimal conOutQty) {
		this.conOutQty = conOutQty;
	}

	public BigDecimal getConOutAmt() {
		return conOutAmt;
	}

	public void setConOutAmt(BigDecimal conOutAmt) {
		this.conOutAmt = conOutAmt;
	}

	public BigDecimal getConOutTax() {
		return conOutTax;
	}

	public void setConOutTax(BigDecimal conOutTax) {
		this.conOutTax = conOutTax;
	}

	public BigDecimal getConOutTaxAmt() {
		return conOutTaxAmt;
	}

	public void setConOutTaxAmt(BigDecimal conOutTaxAmt) {
		this.conOutTaxAmt = conOutTaxAmt;
	}

	public BigDecimal getInvenOutQty() {
		return invenOutQty;
	}

	public void setInvenOutQty(BigDecimal invenOutQty) {
		this.invenOutQty = invenOutQty;
	}

	public BigDecimal getInvenOutAmt() {
		return invenOutAmt;
	}

	public void setInvenOutAmt(BigDecimal invenOutAmt) {
		this.invenOutAmt = invenOutAmt;
	}

	public BigDecimal getInvenOutTax() {
		return invenOutTax;
	}

	public void setInvenOutTax(BigDecimal invenOutTax) {
		this.invenOutTax = invenOutTax;
	}

	public BigDecimal getInvenOutTaxAmt() {
		return invenOutTaxAmt;
	}

	public void setInvenOutTaxAmt(BigDecimal invenOutTaxAmt) {
		this.invenOutTaxAmt = invenOutTaxAmt;
	}

	public BigDecimal getBreakOutQty() {
		return breakOutQty;
	}

	public void setBreakOutQty(BigDecimal breakOutQty) {
		this.breakOutQty = breakOutQty;
	}

	public BigDecimal getBreakOutAmt() {
		return breakOutAmt;
	}

	public void setBreakOutAmt(BigDecimal breakOutAmt) {
		this.breakOutAmt = breakOutAmt;
	}

	public BigDecimal getBreakOutTax() {
		return breakOutTax;
	}

	public void setBreakOutTax(BigDecimal breakOutTax) {
		this.breakOutTax = breakOutTax;
	}

	public BigDecimal getBreakOutTaxAmt() {
		return breakOutTaxAmt;
	}

	public void setBreakOutTaxAmt(BigDecimal breakOutTaxAmt) {
		this.breakOutTaxAmt = breakOutTaxAmt;
	}

	public BigDecimal getPurInAvgPrice() {
		return purInAvgPrice;
	}

	public void setPurInAvgPrice(BigDecimal purInAvgPrice) {
		this.purInAvgPrice = purInAvgPrice;
	}

	public BigDecimal getReceiveInAvgPrice() {
		return receiveInAvgPrice;
	}

	public void setReceiveInAvgPrice(BigDecimal receiveInAvgPrice) {
		this.receiveInAvgPrice = receiveInAvgPrice;
	}

	public BigDecimal getInvSaleOutQty() {
		return invSaleOutQty;
	}

	public void setInvSaleOutQty(BigDecimal invSaleOutQty) {
		this.invSaleOutQty = invSaleOutQty;
	}

	public BigDecimal getInvSaleOutAvgPrice() {
		return invSaleOutAvgPrice;
	}

	public void setInvSaleOutAvgPrice(BigDecimal invSaleOutAvgPrice) {
		this.invSaleOutAvgPrice = invSaleOutAvgPrice;
	}

	public BigDecimal getInvSaleOutAmt() {
		return invSaleOutAmt;
	}

	public void setInvSaleOutAmt(BigDecimal invSaleOutAmt) {
		this.invSaleOutAmt = invSaleOutAmt;
	}

	public BigDecimal getInvSaleOutTax() {
		return invSaleOutTax;
	}

	public void setInvSaleOutTax(BigDecimal invSaleOutTax) {
		this.invSaleOutTax = invSaleOutTax;
	}

	public BigDecimal getInvSaleOutTaxAmt() {
		return invSaleOutTaxAmt;
	}

	public void setInvSaleOutTaxAmt(BigDecimal invSaleOutTaxAmt) {
		this.invSaleOutTaxAmt = invSaleOutTaxAmt;
	}

	public BigDecimal getAllotInAvgPrice() {
		return allotInAvgPrice;
	}

	public void setAllotInAvgPrice(BigDecimal allotInAvgPrice) {
		this.allotInAvgPrice = allotInAvgPrice;
	}

	public BigDecimal getAllotOutAvgPrice() {
		return allotOutAvgPrice;
	}

	public void setAllotOutAvgPrice(BigDecimal allotOutAvgPrice) {
		this.allotOutAvgPrice = allotOutAvgPrice;
	}

	public BigDecimal getConOutAvgPrice() {
		return conOutAvgPrice;
	}

	public void setConOutAvgPrice(BigDecimal conOutAvgPrice) {
		this.conOutAvgPrice = conOutAvgPrice;
	}

	public BigDecimal getInvenOutAvgPrice() {
		return invenOutAvgPrice;
	}

	public void setInvenOutAvgPrice(BigDecimal invenOutAvgPrice) {
		this.invenOutAvgPrice = invenOutAvgPrice;
	}

	public BigDecimal getBreakOutAvgPrice() {
		return breakOutAvgPrice;
	}

	public void setBreakOutAvgPrice(BigDecimal breakOutAvgPrice) {
		this.breakOutAvgPrice = breakOutAvgPrice;
	}
	

	public String getCostTypeName() {
		return costTypeName;
	}

	public void setCostTypeName(String costTypeName) {
		this.costTypeName = costTypeName;
	}

	@Override
	public Map<String, RelationModel> getForeignMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<RelationModel>> getReferenceMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
