package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.purorder.params.PurOrderParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iars.basedata.model.Customer2;
import com.armitage.server.iars.basedata.service.CustomerBiz;
import com.armitage.server.iscm.basedata.model.PayMethod2;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.model.SettleType2;
import com.armitage.server.iscm.basedata.service.PayMethodBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialCompanyInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierConfirmDataBiz;
import com.armitage.server.iscm.basedata.service.ScmSupplierRegInvitationBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.service.SettleTypeBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonBillEntryStatus;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.service.ScmAuditDetailHistoryBiz;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.internaltrans.service.ScmInvSaleOrderBiz;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service.ScmInvOtherIssueBillBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditDetailParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceive2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReceiveEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurReceiveBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurGroupBiz;
import com.armitage.server.quartz.util.SupplierPlatUtil;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.Report2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgCompanyBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.ReportBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Service;

@Service("scmPurOrderBiz")
public class ScmPurOrderBizImpl extends BaseBizImpl<ScmPurOrder2> implements ScmPurOrderBiz {

	private UsrBiz usrBiz;
	private OrgUnitBiz orgUnitBiz;
	private ScmPurOrderEntryBiz scmPurOrderEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private PayMethodBiz payMethodBiz;
	private SettleTypeBiz settleTypeBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmPurReceiveBiz scmPurReceiveBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private CustomerBiz customerBiz;
    private ScmInvSaleOrderBiz scmInvSaleOrderBiz;
	private BillTypeBiz billTypeBiz;
	private ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz;
	private CodeBiz codeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private OrgCompanyBiz orgCompanyBiz;
	private SysParamBiz sysParamBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmPurGroupBiz scmPurGroupBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
	private ScmMaterialCompanyInfoBiz scmMaterialCompanyInfoBiz;
	private EmployeeBiz employeeBiz;
	private ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz;
	private ScmSupplierConfirmDataBiz scmSupplierConfirmDataBiz;
	private ScmAuditDetailHistoryBiz scmAuditDetailHistoryBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	private ReportBiz reportBiz;
	
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
	
	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setScmPurOrderEntryBiz(ScmPurOrderEntryBiz scmPurOrderEntryBiz) {
		this.scmPurOrderEntryBiz = scmPurOrderEntryBiz;
	}
	
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setPayMethodBiz(PayMethodBiz payMethodBiz) {
		this.payMethodBiz = payMethodBiz;
	}

	public void setSettleTypeBiz(SettleTypeBiz settleTypeBiz) {
		this.settleTypeBiz = settleTypeBiz;
	}

	public void setScmPurReceiveBiz(ScmPurReceiveBiz scmPurReceiveBiz) {
		this.scmPurReceiveBiz = scmPurReceiveBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setCustomerBiz(CustomerBiz customerBiz) {
        this.customerBiz = customerBiz;
    }

    public void setScmInvSaleOrderBiz(ScmInvSaleOrderBiz scmInvSaleOrderBiz) {
        this.scmInvSaleOrderBiz = scmInvSaleOrderBiz;
    }
    
    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

    public void setScmInvOtherIssueBillBiz(ScmInvOtherIssueBillBiz scmInvOtherIssueBillBiz) {
		this.scmInvOtherIssueBillBiz = scmInvOtherIssueBillBiz;
	}
    
    public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setOrgCompanyBiz(OrgCompanyBiz orgCompanyBiz) {
		this.orgCompanyBiz = orgCompanyBiz;
	}

	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}
	
	public void setScmPurGroupBiz(ScmPurGroupBiz scmPurGroupBiz) {
		this.scmPurGroupBiz = scmPurGroupBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}
	

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmPurchaseTypeBiz(ScmPurchaseTypeBiz scmPurchaseTypeBiz) {
		this.scmPurchaseTypeBiz = scmPurchaseTypeBiz;
	}
	
	public void setScmMaterialCompanyInfoBiz(ScmMaterialCompanyInfoBiz scmMaterialCompanyInfoBiz) {
		this.scmMaterialCompanyInfoBiz = scmMaterialCompanyInfoBiz;
	}
	
	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}
	
	public void setScmSupplierRegInvitationBiz(ScmSupplierRegInvitationBiz scmSupplierRegInvitationBiz) {
		this.scmSupplierRegInvitationBiz = scmSupplierRegInvitationBiz;
	}

	public void setScmSupplierConfirmDataBiz(ScmSupplierConfirmDataBiz scmSupplierConfirmDataBiz) {
		this.scmSupplierConfirmDataBiz = scmSupplierConfirmDataBiz;
	}

	public void setScmAuditDetailHistoryBiz(ScmAuditDetailHistoryBiz scmAuditDetailHistoryBiz) {
		this.scmAuditDetailHistoryBiz = scmAuditDetailHistoryBiz;
	}

	public void setReportBiz(ReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}

	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}

	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgPurchase2> orgPurchaseList = orgPurchaseBiz.findChild(param.getOrgUnitNo(), param);
		if (orgPurchaseList != null && !orgPurchaseList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgPurchase2 orgPurchase : orgPurchaseList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgPurchase.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

    @Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmPurOrder2 scmPurOrder : (List<ScmPurOrder2>)list){
				/*ScmSupplierConfirmData2 scmSupplierConfirmData = scmSupplierConfirmDataBiz.selectByBillNoAndType(scmPurOrder.getPoNo(), "PurOrder", param);
				if(scmSupplierConfirmData != null && StringUtils.isNotBlank(scmSupplierConfirmData.getStatus())){
					scmPurOrder.setConfirmStatus(scmSupplierConfirmData.getStatus());
					scmPurOrder.setConfirmTime(scmSupplierConfirmData.getConfirmTime());
				}*/
				this.setConvertMap(scmPurOrder, param);
				if("I,R".contains(scmPurOrder.getStatus())) {
					scmPurOrder.setPendingUsrName("");
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(ScmPurOrder2 entity, Param param) throws AppException {
		if (entity != null){
			this.setConvertMap(entity, param);
		}
	}
	
	private void setConvertMap(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if (scmPurOrder != null){
			if(StringUtils.isNotBlank(scmPurOrder.getPendingUsr())) {
				StringBuffer usrName = new StringBuffer("");
				String[] usrCodes = StringUtils.split(scmPurOrder.getPendingUsr(), ",");
				for(String usrCode : usrCodes) {
					Usr usr = usrBiz.selectByCode(usrCode, param);
					if(usr != null) {
						if(StringUtils.isNotBlank(usrName.toString())) 
							usrName.append(",");
						usrName.append(usr.getName());
					}
				}
				scmPurOrder.setPendingUsrName(usrName.toString());
			}

			if (scmPurOrder.getVendorId() > 0){
				//供应商
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurOrder.getVendorId(), param);
				if (scmsupplier != null) {
					scmPurOrder.setVendorName(scmsupplier.getVendorName());
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_VENDORID, scmsupplier);
				}
			}
			if (StringUtils.isNotBlank(scmPurOrder.getPayment())){
				//付款方式
				PayMethod2 payMethod = payMethodBiz.selectByCode(scmPurOrder.getPayment(), param);
				if (payMethod != null) {
					scmPurOrder.setPaymentName(payMethod.getName());
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_PAYMENT, payMethod);
				}
			}
			if (StringUtils.isNotBlank(scmPurOrder.getSettlement())){
				//结算方式
				SettleType2 settleType = settleTypeBiz.selectByCode(scmPurOrder.getSettlement(), param);
				if (settleType != null) {
					scmPurOrder.setSettlementName(settleType.getName());
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_SETTLEMENT, settleType);
				}
			}
			
			if (StringUtils.isNotBlank(scmPurOrder.getCreator())){
				//制单人  
				Usr usr = usrBiz.selectByCode(scmPurOrder.getCreator(), param);
				if (usr != null) {
					if(scmPurOrder.getDataDisplayMap()==null){
						scmPurOrder.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurOrder.setCreatorName(usr.getName());
					scmPurOrder.getDataDisplayMap().put(ScmPurOrder2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmPurOrder.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmPurOrder.getEditor(), param);
				if (usr != null) {
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmPurOrder.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmPurOrder.getChecker(), param);
				if (usr != null) {
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_CHECKER, usr);
				}
			}
			if (scmPurOrder.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurOrder.getBuyerId(), param);
				if (scmPurBuyer != null) {
					scmPurOrder.setBuyerName(scmPurBuyer.getBuyerName());
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_BUYERID, scmPurBuyer);
					Employee employee = employeeBiz.selectDirect(scmPurBuyer.getEmpId(), param);
					if(employee != null && StringUtils.isNotBlank(employee.getCellphone())){
						scmPurOrder.setBuyerPhone(employee.getCellphone());
					}
				}
			}
			if (StringUtils.isNotBlank(scmPurOrder.getOrgUnitNo())){
				//采购组织
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrder.getOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmPurOrder.setPurOrgUnitName(orgBaseUnit.getOrgUnitName());
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_ORGUNITNO, orgBaseUnit);
				}
			}
			
			if(StringUtils.isNotBlank(scmPurOrder.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurOrder.getStatus());
				if(code!=null)
					scmPurOrder.setStatusName(code.getName());
			}
			
			if(StringUtils.isNotBlank(scmPurOrder.getBizType())){
				ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmPurOrder.getBizType(),param);
				if(scmPurchaseType!=null) {
					scmPurOrder.setConvertMap(ScmPurOrder2.FN_BIZTYPE,scmPurchaseType);
					scmPurOrder.setBizTypeName(scmPurchaseType.getName());
				}
			}
			if(StringUtils.isNotBlank(scmPurOrder.getLockStatus())){
				scmPurOrder.setLocked(true);
			}else{
				scmPurOrder.setLocked(false);
			}
			
			scmPurOrder.setTaxAmount(scmPurOrder.getTaxAmt().subtract(scmPurOrder.getAmt()));
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurOrder2 scmPurOrder = (ScmPurOrder2) bean.getList().get(0);
		if(scmPurOrder != null && scmPurOrder.getId() > 0){
			bean.setList2(scmPurOrderEntryBiz.selectByPoId(scmPurOrder.getId(), param));
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmPurOrder2 scmPurOrder2 = (ScmPurOrder2) bean.getList().get(0);
		if(scmPurOrder2 != null){
			//获取财务组织
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, scmPurOrder2.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			scmPurOrder2.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			String code = CodeAutoCalUtil.getBillCode("PurOrder", scmPurOrder2, param);
			scmPurOrder2.setPoNo(code);
			List<ScmPurOrderEntry2> scmPurOrderEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            Date reqDate = null;
            List<String> prNoList = new ArrayList();
			if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
				for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
                    amt = amt.add(scmPurOrderEntry.getAmt());
                    taxAmt = taxAmt.add(scmPurOrderEntry.getTaxAmt());
                    if(scmPurOrderEntry.getReqDate()!=null && (reqDate==null || scmPurOrderEntry.getReqDate().compareTo(reqDate)<0))
                    	reqDate = scmPurOrderEntry.getReqDate();
                    if(StringUtils.isNotBlank(scmPurOrderEntry.getPrNo()) && !prNoList.contains(scmPurOrderEntry.getPrNo()))
                    	prNoList.add(scmPurOrderEntry.getPrNo());
				}
			}
			scmPurOrder2.setAmt(amt);
			scmPurOrder2.setTaxAmt(taxAmt);
			scmPurOrder2.setReqDate(reqDate);
			scmPurOrder2.setPrNos(StringUtils.left(StringUtils.join(prNoList, ","),250));
