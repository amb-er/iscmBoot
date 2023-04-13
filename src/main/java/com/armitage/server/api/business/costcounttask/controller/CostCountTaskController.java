package com.armitage.server.api.business.costcounttask.controller;

import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.costcounttask.params.CountCostMaterialListParams;
import com.armitage.server.api.business.costcounttask.params.CountCostMaterialListParamsApi;
import com.armitage.server.api.business.costcounttask.params.CountCostTableParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTableParamsApi;
import com.armitage.server.api.business.costcounttask.params.CountCostTableSaveParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTableSaveParamsApi;
import com.armitage.server.api.business.costcounttask.params.CountCostTaskDeptParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTaskDeptParamsApi;
import com.armitage.server.api.business.costcounttask.params.CountCostTaskDiffParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTaskDiffParamsApi;
import com.armitage.server.api.business.costcounttask.params.CountCostTaskListParams;
import com.armitage.server.api.business.costcounttask.params.CountCostTaskListParamsApi;
import com.armitage.server.api.business.costcounttask.params.DeptListParams;
import com.armitage.server.api.business.costcounttask.params.DeptListParamsApi;
import com.armitage.server.api.business.costcounttask.result.CountCostMaterialListResult;
import com.armitage.server.api.business.costcounttask.result.CountCostMaterialListResultApi;
import com.armitage.server.api.business.costcounttask.result.CountCostTableDetailResult;
import com.armitage.server.api.business.costcounttask.result.CountCostTableResult;
import com.armitage.server.api.business.costcounttask.result.CountCostTableResultApi;
import com.armitage.server.api.business.costcounttask.result.CountCostTableSaveResultApi;
import com.armitage.server.api.business.costcounttask.result.CountCostTaskDeptResult;
import com.armitage.server.api.business.costcounttask.result.CountCostTaskDeptResultApi;
import com.armitage.server.api.business.costcounttask.result.CountCostTaskDiffResult;
import com.armitage.server.api.business.costcounttask.result.CountCostTaskDiffResultApi;
import com.armitage.server.api.business.costcounttask.result.CountCostTaskListResult;
import com.armitage.server.api.business.costcounttask.result.CountCostTaskListResultApi;
import com.armitage.server.api.business.costcounttask.result.DeptListResult;
import com.armitage.server.api.business.costcounttask.result.DeptListResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountCostTaskDiff;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTaskAdvQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.system.model.OrgAdmin2;

