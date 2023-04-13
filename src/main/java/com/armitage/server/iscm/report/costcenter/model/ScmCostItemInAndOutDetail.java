package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

/**
 * 成本中心进出存明细
 * @author Fengxq
 *
 */
public class ScmCostItemInAndOutDetail extends BaseModel {
	private String costOrgUnitNo;
	private String costOrgUnitName;
	private String billNo;
	private String statusName;
	private Date bizDate;
	private Date postDate;
	private String bizTypeName;
	private String useOrgUnitNo;
	private String useOrgUnitName;
	private String unitName;
	private String lot;
	private String vendorName;
	private BigDecimal addInQty;
	private BigDecimal addInAmt;
	private BigDecimal addInTax;
	private BigDecimal addInTaxAmt;
	private BigDecimal outQty;
	private BigDecimal outAmt;
	private BigDecimal outTax;
	private BigDecimal outTaxAmt;
	private BigDecimal stockQty;
	private BigDecimal stockAmt;
	private BigDecimal stockTax;
	private BigDecimal stockTaxAmt;
	
	// 采购入库
	private BigDecimal purInQty;
	private BigDecimal purInAmt;
	private BigDecimal purInTax;
	private BigDecimal purInTaxAmt;
	// 领料入库
	private BigDecimal receiveInQty;
	private BigDecimal receiveInAmt;
	private BigDecimal receiveInTax;
	private BigDecimal receiveInTaxAmt;
	// 成本转入
	private BigDecimal allotInQty;
	private BigDecimal allotInAmt;
	private BigDecimal allotInTax;
	private BigDecimal allotInTaxAmt;
	// 成本转出
	private BigDecimal allotOutQty;
	private BigDecimal allotOutAmt;
	private BigDecimal allotOutTax;
	private BigDecimal allotOutTaxAmt;
	// 耗用
	private BigDecimal conOutQty;
	private BigDecimal conOutAmt;
	private BigDecimal conOutTax;
	private BigDecimal conOutTaxAmt;
	// 盘盈盘亏
	private BigDecimal invenOutQty;
	private BigDecimal invenOutAmt;
	private BigDecimal invenOutTax;
	private BigDecimal invenOutTaxAmt;
	// 报损
	private BigDecimal breakOutQty;
	private BigDecimal breakOutAmt;
	private BigDecimal breakOutTax;
	private BigDecimal breakOutTaxAmt;
	// 销售出库
	private BigDecimal invSaleOutQty;
	private BigDecimal invSaleOutAvgPrice;
	private BigDecimal invSaleOutAmt;
	private BigDecimal invSaleOutTax;
	private BigDecimal invSaleOutTaxAmt;
	private int initflag;
	
	
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

	public String getCostOrgUnitNo() {
		return costOrgUnitNo;
	}

	public void setCostOrgUnitNo(String costOrgUnitNo) {
		this.costOrgUnitNo = costOrgUnitNo;
	}

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
	}

	public String getCostOrgUnitName() {
		return costOrgUnitName;
	}

	public void setCostOrgUnitName(String costOrgUnitName) {
		this.costOrgUnitName = costOrgUnitName;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Date getBizDate() {
		return bizDate;
	}

	public void setBizDate(Date bizDate) {
		this.bizDate = bizDate;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getBizTypeName() {
		return bizTypeName;
	}

	public void setBizTypeName(String bizTypeName) {
		this.bizTypeName = bizTypeName;
	}

	public String getUseOrgUnitName() {
		return useOrgUnitName;
	}

	public void setUseOrgUnitName(String useOrgUnitName) {
		this.useOrgUnitName = useOrgUnitName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public BigDecimal getAddInQty() {
		return addInQty;
	}

	public void setAddInQty(BigDecimal addInQty) {
		this.addInQty = addInQty;
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

	public int getInitflag() {
		return initflag;
	}

	public void setInitflag(int initflag) {
		this.initflag = initflag;
	}

	public ScmCostItemInAndOutDetail() {
		super();
	}

	public ScmCostItemInAndOutDetail(boolean flag) {
		super();
		if(flag) {
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
			this.purInQty = BigDecimal.ZERO;
			this.purInAmt = BigDecimal.ZERO;
			this.purInTax = BigDecimal.ZERO;
			this.purInTaxAmt = BigDecimal.ZERO;
			this.receiveInQty = BigDecimal.ZERO;
			this.receiveInAmt = BigDecimal.ZERO;
			this.receiveInTax = BigDecimal.ZERO;
			this.receiveInTaxAmt = BigDecimal.ZERO;
			this.allotInQty = BigDecimal.ZERO;
			this.allotInAmt = BigDecimal.ZERO;
			this.allotInTax = BigDecimal.ZERO;
			this.allotInTaxAmt = BigDecimal.ZERO;
			this.allotOutQty = BigDecimal.ZERO;
			this.allotOutAmt = BigDecimal.ZERO;
			this.allotOutTax = BigDecimal.ZERO;
			this.allotOutTaxAmt = BigDecimal.ZERO;
			this.conOutQty = BigDecimal.ZERO;
			this.conOutAmt = BigDecimal.ZERO;
			this.conOutTax = BigDecimal.ZERO;
			this.conOutTaxAmt = BigDecimal.ZERO;
			this.invenOutQty = BigDecimal.ZERO;
			this.invenOutAmt = BigDecimal.ZERO;
			this.invenOutTax = BigDecimal.ZERO;
			this.invenOutTaxAmt = BigDecimal.ZERO;
			this.breakOutQty = BigDecimal.ZERO;
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
