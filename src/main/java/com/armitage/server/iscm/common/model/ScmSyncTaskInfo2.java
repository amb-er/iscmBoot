package com.armitage.server.iscm.common.model;

import org.apache.commons.lang.StringUtils;

public class ScmSyncTaskInfo2 extends ScmSyncTaskInfo {
    public static final String FN_TASKNAME ="taskName";
    public static final String FN_DATAID ="dataId";
    public static final String FN_VENDORID ="vendorId";
    public static final String FN_BILLTYPE ="billType";
    public static final String FN_BILLNO ="billNo";

	private String taskName;
    private long dataId;
    private long vendorId;
	private String billType;
	private String billNo;
	private String requFields;
	private int cycleTime;
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public long getDataId() {
		return dataId;
	}

	public void setDataId(long dataId) {
		this.dataId = dataId;
	}

	public long getVendorId() {
		return vendorId;
	}

	public void setVendorId(long vendorId) {
		this.vendorId = vendorId;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getRequFields() {
		return requFields;
	}

	public void setRequFields(String requFields) {
		this.requFields = requFields;
	}

	public int getCycleTime() {
		return cycleTime;
	}

	public void setCycleTime(int cycleTime) {
		this.cycleTime = cycleTime;
	}

	@Override
	public String[] getRequiredFields() {
		StringBuffer reqFields = new StringBuffer(StringUtils.join(
				super.getRequiredFields(), ","));
		if (!StringUtils.isEmpty(requFields)) {
			String[] requireField = StringUtils.split(requFields, ",");
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

	public ScmSyncTaskInfo2(boolean defaultValue) {
		super(defaultValue);
	}
	
	public ScmSyncTaskInfo2(){
		super();
	}

	@Override
	public String[] getFieldNames() {
		return new String[]{
	            FN_ID,
	            FN_BIZCODE,
	            FN_ORGUNITNO,
	            FN_TASKCODE,
	            FN_TASKACTION,
	            FN_TASKTYPE,
	            FN_PRODUCTCODE,
	            FN_BEGDATE,
	            FN_ENDDATE,
	            FN_SYNCDATAID,
	            FN_TASKOWNER,
	            FN_TASKSTATUS,
	            FN_LOGTIME,
	            FN_TASKEXECUTOR,
	            FN_STATUSMESSAGE,
	            FN_UPDATETIME,
	            FN_CREATETIME,
	            FN_TASKSOURCE,
	            FN_REMARKS,
	            FN_CONTROLUNITNO,
	            FN_VENDORID,
	            FN_BILLTYPE
	        };
	}
	
}
