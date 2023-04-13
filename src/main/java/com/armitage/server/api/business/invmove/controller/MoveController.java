package com.armitage.server.api.business.invmove.controller;

import com.armitage.server.api.business.invmove.params.InvMoveUnAuditParamsApi;
import com.armitage.server.api.business.invmove.result.InvMoveAuditResultApi;
import com.armitage.server.api.business.invmove.result.InvMoveResultApi;
import com.armitage.server.api.business.invmove.result.InvMoveUnAuditResultApi;
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

import com.armitage.server.api.business.invmove.params.InvMoveAuditParams;
import com.armitage.server.api.business.invmove.params.InvMoveAuditParamsApi;
import com.armitage.server.api.business.invmove.params.InvMoveParams;
import com.armitage.server.api.business.invmove.params.InvMoveParamsApi;
import com.armitage.server.api.business.invmove.params.InvMoveUnAuditParams;
import com.armitage.server.api.business.invmove.result.InvMoveDetailResult;
import com.armitage.server.api.business.invmove.result.InvMoveResult;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@Controller
@RequestMapping("/api/invMove")
@Api(tags="成本转移业务接口")
public class MoveController {
	private static Log log = LogFactory.getLog(MoveController.class);
	private ScmInvMoveBillBiz scmInvMoveBillBiz = (ScmInvMoveBillBiz) AppContextUtil.getBean("scmInvMoveBillBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryInvMove", method=RequestMethod.POST)
    @ApiOperation(value="获取成本转移单详情", consumes="application/json",position=1)
    public InvMoveResultApi queryInvMove(@RequestBody InvMoveParamsApi params) {
		InvMoveResultApi result = new InvMoveResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMoveParams invMoveParams = params.getParams();
	        if(invMoveParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmInvMoveBill2 scmInvMove = new ScmInvMoveBill2();
	        scmInvMove.setWtNo(invMoveParams.getWtNo());
	        ScmInvMoveBill2 rtn = scmInvMoveBillBiz.queryInvMove(scmInvMove, ParamHelper.createParam(integratedRequest,"queryInvMove"));
	        if(rtn!=null){
	        	InvMoveResult invMoveResult = new InvMoveResult();
	        	BeanUtils.copyProperties(rtn, invMoveResult);
	        	List<ScmInvMoveBillEntry2> scmInvMoveEntryList = rtn.getScmInvMoveBillEntryList();
	        	if(scmInvMoveEntryList != null && !scmInvMoveEntryList.isEmpty()){
	        		List<InvMoveDetailResult> detailList = new ArrayList<InvMoveDetailResult>();
	        		for(ScmInvMoveBillEntry2 scmInvMoveEntry : scmInvMoveEntryList){
	        			InvMoveDetailResult invMoveDetailResult = new InvMoveDetailResult();
	        			BeanUtils.copyProperties(scmInvMoveEntry, invMoveDetailResult);
	        			invMoveDetailResult.setUnit(scmInvMoveEntry.getUnitName());
	        			invMoveDetailResult.setPieUnit(scmInvMoveEntry.getPieUnitName());
	        			detailList.add(invMoveDetailResult);
	        		}
	        		invMoveResult.setDetailList(detailList);
	        	}
	        	result.setResult(invMoveResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取成本转移单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditInvMove", method=RequestMethod.POST)
    @ApiOperation(value="审批成本转移单", consumes="application/json",position=2)
    public InvMoveAuditResultApi doAuditInvMove(@RequestBody InvMoveAuditParamsApi params) {
    	InvMoveAuditResultApi result = new InvMoveAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMoveAuditParams invMoveAuditParams = params.getParams();
	        if(invMoveAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(invMoveAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(invMoveAuditParams.getWtNo());
	        scmInvMoveBillBiz.doAuditInvMove(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditInvMove"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本转移单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditInvMove", method=RequestMethod.POST)
    @ApiOperation(value="取消审批成本转移单", consumes="application/json",position=3)
    public InvMoveUnAuditResultApi doUnAuditInvMove(@RequestBody InvMoveUnAuditParamsApi params) {
    	InvMoveUnAuditResultApi result = new InvMoveUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	InvMoveUnAuditParams invMoveUnAuditParams = params.getParams();
	        if(invMoveUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmInvMoveBill2 scmInvMoveBill = new ScmInvMoveBill2();
	        scmInvMoveBill.setWtNo(invMoveUnAuditParams.getWtNo());
	        scmInvMoveBillBiz.doUnAuditInvMove(scmInvMoveBill, ParamHelper.createParam(integratedRequest,"doUnAuditInvMove"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本转移单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
