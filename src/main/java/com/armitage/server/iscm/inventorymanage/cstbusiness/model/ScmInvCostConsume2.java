package com.armitage.server.iscm.inventorymanage.cstbusiness.model;

import java.math.BigDecimal;

public class ScmInvCostConsume2 extends ScmInvCostConsume{
	
	public static final String FN_AMT = "amt";
    public static final String FN_TAXAMOUNT = "taxAmount";
    public static final String FN_TAXAMT = "taxAmt";
    public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
    
	private BigDecimal amt;
    private BigDecimal taxAmount;
    private BigDecimal taxAmt;
    private long taskId;
    private boolean freeItem;
	
    public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public boolean isFreeItem() {
		return freeItem;
	}

	public void setFreeItem(boolean freeItem) {
		this.freeItem = freeItem;
	}

	public ScmInvCostConsume2(boolean defaultValue) {
        super(defaultValue);
    }

    public ScmInvCostConsume2() {
        super();
    }
}
