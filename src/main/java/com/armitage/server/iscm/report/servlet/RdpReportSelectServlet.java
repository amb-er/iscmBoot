package com.armitage.server.iscm.report.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.iscm.report.selectdata.ReportSelectDataBiz;

public class RdpReportSelectServlet extends HttpServlet {
	
	private static Log log = LogFactory.getLog(RdpReportSelectServlet.class);

	private ReportSelectDataBiz reportSelectDataBiz = (ReportSelectDataBiz) AppContextUtil.getBean("reportSelectDataBiz");
	/**
     * 
     */
	private static final long serialVersionUID = 1210436705188940602L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String responseMsg = "";

		if (StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
			request.setCharacterEncoding("utf-8"); // 对get无效！
		}
		//String url = request.getRequestURL().toString();
		
		String data = request.getParameter("data");
		
		String orgUnitNo = StringUtils.substringBetween(data, "orgUnitNo=", "language=");
		orgUnitNo = StringUtils.left(orgUnitNo,StringUtils.length(orgUnitNo)-2);
		String language = StringUtils.substringBetween(data, "language=", "controlUnitNo=");
		language = StringUtils.left(language,StringUtils.length(language)-2);
		String controlUnitNo = StringUtils.substringBetween(data, "controlUnitNo=", "key=");
		controlUnitNo = StringUtils.left(controlUnitNo,StringUtils.length(controlUnitNo)-2);
		String parmType,defaultValue = null;
		if(data.indexOf("defaultValue") > 0){
			parmType = StringUtils.substringBetween(data, "key=", "defaultValue=");
			parmType = StringUtils.left(parmType,StringUtils.length(parmType)-2);
			defaultValue = StringUtils.substringAfter(data,"defaultValue=");
		}else{
			parmType = StringUtils.substringAfter(data,"key=");
		}
		
		if(StringUtils.isBlank(controlUnitNo) || StringUtils.equals("null", controlUnitNo)){
			controlUnitNo = "00000001";
	    }
		
		switch (parmType) {
			case "orgUnitNo":
				responseMsg = reportSelectDataBiz.getOrgUnitNoList(defaultValue, orgUnitNo, controlUnitNo);
				break;
			case "reqOrgUnitNo":
				responseMsg = reportSelectDataBiz.getReqOrgUnitNoList(defaultValue, orgUnitNo, controlUnitNo);
				break;
			case "purOrgUnitNo":
				responseMsg = reportSelectDataBiz.getPurOrgUnitNoList(defaultValue, orgUnitNo, controlUnitNo);
				break;
			case "finOrgUnitNo":
				responseMsg = reportSelectDataBiz.getFinOrgUnitNoList(defaultValue, orgUnitNo, controlUnitNo);
				break;
			case "vendorName":
				responseMsg = reportSelectDataBiz.getVenderNameList(defaultValue, controlUnitNo);
				break;
			case "venderClassName":
				responseMsg = reportSelectDataBiz.getVenderClassNameList(defaultValue, controlUnitNo);
				break;
			case "materialClassName":
				responseMsg = reportSelectDataBiz.getMaterialClassNameList(defaultValue, controlUnitNo);
				break;
			case "materialClassName2":
				responseMsg = reportSelectDataBiz.getMaterialClassNameForParentList(defaultValue, controlUnitNo);
				break;
			case "invOrgUnitNo":
                responseMsg = reportSelectDataBiz.getInvOrgUnitNoList(defaultValue, orgUnitNo,controlUnitNo);
                break;
			case "materialName":
                responseMsg = reportSelectDataBiz.getMaterialNameList(defaultValue, controlUnitNo);
                break;
			case "whName":
                responseMsg = reportSelectDataBiz.getWhNameList(defaultValue, orgUnitNo,controlUnitNo);
                break;
			case "purBizType":
                responseMsg = reportSelectDataBiz.getPurBizTypeList(defaultValue, "scmbizType");
                break;
			case "useOrgUnitNo":
			    responseMsg = reportSelectDataBiz.getFormOrgUnitNoList(defaultValue, orgUnitNo,controlUnitNo,"adminToInv");
                break;
			case "adminToPurFrom":
                responseMsg = reportSelectDataBiz.getFormOrgUnitNoList(defaultValue, orgUnitNo,controlUnitNo,"adminToPurFrom");
                break;
			case "adminToCostFrom":
                responseMsg = reportSelectDataBiz.getFormOrgUnitNoList(defaultValue, orgUnitNo,controlUnitNo,"adminToCostFrom");
                break;
			case "costOrgUnitNo":
			    responseMsg = reportSelectDataBiz.getCostOrgunitNoList(defaultValue, orgUnitNo,controlUnitNo);
                break;
			case "purToInvTo":
                responseMsg = reportSelectDataBiz.getToOrgUnitNoList(defaultValue, orgUnitNo,controlUnitNo,"purToInvTo");
                break;
			case "buyerName":
                responseMsg = reportSelectDataBiz.getBuyerNameList(defaultValue, orgUnitNo,controlUnitNo);
                break;
			case "whOrDept":
				responseMsg = reportSelectDataBiz.getWhOrDept(defaultValue, orgUnitNo,controlUnitNo);
                break;
			case "custNo":
				responseMsg = reportSelectDataBiz.getCustNameList(defaultValue, orgUnitNo,controlUnitNo);
				break;
			case "standardName":
				responseMsg = reportSelectDataBiz.getMaterialStandardNameList(controlUnitNo);
				break;
			case "reqFinOrgUnitNo":
				responseMsg = reportSelectDataBiz.getReqFinOrgUnitNoList(defaultValue, controlUnitNo);
				break;
			case "appraiseType":
				responseMsg = reportSelectDataBiz.getAppraiseTypeList(defaultValue, "appraiseType");
				break;
			default:
				break;
		}
		
		response.setContentType("application/json;charset=utf-8");
		//支持跨域
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS");
		response.setHeader("Access-Control-Allow-Headers","origin,X-requested-with,content-type,accept");
//		log.info("responseMsg:"+responseMsg);
		PrintWriter printWriter = response.getWriter();
		printWriter.print(responseMsg);
		response.flushBuffer();
	}

}
