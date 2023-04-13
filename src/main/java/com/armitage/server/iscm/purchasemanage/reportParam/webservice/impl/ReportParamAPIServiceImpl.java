package com.armitage.server.iscm.purchasemanage.reportParam.webservice.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.reportParam.model.ReportParamAPIWSBean;
import com.armitage.server.iscm.purchasemanage.reportParam.service.ReportParamAPIBiz;

public class ReportParamAPIServiceImpl extends BaseServiceImpl<ReportParamAPIBiz, ReportParamAPIWSBean> {
	
	@GET
	@Path("/getReportParamAPI")
	public String getReportParam(@Context HttpServletRequest servletRequest) {
	    // 返回keylist字符串
	    String response = null;
	    // 根据传进来的参数决定使用哪一个biz
	    String table = servletRequest.getParameter("table");
	    String orgUnitNo = servletRequest.getParameter("orgUnitNo");
	    switch (table) {
        case "orgUnitNo":
            response = biz.getOrgUnitNoKeyList(orgUnitNo);
            break;

        default:
            break;
        }
//	    response = "千里马:1,百利玛:2";
	    return response;
	}
	
	@GET
	@Path("/getReportSelectDataAPI")
	public String getReportSelectDataAPI(@Context HttpServletRequest servletRequest) {
	    // 返回json字符串
	    String response = null;
	    // 根据传进来的参数决定使用哪一个biz
	    String parmType = servletRequest.getParameter("parmType");
	    String controlUnitNo = servletRequest.getParameter("controlUnitNo");
	    if(StringUtils.isBlank(controlUnitNo) || StringUtils.equals("null", controlUnitNo)){
	    	controlUnitNo = "00000001";
	    }
	    switch (parmType) {
        case "orgUnitNo":
            response = biz.getOrgUnitNoList(null,controlUnitNo);
            return response;
        case "reqOrgUnitNo":
            response = biz.getReqOrgUnitNoList(null,controlUnitNo);
            return response;
        case "purOrgUnitNo":
            response = biz.getPurOrgUnitNoList(null,controlUnitNo);
            return response;
        case "vendorName":
            response = biz.getVenderNameList(controlUnitNo);
            return response;
        case "venderClassName":
            response = biz.getVenderClassNameList(controlUnitNo);
            return response;
        case "materialClassName":
            response = biz.getMaterialClassNameList(controlUnitNo);
            return response;
		default:
            break;
        }
	    return response;
	}
}

