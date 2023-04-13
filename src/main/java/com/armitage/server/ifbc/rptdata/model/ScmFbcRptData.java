package com.armitage.server.ifbc.rptdata.model;

import java.util.List;

import com.armitage.server.common.base.model.BaseModel;

/**
 * 餐饮报表汇集
 * @author Fengxq
 *
 */
public class ScmFbcRptData extends BaseModel {
	public static final String FN_BEGPERIODID = "begPeriodId";
	public static final String FN_ENDBEGPERIODID = "endPeriodId";

	private long begPeriodId;
	private long endPeriodId;
	
	public long getBegPeriodId() {
		return begPeriodId;
	}

	public void setBegPeriodId(long begPeriodId) {
		this.begPeriodId = begPeriodId;
	}

	public long getEndPeriodId() {
		return endPeriodId;
	}

	public void setEndPeriodId(long endPeriodId) {
		this.endPeriodId = endPeriodId;
	}

	public ScmFbcRptData() {
		super();
	}

	public ScmFbcRptData(boolean defaultValue) {
		super();
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