@Controller
@RequestMapping("/api/costCountTask")
@Api(tags="盘存接口")
public class CostCountTaskController {
	private static Log log = LogFactory.getLog(CostCountTaskController.class);
    private ScmInvCountingTaskBiz scmInvCountingTaskBiz = (ScmInvCountingTaskBiz) AppContextUtil.getBean("scmInvCountingTaskBiz");
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountCostTaskList", method=RequestMethod.POST)
    @ApiOperation(value="获取盘存列表", consumes="application/json")
    public CountCostTaskListResultApi queryCountCostTaskList(@RequestBody CountCostTaskListParamsApi params) {
		CountCostTaskListResultApi result = new CountCostTaskListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	CountCostTaskListParams countCostTaskListParams = params.getParams();
	        if(countCostTaskListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery = new ScmInvCountingTaskAdvQuery();
	        BeanUtils.copyProperties(countCostTaskListParams, scmInvCountingTaskAdvQuery);
	        scmInvCountingTaskAdvQuery.setCostCenter(true);
	        scmInvCountingTaskAdvQuery.setFromInterface(true);
	        List<ScmInvCountingTask2> scmInvCountingTaskList = scmInvCountingTaskBiz.queryCountCostTaskList(scmInvCountingTaskAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryCountCostTaskList"));
	        if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty()){
	        	List< CountCostTaskListResult > resultList = new ArrayList();
	        	for(ScmInvCountingTask2 scmInvCountingTask:scmInvCountingTaskList){
	        		CountCostTaskListResult countCostTaskListResult = new CountCostTaskListResult();
	        		BeanUtils.copyProperties(scmInvCountingTask, countCostTaskListResult);
	        		resultList.add(countCostTaskListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取盘存列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountCostTaskDepteList", method=RequestMethod.POST)
    @ApiOperation(value="查询盘存部门", consumes="application/json")
    public CountCostTaskDeptResultApi queryCountCostTaskDepteList(@RequestBody CountCostTaskDeptParamsApi params) {
		CountCostTaskDeptResultApi result = new CountCostTaskDeptResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountCostTaskDeptParams countCostTaskDeptParams = params.getParams();
	        if(countCostTaskDeptParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTask2 scmInvCountingTask= new ScmInvCountingTask2();
	        scmInvCountingTask.setTaskNo(countCostTaskDeptParams.getTaskNo());
	        List<OrgAdmin2> orgAdminList = scmInvCountingTaskBiz.queryCountCostTaskDepteList(scmInvCountingTask, ParamHelper.createParam(integratedRequest,"queryCountCostTaskDepteList"));
	        if(orgAdminList!=null && !orgAdminList.isEmpty()){
	        	List<CountCostTaskDeptResult> resultList = new ArrayList<>();
	        	for(OrgAdmin2 orgAdmin:orgAdminList){
	        		CountCostTaskDeptResult countCostTaskDeptResult = new CountCostTaskDeptResult();
	        		countCostTaskDeptResult.setDeptNo(orgAdmin.getOrgUnitNo());
	        		countCostTaskDeptResult.setDeptName(orgAdmin.getOrgUnitName());
	        		resultList.add(countCostTaskDeptResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询盘存部门失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountCostTable", method=RequestMethod.POST)
    @ApiOperation(value="查询盘存表", consumes="application/json")
    public CountCostTableResultApi queryCountCostTable(@RequestBody CountCostTableParamsApi params) {
		CountCostTableResultApi result = new CountCostTableResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountCostTableParams countCostTableParams = params.getParams();
	        if(countCostTableParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTask2 scmInvCountingTask= new ScmInvCountingTask2();
	        scmInvCountingTask.setTaskNo(countCostTableParams.getTaskNo());
	        scmInvCountingTask.setUseOrgUnitNo(countCostTableParams.getDeptNo());
	        ScmInvCountingCostCenter2 scmInvCountingCostCenter = scmInvCountingTaskBiz.queryCountCostTable(scmInvCountingTask, ParamHelper.createParam(integratedRequest,"queryCountCostTable"));
	        if(scmInvCountingCostCenter!=null){
	        	CountCostTableResult countCostTableResult = new CountCostTableResult();
	        	BeanUtils.copyProperties(scmInvCountingCostCenter, countCostTableResult);
	        	countCostTableResult.setDeptNo(scmInvCountingCostCenter.getUseOrgUnitNo());
	        	countCostTableResult.setDeptName(scmInvCountingCostCenter.getUserOrgUnitName());
	        	List<CountCostTableDetailResult> detailList = new ArrayList<>();
	        	List<ScmInvCountingCostCenterEntry2> scmInvCountingCostCenterEntryList = scmInvCountingCostCenter.getScmInvCountingCostCenterEntryList();
	        	if(scmInvCountingCostCenterEntryList!=null && !scmInvCountingCostCenterEntryList.isEmpty()){
	        		for(ScmInvCountingCostCenterEntry2 scmInvCountingCostCenterEntry:scmInvCountingCostCenterEntryList){
	        			CountCostTableDetailResult countCostTableDetailResult = new CountCostTableDetailResult();
	        			BeanUtils.copyProperties(scmInvCountingCostCenterEntry, countCostTableDetailResult);
	        			detailList.add(countCostTableDetailResult);
	        		}
	        	}
	        	countCostTableResult.setDetailList(detailList);
	        	result.setResult(countCostTableResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询盘存表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSaveCountCostTable", method=RequestMethod.POST)
    @ApiOperation(value="盘存表保存单", consumes="application/json")
    public CountCostTableSaveResultApi doSaveCountCostTable(@RequestBody CountCostTableSaveParamsApi params) {
		CountCostTableSaveResultApi result = new CountCostTableSaveResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountCostTableSaveParams countCostTableSaveParams = params.getParams();
	        if(countCostTableSaveParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        scmInvCountingTaskBiz.doSaveCountCostTable(countCostTableSaveParams, ParamHelper.createParam(integratedRequest,"doSaveCountCostTable"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("盘存表保存单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountCostTaskDiff", method=RequestMethod.POST)
    @ApiOperation(value="查看盘存差异", consumes="application/json")
    public CountCostTaskDiffResultApi queryCountCostTaskDiff(@RequestBody CountCostTaskDiffParamsApi params) {
		CountCostTaskDiffResultApi result = new CountCostTaskDiffResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountCostTaskDiffParams countCostTaskDiffParams = params.getParams();
	        if(countCostTaskDiffParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTask2 scmInvCountingTask= new ScmInvCountingTask2();
	        scmInvCountingTask.setTaskNo(countCostTaskDiffParams.getTaskNo());
	        scmInvCountingTask.setShowSum(countCostTaskDiffParams.isShowSum());
	        List<ScmInvCountCostTaskDiff> scmInvCountCostTaskDiffList = scmInvCountingTaskBiz.queryCountCostTaskDiff(scmInvCountingTask, ParamHelper.createParam(integratedRequest,"queryCountCostTaskDiff"));
	        if(scmInvCountCostTaskDiffList!=null && !scmInvCountCostTaskDiffList.isEmpty()){
	        	List<CountCostTaskDiffResult> resultList = new ArrayList<>();
	        	for(ScmInvCountCostTaskDiff scmInvCountCostTaskDiff:scmInvCountCostTaskDiffList){
	        		CountCostTaskDiffResult countCostTaskDiffResult = new CountCostTaskDiffResult();
	        		BeanUtils.copyProperties(scmInvCountCostTaskDiff, countCostTaskDiffResult);
	        		countCostTaskDiffResult.setUnit(scmInvCountCostTaskDiff.getUnitName());
	        		countCostTaskDiffResult.setPieUnit(scmInvCountCostTaskDiff.getPieUnitName());
	        		resultList.add(countCostTaskDiffResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查看盘存差异失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountCostMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="获取盘存可用物资列表", consumes="application/json",position=4)
    public CountCostMaterialListResultApi queryCountCostMaterialList(@RequestBody CountCostMaterialListParamsApi params) {
		CountCostMaterialListResultApi result = new CountCostMaterialListResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum!=null && pageNum.getPageNum()!=0){
        	pageIndex = pageNum.getPageNum();
        }

        try {
        	SecurityUtils.verify(integratedRequest);
        	CountCostMaterialListParams countCostMaterialListParams = params.getParams();
        	if(countCostMaterialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(StringUtils.isBlank(countCostMaterialListParams.getDeptNo())){
    		    	result.setErrCode("-1");
    		    	result.setErrText("盘存部门不能为空!");
    	    	}
    	    	List<ScmMaterial2> scmMaterialList = scmInvCountingTaskBiz.queryCountCostMaterialList(countCostMaterialListParams, pageIndex,ParamHelper.createParam(integratedRequest,"queryCountCostMaterialList"));
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<CountCostMaterialListResult> resultList = new ArrayList<>();
    	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
    	    			CountCostMaterialListResult countCostMaterialListResult = new CountCostMaterialListResult();
    	    			BeanUtils.copyProperties(scmMaterial, countCostMaterialListResult);
    	    			resultList.add(countCostMaterialListResult);
    	    		}
    	    		result.setResultList(resultList);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取仓库可用物资列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryDeptList", method=RequestMethod.POST)
    @ApiOperation(value="查询部门列表", consumes="application/json")
    public DeptListResultApi queryDeptList(@RequestBody DeptListParamsApi params) {
		DeptListResultApi result = new DeptListResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	DeptListParams deptListParams = params.getParams();
	        if(deptListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        int pageIndex = -1;
	        PageNum pageNum = params.getPageNum();
	        if(pageNum!=null && pageNum.getPageNum()!=0){
	        	pageIndex = pageNum.getPageNum();
	        }
	        OrgAdmin2 orgAdmin2= new OrgAdmin2();
	        orgAdmin2.setOrgUnitName(deptListParams.getDeptName());
	        List<OrgAdmin2> orgAdminList = scmInvCountingTaskBiz.queryDeptList(orgAdmin2,pageIndex, ParamHelper.createParam(integratedRequest,"queryDeptList"));
	        if(orgAdminList!=null && !orgAdminList.isEmpty()){
	        	List<DeptListResult> resultList = new ArrayList<>();
	        	for(OrgAdmin2 orgAdmin:orgAdminList){
	        		DeptListResult deptListResult = new DeptListResult();
	        		deptListResult.setDeptNo(orgAdmin.getOrgUnitNo());
	        		deptListResult.setDeptName(orgAdmin.getOrgUnitName());
	        		resultList.add(deptListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询部门列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
}
