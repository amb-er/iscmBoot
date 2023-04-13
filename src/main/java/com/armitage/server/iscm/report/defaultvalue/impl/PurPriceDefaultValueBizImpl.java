package com.armitage.server.iscm.report.defaultvalue.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PurPriceDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
	    Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String curDay = format.format(calendar.getTime());
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("purOrgUnitNo=").append(orgUnitNo).append(",")
		.append("bizDate=").append(curDay).append(",")
		.append("priceType=1").append(",")
		.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
