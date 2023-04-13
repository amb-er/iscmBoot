package com.armitage.server.api.business.costconsume.controller;

import com.armitage.server.api.business.costconsume.params.CostConsumeAddParams;
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

import com.armitage.server.api.business.costconsume.params.CostConsumeAddParamsApi;
import com.armitage.server.api.business.costconsume.result.CostConsumeAddResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/costConsume")
@Api(tags="耗用单接口")
public class CostConsumeController {
	private static Log log = LogFactory.getLog(CostConsumeController.class);
	private ScmInvCostConsumeBiz scmInvCostConsumeBiz = (ScmInvCostConsumeBiz) AppContextUtil.getBean("scmInvCostConsumeBiz");
	
	
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAddCostConsume", method=RequestMethod.POST)
    @ApiOperation(value="新增耗用单", consumes="application/json",position=3)
    public CostConsumeAddResultApi doAddCostConsume(@RequestBody CostConsumeAddParamsApi params) {
		CostConsumeAddResultApi result = new CostConsumeAddResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	CostConsumeAddParams costConsumeAddParams = params.getParams();
        	if(costConsumeAddParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	if(costConsumeAddParams.getDetailList() == null || costConsumeAddParams.getDetailList().isEmpty()){
    	    		throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
    	    	}
    	    	ScmInvCostConsume2 scmInvCostConsume=scmInvCostConsumeBiz.doAddCostConsume(costConsumeAddParams, ParamHelper.createParam(integratedRequest,"doAddCostConsume"));
    		    if(scmInvCostConsume!=null){
    		    	result.setDcNo(scmInvCostConsume.getDcNo());
    	        }    
    	    }
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("耗用单新增失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
}
