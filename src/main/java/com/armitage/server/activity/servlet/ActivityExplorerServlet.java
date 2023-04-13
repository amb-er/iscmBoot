package com.armitage.server.activity.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Model;
import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.exception.AppException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ActivityExplorerServlet extends HttpServlet {
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
		if(params.length<6){
			throw new AppException("common.controller.baseformcontroller.error.argument");
		}
		String usrCode=StringUtils.substringAfter(params[0], "=");
		String orgUnitNo=StringUtils.substringAfter(params[1], "=");
		String operation=StringUtils.substringAfter(params[2], "=");
		String billTypeId=StringUtils.substringAfter(params[3], "=");
		String modelId=StringUtils.substringAfter(params[4], "=");
		String language=StringUtils.substringAfter(params[5], "=");
		
		if("new".equalsIgnoreCase(operation)){
			try {
				ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
		       	 
	        	RepositoryService repositoryService = processEngine.getRepositoryService();
	        	//初始化一个空模型
	            Model model = repositoryService.newModel();

	            //设置一些默认信息
	            String name = "new-process";
	            String description = "";
	            int revision = 1;
	            String key = "process";
	            
	            ObjectMapper objectMapper = new ObjectMapper();
	            ObjectNode modelNode = objectMapper.createObjectNode();
	            modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
	            modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
	            modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);

	            model.setName(name);
	            model.setKey(key);
	            model.setMetaInfo(modelNode.toString());

	            repositoryService.saveModel(model);
	            String id = model.getId();

	            //完善ModelEditorSource
	            ObjectNode editorNode = objectMapper.createObjectNode();
	            editorNode.put("id", "canvas");
	            editorNode.put("resourceId", "canvas");
	            ObjectNode stencilSetNode = objectMapper.createObjectNode();
	            stencilSetNode.put("namespace",
	                    "http://b3mn.org/stencilset/bpmn2.0#");
	            editorNode.put("stencilset", stencilSetNode);
	            repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
	            String url = request.getContextPath()+"/modeler.html?modelId="+model.getId()+"&usrCode="+usrCode
	            		+"&orgUnitNo="+orgUnitNo+"&billTypeId="+billTypeId+"&operation="+operation;
	            response.sendRedirect(url);
	        } catch (Exception e) {
	        	throw new AppException("activity.servlet.ActivityExplorerServlet.createProcess.error");
	        }
		}else if("edit".equalsIgnoreCase(operation)){
			String url = request.getContextPath()+"/modeler.html?modelId="+modelId+"&usrCode="+usrCode
            		+"&orgUnitNo="+orgUnitNo+"&billTypeId="+billTypeId+"&operation="+operation;
			response.sendRedirect(url);
		}
	}
}
