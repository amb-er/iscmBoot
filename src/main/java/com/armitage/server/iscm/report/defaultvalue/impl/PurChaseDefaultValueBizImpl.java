package com.armitage.server.iscm.report.defaultvalue.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PurChaseDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
		//格式："venderClassName=60,purOrgUnitNo=00000027,minOrderDate=2019-12-01,maxOrderDate=2019-12-31"
	    Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String curDay = format.format(calendar.getTime());
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("purOrgUnitNo=").append(orgUnitNo).append(",")
		.append("endDate=").append(curDay).append(",")
		.append("date_s=").append(curDay).append(",")
		.append("date_e=").append(curDay).append(",")
		.append("minOrderDate=").append(curDay).append(",")
		.append("maxOrderDate=").append(curDay).append(",")
		.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
