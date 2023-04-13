package com.armitage.server.iscm.report.defaultvalue.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.dingding.service.CommonDingdingBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;

public class DeliveryListDefaultValueBizImpl extends DefaultValueBizImpl{
	private OrgBaseUnitBiz orgBaseUnitBiz = (OrgBaseUnitBiz) AppContextUtil.getBean("orgBaseUnitBiz");

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
        Param param = new Param();
        param.setOrgUnitNo(orgUnitNo);
        param.setControlUnitNo(controlUnitNo);
        OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(orgUnitNo, param);
        if(orgBaseUnit!=null)
        	param.setTimeZone(orgBaseUnit.getTimeZone());
        String lastDay = FormatUtils.fmtDate(CalendarUtil.getDate(param));
		StringBuffer defaultValue = new StringBuffer("");
		defaultValue.append("purOrgUnitNo=").append(orgUnitNo).append(",").append("minOrderDate=")
			.append(firstDay).append(",").append("maxOrderDate=").append(lastDay).append(",")
			.append("currentOrgUnitNo=").append(orgUnitNo).append(",").append("currentControlUnitNo=").append(controlUnitNo);
		return (defaultValue.toString());
	}
}
