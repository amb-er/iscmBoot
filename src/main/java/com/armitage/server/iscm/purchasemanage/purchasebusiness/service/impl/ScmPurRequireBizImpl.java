package com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.purrequire.params.PurReqMaterialListParams;
import com.armitage.server.api.business.purrequire.params.PurReqOrgUnitParams;
import com.armitage.server.api.business.purrequire.params.PurReqPersonParams;
import com.armitage.server.api.business.purrequire.params.PurReqPurOrgUnitParams;
import com.armitage.server.api.business.purrequire.params.PurReqWareHouseParams;
import com.armitage.server.api.business.purrequire.params.PurRequireAddParams;
import com.armitage.server.api.business.purrequire.params.PurRequireDetailParams;
import com.armitage.server.api.business.purrequire.params.PurRequireEditParams;
import com.armitage.server.api.business.purrequire.params.PurRequireParams;
import com.armitage.server.api.business.purrequire.params.PurchaseTypeParams;
import com.armitage.server.activity.util.ActivityUtil;
import com.armitage.server.activity.util.AuditMsgUtil;
import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.BaseModel;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.base.model.SimpleDataDisplay;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonBillEntryStatus;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.model.ScmAuditDetailHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.common.service.ScmAuditDetailHistoryBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvAccreditWhBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.service.ScmInvWareHouseBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurRequireDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditDetailParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurBillDrillResult;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrder2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyInfoBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.Employee2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.model.OrgStorage2;
import com.armitage.server.system.model.OrgUnitRelation;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgAdminBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Operation2;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.OperationBiz;
import com.armitage.server.user.service.UsrBiz;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;

@Service("scmPurRequireBiz")
public class ScmPurRequireBizImpl extends BaseBizImpl<ScmPurRequire2> implements ScmPurRequireBiz {
	
