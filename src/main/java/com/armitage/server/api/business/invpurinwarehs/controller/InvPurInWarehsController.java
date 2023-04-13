package com.armitage.server.api.business.invpurinwarehs.controller;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsResult;
import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsResultApi;
import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsToFinResult;
import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsToFinResultResultApi;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsAuditParams;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsAuditParamsApi;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsParams;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsParamsApi;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsUnAuditParams;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsUnAuditParamsApi;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsToFinParams;
import com.armitage.server.api.business.invpurinwarehs.params.InvPurInWarehsToFinParamsApi;
import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsAuditResultApi;
import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsDetailResult;
import com.armitage.server.api.business.invpurinwarehs.result.InvPurInWarehsUnAuditResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iars.basedata.model.PeriodCalendar;
import com.armitage.server.iars.basedata.service.PeriodCalendarBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvPurInWarehsBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.system.model.SystemState;
import com.armitage.server.system.service.SystemStateBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/invPurInWarehs")
@Api(tags="采购入库业务接口")
public class InvPurInWarehsController {
	private static Log log = LogFactory.getLog(InvPurInWarehsController.class);
	private ScmInvPurInWarehsBillBiz scmInvPurInWarehsBillBiz = (ScmInvPurInWarehsBillBiz) AppContextUtil.getBean("scmInvPurInWarehsBillBiz");
	private PeriodCalendarBiz periodCalendarBiz = (PeriodCalendarBiz) AppContextUtil.getBean("periodCalendarBiz");
	private SystemStateBiz systemStateBiz = (SystemStateBiz) AppContextUtil.getBean("systemStateBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvPurInWarehs", method=RequestMethod.POST)
    @ApiOperation(value="获取采购入库单详情", consumes="application/json",position=1)
    public InvPurInWarehsResultApi queryPurOrder(@RequestBody InvPurInWarehsParamsApi params) {
		InvPurInWarehsResultApi result = new InvPurInWarehsResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvPurInWarehsParams purInvPurInWarehsParams = params.getParams();
	        if(purInvPurInWarehsParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvPurInWarehsBill2 scmInvPurInWarehs= new ScmInvPurInWarehsBill2();
	        scmInvPurInWarehs.setWrNo(purInvPurInWarehsParams.getWrNo());
	        ScmInvPurInWarehsBill2 rtn = scmInvPurInWarehsBillBiz.queryInvPurInWarehs(scmInvPurInWarehs, ParamHelper.createParam(integratedRequest,"queryInvPurInWarehs"));
	        if(rtn!=null){
	        	InvPurInWarehsResult invPurInWarehsResult = new InvPurInWarehsResult();
	        	BeanUtils.copyProperties(rtn, invPurInWarehsResult);
	        	List<ScmInvPurInWarehsBillEntry2> scmInvPurInWarehsBillEntryList = rtn.getScmInvPurInWarehsBillEntryList();
	        	if(scmInvPurInWarehsBillEntryList!=null && !scmInvPurInWarehsBillEntryList.isEmpty()){
	        		List<InvPurInWarehsDetailResult> detailList = new ArrayList<InvPurInWarehsDetailResult>();
	        		for(ScmInvPurInWarehsBillEntry2 scmInvPurInWarehsEntry:scmInvPurInWarehsBillEntryList){
	        			InvPurInWarehsDetailResult InvPurInWarehsDetailResult = new InvPurInWarehsDetailResult();
	        			BeanUtils.copyProperties(scmInvPurInWarehsEntry, InvPurInWarehsDetailResult);
	        			InvPurInWarehsDetailResult.setUnit(scmInvPurInWarehsEntry.getUnitName());
	        			detailList.add(InvPurInWarehsDetailResult);
	        		}
	        		invPurInWarehsResult.setDetailList(detailList);
	        	}
	        	result.setResult(invPurInWarehsResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取订货单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditInvPurInWarehs", method=RequestMethod.POST)
    @ApiOperation(value="审批采购入库单", consumes="application/json",position=2)
    public InvPurInWarehsAuditResultApi doAuditInvPurInWarehs(@RequestBody InvPurInWarehsAuditParamsApi params) {
    	InvPurInWarehsAuditResultApi result = new InvPurInWarehsAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvPurInWarehsAuditParams invPurInWarehsAuditParams = params.getParams();
	        if(invPurInWarehsAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(invPurInWarehsAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(invPurInWarehsAuditParams.getWrNo());
	        scmInvPurInWarehsBillBiz.doAuditInvPurInWarehs(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditInvPurInWarehs"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("采购入库单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditInvPurInWarehs", method=RequestMethod.POST)
    @ApiOperation(value="取消审批采购入库单", consumes="application/json",position=3)
    public InvPurInWarehsUnAuditResultApi doUnAuditInvPurInWarehs(@RequestBody InvPurInWarehsUnAuditParamsApi params) {
    	InvPurInWarehsUnAuditResultApi result = new InvPurInWarehsUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvPurInWarehsUnAuditParams invPurInWarehsUnAuditParams = params.getParams();
	        if(invPurInWarehsUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmInvPurInWarehsBill2 scmInvPurInWarehs = new ScmInvPurInWarehsBill2();
	        scmInvPurInWarehs.setWrNo(invPurInWarehsUnAuditParams.getWrNo());
	        scmInvPurInWarehsBillBiz.doUnAuditInvPurInWarehs(scmInvPurInWarehs, ParamHelper.createParam(integratedRequest,"doUnAuditInvPurInWarehs"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("采购入库单取消审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="finApi")
    @ResponseBody
    @RequestMapping(value="/queryInvPurInWarehsForFin", method=RequestMethod.POST)
    @ApiOperation(value="获取采购入库财务数据", consumes="application/json")
	 public InvPurInWarehsToFinResultResultApi queryInvPurInWarehsForFin(@RequestBody InvPurInWarehsToFinParamsApi params) {
		InvPurInWarehsToFinResultResultApi result = new InvPurInWarehsToFinResultResultApi();//显示的结果集
    	ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
         try {
         	SecurityUtils.verify(integratedRequest);
        	InvPurInWarehsToFinParams invPurInWarehsToFinParams = params.getParams();
        	if(invPurInWarehsToFinParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	Page page = new Page();
				page.setModelClass(ScmInvPurInWarehsBill2.class);
				page.setShowCount(Integer.MAX_VALUE);
				List<String> arglist = new ArrayList<>();
			    Param param=ParamHelper.createParam(integratedRequest,"queryInvPurInWarehsForFin");
			    PeriodCalendar periodCalendar = periodCalendarBiz.selectByBizdate(FormatUtils.parseDate(invPurInWarehsToFinParams.getBegDate()), param);
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
				arglist.add("finOrgUnitNo="+invPurInWarehsToFinParams.getFinOrgUnitNo());
				arglist.add("summaryLevel="+invPurInWarehsToFinParams.getSummaryLevel());
				if (StringUtils.isNotBlank(invPurInWarehsToFinParams.getBizType())){
					String bizType[] = StringUtils.split(invPurInWarehsToFinParams.getBizType(), ",");
					StringBuffer bizTypeBuffer = new StringBuffer("");
					for(String biz:bizType){
						if(StringUtils.isNotBlank(bizTypeBuffer.toString()))
							bizTypeBuffer.append(",");
						bizTypeBuffer.append("'").append(biz).append("'");
					}
					arglist.add("bizType="+bizTypeBuffer.toString());
				}
				arglist.add("HoudeIdOrUnitNo="+invPurInWarehsToFinParams.getHoudeIdOrUnitNo());
				String status[] = StringUtils.split(invPurInWarehsToFinParams.getStatus(), ",");
				StringBuffer statusBuffer = new StringBuffer("");
				for(String sta:status){
					if(StringUtils.isNotBlank(statusBuffer.toString()))
						statusBuffer.append(",");
					statusBuffer.append("'").append(sta).append("'");
				}
				arglist.add("status="+statusBuffer.toString());
				arglist.add("begDate="+invPurInWarehsToFinParams.getBegDate());
				arglist.add("endDate="+invPurInWarehsToFinParams.getEndDate());
    	    	List<ScmInvPurInWarehsBill2> purchaseList=scmInvPurInWarehsBillBiz.queryPage(page, arglist, "queryForFinSystem", param);
    	    	if (purchaseList !=null && !purchaseList.isEmpty()) {
    	    		List< InvPurInWarehsToFinResult > resultList=new ArrayList<>();
    	    		for (ScmInvPurInWarehsBill2 scmInvPurInWarehsBill2 : purchaseList) {
    	    			InvPurInWarehsToFinResult purchasingSystemResult=new InvPurInWarehsToFinResult();
    	    			purchasingSystemResult.setFinOrgUnitNo(scmInvPurInWarehsBill2.getFinOrgUnitNo());
    	    			purchasingSystemResult.setBizType(scmInvPurInWarehsBill2.getBizType());
    	    			purchasingSystemResult.setVendorNo(scmInvPurInWarehsBill2.getVendorNo());
    	    			purchasingSystemResult.setClassCode(scmInvPurInWarehsBill2.getClassCode());
    	    			purchasingSystemResult.setWhNo(scmInvPurInWarehsBill2.getWhNo());
    	    			purchasingSystemResult.setUseOrgUnitNo(scmInvPurInWarehsBill2.getUseOrgUnitNo());
    	    			if(scmInvPurInWarehsBill2.getBizType().equals("1")||scmInvPurInWarehsBill2.getBizType().equals("2")||scmInvPurInWarehsBill2.getBizType().equals("3")
    	    					||scmInvPurInWarehsBill2.getBizType().equals("13")){
    	    				purchasingSystemResult.setAmt(scmInvPurInWarehsBill2.getAmt());
    	    				purchasingSystemResult.setTaxAmt((scmInvPurInWarehsBill2.getTaxAmt().subtract(scmInvPurInWarehsBill2.getAmt())).setScale(2, RoundingMode.HALF_UP));
    	    			}else {
    	    				purchasingSystemResult.setAmt(scmInvPurInWarehsBill2.getAmt().negate());
    	    				purchasingSystemResult.setTaxAmt((scmInvPurInWarehsBill2.getTaxAmt().negate()).subtract((scmInvPurInWarehsBill2.getAmt().negate())).setScale(2, RoundingMode.HALF_UP));
						}
    	    			purchasingSystemResult.setTotalAmt(purchasingSystemResult.getAmt().add(purchasingSystemResult.getTaxAmt()).setScale(2, RoundingMode.HALF_UP));
    	    			resultList.add(purchasingSystemResult);
    				}
    	    	
    	    		List<InvPurInWarehsToFinResult> datalist = new ArrayList<>();
    	    		
    	    		for (InvPurInWarehsToFinResult purchasingSystemResult : resultList) {
    	    			if (datalist.isEmpty()) {
							datalist.add(purchasingSystemResult);
							continue;
						}
    	    			int i= 0;
    	    			for (InvPurInWarehsToFinResult pSystemResult : datalist) {
    	    				if (StringUtils.isNotBlank(pSystemResult.getUseOrgUnitNo()) && StringUtils.isNotBlank(purchasingSystemResult.getUseOrgUnitNo())) {
    	    					if (pSystemResult.getVendorNo().equals(purchasingSystemResult.getVendorNo())
    								&& pSystemResult.getClassCode().equals(purchasingSystemResult.getClassCode())
    								&& pSystemResult.getUseOrgUnitNo().equals(purchasingSystemResult.getUseOrgUnitNo())) {
    	    					
    	    						pSystemResult.setAmt(pSystemResult.getAmt().add(purchasingSystemResult.getAmt()).setScale(2, RoundingMode.HALF_UP));
    	    						pSystemResult.setTaxAmt(pSystemResult.getTaxAmt().add(purchasingSystemResult.getTaxAmt()).setScale(2, RoundingMode.HALF_UP));
    	    						pSystemResult.setTotalAmt(pSystemResult.getTotalAmt().add(purchasingSystemResult.getTotalAmt()).setScale(2, RoundingMode.HALF_UP));
    							
    	    						i = 1;	
    	    						break;
    	    					}
    	    				}else if(StringUtils.isNotBlank(pSystemResult.getWhNo()) && StringUtils.isNotBlank(purchasingSystemResult.getWhNo())) {
								if (pSystemResult.getVendorNo().equals(purchasingSystemResult.getVendorNo())
        								&& pSystemResult.getClassCode().equals(purchasingSystemResult.getClassCode())
        								&& pSystemResult.getWhNo().equals(purchasingSystemResult.getWhNo())) {
        	    					
        	    					pSystemResult.setAmt(pSystemResult.getAmt().add(purchasingSystemResult.getAmt()).setScale(2, RoundingMode.HALF_UP));
        							pSystemResult.setTaxAmt(pSystemResult.getTaxAmt().add(purchasingSystemResult.getTaxAmt()).setScale(2, RoundingMode.HALF_UP));
        							pSystemResult.setTotalAmt(pSystemResult.getTotalAmt().add(purchasingSystemResult.getTotalAmt()).setScale(2, RoundingMode.HALF_UP));
        							
        							i = 1;	
        							break;
								}
							}
    	    			}
    	    			
    	    			if (i == 0 ) {
							datalist.add(purchasingSystemResult);
						}
    	    		}
    	    		result.setResultList(datalist);
    	    	}
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("获取采购入库财务数据:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
		
		return result;
	}
}
