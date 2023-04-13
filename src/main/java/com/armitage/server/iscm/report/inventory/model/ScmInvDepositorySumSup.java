package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
/*
 * 寄存出库汇总-供应商
 */
@XmlRootElement(name = "scmInvDepositorySumSup")  
public class ScmInvDepositorySumSup  extends BaseModel {
	private String orgUnitNo;
	private String orgUnitName;
	private long wareHouseId;
	private String wareHouseName;
	private String useOrgUnitNo;
	private String className;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long unit;
	private String unitName;
	private long vendorId;
	private String vendorName;
	private String reqFinOrgUnitNo;
	private String reqFinOrgUnitName;
	private BigDecimal outQty;
	private BigDecimal outAmt;
	private BigDecimal outTaxAmt;
	private BigDecimal outTaxRate;
	
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

	public String getUseOrgUnitNo() {
		return useOrgUnitNo;
	}

	public void setUseOrgUnitNo(String useOrgUnitNo) {
		this.useOrgUnitNo = useOrgUnitNo;
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

	public BigDecimal getOutTaxAmt() {
		return outTaxAmt;
	}

	public void setOutTaxAmt(BigDecimal outTaxAmt) {
		this.outTaxAmt = outTaxAmt;
	}

	public BigDecimal getOutTaxRate() {
		return outTaxRate;
	}

	public void setOutTaxRate(BigDecimal outTaxRate) {
		this.outTaxRate = outTaxRate;
	}

	public String getReqFinOrgUnitNo() {
		return reqFinOrgUnitNo;
	}

	public void setReqFinOrgUnitNo(String reqFinOrgUnitNo) {
		this.reqFinOrgUnitNo = reqFinOrgUnitNo;
	}

	public String getReqFinOrgUnitName() {
		return reqFinOrgUnitName;
	}

	public void setReqFinOrgUnitName(String reqFinOrgUnitName) {
		this.reqFinOrgUnitName = reqFinOrgUnitName;
	}

	public ScmInvDepositorySumSup() {
		super();
	}

	public ScmInvDepositorySumSup(boolean flag) {
		super();
		if(flag) {
			this.outQty = BigDecimal.ZERO;
			this.outAmt = BigDecimal.ZERO;
			this.outTaxAmt = BigDecimal.ZERO;
			this.outTaxRate = BigDecimal.ZERO;
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