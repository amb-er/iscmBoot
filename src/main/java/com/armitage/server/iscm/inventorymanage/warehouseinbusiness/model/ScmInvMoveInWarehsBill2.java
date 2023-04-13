package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;

public class ScmInvMoveInWarehsBill2 extends ScmInvMoveInWarehsBill{
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_TAXINAMT = "taxInAmt";
	public static final String FN_TASKID = "taskId";
	public static final String FN_FREEITEM = "freeItem";
	
	private boolean choosed;
	private BigDecimal taxInAmt;	//税额
	private long taskId;
	private boolean freeItem;
	
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public BigDecimal getTaxInAmt() {
		return taxInAmt;
	}

	public void setTaxInAmt(BigDecimal taxInAmt) {
		this.taxInAmt = taxInAmt;
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

    public ScmInvMoveInWarehsBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.taxInAmt = BigDecimal.ZERO;
		}
	}

	public ScmInvMoveInWarehsBill2() {
		super();
	}
}
