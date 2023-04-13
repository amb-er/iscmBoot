package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model;

import java.math.BigDecimal;

public class ScmInvMoveIssueBill2 extends ScmInvMoveIssueBill{
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_NETAMT = "netAmt";
	public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
	
	private boolean choosed;
	private BigDecimal netAmt;
	private long taskId;
    private boolean freeItem;
    private BigDecimal taxInAmt;	//税额
    
	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}

	public BigDecimal getNetAmt() {
		return netAmt;
	}

	public void setNetAmt(BigDecimal netAmt) {
		this.netAmt = netAmt;
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

    public BigDecimal getTaxInAmt() {
		return taxInAmt;
	}

	public void setTaxInAmt(BigDecimal taxInAmt) {
		this.taxInAmt = taxInAmt;
	}

	public ScmInvMoveIssueBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.netAmt = BigDecimal.ZERO;
		}
	}

	public ScmInvMoveIssueBill2() {
		super();
	}
}
