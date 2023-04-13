package com.armitage.server.iscm.report.defaultvalue.impl;

public class RealtimeStockDefaultValueBizImpl extends DefaultValueBizImpl{
	
	
	@Override
	public String getDefaultValue(String usrCode, String orgUnitNo, String controlUnitNo) {
		//格式："venderClassName=60,purOrgUnitNo=00000027,minOrderDate=2019-12-01,maxOrderDate=2019-12-31"
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("invOrgUnitNo=").append(orgUnitNo).append(",")
		.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
