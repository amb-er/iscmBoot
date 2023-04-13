package com.armitage.server.iscm.report.costcenter.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.RelationModel;

/**
 * 成本中心事务发生汇总表（含税）
 * @author Sujy
 *
 */
public class ScmCostTransferOccurSum extends BaseModel {
    private String classCode;
    private String className;
    private long itemId;
    private String itemNo;
    private String itemName;
    private String spec;
    private long unit;
    private String unitName;
    private String costOrgUnitNo;
    private String costOrgUnitName;
    private int orderNo;
    private String bizType;
    private BigDecimal qty;
    private BigDecimal amt;
    private BigDecimal tax;
    private BigDecimal taxAmt;
    private BigDecimal qty_1;
    private BigDecimal amt_1;
    private BigDecimal tax_1;
    private BigDecimal taxAmt_1;
    private BigDecimal qty_2;
    private BigDecimal amt_2;
    private BigDecimal tax_2;
    private BigDecimal taxAmt_2;
    private BigDecimal qty_3;
    private BigDecimal amt_3;
    private BigDecimal tax_3;
    private BigDecimal taxAmt_3;
    private BigDecimal qty_4;
    private BigDecimal amt_4;
    private BigDecimal tax_4;
    private BigDecimal taxAmt_4;
    private BigDecimal qty_5;
    private BigDecimal amt_5;
    private BigDecimal tax_5;
    private BigDecimal taxAmt_5;
    private BigDecimal qty_6;
    private BigDecimal amt_6;
    private BigDecimal tax_6;
    private BigDecimal taxAmt_6;
    private BigDecimal qty_7;
    private BigDecimal amt_7;
    private BigDecimal tax_7;
    private BigDecimal taxAmt_7;
    private BigDecimal qty_8;
    private BigDecimal amt_8;
    private BigDecimal tax_8;
    private BigDecimal taxAmt_8;
    private BigDecimal qty_9;
    private BigDecimal amt_9;
    private BigDecimal tax_9;
    private BigDecimal taxAmt_9;

    private List<String> intervaltype;
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

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getQty_1() {
        return qty_1;
    }

    public void setQty_1(BigDecimal qty_1) {
        this.qty_1 = qty_1;
    }

    public BigDecimal getAmt_1() {
        return amt_1;
    }

    public void setAmt_1(BigDecimal amt_1) {
        this.amt_1 = amt_1;
    }

    public BigDecimal getTaxAmt_1() {
        return taxAmt_1;
    }

    public void setTaxAmt_1(BigDecimal taxAmt_1) {
        this.taxAmt_1 = taxAmt_1;
    }

    public BigDecimal getQty_2() {
        return qty_2;
    }

    public void setQty_2(BigDecimal qty_2) {
        this.qty_2 = qty_2;
    }

    public BigDecimal getAmt_2() {
        return amt_2;
    }

    public void setAmt_2(BigDecimal amt_2) {
        this.amt_2 = amt_2;
    }

    public BigDecimal getTaxAmt_2() {
        return taxAmt_2;
    }

    public void setTaxAmt_2(BigDecimal taxAmt_2) {
        this.taxAmt_2 = taxAmt_2;
    }

    public BigDecimal getQty_3() {
        return qty_3;
    }

    public void setQty_3(BigDecimal qty_3) {
        this.qty_3 = qty_3;
    }

    public BigDecimal getAmt_3() {
        return amt_3;
    }

    public void setAmt_3(BigDecimal amt_3) {
        this.amt_3 = amt_3;
    }

    public BigDecimal getTaxAmt_3() {
        return taxAmt_3;
    }

    public void setTaxAmt_3(BigDecimal taxAmt_3) {
        this.taxAmt_3 = taxAmt_3;
    }

    public BigDecimal getQty_4() {
        return qty_4;
    }

    public void setQty_4(BigDecimal qty_4) {
        this.qty_4 = qty_4;
    }

    public BigDecimal getAmt_4() {
        return amt_4;
    }

    public void setAmt_4(BigDecimal amt_4) {
        this.amt_4 = amt_4;
    }

    public BigDecimal getTaxAmt_4() {
        return taxAmt_4;
    }

    public void setTaxAmt_4(BigDecimal taxAmt_4) {
        this.taxAmt_4 = taxAmt_4;
    }

    public BigDecimal getQty_5() {
        return qty_5;
    }

    public void setQty_5(BigDecimal qty_5) {
        this.qty_5 = qty_5;
    }

    public BigDecimal getAmt_5() {
        return amt_5;
    }

    public void setAmt_5(BigDecimal amt_5) {
        this.amt_5 = amt_5;
    }

    public BigDecimal getTaxAmt_5() {
        return taxAmt_5;
    }

    public void setTaxAmt_5(BigDecimal taxAmt_5) {
        this.taxAmt_5 = taxAmt_5;
    }

    public BigDecimal getQty_6() {
        return qty_6;
    }

    public void setQty_6(BigDecimal qty_6) {
        this.qty_6 = qty_6;
    }

    public BigDecimal getAmt_6() {
        return amt_6;
    }

    public void setAmt_6(BigDecimal amt_6) {
        this.amt_6 = amt_6;
    }

    public BigDecimal getTaxAmt_6() {
        return taxAmt_6;
    }

    public void setTaxAmt_6(BigDecimal taxAmt_6) {
        this.taxAmt_6 = taxAmt_6;
    }

    public BigDecimal getQty_7() {
        return qty_7;
    }

    public void setQty_7(BigDecimal qty_7) {
        this.qty_7 = qty_7;
    }

