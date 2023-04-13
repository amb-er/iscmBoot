package com.armitage.server.iscm.report.defaultvalue.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CostTransferOccurSumDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
	    Calendar caleFirst = Calendar.getInstance();
        Calendar caleLast = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        caleFirst.add(Calendar.MONTH, 0);
        caleFirst.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = format.format(caleFirst.getTime());
        caleLast.add(Calendar.MONTH, 1);
        caleLast.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = format.format(caleLast.getTime());
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("costOrgUnitNo=").append(orgUnitNo).append(",").append("beginDate=")
			.append(firstDay).append(",").append("endDate=").append(lastDay).append(",")
			.append("currentOrgUnitNo=").append(orgUnitNo).append(",")
			.append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
