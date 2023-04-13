package com.armitage.server.iscm.report.defaultvalue.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScmInvStorageAgePrimnessAnalysisDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("invOrgUnitNo=").append(orgUnitNo).append(",")
			.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
