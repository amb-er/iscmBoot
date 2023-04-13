package com.armitage.server.api.business.invcounttask.controller;

import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.business.basedata.params.WareHouseParams;
import com.armitage.server.api.business.basedata.result.WareHouseResult;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiLogicSymbol;
import com.armitage.server.api.common.ApiVersion;
import com.armitage.server.api.common.PageNum;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.invcounttask.params.CountInvMaterialListParams;
import com.armitage.server.api.business.invcounttask.params.CountInvMaterialListParamsApi;
import com.armitage.server.api.business.invcounttask.params.CountInvTableParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTableParamsApi;
import com.armitage.server.api.business.invcounttask.params.CountInvTableSaveParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTableSaveParamsApi;
import com.armitage.server.api.business.invcounttask.params.CountInvTaskDiffParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTaskDiffParamsApi;
import com.armitage.server.api.business.invcounttask.params.CountInvTaskListParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTaskListParamsApi;
import com.armitage.server.api.business.invcounttask.params.CountInvTaskWareHouseParams;
import com.armitage.server.api.business.invcounttask.params.CountInvTaskWareHouseParamsApi;
import com.armitage.server.api.business.invcounttask.result.CountInvMaterialListResult;
import com.armitage.server.api.business.invcounttask.result.CountInvMaterialListResultApi;
import com.armitage.server.api.business.invcounttask.result.CountInvTableDetailResult;
import com.armitage.server.api.business.invcounttask.result.CountInvTableResult;
import com.armitage.server.api.business.invcounttask.result.CountInvTableResultApi;
import com.armitage.server.api.business.invcounttask.result.CountInvTableSaveResultApi;
import com.armitage.server.api.business.invcounttask.result.CountInvTaskDiffResult;
import com.armitage.server.api.business.invcounttask.result.CountInvTaskDiffResultApi;
import com.armitage.server.api.business.invcounttask.result.CountInvTaskListResult;
import com.armitage.server.api.business.invcounttask.result.CountInvTaskListResultApi;
import com.armitage.server.api.business.invcounttask.result.CountInvTaskWareHouseResult;
import com.armitage.server.api.business.invcounttask.result.CountInvTaskWareHouseResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountInvTaskDiff;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTaskAdvQuery;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/invCountTask")
@Api(tags="盘点接口")
public class InvCountTaskController {
	private static Log log = LogFactory.getLog(InvCountTaskController.class);
	private ScmInvCountingTaskBiz scmInvCountingTaskBiz = (ScmInvCountingTaskBiz) AppContextUtil.getBean("scmInvCountingTaskBiz");
	private ScmInvWareHouseBiz scmInvWareHouseBiz = (ScmInvWareHouseBiz) AppContextUtil.getBean("scmInvWareHouseBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountInvTaskList", method=RequestMethod.POST)
    @ApiOperation(value="获取盘点列表", consumes="application/json")
    public CountInvTaskListResultApi queryCountInvTaskList(@RequestBody CountInvTaskListParamsApi params) {
		CountInvTaskListResultApi result = new CountInvTaskListResultApi();//显示的结果集
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
        	CountInvTaskListParams countInvTaskListParams = params.getParams();
	        if(countInvTaskListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTaskAdvQuery scmInvCountingTaskAdvQuery = new ScmInvCountingTaskAdvQuery();
	        BeanUtils.copyProperties(countInvTaskListParams, scmInvCountingTaskAdvQuery);
	        scmInvCountingTaskAdvQuery.setCostCenter(false);
	        scmInvCountingTaskAdvQuery.setFromInterface(true);
	        List<ScmInvCountingTask2> scmInvCountingTaskList = scmInvCountingTaskBiz.queryCountCostTaskList(scmInvCountingTaskAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryCountInvTaskList"));
	        if(scmInvCountingTaskList!=null && !scmInvCountingTaskList.isEmpty()){
	        	List< CountInvTaskListResult > resultList = new ArrayList<CountInvTaskListResult>();
	        	for(ScmInvCountingTask2 scmInvCountingTask:scmInvCountingTaskList){
	        		CountInvTaskListResult countInvTaskListResult = new CountInvTaskListResult();
	        		BeanUtils.copyProperties(scmInvCountingTask, countInvTaskListResult);
	        		resultList.add(countInvTaskListResult);
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
    @RequestMapping(value="/queryCountInvTaskWareHouseList", method=RequestMethod.POST)
    @ApiOperation(value="查询盘点任务中仓库", consumes="application/json")
    public CountInvTaskWareHouseResultApi queryCountInvTaskWareHouseList(@RequestBody CountInvTaskWareHouseParamsApi params) {
		CountInvTaskWareHouseResultApi result = new CountInvTaskWareHouseResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountInvTaskWareHouseParams countInvTaskWareHouseParams = params.getParams();
	        if(countInvTaskWareHouseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTask2 scmInvCountingTask= new ScmInvCountingTask2();
	        scmInvCountingTask.setTaskNo(countInvTaskWareHouseParams.getTaskNo());
	        List<ScmInvWareHouse> scmInvWareHouseList = scmInvCountingTaskBiz.queryCountInvTaskWareHouseList(scmInvCountingTask, ParamHelper.createParam(integratedRequest,"queryCountInvTaskWareHouseList"));
	        if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
	        	List< CountInvTaskWareHouseResult > resultList = new ArrayList<>();
	        	for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
	        		CountInvTaskWareHouseResult countInvTaskWareHouseResult = new CountInvTaskWareHouseResult();
	        		countInvTaskWareHouseResult.setWareHouseNo(scmInvWareHouse.getWhNo());
	        		countInvTaskWareHouseResult.setWareHouseName(scmInvWareHouse.getWhName());
	        		resultList.add(countInvTaskWareHouseResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询盘点仓库失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountInvTable", method=RequestMethod.POST)
    @ApiOperation(value="查询盘点表", consumes="application/json")
    public CountInvTableResultApi queryCountInvTable(@RequestBody CountInvTableParamsApi params) {
		CountInvTableResultApi result = new CountInvTableResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountInvTableParams countInvTableParams = params.getParams();
	        if(countInvTableParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTask2 scmInvCountingTask= new ScmInvCountingTask2();
	        scmInvCountingTask.setTaskNo(countInvTableParams.getTaskNo());
	        scmInvCountingTask.setWareHouseNo(countInvTableParams.getWareHouseNo());
	        ScmInvCountingTable2 scmInvCountingTable = scmInvCountingTaskBiz.queryCountInvTable(scmInvCountingTask, ParamHelper.createParam(integratedRequest,"queryCountCostTable"));
	        if(scmInvCountingTable!=null){
	        	CountInvTableResult countInvTableResult = new CountInvTableResult();
	        	BeanUtils.copyProperties(scmInvCountingTable, countInvTableResult);
	        	countInvTableResult.setWareHouseName(scmInvCountingTable.getWhName());
	        	List<CountInvTableDetailResult> detailList = new ArrayList<>();
	        	List<ScmInvCountingTableEntry2> scmInvCountingTableEntryList = scmInvCountingTable.getScmInvCountingTableEntryList();
	        	if(scmInvCountingTableEntryList!=null && !scmInvCountingTableEntryList.isEmpty()){
	        		for(ScmInvCountingTableEntry2 scmInvCountingTableEntry:scmInvCountingTableEntryList){
	        			CountInvTableDetailResult countInvTableDetailResult = new CountInvTableDetailResult();
	        			BeanUtils.copyProperties(scmInvCountingTableEntry, countInvTableDetailResult);
	        			countInvTableDetailResult.setUnit(scmInvCountingTableEntry.getUnitName());
	        			countInvTableDetailResult.setPieUnit(scmInvCountingTableEntry.getPieUnitName());
	        			detailList.add(countInvTableDetailResult);
	        		}
	        	}
	        	countInvTableResult.setDetailList(detailList);
	        	result.setResult(countInvTableResult);
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
    @RequestMapping(value="/doSaveCountInvTable", method=RequestMethod.POST)
    @ApiOperation(value="盘存表保存单", consumes="application/json")
    public CountInvTableSaveResultApi doSaveCountInvTable(@RequestBody CountInvTableSaveParamsApi params) {
		CountInvTableSaveResultApi result = new CountInvTableSaveResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountInvTableSaveParams countInvTableSaveParams = params.getParams();
	        if(countInvTableSaveParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        scmInvCountingTaskBiz.doSaveCountInvTable(countInvTableSaveParams, ParamHelper.createParam(integratedRequest,"doSaveCountInvTable"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("盘存表保存单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountInvTaskDiff", method=RequestMethod.POST)
    @ApiOperation(value="查看盘点差异", consumes="application/json")
    public CountInvTaskDiffResultApi queryCountInvTaskDiff(@RequestBody CountInvTaskDiffParamsApi params) {
		CountInvTaskDiffResultApi result = new CountInvTaskDiffResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CountInvTaskDiffParams countInvTaskDiffParams = params.getParams();
	        if(countInvTaskDiffParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvCountingTask2 scmInvCountingTask= new ScmInvCountingTask2(true);
	        scmInvCountingTask.setTaskNo(countInvTaskDiffParams.getTaskNo());
	        scmInvCountingTask.setShowSum(countInvTaskDiffParams.isShowSum());
	        List<ScmInvCountInvTaskDiff> scmInvCountInvTaskDiffList = scmInvCountingTaskBiz.queryCountInvTaskDiff(scmInvCountingTask, ParamHelper.createParam(integratedRequest,"queryCountInvTaskDiff"));
	        if(scmInvCountInvTaskDiffList!=null && !scmInvCountInvTaskDiffList.isEmpty()){
	        	List<CountInvTaskDiffResult> resultList = new ArrayList<>();
	        	for(ScmInvCountInvTaskDiff scmInvCountInvTaskDiff:scmInvCountInvTaskDiffList){
	        		CountInvTaskDiffResult countInvTaskDiffResult = new CountInvTaskDiffResult();
	        		BeanUtils.copyProperties(scmInvCountInvTaskDiff, countInvTaskDiffResult);
	        		countInvTaskDiffResult.setPieUnit(scmInvCountInvTaskDiff.getPieUnitName());
	        		resultList.add(countInvTaskDiffResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查看盘点差异失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryWareHouseList", method=RequestMethod.POST)
    @ApiOperation(value="查询库存组织仓库", consumes="application/json")
    public com.armitage.server.api.business.basedata.result.WareHouseResultApi queryWareHouseList(@RequestBody com.armitage.server.api.business.basedata.params.WareHouseParamsApi params) {
		com.armitage.server.api.business.basedata.result.WareHouseResultApi result = new com.armitage.server.api.business.basedata.result.WareHouseResultApi();//显示的结果集
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
        	WareHouseParams wareHouseParams = params.getParams();
	        if(wareHouseParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvWareHouse wareHouse= new ScmInvWareHouse();
	        wareHouse.setWhName(wareHouseParams.getWareHouseName());
	        List<ScmInvWareHouse> scmInvWareHouseList = scmInvWareHouseBiz.queryWareHouseList(wareHouse,pageIndex, ParamHelper.createParam(integratedRequest,"queryWareHouseList"));
	        if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty()){
	        	List< com.armitage.server.api.business.basedata.result.WareHouseResult > resultList = new ArrayList<>();
	        	for(ScmInvWareHouse scmInvWareHouse:scmInvWareHouseList){
	        		com.armitage.server.api.business.basedata.result.WareHouseResult wareHouseResult = new WareHouseResult();
	        		wareHouseResult.setWareHouseNo(scmInvWareHouse.getWhNo());
	        		wareHouseResult.setWareHouseName(scmInvWareHouse.getWhName());
	        		resultList.add(wareHouseResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询仓库失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCountInvMaterialList", method=RequestMethod.POST)
    @ApiOperation(value="获取仓库可用物资列表", consumes="application/json",position=4)
    public CountInvMaterialListResultApi queryCountInvMaterialList(@RequestBody CountInvMaterialListParamsApi params) {
		CountInvMaterialListResultApi result = new CountInvMaterialListResultApi();//显示的结果集
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
        	CountInvMaterialListParams countInvMaterialListParams = params.getParams();
        	if(countInvMaterialListParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	List<ScmMaterial2> scmMaterialList = scmInvCountingTaskBiz.queryCountInvMaterialList(countInvMaterialListParams, pageIndex,ParamHelper.createParam(integratedRequest,"queryCountInvMaterialList"));
    	    	if(scmMaterialList!=null && !scmMaterialList.isEmpty()){
    	    		List<CountInvMaterialListResult> resultList = new ArrayList<>();
    	    		for(ScmMaterial2 scmMaterial:scmMaterialList){
    	    			CountInvMaterialListResult purReqMaterialListResult = new CountInvMaterialListResult();
    	    			BeanUtils.copyProperties(scmMaterial, purReqMaterialListResult);
    	    			resultList.add(purReqMaterialListResult);
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
}
