package com.armitage.server.iscm.report.inventory.model;

import java.math.BigDecimal;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import com.armitage.server.common.base.model.BaseModel;
/*
 * 入库事务汇总表-物资汇总表
 */
@XmlRootElement(name = "scmInvItemWrSumMaterials")  
public class ScmInvItemWrSumMaterials  extends BaseModel {
	private String className;
	private long itemId;
	private String itemNo;
	private String itemName;
	private String spec;
	private long unit;
	private String unitName;
	private BigDecimal addInQty;
	private BigDecimal addInPrice;
	private BigDecimal addInAmt;
	private BigDecimal addInTaxPrice;
	private BigDecimal addInTaxAmt;
	private BigDecimal addInTaxRate;

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

	public ScmInvItemWrSumMaterials() {
		super();
	}

	public ScmInvItemWrSumMaterials(boolean flag) {
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