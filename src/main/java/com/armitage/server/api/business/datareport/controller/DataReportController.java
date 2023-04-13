package com.armitage.server.api.business.datareport.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.armitage.server.api.business.datareport.params.DeptConsumeEntryDataParams;
import com.armitage.server.api.business.datareport.params.DeptConsumeEntryDataParamsApi;
import com.armitage.server.api.business.datareport.params.InvRealTimeStockParams;
import com.armitage.server.api.business.datareport.params.InvRealTimeStockParamsApi;
import com.armitage.server.api.business.datareport.params.SupSupplyOfMaterialDetailsParams;
import com.armitage.server.api.business.datareport.params.SupSupplyOfMaterialDetailsParamsApi;
import com.armitage.server.api.business.datareport.result.CostFinConsumeDetailsResult;
import com.armitage.server.api.business.datareport.result.CostFinConsumeDetailsResultApi;
import com.armitage.server.api.business.datareport.result.DeptConsumeEntryDataResult;
import com.armitage.server.api.business.datareport.result.DeptConsumeEntryDataResultApi;
import com.armitage.server.api.business.datareport.result.InvRealTimeStockResult;
import com.armitage.server.api.business.datareport.result.InvRealTimeStockResultApi;
import com.armitage.server.api.business.datareport.result.SupSupplyOfMaterialDetailsResult;
import com.armitage.server.api.business.datareport.result.SupSupplyOfMaterialDetailsResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmCostFinDeptConsumeQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmDeptConsumeQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmFinDeptConsume;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvRealtimeStockQuery;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.report.costcenter.service.ScmCostReportBiz;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.SystemStateBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/dataReport")
@Api(tags="数据查询接口")
public class DataReportController {
	private static Log log = LogFactory.getLog(DataReportController.class);
    private ScmInvStockBiz scmInvStockBiz = (ScmInvStockBiz) AppContextUtil.getBean("scmInvStockBiz");
    private PeriodCalendarBiz periodCalendarBiz = (PeriodCalendarBiz) AppContextUtil.getBean("periodCalendarBiz");
    private SystemStateBiz systemStateBiz = (SystemStateBiz) AppContextUtil.getBean("systemStateBiz");
    private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz = (ScmInvPurInWarehsBillBiz) AppContextUtil.getBean("scmInvPurInWarehsBillBiz");
    private ScmCostReportBiz scmCostReportBiz = (ScmCostReportBiz) AppContextUtil.getBean("scmCostReportBiz");
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryRealTimeStock", method=RequestMethod.POST)
    @ApiOperation(value="查询即时结存", consumes="application/json")
    public InvRealTimeStockResultApi queryRealTimeStock(@RequestBody InvRealTimeStockParamsApi params) {
		InvRealTimeStockResultApi result = new InvRealTimeStockResultApi();//显示的结果集
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
        	InvRealTimeStockParams invRealTimeStockParams = params.getParams();
	        if(invRealTimeStockParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryCountCostTaskList");
	        ScmInvRealtimeStockQuery scmInvRealtimeStockQuery = new ScmInvRealtimeStockQuery();
	        BeanUtils.copyProperties(invRealTimeStockParams, scmInvRealtimeStockQuery);
	        scmInvRealtimeStockQuery.setOrgUnitNo(param.getOrgUnitNo());
	        List<ScmInvStock2> ScmInvStockList = scmInvStockBiz.selectRealtimeStock(scmInvRealtimeStockQuery,pageIndex,param);
	        if(ScmInvStockList!=null && !ScmInvStockList.isEmpty()){
	        	List< InvRealTimeStockResult > resultList = new ArrayList();
	        	for(ScmInvStock2 scmInvStock:ScmInvStockList){
	        		InvRealTimeStockResult invRealTimeStockResult = new InvRealTimeStockResult();
	        		BeanUtils.copyProperties(scmInvStock, invRealTimeStockResult);
	        		invRealTimeStockResult.setWareHouseName(scmInvStock.getWhName());
	        		resultList.add(invRealTimeStockResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询即时结存失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/queryFinConsume", method=RequestMethod.POST)
    @ApiOperation(value="查询部门耗用", consumes="application/json")
    public DeptConsumeEntryDataResultApi queryFinConsume(@RequestBody DeptConsumeEntryDataParamsApi params) {
		DeptConsumeEntryDataResultApi result = new DeptConsumeEntryDataResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	DeptConsumeEntryDataParams deptConsumeEntryDataParams = params.getParams();
	        if(deptConsumeEntryDataParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryFinConsume");
	        ScmDeptConsumeQuery scmDeptConsumeQuery = new ScmDeptConsumeQuery();
	        BeanUtils.copyProperties(deptConsumeEntryDataParams, scmDeptConsumeQuery);
	        PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmDeptConsumeQuery.getBegDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.api.business.datareport.queryFinConsume.selectByBizdate.error");
			}
			SystemState systemState = new SystemState(true);
			systemState.setOrgUnitNo(param.getOrgUnitNo());
			systemState.setSystemid(170L);
			systemState = systemStateBiz.selectBySystemId(systemState, param);
			if(systemState!=null && periodCalendar!=null){
				if(periodCalendar.getPeriodId()>=systemState.getCurrentPeriodId()){
					throw new AppException("com.armitage.api.business.datareport.queryFinConsume.periodNotOver.error");
				}
			}
			scmDeptConsumeQuery.setBegDate(periodCalendar.getStartDate());
			String endDate = FormatUtils.fmtDate(periodCalendar.getEndDate()) + " 23:59:59";
			scmDeptConsumeQuery.setEndDate(FormatUtils.parseDateTime(endDate));
	        List<ScmFinDeptConsume> scmFinDeptConsumeList = scmInvStockBiz.selectFinDeptConsume(scmDeptConsumeQuery, param);
	        if(scmFinDeptConsumeList!=null && !scmFinDeptConsumeList.isEmpty()){
	        	List<DeptConsumeEntryDataResult> resultList = new ArrayList();
	        	for(ScmFinDeptConsume scmFinDeptConsume : scmFinDeptConsumeList){
	        		DeptConsumeEntryDataResult deptConsumeEntryDataResult = new DeptConsumeEntryDataResult();
	        		BeanUtils.copyProperties(scmFinDeptConsume, deptConsumeEntryDataResult);
	        		deptConsumeEntryDataResult.setFinOrgUnitNo(deptConsumeEntryDataParams.getFinOrgUnitNo());
	        		resultList.add(deptConsumeEntryDataResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询部门耗用失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/querySupSupplyOfMaterialDetails", method=RequestMethod.POST)
    @ApiOperation(value="供应商供货明细接口", consumes="application/json")
    public SupSupplyOfMaterialDetailsResultApi querySupSupplyOfMaterialDetails(@RequestBody SupSupplyOfMaterialDetailsParamsApi params) {
		SupSupplyOfMaterialDetailsResultApi result = new SupSupplyOfMaterialDetailsResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	SupSupplyOfMaterialDetailsParams supplyOfMaterialDetailsParams = params.getParams();
	        if(supplyOfMaterialDetailsParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"querySupSupplyOfMaterialDetails");
			List<ScmInvPurInWarehsBill2> purchaseList=scmInvPurInWarehsBillBiz.querySupSupplyOfMaterialDetails(supplyOfMaterialDetailsParams,param);
			if (purchaseList != null) {
				List<SupSupplyOfMaterialDetailsResult> resultList = new ArrayList();
				for (ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 : purchaseList) {
					SupSupplyOfMaterialDetailsResult supSupplyOfMaterialDetailsResult = new SupSupplyOfMaterialDetailsResult();
					BeanUtils.copyProperties(scmInvPurInWarehsBill2, supSupplyOfMaterialDetailsResult);
					supSupplyOfMaterialDetailsResult.setWareHouseName(scmInvPurInWarehsBill2.getWhName());
					supSupplyOfMaterialDetailsResult.setUseOrgUnitNoName(scmInvPurInWarehsBill2.getUseOrgUnitName());
					supSupplyOfMaterialDetailsResult.setUnit(scmInvPurInWarehsBill2.getUnitName());
					supSupplyOfMaterialDetailsResult.setUseOrgUnitNo(scmInvPurInWarehsBill2.getUseOrgUnitNo());
					supSupplyOfMaterialDetailsResult.setItemGroupName(scmInvPurInWarehsBill2.getMaterialClassName());
					supSupplyOfMaterialDetailsResult.setCreator(scmInvPurInWarehsBill2.getCreatorName());
					resultList.add(supSupplyOfMaterialDetailsResult);
				}
				result.setResultList(resultList);
			}
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询供应商供货明细失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
    	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/queryCostFinConsume", method=RequestMethod.POST)
    @ApiOperation(value="成本中心用途耗用分析汇总", consumes="application/json")
    public CostFinConsumeDetailsResultApi queryCostFinConsume(@RequestBody DeptConsumeEntryDataParamsApi params) {
		CostFinConsumeDetailsResultApi result = new CostFinConsumeDetailsResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	DeptConsumeEntryDataParams deptConsumeEntryDataParams = params.getParams();
	        if(deptConsumeEntryDataParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"queryFinConsume");
	        ScmCostFinDeptConsumeQuery scmDeptConsumeQuery = new ScmCostFinDeptConsumeQuery();
	        BeanUtils.copyProperties(deptConsumeEntryDataParams, scmDeptConsumeQuery);
	        PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(scmDeptConsumeQuery.getBegDate(), param);
			if(periodCalendar==null){
				throw new AppException("com.armitage.api.business.datareport.queryFinConsume.selectByBizdate.error");
			}
			SystemState systemState = new SystemState(true);
			systemState.setOrgUnitNo(param.getOrgUnitNo());
			systemState.setSystemid(170L);
			systemState = systemStateBiz.selectBySystemId(systemState, param);
			if(systemState!=null && periodCalendar!=null){
				if(periodCalendar.getPeriodId()>=systemState.getCurrentPeriodId()){
					throw new AppException("com.armitage.api.business.datareport.queryFinConsume.periodNotOver.error");
				}
			}
			scmDeptConsumeQuery.setBegDate(periodCalendar.getStartDate());
			String endDate = FormatUtils.fmtDate(periodCalendar.getEndDate()) + " 23:59:59";
			scmDeptConsumeQuery.setEndDate(FormatUtils.parseDateTime(endDate));
			scmDeptConsumeQuery.setFromApi(true);
	        List<ScmCostFinDeptConsume> scmFinDeptConsumeList = scmCostReportBiz.selectCostFinConsume(scmDeptConsumeQuery, param);
	        if(scmFinDeptConsumeList!=null && !scmFinDeptConsumeList.isEmpty()){
	        	List< CostFinConsumeDetailsResult > resultList = new ArrayList();
	        	for(ScmCostFinDeptConsume scmCostFinDeptConsume : scmFinDeptConsumeList){
	        		CostFinConsumeDetailsResult deptConsumeEntryDataResult = new CostFinConsumeDetailsResult();
	        		BeanUtils.copyProperties(scmCostFinDeptConsume, deptConsumeEntryDataResult);
	        		deptConsumeEntryDataResult.setFinOrgUnitNo(deptConsumeEntryDataParams.getFinOrgUnitNo());
	        		deptConsumeEntryDataResult.setAmt(scmCostFinDeptConsume.getCostAmt());
	        		deptConsumeEntryDataResult.setTaxAmt(scmCostFinDeptConsume.getCostTax());
	        		deptConsumeEntryDataResult.setTotalAmt(scmCostFinDeptConsume.getCostTaxAmt());
	        		resultList.add(deptConsumeEntryDataResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("查询成本中心用途耗用分析汇总失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
	}
}
