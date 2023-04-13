package com.armitage.server.iscm.purchasemanage.reportLocal.webservice.impl;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import com.armitage.server.common.base.webservice.BaseServiceImpl;
import com.armitage.server.iscm.purchasemanage.reportLocal.model.ReportLocalAPI;
import com.armitage.server.iscm.purchasemanage.reportLocal.model.ReportLocalAPIWSBean;
import com.armitage.server.iscm.purchasemanage.reportLocal.service.ReportLocalAPIBiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReportLocalAPIServiceImpl extends BaseServiceImpl<ReportLocalAPIBiz, ReportLocalAPIWSBean>{

    @GET
    @Path("/getLanguageAPI")
    public Object getLanguageAPI(@Context HttpServletRequest servletRequest) {
        String language = servletRequest.getParameter("language");
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        ReportLocalAPI reportLocalAPI = new ReportLocalAPI();
        if ("CN".equals(language) || language == null) {
            reportLocalAPI.setLanguage("CN");
        } else {
            reportLocalAPI.setLanguage("EN");
        }
        
        return gson.toJson(reportLocalAPI);
    }
}
