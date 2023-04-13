package com.armitage.server.iscm.report.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.iscm.report.defaultvalue.impl.DefaultValueBizImpl;
import com.armitage.server.system.model.AppService2;
import com.armitage.server.system.model.QueryScheme;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.model.ReportColumns;
import com.armitage.server.system.model.ReportParam;
import com.armitage.server.system.model.ReportTemplate;
import com.armitage.server.system.service.AppServiceBiz;
import com.armitage.server.system.service.QuerySchemeBiz;
import com.armitage.server.system.service.ReportBiz;
import com.armitage.server.system.service.ReportTemplateBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.service.UsrBiz;

public class RdpReportServlet extends HttpServlet {
	private static Log log = LogFactory.getLog(RdpReportServlet.class);

	private ReportBiz reportBiz = (ReportBiz) AppContextUtil.getBean("reportBiz");
	private AppServiceBiz appServiceBiz = (AppServiceBiz) AppContextUtil.getBean("appServiceBiz");
	private QuerySchemeBiz querySchemeBiz = (QuerySchemeBiz) AppContextUtil.getBean("querySchemeBiz");
	private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
	private ReportTemplateBiz reportTemplateBiz=(ReportTemplateBiz) AppContextUtil.getBean("reportTemplateBiz");
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
		//获取请求参数:usrId=aaa&orgUnitNo=bbb&rptcode=ccc&&language=CN
		String reqParam = request.getQueryString();
		String params[] = StringUtils.split(reqParam, "&");
		if(params.length<3){
			throw new AppException("common.controller.baseformcontroller.error.argument");
		}
		String iSCMUrl = request.getRequestURL().toString();
		iSCMUrl = StringUtils.substringBefore(iSCMUrl, "rdpreport");
		if(StringUtils.equals(StringUtils.right(iSCMUrl, 1),"/"))
			iSCMUrl = StringUtils.left(iSCMUrl, StringUtils.length(iSCMUrl)-1);
		String rptcode=StringUtils.substringAfter(params[0], "=");
		String usrCode=StringUtils.substringAfter(params[1], "=");
		String orgUnitNo=StringUtils.substringAfter(params[2], "=");
		String controlUnitNo=StringUtils.substringAfter(params[3], "=");
		String language=StringUtils.substringAfter(params[4], "=");
		String templateUrl = StringUtils.substringAfter(params[5], "=");
		String currQueryScheme=StringUtils.substringAfter(params[6], "=");
		Param param = new Param();
		param.setUsrCode(usrCode);
		param.setLang(language);
		param.setOrgUnitNo(orgUnitNo);
		param.setControlUnitNo(controlUnitNo);
		Report2 report = reportBiz.selectByCode(rptcode, orgUnitNo, param);
		String url="";
		if(report.getType()==4) {
			url = uReportView(templateUrl,currQueryScheme,reqParam,report,param);
		}else {
			url = rdpView(iSCMUrl,report,param);
		}
		response.sendRedirect(url);
	}
	
	private String getDefaultValues(String dataBiz, String usrCode, String orgUnitNo, String controlUnitNo){
		try {
            Class clazz = Class.forName(dataBiz);
            DefaultValueBizImpl defaultValueBizImpl=(DefaultValueBizImpl) clazz.newInstance();
            if(defaultValueBizImpl != null){
            	String s = defaultValueBizImpl.getDefaultValue(usrCode, orgUnitNo, controlUnitNo);
            	return s;
            }
        } catch (Exception e) {
//            e.printStackTrace();
        }
		return "";
	}

	private String uReportView(String rptTemplateUrl,String schemeId,String reqParam,Report2 report,Param param) throws UnsupportedEncodingException{
		//获取默认值
		AppService2 appService = appServiceBiz.selectByCode(param.getOrgUnitNo(), "UReportSrv", param);
		if(appService==null){
			throw new AppException("common.controller.baseformcontroller.error.argument");//报表应用服务未配置
		}
		String url = appService.getUrl();
		if(StringUtils.equals(StringUtils.right(url, 1),"/"))
			url = StringUtils.left(url, StringUtils.length(url)-1);
		String dataSrvUrl=rptTemplateUrl;
		QueryScheme queryScheme;
		if(StringUtils.isNotBlank(schemeId) && StringUtils.isNumeric(schemeId) && Long.valueOf(schemeId)>0) {
			queryScheme = querySchemeBiz.select(Long.valueOf(schemeId), param);
		}else {
			queryScheme = querySchemeBiz.getDefaultScheme(report.getCode(), param.getUsrCode(), param);
		}
		String hideColumns="";
		if(queryScheme!=null && StringUtils.isNotBlank(queryScheme.getDisplayColumns())) {
			StringBuilder columnIndexsBuilder = new StringBuilder(""); 
			String[] columns = StringUtils.split(queryScheme.getDisplayColumns(), ",");
			List<ReportColumns> reportColumnsList = report.getReportColumnsList();
			if(columns.length>0 && reportColumnsList!=null && !reportColumnsList.isEmpty()) {
				for(String column:columns) {
					for(int i=0;i<reportColumnsList.size();i++) {
						if(StringUtils.equals(column, reportColumnsList.get(i).getColumnKey())) {
							columnIndexsBuilder.append(i+1).append(",");
							break;
						}
					}
				}
				hideColumns="&&columnInfo=,"+columnIndexsBuilder;
			}
		}
		String lockColumns="";
		String lockRows="";
		if(StringUtils.isNotBlank(report.getRemarks())) {
			String[] args = StringUtils.split(report.getRemarks(), ",");
			for(String arg:args) {
				if(StringUtils.equalsIgnoreCase(StringUtils.substringBefore(arg, "="),"lockColumns"))
					lockColumns="&&"+arg;
				if(StringUtils.equalsIgnoreCase(StringUtils.substringBefore(arg, "="),"lockRows"))
					lockRows="&&"+arg;
			}
		}
		if(StringUtils.isBlank(hideColumns))
			hideColumns="&&columnInfo=,0,";
		String operator="";
		String userCode="";
		if(StringUtils.isNotBlank(param.getUsrCode())) {
			userCode = "&&userCode="+param.getUsrCode();
			Usr usr = usrBiz.selectByCode(param.getUsrCode(), param);
			if(usr!=null)
				try {
					operator = "&&operator="+URLEncoder.encode(usr.getName(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
				}
		}
		String templateUrl="";
		if (report.getReportTemplateList() != null && !report.getReportTemplateList().isEmpty()) {
			ReportTemplate reportTemplate = reportTemplateBiz.selectDirect(report.getReportTemplateList().get(0).getId(), param);
			if (reportTemplate != null && reportTemplate.getContent() != null && reportTemplate.getContent().length > 0) {
				if(StringUtils.equals(StringUtils.right(rptTemplateUrl, 1),"/"))
					templateUrl = StringUtils.left(rptTemplateUrl, StringUtils.length(rptTemplateUrl)-1);
				if(StringUtils.isNotBlank(templateUrl)) {
					templateUrl = templateUrl+"/rest/ureportTemplateService/queryTemplateData?templateId="+report.getReportTemplateList().get(0).getId();
					templateUrl = URLEncoder.encode(templateUrl,"UTF-8");
				}
			}
		}
		if(StringUtils.contains(reqParam, "notSearch=true")) {
			if(StringUtils.isNotBlank(templateUrl)) {
				url = url+"/ureport/preview?_u=service:"+templateUrl+"&&_t=1,4,7"+hideColumns+lockColumns+lockRows+operator+userCode;	//打开报表未查询时只出报表模板
			}else {
				url = url+"/ureport/preview?_u=file:"+report.getTemplete()+"&&_t=1,4,7"+hideColumns+lockColumns+lockRows+operator+userCode;	//打开报表未查询时只出报表模板
			}
		}else {
			List<ReportParam> reportParamList = report.getReportParamList();
			List<String> paramList = Arrays.asList(StringUtils.split(reqParam,"&&"));
			if(paramList!=null && !paramList.isEmpty()) {
				reqParam="";
				String queryCondition="";
				for(String parm:paramList) {
					if(StringUtils.equalsIgnoreCase(StringUtils.substringBefore(parm,"="), "queryCondition")) {
						String[] conditionParms = StringUtils.split(StringUtils.substringAfter(parm, "="),";");
						if(reportParamList!=null && !reportParamList.isEmpty()) {
							for(ReportParam reportParam:reportParamList) {
								for(String conditionParm:conditionParms) {
									if(StringUtils.equalsIgnoreCase(StringUtils.substringBefore(conditionParm, ":"), reportParam.getCode())) {
										if(StringUtils.isNotBlank(queryCondition))
											queryCondition=queryCondition+";";
										try {
											queryCondition=queryCondition+URLEncoder.encode(reportParam.getName(),"UTF-8")+":"+StringUtils.substringAfter(conditionParm, ":");
										} catch (UnsupportedEncodingException e) {
										}
										break;
									}
								}
							}
						}
					}else {
						if(StringUtils.isNotBlank(reqParam))
							reqParam = reqParam + "&&";
						reqParam=reqParam+parm;
					}
				}
				if(StringUtils.isNotBlank(queryCondition))
					reqParam=reqParam+"&&queryCondition="+queryCondition;
			}
			AppService2 rdpDataAppService = appServiceBiz.selectByCode(param.getOrgUnitNo(), "iSCM", param);
			if(rdpDataAppService!=null && StringUtils.isNotBlank(rdpDataAppService.getUrl())){
				dataSrvUrl=rdpDataAppService.getUrl();
				if(StringUtils.equals(StringUtils.right(dataSrvUrl, 1),"/"))
					dataSrvUrl = StringUtils.left(dataSrvUrl, StringUtils.length(dataSrvUrl)-1);
			}
			dataSrvUrl = StringUtils.isNotBlank(report.getExecSql()) ? (dataSrvUrl+report.getExecSql()) : "";	//ExecSql中存在具体的路径及方法
			String reportName = "";
			if(StringUtils.isNotBlank(report.getName())){
				try {
					reportName = "&&_n="+URLEncoder.encode(report.getName(),"UTF-8");
				} catch (UnsupportedEncodingException e) {
				}
			}
			if(StringUtils.isNotBlank(templateUrl)) {
				url = url+"/ureport/preview?_u=service:"+templateUrl+reportName+"&&_t=1,4,7&&DataSrvUrl="+dataSrvUrl+"&&language="+param.getLang()+"&&"+reqParam+hideColumns+lockColumns+lockRows+operator+userCode;
			}else {
				url = url+"/ureport/preview?_u=file:"+report.getTemplete()+reportName+"&&_t=1,4,7&&DataSrvUrl="+dataSrvUrl+"&&language="+param.getLang()+"&&"+reqParam+hideColumns+lockColumns+lockRows+operator+userCode;
			}
		}
		return url;
	}

	private String rdpView(String iSCMUrl,Report2 report,Param param) {
		//获取默认值
		String defaultValues = "";
		if(report != null && StringUtils.isNotBlank(report.getDataBiz())){
			defaultValues = getDefaultValues(report.getDataBiz(),param.getUsrCode(),param.getOrgUnitNo(),param.getControlUnitNo());
		}
		AppService2 appService = appServiceBiz.selectByCode(param.getOrgUnitNo(), "RdpReportSrv", param);
		if(appService==null){
			throw new AppException("common.controller.baseformcontroller.error.argument");//报表应用服务未配置
		}
		String rdpDataUrl=iSCMUrl;
		AppService2 rdpDataAppService = appServiceBiz.selectByCode(param.getOrgUnitNo(), "iSCM", param);
		if(rdpDataAppService!=null && StringUtils.isNotBlank(rdpDataAppService.getUrl())){
			rdpDataUrl=rdpDataAppService.getUrl();
			iSCMUrl = rdpDataAppService.getUrl();
			if(StringUtils.equals(StringUtils.right(rdpDataUrl, 1),"/"))
				rdpDataUrl = StringUtils.left(rdpDataUrl, StringUtils.length(rdpDataUrl)-1);
		}
		rdpDataUrl = StringUtils.isNotBlank(report.getExecSql()) ? (rdpDataUrl+report.getExecSql()) : "";	//ExecSql中存在具体的路径及方法
		QueryScheme queryScheme = querySchemeBiz.getDefaultScheme(report.getCode(), param.getUsrCode(), param);
		String hideColumns="";
		if(queryScheme!=null && StringUtils.isNotBlank(queryScheme.getDisplayColumns()))
			hideColumns="&columnInfo="+queryScheme.getDisplayColumns();
		String lockColumns="";
		if(StringUtils.isNotBlank(report.getRemarks())) {
			String[] args = StringUtils.split(report.getRemarks(), ",");
			for(String arg:args) {
				if(StringUtils.equalsIgnoreCase(StringUtils.substringBefore(arg, "="),"lockColumns"))
					lockColumns="&&"+arg;
			}
		}
		String url = appService.getUrl();
		if(StringUtils.equals(StringUtils.right(url, 1),"/"))
			url = StringUtils.left(url, StringUtils.length(url)-1);
		url = url+"/rdppage/main/"+report.getTemplete()+"?language="+param.getLang()+"&orgUnitNo="+param.getOrgUnitNo()+
				"&controlUnitNo="+param.getControlUnitNo()+hideColumns+lockColumns+"&iSCMUrl="+iSCMUrl+"&RdpDataUrl="+rdpDataUrl+"&defaultValues="+defaultValues
				+"&rdpreportselectservlet=rdpreportselectservlet"
				+"&isDynamicReport="+report.getRemarks();
		return url;
	}
	
}
