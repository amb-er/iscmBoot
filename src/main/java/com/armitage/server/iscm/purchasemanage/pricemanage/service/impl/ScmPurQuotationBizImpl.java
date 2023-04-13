package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.purquotation.params.PurQuotaionDetailParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationAddParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationEditParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationListParams;
import com.armitage.server.api.business.purquotation.params.PurQuotationParams;
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
import com.armitage.server.common.util.BeanUtil;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.PubSysBasicInfo;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup;
import com.armitage.server.iscm.basedata.service.PubSysBasicInfoBiz;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurQuotationDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmMaterialPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmBaseAttachment;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequireEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmBaseAttachmentBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurRequireEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.Employee;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.EmployeeBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.service.SysParamBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.springframework.stereotype.Service;

@Service("scmPurQuotationBiz")
public class ScmPurQuotationBizImpl extends BaseBizImpl<ScmPurQuotation2> implements ScmPurQuotationBiz {

	private UsrBiz usrBiz;
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private ScmPurQuotationEntryBiz scmPurQuotationEntryBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private PubSysBasicInfoBiz pubSysBasicInfoBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private EmployeeBiz employeeBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private CodeBiz codeBiz;
	private SysParamBiz sysParamBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmPurRequireEntryBiz scmPurRequireEntryBiz;
	private ScmBaseAttachmentBiz scmBaseAttachmentBiz;
	private ScmPurPriceBiz scmPurPriceBiz;
	
	public EmployeeBiz getEmployeeBiz() {
		return employeeBiz;
	}

