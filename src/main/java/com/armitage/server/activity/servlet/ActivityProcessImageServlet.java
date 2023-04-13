package com.armitage.server.activity.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.exception.AppException;

public class ActivityProcessImageServlet extends HttpServlet {
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
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException, AppException {
		String reqParam = request.getQueryString();
		String params[] = StringUtils.split(reqParam, "&");
		if(params.length<1){
			throw new AppException("common.controller.baseformcontroller.error.argument");
		}
		String processInstanceId=StringUtils.substringAfter(params[0], "=");
		ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		ProcessInstance processInstance = processEngine.getRuntimeService().createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
		String processDefinitionId = "";
		if(processInstance != null){
			processDefinitionId = processInstance.getProcessDefinitionId();
        }else{
        	List<HistoricTaskInstance> htiList = processEngine.getHistoryService().createHistoricTaskInstanceQuery()
    				.processInstanceId(processInstanceId)
    				.orderByTaskCreateTime()
    				.asc()
    				.list();
    		if(htiList != null && !htiList.isEmpty()){
    			processDefinitionId = htiList.get(0).getProcessDefinitionId();
    		}else{
    			throw new AppException("activity.servlet.ActivityProcessImageServlet.auditEnd.error");
    		}
        }
        String url = request.getContextPath()+"/diagram-viewer/index.html?processDefinitionId="+processDefinitionId+"&processInstanceId="+processInstanceId;
        response.sendRedirect(url);
	}
}
