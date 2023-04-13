package com.armitage.server.quartz.model;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

public class ScmSyncTaskLogAdvQuery extends BaseModel{
    public static final String FN_ORGUNITNO ="orgUnitNo"; //加工组织
    public static final String FN_RESORGUNITNO = "resOrgUnitNo"; //资源组织
    public static final String FN_LOGTIMEFROM = "logtimeFrom"; //同步时间起
    public static final String FN_LOGTIMETO = "logtimeTo"; //同步时间止
    public static final String FN_TASKTYPE = "taskType"; //任务类型

    private String orgUnitNo;
	private String resOrgUnitNo;
    private Date logtimeFrom;
    private Date logtimeTo;
	private String taskType;
	
	public String getOrgUnitNo() {
		return orgUnitNo;
	}

	public void setOrgUnitNo(String orgUnitNo) {
		this.orgUnitNo = orgUnitNo;
	}

	public String getResOrgUnitNo() {
		return resOrgUnitNo;
	}

	public void setResOrgUnitNo(String resOrgUnitNo) {
		this.resOrgUnitNo = resOrgUnitNo;
	}

	public Date getLogtimeFrom() {
		return logtimeFrom;
	}

	public void setLogtimeFrom(Date logtimeFrom) {
		this.logtimeFrom = logtimeFrom;
	}

	public Date getLogtimeTo() {
		return logtimeTo;
	}

	public void setLogtimeTo(Date logtimeTo) {
		this.logtimeTo = logtimeTo;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
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
