package com.armitage.server.iscm.common.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.common.dao.CommonEventHistoryDAO;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.CommonEventHistory2;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.util.ScmCommonBillUtil;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("commonEventHistoryBiz")
public class CommonEventHistoryBizImpl extends BaseBizImpl<CommonEventHistory> implements CommonEventHistoryBiz {
	private UsrBiz usrBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CodeBiz codeBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	@Override
	public void addEventHistory(BaseModel entity,CommonAuditOpinion commonAuditOpinion, Param param) throws AppException{
		if (entity != null) {
			String tableName = ClassUtils.getFinalModelSimpleName(entity);
			BaseModel baseModel = (BaseModel) entity;
			CommonEventHistory commonEventHistory = new CommonEventHistory(true);
			commonEventHistory.setBillId(baseModel.getPK());
			commonEventHistory.setStepNo(commonAuditOpinion.getStepNo());
			commonEventHistory.setPreStepNo(commonAuditOpinion.getPreStepNo());
			commonEventHistory.setActiveType(commonAuditOpinion.getActiveType());
			switch (commonAuditOpinion.getOpinion()) {
			case "agree":{
				commonEventHistory.setOpinion("Y");
				break;
			}
			case "refuse":{
				commonEventHistory.setOpinion("N");
				break;
			}
			default:
				commonEventHistory.setOpinion(commonAuditOpinion.getOpinion());
				break;
			}
			commonEventHistory.setHandlerContent(commonAuditOpinion.getHandlerContent());
			commonEventHistory.setRemarks(commonAuditOpinion.getRemarks());
			commonEventHistory.setOper(param.getUsrCode());
			commonEventHistory.setOperDate(CalendarUtil.getDate(param));
			((CommonEventHistoryDAO)dao).saveEventHistory(tableName, commonEventHistory);
		}
	}
	@Override
	public CommonEventHistory2 loadAuditEventHistory(String tableName, String stepNo,long billId,Param param) throws AppException {
		return ((CommonEventHistoryDAO)dao).loadAuditEventHistory(tableName, stepNo, billId);
	}