	private UsrBiz usrBiz;
	private OrgAdminBiz orgAdminBiz;
	private EmployeeBiz employeeBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmInvWareHouseBiz scmInvWareHouseBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private ScmInvAccreditWhBiz scmInvAccreditWhBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private CodeBiz codeBiz;
	private BillTypeBiz billTypeBiz;
	private ScmPurSupplyInfoBiz scmPurSupplyInfoBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private SysParamBiz sysParamBiz;
	private ScmPurchaseTypeBiz scmPurchaseTypeBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	private ScmPurchaseCanuseSetBiz scmPurchaseCanuseSetBiz;
    private OrgUnitBiz orgUnitBiz;
	private ScmAuditDetailHistoryBiz scmAuditDetailHistoryBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private OperationBiz operationBiz;
	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
	
	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}

	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setScmInvWareHouseBiz(ScmInvWareHouseBiz scmInvWareHouseBiz) {
		this.scmInvWareHouseBiz = scmInvWareHouseBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setScmInvAccreditWhBiz(ScmInvAccreditWhBiz scmInvAccreditWhBiz) {
		this.scmInvAccreditWhBiz = scmInvAccreditWhBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

    public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

    public void setScmPurSupplyInfoBiz(ScmPurSupplyInfoBiz scmPurSupplyInfoBiz) {
		this.scmPurSupplyInfoBiz = scmPurSupplyInfoBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}
	
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
	
	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmPurchaseTypeBiz(ScmPurchaseTypeBiz scmPurchaseTypeBiz) {
		this.scmPurchaseTypeBiz = scmPurchaseTypeBiz;
	}

	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}
	
	public void setScmPurchaseCanuseSetBiz(ScmPurchaseCanuseSetBiz scmPurchaseCanuseSetBiz) {
		this.scmPurchaseCanuseSetBiz = scmPurchaseCanuseSetBiz;
	}

	public void setOrgUnitBiz(OrgUnitBiz orgUnitBiz) {
		this.orgUnitBiz = orgUnitBiz;
	}

	public void setGson(Gson gson) {
		this.gson = gson;
	}

	public void setScmAuditDetailHistoryBiz(ScmAuditDetailHistoryBiz scmAuditDetailHistoryBiz) {
		this.scmAuditDetailHistoryBiz = scmAuditDetailHistoryBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setOperationBiz(OperationBiz operationBiz) {
		this.operationBiz = operationBiz;
	}

	/**
	 * 接口保存
	 */
	public void dataSyncSave(ScmPurRequire2 scmPurRequire2,Param param) throws AppException {
	    // beforeAdd
		int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		if(scmPurRequire2 != null){
			String repeatNo = checkRepeat(scmPurRequire2,param);
			if(StringUtils.isNotBlank(repeatNo)) {
				throw new AppException("iscm.server.ScmInvOtherIssueBillBizImpl.Repeat",new String[] {repeatNo});
			}
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN, scmPurRequire2.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequire2.getScmPurRequireEntryList();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					BigDecimal b = (scmPurRequireEntry.getQty()).multiply(scmPurRequireEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
					scmPurRequireEntry.setAmt(b);
                    amt = amt.add(scmPurRequireEntry.getAmt());
				}
			}
			scmPurRequire2.setTaxAmt(amt);
			scmPurRequire2.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			scmPurRequire2.setCurrencyNo(orgCompanyList.get(0).getBaseCurrency());
			scmPurRequire2.setExchangeRate(BigDecimal.ONE);
			scmPurRequire2.setCreator(param.getUsrCode());
			scmPurRequire2.setCreateDate(CalendarUtil.getDate(param));
			scmPurRequire2.setStatus("I");
		}
		
		
		int i =((ScmPurRequireDAO) dao).add(scmPurRequire2);
		
		
		if(scmPurRequire2 != null && scmPurRequire2.getId() > 0){
			scmPurRequire2.setTotalAmt(scmPurRequire2.getTaxAmt());
			String invOrgUnitNo = "";
			List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmPurRequire2.getOrgUnitNo(), false, null, param);
			if(orgStorageList==null || orgStorageList.isEmpty())
				throw new AppException("iscm.purchasemanage.controller.scmPurRequire2FormController.notadmintoinv");
			for(OrgStorage2 orgStorage:orgStorageList){
				if(orgStorage.isDefault()){
					invOrgUnitNo = orgStorage.getOrgUnitNo();
					break;
				}
			}
			if(StringUtils.isBlank(invOrgUnitNo))
				invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
			//新增请购明细
			List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequire2.getScmPurRequireEntryList();
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmPurRequireEntry2 scmPurRequire2Entry : scmPurRequireEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmPurRequire2Entry.getItemId());
				}
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmPurRequire2.getFinOrgUnitNo(), itemIds.toString(), param);
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.scmPurRequire2BizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				int lineId = 1;
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					if(!StringUtils.isBlank(scmPurRequireEntry.getRecStorageOrgUnitNo()))
						invOrgUnitNo = scmPurRequireEntry.getRecStorageOrgUnitNo();
					scmPurRequireEntry.setLineId(lineId);
					scmPurRequireEntry.setPrId(scmPurRequire2.getId());
					scmPurRequireEntry.setOrgUnitNo(scmPurRequire2.getOrgUnitNo());
					scmPurRequireEntry.setPurOrgUnitNo(scmPurRequire2.getPurOrgUnitNo());
					scmPurRequireEntry.setReqDate(scmPurRequire2.getReqDate());
					if(scmPurRequire2.isToWareHouse()){
						scmPurRequireEntry.setWareHouseId(scmPurRequire2.getReceiveWareHouseId());
						ScmInvAccreditWh scmInvAccreditWh = scmInvAccreditWhBiz.selectByWareHouseId(scmPurRequire2.getReceiveWareHouseId(),invOrgUnitNo, param);
						if(scmInvAccreditWh!=null){
							scmPurRequireEntry.setEntrusted(true);
							scmPurRequireEntry.setMsRecStorageOrgUnitNo(scmInvAccreditWh.getMorgUnitNo());
						}else{
							scmPurRequireEntry.setEntrusted(false);
						}
					}
					scmPurRequireEntry.setRecStorageOrgUnitNo(invOrgUnitNo);
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurRequireEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.scmPurRequire2BizImpl.error.itemnorexists");
					}
					scmPurRequireEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurRequireEntry.getItemId(), scmPurRequireEntry.getPurUnit(), param);
					scmPurRequireEntry.setBaseQty(scmPurRequireEntry.getQty().multiply(convRate));
					scmPurRequireEntryBiz.add(scmPurRequireEntry, param);
					lineId = lineId+1;
				}
			}
		}
		
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgAdmin2> orgAdminList = orgAdminBiz.findChild(param.getOrgUnitNo(), param);
		if (orgAdminList != null && !orgAdminList.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgAdmin2 orgAdmin : orgAdminList) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgAdmin.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." + ScmPurRequire2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." + ScmPurRequire2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." + ScmPurRequire2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." + ScmPurRequire2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		List<ScmPurRequire2> scmPurRequireList = list;
		if(scmPurRequireList != null && !scmPurRequireList.isEmpty()){
			for(ScmPurRequire2 scmPurRequire : scmPurRequireList){
				//视图增加待审人
				if(!("I,R".contains(scmPurRequire.getStatus()))) {
					ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurRequire.getId(), "PurRequire",param);
					StringBuffer usrName = new StringBuffer("");
					if (scmBillPendingUsr != null) {
						scmPurRequire.setPendingUsr(scmBillPendingUsr.getUsrCodes());
						String[] usrCodes = StringUtils.split(scmBillPendingUsr.getUsrCodes(), ",");
						for(String usrCode : usrCodes) {
							Usr usr = usrBiz.selectByCode(usrCode, param);
							if(usr != null) {
								if(StringUtils.isNotBlank(usrName.toString())) 
									usrName.append(",");
								usrName.append(usr.getName());
							}
						}
					}
					scmPurRequire.setPendingUsrName(usrName.toString());
				}
				setConvertMap(scmPurRequire,param);
			}
		}
	}
	
	@Override
	protected void afterSelect(ScmPurRequire2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
    protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
        if (list != null && list.size() > 0) {
            List<ScmPurRequire2> scmPurRequireList = list;
            for (ScmPurRequire2 scmPurRequire : scmPurRequireList) {
                setConvertMap(scmPurRequire,param);
            }
        }
    }

    private void setConvertMap(ScmPurRequire2 scmPurRequire,Param param){
		if (scmPurRequire != null){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if (StringUtils.isNotBlank(scmPurRequire.getOrgUnitNo())){
				//申请组织
				OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmPurRequire.getOrgUnitNo(), param);
				if (orgAdmin != null) {
					scmPurRequire.setReqOrgUnitName(orgAdmin.getOrgUnitName());
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_ORGUNITNO, orgAdmin);
				}
			}
			if (StringUtils.isNotBlank(scmPurRequire.getApplicants())){
				//申请人
				Employee employee = employeeBiz.selectByKey(scmPurRequire.getApplicants(), param);
				if (employee != null) {
					scmPurRequire.setApplicantsName(employee.getEmpName());
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_APPLICANTS, employee);
				}
			}
			if (StringUtils.isNotBlank(scmPurRequire.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmPurRequire.getCreator(), param);
				if (usr != null) {
					if(scmPurRequire.getDataDisplayMap()==null){
						scmPurRequire.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurRequire.setCreatorName(usr.getName());
					scmPurRequire.getDataDisplayMap().put(ScmPurRequire2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmPurRequire.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmPurRequire.getEditor(), param);
				if (usr != null) {
					if(scmPurRequire.getDataDisplayMap()==null){
						scmPurRequire.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurRequire.setEditorName(usr.getName());
					scmPurRequire.getDataDisplayMap().put(ScmPurRequire2.FN_EDITOR, SimpleDataDisplayUtil.convert(usr));
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmPurRequire.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmPurRequire.getChecker(), param);
				if (usr != null) {
					if(scmPurRequire.getDataDisplayMap()==null){
						scmPurRequire.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurRequire.setCheckerName(usr.getName());
					scmPurRequire.getDataDisplayMap().put(ScmPurRequire2.FN_CHECKER, SimpleDataDisplayUtil.convert(usr));
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_CHECKER, usr);
				}
			}
			if (StringUtils.isNotBlank(scmPurRequire.getPurOrgUnitNo())){
				//采购组织
				OrgPurchase2  orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(scmPurRequire.getPurOrgUnitNo(), param);
				if (orgPurchase != null) {
					scmPurRequire.setPurOrgUnitName(orgPurchase.getOrgUnitName());
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_PURORGUNITNO, orgPurchase);
				}
			}
			//仓库
			if (scmPurRequire.getReceiveWareHouseId()>0){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectDirect(scmPurRequire.getReceiveWareHouseId(), param);
				if(scmInvWareHouse!=null){
					scmPurRequire.setReceiveWareHouseName(scmInvWareHouse.getWhName());
					scmPurRequire.setReceiveWareHouseNo(scmInvWareHouse.getWhNo());
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_RECEIVEWAREHOUSEID,scmInvWareHouse);
				}
			}
			if(StringUtils.isNotBlank(scmPurRequire.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurRequire.getStatus());
				if(code!=null)
					scmPurRequire.setStatusName(code.getName());
			}
			if(StringUtils.isNotBlank(scmPurRequire.getBizType())){
				ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmPurRequire.getBizType(),param);
				if(scmPurchaseType!=null){
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_BIZTYPE,scmPurchaseType);
					scmPurRequire.setBizTypeName(scmPurchaseType.getName());
				}
			}
			//需求门店
			if (StringUtils.isNotBlank(scmPurRequire.getFinOrgUnitNo())) {
				OrgBaseUnit orgBaseUnit = orgUnitBiz.selectbyOrgNo(scmPurRequire.getFinOrgUnitNo(), param);
				 if (orgBaseUnit != null) {
					scmPurRequire.setConvertMap(ScmPurRequire2.FN_FINORGUNITNO, orgBaseUnit);
				}
			}
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire = (ScmPurRequire2) bean.getList().get(0);
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmPurRequire != null && scmPurRequire.getId() > 0){
			List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(scmPurRequire.getId(), param);
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					if (scmPurRequireEntry.getVendorId() > 0){
						//供应来源
						Scmsupplier2 scmsupplier = (Scmsupplier2) cacheMap.get(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class)+"_"+scmPurRequireEntry.getVendorId());
						if(scmsupplier==null){
							scmsupplier = scmsupplierBiz.selectDirect(scmPurRequireEntry.getVendorId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(Scmsupplier2.class)+"_"+scmPurRequireEntry.getVendorId(),scmsupplier);
						}
						if (scmsupplier != null) {
							scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_VENDORID, scmsupplier);
						}
					}
					if (scmPurRequireEntry.getBuyerId() > 0){
						//采购员
						ScmPurBuyer2 scmPurBuyer = (ScmPurBuyer2) cacheMap.get(ClassUtils.getFinalModelSimpleName(ScmPurBuyer2.class)+"_"+scmPurRequireEntry.getBuyerId());
						if(scmPurBuyer==null){
							scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurRequireEntry.getBuyerId(), param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(ScmPurBuyer2.class)+"_"+scmPurRequireEntry.getBuyerId(),scmPurBuyer);
						}
						if (scmPurBuyer != null) {
							scmPurRequireEntry.setConvertMap(ScmPurRequireEntry2.FN_BUYERID, scmPurBuyer);
						}
					}
				}
				bean.setList2(scmPurRequireEntryList);
			}
		}
	}
	
	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire2 = (ScmPurRequire2) bean.getList().get(0);
		int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		if(scmPurRequire2 != null){
			String repeatNo = checkRepeat(scmPurRequire2,param);
			if(StringUtils.isNotBlank(repeatNo)) {
				throw new AppException("iscm.server.ScmInvOtherIssueBillBizImpl.Repeat",new String[] {repeatNo});
			}
			String code = CodeAutoCalUtil.getBillCode("PurRequire", scmPurRequire2, param);
			scmPurRequire2.setPrNo(code);
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN, scmPurRequire2.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			List<ScmPurRequireEntry2> scmPurRequireEntryList = bean.getList2();
            BigDecimal amt= BigDecimal.ZERO;
            BigDecimal taxAmt= BigDecimal.ZERO;
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					BigDecimal b = (scmPurRequireEntry.getQty()).multiply(scmPurRequireEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
					scmPurRequireEntry.setAmt(b);
                    amt = amt.add(scmPurRequireEntry.getAmt());
				}
			}
			scmPurRequire2.setTaxAmt(amt);
			scmPurRequire2.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			scmPurRequire2.setCurrencyNo(orgCompanyList.get(0).getBaseCurrency());
			scmPurRequire2.setExchangeRate(BigDecimal.ONE);
			scmPurRequire2.setCreator(param.getUsrCode());
			scmPurRequire2.setCreateDate(CalendarUtil.getDate(param));
			scmPurRequire2.setStatus("I");
		}
	}

	private String checkRepeat(ScmPurRequire2 scmPurRequire, Param param) throws AppException{
		if(StringUtils.isNotBlank(scmPurRequire.getReqNo())) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("orgUnitNo", param.getOrgUnitNo());
			map.put("reqNo", scmPurRequire.getReqNo());
			List<ScmPurRequire2> scmPurRequireList = this.findAll(map, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty())
				return scmPurRequireList.get(0).getPrNo();
		}
		return "";
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire = (ScmPurRequire2) bean.getList().get(0);
		if(scmPurRequire != null && scmPurRequire.getId() > 0){
			scmPurRequire.setTotalAmt(scmPurRequire.getTaxAmt());
			String invOrgUnitNo = "";
			List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmPurRequire.getOrgUnitNo(), false, null, param);
			if(orgStorageList==null || orgStorageList.isEmpty())
				throw new AppException("iscm.purchasemanage.controller.ScmPurRequireFormController.notadmintoinv");
			for(OrgStorage2 orgStorage:orgStorageList){
				if(orgStorage.isDefault()){
					invOrgUnitNo = orgStorage.getOrgUnitNo();
					break;
				}
			}
			if(StringUtils.isBlank(invOrgUnitNo))
				invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
			//新增请购明细
			List<ScmPurRequireEntry2> scmPurRequireEntryList = bean.getList2();
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmPurRequireEntry.getItemId());
				}
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmPurRequire.getFinOrgUnitNo(), itemIds.toString(), param);
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				int lineId = 1;
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					if(!StringUtils.isBlank(scmPurRequireEntry.getRecStorageOrgUnitNo()))
						invOrgUnitNo = scmPurRequireEntry.getRecStorageOrgUnitNo();
					scmPurRequireEntry.setLineId(lineId);
					scmPurRequireEntry.setPrId(scmPurRequire.getId());
					scmPurRequireEntry.setOrgUnitNo(scmPurRequire.getOrgUnitNo());
					scmPurRequireEntry.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
					scmPurRequireEntry.setReqDate(scmPurRequire.getReqDate());
					if(scmPurRequire.isToWareHouse()){
						scmPurRequireEntry.setWareHouseId(scmPurRequire.getReceiveWareHouseId());
						ScmInvAccreditWh scmInvAccreditWh = scmInvAccreditWhBiz.selectByWareHouseId(scmPurRequire.getReceiveWareHouseId(),invOrgUnitNo, param);
						if(scmInvAccreditWh!=null){
							scmPurRequireEntry.setEntrusted(true);
							scmPurRequireEntry.setMsRecStorageOrgUnitNo(scmInvAccreditWh.getMorgUnitNo());
						}else{
							scmPurRequireEntry.setEntrusted(false);
						}
					}
					scmPurRequireEntry.setRecStorageOrgUnitNo(invOrgUnitNo);
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurRequireEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurRequireEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurRequireEntry.getItemId(), scmPurRequireEntry.getPurUnit(), param);
					scmPurRequireEntry.setBaseQty(scmPurRequireEntry.getQty().multiply(convRate));
					scmPurRequireEntryBiz.add(scmPurRequireEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire = (ScmPurRequire2) bean.getList().get(0);
		if(scmPurRequire != null && scmPurRequire.getId() > 0){
			String invOrgUnitNo = "";
			List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmPurRequire.getOrgUnitNo(), false, null, param);
			if(orgStorageList==null || orgStorageList.isEmpty())
				throw new AppException("iscm.purchasemanage.controller.ScmPurRequireFormController.notadmintoinv");
			for(OrgStorage2 orgStorage:orgStorageList){
				if(orgStorage.isDefault()){
					invOrgUnitNo = orgStorage.getOrgUnitNo();
					break;
				}
			}
			if(StringUtils.isBlank(invOrgUnitNo))
				invOrgUnitNo = orgStorageList.get(0).getOrgUnitNo();
			//更新请购明细
			List<ScmPurRequireEntry2> scmPurRequireEntryList = bean.getList2();
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				StringBuffer itemIds = new StringBuffer();
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					if(StringUtils.isNotBlank(itemIds.toString()))
						itemIds.append(",");
					itemIds.append(scmPurRequireEntry.getItemId());
				}
				String notRawMaterails = ScmMaterialUtil.getNotRawMaterail(scmPurRequire.getFinOrgUnitNo(), itemIds.toString(), param);
				if(StringUtils.isNotBlank(notRawMaterails))
					throw new AppException(Message.getMessage(param.getLang(), "iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.rawMaterial", new String[]{notRawMaterails.toString()}));
				int lineId = 1;
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					if (StringUtils.equals("I", scmPurRequire.getStatus())) {
						scmPurRequireEntry.setLineId(lineId);
					}
					scmPurRequireEntry.setPrId(scmPurRequire.getId());
					scmPurRequireEntry.setOrgUnitNo(scmPurRequire.getOrgUnitNo());
					scmPurRequireEntry.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
					scmPurRequireEntry.setReqDate(scmPurRequire.getReqDate());
					if(scmPurRequire.isToWareHouse()){
						scmPurRequireEntry.setWareHouseId(scmPurRequire.getReceiveWareHouseId());
						ScmInvAccreditWh scmInvAccreditWh = scmInvAccreditWhBiz.selectByWareHouseId(scmPurRequire.getReceiveWareHouseId(),invOrgUnitNo, param);
						if(scmInvAccreditWh!=null){
							scmPurRequireEntry.setEntrusted(true);
							scmPurRequireEntry.setMsRecStorageOrgUnitNo(scmInvAccreditWh.getMorgUnitNo());
						}else{
							scmPurRequireEntry.setEntrusted(false);
						}
					}
					scmPurRequireEntry.setRecStorageOrgUnitNo(invOrgUnitNo);
					ScmMaterial2 scmMaterial = scmMaterialBiz.selectDirect(scmPurRequireEntry.getItemId(), param);
					if(scmMaterial==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
					}
					scmPurRequireEntry.setBaseUnit(scmMaterial.getBaseUnitId());
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurRequireEntry.getItemId(), scmPurRequireEntry.getPurUnit(), param);
					scmPurRequireEntry.setBaseQty(scmPurRequireEntry.getQty().multiply(convRate));
					if(scmPurRequireEntry.getId() > 0){
						scmPurRequireEntryBiz.update(scmPurRequireEntry, param);
					}else{
						scmPurRequireEntry.setPrId(scmPurRequire.getId());
						scmPurRequireEntryBiz.add(scmPurRequireEntry, param);
					}
					lineId = lineId + 1;
					
					if (scmPurRequireEntry.getQty().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmPurRequireEntry.getPrice().compareTo(BigDecimal.ZERO) > 0 
                    		&& scmPurRequireEntry.getAmt().compareTo(BigDecimal.ZERO) == 0) {
						int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
						BigDecimal b = (scmPurRequireEntry.getQty()).multiply(scmPurRequireEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
	                    scmPurRequireEntry.setAmt(b);
	                    BigDecimal c = (scmPurRequireEntry.getQty()).multiply(scmPurRequireEntry.getConvrate()).setScale(6, RoundingMode.HALF_UP);
	                    scmPurRequireEntry.setBaseQty(c);
					}
				}
				scmPurRequireEntryBiz.update(scmPurRequire, scmPurRequireEntryList, ScmPurRequireEntry2.FN_PRID, param);
			}
		}
		
		
	}
	
	@Override
	protected void afterDelete(ScmPurRequire2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除请购明细
			scmPurRequireEntryBiz.deleteByPrId(entity.getId(), param);
			//删除附件
			scmBaseAttachmentBiz.update(entity, null,ScmBaseAttachment.FN_BILLID,param);
		}
	}
	
	private ScmPurRequire2 updateStatus(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		if(scmPurRequire != null){
			ScmPurRequire2 scmPurRequire2 = this.update(scmPurRequire, param);
			if(scmPurRequire2 != null){
				scmPurRequireEntryBiz.updateRowStatusByPrId(scmPurRequire2.getId(), scmPurRequire2.getStatus(), scmPurRequire2.getChecker(), scmPurRequire2.getCheckDate(), param);
				return scmPurRequire2;
			}
		}
		return null;
	}

	
    /*
     *删除请购单 
     */
	@Override
	public void doDelPurRequire(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmPurRequire2.FN_PRNO, scmPurRequire.getPrNo());
		map.put(ScmPurRequire2.FN_CONTROLUNITNO, param.getControlUnitNo());
		List<ScmPurRequire2> scmPurRequireList = this.findAll(map, param);
		if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
			try {
				this.delete(scmPurRequireList, param);
			} catch (Exception e) {
				throw e;
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
	}

	/*
     *提交请购单 
     */
	@Override
	public ScmPurRequire2 doSubmitPurRequire(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 require = null;
		if(scmPurRequire.getId()>0){
			require = this.selectDirect(scmPurRequire.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurRequire2.FN_PRNO,
					new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,
							scmPurRequire.getPrNo()));
			
			List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				require=scmPurRequireList.get(0);
			}
		}
		if(require!=null){
			this.setConvertMap(require, param);
			String requireForceValidate = sysParamBiz.getValue(require.getPurOrgUnitNo(), "SCM_RequireForceValidate", "N", param);
			if (StringUtils.equals("Y", requireForceValidate)) {
				List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(require.getId(), param);
				String checkDate = scmPurchaseCanuseSetBiz.checkDate(require, scmPurRequireEntryList, param);
				if (StringUtils.isNotBlank(checkDate)) {
					throw new AppException(checkDate);
				}
			}
			if(!StringUtils.equals(require.getStatus(),"I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(require.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getFinOrgUnitNo(), "PurRequire", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				if(needAudit){
					String processInstanceId = startWorkFlow(billType,require, param);
					require.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							require.setChecker(param.getUsrCode());
							require.setSubmitter(param.getUsrCode());
						}
						require.setCheckDate(CalendarUtil.getDate(param));
						require.setSubmitDate(CalendarUtil.getDate(param));
						require.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							require.setSubmitter(param.getUsrCode());
						}
						require.setSubmitDate(CalendarUtil.getDate(param));
						require.setStatus("D");
						sendAuditMsg(require,billType.getBillCode(),param);
					}
				}else{
					if(param.getUsrCode()!=null ){
						require.setChecker(param.getUsrCode());
						require.setSubmitter(param.getUsrCode());
					}
					require.setCheckDate(CalendarUtil.getDate(param));
					require.setSubmitDate(CalendarUtil.getDate(param));
					require.setStatus("A");
				}
				try {
					this.updateStatus(require, param);
				} catch (Exception e) {
					throw e;
				}
				if(StringUtils.contains("A,B", require.getStatus())) {
					//通过或部分通过时将自动生成订货的物资生成订货单
					this.autoGenerateOrder(require,param);
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}

	private String startWorkFlow(BillType2 billType,ScmPurRequire2 require, Param param) {
		//发起流程
		BigDecimal totalAmt = BigDecimal.ZERO;
		List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(require.getId(), param);
		if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
			for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
				totalAmt = totalAmt.add(scmPurRequireEntry.getAmt());
			}
		}
		require.setTotalAmt(totalAmt);
		CommonBean bean = new CommonBean();
		List<ScmPurRequire2> scmPurRequireList = new ArrayList<>();
		scmPurRequireList.add(require);
		bean.setList(scmPurRequireList);
		bean.setList2(scmPurRequireEntryList);
		String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
		return processInstanceId;
	}
	
	private void updateProcessVariable(BillType2 billType,ScmPurRequire2 require,List<CommonAuditDetailParams> detailList,Param param){
		if(StringUtils.isBlank(require.getWorkFlowNo())){
			return;
		}
		BigDecimal totalAmt = BigDecimal.ZERO;
		List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(require.getId(), param);
		if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
			for(int i=scmPurRequireEntryList.size()-1;i>=0;i--){
				if(StringUtils.equalsIgnoreCase("N", scmPurRequireEntryList.get(i).getRowStatus())){
					scmPurRequireEntryList.remove(i);
					continue;
				}
				if(detailList != null && !detailList.isEmpty()){
					boolean removeFlag = false;
					for (CommonAuditDetailParams commonAuditDetailParams : detailList) {
						String rowOpinion = commonAuditDetailParams.getRowOpinion();
						int lineId = commonAuditDetailParams.getLineId();
						if (lineId == scmPurRequireEntryList.get(i).getLineId()) {
							if(StringUtils.equals("refuse",rowOpinion) || StringUtils.equals("N",rowOpinion)){
								scmPurRequireEntryList.remove(i);
								removeFlag = true;
							}
							break;
						}
					}
					if(removeFlag){
						continue;
					}
				}
				totalAmt = totalAmt.add(scmPurRequireEntryList.get(i).getAmt());
			}
		}
		require.setTotalAmt(totalAmt);
		CommonBean bean = new CommonBean();
		List<ScmPurRequire2> scmPurRequireList = new ArrayList<>();
		scmPurRequireList.add(require);
		bean.setList(scmPurRequireList);
		bean.setList2(scmPurRequireEntryList);
		ActivityUtil.updateProcessVariable(bean, billType.getId(), require.getWorkFlowNo(), param);
	}
	
	private void sendAuditMsg(ScmPurRequire2 require,String billCode,Param param) {
		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(require.getWorkFlowNo(),param);
		if(StringUtils.isNotBlank(usrList)) {
			scmBillPendingBiz.addPendingBill(usrList, require, param);
			PurRequireParams purRequireParams = new PurRequireParams();
			purRequireParams.setPrNo(require.getPrNo());
			AuditMsgUtil.sendAuditMsg(billCode,require,purRequireParams, usrList, param);
		}
	}
	
	private void sendAuditMsg(ScmPurRequire2 require,String activeType,String billCode,Param param) {
//		String usrList= ActivityUtil.findAssigneeByProcessInstanceId(require.getWorkFlowNo(),param);
//		if(StringUtils.isNotBlank(usrList)) {
			PurRequireParams purRequireParams = new PurRequireParams();
			purRequireParams.setPrNo(require.getPrNo());
			AuditMsgUtil.sendAuditMsg(billCode,activeType,require,purRequireParams, require.getCreator(), param);
//		}
	}
	/*
     *取消提交请购单 
     */
	@Override
	public ScmPurRequire2 doUnSubmitPurRequire(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 require = null;
		if(scmPurRequire.getId()>0){
			require = this.selectDirect(scmPurRequire.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurRequire2.FN_PRNO,
					new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,
							scmPurRequire.getPrNo()));
			
			List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
			
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				require=scmPurRequireList.get(0);
			}
		}
		if(require!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getFinOrgUnitNo(), "PurRequire", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(require.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(require.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);
			
			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(require.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(require.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(require.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(require.getStatus().equals("A") || require.getStatus().equals("D")){
				require.setChecker(null);
				require.setSubmitter(null);
				require.setCheckDate(null);
				require.setSubmitDate(null);
				require.setStatus("I");
				try{
					this.updateStatus(require, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),require, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}

	@Override
	public List<ScmPurRequire2> queryPurRequireList(ScmPurRequireAdvQuery scmPurRequireAdvQuery, 
			int pageNum, Param param) throws AppException{
		Page page=new Page();
		page.setModelClass(ScmPurRequire2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getReceiveWareHouseNo())){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("whNo", scmPurRequireAdvQuery.getReceiveWareHouseNo());
			map.put("controlUnitNo", param.getControlUnitNo());
			List<ScmInvWareHouse> scmInvWareHouseList = scmInvWareHouseBiz.findAll(map, param);
			if(scmInvWareHouseList!=null && !scmInvWareHouseList.isEmpty())
				scmPurRequireAdvQuery.setReceiveWareHouseId(scmInvWareHouseList.get(0).getId());
		}
		page.setModel(scmPurRequireAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		return this.findPage(page, param);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurRequireAdvQuery) {
				ScmPurRequireAdvQuery scmPurRequireAdvQuery = (ScmPurRequireAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getPrNo())){
					page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_PRNO, QueryParam.QUERY_LIKE,"%"+scmPurRequireAdvQuery.getPrNo()+"%"));
				}
				if(scmPurRequireAdvQuery.getReqDateFrom()!=null){
					if(scmPurRequireAdvQuery.getReqDateTo()!=null) {
						page.getParam().put(ScmPurRequire2.FN_REQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_REQDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurRequireAdvQuery.getReqDateFrom()),FormatUtils.fmtDate(scmPurRequireAdvQuery.getReqDateTo())));
					}else {
						page.getParam().put(ScmPurRequire2.FN_REQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_REQDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurRequireAdvQuery.getReqDateFrom())));
					}
				}else if(scmPurRequireAdvQuery.getReqDateTo()!=null) {
					page.getParam().put(ScmPurRequire2.FN_REQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_REQDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurRequireAdvQuery.getReqDateTo())));
				}
				if(scmPurRequireAdvQuery.getCreateDateFrom()!=null){
					if(scmPurRequireAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurRequire2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurRequireAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurRequireAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurRequire2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurRequireAdvQuery.getCreateDateFrom())));
					}
				}else if(scmPurRequireAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurRequire2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurRequireAdvQuery.getCreateDateTo(),1))));
				}
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getReqOrgUnitNo())){
					page.getParam().put(ScmPurRequire2.FN_ORGUNITNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_ORGUNITNO, QueryParam.QUERY_EQ,scmPurRequireAdvQuery.getReqOrgUnitNo()));
				}
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getApplicants())){
					page.getParam().put(ScmPurRequire2.FN_APPLICANTS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_APPLICANTS, QueryParam.QUERY_EQ,scmPurRequireAdvQuery.getApplicants()));
				}
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getCreator())){
					page.getParam().put(ScmPurRequire2.FN_CREATOR,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_CREATOR, QueryParam.QUERY_EQ,scmPurRequireAdvQuery.getCreator()));
				}
				if(scmPurRequireAdvQuery.getReceiveWareHouseId()>0){
					page.getParam().put(ScmPurRequire2.FN_RECEIVEWAREHOUSEID,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_RECEIVEWAREHOUSEID, QueryParam.QUERY_EQ,String.valueOf(scmPurRequireAdvQuery.getReceiveWareHouseId())));
				}
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getBizType())){
					page.getParam().put(ScmPurRequire2.FN_BIZTYPE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_BIZTYPE, QueryParam.QUERY_EQ,scmPurRequireAdvQuery.getBizType()));
				}
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmPurRequireAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmPurRequire2.FN_STATUS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +	ScmPurRequire2.FN_STATUS, QueryParam.QUERY_IN,statusBuffer.toString()));
					}
				}
				if(StringUtils.isNotBlank(scmPurRequireAdvQuery.getMixParam())) {
					page.getParamOr().put(ScmPurRequire2.FN_PRNO,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_PRNO, QueryParam.QUERY_LIKE,"%"+scmPurRequireAdvQuery.getMixParam()+"%"));
					page.getParamOr().put(ScmPurRequire2.FN_REMARKS,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +ScmPurRequire2.FN_REMARKS, QueryParam.QUERY_LIKE,"%"+scmPurRequireAdvQuery.getMixParam()+"%"));
				}
				page.getParam().put(ScmPurRequire2.FN_TEMPLETE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurRequire2.class) + "." +	ScmPurRequire2.FN_TEMPLETE, QueryParam.QUERY_EQ,scmPurRequireAdvQuery.isTemplete()?"1":"0"));
			}
		}
	}
	
	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurRequireAdvQuery) {
				ScmPurRequireAdvQuery scmPurRequireAdvQuery = (ScmPurRequireAdvQuery) page.getModel();
				if(scmPurRequireAdvQuery.isFromInterface())
					map.put("sortByDate", "Y");
			}
		}
		return map;
	}

	@Override
	public ScmPurRequire2 queryPurRequire(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurRequire2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,scmPurRequire.getPrNo()));
		
		List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
		ScmPurRequire2 scmPurRequire2 = new ScmPurRequire2();
		if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
			scmPurRequire2 = scmPurRequireList.get(0);
			scmPurRequire2.setScmPurRequireEntryList(scmPurRequireEntryBiz.selectByPrId(scmPurRequire2.getId(), param));
			if(StringUtils.contains("D,P,I", scmPurRequire2.getStatus()) && scmPurRequire2.getScmPurRequireEntryList()!=null && !scmPurRequire2.getScmPurRequireEntryList().isEmpty()) {
				Usr usr = usrBiz.selectByCode(param.getUsrCode(), param);
				if(usr!=null) {
					List<Long> systemList = new ArrayList();
					systemList.add(70L);   // 集团应收   
					systemList.add(170L);  // 供应链
					systemList.add(270L);  // 应付系统
	                param.setSystemIdList(systemList);
					Operation2 operation = operationBiz.findUsrOperationFromRole(usr, 619423192l, param);
					if(operation!=null) {
						String editPrice = sysParamBiz.getValue(scmPurRequire2.getPurOrgUnitNo(), "SCM_EditPrice", "Y", param);
						String editVendor = sysParamBiz.getValue(scmPurRequire2.getPurOrgUnitNo(), "SCM_EditReqVendor", "N", param);
						for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequire2.getScmPurRequireEntryList()) {
							StringBuffer editColumn = new StringBuffer("Q");
							if (StringUtils.equals(editPrice, "Y")) {
		                        if (scmPurRequireEntry.getPriceBillId() <= 0 || (scmPurRequireEntry.getPriceBillId() > 0 && "1".equals(scmPurRequireEntry.getRefPriceStatus()))) {
		                            //报价单生效也可改单价
		                        	editColumn.append(",").append("P");
		                        }
			                }
							if (StringUtils.equals(editVendor, "Y")) {
								if (!StringUtils.contains("5,3,6,7", scmPurRequireEntry.getRefPriceStatus())) {
									editColumn.append(",").append("V");
								}
							}
							scmPurRequireEntry.setEditColumn(editColumn.toString());
						}
						
					}
				}
			}
			if (scmPurRequire2.getScmPurRequireEntryList() != null && !scmPurRequire2.getScmPurRequireEntryList().isEmpty()) {
				for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequire2.getScmPurRequireEntryList()) {
					setLineEditType(scmPurRequireEntry2, param);
				}
			}
			//待审接口增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurRequire2.getId(), "PurRequire",param);
			if (scmBillPendingUsr != null) {
				scmPurRequire2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurRequire2;
	}

	/**
	 * 设置行供应商Or价格是否可修改
	 * @param scmPurRequireEntry2
	 * @param param
	 * @throws AppException
	 */
	public void setLineEditType(ScmPurRequireEntry2 scmPurRequireEntry2,Param param) throws AppException {
		String editPrice = sysParamBiz.getValue(scmPurRequireEntry2.getPurOrgUnitNo(), "SCM_EditPrice", "Y", param);
		String getPriceWay = sysParamBiz.getValue(scmPurRequireEntry2.getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
		if (StringUtils.equals(editPrice, "Y")) {
			/*
			 * PriceEditType---1:无价格来源或价格来源为报价单，价格可修改
			 * PriceEditType---0：价格来源为定价单（临时定价And日常定价），价格不可修改
			 */
			if (scmPurRequireEntry2.getPriceBillId()<=0 || (scmPurRequireEntry2.getPriceBillId() > 0 && "1".equals(scmPurRequireEntry2.getRefPriceStatus()))) {
				scmPurRequireEntry2.setPriceEditType("1");
			}else {
				scmPurRequireEntry2.setPriceEditType("0");
			}
		}else {
			scmPurRequireEntry2.setPriceEditType("0");
		}
		if (StringUtils.equals(getPriceWay, "Y")) {
			scmPurRequireEntry2.setVendorEditType("1");
		}else {
			scmPurRequireEntry2.setVendorEditType("0");
		}
	}
	
	@Override
	public ScmPurRequire2 doAddPurRequire(PurRequireAddParams purRequireAddParams, Param param) throws AppException {
		String editVendor = sysParamBiz.getValue(purRequireAddParams.getPurOrgUnitNo(), "SCM_EditReqVendor", "N", param);
		String editPrice = sysParamBiz.getValue(purRequireAddParams.getPurOrgUnitNo(), "SCM_EditPrice", "Y", param);
		CommonBean bean = new CommonBean();
		List<ScmPurRequire2> scmPurRequireList = new ArrayList<>();
		ScmPurRequire2 scmPurRequire=new ScmPurRequire2();
		try {
			BeanUtils.copyProperties(purRequireAddParams, scmPurRequire);
			scmPurRequire.setOrgUnitNo(purRequireAddParams.getReqOrgUnitNo());
			scmPurRequire.setCreateDate(CalendarUtil.getDate(param));
			scmPurRequire.setCreator(param.getUsrCode());
			scmPurRequire.setApplyDate(CalendarUtil.getDate(param));
			scmPurRequire.setReqDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurRequire.getReqDate())));
			if(scmPurRequire.getApplyDate() != null){
				scmPurRequire.setApplyDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurRequire.getApplyDate())));
			}
			//获取默认的收货库存组织
			List<OrgUnitRelation> orgUnitRelationList = orgUnitRelationBiz.findByType(OrgUnitRelationType.ADMINTOINV, purRequireAddParams.getReqOrgUnitNo(), param);
			if(orgUnitRelationList==null || orgUnitRelationList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notinv");
			}
			String orgStorageNo="";
			for(OrgUnitRelation orgUnitRelation:orgUnitRelationList){
				if(orgUnitRelation.isIsDefault()){
					orgStorageNo=orgUnitRelation.getToOrgUnitNo();
				}
			}
			if(StringUtils.isBlank(orgStorageNo)){
				orgStorageNo=orgUnitRelationList.get(0).getToOrgUnitNo();
			}
			if(purRequireAddParams.isToWareHouse() && StringUtils.isNotBlank(purRequireAddParams.getReceiveWareHouseNo())){
				ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(orgStorageNo, purRequireAddParams.getReceiveWareHouseNo(), param);
				if(scmInvWareHouse==null){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.whnoerror");
				}
				scmPurRequire.setReceiveWareHouseId(scmInvWareHouse.getId());
			}
			scmPurRequire.setBizType(purRequireAddParams.getBizType());
			scmPurRequire.setSubscribeReason(purRequireAddParams.getSubscribeReason());
			scmPurRequire.setPurRequireTheme(purRequireAddParams.getPurRequireTheme());
			//申购理由及申购主题
			String subscribeReason = sysParamBiz.getValue(purRequireAddParams.getPurOrgUnitNo(), "SCM_SubscribeReason", "", param);
			if (StringUtils.isNotBlank(subscribeReason) && StringUtils.isNotBlank(scmPurRequire.getBizType())) {
	            String[] subString = subscribeReason.split(",");
	            String[] subString1 = subscribeReason.split("，");
	            if (subString1.length>subString.length) {
	                subString=subString1;
	            }
	            for (String string : subString) {
	                if (StringUtils.isNotBlank(string) && string.equals(scmPurRequire.getBizType())) {
	                	ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmPurRequire.getBizType(),param);
	                    if (scmPurchaseType != null && StringUtils.isBlank(scmPurRequire.getSubscribeReason())) {
	                    	throw new AppException(Message.getMessage(param.getLang(), "iscm.ScmPurRequire.save.bizTypeIsNull", new String[]{scmPurchaseType.getName()}));
	                    }
	                    if (scmPurchaseType != null && StringUtils.isBlank(scmPurRequire.getPurRequireTheme())) {
	                    	throw new AppException(Message.getMessage(param.getLang(), "iscm.ScmPurRequire.save.purRequireThemeIsNull", new String[]{scmPurchaseType.getName()}));
	                    }
	                }
	            }
	        }
			scmPurRequireList.add(scmPurRequire);
			bean.setList(scmPurRequireList);
			List<PurRequireDetailParams> detailList = purRequireAddParams.getDetailList();
			String priceType = sysParamBiz.getValue(scmPurRequire.getPurOrgUnitNo(), "SCM_SelectType", "1", param);
	        Date priceDate = scmPurRequire.getApplyDate();
	    	if(StringUtils.equals("2", priceType))
	    		priceDate = scmPurRequire.getReqDate();
			if(detailList!=null && !detailList.isEmpty()){
				StringBuffer itemNos = new StringBuffer("");
				for(PurRequireDetailParams detailParams:detailList){
					if(StringUtils.isBlank(detailParams.getItemNo())){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
					}
					if(StringUtils.isNotBlank(itemNos.toString()))
						itemNos.append(",");
					itemNos.append("'").append(detailParams.getItemNo()).append("'");
				}
				Page page = new Page();
				page.setModelClass(ScmMaterial2.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
				ArrayList argList = new ArrayList();
		        argList.add("orgUnitNo="+purRequireAddParams.getPurOrgUnitNo());
		        argList.add("controlUnitNo=" + param.getControlUnitNo());
		        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
				List<ScmPurRequireEntry2> scmPurRequireEntryList = new ArrayList<>();
				//待取供应商
		        List<OrgStorage2> invOrglist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmPurRequire.getOrgUnitNo(), false, null, param);
		        String invOrgUnitNo="";
		        if(invOrglist!=null && !invOrglist.isEmpty()){
		            boolean exists = false;
		            for(OrgStorage2 orgStorage:invOrglist){
		                if(orgStorage.isDefault()){
		                    if(!StringUtils.equals(invOrgUnitNo, orgStorage.getOrgUnitNo()))
		                        invOrgUnitNo=orgStorage.getOrgUnitNo();
		                    exists = true;
		                    break;
		                }
		            }
		            if (!exists) {
		                if(!StringUtils.equals(invOrgUnitNo, invOrglist.get(0).getOrgUnitNo()))
		                    invOrgUnitNo = invOrglist.get(0).getOrgUnitNo();
		            }
		        }
				for(PurRequireDetailParams detailParams:detailList){
					if (detailParams.getRecentPrice() == null) {
						detailParams.setRecentPrice(BigDecimal.ZERO);
					}
					if (detailParams.getStockQty()==null) {
						detailParams.setStockQty(BigDecimal.ZERO);
					}
					ScmPurRequireEntry2 scmPurRequireEntry = new ScmPurRequireEntry2(true);
					BeanUtils.copyProperties(detailParams, scmPurRequireEntry);
					if (StringUtils.isNotBlank(detailParams.getVendorCode())) {
						//用户选择的供应商
						Scmsupplier2 scmsupplier2 = scmsupplierBiz.selectByCode(detailParams.getVendorCode(), param);
						if (scmsupplier2 != null) {
							scmPurRequireEntry.setVendorId(scmsupplier2.getId());
						}
					}
					for(ScmMaterial2 scmMaterial:scmMaterialList){
						if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
							scmPurRequireEntry.setItemId(scmMaterial.getId());
							scmPurRequireEntry.setPurUnit(scmMaterial.getPurUnitId());
							scmPurRequireEntry.setPieUnit(scmMaterial.getPieUnitId());
							scmPurRequireEntry.setBaseUnit(scmMaterial.getBaseUnitId());
							scmPurRequireEntry.setBuyerId(scmMaterial.getBuyerId());
							scmPurRequireEntry.setSupplyCycle(scmMaterial.getPurSupplyCycle());
							scmPurRequireEntry.setClassCode(scmMaterial.getClassCode());
							scmPurRequireEntry.setItemNo(scmMaterial.getItemNo());
							scmPurRequireEntry.setItemName(scmMaterial.getItemName());
							break;
						}
					}
					if(scmPurRequireEntry.getPieQty()==null)
						scmPurRequireEntry.setPieQty(BigDecimal.ZERO);
					scmPurRequireEntry.setRowStatus("I");
					BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurRequireEntry.getItemId(), scmPurRequireEntry.getPurUnit(), param);
					scmPurRequireEntry.setBaseQty(scmPurRequireEntry.getQty().multiply(convRate));
					scmPurRequireEntry.setRecStorageOrgUnitNo(orgStorageNo);
					//待取供应商
					boolean needSetDirectPurchase = false;
					long vendorId=0;
					ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(
							purRequireAddParams.getPurOrgUnitNo(), invOrgUnitNo, scmPurRequireEntry.getItemId(),
							purRequireAddParams.getApplyDate(), param);
					if(scmPurSupplyInfo!=null) {
			        	if(scmPurRequireEntry.getVendorId()<=0 || StringUtils.equals("N", editVendor)){
				        	vendorId = scmPurSupplyInfo.getVendorId();
				        	scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
			        	}else {
			        		vendorId = scmPurRequireEntry.getVendorId();
						}
			        }else {
			        	if(StringUtils.equals("N", editVendor))
			        		scmPurRequireEntry.setVendorId(0);
						vendorId=scmPurRequireEntry.getVendorId();
						needSetDirectPurchase = true;
					}
			        scmPurRequireEntry.setVendorId(vendorId);
			        String priceBillId = "";
					ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(purRequireAddParams.getPurOrgUnitNo(),vendorId, String.valueOf(scmPurRequireEntry.getItemId()), scmPurRequireEntry.getPurUnit(),priceDate,orgStorageNo, priceBillId, param);
					if(scmMaterialPrice!=null && !StringUtils.equals("0", scmMaterialPrice.getRefPriceStatus())){
						if (StringUtils.equals("Y", editPrice) && StringUtils.equals("1", scmMaterialPrice.getRefPriceStatus()) && scmMaterialPrice.getPriceBillId()==scmPurRequireEntry.getPriceBillId()) {//TODO价格来源于报价时不进行重新取价，会将手工修改的覆盖
							scmMaterialPrice.setTaxPrice(scmPurRequireEntry.getPrice());
						}
						scmPurRequireEntry.setPrice(scmMaterialPrice.getTaxPrice());
						scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
						scmPurRequireEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
						scmPurRequireEntry.setPriceBillNo(scmMaterialPrice.getPriceBillNo());
						scmPurRequireEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
						if(scmMaterialPrice.getVendorId()>0){
							//定价且绑定供应商
							needSetDirectPurchase = true;
							scmPurRequireEntry.setVendorId(scmMaterialPrice.getVendorId());
						}
					}
					if(needSetDirectPurchase) {
						scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItemAndVendor(
								purRequireAddParams.getPurOrgUnitNo(), invOrgUnitNo,scmPurRequireEntry.getVendorId(), scmPurRequireEntry.getItemId(),
								purRequireAddParams.getApplyDate(), param);
						if(scmPurSupplyInfo==null) {
							scmPurRequireEntry.setDirectPurchase(false);
						}else {
							scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
						}
					}
					if(scmPurRequireEntry.getAmt() == null){
						scmPurRequireEntry.setAmt(BigDecimal.ZERO);
						if(scmPurRequireEntry.getQty() != null && scmPurRequireEntry.getPrice() != null){
							scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
						}
					}
					scmPurRequireEntryList.add(scmPurRequireEntry);
				}
				bean.setList2(scmPurRequireEntryList);
			}
			this.add(bean, param);
		} catch (AppException e) {
			throw e;
		}
		
		return scmPurRequire;
	}

	@Override
	public void doEditPurRequire(PurRequireEditParams purRequireEditParams, Param param) throws AppException {
		if(purRequireEditParams!=null){
			String editVendor = sysParamBiz.getValue(purRequireEditParams.getPurOrgUnitNo(), "SCM_EditReqVendor", "N", param);
			String editPrice = sysParamBiz.getValue(purRequireEditParams.getPurOrgUnitNo(), "SCM_EditPrice", "Y", param);
			CommonBean bean = new CommonBean();
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,purRequireEditParams.getPrNo()));
			ScmPurRequire2 scmPurRequire2 = this.selectByPrNo(purRequireEditParams.getPrNo(), param);
			List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
			if (scmPurRequire2 != null && StringUtils.contains("P,D", scmPurRequire2.getStatus())) {//审批时修改
				if (scmPurRequireList == null || scmPurRequireList.isEmpty()) {
					scmPurRequireList = new ArrayList<>();
					scmPurRequireList.add(scmPurRequire2);
				}
			}
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				//更新主表数据
				ScmPurRequire2 scmPurRequire = scmPurRequireList.get(0);
				BeanUtils.copyProperties(purRequireEditParams, scmPurRequire);
				if(scmPurRequire.getApplyDate() != null){
					scmPurRequire.setApplyDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurRequire.getApplyDate())));
				}
				if(scmPurRequire.getReqDate() != null){
					scmPurRequire.setReqDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurRequire.getReqDate())));
				}
				scmPurRequire.setOrgUnitNo(purRequireEditParams.getReqOrgUnitNo());
				scmPurRequire.setEditDate(CalendarUtil.getDate(param));
				scmPurRequire.setEditor(param.getUsrCode());
				//获取默认的收货库存组织
				List<OrgUnitRelation> orgUnitRelationList = orgUnitRelationBiz.findByType(OrgUnitRelationType.ADMINTOINV, purRequireEditParams.getReqOrgUnitNo(), param);
				if(orgUnitRelationList==null || orgUnitRelationList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notinv");
				}
				String orgStorageNo="";
				for(OrgUnitRelation orgUnitRelation:orgUnitRelationList){
					if(orgUnitRelation.isIsDefault()){
						orgStorageNo=orgUnitRelation.getToOrgUnitNo();
					}
				}
				if(StringUtils.isBlank(orgStorageNo)){
					orgStorageNo=orgUnitRelationList.get(0).getToOrgUnitNo();
				}
				if(purRequireEditParams.isToWareHouse() && StringUtils.isNotBlank(purRequireEditParams.getReceiveWareHouseNo())){
					ScmInvWareHouse scmInvWareHouse = scmInvWareHouseBiz.selectByWhNo(orgStorageNo, purRequireEditParams.getReceiveWareHouseNo(), param);
					if(scmInvWareHouse==null){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.whnoerror");
					}
					scmPurRequire.setReceiveWareHouseId(scmInvWareHouse.getId());
				}
				scmPurRequire.setBizType(purRequireEditParams.getBizType());
				scmPurRequire.setSubscribeReason(purRequireEditParams.getSubscribeReason());
				scmPurRequire.setPurRequireTheme(purRequireEditParams.getPurRequireTheme());
				//申购理由及申购主题
				String subscribeReason = sysParamBiz.getValue(purRequireEditParams.getPurOrgUnitNo(), "SCM_SubscribeReason", "", param);
				if (StringUtils.isNotBlank(subscribeReason) && StringUtils.isNotBlank(scmPurRequire.getBizType())) {
		            String[] subString = subscribeReason.split(",");
		            String[] subString1 = subscribeReason.split("，");
		            if (subString1.length>subString.length) {
		                subString=subString1;
		            }
		            for (String string : subString) {
		                if (StringUtils.isNotBlank(string) && string.equals(scmPurRequire.getBizType())) {
		                	ScmPurchaseType2 scmPurchaseType = scmPurchaseTypeBiz.selectByCodeAncCtrl(scmPurRequire.getBizType(),param);
		                    if (scmPurchaseType != null && StringUtils.isBlank(scmPurRequire.getSubscribeReason())) {
		                    	throw new AppException(Message.getMessage(param.getLang(), "iscm.ScmPurRequire.save.bizTypeIsNull", new String[]{scmPurchaseType.getName()}));
		                    }
		                    if (scmPurchaseType != null && StringUtils.isBlank(scmPurRequire.getPurRequireTheme())) {
		                    	throw new AppException(Message.getMessage(param.getLang(), "iscm.ScmPurRequire.save.purRequireThemeIsNull", new String[]{scmPurchaseType.getName()}));
		                    }
		                }
		            }
		        }
				bean.setList(scmPurRequireList);
				List<PurRequireDetailParams> detailList = purRequireEditParams.getDetailList();
				List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(scmPurRequire.getId(), param);
				if(scmPurRequireEntryList==null || scmPurRequireEntryList.isEmpty()){
					scmPurRequireEntryList = new ArrayList<>();
					StringBuffer itemNos = new StringBuffer("");
					for(PurRequireDetailParams detailParams:detailList){
						if(StringUtils.isBlank(detailParams.getItemNo())){
							throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
						}
						if(StringUtils.isNotBlank(itemNos.toString()))
							itemNos.append(",");
						itemNos.append("'").append(detailParams.getItemNo()).append("'");
					}
					page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+purRequireEditParams.getPurOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
			        List<OrgStorage2> invOrglist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmPurRequire.getOrgUnitNo(), false, null, param);
			        String invOrgUnitNo="";
			        if(invOrglist!=null && !invOrglist.isEmpty()){
			            boolean exists = false;
			            for(OrgStorage2 orgStorage:invOrglist){
			                if(orgStorage.isDefault()){
			                    if(!StringUtils.equals(invOrgUnitNo, orgStorage.getOrgUnitNo()))
			                        invOrgUnitNo=orgStorage.getOrgUnitNo();
			                    exists = true;
			                    break;
			                }
			            }
			            if (!exists) {
			                if(!StringUtils.equals(invOrgUnitNo, invOrglist.get(0).getOrgUnitNo()))
			                    invOrgUnitNo = invOrglist.get(0).getOrgUnitNo();
			            }
			        }
			        String priceType = sysParamBiz.getValue(scmPurRequire.getPurOrgUnitNo(), "SCM_SelectType", "1", param);
			        Date priceDate = scmPurRequire.getApplyDate();
			    	if(StringUtils.equals("2", priceType))
			    		priceDate = scmPurRequire.getReqDate();
					for(PurRequireDetailParams detailParams:detailList){
						if (detailParams.getRecentPrice() == null) {
							detailParams.setRecentPrice(BigDecimal.ZERO);
						}
						if (detailParams.getStockQty()==null) {
							detailParams.setStockQty(BigDecimal.ZERO);
						}
						ScmPurRequireEntry2 scmPurRequireEntry = new ScmPurRequireEntry2(true);
						BeanUtils.copyProperties(detailParams, scmPurRequireEntry);
						if (StringUtils.isNotBlank(detailParams.getVendorCode())) {
							//用户选择的供应商
							Scmsupplier2 scmsupplier2 = scmsupplierBiz.selectByCode(detailParams.getVendorCode(), param);
							if (scmsupplier2 != null) {
								scmPurRequireEntry.setVendorId(scmsupplier2.getId());
							}else {
								scmPurRequireEntry.setVendorId(0);
							}
						}else {
							scmPurRequireEntry.setVendorId(0);
						}
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmPurRequireEntry.setItemId(scmMaterial.getId());
								scmPurRequireEntry.setPurUnit(scmMaterial.getPurUnitId());
								scmPurRequireEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmPurRequireEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmPurRequireEntry.setBuyerId(scmMaterial.getBuyerId());
								scmPurRequireEntry.setSupplyCycle(scmMaterial.getPurSupplyCycle());
								scmPurRequireEntry.setClassCode(scmMaterial.getClassCode());
								scmPurRequireEntry.setItemNo(scmMaterial.getItemNo());
								scmPurRequireEntry.setItemName(scmMaterial.getItemName());
								break;
							}
						}
						if(scmPurRequireEntry.getPieQty()==null)
							scmPurRequireEntry.setPieQty(BigDecimal.ZERO);
						if (StringUtils.equals("R", scmPurRequire.getStatus()) || StringUtils.equals(scmPurRequire.getStatus(), "I")
								|| StringUtils.isEmpty(scmPurRequire.getStatus())) {
							scmPurRequireEntry.setRowStatus("I");
						}
						BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurRequireEntry.getItemId(), scmPurRequireEntry.getPurUnit(), param);
						scmPurRequireEntry.setBaseQty(scmPurRequireEntry.getQty().multiply(convRate));
						scmPurRequireEntry.setRecStorageOrgUnitNo(orgStorageNo);
						//待取供应商
						boolean needSetDirectPurchase = false;
						long vendorId=0;
						ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(
								purRequireEditParams.getPurOrgUnitNo(), invOrgUnitNo, scmPurRequireEntry.getItemId(),
								purRequireEditParams.getApplyDate(), param);
				        if(scmPurSupplyInfo!=null) {
				        	if(scmPurRequireEntry.getVendorId()<=0 || StringUtils.equals("N", editVendor)){
					        	vendorId = scmPurSupplyInfo.getVendorId();
					        	scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
				        	}else {
				        		vendorId = scmPurRequireEntry.getVendorId();
							}
				        }else {
				        	if(StringUtils.equals("N", editVendor))
				        		scmPurRequireEntry.setVendorId(0);
							vendorId=scmPurRequireEntry.getVendorId();
							needSetDirectPurchase = true;
						}
				        scmPurRequireEntry.setVendorId(vendorId);
				        String priceBillId = "";
						ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(purRequireEditParams.getPurOrgUnitNo(),vendorId, String.valueOf(scmPurRequireEntry.getItemId()), scmPurRequireEntry.getPurUnit(),priceDate,orgStorageNo, priceBillId, param);
						if(scmMaterialPrice!=null && !StringUtils.equals("0", scmMaterialPrice.getRefPriceStatus())){
							if (StringUtils.equals("Y", editPrice) && StringUtils.equals("1", scmMaterialPrice.getRefPriceStatus()) && StringUtils.equals(scmMaterialPrice.getPriceBillNo(), scmPurRequireEntry.getPriceBillNo())) {//TODO价格来源于报价时不进行重新取价，会将手工修改的覆盖
								scmMaterialPrice.setTaxPrice(scmPurRequireEntry.getPrice());
							}
							scmPurRequireEntry.setPrice(scmMaterialPrice.getTaxPrice());
							scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
							scmPurRequireEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
							scmPurRequireEntry.setPriceBillNo(scmMaterialPrice.getPriceBillNo());
							scmPurRequireEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
							if(scmMaterialPrice.getVendorId()>0){
								//定价且绑定供应商
								needSetDirectPurchase = true;
								scmPurRequireEntry.setVendorId(scmMaterialPrice.getVendorId());
							}
						}else {
							scmPurRequireEntry.setPriceBillId(0);
							scmPurRequireEntry.setPriceBillNo("");
							scmPurRequireEntry.setRefPriceStatus("0");
						}
						if(needSetDirectPurchase) {
							scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItemAndVendor(
									purRequireEditParams.getPurOrgUnitNo(), invOrgUnitNo,scmPurRequireEntry.getVendorId(), scmPurRequireEntry.getItemId(),
									purRequireEditParams.getApplyDate(), param);
							if(scmPurSupplyInfo==null) {
								scmPurRequireEntry.setDirectPurchase(false);
							}else {
								scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
							}
						}
						if(scmPurRequireEntry.getAmt() == null){
							scmPurRequireEntry.setAmt(BigDecimal.ZERO);
							if(scmPurRequireEntry.getQty() != null && scmPurRequireEntry.getPrice() != null){
								scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
							}
						}
						scmPurRequireEntryList.add(scmPurRequireEntry);
					}
				}else{
					//先删除不存在行的记录
					for(int i = scmPurRequireEntryList.size()-1;i>=0;i--){
						ScmPurRequireEntry2 scmPurRequireEntry2 = scmPurRequireEntryList.get(i);
						boolean exists = false;
						for(PurRequireDetailParams detailParams:detailList){
							if(detailParams.getLineId()==scmPurRequireEntry2.getLineId()){
								exists = true;
								break;
							}
						}
						if(!exists)
							scmPurRequireEntryList.remove(i);
					}
					StringBuffer itemNos = new StringBuffer("");
					for(PurRequireDetailParams detailParams:detailList){
						if(StringUtils.isBlank(detailParams.getItemNo())){
							throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
						}
						if(StringUtils.isNotBlank(itemNos.toString()))
							itemNos.append(",");
						itemNos.append("'").append(detailParams.getItemNo()).append("'");
					}
					page = new Page();
					page.setModelClass(ScmMaterial2.class);
					page.setShowCount(Integer.MAX_VALUE);
					page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
					ArrayList argList = new ArrayList();
			        argList.add("orgUnitNo="+purRequireEditParams.getPurOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
			        List<OrgStorage2> invOrglist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, scmPurRequire.getOrgUnitNo(), false, null, param);
			        String invOrgUnitNo="";
			        if(invOrglist!=null && !invOrglist.isEmpty()){
			            boolean exists = false;
			            for(OrgStorage2 orgStorage:invOrglist){
			                if(orgStorage.isDefault()){
			                    if(!StringUtils.equals(invOrgUnitNo, orgStorage.getOrgUnitNo()))
			                        invOrgUnitNo=orgStorage.getOrgUnitNo();
			                    exists = true;
			                    break;
			                }
			            }
			            if (!exists) {
			                if(!StringUtils.equals(invOrgUnitNo, invOrglist.get(0).getOrgUnitNo()))
			                    invOrgUnitNo = invOrglist.get(0).getOrgUnitNo();
			            }
			        }
					//更新时是根据行号进行更新的
					int lineId = 0;
					String priceType = sysParamBiz.getValue(scmPurRequire.getPurOrgUnitNo(), "SCM_SelectType", "1", param);
			        Date priceDate = scmPurRequire.getApplyDate();
			    	if(StringUtils.equals("2", priceType))
			    		priceDate = scmPurRequire.getReqDate();
					for(PurRequireDetailParams detailParams:detailList){
						if (detailParams.getRecentPrice() == null) {
							detailParams.setRecentPrice(BigDecimal.ZERO);
						}
						if (detailParams.getStockQty()==null) {
							detailParams.setStockQty(BigDecimal.ZERO);
						}
						ScmPurRequireEntry2 scmPurRequireEntry = new ScmPurRequireEntry2(true);
						boolean exists = false;
						for(ScmPurRequireEntry2 scmPurRequireEntry2:scmPurRequireEntryList){
							if(detailParams.getLineId()==scmPurRequireEntry2.getLineId() && detailParams.getLineId()!=0){
								scmPurRequireEntry = scmPurRequireEntry2;
								exists = true;
								break;
							}
						}
						BeanUtils.copyProperties(detailParams, scmPurRequireEntry);
						if (StringUtils.isNotBlank(detailParams.getVendorCode())) {
							//用户选择的供应商
							Scmsupplier2 scmsupplier2 = scmsupplierBiz.selectByCode(detailParams.getVendorCode(), param);
							if (scmsupplier2 != null) {
								scmPurRequireEntry.setVendorId(scmsupplier2.getId());
							}else {
								scmPurRequireEntry.setVendorId(0);
							}
						}else {
							scmPurRequireEntry.setVendorId(0);
						}
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmPurRequireEntry.setItemId(scmMaterial.getId());
								scmPurRequireEntry.setPurUnit(scmMaterial.getPurUnitId());
								scmPurRequireEntry.setPieUnit(scmMaterial.getPieUnitId());
								scmPurRequireEntry.setBaseUnit(scmMaterial.getBaseUnitId());
								scmPurRequireEntry.setBuyerId(scmMaterial.getBuyerId());
								scmPurRequireEntry.setSupplyCycle(scmMaterial.getPurSupplyCycle());
								scmPurRequireEntry.setClassCode(scmMaterial.getClassCode());
								scmPurRequireEntry.setItemNo(scmMaterial.getItemNo());
								scmPurRequireEntry.setItemName(scmMaterial.getItemName());
								break;
							}
						}
						if (StringUtils.equals("R", scmPurRequire.getStatus()) || StringUtils.equals(scmPurRequire.getStatus(), "I")
								|| StringUtils.isEmpty(scmPurRequire.getStatus())) {
							scmPurRequireEntry.setRowStatus("I");
						}
						BigDecimal convRate = ScmMaterialUtil.getConvRate(scmPurRequireEntry.getItemId(), scmPurRequireEntry.getPurUnit(), param);
						scmPurRequireEntry.setBaseQty(scmPurRequireEntry.getQty().multiply(convRate));
						scmPurRequireEntry.setRecStorageOrgUnitNo(orgStorageNo);
						//待取供应商
						boolean needSetDirectPurchase = false;
						long vendorId=0;
						ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(
								purRequireEditParams.getPurOrgUnitNo(), invOrgUnitNo, scmPurRequireEntry.getItemId(),
								purRequireEditParams.getApplyDate(), param);
				        if(scmPurSupplyInfo!=null ) {
				        	if(scmPurRequireEntry.getVendorId()<=0 || StringUtils.equals("N", editVendor)){
					        	vendorId = scmPurSupplyInfo.getVendorId();
					        	scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
				        	}else {
				        		vendorId = scmPurRequireEntry.getVendorId();
							}
				        }else {
				        	if(StringUtils.equals("N", editVendor))
				        		scmPurRequireEntry.setVendorId(0);
							vendorId=scmPurRequireEntry.getVendorId();
							needSetDirectPurchase = true;
						}
				        scmPurRequireEntry.setVendorId(vendorId);
				        String priceBillId = "";
						ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(purRequireEditParams.getPurOrgUnitNo(),vendorId, String.valueOf(scmPurRequireEntry.getItemId()), scmPurRequireEntry.getPurUnit(),priceDate,orgStorageNo, priceBillId,param);
						if(scmMaterialPrice!=null && !StringUtils.equals("0", scmMaterialPrice.getRefPriceStatus())){
							if (StringUtils.equals("Y", editPrice) && StringUtils.equals("1", scmMaterialPrice.getRefPriceStatus()) && StringUtils.equals(scmMaterialPrice.getPriceBillNo(), scmPurRequireEntry.getPriceBillNo())) {//TODO价格来源于报价时不进行重新取价，会将手工修改的覆盖
								scmMaterialPrice.setTaxPrice(scmPurRequireEntry.getPrice());
							}
							scmPurRequireEntry.setPrice(scmMaterialPrice.getTaxPrice());
							scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
							scmPurRequireEntry.setPriceBillId(scmMaterialPrice.getPriceBillId());
							scmPurRequireEntry.setPriceBillNo(scmMaterialPrice.getPriceBillNo());
							scmPurRequireEntry.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
							if(scmMaterialPrice.getVendorId()>0){
								//定价且绑定供应商
								needSetDirectPurchase = true;
								scmPurRequireEntry.setVendorId(scmMaterialPrice.getVendorId());
							}
						}else {
							scmPurRequireEntry.setPriceBillId(0);
							scmPurRequireEntry.setPriceBillNo("");
							scmPurRequireEntry.setRefPriceStatus("0");
						}
						if(needSetDirectPurchase) {
							scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItemAndVendor(
									purRequireEditParams.getPurOrgUnitNo(), invOrgUnitNo,scmPurRequireEntry.getVendorId(), scmPurRequireEntry.getItemId(),
									purRequireEditParams.getApplyDate(), param);
							if(scmPurSupplyInfo==null) {
								scmPurRequireEntry.setDirectPurchase(false);
							}else {
								scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
							}
						}
						if(scmPurRequireEntry.getAmt() == null){
							scmPurRequireEntry.setAmt(BigDecimal.ZERO);
							if(scmPurRequireEntry.getQty() != null && scmPurRequireEntry.getPrice() != null){
								scmPurRequireEntry.setAmt(scmPurRequireEntry.getQty().multiply(scmPurRequireEntry.getPrice()));
							}
						}
						if(scmPurRequireEntry.getPieQty()==null)
							scmPurRequireEntry.setPieQty(BigDecimal.ZERO);
						if(!exists)
							scmPurRequireEntryList.add(lineId, scmPurRequireEntry);
						lineId = lineId +1;
					}
				}
				//更新子表数据
				bean.setList2(scmPurRequireEntryList);
				this.update(bean, param);
			}else{
				throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
			}
		}else{
			throw new AppException("webservice.params.null");
		}
	}

	@Override
	public List<OrgPurchase2> queryPurReqPurOrgUnit(PurReqPurOrgUnitParams purReqPurOrgUnitParams, Param param)
			throws AppException {
		if(purReqPurOrgUnitParams!=null && StringUtils.isNotBlank(purReqPurOrgUnitParams.getReqOrgUnitNo())){
			Page page = new Page();
			page.setModelClass(OrgPurchase2.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<String> arglist = new ArrayList<String>();
			arglist.add("type=to");
			arglist.add("relationType="+OrgUnitRelationType.ADMINTOPUR);
			arglist.add("fromOrgUnitNo="+purReqPurOrgUnitParams.getReqOrgUnitNo());
			return orgPurchaseBiz.queryPage(page, arglist, "queryPageEx", param);
		}
		return null;
	}

	@Override
	public List<ScmMaterial2> queryPurReqMaterialList(PurReqMaterialListParams purReqMaterialListParams, int pageNum,Param param) throws AppException {
		OrgPurchase2  orgPurchase = orgPurchaseBiz.selectByOrgUnitNo(purReqMaterialListParams.getPurOrgUnitNo(), param);
		if (orgPurchase == null) {
			throw new AppException("iscm.scmpurrequire.querypurreqmateriallist.error.orgpurchasenotexists");
		}
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		List<String> arglist = new ArrayList<String>();
		arglist.add("orgUnitNo="+purReqMaterialListParams.getPurOrgUnitNo());
		arglist.add("controlUnitNo="+orgPurchase.getControlUnitNo());
		if(StringUtils.isNotBlank(purReqMaterialListParams.getMixParam())){
			page.setSqlCondition("(ScmMaterial.itemNo like '%"+purReqMaterialListParams.getMixParam()+"%' or ScmMaterial.itemName like '%"+purReqMaterialListParams.getMixParam()+
					"%' or ScmMaterial.barCode like '%"+purReqMaterialListParams.getMixParam()+"%' or ScmMaterial.pym like '%"+purReqMaterialListParams.getMixParam()+"%')");
		}
		return scmMaterialBiz.queryPage(page, arglist, "findBySinglePurAllPage", param);
	}

	@Override
	public List<OrgAdmin2> queryPurReqOrgUnit(PurReqOrgUnitParams purReqOrgUnitParams, Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(OrgAdmin2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if (StringUtils.isNotBlank(purReqOrgUnitParams.getOrgUnitName())) {
			page.getParam().put((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), 
    				new QueryParam((ClassUtils.getFinalModelSimpleName(OrgBaseUnit2.class) + "." + OrgBaseUnit2.FN_ORGUNITNAME), QueryParam.QUERY_LIKE, "%"+purReqOrgUnitParams.getOrgUnitName()+"%"));
		}
		OrgAdmin2 orgAdmin2 = orgAdminBiz.selectByOrgUnitNo(param.getOrgUnitNo(), param);
		page.setSqlCondition("OrgAdmin.longNo like '" + orgAdmin2.getLongNo()+ "%'  and orgType='2' And Exists(Select 1 From orgunitrelation Where fromOrgUnitNo=OrgAdmin.orgUnitNo And relationType='AdminToCost')");
		return orgAdminBiz.findPage(page, param);
	}

	@Override
	public List<Employee2> queryPurReqPerson(PurReqPersonParams purReqPersonParams, int pageNum,Param param) throws AppException {
		if (StringUtils.equals(purReqPersonParams.getDefaultEmp(), "1")) {
			Usr usr = usrBiz.selectByCode(param.getUsrCode(), param);
			Employee2 employee = (Employee2) employeeBiz.select(usr.getEmpid(), param);
			ArrayList<Employee2> arrayList = new ArrayList<>();
			if(employee!=null)
				arrayList.add(employee);
			return arrayList;
		}
		Page page = new Page();
		page.setModelClass(Employee2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		if(StringUtils.isNotBlank(purReqPersonParams.getApplicantsName())){
			page.setSqlCondition("Employee.empName like '"+purReqPersonParams.getApplicantsName()+"%'");
		}
		return employeeBiz.findPage(page, param);
	}

	@Override
	public List<ScmInvWareHouse> queryPurReqWareHouse(PurReqWareHouseParams purReqWareHouseParams, Param param)
			throws AppException {
		if(StringUtils.isBlank(purReqWareHouseParams.getReqOrgUnitNo()))
			throw new AppException("iscm.RequireController.queryPurReqPurOrgUnit.reqOrgUnitNo.isnull");
//		List<OrgStorage2> orgStorageList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, purReqWareHouseParams.getReqOrgUnitNo(), false, null, param);
		List<OrgCompany2> orgCompany2s = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOFIN, purReqWareHouseParams.getReqOrgUnitNo(), false, null, param);
		List<OrgStorage2> orgStorageList=new ArrayList<>();
		if (orgCompany2s !=null && !orgCompany2s.isEmpty()) {
			orgStorageList = orgUnitRelationBiz.findFromOrgUnitByType(OrgUnitRelationType.INVTOFIN, orgCompany2s.get(0).getOrgUnitNo(), false, null, param);
		}
		StringBuffer invOrgUnitNo=new StringBuffer();
		if(orgStorageList!=null && !orgStorageList.isEmpty()) {
			for(OrgStorage2 orgStorage:orgStorageList) {
				if (StringUtils.isNotEmpty(invOrgUnitNo.toString())) 
					invOrgUnitNo.append(",");
				invOrgUnitNo.append("'").append(orgStorage.getOrgUnitNo()).append("'");
			}
		}
		if (StringUtils.isBlank(invOrgUnitNo.toString())) {
			invOrgUnitNo.append("0");
		}
		Page page = new Page();
		page.setModelClass(ScmInvWareHouse.class);
		page.setShowCount(Integer.MAX_VALUE);
		List<String> arglist = new ArrayList<>();
		arglist.add("orgUnitNo="+invOrgUnitNo.toString());
		arglist.add("usrCode="+param.getUsrCode());
		return scmInvWareHouseBiz.queryPage(page, arglist, "selectByUsrAndOrgsPage", param);
	}

	@Override
	public ScmMaterialPrice queryPurReqMaterialPrice(ScmPurPriceQuery scmPurPriceQuery, Param param) throws AppException {
		Page page = new Page();
		page.setModelClass(ScmMaterial2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.setSqlCondition("ScmMaterial.itemNo = '"+scmPurPriceQuery.getItemNo()+"'");
		ArrayList argList = new ArrayList();
        argList.add("orgUnitNo="+scmPurPriceQuery.getPurOrgUnitNo());
        argList.add("controlUnitNo=" + param.getControlUnitNo());
        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
        if(scmMaterialList==null || scmMaterialList.isEmpty()){
        	throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemnorexists");
        }
        scmPurPriceQuery.setItemIds(String.valueOf(scmMaterialList.get(0).getId()));
        scmPurPriceQuery.setUnitId(scmMaterialList.get(0).getPurUnitId());
        //待取供应商
        String reqOrgUnitNo = param.getOrgUnitNo();
        if(StringUtils.isNotBlank(scmPurPriceQuery.getReqOrgUnitNo())){
        	reqOrgUnitNo = scmPurPriceQuery.getReqOrgUnitNo();
        }
        List<OrgStorage2> invOrglist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, reqOrgUnitNo, false, null, param);
        String invOrgUnitNo="";
        if(invOrglist!=null && !invOrglist.isEmpty()){
            boolean exists = false;
            for(OrgStorage2 orgStorage:invOrglist){
                if(orgStorage.isDefault()){
                    if(!StringUtils.equals(invOrgUnitNo, orgStorage.getOrgUnitNo()))
                        invOrgUnitNo=orgStorage.getOrgUnitNo();
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                if(!StringUtils.equals(invOrgUnitNo, invOrglist.get(0).getOrgUnitNo()))
                    invOrgUnitNo = invOrglist.get(0).getOrgUnitNo();
            }
        }
        
		ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(scmPurPriceQuery.getPurOrgUnitNo(),
				invOrgUnitNo, scmMaterialList.get(0).getId(), scmPurPriceQuery.getBizDate(), param);
        if(scmPurSupplyInfo!=null && scmPurPriceQuery.getVendorId()<=0) {
        	scmPurPriceQuery.setVendorId(scmPurSupplyInfo.getVendorId());
        }
        String priceBillId = "";
		ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(scmPurPriceQuery.getPurOrgUnitNo(),
				scmPurPriceQuery.getVendorId(), scmPurPriceQuery.getItemIds(), scmPurPriceQuery.getUnitId(),
				scmPurPriceQuery.getBizDate(), invOrgUnitNo, priceBillId, param);
        if(scmMaterialPrice!=null){
        	scmMaterialPrice.setItemNo(scmPurPriceQuery.getItemNo());
        	if(scmMaterialPrice.getVendorId() > 0){
        		scmPurPriceQuery.setVendorId(scmMaterialPrice.getVendorId());
        	}
        }
    	if(scmPurPriceQuery.getVendorId()>0) {
    		Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurPriceQuery.getVendorId(), param);
    		if(scmsupplier!=null)
    			scmMaterialPrice.setVendorName(scmsupplier.getVendorName());
    	}
    	setLineEditType(scmMaterialPrice,scmPurPriceQuery, param);
        return scmMaterialPrice;
	}
	
	/**
	 * 设置行供应商Or价格是否可修改
	 * @param scmMaterialPrice
	 * @param param
	 * @throws AppException
	 */
	public void setLineEditType(ScmMaterialPrice scmMaterialPrice,ScmPurPriceQuery scmPurPriceQuery,Param param) throws AppException {
		/*
		 * ---1:无价格来源或价格来源为报价单，价格可修改
		 * ---0：价格来源为定价单（临时定价And日常定价），价格不可修改
		 */
		String editPrice = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_EditPrice", "Y", param);
		String getPriceWay = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_GetPriceWay", "N", param);
		String editVendor = sysParamBiz.getValue(scmPurPriceQuery.getPurOrgUnitNo(), "SCM_EditReqVendor", "N", param);
		if (StringUtils.equals(editPrice, "Y")) {
			if (scmMaterialPrice.getPriceBillId()<=0 || (scmMaterialPrice.getPriceBillId() > 0 && "1".equals(scmMaterialPrice.getRefPriceStatus()))) {
				scmMaterialPrice.setPriceEditType("1");
			}else {
				scmMaterialPrice.setPriceEditType("0");
			}
		}else {
			scmMaterialPrice.setPriceEditType("0");
		}
		
		if (StringUtils.equals(editVendor, "Y")) {
			if (!StringUtils.contains("5,3,6,7", scmMaterialPrice.getRefPriceStatus())) {
				scmMaterialPrice.setVendorEditType("1");
			}else {
				scmMaterialPrice.setVendorEditType("0");
			}
		}else {
			scmMaterialPrice.setVendorEditType("0");
		}
		
	}

	@Override
	protected void beforeDelete(ScmPurRequire2 entity, Param param)
			throws AppException {
		ScmPurRequire2 scmPurRequire = this.selectDirect(entity.getId(), param);
		if(scmPurRequire!=null && !scmPurRequire.isTemplete() && !StringUtils.contains("I,R",scmPurRequire.getStatus())){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getPrNo()}));
		}
	}
	
	@Override
	protected void beforeUpdate(ScmPurRequire2 oldEntity, ScmPurRequire2 newEntity,	Param param) throws AppException {
		if(newEntity!=null) {
			ScmPurRequire2 scmPurRequire = this.select(newEntity.getPK(), param);
			if(scmPurRequire != null){
				newEntity.setOutAuditType(scmPurRequire.getOutAuditType());
				newEntity.setOtherAuditNo(scmPurRequire.getOtherAuditNo());
			}
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		int amtPrec = Integer.parseInt(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_AmtPrecision", "2", param));
		ScmPurRequire2 entry = (ScmPurRequire2) bean.getList().get(0);
		if(entry!=null) {
			ScmPurRequire2 scmPurRequire = this.select(entry.getPK(), param);
			if(!StringUtils.contains("I,D,P,R", scmPurRequire.getStatus())){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			if(StringUtils.isNotBlank(entry.getStatus())) {
			if(!StringUtils.equals(entry.getStatus(), scmPurRequire.getStatus())) {
				throw new AppException(Message.getMessage(param.getLang(),"iscm.commom.edit.error.inconsistentstatus"));
			}
			}
			if (StringUtils.equals("R", scmPurRequire.getStatus())) {
				entry.setStatus("I");
            }
			String status = entry.getStatus();
			List<ScmPurRequireEntry2> scmPurRequireEntryList = bean.getList2();
	        BigDecimal amt= BigDecimal.ZERO;
			if(scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()){
				for(ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList){
					BigDecimal b = (scmPurRequireEntry.getQty()).multiply(scmPurRequireEntry.getPrice()).setScale(amtPrec, RoundingMode.HALF_UP);
					scmPurRequireEntry.setAmt(b);
	                amt = amt.add(scmPurRequireEntry.getAmt());
	                if (StringUtils.equals("R", scmPurRequire.getStatus())) {
	                	scmPurRequireEntry.setRowStatus("I");
	                }
	                if(!StringUtils.equals(status, "I") && StringUtils.equals("I",scmPurRequireEntry.getRowStatus())) {
	                	throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
	                }
				}
			}
			entry.setTaxAmt(amt);
		}
	}

    @Override
    public ScmPurRequire2 saveTemplete(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
        if (scmPurRequire == null) {
            return null;
        }
        // 获取明细
        List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(scmPurRequire.getId(), param);
        if (scmPurRequireEntryList == null || scmPurRequireEntryList.isEmpty()) {
            return null;
        }
        CommonBean bean = new CommonBean();
        List<ScmPurRequire2> scmPurRequireTempList = new ArrayList<>();
        List<ScmPurRequireEntry2> scmPurRequireEntryTempList = new ArrayList<>();
        ScmPurRequire2 scmPurRequireTemp = new ScmPurRequire2(true);
        if (scmPurRequire.isCoverTemplete()) { //覆盖原有模板
            //获取原来的模板
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("orgUnitNo", scmPurRequire.getOrgUnitNo());
            map.put("remarks", scmPurRequire.getRemarks());
            map.put("templete", "1");
            List<ScmPurRequire2> scmPurRequireOldList = this.findAll(map, param);
            if (scmPurRequireOldList == null || scmPurRequireOldList.isEmpty()) {
                throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.templateNotExist", 
                        new String[] {scmPurRequire.getRemarks()});
            }
            scmPurRequireTemp = scmPurRequireOldList.get(0);
            scmPurRequireTemp.setControlUnitNo(scmPurRequire.getControlUnitNo());
            scmPurRequireTemp.setOrgUnitNo(scmPurRequire.getOrgUnitNo());
            scmPurRequireTemp.setFinOrgUnitNo(scmPurRequire.getFinOrgUnitNo());
            scmPurRequireTemp.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
            scmPurRequireTemp.setToWareHouse(scmPurRequire.isToWareHouse());
            scmPurRequireTemp.setReceiveWareHouseId(scmPurRequire.getReceiveWareHouseId());
            scmPurRequireTemp.setCurrencyNo(scmPurRequire.getCurrencyNo());
            scmPurRequireTemp.setExchangeRate(scmPurRequire.getExchangeRate());
            scmPurRequireTemp.setCreator(param.getUsrCode());
            scmPurRequireTemp.setApplicants(param.getUsrCode());
            scmPurRequireTemp.setCreateDate(CalendarUtil.getDate(param));
            scmPurRequireTemp.setApplyDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurRequire.getApplyDate())));
            scmPurRequireTemp.setReqDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
            scmPurRequireTemp.setStatus("I");
            scmPurRequireTemp.setTaxAmt(BigDecimal.ZERO);
            scmPurRequireTemp.setTemplete(true);
            scmPurRequireTemp.setRemarks(scmPurRequire.getRemarks()); //模板的名称
            scmPurRequireTemp.setBizType(scmPurRequire.getBizType());
            scmPurRequireTempList.add(scmPurRequireTemp);
            bean.setList(scmPurRequireTempList);
            for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
                ScmPurRequireEntry2 scmPurRequireEntryTemp = new ScmPurRequireEntry2(true);
                scmPurRequireEntryTemp.setItemId(scmPurRequireEntry.getItemId());
                scmPurRequireEntryTemp.setPurUnit(scmPurRequireEntry.getPurUnit());
                scmPurRequireEntryTemp.setBaseUnit(scmPurRequireEntry.getBaseUnit());
                scmPurRequireEntryTemp.setPieUnit(scmPurRequireEntry.getPieUnit());
                scmPurRequireEntryTemp.setBaseQty(scmPurRequireEntry.getBaseQty());
                scmPurRequireEntryTemp.setPurOrgUnitNo(scmPurRequireEntry.getPurOrgUnitNo());
                scmPurRequireEntryTemp.setRecStorageOrgUnitNo(scmPurRequireEntry.getRecStorageOrgUnitNo());
                scmPurRequireEntryTemp.setEntrusted(scmPurRequireEntry.isEntrusted());
                scmPurRequireEntryTemp.setMsRecStorageOrgUnitNo(scmPurRequireEntry.getMsRecStorageOrgUnitNo());
                scmPurRequireEntryTemp.setQty(BigDecimal.ZERO);
                scmPurRequireEntryTemp.setPrice(BigDecimal.ZERO);
                scmPurRequireEntryTemp.setAmt(BigDecimal.ZERO);
                scmPurRequireEntryTemp.setRowStatus("I");
                scmPurRequireEntryTemp.setReqDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
                scmPurRequireEntryTemp.setRecentPrice(scmPurRequireEntry.getRecentPrice());
                scmPurRequireEntryTemp.setStockQty(scmPurRequireEntry.getStockQty());
                scmPurRequireEntryTempList.add(scmPurRequireEntryTemp);
            }
            bean.setList2(scmPurRequireEntryTempList);
            this.update(bean, param);
        } else { //新增模板
            scmPurRequireTemp.setControlUnitNo(scmPurRequire.getControlUnitNo());
            scmPurRequireTemp.setOrgUnitNo(scmPurRequire.getOrgUnitNo());
            scmPurRequireTemp.setFinOrgUnitNo(scmPurRequire.getFinOrgUnitNo());
            scmPurRequireTemp.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
            scmPurRequireTemp.setToWareHouse(scmPurRequire.isToWareHouse());
            scmPurRequireTemp.setReceiveWareHouseId(scmPurRequire.getReceiveWareHouseId());
            scmPurRequireTemp.setCurrencyNo(scmPurRequire.getCurrencyNo());
            scmPurRequireTemp.setExchangeRate(scmPurRequire.getExchangeRate());
            scmPurRequireTemp.setCreator(param.getUsrCode());
            scmPurRequireTemp.setApplicants(param.getUsrCode());
            scmPurRequireTemp.setCreateDate(CalendarUtil.getDate(param));
            scmPurRequireTemp.setApplyDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurRequire.getApplyDate())));
            scmPurRequireTemp.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
            scmPurRequireTemp.setStatus("I");
            scmPurRequireTemp.setTaxAmt(BigDecimal.ZERO);
            scmPurRequireTemp.setTemplete(true);
            scmPurRequireTemp.setRemarks(scmPurRequire.getRemarks()); //模板的名称
            scmPurRequireTemp.setBizType(scmPurRequire.getBizType());
            
            scmPurRequireTempList.add(scmPurRequireTemp);
            bean.setList(scmPurRequireTempList);
            // 明细
            for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
                ScmPurRequireEntry2 scmPurRequireEntryTemp = new ScmPurRequireEntry2(true);
                scmPurRequireEntryTemp.setItemId(scmPurRequireEntry.getItemId());
                scmPurRequireEntryTemp.setPurUnit(scmPurRequireEntry.getPurUnit());
                scmPurRequireEntryTemp.setBaseUnit(scmPurRequireEntry.getBaseUnit());
                scmPurRequireEntryTemp.setPieUnit(scmPurRequireEntry.getPieUnit());
                scmPurRequireEntryTemp.setBaseQty(scmPurRequireEntry.getBaseQty());
                scmPurRequireEntryTemp.setPurOrgUnitNo(scmPurRequireEntry.getPurOrgUnitNo());
                scmPurRequireEntryTemp.setRecStorageOrgUnitNo(scmPurRequireEntry.getRecStorageOrgUnitNo());
                scmPurRequireEntryTemp.setEntrusted(scmPurRequireEntry.isEntrusted());
                scmPurRequireEntryTemp.setMsRecStorageOrgUnitNo(scmPurRequireEntry.getMsRecStorageOrgUnitNo());
                scmPurRequireEntryTemp.setQty(BigDecimal.ZERO);
                scmPurRequireEntryTemp.setPrice(BigDecimal.ZERO);
                scmPurRequireEntryTemp.setAmt(BigDecimal.ZERO);
                scmPurRequireEntryTemp.setRowStatus("I");
                scmPurRequireEntryTemp.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
                scmPurRequireEntryTemp.setRecentPrice(scmPurRequireEntry.getRecentPrice());
                scmPurRequireEntryTemp.setStockQty(scmPurRequireEntry.getStockQty());
                scmPurRequireEntryTempList.add(scmPurRequireEntryTemp);
            }
            bean.setList2(scmPurRequireEntryTempList);
            this.add(bean, param);
        }
        return scmPurRequireTemp;
    }

    @Override
    public ScmPurRequire2 templeteMake(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
        if (scmPurRequire == null) {
            return null;
        }
        // 获取明细
        List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(scmPurRequire.getId(), param);
        if (scmPurRequireEntryList == null || scmPurRequireEntryList.isEmpty()) {
            return null;
        }
        CommonBean bean = new CommonBean();
        List<ScmPurRequire2> scmPurRequireResultList = new ArrayList<>();
        List<ScmPurRequireEntry2> scmPurRequireEntryResultList = new ArrayList<>();
        int pricePrec = Integer.valueOf(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param));	//单价精度  
        ScmPurRequire2 scmPurRequireResult = new ScmPurRequire2(true);
        scmPurRequireResult.setControlUnitNo(scmPurRequire.getControlUnitNo());
        scmPurRequireResult.setOrgUnitNo(scmPurRequire.getOrgUnitNo());
        scmPurRequireResult.setFinOrgUnitNo(scmPurRequire.getFinOrgUnitNo());
        scmPurRequireResult.setPurOrgUnitNo(scmPurRequire.getPurOrgUnitNo());
        scmPurRequireResult.setToWareHouse(scmPurRequire.isToWareHouse());
        scmPurRequireResult.setReceiveWareHouseId(scmPurRequire.getReceiveWareHouseId());
        scmPurRequireResult.setCurrencyNo(scmPurRequire.getCurrencyNo());
        scmPurRequireResult.setExchangeRate(scmPurRequire.getExchangeRate());
        scmPurRequireResult.setCreator(param.getUsrCode());
        scmPurRequireResult.setApplicants(param.getUsrCode());
        scmPurRequireResult.setCreateDate(CalendarUtil.getDate(param));
        scmPurRequireResult.setApplyDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())));
        scmPurRequireResult.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
        scmPurRequireResult.setStatus("I");
        scmPurRequireResult.setTaxAmt(BigDecimal.ZERO);
        scmPurRequireResult.setTemplete(false);
        scmPurRequireResult.setBizType(scmPurRequire.getBizType());
        
        scmPurRequireResultList.add(scmPurRequireResult);
        bean.setList(scmPurRequireResultList);
        // 明细
        for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
            ScmPurRequireEntry2 scmPurRequireEntryResult = new ScmPurRequireEntry2(true);
            scmPurRequireEntryResult.setItemId(scmPurRequireEntry.getItemId());
            scmPurRequireEntryResult.setPurUnit(scmPurRequireEntry.getPurUnit());
            scmPurRequireEntryResult.setBaseUnit(scmPurRequireEntry.getBaseUnit());
            scmPurRequireEntryResult.setBaseQty(scmPurRequireEntry.getBaseQty());
            scmPurRequireEntryResult.setPurOrgUnitNo(scmPurRequireEntry.getPurOrgUnitNo());
            
            //待取供应商
	        List<OrgStorage2> invOrglist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, param.getOrgUnitNo(), false, null, param);
	        String invOrgUnitNo="";
	        if(invOrglist!=null && !invOrglist.isEmpty()){
	            boolean exists = false;
	            for(OrgStorage2 orgStorage:invOrglist){
	                if(orgStorage.isDefault()){
	                    if(!StringUtils.equals(invOrgUnitNo, orgStorage.getOrgUnitNo()))
	                        invOrgUnitNo=orgStorage.getOrgUnitNo();
	                    exists = true;
	                    break;
	                }
	            }
	            if (!exists) {
	                if(!StringUtils.equals(invOrgUnitNo, invOrglist.get(0).getOrgUnitNo()))
	                    invOrgUnitNo = invOrglist.get(0).getOrgUnitNo();
	            }
	        }
	        
            long vendorId=0;
			ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(
					scmPurRequire.getPurOrgUnitNo(), invOrgUnitNo, scmPurRequireEntry.getItemId(),
					scmPurRequire.getApplyDate(), param);
	        if(scmPurSupplyInfo!=null) {
	        	vendorId = scmPurSupplyInfo.getVendorId();
	        	scmPurRequireEntry.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
	        }
	        scmPurRequireEntry.setVendorId(vendorId);
	        String priceBillId = "";
			ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(scmPurRequire.getPurOrgUnitNo(),
					vendorId, String.valueOf(scmPurRequireEntry.getItemId()), scmPurRequireEntry.getPurUnit(),
					scmPurRequire.getApplyDate(), scmPurRequireEntry.getRecStorageOrgUnitNo(), priceBillId, param);
			if(scmMaterialPrice!=null && !StringUtils.equals("0", scmMaterialPrice.getRefPriceStatus())){
				scmPurRequireEntryResult.setPrice(scmMaterialPrice.getTaxPrice().setScale(pricePrec, RoundingMode.HALF_UP));
				scmPurRequireEntryResult.setPriceBillId(scmMaterialPrice.getPriceBillId());
				scmPurRequireEntryResult.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
				if(scmMaterialPrice.getVendorId()>0){
					//定价且绑定供应商
					scmPurRequireEntryResult.setVendorId(scmMaterialPrice.getVendorId());
				}
			} else {
	            scmPurRequireEntryResult.setPrice(BigDecimal.ZERO);
			}
            
			scmPurRequireEntryResult.setAmt(BigDecimal.ZERO);
			scmPurRequireEntryResult.setQty(BigDecimal.ZERO);
            scmPurRequireEntryResult.setRowStatus("I");
            scmPurRequireEntryResult.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
            scmPurRequireEntryResultList.add(scmPurRequireEntryResult);
        }
        bean.setList2(scmPurRequireEntryResultList);
        this.add(bean, param);        
        return scmPurRequireResult;
    }
    
	@Override
	public ScmPurRequire2 doAuditPurRequire(CommonAuditParams commonAuditParams, Param param) throws AppException {
		ScmPurRequire2 require = null;
		
		ScmPurRequire2 scmPurRequire= new ScmPurRequire2();
		scmPurRequire.setId(commonAuditParams.getBillId());
		scmPurRequire.setPrNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		List<CommonAuditDetailParams> detailList = commonAuditParams.getDetailList();
		List<ScmPurRequire2> scmPurRequireList = new ArrayList<> ();
		List<ScmPurRequireEntry2> scmPurRequireEntryList = new ArrayList<> ();
		int countRefuse = 0;
		
		if(scmPurRequire.getId()>0){
			require = this.selectDirect(scmPurRequire.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurRequire2.FN_PRNO,
					new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,
							scmPurRequire.getPrNo()));
			map.put(ScmPurRequire2.FN_CONTROLUNITNO, new QueryParam(ScmPurRequire2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			
			scmPurRequireList =this.findAll(map, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				require=scmPurRequireList.get(0);
			}
		}
		
		if (require != null) {
			this.setConvertMap(require, param);
			scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(require.getId(), param);
			
			if(scmPurRequireEntryList == null || scmPurRequireEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			if(StringUtils.isNotBlank(require.getOutAuditType())){
				throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.needOAAudit.error");
			}else{
				if(StringUtils.isNotBlank(require.getWorkFlowNo()) && StringUtils.isNotBlank(require.getOutAuditType())){
					//ActivityUtil.deleteJob(require.getWorkFlowNo());
					require.setOutAuditType(null);
					this.updateOutAudit(require, param);
				}
			}
			BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getFinOrgUnitNo(), "PurRequire", param);
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),require, param);
				commonEventHistoryBiz.updateInvalid(require,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(require.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(require, commonAuditOpinionR, param);
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
						for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
							if (lineId == scmPurRequireEntry.getLineId()) {
								ScmAuditDetailHistory scmAuditDetailHistory = new ScmAuditDetailHistory(true);
								scmAuditDetailHistory.setBillId(scmPurRequireEntry.getPrId());
								scmAuditDetailHistory.setBillType("PurRequire");
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
				
				//驳回则将单据变成驳回状态
				require.setStatus("R");
				if (scmPurRequireEntryList != null && !scmPurRequireEntryList.isEmpty()) {
					for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntryList) {
						scmPurRequireEntry2.setRowStatus("R");
						scmPurRequireEntry2.setPurRequireEntryAudit(false);
						scmPurRequireEntryBiz.updateDirect(scmPurRequireEntry2, param);
					}
				}
				require.setChecker(null);
				require.setCheckDate(null);
				this.sendAuditMsg(require,"R",billType.getBillCode(), param);
				return this.update(require, param);
			}
			String processInstanceId = require.getWorkFlowNo();
			boolean isCompleteTask = true;
			boolean isFirstTask = false;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//是否首个任务
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				//完成审批任务
				updateProcessVariable(billType, require, detailList, param);
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion, param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			List<CommonBillEntryStatus> commonBillEntryStatusList = new ArrayList();
			
			//I：新建，D：待审，P：审核中，A：通过，B：部分通过，N：未通过，S：部分下达，E：下达，F：部分关闭，C：关闭，多个状态如：I,D,A
			if (scmPurRequireEntryList != null && scmPurRequireEntryList.size() > 0) {
				if ("agree".equals(opinion)) {
					if (isCompleteTask) {
						//A通过，B部分通过，N未通过
						if(detailList==null || detailList.isEmpty()) {
							//未传入明细则认为符合条件的明细都同意
							for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
								CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
								commonBillEntryStatus.setLineId(scmPurRequireEntry.getLineId());
								commonBillEntryStatus.setRowStatus(scmPurRequireEntry.getRowStatus());
								if(scmPurRequireEntry.isPurRequireEntryAudit() && StringUtils.contains("P,N",scmPurRequireEntry.getRowStatus())){
									if(isFirstTask){
										commonBillEntryStatus.setRowStatus("D");
									}else{
										commonBillEntryStatus.setRowStatus("P");
									}
								}
								commonBillEntryStatusList.add(commonBillEntryStatus);
								if(!StringUtils.equals(scmPurRequireEntry.getRowStatus(), "N")) {
									scmPurRequireEntry.setRowStatus("A");
								}else {
									countRefuse++;
								}
							}
							if (countRefuse == 0) {
								require.setStatus("A");
							} else if (countRefuse > 0 && countRefuse < scmPurRequireEntryList.size()) {
								require.setStatus("B");
							} else {
								require.setStatus("N");
							}
						}else {
							for (CommonAuditDetailParams commonAuditDetailParams : detailList) {
								String rowOpinion = commonAuditDetailParams.getRowOpinion();
								int lineId = commonAuditDetailParams.getLineId();
								for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
									if (lineId == scmPurRequireEntry.getLineId()) {
										CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
										commonBillEntryStatus.setLineId(scmPurRequireEntry.getLineId());
										commonBillEntryStatus.setRowStatus(scmPurRequireEntry.getRowStatus());
										if(scmPurRequireEntry.isPurRequireEntryAudit() && StringUtils.contains("P,N",scmPurRequireEntry.getRowStatus())){
											if(isFirstTask){
												commonBillEntryStatus.setRowStatus("D");
											}else{
												commonBillEntryStatus.setRowStatus("P");
											}
										}
										commonBillEntryStatusList.add(commonBillEntryStatus);
										if (!StringUtils.equals(scmPurRequireEntry.getRowStatus(), "N") &&
												(StringUtils.equals("agree",rowOpinion) || StringUtils.equals("Y",rowOpinion))) {
											scmPurRequireEntry.setRowStatus("A");
										} else {
											scmPurRequireEntry.setRowStatus("N");
											countRefuse++;
										}
										break;
									}
								}
							}
							if (countRefuse == 0) {
								require.setStatus("A");
							} else if (countRefuse > 0 && countRefuse < scmPurRequireEntryList.size()) {
								require.setStatus("B");
							} else {
								require.setStatus("N");
							}
						}
					} else {
						//P：审核中，N：未通过
						if(detailList==null || detailList.isEmpty()) {
							//未传入明细则认为符合条件的明细都同意
							for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
								CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
								commonBillEntryStatus.setLineId(scmPurRequireEntry.getLineId());
								commonBillEntryStatus.setRowStatus(scmPurRequireEntry.getRowStatus());
								if(scmPurRequireEntry.isPurRequireEntryAudit() && StringUtils.contains("P,N",scmPurRequireEntry.getRowStatus())){
									if(isFirstTask){
										commonBillEntryStatus.setRowStatus("D");
									}else{
										commonBillEntryStatus.setRowStatus("P");
									}
								}
								commonBillEntryStatusList.add(commonBillEntryStatus);
								if(!StringUtils.equals(scmPurRequireEntry.getRowStatus(), "N")) {
									scmPurRequireEntry.setRowStatus("P");
								}else {
									countRefuse++;
								}
							}
							if (countRefuse == scmPurRequireEntryList.size()) {
								require.setStatus("N");
							} else {
								require.setStatus("P");
							}	
						}else {
							for(CommonAuditDetailParams commonAuditDetailParams : detailList) {
								String rowOpinion = commonAuditDetailParams.getRowOpinion();
								int lineId = commonAuditDetailParams.getLineId();
								for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
									if (lineId == scmPurRequireEntry.getLineId()) {
										CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
										commonBillEntryStatus.setLineId(scmPurRequireEntry.getLineId());
										commonBillEntryStatus.setRowStatus(scmPurRequireEntry.getRowStatus());
										if(scmPurRequireEntry.isPurRequireEntryAudit() && StringUtils.contains("P,N",scmPurRequireEntry.getRowStatus())){
											if(isFirstTask){
												commonBillEntryStatus.setRowStatus("D");
											}else{
												commonBillEntryStatus.setRowStatus("P");
											}
										}
										commonBillEntryStatusList.add(commonBillEntryStatus);
										if (!StringUtils.equals(scmPurRequireEntry.getRowStatus(), "N") &&
												(StringUtils.equals("agree",rowOpinion) || StringUtils.equals("Y",rowOpinion))) {
											scmPurRequireEntry.setRowStatus("P");
										} else {
											scmPurRequireEntry.setRowStatus("N");
											countRefuse++;
										}
										break;
									}
								}
								
								if (countRefuse == scmPurRequireEntryList.size()) {
									require.setStatus("N");
								} else {
									require.setStatus("P");
								}	
							}
						}
					}
				} else {
					for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
						CommonBillEntryStatus commonBillEntryStatus = new CommonBillEntryStatus();
						commonBillEntryStatus.setLineId(scmPurRequireEntry.getLineId());
						commonBillEntryStatus.setRowStatus(scmPurRequireEntry.getRowStatus());
						if(scmPurRequireEntry.isPurRequireEntryAudit() && StringUtils.contains("P,N",scmPurRequireEntry.getRowStatus())){
							if(isFirstTask){
								commonBillEntryStatus.setRowStatus("D");
							}else{
								commonBillEntryStatus.setRowStatus("P");
							}
						}
						commonBillEntryStatusList.add(commonBillEntryStatus);
						scmPurRequireEntry.setRowStatus("N");
					}
					require.setStatus("N");
				}	
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), require, param);
			require.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				this.sendAuditMsg(require, "PurRequire", param);
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(require.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				require.setStepNo(stepNo);
				require.setCheckDate(CalendarUtil.getDate(param));
				this.update(require, param);
			} catch (Exception e) {
				throw e;
			}
			
			for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
				long prId = scmPurRequireEntry.getPrId();
				String status = scmPurRequireEntry.getRowStatus();
				String checker = param.getUsrCode();
				Date checkDate = CalendarUtil.getDate(param);
				int lineId = scmPurRequireEntry.getLineId();
				scmPurRequireEntryBiz.updateRowStatusByLineId(prId, status, checker, checkDate, lineId, param);
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			String aString=new String();
			if (StringUtils.equals("agree", commonAuditParams.getOpinion())) {
				commonAuditOpinion.setOpinion("Y");
			}else if (StringUtils.equals("refuse", commonAuditParams.getOpinion())) {
				commonAuditOpinion.setOpinion("N");
			}else {
				commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			}
			commonAuditOpinion.setHandlerContent(gson.toJson(commonBillEntryStatusList));
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(require, commonAuditOpinion, param);
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
					for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
						if (lineId == scmPurRequireEntry.getLineId()) {
							ScmAuditDetailHistory scmAuditDetailHistory = new ScmAuditDetailHistory(true);
							scmAuditDetailHistory.setBillId(scmPurRequireEntry.getPrId());
							scmAuditDetailHistory.setBillType("PurRequire");
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
			if(StringUtils.contains("A,B", require.getStatus())) {
				//通过或部分通过时将自动生成订货的物资生成订货单
				this.autoGenerateOrder(require,param);
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}

	private void autoGenerateOrder(ScmPurRequire2 require, Param param) {
		List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectAutoOrderListByPrId(require.getId(), param);
		if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()) {
			for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntryList) {
				scmPurRequireEntry.setRowStatus("E");
				scmPurRequireEntryBiz.updateDirect(scmPurRequireEntry, param);
			}
			List<ScmPurOrder2> scmPurOrderList = scmPurRequireEntryBiz.asynGenerateOrder(scmPurRequireEntryList,true, param);
			if(scmPurOrderList!=null && !scmPurOrderList.isEmpty()) {
				for(ScmPurOrder2 scmPurOrder:scmPurOrderList) {
					//自动触发提交
					scmPurOrderBiz.submit(scmPurOrder, param);
				}
			}
		}
	}

	@Override
	public ScmPurRequire2 doUnAuditPurRequire(ScmPurRequire2 scmPurRequire,
			Param param) throws AppException {
		ScmPurRequire2 require = null;
		List<ScmPurRequire2> scmPurRequireList = new ArrayList<> ();
		List<ScmPurRequireEntry2> scmPurRequireEntryList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurRequire.getId()>0){
			require = this.selectDirect(scmPurRequire.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurRequire2.FN_PRNO,
					new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,
							scmPurRequire.getPrNo()));
			map.put(ScmPurRequire2.FN_CONTROLUNITNO, new QueryParam(ScmPurRequire2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmPurRequireList =this.findAll(map, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				require=scmPurRequireList.get(0);
			}
		}
		
		if (require != null) {
			if(!StringUtils.contains("A,B,N,P", require.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(require.getId(), param);
			
			if(scmPurRequireEntryList == null || scmPurRequireEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if(StringUtils.isNotBlank(require.getOutAuditType())){
				if(StringUtils.contains("3,7", require.getOutAuditType()) && StringUtils.isNotBlank(require.getWorkFlowNo())){
					throw new AppException("iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.unDoOAAudit.error");
				}else{
					ActivityUtil.deleteJob(require.getWorkFlowNo());
					require.setOutAuditType(null);
					this.updateOutAudit(require, param);
				}
			}
	
			String processInstanceId = require.getWorkFlowNo();
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurRequireList.add(require);
				bean.setList(scmPurRequireList);
				bean.setList2(scmPurRequireEntryList);
				
				BillType2 billType = billTypeBiz.selectByOrgAndCode(require.getFinOrgUnitNo(), "PurRequire", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(), param);
				require.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String tableName = ClassUtils.getFinalModelSimpleName(require);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,require.getStepNo(),require.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getHandlerContent())) {
				List<CommonBillEntryStatus> commonBillEntryStatusList = gson.fromJson(commonEventHistory.getHandlerContent(),new TypeToken<List<CommonBillEntryStatus>>() {}.getType());
				if(commonBillEntryStatusList!=null && !commonBillEntryStatusList.isEmpty()) {
					for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
						for(CommonBillEntryStatus commonBillEntryStatus:commonBillEntryStatusList) {
							if(commonBillEntryStatus.getLineId()==scmPurRequireEntry.getLineId()) {
								scmPurRequireEntry.setRowStatus(commonBillEntryStatus.getRowStatus());
								commonBillEntryStatusList.remove(commonBillEntryStatus);
								break;
							}
						}
					}
				}
				if(StringUtils.isNotBlank(commonEventHistory.getPreStepNo())){
					CommonEventHistory preCommonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,require.getStepNo(),require.getPK(),param);
					if(preCommonEventHistory!=null) {
						require.setChecker(preCommonEventHistory.getOper());
						require.setCheckDate(preCommonEventHistory.getOperDate());
					}else {
						require.setChecker(null);
						require.setCheckDate(null);
					}
				}else {
					require.setChecker(null);
					require.setCheckDate(null);
				}
			}
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			require.setStatus(status);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		try {
			this.update(require, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),require, param);
			commonEventHistoryBiz.updateInvalid(require,require.getStepNo(),param);
		} catch (Exception e) {
			throw e;
		}
		
		for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
			long prId = scmPurRequireEntry.getPrId();
			String status = scmPurRequireEntry.getRowStatus();
			String checker = param.getUsrCode();
			Date checkDate = CalendarUtil.getDate(param);
			int lineId = scmPurRequireEntry.getLineId();
			scmPurRequireEntryBiz.updateRowStatusByLineId(prId, status, checker, checkDate, lineId, param);
		}
		CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
		commonAuditOpinion.setStepNo(require.getStepNo());
		commonAuditOpinion.setActiveType("U");
		commonAuditOpinion.setOpinion("Y");
		commonEventHistoryBiz.addEventHistory(require, commonAuditOpinion, param);
		return require;
	}

	@Override
	public ScmPurRequire2 doRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire2 = null;
		if(scmPurRequire.getId()>0){
			scmPurRequire2 = this.selectDirect(scmPurRequire.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,scmPurRequire.getPrNo()));
			
			List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				scmPurRequire2=scmPurRequireList.get(0);
			}
		}
		if(scmPurRequire2!=null){
			if(!scmPurRequire2.getStatus().equals("A") && !scmPurRequire2.getStatus().equals("P")){
				throw new AppException("iscm.purchasemanage.error.release");
			}else {
				if(scmPurRequire2.getStatus().equals("A")){
					scmPurRequire2.setStatus("E");
				}else {
					scmPurRequire2.setStatus("S");
				}
				try{
					this.updateDirect(scmPurRequire2, param);
					scmPurRequireEntryBiz.doRelease(scmPurRequire2, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurRequire2;
	}

	@Override
	public ScmPurRequire2 doUndoRelease(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire2 = null;
		if(scmPurRequire.getId()>0){
			scmPurRequire2 = this.selectDirect(scmPurRequire.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,scmPurRequire.getPrNo()));
			
			List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				scmPurRequire2=scmPurRequireList.get(0);
			}
		}
		if(scmPurRequire2!=null){
			if(!StringUtils.contains("E,S", scmPurRequire2.getStatus())){
				throw new AppException("iscm.purchasemanage.error.cancelRelease2");
			}else{
				if(StringUtils.equals("E",scmPurRequire2.getStatus())){
					scmPurRequire2.setStatus("A");
				}else {
					scmPurRequire2.setStatus("B");
				}
				try{
					this.updateDirect(scmPurRequire2, param);
					scmPurRequireEntryBiz.doUndoRelease(scmPurRequire2, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurRequire2;
	}

	@Override
	public ScmPurRequire2 finish(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 purBill = null;
		if(scmPurRequire.getId()>0){
			purBill = this.selectDirect(scmPurRequire.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,scmPurRequire.getPrNo()));
			
			List<ScmPurRequire2> scmBillList =this.findPage(page, param);
			if(scmBillList!=null && !scmBillList.isEmpty()){
				purBill=scmBillList.get(0);
			}
		}
		if(purBill!=null){
			if(!purBill.getStatus().equals("E")){
				throw new AppException("iscm.purchasemanage.error.finish");
			}else if(purBill.getStatus().equals("E")){
				if(param.getUsrCode()!=null ){
					purBill.setChecker(param.getUsrCode());
					purBill.setCheckDate(CalendarUtil.getDate(param));
				}
				purBill.setStatus("C");
				try{
					this.updateStatus(purBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purBill;	}

	@Override
	public ScmPurRequire2 undoFinish(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 purBill = null;
		if(scmPurRequire.getId()>0){
			purBill = this.selectDirect(scmPurRequire.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,scmPurRequire.getPrNo()));
			
			List<ScmPurRequire2> scmBillList =this.findPage(page, param);
			if(scmBillList!=null && !scmBillList.isEmpty()){
				purBill=scmBillList.get(0);
			}
		}
		if(purBill!=null){
			if(!purBill.getStatus().equals("C")){
				throw new AppException("iscm.purchasemanage.error.cancelFinish");
			}else if(purBill.getStatus().equals("C")){
				if(param.getUsrCode()!=null ){
					purBill.setChecker(param.getUsrCode());
					purBill.setCheckDate(CalendarUtil.getDate(param));
				}
				purBill.setStatus("E");
				try{
					this.updateStatus(purBill, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purBill;
	}

	@Override
	public List<ScmPurchaseType2> queryPurchaseType(
			PurchaseTypeParams purchaseTypeParams, Param param)
			throws AppException {
		String purOrgUnitNo = purchaseTypeParams.getPurOrgUnitNo();
		if(StringUtils.isBlank(purOrgUnitNo))
			throw new AppException("iscm.RequireController.queryPurReqPurOrgUnit.reqOrgUnitNo.isnull");
	
		int pageNum = 0;
		Page page = new Page();
		page.setModelClass(ScmPurchaseType2.class);
		List<String> arglist = new ArrayList<>();
		arglist.add("orgUnitNo="+purOrgUnitNo);
		arglist.add("controlUnitNo="+param.getControlUnitNo());
		
		return scmPurchaseTypeBiz.queryPage(page, arglist, "selectByOrgPage", param);
	}

	@Override
	public Map selectPurRequireTotalAmt(long requireId, Param param) {
		if (requireId > 0) {
			HashMap<String, Object> map = new HashMap<>();
			map.put("requireId", requireId);
			return ((ScmPurRequireDAO) dao).selectPurRequireTotalAmt(map);
		}
		return null;
	}

	@Override
	public void updateOutAudit(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		if(scmPurRequire != null){
			HashMap<String, Object> map = new HashMap<>();
			map.put("id", scmPurRequire.getId());
			map.put("outAuditType", scmPurRequire.getOutAuditType());
			map.put("otherAuditNo", scmPurRequire.getOtherAuditNo());
			((ScmPurRequireDAO) dao).updateOutAudit(map);
		}
	}

	@Override
	public List<ScmPurBillDrillResult> selectDrillBills(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		if(scmPurRequire != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("prId", scmPurRequire.getId());
			System.out.println("req:"+((ScmPurRequireDAO)dao).selectDrillBills(map).get(0).getWrNo());
			return ((ScmPurRequireDAO)dao).selectDrillBills(map);
		}
		return null;
	}

	@Override
	public List<ScmPurRequire2> selectByIds(String ids, Param param) throws AppException {
		if (StringUtils.isNotEmpty(ids)) {
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("ids", ids);
			return ((ScmPurRequireDAO)dao).selectByIds(map);
		}
		return null;
	}

	@Override
	public CommonBean doEntryAudit(List<ScmPurRequireEntry2> list,CommonAuditParams commonAuditParams, Param createParam) throws AppException {
		CommonBean bean = new CommonBean();
		if (list != null && list.size()>0) {
			List<ScmPurRequireEntry2> scmPurRequireEntryListGroupByid = list.stream().filter(distinctByKey(ScmPurRequireEntry2 :: getPrId)).collect(Collectors.toList());
			StringBuffer prids=new StringBuffer("");//请购单ID
			StringBuffer msg=new StringBuffer("");
			List<ScmPurRequire2> scmPurRequire2List=new ArrayList<>();//选择单据的所有单据
			List<ScmPurRequireEntry2> scmPurRequireEntry2All=new ArrayList<>();//选择单据的所有明细
			if (scmPurRequireEntryListGroupByid != null && !scmPurRequireEntryListGroupByid.isEmpty()) {
				for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntryListGroupByid) {
					prids.append(scmPurRequireEntry2.getPrId()).append(",");
				}
			}
			prids.deleteCharAt(prids.length()-1);
			if (StringUtils.isNotEmpty(prids.toString())) {
				scmPurRequireEntry2All=scmPurRequireEntryBiz.selectByPrIds(prids.toString(), createParam);
				scmPurRequire2List=selectByIds(prids.toString(),createParam);
			}
			if (StringUtils.equals("R", commonAuditParams.getOpinion())) {//判断审批状态
				for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntry2All) {
					scmPurRequireEntry2.setRowStatus("I");
					scmPurRequireEntry2.setChecker(createParam.getUsrCode());
					scmPurRequireEntry2.setCheckDate(new Date());
				}
				scmPurRequireEntryBiz.update(scmPurRequireEntry2All, createParam);//将驳回明细的单据全部驳回--更新至明细记录
				
				//调用doAuditPurRequire方法驳回审批（所有单据）
				List<CommonAuditDetailParams> detailList = new ArrayList();
				for (ScmPurRequire2 scmPurRequire2 : scmPurRequire2List) {
					msg.append(Message.getMessage(createParam.getLang(),"ScmBillPendingRequireFormController.billReturn",new String[] {scmPurRequire2.getPrNo()})).append("\n");
					detailList.clear();
					for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntry2All){
						if (scmPurRequireEntry.getPrId() == scmPurRequire2.getId()) {
							CommonAuditDetailParams sommonAuditDetailParams = new CommonAuditDetailParams();
							sommonAuditDetailParams.setLineId(scmPurRequireEntry.getLineId());
							sommonAuditDetailParams.setRowOpinion(scmPurRequireEntry.getOpinion());
							detailList.add(sommonAuditDetailParams);
						}
                    }
					commonAuditParams.setBillId(scmPurRequire2.getId());
					commonAuditParams.setBillNo(scmPurRequire2.getPrNo());
					doAuditPurRequire(commonAuditParams, createParam);
				}
				//返回结果告诉用户驳回成功
				
			}else {
				//1、审批状态更新至明细记录
				StringBuffer errMsg = new StringBuffer("");
				HashMap<String,Object> myTaskActivityMap = new HashMap<String,Object>();
				for (ScmPurRequireEntry2 scmPurRequireEntry2 : list) {
					if (StringUtils.equals("Y", commonAuditParams.getOpinion())) {
						ScmPurRequire2 tempScmPurRequire = null;
						for (ScmPurRequire2 scmPurRequire2 : scmPurRequire2List) {
							if (scmPurRequireEntry2.getPrId() == scmPurRequire2.getId()) {
								tempScmPurRequire = scmPurRequire2;
							}
						}
						if(tempScmPurRequire != null && StringUtils.isNotBlank(tempScmPurRequire.getWorkFlowNo())){
							String errMessage = ActivityUtil.checkActivity(scmPurRequireEntry2,tempScmPurRequire.getWorkFlowNo(),"【"+scmPurRequireEntry2.getPrNo()+"】"+scmPurRequireEntry2.getItemName(),myTaskActivityMap,createParam);
							if(StringUtils.isNotBlank(errMessage)){
								if(StringUtils.isNotBlank(errMsg.toString())){
									errMsg.append("\n");
								}
								errMsg.append(errMessage);
							}
						}
						scmPurRequireEntry2.setRowStatus("P");
					}else if(StringUtils.equals("N", commonAuditParams.getOpinion())){
						scmPurRequireEntry2.setRowStatus("N");
					}
					scmPurRequireEntry2.setPurRequireEntryAudit(true);
					scmPurRequireEntry2.setChecker(createParam.getUsrCode());
					scmPurRequireEntry2.setCheckDate(new Date());
				}
				if(StringUtils.isNotBlank(errMsg.toString())){
					throw new AppException(errMsg.toString());
				}
				scmPurRequireEntryBiz.update(list, createParam);//将审批记录更新至明细记录
				Page page = new Page();
				page.setModelClass(ScmPurRequireEntry2.class);
				page.setShowCount(Integer.MAX_VALUE);
				ArrayList<String> argList = new ArrayList<>();
				argList.add("prIds="+prids);
				argList.add("rowStatus='P','D'");
				argList.add("purRequireEntryAudit=0");
				//2、判断单据的明细数据是否全部审核--将审核通过的单据（主单）放入list---根据单据id查询行状态为待审的单据--没有数据则说明单据为全部审批
				List<ScmPurRequireEntry2> scmPurRequireEntryListGroupByid1=null;
				List<ScmPurRequireEntry2> scmPurRequireEntry2s = scmPurRequireEntryBiz.queryPage(page, argList, "selectByPrIds", createParam);//未审批完的单据
				if (scmPurRequireEntry2s !=null && scmPurRequireEntry2s.size()>0) {
					scmPurRequireEntryListGroupByid1 = scmPurRequireEntry2s.stream().filter(distinctByKey(ScmPurRequireEntry2 :: getPrId)).collect(Collectors.toList());
				}
				StringBuffer noAuditPrids=new StringBuffer("");//未审批完请购单ID
				if (scmPurRequireEntryListGroupByid1 != null && !scmPurRequireEntryListGroupByid1.isEmpty()) {
					for (ScmPurRequireEntry2 scmPurRequireEntry2 : scmPurRequireEntryListGroupByid1) {
						noAuditPrids.append(scmPurRequireEntry2.getPrId()).append(",");
						msg.append(Message.getMessage(createParam.getLang(),"ScmBillPendingRequireFormController.entryNoAllSuccess",new String[] {scmPurRequireEntry2.getPrNo()}));
						msg.append("\n");
					}
				}
				if (StringUtils.isNotEmpty(noAuditPrids.toString())) {
					prids.delete(0, prids.length());
					for (ScmPurRequire2 scmPurRequire2 : scmPurRequire2List) {
						if (!noAuditPrids.toString().contains(String.valueOf(scmPurRequire2.getId()))) {
							prids.append(scmPurRequire2.getId()).append(",");
						}
					}
					if (StringUtils.isNotEmpty(prids.toString())) {
						prids.deleteCharAt(prids.length()-1);
					}else {//没有整单明细审完的单据
						bean.setObject(msg.toString());
						return bean;
					}
				}
				if (StringUtils.isNotEmpty(prids.toString())) {
					scmPurRequireEntry2All=scmPurRequireEntryBiz.selectByPrIds(prids.toString(), createParam);
					scmPurRequire2List=selectByIds(prids.toString(),createParam);
				}
				if (scmPurRequire2List != null && scmPurRequire2List.size()>0) {
					for (ScmPurRequire2 scmPurRequire2 : scmPurRequire2List) {
						msg.append(Message.getMessage(createParam.getLang(), "ScmBillPendingRequireFormController.entrySuccess",new String[] {scmPurRequire2.getPrNo()}));
						msg.append("\n");
					}
				}
				//3、list循环调用doAuditPurRequire方法审批通过
				List<CommonAuditDetailParams> detailList = new ArrayList();
				for (ScmPurRequire2 scmPurRequire2 : scmPurRequire2List) {
					detailList.clear();
					for(ScmPurRequireEntry2 scmPurRequireEntry:scmPurRequireEntry2All){
						if (scmPurRequireEntry.getPrId() == scmPurRequire2.getId()) {
							CommonAuditDetailParams sommonAuditDetailParams = new CommonAuditDetailParams();
							sommonAuditDetailParams.setLineId(scmPurRequireEntry.getLineId());
							if(StringUtils.equals("agree",scmPurRequireEntry.getOpinion()) || StringUtils.equals("Y",scmPurRequireEntry.getOpinion())){
								commonAuditParams.setOpinion("Y");
							}
							sommonAuditDetailParams.setRowOpinion(scmPurRequireEntry.getOpinion());
							detailList.add(sommonAuditDetailParams);
						}
                    }
					commonAuditParams.setBillId(scmPurRequire2.getId());
					commonAuditParams.setBillNo(scmPurRequire2.getPrNo());
					doAuditPurRequire(commonAuditParams, createParam);
				}
				//4、list里的对象告诉用户审批通过、其他的则提示用户还有明细记录没有审批
			}
			bean.setObject(msg.toString());
			return bean;
		}
		return null;
	}
	private static <T> Predicate<? super BaseModel> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply((T) t), Boolean.TRUE) == null;
    }

	@Override
	public void deleteTemplete(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		ScmPurRequire2 delScmPurRequire = this.selectDirect(scmPurRequire.getId(), param);
		if(delScmPurRequire!=null && delScmPurRequire.isTemplete()) {
			this.delete(delScmPurRequire, param);
		}
	}
	
	@Override
	public void setConvertMapBiz(ScmPurRequire2 scmPurRequire, Param param) throws AppException{
		setConvertMap(scmPurRequire, param);
	}

	@Override
	public ScmPurRequire2 queryTemplete(ScmPurRequire2 scmPurrequire, Param param) throws AppException {
		ScmPurRequire2 scmPurRequire2 = new ScmPurRequire2();
		if(scmPurrequire.getId()>0) {
			scmPurRequire2 = this.select(scmPurrequire.getId(), param);
		}else {
			Page page=new Page();
			page.setModelClass(ScmPurRequire2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurRequire2.FN_PRNO,new QueryParam(ScmPurRequire2.FN_PRNO, QueryParam.QUERY_EQ,scmPurrequire.getPrNo()));
			
			List<ScmPurRequire2> scmPurRequireList =this.findPage(page, param);
			if(scmPurRequireList!=null && !scmPurRequireList.isEmpty()){
				scmPurRequire2 = scmPurRequireList.get(0);
			}
		}
		if(scmPurRequire2==null) {
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
        // 获取明细
        List<ScmPurRequireEntry2> scmPurRequireEntryList = scmPurRequireEntryBiz.selectByPrId(scmPurRequire2.getId(), param);
        if (scmPurRequireEntryList == null || scmPurRequireEntryList.isEmpty()) {
            return null;
        }
        List<ScmPurRequireEntry2> scmPurRequireEntryResultList = new ArrayList<>();
        int pricePrec = Integer.valueOf(sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PricePrecision", "2", param));	//单价精度 
        String editPrice = sysParamBiz.getValue(scmPurRequire2.getPurOrgUnitNo(), "SCM_EditPrice", "Y", param);
		String editVendor = sysParamBiz.getValue(scmPurRequire2.getPurOrgUnitNo(), "SCM_EditReqVendor", "N", param);
        scmPurRequire2.setCreator(param.getUsrCode());
        scmPurRequire2.setApplicants(param.getUsrCode());
        scmPurRequire2.setCreateDate(CalendarUtil.getDate(param));
        scmPurRequire2.setApplyDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())));
        scmPurRequire2.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
        scmPurRequire2.setStatus("I");
        scmPurRequire2.setTaxAmt(BigDecimal.ZERO);
        scmPurRequire2.setTemplete(false);
        
        List<OrgStorage2> invOrglist = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.ADMINTOINV, param.getOrgUnitNo(), false, null, param);
        String invOrgUnitNo="";
        if(invOrglist!=null && !invOrglist.isEmpty()){
            boolean exists = false;
            for(OrgStorage2 orgStorage:invOrglist){
                if(orgStorage.isDefault()){
                    if(!StringUtils.equals(invOrgUnitNo, orgStorage.getOrgUnitNo()))
                        invOrgUnitNo=orgStorage.getOrgUnitNo();
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                if(!StringUtils.equals(invOrgUnitNo, invOrglist.get(0).getOrgUnitNo()))
                    invOrgUnitNo = invOrglist.get(0).getOrgUnitNo();
            }
        }
        String priceType = sysParamBiz.getValue(scmPurRequire2.getPurOrgUnitNo(), "SCM_SelectType", "1", param);
        Date priceDate = scmPurRequire2.getApplyDate();
    	if(StringUtils.equals("2", priceType))
    		priceDate = scmPurRequire2.getReqDate();
        // 明细
        if(scmPurRequireEntryList!=null && !scmPurRequireEntryList.isEmpty()) {
	        for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntryList) {
	            ScmPurRequireEntry2 scmPurRequireEntryResult = new ScmPurRequireEntry2(true);
	            BeanUtil.copyProperties(scmPurRequireEntryResult, scmPurRequireEntry);
	            scmPurRequireEntryResult.setId(0);
	            //待取供应商
		        
	            long vendorId=0;
				ScmPurSupplyInfo2 scmPurSupplyInfo = scmPurSupplyInfoBiz.getSupplyInfoByItem(
						scmPurRequire2.getPurOrgUnitNo(), invOrgUnitNo, scmPurRequireEntry.getItemId(), priceDate,
						param);
				if (scmPurSupplyInfo != null) {
		        	vendorId = scmPurSupplyInfo.getVendorId();
		        	scmPurRequireEntryResult.setDirectPurchase(scmPurSupplyInfo.isDirectPurchase());
				}
		        scmPurRequireEntryResult.setVendorId(vendorId);
		        String priceBillId = "";
				ScmMaterialPrice scmMaterialPrice = ScmMaterialUtil.getMaterialPrice(scmPurRequire2.getPurOrgUnitNo(),
						vendorId, String.valueOf(scmPurRequireEntry.getItemId()), scmPurRequireEntry.getPurUnit(),
						priceDate, scmPurRequireEntry.getRecStorageOrgUnitNo(), priceBillId, param);
				if(scmMaterialPrice!=null && !StringUtils.equals("0", scmMaterialPrice.getRefPriceStatus())){
					scmPurRequireEntryResult.setPrice(scmMaterialPrice.getTaxPrice().setScale(pricePrec, RoundingMode.HALF_UP));
					scmPurRequireEntryResult.setPriceBillId(scmMaterialPrice.getPriceBillId());
					scmPurRequireEntryResult.setRefPriceStatus(scmMaterialPrice.getRefPriceStatus());
					if(scmMaterialPrice.getVendorId()>0){
						//定价且绑定供应商
						scmPurRequireEntryResult.setVendorId(scmMaterialPrice.getVendorId());
					}
				} else {
		            scmPurRequireEntryResult.setPrice(BigDecimal.ZERO);
				}
				if (scmPurRequireEntryResult.getVendorId() > 0) {
		            // 供应商
		            Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurRequireEntryResult.getVendorId(), param);
		            if (scmsupplier != null) {
		            	scmPurRequireEntryResult.setConvertMap(ScmPurRequireEntry2.FN_VENDORID,scmsupplier);
		            	scmPurRequireEntryResult.setVendorName(scmsupplier.getVendorName());
		            }
		        }
				scmPurRequireEntryResult.setAmt(BigDecimal.ZERO);
				scmPurRequireEntryResult.setQty(BigDecimal.ZERO);
	            scmPurRequireEntryResult.setRowStatus("I");
	            scmPurRequireEntryResult.setReqDate(CalendarUtil.relativeDate(FormatUtils.parseDate(FormatUtils.fmtDate(new Date())), 1));
	            scmPurRequireEntryBiz.setConvertMap(scmPurRequireEntryResult,param);
            	StringBuffer editColumn = new StringBuffer("Q");
				if (StringUtils.equals(editPrice, "Y")) {
                    if (scmPurRequireEntry.getPriceBillId() <= 0 || (scmPurRequireEntry.getPriceBillId() > 0 && "1".equals(scmPurRequireEntry.getRefPriceStatus()))) {
                        //报价单生效也可改单价
                    	editColumn.append(",").append("P");
                    }
                }
				if (StringUtils.equals(editVendor, "Y")) {
					if (!StringUtils.contains("5,3,6,7", scmPurRequireEntry.getRefPriceStatus())) {
						editColumn.append(",").append("V");
					}
				}
				scmPurRequireEntryResult.setEditColumn(editColumn.toString());
	            scmPurRequireEntryResultList.add(scmPurRequireEntryResult);
	        }
        }
        scmPurRequire2.setScmPurRequireEntryList(scmPurRequireEntryResultList);
        return scmPurRequire2;
    }

	@Override
	public ScmPurRequire2 selectByTypeCode(String code, Param param) throws AppException {
		if (StringUtils.isNotBlank(code)) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("bizType", code);
			map.put("controlUnitNo", param.getControlUnitNo());
			return ((ScmPurRequireDAO)dao).selectByTypeCode(map);
		}
		return null;
	}

	@Override
	public ScmPurRequire2 updatePrtCount(ScmPurRequire2 scmPurRequire, Param param) throws AppException {
		if(scmPurRequire.getId()>0){
			ScmPurRequire2 bill = this.selectDirect(scmPurRequire.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurRequire;
	}

	@Override
	public ScmPurRequire2 selectByPrNo(String billNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("prNo", billNo);
		return ((ScmPurRequireDAO)dao).selectByPrNo(map);
	}
}