package com.armitage.server.iscm.common.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmSyncTaskInfoAdvQuery  extends BaseModel{
	public static final String FN_TASKSTATUS = "taskStatus";
	public static final String FN_TASKTYPE = "taskType";
	public static final String FN_BEGDATE = "begDate"; 
    public static final String FN_ENDDATE = "endDate"; 
	
	private String taskStatus;
	private String taskType;
	private Date begDate;
	private Date endDate;
	
	
	public String getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}
	public String getTaskType() {
		return taskType;
	}
	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}
	public Date getBegDate() {
		return begDate;
	}
	public void setBegDate(Date begDate) {
		this.begDate = begDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String getPkKey() {
		return null;
	}
	@Override
	public long getPK() {
		return 0;
	}
	@Override
	public String[] getRequiredFields() {
		return null;
	}
	@Override
	public String[] getFieldNames() {
		return null;
	}
	@Override
	public List<String[]> getUniqueKeys() {
		return null;
	}
	
}
