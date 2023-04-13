package com.armitage.server.iscm.report.common.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public interface ScmCommonReportService {
    
	/**
	 * 通用报表查询方法
	 * @param request
	 * @return
	 */
	@GET
	@Path("/queryCommonReportData")
	public Object queryCommonReportData(@Context HttpServletRequest request);

}
