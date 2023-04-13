package com.armitage.server.api.business.basedata.controller;

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

import com.armitage.server.api.business.basedata.params.QualifieBillAuditParams;
import com.armitage.server.api.business.basedata.params.QualifieBillAuditParamsApi;
import com.armitage.server.api.business.basedata.params.QualifieBillListParams;
import com.armitage.server.api.business.basedata.params.QualifieBillListParamsApi;
import com.armitage.server.api.business.basedata.params.QualifieBillParams;
import com.armitage.server.api.business.basedata.params.QualifieBillParamsApi;
import com.armitage.server.api.business.basedata.params.QualifieBillUnAuditParams;
import com.armitage.server.api.business.basedata.params.QualifieBillUnAuditParamsApi;
import com.armitage.server.api.business.basedata.result.QualifieBillAuditResultApi;
import com.armitage.server.api.business.basedata.result.QualifieBillDetailResult;
import com.armitage.server.api.business.basedata.result.QualifieBillListResult;
import com.armitage.server.api.business.basedata.result.QualifieBillListResultApi;
import com.armitage.server.api.business.basedata.result.QualifieBillResult;
import com.armitage.server.api.business.basedata.result.QualifieBillResultApi;
import com.armitage.server.api.business.basedata.result.QualifieBillUnAuditResultApi;
import com.armitage.server.api.business.basedata.result.QualifiePicResult;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.LogicSymbol;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry2;
import com.armitage.server.iscm.basedata.service.ScmSupplierQualifieInfoBillBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment2;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/qualifieBill")
@Api(tags="资质申请单业务接口")
public class QualifieBillController {
	private static Log log = LogFactory.getLog(QualifieBillController.class);
	private ScmSupplierQualifieInfoBillBiz scmSupplierQualifieInfoBillBiz = (ScmSupplierQualifieInfoBillBiz) AppContextUtil.getBean("scmSupplierQualifieInfoBillBiz");
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryQualifieInfoBillList", method=RequestMethod.POST)
    @ApiOperation(value="获取资质申请单列表", consumes="application/json",position=1)
    public QualifieBillListResultApi queryQualifieInfoBillList(@RequestBody QualifieBillListParamsApi params) {
		QualifieBillListResultApi result = new QualifieBillListResultApi(); 	//显示结果集
		ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        ApiLogicSymbol apiLogicSymbol = params.getLogicSymbol();
        LogicSymbol logicSymbol = new LogicSymbol();
        BeanUtils.copyProperties(apiLogicSymbol, logicSymbol);
        int pageIndex = -1;
        PageNum pageNum = params.getPageNum();
        if(pageNum != null && pageNum.getPageNum() != 0) {
        	pageIndex = pageNum.getPageNum();
        }
        
        try {
        	SecurityUtils.verify(integratedRequest);
        	QualifieBillListParams qualifieBillListParams = params.getParams();
        	if (qualifieBillListParams == null) {
        		throw new AppException("webservice.params.null");
        	}
        	
        	ScmSupplierQualifieInfoBillAdvQuery scmSupplierQualifieInfoBillAdvQuery = new ScmSupplierQualifieInfoBillAdvQuery();
        	BeanUtils.copyProperties(qualifieBillListParams, scmSupplierQualifieInfoBillAdvQuery);
        	List<ScmSupplierQualifieInfoBill2> scmSupplierQualifieInfoBillList = scmSupplierQualifieInfoBillBiz.queryQualifieInfoBillList(scmSupplierQualifieInfoBillAdvQuery,pageIndex, ParamHelper.createParam(integratedRequest,"queryQualifieInfoBillList"));
        	
        	if (scmSupplierQualifieInfoBillList != null && !scmSupplierQualifieInfoBillList.isEmpty()) {
        		List< QualifieBillListResult > resultList = new ArrayList<> ();
        		for(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill : scmSupplierQualifieInfoBillList) {
        			QualifieBillListResult qualifieBillListResult = new QualifieBillListResult();
        			BeanUtils.copyProperties(scmSupplierQualifieInfoBill, qualifieBillListResult);
	        		resultList.add(qualifieBillListResult);
        		}
        		result.setResultList(resultList);
        	}
        } catch (Exception e) {
        	log.error(e);
        	result.setErrCode("-1");
        	result.setErrText("获取资质申请单列表失败：" + Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
        }
		
		return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/queryQualifieInfoBill", method=RequestMethod.POST)
    @ApiOperation(value="获取资质申请单详情", consumes="application/json",position=2)
    public QualifieBillResultApi queryQualifieInfoBill(@RequestBody QualifieBillParamsApi params) {
		QualifieBillResultApi result = new QualifieBillResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	QualifieBillParams qualifieBillParams = params.getParams();
	        if(qualifieBillParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill= new ScmSupplierQualifieInfoBill2();
	        scmSupplierQualifieInfoBill.setBillNo(qualifieBillParams.getBillNo());
	        ScmSupplierQualifieInfoBill2 rtn = scmSupplierQualifieInfoBillBiz.queryQualifieInfoBill(scmSupplierQualifieInfoBill, ParamHelper.createParam(integratedRequest,"queryQualifieInfoBill"));
	        if(rtn!=null){
	        	QualifieBillResult qualifieBillResult = new QualifieBillResult();
	        	BeanUtils.copyProperties(rtn, qualifieBillResult);
	        	List<ScmSupplierQualifieInfoBillEntry2> scmSupplierQualifieInfoBillEntryList = rtn.getScmSupplierQualifieInfoBillEntryList();
	        	if(scmSupplierQualifieInfoBillEntryList!=null && !scmSupplierQualifieInfoBillEntryList.isEmpty()){
	        		List< QualifieBillDetailResult > detailList = new ArrayList<QualifieBillDetailResult>();
	        		for(ScmSupplierQualifieInfoBillEntry2 scmSupplierQualifieInfoBillEntry:scmSupplierQualifieInfoBillEntryList){
	        			QualifieBillDetailResult qualifieBillDetailResult = new QualifieBillDetailResult();
	        			BeanUtils.copyProperties(scmSupplierQualifieInfoBillEntry, qualifieBillDetailResult);
	        			if(scmSupplierQualifieInfoBillEntry.getScmBaseAttachmentList() != null && !scmSupplierQualifieInfoBillEntry.getScmBaseAttachmentList().isEmpty()){
	        				List<QualifiePicResult> qualifiePicResultList = new ArrayList<QualifiePicResult>();
	        				for(ScmBaseAttachment2 scmBaseAttachment : scmSupplierQualifieInfoBillEntry.getScmBaseAttachmentList()){
	        					QualifiePicResult qualifiePicResult = new QualifiePicResult();
	        					BeanUtils.copyProperties(scmBaseAttachment, qualifiePicResult);
	        					qualifiePicResultList.add(qualifiePicResult);
	        				}
	        				qualifieBillDetailResult.setQualifiePicResultList(qualifiePicResultList);
	        			}
	        			detailList.add(qualifieBillDetailResult);
	        		}
	        		qualifieBillResult.setDetailList(detailList);
	        	}
	        	result.setResult(qualifieBillResult);
	        }
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("获取资质申请单详情失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result;
	}
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doAuditQualifieInfo", method=RequestMethod.POST)
    @ApiOperation(value="审批资质申请单", consumes="application/json",position=3)
    public QualifieBillAuditResultApi doAuditQualifieInfo(@RequestBody QualifieBillAuditParamsApi params) {
		QualifieBillAuditResultApi result = new QualifieBillAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	QualifieBillAuditParams qualifieBillAuditParams = params.getParams();
	        if(qualifieBillAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        CommonAuditParams commonAuditParams = new CommonAuditParams();
	        BeanUtils.copyProperties(qualifieBillAuditParams, commonAuditParams);
	        scmSupplierQualifieInfoBillBiz.doAuditQualifieInfo(commonAuditParams, ParamHelper.createParam(integratedRequest,"doAuditQualifieInfo"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("资质申请单审批失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="mobileApi")
    @ResponseBody
    @RequestMapping(value="/doUnAuditQualifieInfo", method=RequestMethod.POST)
    @ApiOperation(value="取消审批资质申请单", consumes="application/json",position=4)
    public QualifieBillUnAuditResultApi doUnAuditQualifieInfo(@RequestBody QualifieBillUnAuditParamsApi params) {
		QualifieBillUnAuditResultApi result = new QualifieBillUnAuditResultApi();//显示的结果集
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	QualifieBillUnAuditParams qualifieBillUnAuditParams = params.getParams();
	        if(qualifieBillUnAuditParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        
	        ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill= new ScmSupplierQualifieInfoBill2();
	        scmSupplierQualifieInfoBill.setBillNo(qualifieBillUnAuditParams.getBillNo());
	        scmSupplierQualifieInfoBillBiz.doUnAuditQualifieInfo(scmSupplierQualifieInfoBill, ParamHelper.createParam(integratedRequest,"doUnAuditQualifieInfo"));
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("资质申请单取消审核失败:"+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
}