    public BigDecimal getAmt_7() {
        return amt_7;
    }

    public void setAmt_7(BigDecimal amt_7) {
        this.amt_7 = amt_7;
    }

    public BigDecimal getTaxAmt_7() {
        return taxAmt_7;
    }

    public void setTaxAmt_7(BigDecimal taxAmt_7) {
        this.taxAmt_7 = taxAmt_7;
    }

    public BigDecimal getQty_8() {
        return qty_8;
    }

    public void setQty_8(BigDecimal qty_8) {
        this.qty_8 = qty_8;
    }

    public BigDecimal getAmt_8() {
        return amt_8;
    }

    public void setAmt_8(BigDecimal amt_8) {
        this.amt_8 = amt_8;
    }

    public BigDecimal getTaxAmt_8() {
        return taxAmt_8;
    }

    public void setTaxAmt_8(BigDecimal taxAmt_8) {
        this.taxAmt_8 = taxAmt_8;
    }
    public List<String> getIntervaltype() {
        return intervaltype;
    }

    public void setIntervaltype(List<String> intervaltype) {
        this.intervaltype = intervaltype;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTax_1() {
        return tax_1;
    }

    public void setTax_1(BigDecimal tax_1) {
        this.tax_1 = tax_1;
    }

    public BigDecimal getTax_2() {
        return tax_2;
    }

    public void setTax_2(BigDecimal tax_2) {
        this.tax_2 = tax_2;
    }

    public BigDecimal getTax_3() {
        return tax_3;
    }

    public void setTax_3(BigDecimal tax_3) {
        this.tax_3 = tax_3;
    }

    public BigDecimal getTax_4() {
        return tax_4;
    }

    public void setTax_4(BigDecimal tax_4) {
        this.tax_4 = tax_4;
    }

    public BigDecimal getTax_5() {
        return tax_5;
    }

    public void setTax_5(BigDecimal tax_5) {
        this.tax_5 = tax_5;
    }

    public BigDecimal getTax_6() {
        return tax_6;
    }

    public void setTax_6(BigDecimal tax_6) {
        this.tax_6 = tax_6;
    }

    public BigDecimal getTax_7() {
        return tax_7;
    }

    public void setTax_7(BigDecimal tax_7) {
        this.tax_7 = tax_7;
    }

    public BigDecimal getTax_8() {
        return tax_8;
    }

    public void setTax_8(BigDecimal tax_8) {
        this.tax_8 = tax_8;
    }

    public BigDecimal getQty_9() {
		return qty_9;
	}

	public void setQty_9(BigDecimal qty_9) {
		this.qty_9 = qty_9;
	}

	public BigDecimal getAmt_9() {
		return amt_9;
	}

	public void setAmt_9(BigDecimal amt_9) {
		this.amt_9 = amt_9;
	}

	public BigDecimal getTax_9() {
		return tax_9;
	}

	public void setTax_9(BigDecimal tax_9) {
		this.tax_9 = tax_9;
	}

	public BigDecimal getTaxAmt_9() {
		return taxAmt_9;
	}

	public void setTaxAmt_9(BigDecimal taxAmt_9) {
		this.taxAmt_9 = taxAmt_9;
	}

	public ScmCostTransferOccurSum() {
        super();
    }

    public ScmCostTransferOccurSum(boolean flag) {
        super();
        if(flag) {
            this.qty = BigDecimal.ZERO;
            this.amt = BigDecimal.ZERO;
            this.tax = BigDecimal.ZERO;
            this.taxAmt = BigDecimal.ZERO;
            this.qty_1 = BigDecimal.ZERO;
            this.amt_1 = BigDecimal.ZERO;
            this.tax_1 = BigDecimal.ZERO;
            this.taxAmt_1 = BigDecimal.ZERO;
            this.qty_2 = BigDecimal.ZERO;
            this.amt_2 = BigDecimal.ZERO;
            this.tax_2 = BigDecimal.ZERO;
            this.taxAmt_2 = BigDecimal.ZERO;
            this.qty_3 = BigDecimal.ZERO;
            this.amt_3 = BigDecimal.ZERO;
            this.tax_3 = BigDecimal.ZERO;
            this.taxAmt_3 = BigDecimal.ZERO;
            this.qty_4 = BigDecimal.ZERO;
            this.amt_4 = BigDecimal.ZERO;
            this.tax_4 = BigDecimal.ZERO;
            this.taxAmt_4 = BigDecimal.ZERO;
            this.qty_5 = BigDecimal.ZERO;
            this.amt_5 = BigDecimal.ZERO;
            this.tax_5 = BigDecimal.ZERO;
            this.taxAmt_5 = BigDecimal.ZERO;
            this.qty_6 = BigDecimal.ZERO;
            this.amt_6 = BigDecimal.ZERO;
            this.tax_6 = BigDecimal.ZERO;
            this.taxAmt_6 = BigDecimal.ZERO;
            this.qty_7 = BigDecimal.ZERO;
            this.amt_7 = BigDecimal.ZERO;
            this.tax_7 = BigDecimal.ZERO;
            this.taxAmt_7 = BigDecimal.ZERO;
            this.qty_8 = BigDecimal.ZERO;
            this.amt_8 = BigDecimal.ZERO;
            this.tax_8 = BigDecimal.ZERO;
            this.taxAmt_8 = BigDecimal.ZERO;
            this.qty_9 = BigDecimal.ZERO;
            this.amt_9 = BigDecimal.ZERO;
            this.tax_9 = BigDecimal.ZERO;
            this.taxAmt_9 = BigDecimal.ZERO;
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
