package com.armitage.server.api.business.purprice.controller;

import com.armitage.server.api.business.purprice.params.PurPriceAuditParams;
import com.armitage.server.api.business.purprice.result.PurPriceAuditResultApi;
import com.armitage.server.api.business.purprice.result.PurPriceResult;
import com.armitage.server.api.business.purprice.result.PurPriceResultApi;
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

import com.armitage.server.api.business.purprice.params.PurPriceAuditParamsApi;
import com.armitage.server.api.business.purprice.params.PurPriceParams;
import com.armitage.server.api.business.purprice.params.PurPriceParamsApi;
import com.armitage.server.api.business.purprice.params.PurPriceUnAuditParams;
import com.armitage.server.api.business.purprice.params.PurPriceUnAuditParamsApi;
import com.armitage.server.api.business.purprice.result.PurPriceDetailResult;
import com.armitage.server.api.business.purprice.result.PurPriceUnAuditResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

@Controller
@RequestMapping("/api/purPrice")
@Api(tags="定价业务接口")
public class PriceController {
	private static Log log = LogFactory.getLog(PriceController.class);
	private ScmPurPriceBiz scmPurPriceBiz = (ScmPurPriceBiz) AppContextUtil.getBean("scmPurPriceBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurPrice", method=RequestMethod.POST)
    @ApiOperation(value="获取定价单详情", consumes="application/json",position=1)
    public PurPriceResultApi queryPurPrice(@RequestBody PurPriceParamsApi params) {
		PurPriceResultApi result = new PurPriceResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurPriceParams purPriceParams = params.getParams();
	        if(purPriceParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurPrice2 scmPurPrice= new ScmPurPrice2();
	        scmPurPrice.setPmNo(purPriceParams.getPmNo());
	        ScmPurPrice2 rtn = scmPurPriceBiz.queryPurPrice(scmPurPrice, ParamHelper.createParam(integratedRequest,"queryPurPrice"));
	        if(rtn!=null){
	        	PurPriceResult purPriceResult = new PurPriceResult();
	        	BeanUtils.copyProperties(rtn, purPriceResult);
	        	purPriceResult.setPriceName(rtn.getPriceOfficer());
	        	List<ScmPurPriceEntry2> scmPurPriceEntryList = rtn.getScmPurPriceEntryList();
	        	if(scmPurPriceEntryList!=null && !scmPurPriceEntryList.isEmpty()){
	        		List<PurPriceDetailResult> detailList = new ArrayList<PurPriceDetailResult>();
	        		for(ScmPurPriceEntry2 scmPurPriceEntry : scmPurPriceEntryList){
	        			PurPriceDetailResult purPriceDetailResult = new PurPriceDetailResult();
	        			BeanUtils.copyProperties(scmPurPriceEntry, purPriceDetailResult);
	        			purPriceDetailResult.setPurUnit(scmPurPriceEntry.getPurUnitName());
	        			detailList.add(purPriceDetailResult);
	        		}
	        		purPriceResult.setDetailList(detailList);
	        	}
	        	result.setResult(purPriceResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取定价单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditPurPrice", method=RequestMethod.POST)
    @ApiOperation(value="审批定价单", consumes="application/json",position=2)
    public PurPriceAuditResultApi doAuditPurPrice(@RequestBody PurPriceAuditParamsApi params) {
    	PurPriceAuditResultApi result = new PurPriceAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurPriceAuditParams purPriceAuditParams = params.getParams();
	        if(purPriceAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }

	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(purPriceAuditParams, commonAuditParams);
	        commonAuditParams.setBillNo(purPriceAuditParams.getPmNo());
	        scmPurPriceBiz.doAuditPurPrice(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditPurPrice"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("定价单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditPurPrice", method=RequestMethod.POST)
    @ApiOperation(value="取消审批定价单", consumes="application/json",position=3)
    public PurPriceUnAuditResultApi doUnAuditPurPrice(@RequestBody PurPriceUnAuditParamsApi params) {
    	PurPriceUnAuditResultApi result = new PurPriceUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurPriceUnAuditParams purPriceUnAuditParams = params.getParams();
	        if(purPriceUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmPurPrice2 scmPurPrice = new ScmPurPrice2();
	        scmPurPrice.setPmNo(purPriceUnAuditParams.getPmNo());
	        scmPurPriceBiz.doUnAuditPurPrice(scmPurPrice, ParamHelper.createParam(integratedRequest,"doUnAuditPurPrice"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("定价单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
