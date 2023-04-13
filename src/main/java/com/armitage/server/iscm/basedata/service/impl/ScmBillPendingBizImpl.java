
package com.armitage.server.iscm.basedata.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.iaps.daily.model.ApPaymentBill2;
import com.armitage.server.iscm.basedata.dao.ScmBillPendingDAO;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmBillPendingAdvQuery;
import com.armitage.server.iscm.basedata.model.ScmBillPendingToUsr;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingToUsrBiz;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLoss2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvOtherIssueBill2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturns2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.BillTypeSetting;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.BillTypeSettingBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmBillPendingBiz")
public class ScmBillPendingBizImpl extends BaseBizImpl<ScmBillPending> implements ScmBillPendingBiz {
	private UsrBiz usrBiz;
	private BillTypeBiz billTypeBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmBillPendingToUsrBiz scmBillPendingToUsrBiz;
	private BillTypeSettingBiz billTypeSettingBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public UsrBiz getUsrBiz() {
		return usrBiz;
	}

	public void setScmBillPendingToUsrBiz(ScmBillPendingToUsrBiz scmBillPendingToUsrBiz) {
		this.scmBillPendingToUsrBiz = scmBillPendingToUsrBiz;
	}
	
	public void setBillTypeSettingBiz(BillTypeSettingBiz billTypeSettingBiz) {
		this.billTypeSettingBiz = billTypeSettingBiz;
	}

	@Override
	protected void afterDelete(ScmBillPending entity, Param param) throws AppException {
		List<ScmBillPendingToUsr> scmBillPendingToUsrList =  scmBillPendingToUsrBiz.selectByPendingId(entity.getId(),param);
		if(scmBillPendingToUsrList!=null && !scmBillPendingToUsrList.isEmpty())
			scmBillPendingToUsrBiz.delete(scmBillPendingToUsrList, param);
	}

