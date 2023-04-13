package com.armitage.server.api.business.purmarketprice.controller;

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

import com.armitage.server.api.business.purmarketprice.params.PurMarketPersonParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPersonParamsApi;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceEditParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceEditParamsApi;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceListParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceListParamsApi;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceParamsApi;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceSubmitParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceSubmitParamsApi;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceUnSubmitParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceUnSubmitParamsApi;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPersonResult;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPersonResultApi;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceDetailResult;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceEditResultApi;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceListResult;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceListResultApi;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceResult;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceResultApi;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceSubmitResultApi;
import com.armitage.server.api.business.purmarketprice.result.PurMarketPriceUnSubmitResultApi;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceBiz;
import com.armitage.server.user.model.Usr2;
import com.armitage.server.user.model.UsrAdvQuery;
import com.armitage.server.user.service.UsrBiz;

@Controller
@RequestMapping("/api/purMarketPrice")
@Api(tags="市调单业务接口")
public class MarketPriceController {
	private static Log log = LogFactory.getLog(MarketPriceController.class);
    private ScmPurMarketPriceBiz scmPurMarketPriceBiz = (ScmPurMarketPriceBiz) AppContextUtil.getBean("scmPurMarketPriceBiz");
    private UsrBiz usrBiz = (UsrBiz) AppContextUtil.getBean("usrBiz");
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurMarketPriceList", method=RequestMethod.POST)
    @ApiOperation(value="获取市调单列表", consumes="application/json")
    public PurMarketPriceListResultApi queryPurMarketPriceList(@RequestBody PurMarketPriceListParamsApi params) {
    	PurMarketPriceListResultApi result = new PurMarketPriceListResultApi();//显示的结果集
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
        	PurMarketPriceListParams purMarketPriceListParams = params.getParams();
	        if(purMarketPriceListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurMarketPriceAdvQuery scmPurMarketPriceAdvQuery = new ScmPurMarketPriceAdvQuery();
	        BeanUtils.copyProperties(purMarketPriceListParams, scmPurMarketPriceAdvQuery);
	        scmPurMarketPriceAdvQuery.setFromInterface(true);
	        List<ScmPurMarketPrice2> scmPurMarketPriceList = scmPurMarketPriceBiz.queryPurMarketPriceList(scmPurMarketPriceAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryPurMarketPriceList"));
	        if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
	        	List< PurMarketPriceListResult > resultList = new ArrayList();
	        	for(ScmPurMarketPrice2 scmPurMarketPrice:scmPurMarketPriceList){
	        		PurMarketPriceListResult purPriceListResult = new PurMarketPriceListResult();
	        		BeanUtils.copyProperties(scmPurMarketPrice, purPriceListResult);
	        		resultList.add(purPriceListResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取市调单列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurMarketPrice", method=RequestMethod.POST)
    @ApiOperation(value="获取市调单详情", consumes="application/json")
    public PurMarketPriceResultApi queryPurMarketPrice(@RequestBody PurMarketPriceParamsApi params) {
    	PurMarketPriceResultApi result = new PurMarketPriceResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurMarketPriceParams purPriceParams = params.getParams();
	        if(purPriceParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurMarketPrice2 scmPurMarketPrice= new ScmPurMarketPrice2();
	        scmPurMarketPrice.setPiNo(purPriceParams.getPiNo());
	        ScmPurMarketPrice2 rtn = scmPurMarketPriceBiz.queryPurMarketPrice(scmPurMarketPrice, ParamHelper.createParam(integratedRequest,"queryPurMarketPrice"));
	        if(rtn!=null){
	        	PurMarketPriceResult purPriceResult = new PurMarketPriceResult();
	        	BeanUtils.copyProperties(rtn, purPriceResult);
	        	List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = rtn.getScmPurMarketPriceEntryList();
	        	if(scmPurMarketPriceEntryList!=null && !scmPurMarketPriceEntryList.isEmpty()){
	        		List< PurMarketPriceDetailResult > detailList = new ArrayList();
	        		for(ScmPurMarketPriceEntry2 scmPurMarketPriceEntry:scmPurMarketPriceEntryList){
	        			PurMarketPriceDetailResult purPriceDetailResult = new PurMarketPriceDetailResult();
	        			BeanUtils.copyProperties(scmPurMarketPriceEntry, purPriceDetailResult);
	        			detailList.add(purPriceDetailResult);
	        		}
	        		purPriceResult.setDetailList(detailList);
	        	}
	        	result.setResult(purPriceResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取定价单详情:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doSubmitPurMarketPrice", method=RequestMethod.POST)
    @ApiOperation(value="提交市调单", consumes="application/json")
    public PurMarketPriceSubmitResultApi doSubmitPurMarketPrice(@RequestBody PurMarketPriceSubmitParamsApi params) {
    	PurMarketPriceSubmitResultApi result = new PurMarketPriceSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurMarketPriceSubmitParams purMarketPriceSubmitParams = params.getParams();
	        if(purMarketPriceSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurMarketPrice2 scmPurMarketPrice= new ScmPurMarketPrice2();
	        scmPurMarketPrice.setPiNo(purMarketPriceSubmitParams.getPiNo());
	        scmPurMarketPriceBiz.submit(scmPurMarketPrice, ParamHelper.createParam(integratedRequest,"doSubmitPurMarketPrice"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("提交市调单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
    
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnSubmitPurMarketPrice", method=RequestMethod.POST)
    @ApiOperation(value="取消提交市调单", consumes="application/json")
    public PurMarketPriceUnSubmitResultApi doUnSubmitPurMarketPrice(@RequestBody PurMarketPriceUnSubmitParamsApi params) {
    	PurMarketPriceUnSubmitResultApi result = new PurMarketPriceUnSubmitResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurMarketPriceUnSubmitParams purMarketPriceUnSubmitParams = params.getParams();
	        if(purMarketPriceUnSubmitParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmPurMarketPrice2 scmPurMarketPrice= new ScmPurMarketPrice2();
	        scmPurMarketPrice.setPiNo(purMarketPriceUnSubmitParams.getPiNo());
	        scmPurMarketPriceBiz.undoSubmit(scmPurMarketPrice, ParamHelper.createParam(integratedRequest,"doUnSubmitPurMarketPrice"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("取消提交市调单失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    }
    

	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doEditPurMarketPrice", method=RequestMethod.POST)
    @ApiOperation(value="修改市调单", consumes="application/json")
    public PurMarketPriceEditResultApi doEditPurMarketPrice(@RequestBody PurMarketPriceEditParamsApi params) {
    	PurMarketPriceEditResultApi result = new PurMarketPriceEditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        String piNo=null;
        try {
        	SecurityUtils.verify(integratedRequest);
        	PurMarketPriceEditParams purPriceEditParams = params.getParams();
        	if(purPriceEditParams==null){
    	          throw new AppException("webservice.params.null");
    	    }else{
    	    	ScmPurMarketPrice scmPurMarketPrice=scmPurMarketPriceBiz.doEditPurMarketPrice(purPriceEditParams, ParamHelper.createParam(integratedRequest,"doEditPurMarketPrice"));
    		    if(scmPurMarketPrice!=null){
    	        	piNo=scmPurMarketPrice.getPiNo();
    	        }    
    	    }
        result.setPiNo(piNo);
        } catch (Exception e) {
	    	log.error(e);
	    	result.setErrCode("-1");
	    	result.setErrText("市调单修改失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
	    	e.printStackTrace();
		}
	    return result; 
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryPurMarketPerson", method=RequestMethod.POST)
    @ApiOperation(value="查询市调员列表", consumes="application/json")
    public PurMarketPersonResultApi queryPurMarketPerson(@RequestBody PurMarketPersonParamsApi params) {
		PurMarketPersonResultApi result = new PurMarketPersonResultApi();//显示的结果集
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
        	PurMarketPersonParams purMarketPersonParams = params.getParams();
	        if(purMarketPersonParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        UsrAdvQuery usrAdvQuery = new UsrAdvQuery();
	        usrAdvQuery.setName(purMarketPersonParams.getEnquiryName());
	        Page page = new Page();
	        page.setModelClass(Usr2.class);
	        if (pageIndex == -1) {
				page.setPagePos(1);
				page.setShowCount(Integer.MAX_VALUE);
			} else {
				page.setPagePos(pageIndex);
				page.setShowCount(20);
			}
			page.setModel(usrAdvQuery);
	        List<Usr2> usrList = usrBiz.findPage(page, ParamHelper.createParam(integratedRequest,"queryPurMarketPerson"));
	        if(usrList!=null && !usrList.isEmpty()){
	        	List<PurMarketPersonResult> resultList = new ArrayList();
	        	for(Usr2 usr:usrList){
	        		PurMarketPersonResult purMarketPersonResult = new PurMarketPersonResult();
	        		purMarketPersonResult.setEnquiryCode(usr.getCode());
	        		purMarketPersonResult.setEnquiryName(usr.getName());
	        		resultList.add(purMarketPersonResult);
	        	}
	        	result.setResultList(resultList);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取市调员列表失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 
    	
    }
}
