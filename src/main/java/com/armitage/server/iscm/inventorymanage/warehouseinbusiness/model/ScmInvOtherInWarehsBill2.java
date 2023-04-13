package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

public class ScmInvOtherInWarehsBill2 extends ScmInvOtherInWarehsBill{
	public static final String FN_CHOOSED = "choosed";
	public static final String FN_AMT = "Amt";
	public static final String FN_TAXAMT = "taxAmt";
	public static final String FN_TASKID = "taskId";
    public static final String FN_FREEITEM = "freeItem";
	public static final String FN_TAXINAMT = "taxInAmt";
	
	private boolean choosed;
	private BigDecimal amt;
	private BigDecimal taxAmt;
	private BigDecimal taxInAmt;	//税额
	private long taskId;
    private boolean freeItem;
	public String requireFields;

	public boolean isChoosed() {
		return choosed;
	}

	public void setChoosed(boolean choosed) {
		this.choosed = choosed;
	}
	
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}

	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}

	public BigDecimal getAmt() {
		return amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
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

	public void setRequiredFields(String requireFields) {
		this.requireFields = requireFields;
	}

	@Override
	public String[] getRequiredFields() {
		StringBuffer reqFields = new StringBuffer(StringUtils.join(
				super.getRequiredFields(), ","));
		if (!StringUtils.isEmpty(requireFields)) {
			String[] requireField = StringUtils.split(requireFields, ",");
			String[] fieldNames = getFieldNames();
			for (String reqField : requireField) {
				for (String fiedName : fieldNames) {
					if (StringUtils.equalsIgnoreCase(reqField, fiedName)) {
						reqFields.append(",").append(fiedName);
						break;
					}
				}
			}
		}
		StringBuffer newReqFields = new StringBuffer("");
		String orgReqFields[] = StringUtils.split(reqFields.toString(), ",");
		for (int i = orgReqFields.length - 1; i >= 0; i--) {
			String filed = orgReqFields[i];
			if (StringUtils.isEmpty(newReqFields.toString())) {
				newReqFields.append(filed);
			} else {
				newReqFields.append(",").append(filed);
			}
		}
		return StringUtils.split(newReqFields.toString(), ",");
	}
	public ScmInvOtherInWarehsBill2(boolean defaultValue) {
		super(defaultValue);
		if(defaultValue){
			this.taxAmt = BigDecimal.ZERO;
			this.amt = BigDecimal.ZERO;
		}
	}

	public ScmInvOtherInWarehsBill2() {
		super();
	}
}
