package com.armitage.server.api.business.cstfrmloss.controller;

import com.armitage.server.api.business.cstfrmloss.result.CstFrmLossAuditResultApi;
import com.armitage.server.api.business.cstfrmloss.result.CstFrmLossDetailResult;
import com.armitage.server.api.business.cstfrmloss.result.CstFrmLossResultApi;
import com.armitage.server.api.business.cstfrmloss.result.CstFrmLossUnAuditResultApi;
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

import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossAuditParams;
import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossAuditParamsApi;
import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossParams;
import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossParamsApi;
import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossUnAuditParams;
import com.armitage.server.api.business.cstfrmloss.params.CstFrmLossUnAuditParamsApi;
import com.armitage.server.api.business.cstfrmloss.result.CstFrmLossResult;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmCstFrmLossBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@Controller
@RequestMapping("/api/cstFrmLoss")
@Api(tags="成本中心报损业务接口")
public class CstFrmLossController {
	private static Log log = LogFactory.getLog(CstFrmLossController.class);
	private ScmCstFrmLossBiz scmCstFrmLossBiz = (ScmCstFrmLossBiz) AppContextUtil.getBean("scmCstFrmLossBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryCstFrmLoss", method=RequestMethod.POST)
    @ApiOperation(value="获取成本中心报损单详情", consumes="application/json",position=1)
    public CstFrmLossResultApi queryCstFrmLoss(@RequestBody CstFrmLossParamsApi params) {
		CstFrmLossResultApi result = new CstFrmLossResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CstFrmLossParams cstFrmLossParams = params.getParams();
	        if(cstFrmLossParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmCstFrmLoss2 scmCstFrmLoss = new ScmCstFrmLoss2();
	        scmCstFrmLoss.setBillNo(cstFrmLossParams.getBillNo());
	        ScmCstFrmLoss2 rtn = scmCstFrmLossBiz.queryCstFrmLoss(scmCstFrmLoss, ParamHelper.createParam(integratedRequest,"queryCstFrmLoss"));
	        if(rtn!=null){
	        	CstFrmLossResult cstFrmLossResult = new CstFrmLossResult();
	        	BeanUtils.copyProperties(rtn, cstFrmLossResult);
	        	List<ScmCstFrmLossEntry2> scmCstFrmLossEntryList = rtn.getScmCstFrmLossEntryList();
	        	if(scmCstFrmLossEntryList != null && !scmCstFrmLossEntryList.isEmpty()){
	        		List< CstFrmLossDetailResult > detailList = new ArrayList<CstFrmLossDetailResult>();
	        		for(ScmCstFrmLossEntry2 scmCstFrmLossEntry : scmCstFrmLossEntryList){
	        			CstFrmLossDetailResult cstFrmLossDetailResult = new CstFrmLossDetailResult();
	        			BeanUtils.copyProperties(scmCstFrmLossEntry, cstFrmLossDetailResult);
	        			cstFrmLossDetailResult.setUnit(scmCstFrmLossEntry.getUnitName());
	        			detailList.add(cstFrmLossDetailResult);
	        		}
	        		cstFrmLossResult.setDetailList(detailList);
	        	}
	        	result.setResult(cstFrmLossResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取成本中心报损单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditCstFrmLoss", method=RequestMethod.POST)
    @ApiOperation(value="审批成本中心报损单", consumes="application/json",position=2)
    public CstFrmLossAuditResultApi doAuditCstFrmLoss(@RequestBody CstFrmLossAuditParamsApi params) {
		CstFrmLossAuditResultApi result = new CstFrmLossAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CstFrmLossAuditParams cstFrmLossAuditParams = params.getParams();
	        if(cstFrmLossAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(cstFrmLossAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(cstFrmLossAuditParams.getWtNo());
	        scmCstFrmLossBiz.doAuditCstFrmLoss(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditCstFrmLoss"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本中心报损单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditCstFrmLoss", method=RequestMethod.POST)
    @ApiOperation(value="取消审批成本中心报损单", consumes="application/json",position=3)
    public CstFrmLossUnAuditResultApi doUnAuditCstFrmLoss(@RequestBody CstFrmLossUnAuditParamsApi params) {
		CstFrmLossUnAuditResultApi result = new CstFrmLossUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CstFrmLossUnAuditParams cstFrmLossUnAuditParams = params.getParams();
	        if(cstFrmLossUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmCstFrmLoss2 scmCstFrmLoss = new ScmCstFrmLoss2();
	        scmCstFrmLoss.setBillNo(cstFrmLossUnAuditParams.getBillNo());
	        scmCstFrmLossBiz.doUnAuditCstFrmLoss(scmCstFrmLoss, ParamHelper.createParam(integratedRequest,"doUnAuditCstFrmLoss"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本中心报损单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
