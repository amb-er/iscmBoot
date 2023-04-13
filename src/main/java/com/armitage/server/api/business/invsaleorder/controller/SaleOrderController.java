package com.armitage.server.api.business.invsaleorder.controller;

import com.armitage.server.api.business.invsaleorder.result.InvSaleOrderAuditResultApi;
import com.armitage.server.api.business.invsaleorder.result.InvSaleOrderDetailResult;
import com.armitage.server.api.common.ApiIntegratedRequest;
import com.armitage.server.api.common.ApiVersion;
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

import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderAuditParams;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderAuditParamsApi;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderParams;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderParamsApi;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderUnAuditParams;
import com.armitage.server.api.business.invsaleorder.params.InvSaleOrderUnAuditParamsApi;
import com.armitage.server.api.business.invsaleorder.result.InvSaleOrderResult;
import com.armitage.server.api.business.invsaleorder.result.InvSaleOrderResultApi;
import com.armitage.server.api.business.invsaleorder.result.InvSaleOrderUnAuditResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@Controller
@RequestMapping("/api/invSaleOrder")
@Api(tags="销售订单业务接口")
public class SaleOrderController {
	private static Log log = LogFactory.getLog(SaleOrderController.class);
	private ScmInvSaleOrderBiz scmInvSaleOrderBiz = (ScmInvSaleOrderBiz) AppContextUtil.getBean("scmInvSaleOrderBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvSaleOrder", method=RequestMethod.POST)
    @ApiOperation(value="获取销售订单详情", consumes="application/json",position=1)
    public InvSaleOrderResultApi queryInvSaleOrder(@RequestBody InvSaleOrderParamsApi params) {
		InvSaleOrderResultApi result = new InvSaleOrderResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvSaleOrderParams invSaleOrderParams = params.getParams();
	        if(invSaleOrderParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvSaleOrder2 scmInvSaleOrder= new ScmInvSaleOrder2();
	        scmInvSaleOrder.setOtNo(invSaleOrderParams.getOtNo());
	        ScmInvSaleOrder2 rtn = scmInvSaleOrderBiz.queryInvSaleOrder(scmInvSaleOrder, ParamHelper.createParam(integratedRequest,"queryInvSaleOrder"));
	        if(rtn!=null){
	        	InvSaleOrderResult invSaleOrderResult = new InvSaleOrderResult();
	        	BeanUtils.copyProperties(rtn, invSaleOrderResult);
	        	List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = rtn.getScmInvSaleOrderEntryList();
	        	if(scmInvSaleOrderEntryList != null && !scmInvSaleOrderEntryList.isEmpty()){
	        		List< InvSaleOrderDetailResult > detailList = new ArrayList<InvSaleOrderDetailResult>();
	        		for(ScmInvSaleOrderEntry2 scmInvSaleOrderEntry : scmInvSaleOrderEntryList){
	        			InvSaleOrderDetailResult invSaleOrderDetailResult = new InvSaleOrderDetailResult();
	        			BeanUtils.copyProperties(scmInvSaleOrderEntry, invSaleOrderDetailResult);
	        			invSaleOrderDetailResult.setUnit(scmInvSaleOrderEntry.getUnitName());
	        			detailList.add(invSaleOrderDetailResult);
	        		}
	        		invSaleOrderResult.setDetailList(detailList);
	        	}
	        	result.setResult(invSaleOrderResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取销售订单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditInvSaleOrder", method=RequestMethod.POST)
    @ApiOperation(value="审批销售订单", consumes="application/json",position=2)
    public InvSaleOrderAuditResultApi doAuditInvSaleOrder(@RequestBody InvSaleOrderAuditParamsApi params) {
    	InvSaleOrderAuditResultApi result = new InvSaleOrderAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvSaleOrderAuditParams invSaleOrderAuditParams = params.getParams();
	        if(invSaleOrderAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(invSaleOrderAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(invSaleOrderAuditParams.getOtNo());
	        scmInvSaleOrderBiz.doAuditInvSaleOrder(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditInvSaleOrder"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("销售订单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditInvSaleOrder", method=RequestMethod.POST)
    @ApiOperation(value="取消审批销售订单", consumes="application/json",position=3)
    public InvSaleOrderUnAuditResultApi doUnAuditInvSaleOrder(@RequestBody InvSaleOrderUnAuditParamsApi params) {
    	InvSaleOrderUnAuditResultApi result = new InvSaleOrderUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvSaleOrderUnAuditParams invSaleOrderUnAuditParams = params.getParams();
	        if(invSaleOrderUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmInvSaleOrder2 scmInvSaleOrder = new ScmInvSaleOrder2();
	        scmInvSaleOrder.setOtNo(invSaleOrderUnAuditParams.getOtNo());
	        scmInvSaleOrderBiz.doUnAuditInvSaleOrder(scmInvSaleOrder, ParamHelper.createParam(integratedRequest,"doUnAuditInvSaleOrder"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("销售订单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
