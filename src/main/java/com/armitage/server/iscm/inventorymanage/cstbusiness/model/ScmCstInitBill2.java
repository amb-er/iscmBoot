package com.armitage.server.iscm.inventorymanage.cstbusiness.model;
import java.math.BigDecimal;
 
public class ScmCstInitBill2 extends ScmCstInitBill  {
    
    public static final String FN_ISBIZUNIT ="isBizUnit";
    
    private boolean isBizUnit;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private BigDecimal taxInAmt;	//税额
    
    public boolean isBizUnit() {
        return isBizUnit;
    }

    public void setBizUnit(boolean isBizUnit) {
        this.isBizUnit = isBizUnit;
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

	public BigDecimal getTaxInAmt() {
		return taxInAmt;
	}

	public void setTaxInAmt(BigDecimal taxInAmt) {
		this.taxInAmt = taxInAmt;
	}

	public ScmCstInitBill2(boolean defaultValue) {
        super(defaultValue);
        if(defaultValue) {
			this.amt = BigDecimal.ZERO;
			this.taxAmt = BigDecimal.ZERO;
			this.taxInAmt = BigDecimal.ZERO;
		}
    }

    public ScmCstInitBill2() {
        super();
    }
}
