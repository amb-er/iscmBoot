package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
/*
 * 物资入库汇总
 */
@XmlRootElement(name = "scmInvItemWrSum")  
public class ScmInvItemWrSum  extends BaseModel {
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
	private BigDecimal addInQty;
	private BigDecimal addInPrice;
	private BigDecimal addInAmt;
	private BigDecimal addInTaxPrice;
	private BigDecimal addInTaxAmt;
	private BigDecimal addInTaxRate;
	
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

	public BigDecimal getAddInQty() {
		return addInQty;
	}

	public void setAddInQty(BigDecimal addInQty) {
		this.addInQty = addInQty;
	}

	public BigDecimal getAddInPrice() {
		return addInPrice;
	}

	public void setAddInPrice(BigDecimal addInPrice) {
		this.addInPrice = addInPrice;
	}

	public BigDecimal getAddInAmt() {
		return addInAmt;
	}

	public void setAddInAmt(BigDecimal addInAmt) {
		this.addInAmt = addInAmt;
	}

	public BigDecimal getAddInTaxPrice() {
		return addInTaxPrice;
	}

	public void setAddInTaxPrice(BigDecimal addInTaxPrice) {
		this.addInTaxPrice = addInTaxPrice;
	}

	public BigDecimal getAddInTaxAmt() {
		return addInTaxAmt;
	}

	public void setAddInTaxAmt(BigDecimal addInTaxAmt) {
		this.addInTaxAmt = addInTaxAmt;
	}

	public BigDecimal getAddInTaxRate() {
		return addInTaxRate;
	}

	public void setAddInTaxRate(BigDecimal addInTaxRate) {
		this.addInTaxRate = addInTaxRate;
	}

	public ScmInvItemWrSum() {
		super();
	}

	public ScmInvItemWrSum(boolean flag) {
		super();
		if(flag) {
			this.addInQty = BigDecimal.ZERO;
			this.addInPrice = BigDecimal.ZERO;
			this.addInAmt = BigDecimal.ZERO;
			this.addInTaxPrice = BigDecimal.ZERO;
			this.addInTaxAmt = BigDecimal.ZERO;
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