package com.armitage.server.iscm.report.defaultvalue.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InventoryDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
		//格式："venderClassName=60,purOrgUnitNo=00000027,minOrderDate=2019-12-01,maxOrderDate=2019-12-31"
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
		defaultValue.append("invOrgUnitNo=").append(orgUnitNo).append(",")
		    .append("dates=").append(firstDay).append(",")
		    .append("datee=").append(lastDay).append(",")
		    .append("date_s=").append(firstDay).append(",")
		    .append("date_e=").append(lastDay).append(",")
		    .append("reqOrgUnitNo=").append(orgUnitNo).append(",")
		    .append("applyBegDate=").append(firstDay).append(",")
		    .append("applyEndDate=").append(lastDay).append(",")
		    .append("reqBegDate=").append(firstDay).append(",")
		    .append("reqEndDate=").append(lastDay).append(",")
			.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
