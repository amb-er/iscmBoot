package com.armitage.server.iscm.report.common.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.report.common.service.ScmCommonReportBiz;
import com.armitage.server.iscm.report.common.webservice.ScmCommonReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmCommonReportService")
public class ScmCommonReportServiceImpl extends BaseServiceImpl<ScmCommonReportBiz, BaseWSBean> implements ScmCommonReportService {

	@Override
	public Object queryCommonReportData(HttpServletRequest request) {
		List list=new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		try {
			list=biz.queryCommonReportData(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(list);
	}
  
}