	@Override
	public List<CommonEventHistory2> loadAuditEventHistory(String tableName, long billId,boolean onlyAudit, Param param)
			throws AppException {
		List<CommonEventHistory2> commonEventHistoryList =  ((CommonEventHistoryDAO)dao).loadAuditEventHistory(tableName, billId,onlyAudit);
		if(commonEventHistoryList!=null && !commonEventHistoryList.isEmpty()) {
			for(CommonEventHistory2 commonEventHistory:commonEventHistoryList) {
				if(StringUtils.isNotBlank(commonEventHistory.getOper())) {
					Usr usr = usrBiz.selectByCode(commonEventHistory.getOper(), param);
					if(usr!=null) {
						commonEventHistory.setOperName(usr.getName());
					}
				}
			}
		}
		return commonEventHistoryList;
	}
	@Override
	public List<CommonEventHistory2> loadAuditStatus(String tableName, String billTypeCode, long billId, Param param)
			throws AppException {
		List<CommonEventHistory2> commonEventHistoryList = this.loadAuditEventHistory(tableName, billId, false, param);
		if(commonEventHistoryList==null)
			commonEventHistoryList = new ArrayList();
		ScmBillPending2 scmBillPending = scmBillPendingBiz.selectPendingUsr(billId,billTypeCode,param);
		if(scmBillPending!=null) {
			if(StringUtils.isNoneBlank(scmBillPending.getUsrCodes())){
				String[] codes=StringUtils.split(scmBillPending.getUsrCodes(), ",");
				StringBuffer usrName= new StringBuffer("");
				for(String code:codes) {
					Usr usr = usrBiz.selectByCode(code, param);
					if(usr!=null) {
						if(StringUtils.isNoneBlank(usrName.toString()))
							usrName.append(",");
						usrName.append(usr.getName());
					}
				}
				CommonEventHistory2 commonEventHistory= new CommonEventHistory2(true);
				commonEventHistory.setOperName(usrName.toString());
				commonEventHistory.setActiveType("D");
				commonEventHistoryList.add(commonEventHistory);
			}
		}
		if(commonEventHistoryList!=null && !commonEventHistoryList.isEmpty()) {
			for(CommonEventHistory2 commonEventHistory:commonEventHistoryList) {
				if(StringUtils.isNotBlank(commonEventHistory.getOpinion())) {
					Code code = codeBiz.selectByCategoryAndCode("SCM_opinion", commonEventHistory.getOpinion());
					if(code!=null) {
						commonEventHistory.setOpinionName(code.getName());
					}
				}
				if(StringUtils.isNotBlank(commonEventHistory.getActiveType())) {
					Code code = codeBiz.selectByCategoryAndCode("SCM_activeType", commonEventHistory.getActiveType());
					if(code!=null) {
						commonEventHistory.setActiveTypeName(code.getName());
					}
				}
			}
		}
		return commonEventHistoryList;
	}
	@Override
	public List<CommonEventHistory2> loadAuditStatus(BaseModel entity, Param param) throws AppException {
		if (entity != null) {
			String tableName = ClassUtils.getFinalModelSimpleName(entity);
			long billId = entity.getPK();
			String billType = "";
			switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(entity.getClass()))) {
				case "scmpurquotation": {
					// 报价单
					billType = "PurQuotation";
					break;
				}
				case "scmpurprice": {
					// 定价单
					billType = "ScmPurPrice";
					break;
				}
				case "scmpurrequire": {
					// 请购单
					billType = "PurRequire";
					break;
				}
				case "scmpurorder": {
					// 订货单
					billType = "PurOrder";
					break;
				}
				case "scmpurreceive": {
					// 收货单
					billType = "PurReceive";
					break;
				}
				case "scmpurreturns": {
					// 退货申请
					billType = "PurReturns";
					break;
				}
				case "scminvmaterialrequestbill": {
					// 领料申请
					billType = "InvMatReqBill";
					break;
				}
				case "scminvpurinwarehsbill": {
					// 采购入库
					billType = "InvPurInWhsBill";
					break;
				}
				case "scminvmaterialreqbill": {
					// 领料出库
					billType = "InvMatReqout";
					break;
				}
				case "scminvsaleorder": {
					// 销售订单
					billType = "InvSaleOrder";
					break;
				}
				case "scminvmovebill": {
					// 成本转移
					billType = "InvMoveBill";
					break;
				}
				case "scminvotherissuebill": {
					// 其他出库
					billType = "InvOthIssueBill";
					break;
				}
				case "scmcstfrmloss": {
					// 成本中心报损
					billType = "CstFrmLoss";
					break;
				}
				case "appaymentbill":{
					billType = "ApPaymentBill"; 
					break;
				}
				case "scmqualifieinfo":{
					// 资质申请单
					tableName = "ScmSupplierQualifieInfoBill";
					break;
				}
			}
			return loadAuditStatus(tableName, billType, billId, param);
		}
		return null;
	}
	@Override
	public void updateInvalid(BaseModel entity, String stepNo, Param param) throws AppException {
		if (entity != null) {
			String tableName = ClassUtils.getFinalModelSimpleName(entity);
			((CommonEventHistoryDAO)dao).updateInvalid(tableName, entity.getPK(),stepNo);
		}
	}
	@Override
	public List<CommonEventHistory2> loadAuditStatus(String billTypeCode, String billCode, Param param)
			throws AppException {
		String tableName="";
		switch (StringUtils.lowerCase(billTypeCode)) {
			case "purquotation": {
				// 报价单
				tableName = "ScmPurQuotation";
				break;
			}
			case "scmpurprice": {
				// 定价单
				tableName = "ScmPurPrice";
				break;
			}
			case "purrequire": {
				// 请购单
				tableName = "ScmPurRequire";
				break;
			}
			case "purorder": {
				// 订货单
				tableName = "ScmPurOrder";
				break;
			}
			case "purreceive": {
				// 收货单
				tableName = "ScmPurReceive";
				break;
			}
			case "purreturns": {
				// 退货申请
				tableName = "ScmPurReturns";
				break;
			}
			case "invmatreqbill": {
				// 领料申请
				tableName = "ScmInvMaterialRequestBill";
				break;
			}
			case "invpurinwhsbill": {
				// 采购入库
				tableName = "ScmInvPurInWarehsBill";
				break;
			}
			case "invmatreqout": {
				// 领料出库
				tableName = "ScmInvMaterialReqBill";
				break;
			}
			case "invsaleorder": {
				// 销售订单
				tableName = "ScmInvSaleOrder";
				break;
			}
			case "invmovebill": {
				// 成本转移
				tableName = "ScmInvMoveBill";
				break;
			}
			case "invothissuebill": {
				// 其他出库
				tableName = "ScmInvOtherIssueBill";
				break;
			}
			case "appaymentbill":{
				tableName = "ApPaymentBill"; 
				break;
			}
			case "scmqualifieinfo":{
				// 资质申请单
				tableName = "ScmSupplierQualifieInfoBill";
				break;
			}
			case "pursuppliersrc":{
				// 供应商寻源
				tableName = "ScmPurSupplierSource";
				break;
			}
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billType", billTypeCode);
		map.put("billNo", billCode);
		map.put("controlUnitNo", param.getControlUnitNo());
		List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
		if(scmBillPendingList!=null && !scmBillPendingList.isEmpty()) {
			return loadAuditStatus(tableName,billTypeCode, scmBillPendingList.get(0).getBillId(),param);
		}else{
			ScmCommonBillUtil scmCommonBillUtil = new ScmCommonBillUtil();
			long billId = scmCommonBillUtil.getBillId(billTypeCode, billCode, param);
			if(billId > 0){
				return loadAuditStatus(tableName,billTypeCode, billId,param);
			}
		}
		return null;
	}

}

