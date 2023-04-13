package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
/*
 * 物资进出存明细
 */
@XmlRootElement(name = "scmInvItemInAndOutDetail")  
public class ScmInvItemInAndOutDetail  extends BaseModel {
	private String orgUnitNo;
	private String orgUnitName;
	private String billNo;
	private String statusName;
	private Date bizDate;
	private Date postDate;
	private String bizTypeName;
	private long wareHouseId;
	private String wareHouseName;
	private long unit;
	private String unitName;
	private String lot;
	private long vendorId;
	private String vendorName;
	private BigDecimal addInQty;
	private BigDecimal addInAmt;
	private BigDecimal addInTax;
	private BigDecimal addInTaxAmt;
	private BigDecimal moveQty;
	private BigDecimal moveAmt;
	private BigDecimal moveTax;
	private BigDecimal moveTaxAmt;
	private BigDecimal outQty;
	private BigDecimal outAmt;
	private BigDecimal outTax;
	private BigDecimal outTaxAmt;
	private BigDecimal reqQty;
	private BigDecimal reqAmt;
	private BigDecimal reqTax;
	private BigDecimal reqTaxAmt;
	private BigDecimal profitQty;
	private BigDecimal profitAmt;
	private BigDecimal profitTax;
	private BigDecimal profitTaxAmt;
	private BigDecimal scrapQty;
	private BigDecimal scrapAmt;
	private BigDecimal scrapTax;
	private BigDecimal scrapTaxAmt;
	private BigDecimal stockQty;
	private BigDecimal stockAmt;
	private BigDecimal stockTax;
	private BigDecimal stockTaxAmt;
	private int initflag;

	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getOrgUnitName() {
		return orgUnitName;
	}

	public void setOrgUnitName(String orgUnitName) {
		this.orgUnitName = orgUnitName;
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

	public long getWareHouseId() {
		return wareHouseId;
	}

	public void setWareHouseId(long wareHouseId) {
		this.wareHouseId = wareHouseId;
	}

	public String getWareHouseName() {
		return wareHouseName;
	}

	public void setWareHouseName(String wareHouseName) {
		this.wareHouseName = wareHouseName;
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

	public String getLot() {
		return lot;
	}

	public void setLot(String lot) {
		this.lot = lot;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
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

	public BigDecimal getMoveQty() {
		return moveQty;
	}

	public void setMoveQty(BigDecimal moveQty) {
		this.moveQty = moveQty;
	}

	public BigDecimal getMoveAmt() {
		return moveAmt;
	}

	public void setMoveAmt(BigDecimal moveAmt) {
		this.moveAmt = moveAmt;
	}

	public BigDecimal getMoveTax() {
		return moveTax;
	}

	public void setMoveTax(BigDecimal moveTax) {
		this.moveTax = moveTax;
	}

	public BigDecimal getMoveTaxAmt() {
		return moveTaxAmt;
	}

	public void setMoveTaxAmt(BigDecimal moveTaxAmt) {
		this.moveTaxAmt = moveTaxAmt;
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

	public BigDecimal getReqQty() {
		return reqQty;
	}

	public void setReqQty(BigDecimal reqQty) {
		this.reqQty = reqQty;
	}

	public BigDecimal getReqAmt() {
		return reqAmt;
	}

	public void setReqAmt(BigDecimal reqAmt) {
		this.reqAmt = reqAmt;
	}

	public BigDecimal getReqTax() {
		return reqTax;
	}

	public void setReqTax(BigDecimal reqTax) {
		this.reqTax = reqTax;
	}

	public BigDecimal getReqTaxAmt() {
		return reqTaxAmt;
	}

	public void setReqTaxAmt(BigDecimal reqTaxAmt) {
		this.reqTaxAmt = reqTaxAmt;
	}

	public BigDecimal getProfitQty() {
		return profitQty;
	}

	public void setProfitQty(BigDecimal profitQty) {
		this.profitQty = profitQty;
	}

	public BigDecimal getProfitAmt() {
		return profitAmt;
	}

	public void setProfitAmt(BigDecimal profitAmt) {
		this.profitAmt = profitAmt;
	}

	public BigDecimal getProfitTax() {
		return profitTax;
	}

	public void setProfitTax(BigDecimal profitTax) {
		this.profitTax = profitTax;
	}

	public BigDecimal getProfitTaxAmt() {
		return profitTaxAmt;
	}

	public void setProfitTaxAmt(BigDecimal profitTaxAmt) {
		this.profitTaxAmt = profitTaxAmt;
	}

	public BigDecimal getScrapQty() {
		return scrapQty;
	}

	public void setScrapQty(BigDecimal scrapQty) {
		this.scrapQty = scrapQty;
	}

	public BigDecimal getScrapAmt() {
		return scrapAmt;
	}

	public void setScrapAmt(BigDecimal scrapAmt) {
		this.scrapAmt = scrapAmt;
	}

	public BigDecimal getScrapTax() {
		return scrapTax;
	}

	public void setScrapTax(BigDecimal scrapTax) {
		this.scrapTax = scrapTax;
	}

	public BigDecimal getScrapTaxAmt() {
		return scrapTaxAmt;
	}

	public void setScrapTaxAmt(BigDecimal scrapTaxAmt) {
		this.scrapTaxAmt = scrapTaxAmt;
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

	public ScmInvItemInAndOutDetail() {
		super();
	}

	public ScmInvItemInAndOutDetail(boolean flag) {
		super();
		if(flag) {
			this.addInQty = BigDecimal.ZERO;
			this.addInAmt = BigDecimal.ZERO;
			this.addInTax = BigDecimal.ZERO;
			this.addInTaxAmt = BigDecimal.ZERO;
			this.moveQty = BigDecimal.ZERO;
			this.moveAmt = BigDecimal.ZERO;
			this.moveTax = BigDecimal.ZERO;
			this.moveTaxAmt = BigDecimal.ZERO;
			this.reqQty = BigDecimal.ZERO;
			this.reqAmt = BigDecimal.ZERO;
			this.reqTax = BigDecimal.ZERO;
			this.reqTaxAmt = BigDecimal.ZERO;
			this.profitQty = BigDecimal.ZERO;
			this.profitAmt = BigDecimal.ZERO;
			this.profitTax = BigDecimal.ZERO;
			this.profitTaxAmt = BigDecimal.ZERO;
			this.scrapQty = BigDecimal.ZERO;
			this.scrapAmt = BigDecimal.ZERO;
			this.scrapTax = BigDecimal.ZERO;
			this.scrapTaxAmt = BigDecimal.ZERO;
			this.outQty = BigDecimal.ZERO;
			this.outAmt = BigDecimal.ZERO;
			this.outTax = BigDecimal.ZERO;
			this.outTaxAmt = BigDecimal.ZERO;
			this.stockQty = BigDecimal.ZERO;
			this.stockAmt = BigDecimal.ZERO;
			this.stockTax = BigDecimal.ZERO;
			this.stockTaxAmt = BigDecimal.ZERO;
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
}