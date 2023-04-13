package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmNewCostTransferOccurDetail extends BaseModel{
	
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

    
    public ScmNewCostTransferOccurDetail(boolean flag) {
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
        }
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
	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}
	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
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
	public BigDecimal getOutQty() {
		return outQty;
	}
	public void setOutQty(BigDecimal outQty) {
		this.outQty = outQty;
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