	public void setEmployeeBiz(EmployeeBiz employeeBiz) {
		this.employeeBiz = employeeBiz;
	}

	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setPubSysBasicInfoBiz(PubSysBasicInfoBiz pubSysBasicInfoBiz) {
		this.pubSysBasicInfoBiz = pubSysBasicInfoBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setScmPurQuotationEntryBiz(ScmPurQuotationEntryBiz scmPurQuotationEntryBiz) {
		this.scmPurQuotationEntryBiz = scmPurQuotationEntryBiz;
	}
	
	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}
	
	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}
	
	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}
	
	public void setSysParamBiz(SysParamBiz sysParamBiz) {
		this.sysParamBiz = sysParamBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmPurRequireEntryBiz(ScmPurRequireEntryBiz scmPurRequireEntryBiz) {
		this.scmPurRequireEntryBiz = scmPurRequireEntryBiz;
	}

	public void setScmBaseAttachmentBiz(ScmBaseAttachmentBiz scmBaseAttachmentBiz) {
		this.scmBaseAttachmentBiz = scmBaseAttachmentBiz;
	}

	public void setScmPurPriceBiz(ScmPurPriceBiz scmPurPriceBiz) {
		this.scmPurPriceBiz = scmPurPriceBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." + ScmPurQuotation2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." + ScmPurQuotation2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." + ScmPurQuotation2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." + ScmPurQuotation2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmPurQuotation2 scmPurQuotation : (List<ScmPurQuotation2>)list){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurQuotation.getId(), "PurQuotation",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmPurQuotation.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmPurQuotation.setPendingUsrName(usrName.toString());
				this.setConvertMap(scmPurQuotation, param);
				if("I,R".contains(scmPurQuotation.getStatus())) {
					scmPurQuotation.setPendingUsrName("");
				}
			}
		}
	}

	private void setConvertMap(ScmPurQuotation2 scmPurQuotation,Param param) {
		if(scmPurQuotation!=null) {
			if(StringUtils.isNotBlank(scmPurQuotation.getOrgUnitNo())){
				//采购组织
				OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurQuotation.getOrgUnitNo(), param);
				if (orgBaseUnit != null) {
					scmPurQuotation.setConvertMap(ScmPurQuotation2.FN_ORGUNITNO, orgBaseUnit);
					scmPurQuotation.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
			if (scmPurQuotation.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurQuotation.getBuyerId(), param);
				if (scmPurBuyer != null) {
					scmPurQuotation.setConvertMap(ScmPurQuotation2.FN_BUYERID, scmPurBuyer);
					scmPurQuotation.setBuyerCode(scmPurBuyer.getBuyerCode());
					scmPurQuotation.setBuyerName(scmPurBuyer.getBuyerName());
				}
			}
			if (StringUtils.isNotBlank(scmPurQuotation.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmPurQuotation.getCreator(), param);
				if (usr != null) {
					if(scmPurQuotation.getDataDisplayMap()==null){
						scmPurQuotation.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurQuotation.getDataDisplayMap().put(ScmPurQuotation2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
					scmPurQuotation.setCreatorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmPurQuotation.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmPurQuotation.getEditor(), param);
				if (usr != null) {
					scmPurQuotation.setConvertMap(ScmPurQuotation2.FN_EDITOR, usr);
					scmPurQuotation.setEditorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmPurQuotation.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmPurQuotation.getChecker(), param);
				if (usr != null) {
					scmPurQuotation.setConvertMap(ScmPurQuotation2.FN_CHECKER, usr);
					scmPurQuotation.setCheckerName(usr.getName());
				}
			}
			if(StringUtils.isNotBlank(scmPurQuotation.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurQuotation.getStatus());
				if(code!=null)
					scmPurQuotation.setStatusName(code.getName());
			}
			if(scmPurQuotation.getVendorId() > 0){
				//供应商
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurQuotation.getVendorId(), param);
				if(scmsupplier != null){
					scmPurQuotation.setConvertMap(ScmPurQuotation2.FN_VENDORID, scmsupplier);
					scmPurQuotation.setVendorCode(scmsupplier.getVendorNo());
					scmPurQuotation.setVendorName(scmsupplier.getVendorName());
				}
			}
		
		
		}
	}
	@Override
	protected void afterSelect(ScmPurQuotation2 entity, Param param) throws AppException {
		if (entity != null){
			HashMap<String,Object> cacheMap = new HashMap<String,Object>();
			if(entity.getVendorId() > 0){
				//供应商
				Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(entity.getVendorId(), param);
				if(scmsupplier != null){
					entity.setConvertMap(ScmPurQuotation2.FN_VENDORID, scmsupplier);
				}
			}
			if (entity.getBuyerId() > 0){
				//采购员
				ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(entity.getBuyerId(), param);
				if (scmPurBuyer != null) {
					entity.setConvertMap(ScmPurQuotation2.FN_BUYERID, scmPurBuyer);
				}
			}
			if (StringUtils.isNotBlank(entity.getCreator())){
				//制单人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getCreator());
				if(usr==null){
					usr = usrBiz.selectByCode(entity.getCreator(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getCreator(),usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmPurQuotation2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(entity.getEditor())){
				//修改人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getEditor());
				if(usr==null){
					usr = usrBiz.selectByCode(entity.getEditor(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getEditor(),usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmPurQuotation2.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(entity.getChecker())){
				//审核人
				Usr usr = (Usr) cacheMap.get(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getChecker());
				if(usr==null){
					usr = usrBiz.selectByCode(entity.getChecker(), param);
					cacheMap.put(ClassUtils.getFinalModelSimpleName(Usr.class)+"_"+entity.getChecker(),usr);
				}
				if (usr != null) {
					entity.setConvertMap(ScmPurQuotation2.FN_CHECKER, usr);
				}
			}
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) bean.getList().get(0);
		HashMap<String,Object> cacheMap = new HashMap<String,Object>();
		if(scmPurQuotation != null && scmPurQuotation.getId() > 0){
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList = scmPurQuotationEntryBiz.selectByPqId(scmPurQuotation.getId(), param);
			if(scmPurQuotationEntryList != null && !scmPurQuotationEntryList.isEmpty()){
				for(ScmPurQuotationEntry2 scmPurQuotationEntry : scmPurQuotationEntryList){
					if (scmPurQuotationEntry.getTaxRate() != null){
						//税率
						PubSysBasicInfo pubSysBasicInfo = (PubSysBasicInfo) cacheMap.get(ClassUtils.getFinalModelSimpleName(PubSysBasicInfo.class)+"_"+scmPurQuotationEntry.getTaxRate());
						if(pubSysBasicInfo==null){
							pubSysBasicInfo = pubSysBasicInfoBiz.selectByTaxRate("TaxRate", scmPurQuotationEntry.getTaxRate().toString(), null, param);
							cacheMap.put(ClassUtils.getFinalModelSimpleName(PubSysBasicInfo.class)+"_"+scmPurQuotationEntry.getTaxRate(),pubSysBasicInfo);
						}
						if (pubSysBasicInfo != null) {
							scmPurQuotationEntry.setTaxRateStr(pubSysBasicInfo.getFInfoNo());
							scmPurQuotationEntry.setConvertMap(ScmPurQuotationEntry2.FN_TAXRATESTR, pubSysBasicInfo);
						}
					}
				}
				bean.setList2(scmPurQuotationEntryList);
			}
		}
	}

	@Override
	protected void beforeAdd(ScmPurQuotation2 entity, Param param) throws AppException {
		if(entity != null && StringUtils.isBlank(entity.getPqNo())){
			String code = CodeAutoCalUtil.getBillCode("PurQuotation", entity, param);
			entity.setPqNo(code);
			//获取财务组织以及期间、币别
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			entity.setCurrencyNo(orgCompanyList.get(0).getBaseCurrency());
			entity.setExchangeRate(BigDecimal.ONE);
			entity.setCreateDate(CalendarUtil.getDate(param));
			if(StringUtil.isBlank(entity.getCreator()))
				entity.setCreator(param.getUsrCode());
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) bean.getList().get(0);
		if(scmPurQuotation != null && scmPurQuotation.getId() > 0){
			//新增报价明细
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList = bean.getList2();
			if(scmPurQuotationEntryList != null && !scmPurQuotationEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurQuotationEntry2 scmPurQuotationEntry : scmPurQuotationEntryList){
					scmPurQuotationEntry.setLineId(lineId);
					scmPurQuotationEntry.setPqId(scmPurQuotation.getId());
					scmPurQuotationEntryBiz.add(scmPurQuotationEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurQuotation2 scmPurQuotation = (ScmPurQuotation2) bean.getList().get(0);
		if(scmPurQuotation != null && scmPurQuotation.getId() > 0){
			//更新报价明细
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList = bean.getList2();
			if(scmPurQuotationEntryList != null && !scmPurQuotationEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurQuotationEntry2 scmPurQuotationEntry : scmPurQuotationEntryList){
					if (StringUtils.equals("I", scmPurQuotation.getStatus())) {
						scmPurQuotationEntry.setLineId(lineId);
					}
					scmPurQuotationEntry.setPqId(scmPurQuotation.getId());
					lineId = lineId+1;
				}
				scmPurQuotationEntryBiz.update(scmPurQuotation, scmPurQuotationEntryList, ScmPurQuotationEntry2.FN_PQID, param);
			}
		}
	}
	
	@Override
	protected void beforeDelete(ScmPurQuotation2 entity, Param param) throws AppException {
		ScmPurQuotation2 scmPurQuotation = this.selectDirect(entity.getId(), param);
		if(scmPurQuotation!=null && !StringUtils.equals(entity.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getPqNo()}));
		}
	}
	
	@Override
	protected void afterDelete(ScmPurQuotation2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除报价明细
			scmPurQuotationEntryBiz.deleteByPqId(entity.getId(), param);
			//删除附件
			scmBaseAttachmentBiz.update(entity,null,ScmBaseAttachment.FN_BILLID,param);
		}
	}

	private ScmPurQuotation2 updateStatus(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException {
		if(scmPurQuotation != null){
			ScmPurQuotation2 scmPurQuotation2 = this.updateDirect(scmPurQuotation, param);
			if(scmPurQuotation2 != null){
				scmPurQuotationEntryBiz.updateRowStatusByPqId(scmPurQuotation2.getId(), scmPurQuotation2.getStatus(), scmPurQuotation.getChecker(), scmPurQuotation.getCheckDate(), param);
			}
			return scmPurQuotation2;
		}
		return null;
	}

	/*
	 * 删除报价单
	 */
	@Override
	public void doDelPurQuotation(ScmPurQuotation scmPurQuotation, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put(ScmPurQuotation2.FN_PQNO, scmPurQuotation.getPqNo());
		map.put(ScmPurQuotation2.FN_ORGUNITNO, param.getOrgUnitNo());
		List<ScmPurQuotation2> scmPurQuotationList = this.findAll(map, param);
		if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
			try{
				this.delete(scmPurQuotationList, param);
			}catch(AppException e){
				throw e;
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
	}

	/*
	 * 提交报价单
	 */
	@Override
	public ScmPurQuotation2 doSubmitPurQuotation(ScmPurQuotation scmPurQuotation, Param param) throws AppException {
		ScmPurQuotation2 quotation = null;
		if(scmPurQuotation.getId()>0){
			quotation = this.selectDirect(scmPurQuotation.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurQuotation2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurQuotation2.FN_PQNO,
					new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,
							scmPurQuotation.getPqNo()));
			
			List<ScmPurQuotation2> scmPurQuotationList =this.findPage(page, param);
			if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
				quotation = scmPurQuotationList.get(0);
			}
		}
		if(quotation!=null) {
			this.setConvertMap(quotation,param);
			if(!quotation.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(quotation.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(quotation.getFinOrgUnitNo(), "PurQuotation", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				
				if(needAudit){
					//发起流程
					List<ScmPurQuotationEntry2> scmPurQuotationEntryList = scmPurQuotationEntryBiz.selectByPqId(scmPurQuotation.getId(), param);
					
					CommonBean bean = new CommonBean();
					List<ScmPurQuotation2> scmPurQuotationList = new ArrayList();
					scmPurQuotationList.add(quotation);
					bean.setList(scmPurQuotationList);
					bean.setList2(scmPurQuotationEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					quotation.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							quotation.setChecker(param.getUsrCode());
							quotation.setSubmitter(param.getUsrCode());
						}
						quotation.setSubmitDate(CalendarUtil.getDate(param));
						quotation.setCheckDate(CalendarUtil.getDate(param));
						quotation.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							quotation.setChecker(param.getUsrCode());
							quotation.setSubmitter(param.getUsrCode());
						}
						quotation.setCheckDate(CalendarUtil.getDate(param));
						quotation.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							if(param.getUsrCode()!=null ){
								quotation.setChecker(param.getUsrCode());
								quotation.setSubmitter(param.getUsrCode());
							}
							quotation.setSubmitDate(CalendarUtil.getDate(param));
							quotation.setCheckDate(CalendarUtil.getDate(param));
							scmBillPendingBiz.addPendingBill(usrList, quotation, param);
							PurQuotationParams purQuotationParams = new PurQuotationParams();
							purQuotationParams.setPqNo(quotation.getPqNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(), quotation, purQuotationParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						quotation.setEditor(param.getUsrCode());
						quotation.setSubmitter(param.getUsrCode());
					}
					quotation.setEditDate(CalendarUtil.getDate(param));
					quotation.setSubmitDate(CalendarUtil.getDate(param));
					quotation.setStatus("A");
				}
				
				try{
					this.updateStatus(quotation, param);
				}catch(AppException e){
					throw e;
				}
				if(StringUtils.contains("A,B", quotation.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.release(quotation, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return quotation;
	}

	/*
	 * 取消提交报价单
	 */
	@Override
	public ScmPurQuotation2 doUnSubmitPurQuotation(ScmPurQuotation scmPurQuotation, Param param) throws AppException {
		ScmPurQuotation2 quotation = null;
		Page page=new Page();
		page.setModelClass(ScmPurQuotation2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(
				ScmPurQuotation2.FN_PQNO,
				new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,
						scmPurQuotation.getPqNo()));
		
		List<ScmPurQuotation2> scmPurQuotationList =this.findPage(page, param);
		if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
			quotation = scmPurQuotationList.get(0);
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmPurQuotation.getFinOrgUnitNo(), "PurQuotation", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(quotation.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(quotation.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);

			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(quotation.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(quotation.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(quotation.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(quotation.getStatus().equals("A") || quotation.getStatus().equals("D")){
				if(param.getUsrCode()!=null ){
					quotation.setChecker(null);
					quotation.setSubmitter(null);
				}
				quotation.setCheckDate(null);
				quotation.setSubmitDate(null);
				quotation.setStatus("I");
				try{
					this.updateStatus(quotation, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),quotation, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return quotation;
	}

	@Override
	public List<ScmPurQuotation2> queryPurQuotationList(PurQuotationListParams purQuotationListParams,Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurQuotation2.class);
		page.setShowCount(Integer.MAX_VALUE);
		//vendorId    scmsupplier
		HashMap<String,Object> supplierMap = new HashMap<String,Object>();
		supplierMap.put(Scmsupplier2.FN_VENDORNO, purQuotationListParams.getVendorCode());
		supplierMap.put(Scmsupplier2.FN_CONTROLUNITNO, param.getControlUnitNo());		
		//buyerId      scmbuyer   Employee
		HashMap<String,Object> employeeMap = new HashMap<String,Object>();
		employeeMap.put(Employee.FN_EMPNO, purQuotationListParams.getBuyerCode());
		List<Employee> employeeList=employeeBiz.findAll(employeeMap, param);	
		//根据员工档案表的id  找到scmbuyer表的id
		Long empId=employeeList.get(0).getId();		
		HashMap<String,Object> buyerMap = new HashMap<String,Object>();
		buyerMap.put(ScmPurBuyer.FN_EMPID, empId);
		buyerMap.put(ScmPurBuyer.FN_CONTROLUNITNO, param.getControlUnitNo());
		List<ScmPurBuyer2> scmPurBuyerList=scmPurBuyerBiz.findAll(buyerMap, param);		
		List<Scmsupplier2> scmsupplierList=scmsupplierBiz.findAll(supplierMap, param);
		String vendorId=String.valueOf(scmsupplierList.get(0).getId());
		String buyerId=String.valueOf(scmPurBuyerList.get(0).getId());
		
		if(purQuotationListParams.getPqDateFrom()!=null && purQuotationListParams.getPqDateTo()!=null){
			page.getParam().put(
					ScmPurQuotation2.FN_PQDATE,
					new QueryParam(ScmPurQuotation2.FN_PQDATE,
							QueryParam.QUERY_BETWEEN, FormatUtils
									.fmtDateTime(purQuotationListParams.getPqDateFrom()),
							FormatUtils.fmtDateTime(purQuotationListParams.getPqDateTo())));
		}
		if (StringUtils.isNotBlank(purQuotationListParams.getPqNo())) {
			page.getParam().put(ScmPurQuotation2.FN_PQNO, new QueryParam(ScmPurQuotation2.FN_PQNO,
					QueryParam.QUERY_EQ, purQuotationListParams.getPqNo()));
		}
		if (StringUtils.isNotBlank(vendorId)) {
			page.getParam().put(ScmPurQuotation2.FN_VENDORID, new QueryParam(ScmPurQuotation2.FN_VENDORID,
					QueryParam.QUERY_EQ, vendorId));
		}
		if (StringUtils.isNotBlank(buyerId)) {
			page.getParam().put(ScmPurQuotation2.FN_BUYERID, new QueryParam(ScmPurQuotation2.FN_BUYERID,
					QueryParam.QUERY_EQ, buyerId));
		}
		try {
			List<ScmPurQuotation2> scmPurQuotationList =this.findPage(page, param);
			if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
				return scmPurQuotationList;
			}else{
				return null;
			}
		} catch (Exception e) {
			throw e;
		}
		
	}

	@Override
	public ScmPurQuotation2 queryPurQuotation(ScmPurQuotation2 scmPurQuotation, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurQuotation2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurQuotation2.FN_PQNO,new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,scmPurQuotation.getPqNo()));
		
		List<ScmPurQuotation2> scmPurQuotationList =this.findPage(page, param);
		ScmPurQuotation2 scmPurQuotation2 = new ScmPurQuotation2();
		if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
			scmPurQuotation2 = scmPurQuotationList.get(0);
			scmPurQuotation2.setScmPurQuotationEntryList(scmPurQuotationEntryBiz.selectByPqId(scmPurQuotation2.getId(), param));
			
			//增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurQuotation2.getId(), "PurQuotation",param);
			if (scmBillPendingUsr != null) {
				scmPurQuotation2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurQuotation2;
	}

	@Override
	public ScmPurQuotation2 doAddPurQuotation(PurQuotationAddParams purQuotationAddParams, Param param) throws AppException {
		
		ScmPurQuotation2 scmPurQuotation2=new ScmPurQuotation2();
		BeanUtils.copyProperties(purQuotationAddParams, scmPurQuotation2);
		if(scmPurQuotation2.getPqDate() != null){
			scmPurQuotation2.setPqDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurQuotation2.getPqDate())));
		}
		scmPurQuotation2.setCreateDate(CalendarUtil.getDate(param));
		scmPurQuotation2.setOrgUnitNo(param.getOrgUnitNo());
		this.beforeAdd(scmPurQuotation2, param);
		Page page=new Page();
		page.setModelClass(ScmPurQuotation2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if(StringUtils.isNotBlank(scmPurQuotation2.getPqNo())){
			page.getParam().put(
					ScmPurQuotation2.FN_PQNO,
					new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,
							scmPurQuotation2.getPqNo()));
		}
		
		//vendorId    scmsupplier
		HashMap<String,Object> supplierMap = new HashMap<String,Object>();
		supplierMap.put(Scmsupplier2.FN_VENDORNO, purQuotationAddParams.getVendorCode());
		supplierMap.put(Scmsupplier2.FN_CONTROLUNITNO, param.getControlUnitNo());
		
		//buyerId      scmbuyer   Employee
		HashMap<String,Object> employeeMap = new HashMap<String,Object>();
		employeeMap.put(Employee.FN_EMPNO, purQuotationAddParams.getBuyerCode());
		List<Employee> employeeList=employeeBiz.findAll(employeeMap, param);
		
		//根据员工档案表的id  找到scmbuyer表的id
		Long empId = (long) 0;
		if(employeeList!=null && !employeeList.isEmpty()){ 
			empId=employeeList.get(0).getId();
		}
		
		HashMap<String,Object> buyerMap = new HashMap<String,Object>();
		buyerMap.put(ScmPurBuyer.FN_EMPID, empId);
		buyerMap.put(ScmPurBuyer.FN_CONTROLUNITNO, param.getControlUnitNo());
		List<ScmPurBuyer2> scmPurBuyerList=scmPurBuyerBiz.findAll(buyerMap, param);
		
		List<ScmPurQuotation2> scmPurQuotationList=this.findPage(page, param);
		List<Scmsupplier2> scmsupplierList=scmsupplierBiz.findAll(supplierMap, param);
		
		Long vendorId = (long) 0;
		if(scmsupplierList!=null && !scmsupplierList.isEmpty()){ 
			vendorId = scmsupplierList.get(0).getId();
		}
		
		Long buyerId = (long) 0;
		Long purGroupId = (long) 0;
		if(scmPurBuyerList!=null && !scmPurBuyerList.isEmpty()){ 
			buyerId = scmPurBuyerList.get(0).getId();
			purGroupId = scmPurBuyerList.get(0).getPurGroupId();
		}
		
		if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
			throw new AppException("iscm.purchasemanage.error.areadyExists");
		}else{
			try {
				scmPurQuotation2.setPrtcount(0);
				scmPurQuotation2.setCurrencyNo("0");
				scmPurQuotation2.setStatus("I");
				scmPurQuotation2.setVendorId(vendorId);
				scmPurQuotation2.setCreator(param.getUsrCode());
				
				scmPurQuotation2.setBuyerId(buyerId);
				scmPurQuotation2.setPurGroupId(purGroupId);
				
				
				scmPurQuotation2=this.add(scmPurQuotation2, param);
				if(scmPurQuotation2==null){
					throw new AppException("iscm.purchasemanage.error.Failadd");
				}
				
				StringBuffer itemNos = new StringBuffer("");
				List<ScmPurQuotationEntry2> scmPurQuotationEntry2List=new ArrayList<ScmPurQuotationEntry2>();
				List<PurQuotaionDetailParams> purQuotaionDetailParamsList=purQuotationAddParams.getDetailList();
				for(PurQuotaionDetailParams purQuotaionDetailParams : purQuotaionDetailParamsList) {
					if(StringUtils.isBlank(purQuotaionDetailParams.getItemNo())){
						throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.itemisnull");
					}
					if(StringUtils.isNotBlank(itemNos.toString()))
						itemNos.append(",");
					itemNos.append("'").append(purQuotaionDetailParams.getItemNo()).append("'");
					
					
					ScmPurQuotationEntry2 scmPurQuotationEntry2 = new ScmPurQuotationEntry2();
					BeanUtils.copyProperties(purQuotaionDetailParams,scmPurQuotationEntry2);
					
					scmPurQuotationEntry2List.add(scmPurQuotationEntry2);
				}
				Long pqNo = scmPurQuotation2.getId();
				
				page = new Page();
				page.setModelClass(ScmMaterial2.class);
				page.setShowCount(Integer.MAX_VALUE);
				page.setSqlCondition("ScmMaterial.itemNo in ("+itemNos.toString()+")");
				ArrayList argList = new ArrayList();
		        argList.add("orgUnitNo="+param.getOrgUnitNo());
		        argList.add("controlUnitNo=" + param.getControlUnitNo());
		        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
		        StringBuffer itemIds = new StringBuffer("");
		        if(scmMaterialList!=null && !scmMaterialList.isEmpty()) {
		        	for(ScmMaterial2 scmMaterial:scmMaterialList){
		        		if(StringUtils.isNotBlank(itemIds.toString()))
		        			itemIds.append(",");
		        		itemIds.append(scmMaterial.getId());
		        	}
			        List<ScmMaterialPrice> scmMaterialPriceList = this.getLastPrice(itemIds.toString(), vendorId, scmPurQuotation2.getPqDate(), param);
	
					int i=1;
					for(ScmPurQuotationEntry2 scmPurQuotationEntry2 : scmPurQuotationEntry2List) {
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), scmPurQuotationEntry2.getItemNo())){
								scmPurQuotationEntry2.setItemId(scmMaterial.getId());
								scmPurQuotationEntry2.setPurUnit(scmMaterial.getPurUnitId());
								break;
							}
						}
						
						scmPurQuotationEntry2.setPqId(pqNo);
						boolean exists = false;
						if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()) {
							for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList) {
								if(scmMaterialPrice.getItemId()==scmPurQuotationEntry2.getItemId()) {
									scmPurQuotationEntry2.setPrePrice(scmMaterialPrice.getPrice());
									scmPurQuotationEntry2.setPreTaxPrice(scmMaterialPrice.getTaxPrice());
									exists = true;
									break;
								}
							}
						}
						if(!exists) {
							scmPurQuotationEntry2.setPrePrice(BigDecimal.valueOf(0));
							scmPurQuotationEntry2.setPreTaxPrice(BigDecimal.valueOf(0));
						}
						scmPurQuotationEntry2.setRowStatus("I");
						scmPurQuotationEntry2.setLineId(i++);
						
						ScmPurQuotationEntry2 entry2 = scmPurQuotationEntryBiz.add(scmPurQuotationEntry2, param);
						
						if(entry2==null){
							throw new AppException("iscm.purchasemanage.error.Failadd");
						}
					}
				}
			} catch (Exception e) {
				throw e;
			}
			return scmPurQuotation2;
		}
	}

	@Override
	public ScmPurQuotation2 doEditPurQuotation(PurQuotationEditParams purQuotationEditParams, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurQuotation2.class);
		page.setShowCount(Integer.MAX_VALUE);
		if(StringUtils.isNotBlank(purQuotationEditParams.getPqNo())){
			page.getParam().put(
					ScmPurQuotation2.FN_PQNO,
					new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,
							purQuotationEditParams.getPqNo()));
		}
		
		//vendorId    scmsupplier
		HashMap<String,Object> supplierMap = new HashMap<String,Object>();
		supplierMap.put(Scmsupplier2.FN_VENDORNO, purQuotationEditParams.getVendorCode());
		supplierMap.put(Scmsupplier2.FN_CONTROLUNITNO, param.getControlUnitNo());
		
		//buyerId      scmbuyer   Employee
		HashMap<String,Object> employeeMap = new HashMap<String,Object>();
		employeeMap.put(Employee.FN_EMPNO, purQuotationEditParams.getBuyerCode());
		List<Employee> employeeList=employeeBiz.findAll(employeeMap, param);
		
		//根据员工档案表的id  找到scmbuyer表的id
		Long empId=employeeList.get(0).getId();
		
		HashMap<String,Object> buyerMap = new HashMap<String,Object>();
		buyerMap.put(ScmPurBuyer.FN_EMPID, empId);
		buyerMap.put(ScmPurBuyer.FN_CONTROLUNITNO, param.getControlUnitNo());
		List<ScmPurBuyer2> scmPurBuyerList=scmPurBuyerBiz.findAll(buyerMap, param);
		List<Scmsupplier2> scmsupplierList=scmsupplierBiz.findAll(supplierMap, param);
		Long vendorId=scmsupplierList.get(0).getId();
		Long buyerId=scmPurBuyerList.get(0).getId();
		Long purGroupId=scmPurBuyerList.get(0).getPurGroupId();
		
		List<ScmPurQuotation2> scmPurQuotationList=this.findPage(page, param);
		ScmPurQuotation2 scmPurQuotation=null;
		if(scmPurQuotationList.get(0).getStatus().equals("I")){
			throw new AppException("iscm.inventorymanage.warehouseoutbusiness.add.statusError");
		}else if(scmPurQuotationList==null || scmPurQuotationList.isEmpty()){
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}else{
			try {
				ScmPurQuotation2 scmPurQuotation2=scmPurQuotationList.get(0);
				String id=String.valueOf(scmPurQuotation2.getId());
				BeanUtils.copyProperties(purQuotationEditParams, scmPurQuotation2);
				if(scmPurQuotation2.getPqDate() != null){
					scmPurQuotation2.setPqDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurQuotation2.getPqDate())));
				}
				scmPurQuotation2.setEditor(param.getUsrCode());
				scmPurQuotation2.setEditDate(CalendarUtil.getDate(param));
				scmPurQuotation2.setVendorId(vendorId);
				scmPurQuotation2.setBuyerId(buyerId);
				scmPurQuotation2.setPurGroupId(purGroupId);
				scmPurQuotation=this.update(scmPurQuotationList.get(0), param);
				if(scmPurQuotation==null){
					throw new AppException("iscm.purchasemanage.error.FailEdit");
				}
				Page entryPage=new Page();
				entryPage.setModelClass(ScmPurQuotationEntry2.class);
				entryPage.setShowCount(Integer.MAX_VALUE);
				
				List<PurQuotaionDetailParams> purQuotaionDetailParamsList=purQuotationEditParams.getDetailList();	
				HashMap<String,Object> materialMap = new HashMap<String,Object>();
				for(PurQuotaionDetailParams purQuotaionDetailParams : purQuotaionDetailParamsList) {
					entryPage.getParam().put(
							ScmPurQuotationEntry2.FN_PQID,
							new QueryParam(ScmPurQuotationEntry2.FN_PQID, QueryParam.QUERY_EQ,
									id));
					entryPage.getParam().put(
							ScmPurQuotationEntry2.FN_LINEID,
							new QueryParam(ScmPurQuotationEntry2.FN_LINEID, QueryParam.QUERY_EQ,
									String.valueOf(purQuotaionDetailParams.getLineId())));
					
					List<ScmPurQuotationEntry2> scmPurQuotationEntryList=scmPurQuotationEntryBiz.findPage(entryPage, param);
					materialMap.put(ScmMaterial2.FN_ITEMNO, purQuotaionDetailParams.getItemNo());
					materialMap.put(ScmMaterial2.FN_CONTROLUNITNO, param.getControlUnitNo());
					List<ScmMaterial2> scmMaterialList=scmMaterialBiz.findAll(materialMap, param);
					
					BeanUtils.copyProperties(purQuotaionDetailParams,scmPurQuotationEntryList.get(0));
					scmPurQuotationEntryList.get(0).setItemId(scmMaterialList.get(0).getId());
					ScmPurQuotationEntry2 entry = scmPurQuotationEntryBiz.update(scmPurQuotationEntryList.get(0), param);
					if(entry==null){
						throw new AppException("iscm.purchasemanage.error.FailEdit");
					}
				}
			} catch (Exception e) {
				throw e;
			}
			return scmPurQuotation;
		}
	}

	@Override
	public List<ScmMaterialPrice> getPrice(String purOrgUnitNo,long vendorId, String itemIds,Date bizDate, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", purOrgUnitNo);
		map.put("vendorId", vendorId);
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		return ((ScmPurQuotationDAO)dao).getPrice(map);
	}

	@Override
	public List<ScmPurQuotation2> selectRecentPrice(long itemId, Date begDate,Date endDate, String vendorIds, Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		map.put("vendorIds", vendorIds);
		map.put("priceType", priceType);
		map.put("orgUnitNo", param.getOrgUnitNo());
		return ((ScmPurQuotationDAO)dao).selectRecentPrice(map);
	}
	
	public List<ScmMaterialPrice> getLastPriceToSupplier(String itemIds,long vendorId,Date bizDate,String orgUnitNo, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("vendorId", vendorId);
		map.put("orgUnitNo", orgUnitNo);
		return ((ScmPurQuotationDAO)dao).getLastPrice(map);
	}

	@Override
	public List<ScmMaterialPrice> getLastPrice(String itemIds,long vendorId,Date bizDate, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemIds", itemIds);
		map.put("bizDate", FormatUtils.fmtDate(bizDate));
		map.put("vendorId", vendorId);
		map.put("orgUnitNo", param.getOrgUnitNo());
		return ((ScmPurQuotationDAO)dao).getLastPrice(map);
	}
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurQuotation2 entity = (ScmPurQuotation2) bean.getList().get(0);
		if(entity!=null) {
			ScmPurQuotation2 scmPurQuotation = this.select(entity.getPK(), param);
			if(!StringUtils.equals(scmPurQuotation.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		}
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurQuotationAdvQuery) {
				ScmPurQuotationAdvQuery scmPurQuotationAdvQuery = (ScmPurQuotationAdvQuery) page.getModel();
				if(scmPurQuotationAdvQuery.getBizDateFrom()!=null){
					if(scmPurQuotationAdvQuery.getBizDateTo()!=null) {
						page.getParam().put(ScmPurQuotation2.FN_PQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." +ScmPurQuotation2.FN_PQDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurQuotationAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmPurQuotationAdvQuery.getBizDateTo())));
					}else {
						page.getParam().put(ScmPurQuotation2.FN_PQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." +ScmPurQuotation2.FN_PQDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(scmPurQuotationAdvQuery.getBizDateFrom())));
					}
				}else if(scmPurQuotationAdvQuery.getBizDateTo()!=null) {
					page.getParam().put(ScmPurQuotation2.FN_PQDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." +ScmPurQuotation2.FN_PQDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurQuotationAdvQuery.getBizDateTo())));
				}
				if(scmPurQuotationAdvQuery.getCreateDateFrom()!=null){
					if(scmPurQuotationAdvQuery.getCreateDateTo()!=null) {
						page.getParam().put(ScmPurQuotation2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." +ScmPurQuotation2.FN_CREATEDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurQuotationAdvQuery.getCreateDateFrom()),FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurQuotationAdvQuery.getCreateDateTo(),1))));
					}else {
						page.getParam().put(ScmPurQuotation2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." +ScmPurQuotation2.FN_CREATEDATE, QueryParam.QUERY_GE,FormatUtils.fmtDate(CalendarUtil.relativeDate(scmPurQuotationAdvQuery.getCreateDateFrom(),1))));
					}
				}else if(scmPurQuotationAdvQuery.getCreateDateTo()!=null) {
					page.getParam().put(ScmPurQuotation2.FN_CREATEDATE,new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class) + "." +ScmPurQuotation2.FN_CREATEDATE, QueryParam.QUERY_LE,FormatUtils.fmtDate(scmPurQuotationAdvQuery.getCreateDateTo())));
				}
				if(scmPurQuotationAdvQuery.getVendorId()>0){
					page.getParam().put(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"."+ScmPurQuotation2.FN_VENDORID,
							new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurQuotation2.class)+"."+ScmPurQuotation2.FN_VENDORID, QueryParam.QUERY_EQ,String.valueOf(scmPurQuotationAdvQuery.getVendorId())));
				}
				if(scmPurQuotationAdvQuery.getVendorClassId()>0){
					page.getParam().put(Scmsuppliergroup.FN_ID,new QueryParam(ClassUtils.getFinalModelSimpleName(Scmsuppliergroup.class) + "." +Scmsuppliergroup.FN_ID, QueryParam.QUERY_EQ,String.valueOf(scmPurQuotationAdvQuery.getVendorClassId())));
				}
			}
		}
	}

	@Override
	public ScmPurQuotation2 doAuditPurQuotation(
			CommonAuditParams commonAuditParams, Param param)
			throws AppException {
		ScmPurQuotation2 quotation = null;
		
		ScmPurQuotation2 scmPurQuotation= new ScmPurQuotation2();
		scmPurQuotation.setId(commonAuditParams.getBillId());
		scmPurQuotation.setPqNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmPurQuotation.getId()>0){
			quotation = this.selectDirect(scmPurQuotation.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurQuotation.FN_PQNO,
					new QueryParam(ScmPurQuotation.FN_PQNO, QueryParam.QUERY_EQ,
							scmPurQuotation.getPqNo()));
			map.put(ScmPurQuotation.FN_CONTROLUNITNO, new QueryParam(ScmPurQuotation.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmPurQuotation2> scmPurQuotationList =this.findAll(map, param);
			
			if(scmPurQuotationList!=null && !scmPurQuotationList.isEmpty()){
				quotation = scmPurQuotationList.get(0);
			}
		}
		
		if (quotation != null) {
			this.setConvertMap(quotation,param);
			if(!(quotation.getStatus().equals("D") || quotation.getStatus().equals("P"))){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),quotation, param);
				commonEventHistoryBiz.updateInvalid(quotation,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(quotation.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(quotation, commonAuditOpinionR, param);
				
				//驳回则将单据变回新单状态
				quotation.setStatus("I");
				quotation.setChecker(null);
				quotation.setCheckDate(null);
				return this.updateDirect(quotation, param);
			}
			String processInstanceId = quotation.getWorkFlowNo();
			boolean isCompleteTask = true;
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//完成审批任务
				ActivityUtil.completeTaskByAssigneeAndOpinion(processInstanceId, param.getUsrCode(), opinion, param);
				ActivityUtil activityUtil = new ActivityUtil();
				//判断当前流程是否结束
				isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			//P：审核中，A：通过，N：未通过
			if ("agree".equals(opinion)) {
				if (isCompleteTask) {
					quotation.setStatus("A");
				} else {
					quotation.setStatus("P");
				}
			} else {
				quotation.setStatus("N");
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), quotation, param);
			quotation.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, quotation, param);
					PurQuotationParams purQuotationParams = new PurQuotationParams();
					purQuotationParams.setPqNo(quotation.getPqNo());
					AuditMsgUtil.sendAuditMsg("PurQuotation", quotation, purQuotationParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(quotation.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				quotation.setStepNo(stepNo);
				quotation.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(quotation, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(quotation, commonAuditOpinion, param);
			if(StringUtils.contains("A,B", quotation.getStatus())) {
				//通过或部分通过时检查是否自动下达
				BillType2 billType = billTypeBiz.selectByOrgAndCode(quotation.getFinOrgUnitNo(), "PurQuotation", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.release(quotation, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return quotation;
	}

	@Override
	public ScmPurQuotation2 doUnAuditPurQuotation(
			ScmPurQuotation2 scmPurQuotation, Param param)
			throws AppException {
		ScmPurQuotation2 quotation = null;
		List<ScmPurQuotation2> scmPurQuotationList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurQuotation.getId()>0){
			quotation = this.selectDirect(scmPurQuotation.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurQuotation2.FN_PQNO,
					new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,
							scmPurQuotation.getPqNo()));
			map.put(ScmPurQuotation.FN_CONTROLUNITNO, new QueryParam(ScmPurQuotation.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmPurQuotationList = this.findAll(map, param);
			if(scmPurQuotationList != null && !scmPurQuotationList.isEmpty()){
				quotation = scmPurQuotationList.get(0);
			}
		}
		
		if (quotation != null) {
			if(!StringUtils.contains("A,B,N,P", quotation.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = quotation.getWorkFlowNo();
			List<ScmPurQuotationEntry2> scmPurQuotationEntryList = scmPurQuotationEntryBiz.selectByPqId(quotation.getId(), param);
			
			if(scmPurQuotationEntryList == null || scmPurQuotationEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurQuotationList.add(quotation);
				bean.setList(scmPurQuotationList);
				bean.setList2(scmPurQuotationEntryList);
				BillType2 billType = billTypeBiz.selectByOrgAndCode(quotation.getFinOrgUnitNo(), "PurQuotation", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(),  param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				quotation.setWorkFlowNo(processInstanceId);
			} else {
				//流程编号不存在
				throw new AppException("com.armitage.server.iscm.purchasemanage.purchasebusiness.service.impl.ScmPurRequireBizImpl.doAuditPurRequire.processInstanceId");
			}
			
			String status = "";
			if (isFirstTask) {
				status = "D";
			} else {
				status = "P";
			}
			quotation.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(quotation);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,quotation.getStepNo(),quotation.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),quotation.getPK(),param);
			}
			if(commonEventHistory!=null) {
				quotation.setChecker(commonEventHistory.getOper());
				quotation.setCheckDate(commonEventHistory.getOperDate());
			}else {
				quotation.setChecker(null);
				quotation.setCheckDate(null);
			}
			this.updateStatus(quotation, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),quotation, param);
			commonEventHistoryBiz.updateInvalid(quotation,quotation.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(quotation.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(quotation, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return quotation;
	}

	@Override
	public ScmPurQuotation2 release(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException {
		if(!StringUtils.equals("A",scmPurQuotation.getStatus()))
			throw new AppException("iscm.purchasemanage.error.release");
		scmPurQuotation.setStatus("E");
		return this.updateDirect(scmPurQuotation, param);
	}

	@Override
	public CommonBean undoRelease(List<ScmPurQuotation2> scmPurQuotation1, int type, Param param) throws AppException {
		CommonBean undoReleaseCheck = undoReleaseCheck(scmPurQuotation1, type, param);//检查是否有正在使用的单据
		List<ScmPurQuotation2> list = undoReleaseCheck.getList();
		if (list != null && list.size() > 0) {
			for (ScmPurQuotation2 scmPurQuotation : list) {
				if (!StringUtils.equals("E", scmPurQuotation.getStatus()))
					throw new AppException("iscm.purchasemanage.error.cancelRelease");
				scmPurQuotation.setStatus("A");
				this.updateDirect(scmPurQuotation, param);
			}
		}
		return undoReleaseCheck;
	}

	@Override
	public ScmPurQuotation2 finish(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException {
		ScmPurQuotation2 purBill = null;
		if(scmPurQuotation.getId()>0){
			purBill = this.selectDirect(scmPurQuotation.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurQuotation2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurQuotation2.FN_PQNO,new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,scmPurQuotation.getPqNo()));
			
			List<ScmPurQuotation2> scmBillList =this.findPage(page, param);
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
		return purBill;
	}

	@Override
	public ScmPurQuotation2 undoFinish(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException {
		ScmPurQuotation2 purBill = null;
		if(scmPurQuotation.getId()>0){
			purBill = this.selectDirect(scmPurQuotation.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurQuotation2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurQuotation2.FN_PQNO,new QueryParam(ScmPurQuotation2.FN_PQNO, QueryParam.QUERY_EQ,scmPurQuotation.getPqNo()));
			
			List<ScmPurQuotation2> scmBillList =this.findPage(page, param);
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
	public List<ScmPurQuotation2> selectItemsRecentPrice(String itemIds, Date begDate, Date endDate, String vendorIds,
			Param param) throws AppException {
		String priceType =sysParamBiz.getValue(param.getOrgUnitNo(), "SCM_PriceType", "1", param);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemIds", itemIds);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		map.put("vendorIds", vendorIds);
		map.put("priceType", priceType);
		return ((ScmPurQuotationDAO)dao).selectItemsRecentPrice(map);
	}
	
	@Override
	public CommonBean undoReleaseCheck(List<ScmPurQuotation2> scmPurQuotationList, int type, Param param)
			throws AppException {
		CommonBean commonBean = new CommonBean();
		if (scmPurQuotationList!=null && scmPurQuotationList.size()>0) {
			int sucess = 0;
			StringBuffer stringBuffer = new StringBuffer("");
			StringBuffer messageBuffer = new StringBuffer("");
			HashMap<Object, Object> map = new HashMap<>();
			List<ScmPurQuotation2> scmList=new ArrayList();
			scmList.addAll(scmPurQuotationList);
			stringBuffer.append("(");
			System.out.println(stringBuffer.length());
			for (ScmPurQuotation2 scmPurQuotation2 : scmPurQuotationList) {
				if (stringBuffer.length()>2) {
					stringBuffer.append(",");
				}
				stringBuffer.append(scmPurQuotation2.getId());
			}
			stringBuffer.append(")");
			map.put(ScmPurRequireEntry.FN_PRICEBILLID, stringBuffer);
			map.put("billType", type);
			List<ScmPurRequireEntry2> scmPurRequireEntry2s = scmPurRequireEntryBiz.undoReleaseCheck(map, param);
			if (scmPurRequireEntry2s!=null && scmPurRequireEntry2s.size()>0) {
				for (ScmPurQuotation2 scmPurQuotation : scmPurQuotationList) {
					for (ScmPurRequireEntry2 scmPurRequireEntry : scmPurRequireEntry2s) {
						if (scmPurQuotation.getId()==scmPurRequireEntry.getPriceBillId()) {
							scmList.remove(scmPurQuotation);
							switch (scmPurRequireEntry.getBizType()) {
							case "1":
								messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.checkPurQuotation",
										new String[] {scmPurQuotation.getPqNo(),scmPurRequireEntry.getPrNo()}));
								messageBuffer.append("\r\n");
								break;
							case "2":
								messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.PurOrder",
										new String[] {scmPurQuotation.getPqNo(),scmPurRequireEntry.getPrNo()}));
								messageBuffer.append("\r\n");
								break;
							case "3":
								messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.PurReceive",
										new String[] {scmPurQuotation.getPqNo(),scmPurRequireEntry.getPrNo()}));
								messageBuffer.append("\r\n");
								break;
							case "4":
								messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.InvPurInWarehsBill",
										new String[] {scmPurQuotation.getPqNo(),scmPurRequireEntry.getPrNo()}));
								messageBuffer.append("\r\n");
								break;
							}
						}
					}
				}
			}
			ArrayList<ScmPurQuotation2> arrayList = new ArrayList<>();
			if (scmList!=null && scmList.size()>0) {
				for (ScmPurQuotation2 scmPurQuotation2 : scmList) {
					if (StringUtils.equals("E", scmPurQuotation2.getStatus())) {
						arrayList.add(scmPurQuotation2);
						sucess++;
					}else {
						messageBuffer.append(Message.getMessage(param.getLang(),"iscm.purchasemanage.pricemanage.undoReleaseCheck.undoReleaseCheckCode",
								new String[] {scmPurQuotation2.getPqNo()}));
						messageBuffer.append("\r\n");
					}
				}
			}
			String message = Message.getMessage("iscm.purchasemanage.pricemanage.undoReleaseCheck.undoReleaseCheckMessage",
					new String[] {String.valueOf(sucess),String.valueOf((scmPurQuotationList.size() - sucess))});
			commonBean.setObject(messageBuffer.toString());
			commonBean.setObject2(message);
			commonBean.setList(arrayList);
		}
		return commonBean;
	}

	@Override
	public List<ScmMaterialPrice> getPriceByVendorIds(List<ScmPurPriceQuery> scmPurPriceQuery,Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ScmPurPriceQuery", scmPurPriceQuery);
		return ((ScmPurQuotationDAO)dao).getPriceByVendorIds(map);
	}

	@Override
	public ScmPurQuotation2 generateBillFromPmBill(ScmPurPrice2 scmPurPrice,long vendorId, Param param) throws AppException {
		if(vendorId>0 && scmPurPrice!=null) {
			CommonBean bean = new CommonBean();
			List<ScmPurPrice2> scmPurPriceList = new ArrayList();
			scmPurPriceList.add(scmPurPrice);
			bean.setList(scmPurPriceList);
			bean = scmPurPriceBiz.select(bean, param);
			if(bean!=null) {
				scmPurPrice = (ScmPurPrice2) bean.getList().get(0);
				List<ScmPurPriceEntry2> scmPurPriceEntryList = bean.getList2();
				String priceColumn;
				String taxRateColumn;
				long pqId = 0;
				if(vendorId==scmPurPrice.getVendorId1()) {
					//第一个供应商报价
					pqId = scmPurPrice.getPqId1();
					priceColumn = "price1";
					taxRateColumn = "taxRate1";
				}else if(vendorId==scmPurPrice.getVendorId2()) {
					//第二个供应商报价
					pqId = scmPurPrice.getPqId2();
					priceColumn = "price2";
					taxRateColumn = "taxRate2";
				}else if(vendorId==scmPurPrice.getVendorId3()) {
					//第三个供应商报价
					pqId = scmPurPrice.getPqId3();
					priceColumn = "price3";
					taxRateColumn = "taxRate3";
				}else {
					return null;
				}
				if(scmPurPriceEntryList!=null && !scmPurPriceEntryList.isEmpty()) {
					StringBuffer itemIds = new StringBuffer("");
					for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList){
		        		if(StringUtils.isNotBlank(itemIds.toString()))
		        			itemIds.append(",");
		        		itemIds.append(scmPurPriceEntry.getItemId());
		        	}
			        List<ScmMaterialPrice> scmMaterialPriceList = this.getLastPriceToSupplier(itemIds.toString(), vendorId, scmPurPrice.getPmDate(),scmPurPrice.getOrgUnitNo(), param);
					String priceType = sysParamBiz.getValue(scmPurPrice.getOrgUnitNo(), "SCM_PriceType", "1", param);
					int pricePrec = Integer.valueOf(sysParamBiz.getValue(scmPurPrice.getOrgUnitNo(), "SCM_PricePrecision", "2",param)); 
					ScmPurQuotation2 scmPurQuotation = null;
					List<ScmPurQuotationEntry2> scmPurQuotationEntryList = new ArrayList();
					if(pqId>0) {
						scmPurQuotation = this.selectDirect(pqId, param);
						scmPurQuotationEntryList = scmPurQuotationEntryBiz.selectByPqId(pqId, param);
						if(scmPurQuotationEntryList==null)
							scmPurQuotationEntryList = new ArrayList();
						for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList) {
							boolean exists=false;
							if(!scmPurQuotationEntryList.isEmpty()) {
								for(ScmPurQuotationEntry2 scmPurQuotationEntry:scmPurQuotationEntryList) {
									if(scmPurPriceEntry.getItemId()==scmPurQuotationEntry.getItemId()) {
										try {
											scmPurQuotationEntry.setTaxRate((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, taxRateColumn));
										} catch (Exception e) {
										}
										if(StringUtils.equals("1", priceType)){
											//含税价
											try {
												scmPurQuotationEntry.setTaxPrice((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, priceColumn));
											} catch (Exception e) {
											}
											scmPurQuotationEntry.setPrice(scmPurQuotationEntry.getTaxPrice().divide((BigDecimal.ONE).add(scmPurQuotationEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP));
										}else {
											//净价
											try {
												scmPurQuotationEntry.setPrice((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, priceColumn));
											} catch (Exception e) {
											}
											scmPurQuotationEntry.setTaxPrice(scmPurQuotationEntry.getPrice().multiply((BigDecimal.ONE).add(scmPurQuotationEntry.getTaxRate())).setScale(pricePrec,RoundingMode.HALF_UP));
										}
										exists = true;
										break;
									}
								}
							}
							if(!exists) {
								ScmPurQuotationEntry2 scmPurQuotationEntry = new ScmPurQuotationEntry2();
								scmPurQuotationEntry.setLineId(scmPurPriceEntry.getLineId());
								scmPurQuotationEntry.setItemId(scmPurPriceEntry.getItemId());
								scmPurQuotationEntry.setPurUnit(scmPurPriceEntry.getPurUnit());
								try {
									scmPurQuotationEntry.setTaxRate((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, taxRateColumn));
								} catch (Exception e) {
								}
								if(StringUtils.equals("1", priceType)){
									//含税价
									try {
										scmPurQuotationEntry.setTaxPrice((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, priceColumn));
									} catch (Exception e) {
									}
									scmPurQuotationEntry.setPrice(scmPurQuotationEntry.getTaxPrice().divide((BigDecimal.ONE).add(scmPurQuotationEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP));
								}else {
									//净价
									try {
										scmPurQuotationEntry.setPrice((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, priceColumn));
									} catch (Exception e) {
									}
									scmPurQuotationEntry.setTaxPrice(scmPurQuotationEntry.getPrice().multiply((BigDecimal.ONE).add(scmPurQuotationEntry.getTaxRate())).setScale(pricePrec,RoundingMode.HALF_UP));
								}
								if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()) {
									for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList) {
										if(scmMaterialPrice.getItemId()==scmPurQuotationEntry.getItemId()) {
											scmPurQuotationEntry.setPrePrice(scmMaterialPrice.getPrice());
											scmPurQuotationEntry.setPreTaxPrice(scmMaterialPrice.getTaxPrice());
											break;
										}
									}
								}
								scmPurQuotationEntryList.add(scmPurQuotationEntry);
							}
						}
						for(int i=scmPurQuotationEntryList.size()-1;i>=0;i--) {
							ScmPurQuotationEntry2 scmPurQuotationEntry=scmPurQuotationEntryList.get(i);
							boolean exists=false;
							for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList) {
								if(scmPurPriceEntry.getItemId()==scmPurQuotationEntry.getItemId()) {
									exists=true;
									break;
								}
							}
							if(!exists) {
								scmPurQuotationEntryList.remove(i);
							}
						}
					}else {
						scmPurQuotation = new ScmPurQuotation2(true);
						scmPurQuotation.setVendorId(vendorId);
						scmPurQuotation.setPqDate(scmPurPrice.getPmDate());
						scmPurQuotation.setBegDate(scmPurPrice.getBegDate());
						scmPurQuotation.setEndDate(scmPurPrice.getEndDate());
						scmPurQuotation.setOrgUnitNo(scmPurPrice.getOrgUnitNo());
						scmPurQuotation.setFinOrgUnitNo(scmPurPrice.getFinOrgUnitNo());
						scmPurQuotation.setControlUnitNo(scmPurPrice.getControlUnitNo());
						scmPurQuotation.setCreator(scmPurPrice.getCreator());
						scmPurQuotation.setRemarks(scmPurPrice.getPmNo());
						for(ScmPurPriceEntry2 scmPurPriceEntry:scmPurPriceEntryList) {
							ScmPurQuotationEntry2 scmPurQuotationEntry = new ScmPurQuotationEntry2(true);
							scmPurQuotationEntry.setLineId(scmPurPriceEntry.getLineId());
							scmPurQuotationEntry.setItemId(scmPurPriceEntry.getItemId());
							scmPurQuotationEntry.setPurUnit(scmPurPriceEntry.getPurUnit());
							try {
								scmPurQuotationEntry.setTaxRate((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, taxRateColumn));
							} catch (Exception e) {
							}
							if(StringUtils.equals("1", priceType)){
								//含税价
								try {
									scmPurQuotationEntry.setTaxPrice((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, priceColumn));
								} catch (Exception e) {
								}
								scmPurQuotationEntry.setPrice(scmPurQuotationEntry.getTaxPrice().divide((BigDecimal.ONE).add(scmPurQuotationEntry.getTaxRate()),pricePrec,RoundingMode.HALF_UP));
							}else {
								//净价
								try {
									scmPurQuotationEntry.setPrice((BigDecimal)BeanUtil.getPropertyValue(scmPurPriceEntry, priceColumn));
								} catch (Exception e) {
								}
								scmPurQuotationEntry.setTaxPrice(scmPurQuotationEntry.getPrice().multiply((BigDecimal.ONE).add(scmPurQuotationEntry.getTaxRate())).setScale(pricePrec,RoundingMode.HALF_UP));
							}
							if(scmMaterialPriceList!=null && !scmMaterialPriceList.isEmpty()) {
								for(ScmMaterialPrice scmMaterialPrice:scmMaterialPriceList) {
									if(scmMaterialPrice.getItemId()==scmPurQuotationEntry.getItemId()) {
										scmPurQuotationEntry.setPrePrice(scmMaterialPrice.getPrice());
										scmPurQuotationEntry.setPreTaxPrice(scmMaterialPrice.getTaxPrice());
										break;
									}
								}
							}
							scmPurQuotationEntryList.add(scmPurQuotationEntry);
						}
					}
					List<ScmPurQuotation2> list = new ArrayList();
					list.add(scmPurQuotation);
					bean = new CommonBean();
					bean.setList(list);
					bean.setList2(scmPurQuotationEntryList);
					if(pqId>0) {
						this.update(bean, param);
					}else {
						this.add(bean, param);
					}
					return scmPurQuotation;
				}
			}
		}
		return null;
	}

	@Override
	public void releaseByPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		if(scmPurPrice.getPqId1()>0) {
			ScmPurQuotation2 scmPurQuotation = this.selectDirect(scmPurPrice.getPqId1(), param);
			scmPurQuotation.setStatus("E");
			this.updateDirect(scmPurQuotation, param);
		}
		if(scmPurPrice.getPqId2()>0) {
			ScmPurQuotation2 scmPurQuotation = this.selectDirect(scmPurPrice.getPqId2(), param);
			scmPurQuotation.setStatus("E");
			this.updateDirect(scmPurQuotation, param);
		}
		if(scmPurPrice.getPqId3()>0) {
			ScmPurQuotation2 scmPurQuotation = this.selectDirect(scmPurPrice.getPqId3(), param);
			scmPurQuotation.setStatus("E");
			this.updateDirect(scmPurQuotation, param);
		}
	}

	@Override
	public void undoReleaseByPurPrice(ScmPurPrice2 scmPurPrice, Param param) throws AppException {
		if(scmPurPrice.getPqId1()>0) {
			ScmPurQuotation2 scmPurQuotation = this.selectDirect(scmPurPrice.getPqId1(), param);
			scmPurQuotation.setStatus("I");
			this.updateDirect(scmPurQuotation, param);
		}
		if(scmPurPrice.getPqId2()>0) {
			ScmPurQuotation2 scmPurQuotation = this.selectDirect(scmPurPrice.getPqId2(), param);
			scmPurQuotation.setStatus("I");
			this.updateDirect(scmPurQuotation, param);
		}
		if(scmPurPrice.getPqId3()>0) {
			ScmPurQuotation2 scmPurQuotation = this.selectDirect(scmPurPrice.getPqId3(), param);
			scmPurQuotation.setStatus("I");
			this.updateDirect(scmPurQuotation, param);
		}
		
	}

	@Override
	public ScmPurQuotation2 updatePrtCount(ScmPurQuotation2 scmPurQuotation, Param param) throws AppException {
		if(scmPurQuotation.getId()>0){
			ScmPurQuotation2 bill = this.selectDirect(scmPurQuotation.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurQuotation;
	}
}