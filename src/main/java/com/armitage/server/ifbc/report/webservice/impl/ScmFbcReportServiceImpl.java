package com.armitage.server.ifbc.report.webservice.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;

import com.armitage.server.common.base.model.BaseWSBean;
import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.ifbc.report.model.ScmDeptAndOutltProfit;
import com.armitage.server.ifbc.report.model.ScmDishSaleStructureAnalysis;
import com.armitage.server.ifbc.report.service.ScmFbcReportBiz;
import com.armitage.server.ifbc.report.webservice.ScmFbcReportService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Controller;

@Controller
@Path("/scmFbcReportService")
public class ScmFbcReportServiceImpl extends BaseServiceImpl<ScmFbcReportBiz, BaseWSBean> implements ScmFbcReportService {

	@Override
	public Object queryFbcReportData(HttpServletRequest request) {
		List list=new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		try {
			list=biz.queryFbcReportData(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(list);
	}

	@Override
	public Object queryFbcStoreProfitData(HttpServletRequest request) {
		List list=new ArrayList<>();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
		try {
			list=biz.queryFbcStoreProfitData(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gson.toJson(list);
	}

	@Override
	public Object selectOrgAndOutltProfit(HttpServletRequest request) {
		List<ScmDeptAndOutltProfit> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectOrgAndOutltProfit(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(list);
	}
	
	@Override
	public Object selectDishSaleStructureAnalysis(HttpServletRequest request) {
		List<ScmDishSaleStructureAnalysis> list=new ArrayList<>();
        Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        try {
            list=biz.selectDishSaleStructureAnalysis(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson.toJson(list);
	}

}