	@Override
	public int checkExistPendingBill(String usrCode, Param param) throws AppException {
		HashMap<String,Object> map =new HashMap<String,Object>();
		map.put("usrCode",usrCode);
		map.put("controlUnitNo",param.getControlUnitNo());
		return ((ScmBillPendingDAO)dao).checkExistPendingBill(map);
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("usrCode",param.getUsrCode());
		return map;
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(ScmBillPending scmBillPending:(List<ScmBillPending>)list){
				setConvertMap(scmBillPending,param);
			}
		}
	}

	private void setConvertMap(ScmBillPending scmBillPending,Param param){
		if(scmBillPending!=null) {
			if(StringUtils.isNotBlank(scmBillPending.getOrgUnitNo())) {
				OrgBaseUnit2 orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmBillPending.getOrgUnitNo(), param);
				if(orgBaseUnit!=null)
					scmBillPending.setConvertMap(ScmBillPending.FN_ORGUNITNO, orgBaseUnit);
			}
			if(StringUtils.isNotBlank(scmBillPending.getBillType())) {
				BillType2 billType = billTypeBiz.selectByBillCode(scmBillPending.getBillType(), param);
				if(billType!=null)
					scmBillPending.setConvertMap(scmBillPending.FN_BILLTYPE, billType);
			}
			if(StringUtils.isNotBlank(scmBillPending.getChecker())) {
				Usr usr = usrBiz.selectByCode(scmBillPending.getChecker(), param);
				if(usr!=null)
					scmBillPending.setConvertMap(ScmBillPending.FN_CHECKER, usr);
			}
			if(StringUtils.isNotBlank(scmBillPending.getConfirmor())) {
				Usr usr = usrBiz.selectByCode(scmBillPending.getConfirmor(), param);
				if(usr!=null)
					scmBillPending.setConvertMap(ScmBillPending.FN_CONFIRMOR, usr);
			}
		}
	}

	@Override
	public void addPendingBill(String usrCodes, BaseModel model, Param param) throws AppException {
		if(StringUtils.isNotBlank(usrCodes)) {
			String billNo="";
			String billType="";
			String orgUnitNo="";
			Date billDate=null;
			String checker="";
			String confirmor="";
			Date submitDate=CalendarUtil.getDate(param);
			String remarks="";
			switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(model.getClass()))) {
			 	case "scmpursuppliersource":{
		            //报价单
		        	billNo = ((ScmPurSupplierSource2)model).getBillNo();
		        	billType="PurSupplierSrc";
		        	orgUnitNo = ((ScmPurSupplierSource2)model).getOrgUnitNo();
		        	billDate = ((ScmPurSupplierSource2)model).getBizDate();
		        	checker = ((ScmPurSupplierSource2)model).getChecker();
		        	confirmor = ((ScmPurSupplierSource2)model).getSubmitter();
		        	submitDate = ((ScmPurSupplierSource2)model).getSubmitDate();
		            break;
		        }
		        case "scmpurquotation":{
		            //报价单
		        	billNo = ((ScmPurQuotation2)model).getPqNo();
		        	billType="PurQuotation";
		        	orgUnitNo = ((ScmPurQuotation2)model).getOrgUnitNo();
		        	billDate = ((ScmPurQuotation2)model).getPqDate();
		        	checker = ((ScmPurQuotation2)model).getChecker();
		        	confirmor = ((ScmPurQuotation2)model).getSubmitter();
		        	submitDate = ((ScmPurQuotation2)model).getSubmitDate();
		        	if (StringUtils.isNotBlank(((ScmPurQuotation2)model).getVendorName())) {
		        		remarks = ((ScmPurQuotation2)model).getVendorName() + "(" + Message.getMessage(param.getLang(), 
		                    "field.ScmBillPending.remarks.vendorName") + ")";
		        	}
		            break;
		        }
		        case "scmpurprice":{
		            //定价单
		        	billNo = ((ScmPurPrice2)model).getPmNo();
		        	billType="ScmPurPrice";
		        	orgUnitNo = ((ScmPurPrice2)model).getOrgUnitNo();
		        	billDate = ((ScmPurPrice2)model).getPmDate();
		        	checker = ((ScmPurPrice2)model).getChecker();
		        	confirmor = ((ScmPurPrice2)model).getSubmitter();
		        	submitDate = ((ScmPurPrice2)model).getSubmitDate();
		        	if (StringUtils.isNotBlank(((ScmPurPrice2)model).getSelVndrName())) {
		        		remarks = ((ScmPurPrice2)model).getSelVndrName() + "(" + Message.getMessage(param.getLang(), 
		                    "field.ScmBillPending.remarks.vendorName") + ")";
		        	}
		            break;
		        }
		        case "scmpurrequire":{
		            //请购单
		        	billNo = ((ScmPurRequire2)model).getPrNo();
		        	billType="PurRequire";
		        	orgUnitNo = ((ScmPurRequire2)model).getOrgUnitNo();
		        	billDate = ((ScmPurRequire2)model).getApplyDate();
		        	checker = ((ScmPurRequire2)model).getChecker();
		        	confirmor = ((ScmPurRequire2)model).getSubmitter();
		        	submitDate = ((ScmPurRequire2)model).getSubmitDate();
		        	break;
		        }
		        case "scmpurorder":{
		            //订货单
		        	billNo = ((ScmPurOrder2)model).getPoNo();
		        	billType="PurOrder";
		        	orgUnitNo = ((ScmPurOrder2)model).getOrgUnitNo();
		        	billDate = ((ScmPurOrder2)model).getOrderDate();
		        	checker = ((ScmPurOrder2)model).getChecker();
		        	confirmor = ((ScmPurOrder2)model).getSubmitter();
		        	submitDate = ((ScmPurOrder2)model).getSubmitDate();
		            break;
		        }
		        case "scmpurreceive":{
		            //收货单
		        	billNo = ((ScmPurReceive2)model).getPvNo();
		        	billType="PurReceive";
		        	orgUnitNo = ((ScmPurReceive2)model).getOrgUnitNo();
		        	billDate = ((ScmPurReceive2)model).getReceiveDate();
		        	checker = ((ScmPurReceive2)model).getChecker();
		        	confirmor = ((ScmPurReceive2)model).getSubmitter();
		        	submitDate = ((ScmPurReceive2)model).getSubmitDate();
		        	if (StringUtils.isNotBlank(((ScmPurReceive2)model).getVendorName())) {
		        		remarks = ((ScmPurReceive2)model).getVendorName()+ "(" + Message.getMessage(param.getLang(), 
		                    "field.ScmBillPending.remarks.vendorName") + ")";
		        	}
		            break;
		        }
		        case "scmpurreturns":{
		            //退货申请
		        	billNo = ((ScmPurReturns2)model).getRtNo();
		        	billType="PurReturns";
		        	orgUnitNo = ((ScmPurReturns2)model).getOrgUnitNo();
		        	billDate = ((ScmPurReturns2)model).getBizDate();
		        	checker = ((ScmPurReturns2)model).getChecker();
		        	confirmor = ((ScmPurReturns2)model).getSubmitter();
		        	submitDate = ((ScmPurReturns2)model).getSubmitDate();
		        	if (StringUtils.isNotBlank(((ScmPurReturns2)model).getVendorName())) {
		        		remarks = ((ScmPurReturns2)model).getVendorName()+ "(" + Message.getMessage(param.getLang(), 
		                    "field.ScmBillPending.remarks.vendorName") + ")";
		        	}
		            break;
		        }
		        case "scminvmaterialrequestbill":{
		            //领料申请
		        	billNo = ((ScmInvMaterialRequestBill2)model).getReqNo();
		        	billType="InvMatReqBill";
		        	orgUnitNo = ((ScmInvMaterialRequestBill2)model).getOrgUnitNo();
		        	billDate = ((ScmInvMaterialRequestBill2)model).getBizDate();
		        	checker = ((ScmInvMaterialRequestBill2)model).getChecker();
		        	confirmor = ((ScmInvMaterialRequestBill2)model).getSubmitter();
		        	submitDate = ((ScmInvMaterialRequestBill2)model).getSubmitDate();
		            break;
		        }
		        case "scminvpurinwarehsbill":{
		            //采购入库
		        	billNo = ((ScmInvPurInWarehsBill2)model).getWrNo();
		        	billType="InvPurInWhsBill";
		        	orgUnitNo = ((ScmInvPurInWarehsBill2)model).getOrgUnitNo();
		        	billDate = ((ScmInvPurInWarehsBill2)model).getBizDate();
		        	checker = ((ScmInvPurInWarehsBill2)model).getChecker();
		        	confirmor = ((ScmInvPurInWarehsBill2)model).getSubmitter();
		        	submitDate = ((ScmInvPurInWarehsBill2)model).getSubmitDate();
		        	if (StringUtils.isNotBlank(((ScmInvPurInWarehsBill2)model).getVendorName())) {
		        		remarks = ((ScmInvPurInWarehsBill2)model).getVendorName()+ "(" + Message.getMessage(param.getLang(), 
		                    "field.ScmBillPending.remarks.vendorName") + ")";
		        	}
		            break;
		        }
		        case "scminvmaterialreqbill":{
		            //领料出库
		        	billNo = ((ScmInvMaterialReqBill2)model).getOtNo();
		        	billType="InvMatReqout";
		        	orgUnitNo = ((ScmInvMaterialReqBill2)model).getOrgUnitNo();
		        	billDate = ((ScmInvMaterialReqBill2)model).getBizDate();
		        	checker = ((ScmInvMaterialReqBill2)model).getChecker();
		        	confirmor = ((ScmInvMaterialReqBill2)model).getSubmitter();
		        	submitDate = ((ScmInvMaterialReqBill2)model).getSubmitDate();
		        	if (StringUtils.isNotBlank(((ScmInvMaterialReqBill2)model).getUseOrgUnitName())) {
		        		remarks = ((ScmInvMaterialReqBill2)model).getUseOrgUnitName()+ "(" + Message.getMessage(param.getLang(), 
		                    "field.ScmBillPending.remarks.useOrgUnitNo") + ")";
		        	}
		            break;
		        }
		        case "scminvsaleorder":{
		            //销售订单
		        	billNo = ((ScmInvSaleOrder2)model).getOtNo();
		        	billType="InvSaleOrder";
		        	orgUnitNo = ((ScmInvSaleOrder2)model).getOrgUnitNo();
		        	billDate = ((ScmInvSaleOrder2)model).getBizDate();
		        	checker = ((ScmInvSaleOrder2)model).getChecker();
		        	confirmor = ((ScmInvSaleOrder2)model).getSubmitter();
		        	submitDate = ((ScmInvSaleOrder2)model).getSubmitDate();
		            break;
		        }
		        case "scminvmovebill":{
		            //成本转移
		        	billNo = ((ScmInvMoveBill2)model).getWtNo();
		        	billType="InvMoveBill";
		        	orgUnitNo = ((ScmInvMoveBill2)model).getOutCstOrgUnitNo();
		        	billDate = ((ScmInvMoveBill2)model).getBizDate();
		        	checker = ((ScmInvMoveBill2)model).getChecker();
		        	confirmor = ((ScmInvMoveBill2)model).getSubmitter();
		        	submitDate = ((ScmInvMoveBill2)model).getSubmitDate();
		            break;
		        }
		        case "scminvotherissuebill":{
		            //其他出库
		        	billNo = ((ScmInvOtherIssueBill2)model).getOtNo();
		        	billType="InvOthIssueBill";
		        	orgUnitNo = ((ScmInvOtherIssueBill2)model).getOrgUnitNo();
		        	billDate = ((ScmInvOtherIssueBill2)model).getBizDate();
		        	checker = ((ScmInvOtherIssueBill2)model).getChecker();
		        	confirmor = ((ScmInvOtherIssueBill2)model).getSubmitter();
		        	submitDate = ((ScmInvOtherIssueBill2)model).getSubmitDate();
		            break;
		        }
		        case "scmcstfrmloss":{
		            //成本中心报损单
		        	billNo = ((ScmCstFrmLoss2)model).getBillNo();
		        	billType="CstFrmLoss";
		        	orgUnitNo = ((ScmCstFrmLoss2)model).getOrgUnitNo();
		        	billDate = ((ScmCstFrmLoss2)model).getBizDate();
		        	checker = ((ScmCstFrmLoss2)model).getChecker();
		        	confirmor = ((ScmCstFrmLoss2)model).getSubmitter();
		        	submitDate = ((ScmCstFrmLoss2)model).getSubmitDate();
		            break;
		        }
		        case "appaymentbill":{
		            //付款单
		        	billNo = ((ApPaymentBill2)model).getBillNo();
		        	billType="ApPaymentBill";
		        	orgUnitNo = ((ApPaymentBill2)model).getOrgUnitNo();
		        	billDate = ((ApPaymentBill2)model).getBillDate();
		        	checker = ((ApPaymentBill2)model).getChecker();
		        	confirmor = ((ApPaymentBill2)model).getSubmitter();
		        	submitDate = ((ApPaymentBill2)model).getSubmitDate();
		            break;
		        }
		        case "scmsupplierqualifieinfobill":{	
		        	//供应商资质申请
		        	billNo = ((ScmSupplierQualifieInfoBill)model).getBillNo();
		        	billType="ScmQualifieInfo";
		        	orgUnitNo = ((ScmSupplierQualifieInfoBill)model).getControlUnitNo();
		        	billDate = ((ScmSupplierQualifieInfoBill)model).getBizDate();
		        	checker = ((ScmSupplierQualifieInfoBill)model).getChecker();
		        	confirmor = ((ScmSupplierQualifieInfoBill)model).getSubmitter();
		        	submitDate = ((ScmSupplierQualifieInfoBill)model).getSubmitDate();
		            break;
		        }
		        case "scminvsaleissuebill":{	
		        	//销售出库
		        	billNo = ((ScmInvSaleIssueBill2)model).getOtNo();
		        	billType="InvSaleIssueBill";
		        	orgUnitNo = ((ScmInvSaleIssueBill2)model).getOrgUnitNo();
		        	billDate = ((ScmInvSaleIssueBill2)model).getBizDate();
		        	checker = ((ScmInvSaleIssueBill2)model).getChecker();
		        	confirmor = ((ScmInvSaleIssueBill2)model).getSubmitter();
		        	submitDate = ((ScmInvSaleIssueBill2)model).getSubmitDate();
		            break;
		        }
		    }
			ScmBillPending scmBillPending = new ScmBillPending();
			scmBillPending.setBillId(model.getPK());
			scmBillPending.setBillNo(billNo);
			scmBillPending.setBillType(billType);
			scmBillPending.setOrgUnitNo(orgUnitNo);
			scmBillPending.setBillDate(billDate);
			scmBillPending.setChecker(checker);
			scmBillPending.setConfirmor(confirmor);
			scmBillPending.setSubmitDate(submitDate);
			scmBillPending.setRemarks(remarks);
			scmBillPending.setControlUnitNo(param.getControlUnitNo());
			scmBillPending = this.add(scmBillPending, param);
			String[] usrList = StringUtils.split(usrCodes, ",");
			List<ScmBillPendingToUsr> scmBillPendingToUsrList = new ArrayList();
			for(String usrCode:usrList) {
				ScmBillPendingToUsr scmBillPendingToUsr = new ScmBillPendingToUsr(true);
				scmBillPendingToUsr.setUsrCode(usrCode);
				scmBillPendingToUsr.setPendingId(scmBillPending.getId());
				scmBillPendingToUsrList.add(scmBillPendingToUsr);
			}
			scmBillPendingToUsrBiz.add(scmBillPendingToUsrList, param);
		}
	}

	@Override
	public void updatePendingBill(String usrCode, BaseModel model, Param param) throws AppException {
		String billType = "";
		switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(model.getClass()))) {
			case "scmpursuppliersource": {
				// 供应商寻源单
				billType = "PurSupplierSrc";
				break;
			}
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
				// 成本中心报损
				billType = "ApPaymentBill";
				break;
			}
			case "scmsupplierqualifieinfobill":{
				// 供应商资质申请
				billType = "ScmQualifieInfo";
				break;
			}
			case "scminvsaleissuebill":{
				// 供应商资质申请
				billType = "InvSaleIssueBill";
				break;
			}
		}
		ScmBillPending scmBillPending = selectByUsrCode(usrCode,model.getPK(),billType,param);
 		if(scmBillPending!=null) {
			boolean processed = true;
			if(scmBillPending.isCounterSign()) {
				//会签，即每个审批人都需要审批（预留，审批流设置未实现）
				scmBillPendingToUsrBiz.updateUsrProcessed(scmBillPending.getId(),usrCode,param);
				List<ScmBillPendingToUsr> scmBillPendingToUsrList = scmBillPendingToUsrBiz.selectByPendingId(scmBillPending.getId(),param);
				if(scmBillPendingToUsrList!=null && !scmBillPendingToUsrList.isEmpty()) {
					for(ScmBillPendingToUsr scmBillPendingToUsr :scmBillPendingToUsrList) {
						if(!StringUtils.equals(scmBillPendingToUsr.getUsrCode(),usrCode) && !scmBillPendingToUsr.isProcessed())
							processed=false;
					}
				}
			}else {
				//不是会签时，一人审批则把所有同步骤的用户都标记为已处理
				scmBillPendingToUsrBiz.updateProcessed(scmBillPending.getId(),param);
			}
			if(processed) {
				scmBillPending.setProcessed(true);
				this.updateDirect(scmBillPending, param);
			}
		} else {
			throw new AppException("com.armitage.server.iscm.basedata.service.impl.error.ScmBillPendingBizImpl.updatePendingBill.notUsr");
		}
	}

	@Override
	public void cancelPendingBill(String usrCode, BaseModel model, Param param) throws AppException {
		String billType = "";
		String status = "";
		switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(model.getClass()))) {
			case "scmpursuppliersource": {
				// 供应商寻源
				billType = "PurSupplierSrc";
				status = ((ScmPurSupplierSource2)model).getStatus();
				break;
			}
			case "scmpurquotation": {
				// 报价单
				billType = "PurQuotation";
				status = ((ScmPurQuotation2)model).getStatus();
				break;
			}
			case "scmpurprice": {
				// 定价单
				billType = "ScmPurPrice";
				status = ((ScmPurPrice2)model).getStatus();
				break;
			}
			case "scmpurrequire": {
				// 请购单
				billType = "PurRequire";
				status = ((ScmPurRequire2)model).getStatus();
				break;
			}
			case "scmpurorder": {
				// 订货单
				billType = "PurOrder";
				status = ((ScmPurOrder2)model).getStatus();
				break;
			}
			case "scmpurreceive": {
				// 收货单
				billType = "PurReceive";
				status = ((ScmPurReceive2)model).getStatus();
				break;
			}
			case "scmpurreturns": {
				// 退货申请
				billType = "PurReturns";
				status = ((ScmPurReturns2)model).getStatus();
				break;
			}
			case "scminvmaterialrequestbill": {
				// 领料申请
				billType = "InvMatReqBill";
				status = ((ScmInvMaterialRequestBill2)model).getStatus();
				break;
			}
			case "scminvpurinwarehsbill": {
				// 采购入库
				billType = "InvPurInWhsBill";
				status = ((ScmInvPurInWarehsBill2)model).getStatus();
				break;
			}
			case "scminvmaterialreqbill": {
				// 领料出库
				billType = "InvMatReqout";
				status = ((ScmInvMaterialReqBill2)model).getStatus();
				break;
			}
			case "scminvsaleorder": {
				// 销售订单
				billType = "InvSaleOrder";
				status = ((ScmInvSaleOrder2)model).getStatus();
				break;
			}
			case "scminvmovebill": {
				// 成本转移
				billType = "InvMoveBill";
				status = ((ScmInvMoveBill2)model).getStatus();
				break;
			}
			case "scminvotherissuebill": {
				// 其他出库
				billType = "InvOthIssueBill";
				status = ((ScmInvOtherIssueBill2)model).getStatus();
				break;
			}
			case "scmcstfrmloss": {
				// 成本中心报损
				billType = "CstFrmLoss";
				status = ((ScmCstFrmLoss2)model).getStatus();
				break;
			}
			case "appaymentbill": {
				// 成本中心报损
				billType = "ApPaymentBill";
				status = ((ApPaymentBill2)model).getStatus();
				break;
			}
			case "scmsupplierqualifieinfobill":{
				// 供应商资质申请
				billType = "ScmQualifieInfo";
				status = ((ScmSupplierQualifieInfoBill)model).getStatus();
				break;
			}
			case "scminvsaleissuebill":{
				// 销售出库
				billType = "InvSaleIssueBill";
				status = ((ScmInvSaleIssueBill2)model).getStatus();
				break;
			}
		}
		this.deleteLastUsrPending(model.getPK(),billType,param);
		ScmBillPending scmBillPending = selectLastUsrPended(usrCode,model.getPK(),billType,param);
		if(scmBillPending!=null && !StringUtils.equals(status, "I")) {
			boolean falg = true;
			if(scmBillPending.isCounterSign()) {
				//会签，即每个审批人都需要审批（预留，审批流设置未实现）
				scmBillPendingToUsrBiz.cancelUsrProcessed(scmBillPending.getId(),usrCode,param);
				List<ScmBillPendingToUsr> scmBillPendingToUsrList = scmBillPendingToUsrBiz.selectByPendingId(scmBillPending.getId(),param);
				if(scmBillPendingToUsrList!=null && !scmBillPendingToUsrList.isEmpty()) {
					for(ScmBillPendingToUsr scmBillPendingToUsr :scmBillPendingToUsrList) {
						if(!StringUtils.equals(scmBillPendingToUsr.getUsrCode(),usrCode) && !scmBillPendingToUsr.isProcessed())
							falg=false;
					}
				}
			}else {
				//不是会签时，一人审批则把所有同步骤的用户都标记为已处理
				scmBillPendingToUsrBiz.cancelProcessed(scmBillPending.getId(),param);
			}
			if(falg) {
				scmBillPending.setProcessed(false);
				this.updateDirect(scmBillPending, param);
			}
		}
	}

	private void deleteLastUsrPending(long billId, String billType, Param param) {
		ScmBillPending scmBillPending = this.selectLastUsrPending(billId, billType, param);
		if(scmBillPending!=null)
			this.delete(scmBillPending, param);
	}

	@Override
	public ScmBillPending selectByUsrCode(String usrCode, long billId, String billType, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("usrCode", usrCode);
		map.put("billId", billId);
		map.put("billType", billType);
		return ((ScmBillPendingDAO)dao).selectByUsrCode(map);
	}

	@Override
	public ScmBillPending selectLastUsrPended(String usrCode, long billId, String billType, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("usrCode", usrCode);
		map.put("billId", billId);
		map.put("billType", billType);
		return ((ScmBillPendingDAO)dao).selectLastUsrPended(map);
	}

	@Override
	public ScmBillPending selectLastUsrPending(long billId, String billType, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billId", billId);
		map.put("billType", billType);
		return ((ScmBillPendingDAO)dao).selectLastUsrPending(map);
	}

	@Override
	public ScmBillPending2 selectPendingUsr(long billId, String billType, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billId", billId);
		map.put("billType", billType);
		return ((ScmBillPendingDAO)dao).selectPendingUsr(map);
	}

	@Override
	public List<ScmBillPending2> queryPendingBill(ScmBillPendingAdvQuery scmBillPendingAdvQuery,int pageNum, Param param) throws AppException {
		List<ScmBillPending2> rtnList = new ArrayList();
		Page page=new Page();
		page.setModelClass(ScmBillPending.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		List<String> arglist = new ArrayList();
		arglist.add("usrCode="+scmBillPendingAdvQuery.getUsrCode());
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		if(StringUtils.equals(scmBillPendingAdvQuery.getType(), "D")) {
			//待审
			page.setSqlCondition("ScmBillPendingToUsr.processed=0");
		}else {
			//已审 ：A
			page.setSqlCondition("ScmBillPendingToUsr.processed=1");
		}
		
		if(scmBillPendingAdvQuery.getSubmitDateFrom()!=null){
			if(scmBillPendingAdvQuery.getSubmitDateTo()!=null) {
				page.getParam().put(ScmBillPending2.FN_SUBMITDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_SUBMITDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmBillPendingAdvQuery.getSubmitDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmBillPendingAdvQuery.getSubmitDateTo(),1))));
			}else {
				page.getParam().put(ScmBillPending2.FN_SUBMITDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_SUBMITDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmBillPendingAdvQuery.getSubmitDateFrom())));
			}
		}else if(scmBillPendingAdvQuery.getSubmitDateTo()!=null) {
			page.getParam().put(ScmBillPending2.FN_SUBMITDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_SUBMITDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmBillPendingAdvQuery.getSubmitDateTo(),1))));
		}
		
		if(StringUtils.isNotBlank(scmBillPendingAdvQuery.getBillType())){
			page.getParam().put(ScmBillPending2.FN_BILLTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_BILLTYPE, QueryParam.QUERY_EQ,scmBillPendingAdvQuery.getBillType()));
		}
		
		List<ScmBillPending> scmBillPendingList = this.queryPage(page, arglist, "findAllPage", param);
		
		if(scmBillPendingList!=null && !scmBillPendingList.isEmpty()) {
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			for(ScmBillPending scmBillPending:scmBillPendingList) {
				ScmBillPending2 scmBillPending2 = new ScmBillPending2(true);
				BeanUtil.copyProperties(scmBillPending2, scmBillPending);
				if(StringUtils.isNotBlank(scmBillPending2.getBillType())) {
					BillType2 billType = (BillType2) cacheMap.get(ClassUtils.getFinalModelSimpleName(BillType2.class)+"_"+scmBillPending2.getBillType());
					if(billType==null) {
						billType = billTypeBiz.selectByBillCode(scmBillPending2.getBillType(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(BillType2.class)+"_"+scmBillPending2.getBillType(), billType);
					}
					if(billType!=null)
						scmBillPending2.setBillTypeName(billType.getBillName());
				}
				if(StringUtils.isNotBlank(scmBillPending2.getOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = (OrgBaseUnit2) cacheMap.get(ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class)+"_"+scmBillPending2.getOrgUnitNo());
					if(orgBaseUnit == null) {
						orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmBillPending2.getOrgUnitNo(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class)+"_"+scmBillPending2.getOrgUnitNo(), orgBaseUnit);
					}
					if(orgBaseUnit!=null)
						scmBillPending2.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
				if(StringUtils.isNotBlank(scmBillPending2.getChecker())) {
					Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmBillPending2.getChecker());
					if(usr == null) {
						usr = usrBiz.selectByCode(scmBillPending2.getChecker(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmBillPending2.getChecker(), usr);
					}
					if(usr!=null)
						scmBillPending2.setCheckerName(usr.getName());
				}
				if(StringUtils.isNotBlank(scmBillPending2.getConfirmor())) {
					Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmBillPending2.getConfirmor());
					if(usr == null) {
						usr = usrBiz.selectByCode(scmBillPending2.getConfirmor(), param);
						cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+scmBillPending2.getConfirmor(), usr);
					}
					if(usr!=null)
						scmBillPending2.setConfirmorName(usr.getName());
				}
				rtnList.add(scmBillPending2);
			}
		}
		
		if(StringUtils.isNotBlank(scmBillPendingAdvQuery.getRemarks())){
			page.getParam().put(ScmBillPending2.FN_REMARKS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_REMARKS, QueryParam.QUERY_LIKE,scmBillPendingAdvQuery.getRemarks()));
		}
		
		return rtnList;
	}

	@Override
	public List<ScmBillPending2> queryPendingBillType(Param param)
			throws AppException {
	
		Page page = new Page();
		page.setModelClass(ScmBillPending2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(BillTypeSetting.FN_CONTROLUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(BillTypeSetting.class) + "." +BillTypeSetting.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,param.getControlUnitNo()));
		page.getParam().put(BillTypeSetting.FN_NEEDAUDIT,new QueryParam(ClassUtils.getFinalModelSimpleName(BillTypeSetting.class) + "." +BillTypeSetting.FN_NEEDAUDIT, QueryParam.QUERY_EQ,"1"));
		
		List<String> arglist = new ArrayList<>();
		List<BillTypeSetting> billTypeSettingList = billTypeSettingBiz.queryPage(page, arglist, "findAllPage", param);

		List<ScmBillPending2> scmBillPendingList = new ArrayList();
		HashMap<Integer, String> billTypeMap = new HashMap<>(); 
		if(billTypeSettingList!=null && !billTypeSettingList.isEmpty()) {
			for(BillTypeSetting billTypeSetting : billTypeSettingList) {
				List<String> argTypelist = new ArrayList<>();
				argTypelist.add("value="+billTypeSetting.getBillTypeId());

				List<BillType2> billTypeList = billTypeBiz.queryPage(page, argTypelist, "selectById", param);
				if(billTypeList!=null && !billTypeList.isEmpty()) {
					BillType2 billType = billTypeList.get(0);
					if (billType != null) {
						if (!billTypeMap.containsKey((int)billType.getId())) {
							billTypeMap.put((int)billType.getId(), billType.getBillCode());
		        			ScmBillPending2 scmBillPending = new ScmBillPending2();
							scmBillPending.setBillType(billType.getBillCode());
							scmBillPending.setBillTypeName(billType.getBillName());
							scmBillPendingList.add(scmBillPending);
		        		}
						
					}
				}
			}
		}	

		return scmBillPendingList; 
	}


	
	@Override
	public void deletePendingBill(String usrCode,BaseModel model, Param param) throws AppException {
		String billType = "";
		switch (StringUtils.lowerCase(ClassUtils.getFinalModelSimpleName(model.getClass()))) {
			case "scmpursuppliersource": {
				// 供应商寻源
				billType = "PurSupplierSrc";
				break;
			}
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
			case "appaymentbill": {
				// 成本中心报损
				billType = "ApPaymentBill";
				break;
			}
			case "scmsupplierqualifieinfobill":{
				// 资质申请
				billType = "ScmQualifieInfo";
				break;
			}
			case "scminvsaleissuebill":{
				// 销售出库
				billType = "InvSaleIssueBill";
				break;
			}
		}
		ScmBillPending scmBillPending = selectByUsrCode(usrCode,model.getPK(),billType,param);
 		if(scmBillPending!=null) {
 			HashMap<String,Object> map = new HashMap<String,Object>();
 			map.put("billId", model.getPK());
 			map.put("billType", billType);
 			((ScmBillPendingDAO)dao).deletePendingBill(map);
		} else {
			throw new AppException("com.armitage.server.iscm.basedata.service.impl.error.ScmBillPendingBizImpl.updatePendingBill.notUsr");
		}
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmBillPendingAdvQuery) {
				ScmBillPendingAdvQuery scmBillPendingAdvQuery = (ScmBillPendingAdvQuery) page.getModel();
				if(scmBillPendingAdvQuery.getBillDateFrom()!=null && scmBillPendingAdvQuery.getBillDateTo()!=null){
					page.getParam().put(ScmBillPending2.FN_BILLDATE,new QueryParam(ScmBillPending2.FN_BILLDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmBillPendingAdvQuery.getBillDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmBillPendingAdvQuery.getBillDateTo(),1))));
				}
				if(scmBillPendingAdvQuery.getSubmitDateFrom()!=null){
					if(scmBillPendingAdvQuery.getSubmitDateTo()!=null) {
						page.getParam().put(ScmBillPending2.FN_SUBMITDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_SUBMITDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmBillPendingAdvQuery.getSubmitDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmBillPendingAdvQuery.getSubmitDateTo(),1))));
					}else {
						page.getParam().put(ScmBillPending2.FN_SUBMITDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_SUBMITDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmBillPendingAdvQuery.getSubmitDateFrom())));
					}
				}else if(scmBillPendingAdvQuery.getSubmitDateTo()!=null) {
					page.getParam().put(ScmBillPending2.FN_SUBMITDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_SUBMITDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmBillPendingAdvQuery.getSubmitDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmBillPendingAdvQuery.getBillType())){
					page.getParam().put(ScmBillPending2.FN_BILLTYPE,new QueryParam(ScmBillPending2.FN_BILLTYPE, QueryParam.QUERY_EQ,String.valueOf(scmBillPendingAdvQuery.getBillType())));
				}
				if(StringUtils.isNotBlank(scmBillPendingAdvQuery.getRemarks())){
					page.getParam().put(ScmBillPending2.FN_REMARKS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmBillPending2.class) + "." +ScmBillPending2.FN_REMARKS, QueryParam.QUERY_LIKE,"%"+scmBillPendingAdvQuery.getRemarks()+"%"));
				}
			}
		}
	}

}
