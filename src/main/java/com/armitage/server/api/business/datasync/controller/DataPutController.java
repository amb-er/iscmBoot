package com.armitage.server.api.business.datasync.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;import java.util.List;

import com.armitage.server.api.business.datasync.result.DataSyncResult;
import com.armitage.server.api.business.datasync.result.DataSyncResultApi;
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

import com.armitage.server.api.business.datasync.params.InvCostConsumeListParams;
import com.armitage.server.api.business.datasync.params.InvCostConsumeParamsApi;
import com.armitage.server.api.business.datasync.params.InvCountingCostListParams;
import com.armitage.server.api.business.datasync.params.InvCountingCostParamsApi;
import com.armitage.server.api.business.datasync.params.InvMaterialReqListSParams;
import com.armitage.server.api.business.datasync.params.InvMaterialReqParamsSnycApi;
import com.armitage.server.api.business.datasync.params.InvMoveListParams;
import com.armitage.server.api.business.datasync.params.InvMoveListParamsApi;
import com.armitage.server.api.business.datasync.params.InvOtherInWarehsListParams;
import com.armitage.server.api.business.datasync.params.InvOtherInWarehsParamsApi;
import com.armitage.server.api.business.datasync.params.InvOtherIssueBillListParams;
import com.armitage.server.api.business.datasync.params.InvOtherIssueBillListParamsApi;
import com.armitage.server.api.business.datasync.params.InvStockListParams;
import com.armitage.server.api.business.datasync.params.InvStockParamsApi;
import com.armitage.server.api.business.datasync.params.ScmPurRequireBillParam;
import com.armitage.server.api.business.datasync.params.ScmPurRequireBillParamsApi;
import com.armitage.server.api.business.datasync.params.ScmPurRequireDetailParams;
import com.armitage.server.api.business.purrequire.controller.RequireController;
import com.armitage.server.common.base.model.IntegratedRequest;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.security.SecurityUtils;
import com.armitage.server.common.util.AppContextUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.ParamHelper;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialPurchaseBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsume2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCostConsumeBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvCountingTaskBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.service.ScmInvMoveBillBiz;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStockNo;
import com.armitage.server.iscm.inventorymanage.inventorydata.service.ScmInvStockNoBiz;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvOtherInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service.ScmInvOtherInWarehsBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvMaterialReqBillBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmInvPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmMaterialPriceBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgPurchase;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.OrgUnitRelationType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/api/dataPut")
@Api(tags="数据写入接口")
public class DataPutController {	
	private static Log log = LogFactory.getLog(RequireController.class);
	
	private ScmInvMoveBillBiz scmInvMoveBillBiz = (ScmInvMoveBillBiz) AppContextUtil.getBean("scmInvMoveBillBiz");
	private ScmMeasureUnitBiz scmMeasureUnitBiz = (ScmMeasureUnitBiz) AppContextUtil.getBean("scmMeasureUnitBiz");
	private ScmInvCostConsumeBiz scmInvCostConsumeBiz = (ScmInvCostConsumeBiz) AppContextUtil.getBean("scmInvCostConsumeBiz");
	private ScmInvOtherInWarehsBillBiz scmInvOtherInWarehsBillBiz = (ScmInvOtherInWarehsBillBiz) AppContextUtil.getBean("scmInvOtherInWarehsBillBiz");
	private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz = (ScmInvOtherIssueBillBiz) AppContextUtil.getBean("scmInvOtherIssueBillBiz");
	private OrgUnitRelationBiz orgUnitRelationBiz =(OrgUnitRelationBiz) AppContextUtil.getBean("orgUnitRelationBiz");
	private ScmPurRequireBiz scmPurRequireBiz = (ScmPurRequireBiz) AppContextUtil.getBean("scmPurRequireBiz");
	private ScmInvCountingTaskBiz scmInvCountingTaskBiz = (ScmInvCountingTaskBiz) AppContextUtil.getBean("scmInvCountingTaskBiz");
	private ScmInvStockNoBiz scmInvStockNoBiz = (ScmInvStockNoBiz) AppContextUtil.getBean("scmInvStockNoBiz");
	private ScmInvMaterialReqBillBiz scmInvMaterialReqBillBiz = (ScmInvMaterialReqBillBiz) AppContextUtil.getBean("scmInvMaterialReqBillBiz");
	private OrgAdminBiz orgAdminBiz = (OrgAdminBiz) AppContextUtil.getBean("orgAdminBiz");
	private OrgPurchaseBiz orgPurchaseBiz = (OrgPurchaseBiz) AppContextUtil.getBean("orgPurchaseBiz");
	private ScmMaterialBiz scmMaterialBiz = (ScmMaterialBiz) AppContextUtil.getBean("scmMaterialBiz");
	private ScmsupplierBiz scmsupplierBiz = (ScmsupplierBiz) AppContextUtil.getBean("scmsupplierBiz");
    private ScmMaterialPriceBiz scmMaterialPriceBiz = (ScmMaterialPriceBiz) AppContextUtil.getBean("scmMaterialPriceBiz");
    private ScmMaterialPurchaseBiz scmMaterialPurchaseBiz = (ScmMaterialPurchaseBiz) AppContextUtil.getBean("scmMaterialPurchaseBiz");
    