//			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmPurOrder2.getFinOrgUnitNo(), "PurOrder", param);
//			if(billType!=null && billType.isNeedAudit()) {
//				if(StringUtils.isBlank(billType.getWorkFlowNo())) {
//					throw new AppException(Message.getMessage(param.getLang(), "iscm.workflow.needaudit.notprocess", new String[] {Message.getMessage(param.getLang(),"field.ScmPurOrder")}));
//				}
//				scmPurOrder2.setWorkFlowNo(billType.getWorkFlowNo());
//			}
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurOrder2 scmPurOrder = (ScmPurOrder2) bean.getList().get(0);
		if(scmPurOrder != null && scmPurOrder.getId() > 0){
			//新增订货明细
			List<ScmPurOrderEntry2> scmPurOrderEntryList = bean.getList2();
			if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
				int lineId = 1;
				HashMap<String, Object> companyMap = new HashMap<String, Object>();
				HashMap<String, StringBuffer> companyMaterialIdMap = new HashMap<String ,StringBuffer>();
				for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
					scmPurOrderEntry.setLineId(lineId);
					scmPurOrderEntry.setPoId(scmPurOrder.getId());
					scmPurOrderEntry.setBuyerId(scmPurOrder.getBuyerId());
					scmPurOrderEntry.setPurGroupId(scmPurOrder.getPurGroupId());
					scmPurOrderEntry.setBalanceSupplierId(scmPurOrder.getVendorId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurOrderEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurOrderEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurOrderEntry.getItemId(), scmPurOrderEntry.getPurUnit(), param);
					scmPurOrderEntry.setBaseQty(scmPurOrderEntry.getQty().multiply(convRate));
					if(StringUtils.isNotBlank(scmPurOrderEntry.getReqOrgUnitNo())) {
						OrgCompany2 orgCompany = (OrgCompany2) companyMap.get(scmPurOrderEntry.getReqOrgUnitNo());
						if(orgCompany==null) {
							Page page = new Page();
							page.setModelClass(OrgCompany2.class);
							page.setShowCount(Integer.MAX_VALUE);
							List<String> arglist = new ArrayList<>();
							arglist.add("type=to");
							arglist.add("relationType="+OrgUnitRelationType.ADMINTOFIN);
							arglist.add("fromOrgUnitNo="+scmPurOrderEntry.getReqOrgUnitNo());
							List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
							if(orgCompanyList==null || orgCompanyList.isEmpty())
								throw new AppException("iscm.inventorymanage.ScmInvMaterialRequestBillBizImpl.reqOrgUnit.notfinorg");
							orgCompany = orgCompanyList.get(0);
							companyMap.put(scmPurOrderEntry.getReqOrgUnitNo(), orgCompany);
						}
						scmPurOrderEntry.setReqFinOrgUnitNo(orgCompany.getOrgUnitNo());
					}else if(StringUtils.isNotBlank(scmPurOrderEntry.getReceiveOrgUnitNo())) {
						OrgCompany2 orgCompany = (OrgCompany2) companyMap.get(scmPurOrderEntry.getReceiveOrgUnitNo());
						if(orgCompany==null) {
							Page page = new Page();
							page.setModelClass(OrgCompany2.class);
							page.setShowCount(Integer.MAX_VALUE);
							List<String> arglist = new ArrayList<>();
							arglist.add("type=to");
							arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
							arglist.add("fromOrgUnitNo="+scmPurOrderEntry.getReceiveOrgUnitNo());
							List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
							if(orgCompanyList==null || orgCompanyList.isEmpty())
								throw new AppException("iscm.inventorymanage.ScmInvMaterialRequestBillBizImpl.reqOrgUnit.notfinorg");
							orgCompany = orgCompanyList.get(0);
							companyMap.put(scmPurOrderEntry.getReceiveOrgUnitNo(), orgCompany);
						}
						scmPurOrderEntry.setReqFinOrgUnitNo(orgCompany.getOrgUnitNo());
					}
					if(companyMaterialIdMap.containsKey(scmPurOrderEntry.getReqFinOrgUnitNo())) {
//						if(StringUtils.isBlank(companyMaterialIdMap.get(scmPurOrderEntry.getReqFinOrgUnitNo()).toString())) 
							companyMaterialIdMap.get(scmPurOrderEntry.getReqFinOrgUnitNo()).append(",");
						companyMaterialIdMap.get(scmPurOrderEntry.getReqFinOrgUnitNo()).append(scmPurOrderEntry.getItemId());
					}else {
						companyMaterialIdMap.put(scmPurOrderEntry.getReqFinOrgUnitNo(), new StringBuffer().append(scmPurOrderEntry.getItemId()));
					}
					lineId = lineId+1;
				}
				StringBuffer notRawMaterails = new StringBuffer();
				for(String company:companyMaterialIdMap.keySet()){
					if(StringUtils.isBlank(notRawMaterails.toString())) 
						notRawMaterails.append(ScmMaterialUtil.getNotRawMaterail(company, companyMaterialIdMap.get(company).toString(), param));
				}
				if(StringUtils.isNotBlank(notRawMaterails.toString()))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
					if (scmPurOrderEntry.getPrDtlId()>0) {
						scmPurOrderEntryBiz.generateAdd(scmPurOrderEntry, param);
					}else {
						scmPurOrderEntryBiz.add(scmPurOrderEntry, param);
					}
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurOrder2 scmPurOrder = (ScmPurOrder2) bean.getList().get(0);
		if(scmPurOrder != null && scmPurOrder.getId() > 0){
			//更新订货明细
			List<ScmPurOrderEntry2> scmPurOrderEntryList = bean.getList2();
			HashMap<String, Object> companyMap = new HashMap<String, Object>();
			if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
				HashMap<String, StringBuffer> companyMaterialIdMap = new HashMap<String ,StringBuffer>();
				int lineId = 1;
				for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
					if (StringUtils.equals("I", scmPurOrder.getStatus())) {
						scmPurOrderEntry.setLineId(lineId);
					}
					scmPurOrderEntry.setBuyerId(scmPurOrder.getBuyerId());
					scmPurOrderEntry.setPurGroupId(scmPurOrder.getPurGroupId());
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurOrderEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurOrderEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurOrderEntry.getItemId(), scmPurOrderEntry.getPurUnit(), param);
					scmPurOrderEntry.setBaseQty(scmPurOrderEntry.getQty().multiply(convRate));
					scmPurOrderEntry.setPoId(scmPurOrder.getId());
					scmPurOrderEntry.setBalanceSupplierId(scmPurOrder.getVendorId());
					if(StringUtils.isNotBlank(scmPurOrderEntry.getReqOrgUnitNo())) {
						OrgCompany2 orgCompany = (OrgCompany2) companyMap.get(scmPurOrderEntry.getReqOrgUnitNo());
						if(orgCompany==null) {
							Page page = new Page();
							page.setModelClass(OrgCompany2.class);
							page.setShowCount(Integer.MAX_VALUE);
							List<String> arglist = new ArrayList<>();
							arglist.add("type=to");
							arglist.add("relationType="+OrgUnitRelationType.ADMINTOFIN);
							arglist.add("fromOrgUnitNo="+scmPurOrderEntry.getReqOrgUnitNo());
							List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
							if(orgCompanyList==null || orgCompanyList.isEmpty())
								throw new AppException("iscm.inventorymanage.ScmInvMaterialRequestBillBizImpl.reqOrgUnit.notfinorg");
							orgCompany = orgCompanyList.get(0);
							companyMap.put(scmPurOrderEntry.getReqOrgUnitNo(), orgCompany);
						}
						scmPurOrderEntry.setReqFinOrgUnitNo(orgCompany.getOrgUnitNo());
					}else if(StringUtils.isNotBlank(scmPurOrderEntry.getReceiveOrgUnitNo())) {
						OrgCompany2 orgCompany = (OrgCompany2) companyMap.get(scmPurOrderEntry.getReceiveOrgUnitNo());
						if(orgCompany==null) {
							Page page = new Page();
							page.setModelClass(OrgCompany2.class);
							page.setShowCount(Integer.MAX_VALUE);
							List<String> arglist = new ArrayList<>();
							arglist.add("type=to");
							arglist.add("relationType="+OrgUnitRelationType.INVTOFIN);
							arglist.add("fromOrgUnitNo="+scmPurOrderEntry.getReceiveOrgUnitNo());
							List<OrgCompany2> orgCompanyList = orgCompanyBiz.queryPage(page, arglist, "queryPageEx", param);
							if(orgCompanyList==null || orgCompanyList.isEmpty())
								throw new AppException("iscm.inventorymanage.ScmInvMaterialRequestBillBizImpl.reqOrgUnit.notfinorg");
							orgCompany = orgCompanyList.get(0);
							companyMap.put(scmPurOrderEntry.getReceiveOrgUnitNo(), orgCompany);
						}
						scmPurOrderEntry.setReqFinOrgUnitNo(orgCompany.getOrgUnitNo());
					}
					if(companyMaterialIdMap.containsKey(scmPurOrderEntry.getReqFinOrgUnitNo())) {
//						if(StringUtils.isBlank(companyMaterialIdMap.get(scmPurOrderEntry.getReqFinOrgUnitNo()).toString())) 
							companyMaterialIdMap.get(scmPurOrderEntry.getReqFinOrgUnitNo()).append(",");
						companyMaterialIdMap.get(scmPurOrderEntry.getReqFinOrgUnitNo()).append(scmPurOrderEntry.getItemId());
					}else {
						companyMaterialIdMap.put(scmPurOrderEntry.getReqFinOrgUnitNo(), new StringBuffer().append(scmPurOrderEntry.getItemId()));
					}
					lineId = lineId+1;
				}
				StringBuffer notRawMaterails = new StringBuffer();
				for(String company:companyMaterialIdMap.keySet()){
					if(StringUtils.isBlank(notRawMaterails.toString())) 
						notRawMaterails.append(ScmMaterialUtil.getNotRawMaterail(company, companyMaterialIdMap.get(company).toString(), param));
				}
				if(StringUtils.isNotBlank(notRawMaterails.toString()))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				scmPurOrderEntryBiz.update(scmPurOrder, scmPurOrderEntryList, ScmPurOrderEntry2.FN_POID, param);
			}
		}
	}
	
	private ScmPurOrder2 updateStatus(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder != null){
			ScmPurOrder2 scmPurOrder2 = this.update(scmPurOrder, param);
			if(scmPurOrder2 != null){
				scmPurOrderEntryBiz.updateRowStatusByPoId(scmPurOrder2.getId(), scmPurOrder2.getStatus(), scmPurOrder2.getChecker(), scmPurOrder2.getCheckDate(), param);
				return scmPurOrder2;
			}
		}
		return null;
	}
	
	
	@Override
	public void sendOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		scmPurOrder = this.selectDirect(scmPurOrder.getId(), param);
		if(scmPurOrder.isSended()) {
			throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.error.issended",new String[]{scmPurOrder.getPoNo()}));
		}
		List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz.selectByNotSend(scmPurOrder.getId(), param);
		if(scmPurOrderEntryList==null || scmPurOrderEntryList.isEmpty())
			return;
		// 查出供应商类型
		// 库存组织: 供应商对应的组织单元
        Scmsupplier2 scmSupplier = scmsupplierBiz.selectDirect(scmPurOrder.getVendorId(), param);
        if (scmSupplier == null) {
            throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.noSupplier");
        }
        if ("2".equals(scmSupplier.getRole())) {
            // 内部供应商, 生成销售订单
            generateSaleOrder(scmPurOrder, scmPurOrderEntryList, param);
        }else if ("3".equals(scmSupplier.getRole()) && scmPurOrder.isUnified()) {
        	//寄存供应商，则生成寄存组织的寄存出库
        	generateDepositIssue(scmSupplier.getOrgUnitNo(),scmPurOrder, scmPurOrderEntryList, param);
        } else {
            // 外部供应商或寄存供应商直配时, 生成验收单和收货单
            generateReceiveOrder(scmPurOrder, scmPurOrderEntryList, param);
        }
		int updateRow = this.updateSendedStaus(scmPurOrder.getId(), param);
		sendEmail(scmPurOrder,param);
		if(updateRow!=1) {
			throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.error.issended",new String[]{scmPurOrder.getPoNo()}));
		}
	}
	
	private void sendEmail(ScmPurOrder2 scmPurOrder, Param param) {
		Report2 report = reportBiz.selectByCode("scmord001", param.getOrgUnitNo(), param);
		if (report != null) {
			LinkedHashMap<String, Object> paramValueMap = new LinkedHashMap<String, Object>();
			paramValueMap.put("billNo", "'"+scmPurOrder.getPoNo()+"'");
			paramValueMap.put("orgUnitNo", scmPurOrder.getOrgUnitNo());
			report.setParamValueMap(paramValueMap);
			String reportAttachmentBASE64 = AuditMsgUtil.getReportAttachment(report, param);
			AuditMsgUtil.sendEmailMsg(scmPurOrder,"B",reportAttachmentBASE64, param);
		}
	}
	
	private void generateReceiveOrder(ScmPurOrder2 scmPurOrder, List<ScmPurOrderEntry2> scmPurOrderEntryList,
            Param param) throws AppException {
		scmPurReceiveBiz.generateFromPurOrder(scmPurOrder, scmPurOrderEntryList, param);
    }

    private void generateSaleOrder(ScmPurOrder2 scmPurOrder, List<ScmPurOrderEntry2> scmPurOrderEntryList,  Param param) throws AppException {
    	String toSaleOut = sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_ToSaleOut", "N", param);
	    //按收货组织分单
        List<String> list = new ArrayList<>();
        HashMap<Integer, ScmPurOrderEntry2> dealMap = new HashMap<Integer, ScmPurOrderEntry2>();
        HashMap<Long, ScmMaterial2> scmMaterialMap = new HashMap<Long, ScmMaterial2>();
        HashMap<String, ScmMaterialPrice> scmMaterialPriceMap = new HashMap<String, ScmMaterialPrice>();
        for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
        	if(dealMap.containsKey(scmPurOrderEntry.getLineId()))
        		continue;
            if(!list.contains(scmPurOrderEntry.getReceiveOrgUnitNo())){
                list.add(scmPurOrderEntry.getReceiveOrgUnitNo());
                CommonBean bean = new CommonBean();
                List<ScmInvSaleOrder2> scmInvSaleOrderList = new ArrayList<>();
                List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList = new ArrayList<>();
                ScmInvSaleOrder2 scmInvSaleOrder = new ScmInvSaleOrder2(true);
                scmInvSaleOrder.setStatus("I");
                // 库存组织: 供应商对应的组织单元
                Scmsupplier2 scmSupplier = scmsupplierBiz.selectDirect(scmPurOrder.getVendorId(), param);
                if (scmSupplier == null) {
                    throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.generateSaleOrder.noSupplier");
                }
                scmInvSaleOrder.setOrgUnitNo(scmSupplier.getOrgUnitNo());
                // 客户
                HashMap<String, Object> custIdMap = new HashMap<String, Object>();
                custIdMap.put(Customer2.FN_FLAG, 1);
                custIdMap.put(Customer2.FN_ORGUNITNO, scmPurOrderEntry.getReceiveOrgUnitNo());
                custIdMap.put(Customer2.FN_CONTROLUNITNO, param.getControlUnitNo());
                List<Customer2> customerList = customerBiz.findAll(custIdMap, param);
                if (customerList == null || customerList.isEmpty()) {
                    throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.generateSaleOrder.noCustId");
                }
                scmInvSaleOrder.setCustId(customerList.get(0).getId());
                scmInvSaleOrder.setBizDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
                // 来源单类型
                scmInvSaleOrder.setBillType("PurOrder");	//来自采购订单
                scmInvSaleOrder.setBizType("2");
                scmInvSaleOrder.setReqDate(scmPurOrderEntry.getReqDate());
                scmInvSaleOrder.setCurrencyNo(scmPurOrder.getCurrencyNo());
                scmInvSaleOrder.setExchangeRate(scmPurOrder.getExchangeRate());
                scmInvSaleOrder.setCreator(param.getUsrCode());
                scmInvSaleOrder.setCreateDate(CalendarUtil.getDate(param));
                scmInvSaleOrder.setControlUnitNo(param.getControlUnitNo());
                scmInvSaleOrderList.add(scmInvSaleOrder);
                bean.setList(scmInvSaleOrderList);
                HashMap<String, String> invToFinMap = new HashMap<String, String>();
                for (int i=0;i<scmPurOrderEntryList.size();i++) {
                    if(StringUtils.equals(scmPurOrderEntryList.get(i).getReceiveOrgUnitNo(),scmPurOrderEntry.getReceiveOrgUnitNo())){
                    	dealMap.put(scmPurOrderEntryList.get(i).getLineId(), scmPurOrderEntryList.get(i));
                        ScmInvSaleOrderEntry2 scmInvSaleOrderEntry = new ScmInvSaleOrderEntry2(true);
                        scmInvSaleOrderEntry.setLineId(i+1);
                        scmInvSaleOrderEntry.setItemId(scmPurOrderEntryList.get(i).getItemId());
                        ScmMaterial2 scmMaterial = scmMaterialMap.get(scmPurOrderEntryList.get(i).getItemId());
                        if(scmMaterial==null) {
                        	scmMaterial = scmMaterialBiz.findByInvItemId(param.getControlUnitNo(),scmInvSaleOrder.getOrgUnitNo(),scmPurOrderEntryList.get(i).getItemId(), param);
                        	scmMaterialMap.put(scmPurOrderEntryList.get(i).getItemId(), scmMaterial);
                        }
                        if(scmMaterial==null)
                            throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.generateSaleOrder.error.notScmMaterial");
                        if(scmMaterial.getUnitId()==0)
                        	throw new AppException(Message.getMessage(param.getLang(),"iscm.inventorymanage.warehouseoutbusiness.ScmInvMoveIssueBillBizImpl.generateInWaresBill.error.noUnit",new String[]{scmMaterial.getItemName()}));
                        scmInvSaleOrderEntry.setUnit(scmMaterial.getUnitId());
                        scmInvSaleOrderEntry.setBaseUnit(scmPurOrderEntryList.get(i).getBaseUnit());
                        scmInvSaleOrderEntry.setQty(scmPurOrderEntryList.get(i).getQty());
                        scmInvSaleOrderEntry.setBaseQty(scmPurOrderEntryList.get(i).getBaseQty());
                        scmInvSaleOrderEntry.setPieQty(scmPurOrderEntryList.get(i).getPieQty());
                        scmInvSaleOrderEntry.setPieUnit(scmPurOrderEntryList.get(i).getPieUnit());
                        scmInvSaleOrderEntry.setSourceBillDtlId(scmPurOrderEntryList.get(i).getId());
                        if(scmPurOrderEntryList.get(i).getPriceBillId()>0) {
                        	scmInvSaleOrderEntry.setSaleTaxPrice(scmPurOrderEntryList.get(i).getTaxPrice());
                        	scmInvSaleOrderEntry.setPriceBillId(scmPurOrderEntryList.get(i).getPriceBillId());
                        	scmInvSaleOrderEntry.setRefPriceStatus(scmPurOrderEntryList.get(i).getRefPriceStatus());
                        }else {
	                        // 销售金额相关
                        	ScmMaterialPrice scmMaterialPrice = scmMaterialPriceMap.get(scmInvSaleOrder.getOrgUnitNo()+"_"+scmInvSaleOrderEntry.getItemId());
                        	if(scmMaterialPrice==null) {
                        		scmMaterialPrice = ScmMaterialUtil.getMaterialSalePrice(scmInvSaleOrder.getOrgUnitNo(), scmInvSaleOrderEntry.getItemId(), scmInvSaleOrder.getBizDate(), param);
                        		scmMaterialPriceMap.put(scmInvSaleOrder.getOrgUnitNo()+"_"+scmInvSaleOrderEntry.getItemId(), scmMaterialPrice);
                        	}
                        	if (scmMaterialPrice != null) {
	                        	scmInvSaleOrderEntry.setSaleTaxPrice(scmMaterialPrice.getTaxPrice());
	                        	scmInvSaleOrderEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
	                        	scmInvSaleOrderEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
	                        } else {
	                        	scmInvSaleOrderEntry.setSaleTaxPrice(scmPurOrderEntryList.get(i).getTaxPrice());
	                        	scmInvSaleOrderEntry.setPriceBillId(scmPurOrderEntryList.get(i).getPriceBillId());
	                        	scmInvSaleOrderEntry.setRefPriceStatus(scmPurOrderEntryList.get(i).getRefPriceStatus());
	                        }
                        }
                        scmInvSaleOrderEntry.setSaleTaxAmt(scmInvSaleOrderEntry.getQty().multiply(scmInvSaleOrderEntry.getSaleTaxPrice()));
                        scmInvSaleOrderEntry.setRemarks(scmPurOrderEntryList.get(i).getRemarks());
                        scmInvSaleOrderEntryList.add(scmInvSaleOrderEntry);
                        bean.setList2(scmInvSaleOrderEntryList);
                    }
                }
                scmInvSaleOrderBiz.add(bean, param);
                if(StringUtils.equals("Y", toSaleOut)) {
                	//提交
                	if(param.getUsrCode()!=null ){
                		scmInvSaleOrder.setChecker(param.getUsrCode());
					}
                	scmInvSaleOrder.setCheckDate(CalendarUtil.getDate(param));
					scmInvSaleOrder.setStatus("A");
					scmInvSaleOrderBiz.updateStatus(scmInvSaleOrder, param);
					//下达
					scmInvSaleOrder.setStatus("A");
					scmInvSaleOrderBiz.updateStatus(scmInvSaleOrder, param);
					//生成出库
					scmInvSaleOrderBiz.generateOutBoundOrders(scmInvSaleOrder, param);
				}
            }
        }
	}

    private void generateDepositIssue(String orgUnitNo,ScmPurOrder2 scmPurOrder, List<ScmPurOrderEntry2> scmPurOrderEntryList,Param param) throws AppException {
    	scmInvOtherIssueBillBiz.generateDepositIssue(orgUnitNo,scmPurOrder, scmPurOrderEntryList, param);
    }
	@Override
	public ScmPurOrderEntry2 getPreUseQty(String orgUnitNo,long itemId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("orgUnitNo", orgUnitNo);
		map.put("begDate", FormatUtils.fmtDate(CalendarUtil.relativeDate(CalendarUtil.getDate(param),-11)));
		map.put("endDate", FormatUtils.fmtDate(CalendarUtil.getDate(param)));
		return  ((ScmPurOrderDAO)dao).getPreUseQty(map);
	}

	@Override
	public ScmPurOrder2 submit(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		ScmPurOrder2 pruOrder = null;
		if(scmPurOrder.getId()>0){
			pruOrder = this.selectDirect(scmPurOrder.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurOrder2.FN_PONO,
					new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,
							scmPurOrder.getPoNo()));
			
			List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
				pruOrder=scmPurOrderList.get(0);
			}
		}
		if(pruOrder!=null){
			this.setConvertMap(pruOrder,param);
			if(!StringUtils.equals(pruOrder.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(pruOrder.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(pruOrder.getFinOrgUnitNo(), "PurOrder", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					//发起流程
					
					BigDecimal totalAmt = BigDecimal.ZERO;
					BigDecimal totalTaxAmt = BigDecimal.ZERO;
					List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz.selectByPoId(pruOrder.getId(),param);
					if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
						for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
							ScmMaterialCompanyInfo scmMaterialCompanyInfo =  scmMaterialCompanyInfoBiz.selectByItemIdAndOrgUnitNo(scmPurOrderEntry.getItemId(), scmPurOrderEntry.getFinOrgUnitNo(), param);
							if(scmMaterialCompanyInfo == null){
								scmMaterialCompanyInfo = scmMaterialCompanyInfoBiz.selectByItemIdAndOrgUnitNo(scmPurOrderEntry.getItemId(), param.getControlUnitNo(), param);
							}
							
							if(scmMaterialCompanyInfo!=null && StringUtils.equals(scmMaterialCompanyInfo.getNeedPricing(),"Y")) {
								if (scmPurOrderEntry.getPrice().compareTo(BigDecimal.ZERO) == 0) {
									throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurOrderBizImpl.error.submit.NeedPricing",new String[]{scmPurOrderEntry.getItemName()}));
								}
							}
							
							totalAmt = totalAmt.add(scmPurOrderEntry.getAmt());
							totalTaxAmt = totalTaxAmt.add(scmPurOrderEntry.getTaxAmt());
						}
					}
					pruOrder.setTotalAmt(totalAmt);
					pruOrder.setTotalTaxAmt(totalTaxAmt);
					CommonBean bean = new CommonBean();
					List<ScmPurOrder2> scmPurOrderList = new ArrayList<>();
					scmPurOrderList.add(pruOrder);
					bean.setList(scmPurOrderList);
					bean.setList2(scmPurOrderEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					pruOrder.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							pruOrder.setChecker(param.getUsrCode());
							pruOrder.setSubmitter(param.getUsrCode());
						}
						pruOrder.setCheckDate(CalendarUtil.getDate(param));
						pruOrder.setSubmitDate(CalendarUtil.getDate(param));
						pruOrder.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							pruOrder.setSubmitter(param.getUsrCode());
						}
						pruOrder.setSubmitDate(CalendarUtil.getDate(param));
						pruOrder.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							pruOrder.setPendingUsr(StringUtils.left(usrList,250));
							scmBillPendingBiz.addPendingBill(usrList, pruOrder, param);
							PurOrderParams purOrderParams = new PurOrderParams();
							purOrderParams.setPoNo(pruOrder.getPoNo());
							AuditMsgUtil.sendAuditMsg("PurOrder",pruOrder,purOrderParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						pruOrder.setChecker(param.getUsrCode());
						pruOrder.setSubmitter(param.getUsrCode());
					}
					pruOrder.setCheckDate(CalendarUtil.getDate(param));
					pruOrder.setSubmitDate(CalendarUtil.getDate(param));
					pruOrder.setStatus("A");
				}
				try {
					this.updateStatus(pruOrder, param);
				} catch (Exception e) {
					throw e;
				}
				if(StringUtils.contains("A,B", pruOrder.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.release(pruOrder, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruOrder;
	}

	@Override
	public ScmPurOrder2 undoSubmit(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		ScmPurOrder2 pruOrder = null;
		if(scmPurOrder.getId()>0){
			pruOrder = this.selectDirect(scmPurOrder.getId(), param);
		}
		if(pruOrder!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(pruOrder.getFinOrgUnitNo(), "PurOrder", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(pruOrder.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(pruOrder.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(pruOrder.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(pruOrder.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(pruOrder.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(pruOrder.getStatus().equals("A") || pruOrder.getStatus().equals("D")){
				if(param.getUsrCode()!=null ){
					pruOrder.setChecker(null);
					pruOrder.setSubmitter(null);
				}
				pruOrder.setCheckDate(null);
				pruOrder.setSubmitDate(null);
				pruOrder.setPendingUsr(null);
				pruOrder.setStatus("I");
				try {
					this.updateStatus(pruOrder, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),pruOrder, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruOrder;
	}

	@Override
	public ScmPurOrder2 release(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		ScmPurOrder2 pruOrder = null;
		if(scmPurOrder.getId()>0){
			pruOrder = this.selectDirect(scmPurOrder.getId(), param);
		}
		if(pruOrder!=null){
			if(!StringUtils.contains("A,B", pruOrder.getStatus())){
				throw new AppException("iscm.purchasemanage.error.release");
			}else{
				if(pruOrder.getStatus().equals("A")){
					pruOrder.setStatus("E");
					pruOrder.setReleaseDate(CalendarUtil.getDate(param));
				}else {
					pruOrder.setStatus("S");
					pruOrder.setReleaseDate(CalendarUtil.getDate(param));
				}
				try {
					this.update(pruOrder, param);
					scmPurOrderEntryBiz.release(pruOrder, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruOrder;
	}

	@Override
	public ScmPurOrder2 undoRelease(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		ScmPurOrder2 pruOrder = null;
		if(scmPurOrder.getId()>0){
			pruOrder = this.selectDirect(scmPurOrder.getId(), param);
		}
		if(pruOrder!=null){
			if(!StringUtils.contains("E,S",pruOrder.getStatus())){
				throw new AppException("iscm.purchasemanage.error.cancelRelease");
			}else {
				if(!checkFollowUpBill(pruOrder,param)){
					throw new AppException("iscm.purchasemanage.cancelRelease.error.existsfollowup");
				}
				if(pruOrder.getStatus().equals("E")){
					pruOrder.setStatus("A");
					pruOrder.setReleaseDate(null);
				}else {
					pruOrder.setStatus("B");
					pruOrder.setReleaseDate(null);
				}
				pruOrder.setPushed(false);
				try {
					scmSupplierConfirmDataBiz.deleteListByBillNoAndType(scmPurOrder.getPoNo(), "PurOrder", param);
					this.update(pruOrder, param);
					scmPurOrderEntryBiz.undoRelease(pruOrder, param);
				} catch (Exception e) {
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return pruOrder;
	}

	private boolean checkFollowUpBill(ScmPurOrder2 pruOrder, Param param) throws AppException {
		List<ScmPurReceive2> scmPurReceiveList = scmPurReceiveBiz.selectByPurOrder(pruOrder,param);
		if(scmPurReceiveList!=null && !scmPurReceiveList.isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public List<ScmPurOrder2> selectBySaleIssueBill(long otId, Param param)	throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("otId", otId);
		return ((ScmPurOrderDAO)dao).selectBySaleIssueBill(map);
	}

	@Override
	protected void beforeDelete(ScmPurOrder2 entity, Param param) throws AppException {
		ScmPurOrder2 scmPurOrder = this.selectDirect(entity.getId(), param);
		if(scmPurOrder!=null && !StringUtils.contains("I,R",scmPurOrder.getStatus())){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{scmPurOrder.getPoNo()}));
		}
		//删除订货明细
		scmPurOrderEntryBiz.deleteByPoId(entity.getId(), param);
		SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
		supplierPlatUtil.changeSyncData(entity, param);
		//删除附件
		scmBaseAttachmentBiz.update(entity, null,ScmBaseAttachment.FN_BILLID,param);
	}

	@Override
	public void unSendOrder(ScmPurOrder2 scmPurOrder, Param param)
			throws AppException {
		scmPurOrder.setSended(false);	//标记未发送
		scmPurOrder.setSendDate(null);
		this.updateBillNoChangeTime(scmPurOrder, param);
		// 查出供应商类型
		// 库存组织: 供应商对应的组织单元
        Scmsupplier2 scmSupplier = scmsupplierBiz.selectDirect(scmPurOrder.getVendorId(), param);
        if (scmSupplier == null) {
            throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurOrderBizImpl.noSupplier");
        }
        if ("2".equals(scmSupplier.getRole())) {
            // 内部供应商, 取消发单时删除生成的销售订单
        	scmInvSaleOrderBiz.delByPurOrder(scmPurOrder, param);
        }else if ("3".equals(scmSupplier.getRole()) && scmPurOrder.isUnified()) {
        	//寄存供应商且统配，取消发单时删除生成寄存出库单
        	scmInvOtherIssueBillBiz.delByPurOrder(scmPurOrder, param);
        } else {
            // 外部供应商或寄存供应商直配时, 取消发单时删除生成验收单和收货单
        	scmPurReceiveBiz.delByPurOrder(scmPurOrder, param);
        }
	}
	
	@Override
	protected void beforeUpdate(ScmPurOrder2 oldEntity, ScmPurOrder2 newEntity,	Param param) throws AppException {
		if(StringUtils.isNotBlank(oldEntity.getLockStatus())){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.billLocked"));
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurOrder2 entry = (ScmPurOrder2) bean.getList().get(0);
		if(entry!=null){
			ScmPurOrder2 scmPurOrder = this.select(entry.getPK(), param);
			if(!StringUtils.contains("I,D,P,R", scmPurOrder.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			List<ScmPurOrderEntry2> scmPurOrderEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
            Date reqDate = null;
            List<String> prNoList = new ArrayList();
			if(scmPurOrderEntryList != null && !scmPurOrderEntryList.isEmpty()){
				for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList){
                    amt = amt.add(scmPurOrderEntry.getAmt());
                    taxAmt = taxAmt.add(scmPurOrderEntry.getTaxAmt());
                    if(scmPurOrderEntry.getReqDate()!=null && (reqDate==null || scmPurOrderEntry.getReqDate().compareTo(reqDate)<0))
                    	reqDate = scmPurOrderEntry.getReqDate();
                    if(StringUtils.isNotBlank(scmPurOrderEntry.getPrNo()) && !prNoList.contains(scmPurOrderEntry.getPrNo()))
                    	prNoList.add(scmPurOrderEntry.getPrNo());
				}
			}
			entry.setAmt(amt);
			entry.setTaxAmt(taxAmt);
			entry.setReqDate(reqDate);
			entry.setPrNos(StringUtils.left(StringUtils.join(prNoList, ","),250));
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entry.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entry.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		}
	}
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurOrderAdvQuery) {
				ScmPurOrderAdvQuery scmPurOrderAdvQuery = (ScmPurOrderAdvQuery) page.getModel();
				String sqlCondition = page.getSqlCondition();
				if(scmPurOrderAdvQuery.getBegBizDate()!=null){
					if(scmPurOrderAdvQuery.getEndBizDate()!=null) {
						if(StringUtils.isEmpty(sqlCondition)) {
							sqlCondition="ScmPurOrder.id in (Select distinct ScmPurOrder.id from ScmPurOrder,ScmPurOrderEntry Where ScmPurOrder.id=ScmPurOrderEntry.poId"
									+ " and ScmPurOrderEntry.reqDate between '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getBegBizDate())+"' and '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getEndBizDate())+"')";
						}else {
							sqlCondition=sqlCondition+" And ScmPurOrder.id in (Select distinct ScmPurOrder.id from ScmPurOrder,ScmPurOrderEntry Where ScmPurOrder.id=ScmPurOrderEntry.poId"
									+ " and ScmPurOrderEntry.reqDate between '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getBegBizDate())+"' and '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getEndBizDate())+"')";
						}
						page.setSqlCondition(sqlCondition);
					}else {
						if(StringUtils.isEmpty(sqlCondition)) {
							sqlCondition="ScmPurOrder.id in (Select distinct ScmPurOrder.id from ScmPurOrder,ScmPurOrderEntry Where ScmPurOrder.id=ScmPurOrderEntry.poId"
									+ " and ScmPurOrderEntry.reqDate >= '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getBegBizDate())+"')";
						}else {
							sqlCondition=sqlCondition+" And ScmPurOrder.id in (Select distinct ScmPurOrder.id from ScmPurOrder,ScmPurOrderEntry Where ScmPurOrder.id=ScmPurOrderEntry.poId"
									+ " and ScmPurOrderEntry.reqDate >= '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getBegBizDate())+"')";
						}
						page.setSqlCondition(sqlCondition);
					}
				}else if(scmPurOrderAdvQuery.getEndBizDate()!=null) {
					if(StringUtils.isEmpty(sqlCondition)) {
						sqlCondition="ScmPurOrder.id in (Select distinct ScmPurOrder.id from ScmPurOrder,ScmPurOrderEntry Where ScmPurOrder.id=ScmPurOrderEntry.poId"
								+ " and ScmPurOrderEntry.reqDate <= '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getEndBizDate())+"')";
					}else {
						sqlCondition=sqlCondition+" And ScmPurOrder.id in (Select distinct ScmPurOrder.id from ScmPurOrder,ScmPurOrderEntry Where ScmPurOrder.id=ScmPurOrderEntry.poId"
								+ " and ScmPurOrderEntry.reqDate <= '"+FormatUtils.fmtDate(scmPurOrderAdvQuery.getEndBizDate())+"')";
					}
					page.setSqlCondition(sqlCondition);
				}
				if(scmPurOrderAdvQuery.getCreateDateFrom()!=null){
					if(scmPurOrderAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurOrderAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurOrderAdvQuery.getCreateDateFrom())));
					}
				}else if(scmPurOrderAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurOrder2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderAdvQuery.getCreateDateTo(),1))));
				}
				if(scmPurOrderAdvQuery.getReleaseDateFrom()!=null){
					if(scmPurOrderAdvQuery.getReleaseDateTo()!=null) {
						page.getParam().put(ScmPurOrder2.FN_RELEASEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_RELEASEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurOrderAdvQuery.getReleaseDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderAdvQuery.getReleaseDateTo(),1))));
					}else {
						page.getParam().put(ScmPurOrder2.FN_RELEASEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_RELEASEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurOrderAdvQuery.getReleaseDateFrom())));
					}
				}else if(scmPurOrderAdvQuery.getReleaseDateTo()!=null) {
					page.getParam().put(ScmPurOrder2.FN_RELEASEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_RELEASEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderAdvQuery.getReleaseDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmPurOrderAdvQuery.getCreator())){
					page.getParam().put(ScmPurOrder2.FN_CREATOR,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_CREATOR, QueryParam.QUERY_EQ,scmPurOrderAdvQuery.getCreator()));
				}
				if(scmPurOrderAdvQuery.getVendorId()>0){
					page.getParam().put(ScmPurOrder2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurOrderAdvQuery.getVendorId())));
				}else if(StringUtils.isNotBlank(scmPurOrderAdvQuery.getVendorCode())) {
					Scmsupplier2 scmsupplier = scmsupplierBiz.selectByCode(scmPurOrderAdvQuery.getVendorCode(), param);
					if(scmsupplier!=null)
						page.getParam().put(ScmPurOrder2.FN_VENDORID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmsupplier.getId())));
				}
				if(scmPurOrderAdvQuery.getVendorClassId()>0){
					page.getParam().put(Scmsuppliergroup.FN_ID,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsuppliergroup.class) + "." +Scmsuppliergroup.FN_ID, QueryParam.QUERY_EQ,String.valueOf(scmPurOrderAdvQuery.getVendorClassId())));
				}
				
				if(StringUtils.isNotBlank(scmPurOrderAdvQuery.getPoNo())){
					page.getParam().put(ScmPurOrder2.FN_PONO, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ, String.valueOf(scmPurOrderAdvQuery.getPoNo())));
				}
				if(StringUtils.isNotBlank(scmPurOrderAdvQuery.getBuyerId())){
					page.getParam().put(ScmPurOrder2.FN_BUYERID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_BUYERID, QueryParam.QUERY_EQ,scmPurOrderAdvQuery.getBuyerId()));
				}
				if(scmPurOrderAdvQuery.getOrderDateFrom()!=null){
					if(scmPurOrderAdvQuery.getOrderDateTo()!=null) {
						page.getParam().put(ScmPurOrder2.FN_ORDERDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_ORDERDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurOrderAdvQuery.getOrderDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderAdvQuery.getOrderDateTo(),1))));
					}else {
						page.getParam().put(ScmPurOrder2.FN_ORDERDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_ORDERDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurOrderAdvQuery.getOrderDateFrom())));
					}
				}else if(scmPurOrderAdvQuery.getOrderDateTo()!=null) {
					page.getParam().put(ScmPurOrder2.FN_ORDERDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +ScmPurOrder2.FN_ORDERDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurOrderAdvQuery.getOrderDateTo(),1))));
				}
				
				if(StringUtils.isNotBlank(scmPurOrderAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmPurOrderAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmPurOrder2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." +	ScmPurOrder2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
				if(StringUtils.isNotBlank(scmPurOrderAdvQuery.getOrgUnitNo())) {
					page.setSqlCondition("ScmPurOrder.orgUnitNo='"+scmPurOrderAdvQuery.getOrgUnitNo()+"'");
				}
			}
		}
	}

	@Override
	public void writeBackInvQty(ScmPurReceive2 scmPurReceive,int sign, Param param) throws AppException {
		scmPurOrderEntryBiz.writeBackInvQty(scmPurReceive,sign,param);
	}

	@Override
	public List<ScmPurOrder2> selectByOtherIssueBill(long otId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("otId", otId);
		return ((ScmPurOrderDAO)dao).selectByOtherIssueBill(map);
	}

	@Override
	public int updateSendedStaus(long poId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("poId", poId);
		map.put("sendDate", FormatUtils.fmtDateTime(CalendarUtil.getDate(param)));
		return ((ScmPurOrderDAO)dao).updateSendedStaus(map);
	}

	@Override
	public void writeBackQtyAndStatus(List<ScmPurReceiveEntry2> scmPurReceiveEntryList, Param param) throws AppException {
		if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()) {
			StringBuffer ids= new StringBuffer("");
			for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList) {
				if(scmPurReceiveEntry.getPoDtlId()==0)
					continue;
				if(StringUtils.isNotBlank(ids.toString()))
					ids.append(",");
				ids.append(scmPurReceiveEntry.getPoDtlId());
			}
			if(StringUtils.isNotBlank(ids.toString())) {
				HashMap<String, Object> queryMap = new HashMap<String,Object>();
				queryMap.put(ScmPurOrderEntry2.FN_ID, new QueryParam(ScmPurOrderEntry2.FN_ID, QueryParam.QUERY_IN, ids.toString()));
				List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz.findAll(queryMap, param);
				if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()) {
					for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList) {
						if(scmPurReceiveEntry.getPoDtlId()==0)
							continue;
						for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
							if(scmPurOrderEntry.getId()==scmPurReceiveEntry.getPoDtlId()) {
								scmPurOrderEntry.setReceiveQty(scmPurOrderEntry.getReceiveQty().add(scmPurReceiveEntry.getBaseQty()));
								if((scmPurOrderEntry.getReceiveQty().add(scmPurOrderEntry.getMovedQty().add(scmPurOrderEntry.getReturnQty()))).compareTo(scmPurOrderEntry.getBaseQty())>=0){
									scmPurOrderEntry.setRowStatus("C");
								}else{
									scmPurOrderEntry.setRowStatus("E");
								}
								scmPurOrderEntryBiz.updateDirect(scmPurOrderEntry, param);
								break;
							}
						}
					}
				}
				//更新状态
				List<ScmPurOrderEntry2> scmPurOrderEntryList2 = scmPurOrderEntryBiz.selectStatusByIds(ids.toString(),param);
				if(scmPurOrderEntryList2!=null && !scmPurOrderEntryList2.isEmpty()) {
					for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList2) {
						scmPurOrderEntryBiz.updateBillStatusByEntry(scmPurOrderEntry, param);
					}
				}
			}
		}
	}

	@Override
	public List<ScmPurOrder2> queryPurOrderList(
			ScmPurOrderAdvQuery scmPurOrderAdvQuery, int pageNum, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurOrder2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		
		page.setModel(scmPurOrderAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		return this.findPage(page, param);
	}
	
	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurOrderAdvQuery) {
				ScmPurOrderAdvQuery scmPurOrderAdvQuery = (ScmPurOrderAdvQuery) page.getModel();
				if(scmPurOrderAdvQuery.isFromInterface())
					map.put("sortByDate", "Y");
			}
		}
		return map;
	}

	@Override
	public ScmPurOrder2 queryPurOrder(ScmPurOrder2 scmPurOrder,
			Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurOrder2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,scmPurOrder.getPoNo()));
		
		List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
		ScmPurOrder2 scmPurOrder2 = new ScmPurOrder2();
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			scmPurOrder2 = scmPurOrderList.get(0);
			scmPurOrder2.setScmPurOrderEntryList(scmPurOrderEntryBiz.selectByPoId(scmPurOrder2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurOrder2.getId(), "PurOrder",param);
			if (scmBillPendingUsr != null) {
				scmPurOrder2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurOrder2;
	}

	@Override
	public ScmPurOrder2 doAuditPurOrder(CommonAuditParams commonAuditParams,
			Param param) throws AppException {
		ScmPurOrder2 order = null;
		
		ScmPurOrder2 scmPurOrder= new ScmPurOrder2();
		scmPurOrder.setId(commonAuditParams.getBillId());
		scmPurOrder.setPoNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		List<CommonAuditDetailParams> detailList = commonAuditParams.getDetailList();
		List<ScmPurOrder2> scmPurOrderList = new ArrayList<> ();
		List<ScmPurOrderEntry2> scmPurOrderEntryList = new ArrayList<> ();
		int countRefuse = 0;
		
		if(scmPurOrder.getId()>0){
			order = this.selectDirect(scmPurOrder.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurOrder2.FN_PONO,
					new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,
							scmPurOrder.getPoNo()));
			map.put(ScmPurOrder2.FN_CONTROLUNITNO, new QueryParam(ScmPurOrder2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			scmPurOrderList =this.findAll(map, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
				order=scmPurOrderList.get(0);
			}
		}
		
		if (order != null) {
			this.setConvertMap(order,param);
			scmPurOrderEntryList = scmPurOrderEntryBiz.selectByPoId(order.getId(), param);
			
			if(scmPurOrderEntryList == null || scmPurOrderEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			BillType2 billType = billTypeBiz.selectByOrgAndCode(order.getFinOrgUnitNo(), "PurOrder", param);
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),order, param);
				commonEventHistoryBiz.updateInvalid(order,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(order.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(order, commonAuditOpinionR, param);
				//记录行审批记录
				if(detailList!=null && !detailList.isEmpty()) {
					List<ScmAuditDetailHistory> scmAuditDetailHistoryList = new ArrayList<>();
					for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
						String rowOpinion = commonAuditDetailParams.getRowOpinion();
						if(StringUtils.equals("Y", rowOpinion) || StringUtils.equals("agree", rowOpinion)) {
							rowOpinion="Y";
						}else if(StringUtils.equals("N", rowOpinion) || StringUtils.equals("refuse", rowOpinion)) {
							rowOpinion="N";
						}
						int lineId = commonAuditDetailParams.getLineId();
						String rowAuditRemarks = commonAuditDetailParams.getRemarks();
						if(StringUtils.isNotBlank(rowAuditRemarks)){
							rowAuditRemarks = rowAuditRemarks.replace(",", "，");
						}
						for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
							if (lineId == scmPurOrderEntry.getLineId()) {
								ScmAuditDetailHistory scmAuditDetailHistory = new ScmAuditDetailHistory(true);
								scmAuditDetailHistory.setBillId(scmPurOrderEntry.getPoId());
								scmAuditDetailHistory.setBillType("PurOrder");
								scmAuditDetailHistory.setLineId(lineId);
								scmAuditDetailHistory.setOpinion(rowOpinion);
								scmAuditDetailHistory.setOper(param.getUsrCode());
								scmAuditDetailHistory.setOperDate(CalendarUtil.getDate(param));
								scmAuditDetailHistory.setRemarks(rowAuditRemarks);
								scmAuditDetailHistoryList.add(scmAuditDetailHistory);
							}
						}
					}
					if(scmAuditDetailHistoryList!=null && !scmAuditDetailHistoryList.isEmpty()){
						scmAuditDetailHistoryBiz.add(scmAuditDetailHistoryList, param);
					}
				}
				
				//驳回则将单据变回新单状态
				order.setStatus("R");
				order.setChecker(null);
				order.setCheckDate(null);
				order.setPendingUsr(null);
				this.updateDirect(order, param);
				if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()) {
					for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList) {
						scmPurOrderEntry.setRowStatus("R");
						scmPurOrderEntryBiz.updateDirect(scmPurOrderEntry, param);
					}
				}
				this.sendAuditMsg(order,"R",billType.getBillCode(), param);
				return order;
			}
			String processInstanceId = order.getWorkFlowNo();
			boolean isCompleteTask = true;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion,  param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			List<CommonBillEntryStatus> commonBillEntryStatusList = new ArrayList();
			
			//I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A
			if (scmPurOrderEntryList != null && scmPurOrderEntryList.size() > 0) {
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						//A通过，B部分通过，N未通过
						if(detailList==null || detailList.isEmpty()) {
							//未传入明细则认为符合条件的明细都同意
							for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
								CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
								commonBillEntryStatus.setLineId(scmPurOrderEntry.getLineId());
								commonBillEntryStatus.setRowStatus(scmPurOrderEntry.getRowStatus());
								commonBillEntryStatusList.add(commonBillEntryStatus);
								if(!StringUtils.equals(scmPurOrderEntry.getRowStatus(), "N")) {
									scmPurOrderEntry.setRowStatus("A");
								}else {
									countRefuse++;
								}
							}
							if (countRefuse == 0) {
								order.setPendingUsr("");
								order.setStatus("A");
							} else if (countRefuse > 0 && countRefuse < scmPurOrderEntryList.size()) {
								order.setStatus("B");
							} else {
								order.setPendingUsr("");
								order.setStatus("N");
							}
						}else {
							for (CommonAuditDetailParams commonAuditDetailParams : detailList) {
								String rowOpinion = commonAuditDetailParams.getRowOpinion();
								int lineId = commonAuditDetailParams.getLineId();
								for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
									if (lineId == scmPurOrderEntry.getLineId()) {
										CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
										commonBillEntryStatus.setLineId(scmPurOrderEntry.getLineId());
										commonBillEntryStatus.setRowStatus(scmPurOrderEntry.getRowStatus());
										commonBillEntryStatusList.add(commonBillEntryStatus);
										if (!StringUtils.equals(scmPurOrderEntry.getRowStatus(), "N") 
												&& (StringUtils.equals("agree",rowOpinion) || StringUtils.equals("Y",rowOpinion))) {
											scmPurOrderEntry.setRowStatus("A");
										} else {
											scmPurOrderEntry.setRowStatus("N");
											countRefuse++;
										}
										break;
									}
								}
							}
							if (countRefuse == 0) {
								order.setPendingUsr("");
								order.setStatus("A");
							} else if (countRefuse > 0 && countRefuse < scmPurOrderEntryList.size()) {
								order.setStatus("B");
							} else {
								order.setPendingUsr("");
								order.setStatus("N");
							}
						}
					} else {
						//P：审核中，N：未通过
						if(detailList==null || detailList.isEmpty()) {
							//未传入明细则认为符合条件的明细都同意
							for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
								CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
								commonBillEntryStatus.setLineId(scmPurOrderEntry.getLineId());
								commonBillEntryStatus.setRowStatus(scmPurOrderEntry.getRowStatus());
								commonBillEntryStatusList.add(commonBillEntryStatus);
								if(!StringUtils.equals(scmPurOrderEntry.getRowStatus(), "N")) {
									scmPurOrderEntry.setRowStatus("P");
								}else {
									countRefuse++;
								}
							}
							if (countRefuse == scmPurOrderEntryList.size()) {
								order.setPendingUsr("");
								order.setStatus("N");
							} else {
								order.setStatus("P");
							}	
						}else {
							for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
								String rowOpinion = commonAuditDetailParams.getRowOpinion();
								int lineId = commonAuditDetailParams.getLineId();
								for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
									if (lineId == scmPurOrderEntry.getLineId()) {
										CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
										commonBillEntryStatus.setLineId(scmPurOrderEntry.getLineId());
										commonBillEntryStatus.setRowStatus(scmPurOrderEntry.getRowStatus());
										commonBillEntryStatusList.add(commonBillEntryStatus);
										if (!StringUtils.equals(scmPurOrderEntry.getRowStatus(), "N") && 
												(StringUtils.equals("agree",rowOpinion) || StringUtils.equals("Y",rowOpinion))) {
											scmPurOrderEntry.setRowStatus("P");
										} else {
											scmPurOrderEntry.setRowStatus("N");
											countRefuse++;
										}
										break;
									}
								}
								
								if (countRefuse == scmPurOrderEntryList.size()) {
									order.setPendingUsr("");
									order.setStatus("N");
								} else {
									order.setStatus("P");
								}	
							}
						}
					}
				} else {
					for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
						CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
						commonBillEntryStatus.setLineId(scmPurOrderEntry.getLineId());
						commonBillEntryStatus.setRowStatus(scmPurOrderEntry.getRowStatus());
						commonBillEntryStatusList.add(commonBillEntryStatus);
						scmPurOrderEntry.setRowStatus("N");
					}
					order.setStatus("N");
					order.setPendingUsr("");
				}	
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), order, param);
			order.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					order.setPendingUsr(StringUtils.left(usrList,250));
					scmBillPendingBiz.addPendingBill(usrList, order, param);
					PurOrderParams purOrderParams = new PurOrderParams();
					purOrderParams.setPoNo(order.getPoNo());
					AuditMsgUtil.sendAuditMsg("PurOrder",order,purOrderParams, usrList, param);
				}
			}else {
				order.setPendingUsr("");
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(order.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				order.setStepNo(stepNo);
				order.setCheckDate(CalendarUtil.getDate(param));
				this.update(order, param);
			} catch (Exception e) {
				throw e;
			}
			
			for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
				long poId = scmPurOrderEntry.getPoId();
				String status = scmPurOrderEntry.getRowStatus();
				String checker = param.getUsrCode();
				Date checkDate = CalendarUtil.getDate(param);
				int lineId = scmPurOrderEntry.getLineId();
				scmPurOrderEntryBiz.updateRowStatusByLineId(poId, status, checker, checkDate, lineId, param);
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setHandlerContent(gson.toJson(commonBillEntryStatusList));
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(order, commonAuditOpinion, param);
			//记录行审批记录
			if(detailList!=null && !detailList.isEmpty()) {
				List<ScmAuditDetailHistory> scmAuditDetailHistoryList = new ArrayList<>();
				for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
					String rowOpinion = commonAuditDetailParams.getRowOpinion();
					if(StringUtils.equals("Y", rowOpinion) || StringUtils.equals("agree", rowOpinion)) {
						rowOpinion="Y";
					}else if(StringUtils.equals("N", rowOpinion) || StringUtils.equals("refuse", rowOpinion)) {
						rowOpinion="N";
					}
					int lineId = commonAuditDetailParams.getLineId();
					String rowAuditRemarks = commonAuditDetailParams.getRemarks();
					if(StringUtils.isNotBlank(rowAuditRemarks)){
						rowAuditRemarks = rowAuditRemarks.replace(",", "，");
					}
					for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
						if (lineId == scmPurOrderEntry.getLineId()) {
							ScmAuditDetailHistory scmAuditDetailHistory = new ScmAuditDetailHistory(true);
							scmAuditDetailHistory.setBillId(scmPurOrderEntry.getPoId());
							scmAuditDetailHistory.setBillType("PurOrder");
							scmAuditDetailHistory.setLineId(lineId);
							scmAuditDetailHistory.setOpinion(rowOpinion);
							scmAuditDetailHistory.setOper(param.getUsrCode());
							scmAuditDetailHistory.setOperDate(CalendarUtil.getDate(param));
							scmAuditDetailHistory.setRemarks(rowAuditRemarks);
							scmAuditDetailHistoryList.add(scmAuditDetailHistory);
						}
					}
				}
				if(scmAuditDetailHistoryList!=null && !scmAuditDetailHistoryList.isEmpty()){
					scmAuditDetailHistoryBiz.add(scmAuditDetailHistoryList, param);
				}
			}
			if(StringUtils.contains("A,B", order.getStatus())) {
				//通过或部分通过时检查是否自动下达
				if(billType!=null && billType.isAutoRelease()) {
					this.release(order, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return order;
	}
	
	private void sendAuditMsg(ScmPurOrder2 order,String activeType,String billCode,Param param) {
//		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(order.getWorkFlowNo(),param);
//		if(StringUtils.isNotBlank(usrList)) {
//			order.setPendingUsr(StringUtils.left(usrList,250));
			PurOrderParams purOrderParams = new PurOrderParams();
			purOrderParams.setPoNo(order.getPoNo());
			AuditMsgUtil.sendAuditMsg("PurOrder",activeType,order,purOrderParams, order.getCreator(), param);
//		}
	}

	@Override
	public ScmPurOrder2 doUnAuditPurOrder(ScmPurOrder2 scmPurOrder, Param param)
			throws AppException {
		
		ScmPurOrder2 order = null;
		List<ScmPurOrder2> scmPurOrderList = new ArrayList<> ();
		List<ScmPurOrderEntry2> scmPurOrderEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurOrder.getId()>0){
			order = this.selectDirect(scmPurOrder.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurOrder2.FN_PONO,
					new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,
							scmPurOrder.getPoNo()));
			map.put(ScmPurOrder2.FN_CONTROLUNITNO, new QueryParam(ScmPurOrder2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmPurOrderList =this.findAll(map, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
				order=scmPurOrderList.get(0);
			}
		}
		
		if (order != null) {
			if(!StringUtils.contains("A,B,N,P", order.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			scmPurOrderEntryList = scmPurOrderEntryBiz.selectByPoId(order.getId(), param);
			
			if(scmPurOrderEntryList == null || scmPurOrderEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			String processInstanceId = order.getWorkFlowNo();
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurOrderList.add(order);
				bean.setList(scmPurOrderList);
				bean.setList2(scmPurOrderEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(order.getFinOrgUnitNo(), "PurOrder", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				order.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String tableName = ClassUtils.getFinalModelSimpleName(order);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,order.getStepNo(),order.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getHandlerContent())) {
				List<CommonBillEntryStatus> commonBillEntryStatusList = gson.fromJson(commonEventHistory.getHandlerContent(),new TypeToken<List<CommonBillEntryStatus>>(){}.getType());
				if(commonBillEntryStatusList!=null && !commonBillEntryStatusList.isEmpty()) {
					for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
						for(CommonBillEntryStatus commonBillEntryStatus:commonBillEntryStatusList) {
							if(commonBillEntryStatus.getLineId()==scmPurOrderEntry.getLineId()) {
								scmPurOrderEntry.setRowStatus(commonBillEntryStatus.getRowStatus());
								commonBillEntryStatusList.remove(commonBillEntryStatus);
								break;
							}
						}
					}
				}
				if(StringUtils.isNotBlank(commonEventHistory.getPreStepNo())){
					CommonEventHistory preCommonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,order.getStepNo(),order.getPK(),param);
					if(preCommonEventHistory!=null) {
						order.setChecker(preCommonEventHistory.getOper());
						order.setCheckDate(preCommonEventHistory.getOperDate());
					}else {
						order.setChecker(null);
						order.setCheckDate(null);
					}
				}else {
					order.setChecker(null);
					order.setCheckDate(null);
				}
			}
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			order.setStatus(status);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		try {
			order.setChecker(param.getUsrCode());
			order.setCheckDate(CalendarUtil.getDate(param));
			this.update(order, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),order, param);
			commonEventHistoryBiz.updateInvalid(order,order.getStepNo(),param);
		} catch (Exception e) {
			throw e;
		}
		
		for (ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
			long poId = scmPurOrderEntry.getPoId();
			String status = scmPurOrderEntry.getRowStatus();
			String checker = param.getUsrCode();
			Date checkDate = CalendarUtil.getDate(param);
			int lineId = scmPurOrderEntry.getLineId();
			scmPurOrderEntryBiz.updateRowStatusByLineId(poId, status, checker, checkDate, lineId, param);
		}
		//scmPurOrderEntryBiz.updateBillStatusByEntry(scmPurOrderEntryList.get(0), param);
		CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
		commonAuditOpinion.setStepNo(order.getStepNo());
		commonAuditOpinion.setActiveType("U");
		commonAuditOpinion.setOpinion("Y");
		commonEventHistoryBiz.addEventHistory(order, commonAuditOpinion, param);
		
		return order;
		
	}
	
	@Override
	public ScmPurOrder2 doLockPurOrder(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurOrder2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,scmPurOrder.getPoNo()));
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		if(StringUtils.isNotBlank(scmPurOrder.getStatus())){
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_STATUS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_STATUS), QueryParam.QUERY_EQ, scmPurOrder.getStatus()));
		}
		List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			ScmPurOrder2 scmPurOrder2 = scmPurOrderList.get(0);
			if(StringUtils.isNotBlank(scmPurOrder.getLockStatus())){
				scmPurOrder2.setLockStatus(scmPurOrder.getLockStatus());
			}else{
				scmPurOrder2.setLockStatus("THD");
			}
			this.updateLockStatusOrContractNoByPoId(scmPurOrder2.getId(),scmPurOrder2.getLockStatus(),scmPurOrder2.getContractNo(),param);
			//this.updateDirect(scmPurOrder2, param);
			return scmPurOrder2;
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}

	@Override
	public ScmPurOrder2 doUpdatePurOrderContractNo(ScmPurOrder2 scmPurOrder, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurOrder2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,scmPurOrder.getPoNo()));
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			ScmPurOrder2 scmPurOrder2 = scmPurOrderList.get(0);
			scmPurOrder2.setContractNo(scmPurOrder.getContractNo());
			scmPurOrder2.setLockStatus("");
			this.updateLockStatusOrContractNoByPoId(scmPurOrder2.getId(),scmPurOrder2.getLockStatus(),scmPurOrder2.getContractNo(),param);
			//this.updateDirect(scmPurOrder2, param);
			return scmPurOrder2;
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}

	@Override
	public ScmPurOrder2 doUpdatePurOrderStatus(ScmPurOrder2 scmPurOrder, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurOrder2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,scmPurOrder.getPoNo()));
		page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
				new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			ScmPurOrder2 scmPurOrder2 = scmPurOrderList.get(0);
			scmPurOrder2.setStatus(scmPurOrder.getStatus());
			scmPurOrder2.setLockStatus("");
			this.updateStatus(scmPurOrder2, param);
			return scmPurOrder2;
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}

	@Override
	public List<ScmPurOrder2> queryPurOrderOA(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurOrder2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if ("1".equals(scmPurOrder.getQueryType())){
			if(StringUtils.isNotBlank(scmPurOrder.getPoNo())){
				page.getParam().put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,scmPurOrder.getPoNo()));
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		}else{
			page.getParam().put(ScmPurOrder2.FN_PONO,new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,scmPurOrder.getPoNo()));
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_ORGUNITNO), QueryParam.QUERY_EQ, param.getOrgUnitNo()));
		}
		if(StringUtils.isNotBlank(scmPurOrder.getStatus())){
			String status[] = StringUtils.split(scmPurOrder.getStatus(), ",");
			StringBuffer statusBuffer = new StringBuffer("");
			for(String sta:status){
				if(StringUtils.isNotBlank(statusBuffer.toString()))
					statusBuffer.append(",");
				statusBuffer.append("'").append(sta).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_STATUS), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurOrder2.class) + "." + ScmPurOrder2.FN_STATUS), QueryParam.QUERY_IN, statusBuffer.toString()));
		}
		
		List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
		List<ScmPurOrder2> scmPurOrderList2 = new ArrayList<>();
		if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
			for(ScmPurOrder2 scmPurOrder2 : scmPurOrderList){
				if("1".equals(scmPurOrder.getQueryType()) && (StringUtils.isNotBlank(scmPurOrder2.getLockStatus()) || StringUtils.isNotBlank(scmPurOrder2.getContractNo()))){
					continue;
				}
				if (StringUtils.isNotBlank(scmPurOrder2.getFinOrgUnitNo())){
					//财务组织
					OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrder2.getFinOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmPurOrder2.setFinOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (StringUtils.isNotBlank(scmPurOrder2.getStorageOrgUnitNo())){
					//库存组织
					OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrder2.getStorageOrgUnitNo(), param);
					if (orgBaseUnit != null) {
						scmPurOrder2.setStorageOrgUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (StringUtils.isNotBlank(scmPurOrder2.getControlUnitNo())){
					//控制单元
					OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurOrder2.getControlUnitNo(), param);
					if (orgBaseUnit != null) {
						scmPurOrder2.setControlUnitName(orgBaseUnit.getOrgUnitName());
					}
				}
				if (scmPurOrder2.getPurGroupId() > 0){
					//采购组
					ScmPurGroup scmPurGroup = scmPurGroupBiz.selectDirect(scmPurOrder2.getPurGroupId(), param);
					if (scmPurGroup != null) {
						scmPurOrder2.setPurGroupName(scmPurGroup.getPurGroupName());
					}
				}
				scmPurOrder2.setScmPurOrderEntryList(scmPurOrderEntryBiz.selectByPoIdOA(scmPurOrder2.getId(), param));
				scmPurOrderList2.add(scmPurOrder2);
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurOrderList2;
	}

	@Override
	public BigDecimal getTotalOrderQty(ScmPurOrderAdvQuery scmPurOrderAdvQuery, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("begDate", FormatUtils.fmtDate(scmPurOrderAdvQuery.getBegBizDate()));
		map.put("endDate", FormatUtils.fmtDate(scmPurOrderAdvQuery.getEndBizDate()));
		map.put("itemId", scmPurOrderAdvQuery.getItemId());
		return ((ScmPurOrderDAO)dao).getTotalOrderQty(map);
	}

	@Override
	public ScmPurOrder2 unlockBill(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		ScmPurOrder2 pruOrder = null;
		if(scmPurOrder.getId()>0){
			pruOrder = this.selectDirect(scmPurOrder.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurOrder2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurOrder2.FN_PONO,
					new QueryParam(ScmPurOrder2.FN_PONO, QueryParam.QUERY_EQ,
							scmPurOrder.getPoNo()));
			
			List<ScmPurOrder2> scmPurOrderList =this.findPage(page, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()){
				pruOrder=scmPurOrderList.get(0);
			}
		}
		if(pruOrder!=null){
			if(StringUtils.isBlank(pruOrder.getLockStatus())){
				return pruOrder;
			}else{
				pruOrder.setLockStatus("");
				this.updateLockStatusOrContractNoByPoId(pruOrder.getId(),pruOrder.getLockStatus(),pruOrder.getContractNo(),param);
				//this.updateDirect(pruOrder, param);
				return pruOrder;
			}
		}
		return null;
	}

	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("poId", scmPurOrder.getId());
			System.out.println("po:"+((ScmPurOrderDAO)dao).selectDrillBills(map).get(0).getWrNo());
			return ((ScmPurOrderDAO)dao).selectDrillBills(map);
		}
		return null;
	}

	@Override
	public void updatePushed(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("poId", scmPurOrder.getId());
			map.put("pushed", (scmPurOrder.isPushed() ? 1 : 0));
			((ScmPurOrderDAO)dao).updatePushed(map);
		}
	}

	@Override
	public void updateVersion(ScmPurOrderEntry2 scmPurOrderEntry, Param param) throws AppException {
		if(scmPurOrderEntry != null){
			ScmPurOrder2 scmPurOrder = this.selectDirect(scmPurOrderEntry.getPoId(), param);
			if(scmPurOrder != null){
				HashMap<String,Object> map = new HashMap<String,Object>();
				map.put("poId", scmPurOrder.getId());
				map.put("version", scmPurOrder.getVersion());
				((ScmPurOrderDAO)dao).updateVersion(map);
			}
		}
	}

	@Override
	protected void afterUpdate(ScmPurOrder2 oldEntity, ScmPurOrder2 newEntity, Param param) throws AppException {
		if(oldEntity!=null && newEntity!=null && oldEntity.getVendorId()!=newEntity.getVendorId()) {
			ScmSupplierRegInvitation oldscmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(oldEntity.getVendorId(),param.getControlUnitNo(),param);
			if(oldscmSupplierRegInvitation!=null && oldscmSupplierRegInvitation.getPlatSupplierId()>0) {
				ScmSupplierRegInvitation scmSupplierRegInvitation = scmSupplierRegInvitationBiz.selectByVendorIdAndCtrl(newEntity.getVendorId(),param.getControlUnitNo(),param);
				if(scmSupplierRegInvitation==null || scmSupplierRegInvitation.getPlatSupplierId()==0) {
					SupplierPlatUtil supplierPlatUtil = new SupplierPlatUtil();
					supplierPlatUtil.changeSyncData(oldEntity, param);
				}
			}
		}
	}

	@Override
	public ScmPurOrder2 selectByPoNo(String poNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("poNo", poNo);
		return ((ScmPurOrderDAO)dao).selectByPoNo(map);
	}

	@Override
	public void writeBackMovedQtyAndStatus(List<ScmInvSaleOrderEntry2> scmInvSaleOrderEntryList, Param param)
			throws AppException {
		if(scmInvSaleOrderEntryList!=null && !scmInvSaleOrderEntryList.isEmpty()) {
			StringBuffer ids= new StringBuffer("");
			for(ScmInvSaleOrderEntry2 scmInvSaleOrderEntry:scmInvSaleOrderEntryList) {
				if(scmInvSaleOrderEntry.getSourceBillDtlId()==0)
					continue;
				if(StringUtils.isNotBlank(ids.toString()))
					ids.append(",");
				ids.append(scmInvSaleOrderEntry.getSourceBillDtlId());
			}
			if(StringUtils.isNotBlank(ids.toString())) {
				HashMap<String, Object> queryMap = new HashMap<String,Object>();
				queryMap.put(ScmPurOrderEntry2.FN_ID, new QueryParam(ScmPurOrderEntry2.FN_ID, QueryParam.QUERY_IN, ids.toString()));
				List<ScmPurOrderEntry2> scmPurOrderEntryList = scmPurOrderEntryBiz.findAll(queryMap, param);
				if(scmPurOrderEntryList!=null && !scmPurOrderEntryList.isEmpty()) {
					for(ScmInvSaleOrderEntry2 scmInvSaleOrderEntry:scmInvSaleOrderEntryList) {
						if(scmInvSaleOrderEntry.getSourceBillDtlId()==0)
							continue;
						for(ScmPurOrderEntry2 scmPurOrderEntry : scmPurOrderEntryList) {
							if(scmPurOrderEntry.getId()==scmInvSaleOrderEntry.getSourceBillDtlId()) {
								scmPurOrderEntry.setMovedQty(scmPurOrderEntry.getMovedQty().add(scmInvSaleOrderEntry.getBaseQty()));
								if((scmPurOrderEntry.getReceiveQty().add(scmPurOrderEntry.getMovedQty().add(scmPurOrderEntry.getReturnQty()))).compareTo(scmPurOrderEntry.getBaseQty())>=0){
									scmPurOrderEntry.setRowStatus("C");
								}else{
									scmPurOrderEntry.setRowStatus("E");
								}
								scmPurOrderEntryBiz.updateDirect(scmPurOrderEntry, param);
								break;
							}
						}
					}
				}
				//更新状态
				List<ScmPurOrderEntry2> scmPurOrderEntryList2 = scmPurOrderEntryBiz.selectStatusByIds(ids.toString(),param);
				if(scmPurOrderEntryList2!=null && !scmPurOrderEntryList2.isEmpty()) {
					for(ScmPurOrderEntry2 scmPurOrderEntry:scmPurOrderEntryList2) {
						scmPurOrderEntryBiz.updateBillStatusByEntry(scmPurOrderEntry, param);
					}
				}
			}
		}
	}

	@Override
	public void updateLockStatusOrContractNoByPoId(long poId, String lockStatus, String contractNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("lockStatus", lockStatus);
		map.put("contractNo", contractNo);
		map.put("poId", poId);
		((ScmPurOrderDAO)dao).updateLockStatusOrContractNo(map);
	}

	@Override
	public void updateBillNoChangeTime(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder == null || scmPurOrder.getId() <= 0){
			throw new AppException("argument.null", new String[] { "scmPurOrder" });
		}
		ScmPurOrder2 scmPurOrder2 = this.selectDirect(scmPurOrder.getId(), param);
		if(scmPurOrder2 != null){
			((ScmPurOrderDAO)dao).updateBillNoChangeTime(scmPurOrder);
		}
	}

	@Override
	public void writeBackSended(List<ScmPurReceiveEntry2> scmPurReceiveEntryList, Param param) throws AppException {
		if(scmPurReceiveEntryList!=null && !scmPurReceiveEntryList.isEmpty()) {
			StringBuffer entryIds= new StringBuffer("");
			for(ScmPurReceiveEntry2 scmPurReceiveEntry:scmPurReceiveEntryList) {
				if(scmPurReceiveEntry.getPoDtlId()==0){
					continue;
				}
				if(StringUtils.isNotBlank(entryIds.toString())){
					entryIds.append(",");
				}
				entryIds.append(scmPurReceiveEntry.getPoDtlId());
			}
			if(StringUtils.isNotBlank(entryIds.toString())) {
				List<ScmPurOrder2> scmPurOrderList = this.selectByEntryIds(entryIds.toString(), "1", param);
				if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()) {
					for(ScmPurOrder2 scmPurOrder : scmPurOrderList){
						boolean flag = this.checkFollowUpBill(scmPurOrder, param);
						if(flag){
							this.updateUnSendedStaus(scmPurOrder.getId(), param);
						}
					}
				}
			}
		}
	}

	@Override
	public List<ScmPurOrder2> selectByEntryIds(String entryIds, String sended, Param param)
			throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("entryIds", entryIds);
		if(StringUtils.isNotBlank(sended)){
			map.put("sended", sended);
		}
		return ((ScmPurOrderDAO)dao).selectByEntryIds(map);
	}

	@Override
	public int updateUnSendedStaus(long poId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("poId", poId);
		return ((ScmPurOrderDAO)dao).updateUnSendedStaus(map);
	}

	@Override
	public ScmPurOrder2 selectByTypeCode(String code, Param param) throws AppException {
		if (StringUtils.isNotBlank(code)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("bizType", code);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmPurOrderDAO)dao).selectByTypeCode(map);
		}
		return null;
	}

	@Override
	public ScmPurOrder2 updatePrtCount(ScmPurOrder2 scmPurOrder, Param param) throws AppException {
		if(scmPurOrder.getId()>0){
			ScmPurOrder2 bill = this.selectDirect(scmPurOrder.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurOrder;
	}
}

