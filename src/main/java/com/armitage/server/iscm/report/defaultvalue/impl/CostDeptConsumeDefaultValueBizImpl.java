package com.armitage.server.iscm.report.defaultvalue.impl;


import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CostDeptConsumeDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
	    Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        String accountDate = format.format(calendar.getTime());
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("accountDate=").append(accountDate).append(",")
			.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