	@ApiVersion(group="dataSyncApi")
	@ResponseBody
    @RequestMapping(value="/doAddInvMoveList", method=RequestMethod.POST)
    @ApiOperation(value="写入成本转移单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvMoveList(@RequestBody InvMoveListParamsApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List< DataSyncResult > resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List< InvMoveListParams > invMoveListParams = params.getParams();
	        if(invMoveListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"scmInvMoveBill");
	        StringBuffer invMoveBillNos = new StringBuffer();
			for(InvMoveListParams invMoveListParam:invMoveListParams) {
				if(!StringUtils.isNotBlank(invMoveListParam.getWtNo()))
					continue;
				if(StringUtils.isNotBlank(invMoveBillNos.toString()))
					invMoveBillNos.append(",");
				invMoveBillNos.append("'").append(invMoveListParam.getWtNo()).append("'");
			}
			if(!StringUtils.isNotBlank(invMoveBillNos.toString())) 
				throw new AppException(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),"iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvMoveBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "."
					+ ScmInvMoveBill2.FN_WTNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "."
									+ ScmInvMoveBill2.FN_WTNO,
							QueryParam.QUERY_IN, invMoveBillNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "."
					+ ScmInvMoveBill2.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMoveBill2.class) + "."
									+ ScmInvMoveBill2.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvMoveBill2> scmInvMoveBills = scmInvMoveBillBiz.queryPage(page,arglist, "findAllPage", param);
			for(InvMoveListParams invMoveListParam:invMoveListParams) {
				try {
					DataSyncResult dataSync = scmInvMoveBillBiz.dataSync(invMoveListParam, scmInvMoveBills, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invMoveListParam.getWtNo()))
						dataSyncResult.setNo(invMoveListParam.getWtNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本转移单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvCostConsumeList", method=RequestMethod.POST)
    @ApiOperation(value="写入成本耗用单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvCostConsumeList(@RequestBody InvCostConsumeParamsApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List<DataSyncResult> resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List< InvCostConsumeListParams > invCostConsumeListParams = params.getParams();
	        if(invCostConsumeListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"ScmInvCostConsume");
	        StringBuffer invCostConsumeNos = new StringBuffer();
			for(InvCostConsumeListParams invCostConsumeListParam:invCostConsumeListParams) {
				if(!StringUtils.isNotBlank(invCostConsumeListParam.getDcNo()))
					continue;
				if(StringUtils.isNotBlank(invCostConsumeNos.toString()))
					invCostConsumeNos.append(",");
				invCostConsumeNos.append("'").append(invCostConsumeListParam.getDcNo()).append("'");
			}
			if(!StringUtils.isNotBlank(invCostConsumeNos.toString())) 
				throw new AppException(Message.getMessage("iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvCostConsume2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "."
					+ ScmInvCostConsume2.FN_DCNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "."
									+ ScmInvCostConsume2.FN_DCNO,
							QueryParam.QUERY_IN, invCostConsumeNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "."
					+ ScmInvCostConsume2.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCostConsume2.class) + "."
									+ ScmInvCostConsume2.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvCostConsume2> scmInvCostConsumes = scmInvCostConsumeBiz.queryPage(page,arglist, "findByNoPage", param);
			for(InvCostConsumeListParams invCostConsumeListParam:invCostConsumeListParams) {
				try {
					DataSyncResult dataSync = scmInvCostConsumeBiz.dataSync(invCostConsumeListParam, scmInvCostConsumes, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invCostConsumeListParam.getDcNo()))
						dataSyncResult.setNo(invCostConsumeListParam.getDcNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本耗用单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvOtherInWarehsList", method=RequestMethod.POST)
    @ApiOperation(value="写入其他入库单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvOtherInWarehsList(@RequestBody InvOtherInWarehsParamsApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List<DataSyncResult> resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List<InvOtherInWarehsListParams> invOtherInWarehsListParams = params.getParams();
	        if(invOtherInWarehsListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"ScmInvOtherInWarehsBill");
	        StringBuffer invOtherInWarehsNos = new StringBuffer();
			for(InvOtherInWarehsListParams invOtherInWarehsListParam:invOtherInWarehsListParams) {
				if(!StringUtils.isNotBlank(invOtherInWarehsListParam.getWrNo()))
					continue;
				if(StringUtils.isNotBlank(invOtherInWarehsNos.toString()))
					invOtherInWarehsNos.append(",");
				invOtherInWarehsNos.append("'").append(invOtherInWarehsListParam.getWrNo()).append("'");
			}
			if(!StringUtils.isNotBlank(invOtherInWarehsNos.toString())) 
				throw new AppException(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),"iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvOtherInWarehsBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "."
					+ ScmInvOtherInWarehsBill2.FN_WRNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "."
									+ ScmInvOtherInWarehsBill2.FN_WRNO,
							QueryParam.QUERY_IN, invOtherInWarehsNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "."
					+ ScmInvOtherInWarehsBill2.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvOtherInWarehsBill2.class) + "."
									+ ScmInvOtherInWarehsBill2.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvOtherInWarehsBill2> scmInvOtherInWarehsBill2s = scmInvOtherInWarehsBillBiz.queryPage(page,arglist, "findAllPage", param);
			for(InvOtherInWarehsListParams invOtherInWarehsListParam:invOtherInWarehsListParams) {
				try {
					DataSyncResult dataSync = scmInvOtherInWarehsBillBiz.dataSync(invOtherInWarehsListParam, scmInvOtherInWarehsBill2s, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invOtherInWarehsListParam.getWrNo()))
						dataSyncResult.setNo(invOtherInWarehsListParam.getWrNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("其他入库单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvOtherIssueBillList", method=RequestMethod.POST)
    @ApiOperation(value="写入其他出库单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvOtherIssueBillList(@RequestBody InvOtherIssueBillListParamsApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List<DataSyncResult> resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List<InvOtherIssueBillListParams> invOtherIssueBillListParams = params.getParams();
	        if(invOtherIssueBillListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"ScmInvOtherIssueBill");
	        StringBuffer invOtherIssueNos = new StringBuffer();
			for(InvOtherIssueBillListParams invOtherIssueBillListParam:invOtherIssueBillListParams) {
				if(!StringUtils.isNotBlank(invOtherIssueBillListParam.getOtNo()))
					continue;
				if(StringUtils.isNotBlank(invOtherIssueNos.toString()))
					invOtherIssueNos.append(",");
				invOtherIssueNos.append("'").append(invOtherIssueBillListParam.getOtNo()).append("'");
			}
			if(!StringUtils.isNotBlank(invOtherIssueNos.toString())) 
				throw new AppException(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),"iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvOtherIssueBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "."
					+ ScmInvOtherIssueBill2.FN_OTNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "."
									+ ScmInvOtherIssueBill2.FN_OTNO,
							QueryParam.QUERY_IN, invOtherIssueNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "."
					+ ScmInvOtherIssueBill2.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvOtherIssueBill2.class) + "."
									+ ScmInvOtherIssueBill2.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvOtherIssueBill2> scmInvOtherInWarehsBill2s = scmInvOtherIssueBillBiz.queryPage(page,arglist, "findAllPage", param);
			for(InvOtherIssueBillListParams invOtherIssueBillListParam:invOtherIssueBillListParams) {
				try {
					DataSyncResult dataSync = scmInvOtherIssueBillBiz.dataSync(invOtherIssueBillListParam, scmInvOtherInWarehsBill2s, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invOtherIssueBillListParam.getOtNo()))
						dataSyncResult.setNo(invOtherIssueBillListParam.getOtNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("其他出库单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvCountingCostList", method=RequestMethod.POST)
    @ApiOperation(value="写入盘存单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvCountingCostList(@RequestBody InvCountingCostParamsApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List<DataSyncResult> resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List<InvCountingCostListParams> invCountingCostListParams = params.getParams();
	        if(invCountingCostListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"ScmInvOtherIssueBill");
	        StringBuffer invCountingCostNos = new StringBuffer();
			for(InvCountingCostListParams invCountingCostListParam:invCountingCostListParams) {
				if(!StringUtils.isNotBlank(invCountingCostListParam.getTaskNo()))
					continue;
				if(StringUtils.isNotBlank(invCountingCostNos.toString()))
					invCountingCostNos.append(",");
				invCountingCostNos.append("'").append(invCountingCostListParam.getTaskNo()).append("'");
			}
			if(!StringUtils.isNotBlank(invCountingCostNos.toString())) 
				throw new AppException(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),"iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvCountingTask2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "."
					+ ScmInvCountingTask2.FN_TASKNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "."
									+ ScmInvCountingTask2.FN_TASKNO,
							QueryParam.QUERY_IN, invCountingCostNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "."
					+ ScmInvCountingTask2.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvCountingTask2.class) + "."
									+ ScmInvCountingTask2.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvCountingTask2> scmInvCountingTask2s = scmInvCountingTaskBiz.queryPage(page,arglist, "findAllPage", param);
			for(InvCountingCostListParams invCountingCostListParam:invCountingCostListParams) {
				try {
					DataSyncResult dataSync = scmInvCountingTaskBiz.dataSync(invCountingCostListParam, scmInvCountingTask2s, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invCountingCostListParam.getTaskNo()))
						dataSyncResult.setNo(invCountingCostListParam.getTaskNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("成本中心盘存单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvStockList", method=RequestMethod.POST)
    @ApiOperation(value="写入结存单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvStockList(@RequestBody InvStockParamsApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List<DataSyncResult> resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List<InvStockListParams> invStockListParams = params.getParams();
	        if(invStockListParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"ScmInvStock");
	        StringBuffer scmInvStockNos = new StringBuffer();
			for(InvStockListParams invStockListParam:invStockListParams) {
				if(!StringUtils.isNotBlank(invStockListParam.getInvNo()))
					continue;
				if(StringUtils.isNotBlank(scmInvStockNos.toString()))
					scmInvStockNos.append(",");
				scmInvStockNos.append("'").append(invStockListParam.getInvNo()).append("'");
			}
			if(!StringUtils.isNotBlank(scmInvStockNos.toString())) 
				throw new AppException(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),"iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvStockNo.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvStockNo.class) + "."
					+ ScmInvStockNo.FN_INVSTOCKNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvStockNo.class) + "."
									+ ScmInvStockNo.FN_INVSTOCKNO,
							QueryParam.QUERY_IN, scmInvStockNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvStockNo.class) + "."
					+ ScmInvStockNo.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvStockNo.class) + "."
									+ ScmInvStockNo.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvStock2> scmInvStockS = scmInvStockNoBiz.queryPage(page,arglist, "findAllForScmInvStockPage", param);
			for(InvStockListParams invStockListParam:invStockListParams) {
				try { 
					DataSyncResult dataSync = scmInvStockNoBiz.dataSync(invStockListParam, scmInvStockS, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invStockListParam.getInvNo()))
						dataSyncResult.setNo(invStockListParam.getInvNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("结存单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }
	
	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddInvMaterialReqBillList", method=RequestMethod.POST)
    @ApiOperation(value="写入领料出库单", consumes="application/json",position=6)
    public DataSyncResultApi doAddInvMaterialReqBillList(@RequestBody InvMaterialReqParamsSnycApi params) {
		DataSyncResultApi result = new DataSyncResultApi();//显示的结果集
		List<DataSyncResult> resultlist = new ArrayList<DataSyncResult>();
        ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        IntegratedRequest integratedRequest = new IntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        try {
        	SecurityUtils.verify(integratedRequest);
        	List<InvMaterialReqListSParams> invMaterialReqListSParams = params.getParams(); 
	        if(invMaterialReqListSParams==null){
	          throw new AppException("webservice.params.null");
	        }
	        Param param = ParamHelper.createParam(integratedRequest,"ScmInvMaterialReqBill");
	        StringBuffer invMaterialReqNos = new StringBuffer();
			for(InvMaterialReqListSParams invMaterialReqListSParam:invMaterialReqListSParams) {
				if(!StringUtils.isNotBlank(invMaterialReqListSParam.getOtNo()))
					continue;
				if(StringUtils.isNotBlank(invMaterialReqNos.toString()))
					invMaterialReqNos.append(",");
				invMaterialReqNos.append("'").append(invMaterialReqListSParam.getOtNo()).append("'");
			}
			if(!StringUtils.isNotBlank(invMaterialReqNos.toString())) 
				throw new AppException(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),"iscm.api.datePut.billNo.notExist"));
			Page page = new Page();
			page.setModelClass(ScmInvMaterialReqBill2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
					+ ScmInvMaterialReqBill2.FN_OTNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
									+ ScmInvMaterialReqBill2.FN_OTNO,
							QueryParam.QUERY_IN, invMaterialReqNos.toString()));
			page.getParam()
			.put(ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
					+ ScmInvMaterialReqBill2.FN_CONTROLUNITNO,
					new QueryParam(
							ClassUtils.getFinalModelSimpleName(ScmInvMaterialReqBill2.class) + "."
									+ ScmInvMaterialReqBill2.FN_CONTROLUNITNO,
							QueryParam.QUERY_EQ, param.getControlUnitNo()));
			List<String> arglist = new ArrayList<>();
			arglist.add("controlUnitNo=" + param.getControlUnitNo());
//			iscm 岸端单据
			List<ScmInvMaterialReqBill2> scmInvMaterialReqBills = scmInvMaterialReqBillBiz.queryPage(page,arglist, "findAllPage", param);
			for(InvMaterialReqListSParams invMaterialReqListSParam:invMaterialReqListSParams) {
				try {
					DataSyncResult dataSync = scmInvMaterialReqBillBiz.dataSync(invMaterialReqListSParam, scmInvMaterialReqBills, param);
					resultlist.add(dataSync);
				} catch (Exception e) {
					e.printStackTrace();
					DataSyncResult dataSyncResult = new DataSyncResult();
					dataSyncResult.setErrCode("-1");
					dataSyncResult.setErrText(Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
					if(StringUtils.isNotBlank(invMaterialReqListSParam.getOtNo()))
						dataSyncResult.setNo(invMaterialReqListSParam.getOtNo());
					resultlist.add(dataSyncResult);
				}
			}
			result.setResultList(resultlist);
        } catch (Exception e) {
    		log.error(e);
    		result.setErrCode("-1");
    		result.setErrText("领料出库单同步失败："+Message.getMessage((StringUtils.isBlank(integratedRequest.getLang())?"cn":integratedRequest.getLang()),e.getMessage()));
		}
        return result; 	
    }

	@ApiVersion(group="dataSyncApi")
    @ResponseBody
    @RequestMapping(value="/doAddScmPurRequireBillList", method=RequestMethod.POST)
    @ApiOperation(value="写入请购单", consumes="application/json",position=6)
    public DataSyncResultApi doAddScmPurRequireBillList(@RequestBody ScmPurRequireBillParamsApi params) {
		
		DataSyncResultApi ResultApi = new DataSyncResultApi();
		List<ScmPurRequireBillParam> ScmPurRequireBillParamList = params.getParams();
	    IntegratedRequest integratedRequest = new IntegratedRequest();
	    BeanUtils.copyProperties(params.getIntegratedRequest(), integratedRequest);	 
	    Param param = ParamHelper.createParam(integratedRequest,"doAddScmPurRequireBillList");
	    try {
	    ApiIntegratedRequest apiIntegratedRequest = params.getIntegratedRequest();
        BeanUtils.copyProperties(apiIntegratedRequest, integratedRequest);
        SecurityUtils.verify(integratedRequest);
		} catch (Exception e) {
			ResultApi.setErrCode("-1");
			ResultApi.setErrText("写入请购单报错:"+e.getMessage());
			return ResultApi;
		}
	    DataSyncResultApi resultApi = new DataSyncResultApi();
	    List<DataSyncResult> resultList = new ArrayList<DataSyncResult>();
	    
		for(ScmPurRequireBillParam RequireBill : ScmPurRequireBillParamList) {
			DataSyncResult result = new DataSyncResult();
			result.setNo(RequireBill.getPrNo());
			try {
			String orgUnitNo = RequireBill.getOrgUnitNo();
			String purOrgUnitNo = RequireBill.getPurOrgUnitNo();
			OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(orgUnitNo, param);
			if(orgAdmin==null||StringUtils.isBlank(orgAdmin.getOrgUnitNo())) {
				throw new Exception("申购组织异常:"+orgUnitNo);
			}
			OrgPurchase orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(purOrgUnitNo, param);
			if(orgPurchase==null||StringUtils.isBlank(orgPurchase.getOrgUnitNo())) {
				throw new Exception("采购组织异常:"+purOrgUnitNo);
			}
			//转换对象
			ScmPurRequire2 scmpur =convert(RequireBill,param);
			scmPurRequireBiz.dataSyncSave(scmpur, param);	
				
				
			} catch (Exception e) {
				e.printStackTrace();
				result.setErrCode("-1");
				result.setErrText(e.getMessage());
				
			}
			
			resultList.add(result);
		}
		resultApi.setResultList(resultList);
	    
		
		return resultApi;
	}

	/**
	 * 生成请购单
	 * @param requireBill
	 * @return
	 * @throws Exception 
	 */
	private ScmPurRequire2 convert(ScmPurRequireBillParam requireBill,Param param) throws Exception {
		ScmPurRequire2 bill = new ScmPurRequire2(true);
		if(requireBill.getReqDate().compareTo(requireBill.getApplyDate())!=1) {
			throw new Exception("需求日期需大于申购日期");
		}
		bill.setPrNo(requireBill.getPrNo());
		bill.setOrgUnitNo(requireBill.getOrgUnitNo());
		bill.setApplicants(requireBill.getCreator());
		bill.setApplyDate(requireBill.getApplyDate());
		bill.setBizType("1");
		bill.setBillType("0");
		bill.setFinOrgUnitNo(requireBill.getPurOrgUnitNo());
		bill.setPurOrgUnitNo(requireBill.getPurOrgUnitNo());
		bill.setCurrencyNo("RMB");
		bill.setExchangeRate(new BigDecimal(1));
		bill.setCreator(requireBill.getCreator());
		bill.setCreateDate(new Date());
		bill.setPrtcount(0);
		bill.setStatus("I");
		bill.setControlUnitNo(param.getControlUnitNo());
		bill.setRemarks(requireBill.getRemarks());
		bill.setReqDate(requireBill.getReqDate());
		bill.setSubscribeReason(requireBill.getSubscribeReason());
		bill.setPurRequireTheme(requireBill.getPurRequireTheme());
		bill.setRemarks(requireBill.getRemarks());
		List<ScmPurRequireEntry2> entryList = new ArrayList<ScmPurRequireEntry2>();
		for(int i=0;i<requireBill.getDetailList().size();i++) {
			ScmPurRequireDetailParams Detail=requireBill.getDetailList().get(i);
			ScmPurRequireEntry2 entry = new ScmPurRequireEntry2(true);
			ScmMaterial2 scmMaterial = scmMaterialBiz.selectByItemNo(param.getControlUnitNo(), Detail.getItemNo(), param);
			if(scmMaterial==null||scmMaterial.getId()==0) {
				throw new Exception("物资异常:"+ Detail.getItemNo());
			}
			ScmMaterial2 purunit = scmMaterialBiz.findByPurItemId(param.getControlUnitNo(), requireBill.getOrgUnitNo(), scmMaterial.getId(), param);
			entry.setLineId(i+1);
			entry.setReqDate(requireBill.getReqDate());
			entry.setOrgUnitNo(requireBill.getOrgUnitNo());
			entry.setItemId(scmMaterial.getId());
			entry.setPurUnit(purunit.getPurUnitId());
			entry.setBaseUnit(scmMaterial.getBaseUnitId());
			entry.setBaseQty(Detail.getQty());
			entry.setPieUnit(0);
			entry.setPieQty(BigDecimal.ZERO);
			entry.setPurOrgUnitNo(requireBill.getPurOrgUnitNo());
			entry.setRecStorageOrgUnitNo(requireBill.getPurOrgUnitNo());
			entry.setEntrusted(false);
			entry.setQty(new BigDecimal(123));
			entry.setReqDate(requireBill.getReqDate());
			entry.setOrderQty(BigDecimal.ZERO);
			entry.setPrice(Detail.getPrice());
			entry.setAmt(Detail.getAmt());
			entry.setSourceDtlId(0);
			entry.setPriceBillId(0);
			entry.setRefPriceStatus("0");
			entry.setRowStatus("I");
			entry.setFromSupplyInfo(false);
			entry.setSupplyCycle(0);
			entry.setIdle(false);
			entry.setPurRequireEntryAudit(false);
			entry.setItemNo(Detail.getItemNo());
			entry.setRemarks(Detail.getRemarks());
            //查看库存量及最近入库单价
            ScmMaterialPrice invScmMaterialPrice = getRecentPriceAndStockByReqOrgUnit(bill.getOrgUnitNo(), entry.getItemNo(),0, param);
            entry.setStockQty(invScmMaterialPrice==null || invScmMaterialPrice.getInvQty()==null?BigDecimal.ZERO:invScmMaterialPrice.getInvQty());
            entry.setRecentPrice(invScmMaterialPrice==null || invScmMaterialPrice.getPrice()==null?BigDecimal.ZERO:invScmMaterialPrice.getPrice());
			entryList.add(entry);
		}
		bill.setScmPurRequireEntryList(entryList);
		
		return bill;
	}	
	
	public  ScmMaterialPrice getRecentPriceAndStockByReqOrgUnit(String reqOrgUnitNo,String itemNo,long warehouseId, Param param) {
        try {
        
            List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV,reqOrgUnitNo,false,null,param);
            String invOrgUnitNo="0";
            if(orgStorageList!=null && !orgStorageList.isEmpty()){
                boolean exists=false;
                for(OrgStorage2 orgStorage:orgStorageList){
                    if(orgStorage.isDefault()){
                        invOrgUnitNo = orgStorage.getOrgUnitNo();
                        exists=true;
                        break;
                    }
                }
                if(!exists)
                    invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
            }
            ScmInvPriceQuery scmInvPriceQuery = new ScmInvPriceQuery();
            scmInvPriceQuery.setInvOrgUnitNo(invOrgUnitNo);
            scmInvPriceQuery.setReqOrgUnitNo(reqOrgUnitNo);
            scmInvPriceQuery.setItemNo(itemNo);
            scmInvPriceQuery.setWarehouseId(warehouseId);

            return scmMaterialPriceBiz.getRecentPriceAndStock(scmInvPriceQuery, param);
        } catch (Exception e) {
            return null;
        }
    }
}
