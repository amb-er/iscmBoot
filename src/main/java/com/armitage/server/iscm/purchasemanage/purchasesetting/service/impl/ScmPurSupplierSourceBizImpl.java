
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.api.business.purprice.params.PurPriceParams;
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
import com.armitage.server.common.util.SimpleDataDisplayUtil;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmsupplierBiz;
import com.armitage.server.iscm.common.model.CommonAuditOpinion;
import com.armitage.server.iscm.common.model.CommonEventHistory;
import com.armitage.server.iscm.common.service.CommonEventHistoryBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPrice2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceRecOrgBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplyInfoBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.springframework.stereotype.Service;

@Service("scmPurSupplierSourceBiz")
public class ScmPurSupplierSourceBizImpl extends BaseBizImpl<ScmPurSupplierSource2> implements ScmPurSupplierSourceBiz {
	private OrgPurchaseBiz orgPurchaseBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private UsrBiz usrBiz;
	private ScmPurBuyerBiz scmPurBuyerBiz;
	private ScmsupplierBiz scmsupplierBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmPurSupplierSourceEntryBiz scmPurSupplierSourceEntryBiz;
	private BillTypeBiz billTypeBiz;
	private CommonEventHistoryBiz commonEventHistoryBiz;
	private ScmPurSupplierSourceRecOrgBiz scmPurSupplierSourceRecOrgBiz;
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private ScmPurSupplyInfoBiz scmPurSupplyInfoBiz;
	private CodeBiz codeBiz;
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmPurBuyerBiz(ScmPurBuyerBiz scmPurBuyerBiz) {
		this.scmPurBuyerBiz = scmPurBuyerBiz;
	}

	public void setScmsupplierBiz(ScmsupplierBiz scmsupplierBiz) {
		this.scmsupplierBiz = scmsupplierBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setScmPurSupplierSourceEntryBiz(ScmPurSupplierSourceEntryBiz scmPurSupplierSourceEntryBiz) {
		this.scmPurSupplierSourceEntryBiz = scmPurSupplierSourceEntryBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setCommonEventHistoryBiz(CommonEventHistoryBiz commonEventHistoryBiz) {
		this.commonEventHistoryBiz = commonEventHistoryBiz;
	}

	public void setScmPurSupplierSourceRecOrgBiz(ScmPurSupplierSourceRecOrgBiz scmPurSupplierSourceRecOrgBiz) {
		this.scmPurSupplierSourceRecOrgBiz = scmPurSupplierSourceRecOrgBiz;
	}

	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setScmPurSupplyInfoBiz(ScmPurSupplyInfoBiz scmPurSupplyInfoBiz) {
		this.scmPurSupplyInfoBiz = scmPurSupplyInfoBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurSupplierSource2.class) + "." + ScmPurSupplierSource2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurSupplierSource2.class) + "." + ScmPurSupplierSource2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurSupplierSource2.class) + "." + ScmPurSupplierSource2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurSupplierSource2.class) + "." + ScmPurSupplierSource2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmPurSupplierSource2 scmPurSupplierSource : (List<ScmPurSupplierSource2>)list){
				//视图增加待审人
				ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurSupplierSource.getId(), "PurSupplierSrc",param);
				StringBuffer usrName = new StringBuffer("");
				if (scmBillPendingUsr != null) {
					scmPurSupplierSource.setPendingUsr(scmBillPendingUsr.getUsrCodes());
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
				scmPurSupplierSource.setPendingUsrName(usrName.toString());
				this.setConvertMap(scmPurSupplierSource, param);
				if("I,R".contains(scmPurSupplierSource.getStatus())) {
					scmPurSupplierSource.setPendingUsrName("");
				}
			}
		}
	}
	
	private void setConvertMap(ScmPurSupplierSource2 scmPurSupplierSource,Param param) {
		if (scmPurSupplierSource.getBuyerId() > 0){
			//采购员
			ScmPurBuyer2 scmPurBuyer = scmPurBuyerBiz.selectDirect(scmPurSupplierSource.getBuyerId(), param);
			if (scmPurBuyer != null) {
				scmPurSupplierSource.setConvertMap(ScmPurSupplierSource2.FN_BUYERID, scmPurBuyer);
				scmPurSupplierSource.setBuyerCode(scmPurBuyer.getBuyerCode());
				scmPurSupplierSource.setBuyerName(scmPurBuyer.getBuyerName());
			}
		}
		if (StringUtils.isNotBlank(scmPurSupplierSource.getOrgUnitNo())){
			OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurSupplierSource.getOrgUnitNo(), param);
			if(orgBaseUnit!=null)
				scmPurSupplierSource.setOrgUnitName(orgBaseUnit.getOrgUnitName());
		}
		if (StringUtils.isNotBlank(scmPurSupplierSource.getCreator())){
			//制单人
			Usr usr = usrBiz.selectByCode(scmPurSupplierSource.getCreator(), param);
			if (usr != null) {
				if(scmPurSupplierSource.getDataDisplayMap()==null){
					scmPurSupplierSource.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
				}
				scmPurSupplierSource.getDataDisplayMap().put(ScmPurSupplierSource2.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				scmPurSupplierSource.setConvertMap(ScmPurSupplierSource2.FN_CREATOR, usr);				
				scmPurSupplierSource.setCreatorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurSupplierSource.getEditor())){
			//修改人
			Usr usr = usrBiz.selectByCode(scmPurSupplierSource.getEditor(), param);
			if (usr != null) {
				scmPurSupplierSource.setConvertMap(ScmPurSupplierSource2.FN_EDITOR, usr);
				scmPurSupplierSource.setEditorName(usr.getName());
			}
		}
		if (StringUtils.isNotBlank(scmPurSupplierSource.getChecker())){
			//审核人
			Usr usr = usrBiz.selectByCode(scmPurSupplierSource.getChecker(), param);
			if (usr != null) {
				scmPurSupplierSource.setConvertMap(ScmPurSupplierSource2.FN_CHECKER, usr);
				scmPurSupplierSource.setCheckerName(usr.getName());
			}
		}
		if(scmPurSupplierSource.getVendorId() > 0){
			//供应商
			Scmsupplier2 scmsupplier = scmsupplierBiz.selectDirect(scmPurSupplierSource.getVendorId(), param);
			if(scmsupplier != null){
				scmPurSupplierSource.setConvertMap(ScmPurSupplierSource2.FN_VENDORID, scmsupplier);
				scmPurSupplierSource.setVendorCode(scmsupplier.getVendorNo());
				scmPurSupplierSource.setVendorName(scmsupplier.getVendorName());
			}
		}
		if(StringUtils.isNotBlank(scmPurSupplierSource.getStatus())){
			Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurSupplierSource.getStatus());
			if(code!=null)
				scmPurSupplierSource.setStatusName(code.getName());
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurSupplierSource2 scmPurSupplierSource = (ScmPurSupplierSource2) bean.getList().get(0);
		this.setConvertMap(scmPurSupplierSource, param);
		if(scmPurSupplierSource != null && scmPurSupplierSource.getId() > 0){
			bean.setList2(scmPurSupplierSourceEntryBiz.selectByBillId(scmPurSupplierSource.getId(), param));
			bean.setList3(scmPurSupplierSourceRecOrgBiz.selectByBillId(scmPurSupplierSource.getId(), param));
		}
	}
	@Override
	protected void beforeAdd(ScmPurSupplierSource2 entity, Param param) throws AppException {
		if(entity != null && StringUtils.isBlank(entity.getBillNo())){
			String code = CodeAutoCalUtil.getBillCode("PurSupplierSrc", entity, param);
			entity.setBillNo(code);
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
		ScmPurSupplierSource2 scmPurSupplierSource = (ScmPurSupplierSource2) bean.getList().get(0);
		if(scmPurSupplierSource != null && scmPurSupplierSource.getId() > 0){
			//新增报价明细
			List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList = bean.getList2();
			if(scmPurSupplierSourceEntryList != null && !scmPurSupplierSourceEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurSupplierSourceEntry2 scmPurSupplierSourceEntry : scmPurSupplierSourceEntryList){
					scmPurSupplierSourceEntry.setLineId(lineId);
					scmPurSupplierSourceEntry.setBillId(scmPurSupplierSource.getId());
					scmPurSupplierSourceEntryBiz.add(scmPurSupplierSourceEntry, param);
					lineId = lineId+1;
				}
			}
			List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList = bean.getList3();
			if(scmPurSupplierSourceRecOrgList!=null && !scmPurSupplierSourceRecOrgList.isEmpty()) {
				for(ScmPurSupplierSourceRecOrg2 scmPurSupplierSourceRecOrg:scmPurSupplierSourceRecOrgList) {
					scmPurSupplierSourceRecOrg.setBillId(scmPurSupplierSource.getId());
					scmPurSupplierSourceRecOrgBiz.add(scmPurSupplierSourceRecOrg, param);
				}
			}
		}
	}
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurSupplierSource2 scmPurSupplierSource = (ScmPurSupplierSource2) bean.getList().get(0);
		if(scmPurSupplierSource != null && scmPurSupplierSource.getId() > 0){
			//更新报价明细
			List<ScmPurSupplierSourceEntry2> scmPurSupplierSourceEntryList = bean.getList2();
			if(scmPurSupplierSourceEntryList != null && !scmPurSupplierSourceEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurSupplierSourceEntry2 scmPurSupplierSourceEntry : scmPurSupplierSourceEntryList){
					scmPurSupplierSourceEntry.setLineId(lineId);
					scmPurSupplierSourceEntry.setBillId(scmPurSupplierSource.getId());
					lineId = lineId+1;
				}
				scmPurSupplierSourceEntryBiz.update(scmPurSupplierSource, scmPurSupplierSourceEntryList, ScmPurSupplierSourceEntry2.FN_BILLID, param);
			}
			List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList = bean.getList3();
			if(scmPurSupplierSourceRecOrgList!=null && !scmPurSupplierSourceRecOrgList.isEmpty()) {
				for(ScmPurSupplierSourceRecOrg2 scmPurSupplierSourceRecOrg:scmPurSupplierSourceRecOrgList) {
					scmPurSupplierSourceRecOrg.setBillId(scmPurSupplierSource.getId());
				}
				scmPurSupplierSourceRecOrgBiz.update(scmPurSupplierSource, scmPurSupplierSourceRecOrgList, ScmPurSupplierSourceRecOrg2.FN_BILLID, param);
			}
		}
	}
	
	@Override
	protected void beforeDelete(ScmPurSupplierSource2 entity, Param param) throws AppException {
		ScmPurSupplierSource2 scmPurSupplierSource = this.selectDirect(entity.getId(), param);
		if(scmPurSupplierSource!=null && !StringUtils.equals(entity.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getBillNo()}));
		}
	}
	
	@Override
	protected void afterDelete(ScmPurSupplierSource2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除报价明细
			scmPurSupplierSourceEntryBiz.deleteByBillId(entity.getId(), param);
			//删除组织分配
			scmPurSupplierSourceRecOrgBiz.deleteByBillId(entity.getId(), param);
		}
	}

	@Override
	public ScmPurSupplierSource2 doSubmit(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException {
		ScmPurSupplierSource2 purPrice = null;
		List<ScmPurSupplierSource2> scmPurPriceList = new ArrayList<> ();
		if(scmPurSupplierSource.getId()>0){
			purPrice = this.selectDirect(scmPurSupplierSource.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurSupplierSource2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurSupplierSource2.FN_BILLNO,new QueryParam(ScmPurSupplierSource2.FN_BILLNO, QueryParam.QUERY_EQ,scmPurSupplierSource.getBillNo()));
			
			scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		if(purPrice!=null){
			this.setConvertMap(purPrice,param);
			if(!purPrice.getStatus().equals("I")){
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(purPrice.getStatus().equals("I")){
				BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "PurSupplierSrc", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				List<ScmPurSupplierSourceEntry2> scmPurPriceEntryList = scmPurSupplierSourceEntryBiz.selectByBillId(scmPurSupplierSource.getId(), param);
				if(needAudit){
					//发起流程
					CommonBean bean = new CommonBean();
					scmPurPriceList.add(purPrice);
					bean.setList(scmPurPriceList);
					bean.setList2(scmPurPriceEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					purPrice.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							purPrice.setChecker(param.getUsrCode());
							purPrice.setSubmitter(param.getUsrCode());
						}
						purPrice.setCheckDate(CalendarUtil.getDate(param));
						purPrice.setSubmitDate(CalendarUtil.getDate(param));
						purPrice.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							purPrice.setSubmitter(param.getUsrCode());
						}
						purPrice.setSubmitDate(CalendarUtil.getDate(param));
						purPrice.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, purPrice, param);
							PurPriceParams purPriceParams = new PurPriceParams();
							purPriceParams.setPmNo(purPrice.getBillNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(), purPrice, purPriceParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						purPrice.setEditor(param.getUsrCode());
						purPrice.setSubmitter(param.getUsrCode());
					}
					purPrice.setEditDate(CalendarUtil.getDate(param));
					purPrice.setSubmitDate(CalendarUtil.getDate(param));
					purPrice.setStatus("A");
				}
				
				try{
					this.updateStatus(purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purPrice;
	}

	private ScmPurSupplierSource2 updateStatus(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException {
		if(scmPurSupplierSource != null){
			ScmPurSupplierSource2 scmPurSupplierSource2 = this.updateDirect(scmPurSupplierSource, param);
			return scmPurSupplierSource2;
		}
		return null;
	}
	
	@Override
	public ScmPurSupplierSource2 doUnSubmit(ScmPurSupplierSource2 scmPurSupplierSource, Param param)
			throws AppException {
		ScmPurSupplierSource2 purPrice = null;
		if(scmPurSupplierSource.getId()>0){
			purPrice = this.selectDirect(scmPurSupplierSource.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurSupplierSource2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurSupplierSource2.FN_BILLNO,new QueryParam(ScmPurSupplierSource2.FN_BILLNO, QueryParam.QUERY_EQ,scmPurSupplierSource.getBillNo()));
			
			List<ScmPurSupplierSource2> scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		
		if(purPrice!=null){
			BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "PurSupplierSrc", param);
			boolean needAudit = false;
			if(billType!=null && billType.isNeedAudit() && StringUtils.isNotBlank(purPrice.getWorkFlowNo()))
				needAudit = true;
			
			//单据设置审批流程，但设置没有审批节点，状态为通过，可以取消通过
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put(ScmBillPending.FN_BILLID, new QueryParam(ScmBillPending.FN_BILLID, QueryParam.QUERY_EQ, String.valueOf(purPrice.getId())));
			map.put(ScmBillPending.FN_BILLTYPE, new QueryParam(ScmBillPending.FN_BILLTYPE, QueryParam.QUERY_EQ, billType.getBillCode()));
			List<ScmBillPending> scmBillPendingList = scmBillPendingBiz.findAll(map, param);

			if(needAudit){
				if (scmBillPendingList == null || scmBillPendingList.isEmpty()) {
					if(!StringUtils.equals(purPrice.getStatus(),"A"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				} else {
					if(!StringUtils.equals(purPrice.getStatus(),"D"))
						throw new AppException("iscm.purchasemanage.error.cancelCommit");
				}
			}else{
				if(!StringUtils.equals(purPrice.getStatus(),"A"))
					throw new AppException("iscm.purchasemanage.error.cancelCommit");
			} 
			if(purPrice.getStatus().equals("A") || purPrice.getStatus().equals("D")){
				if(param.getUsrCode()!=null ){
					purPrice.setChecker(null);
					purPrice.setSubmitter(null);
				}
				purPrice.setCheckDate(null);
				purPrice.setSubmitDate(null);
				purPrice.setStatus("I");
				try{
					this.updateStatus(purPrice, param);
					scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return purPrice;
	}

	@Override
	public ScmPurSupplierSource2 doAudit(CommonAuditParams commonAuditParams, Param param) throws AppException {
		ScmPurSupplierSource2 purPrice = null;
		
		ScmPurSupplierSource2 scmPurPrice = new ScmPurSupplierSource2();
		scmPurPrice.setId(commonAuditParams.getBillId());
		scmPurPrice.setBillNo(commonAuditParams.getBillNo());
		String opinion = commonAuditParams.getOpinion();
		if(StringUtils.equals("Y", opinion) || StringUtils.equals("agree", opinion)) {
			opinion="agree";
		}else if(StringUtils.equals("N", opinion) || StringUtils.equals("refuse", opinion)) {
			opinion="refuse";
		}
		if(scmPurPrice.getId()>0){
			purPrice = this.selectDirect(scmPurPrice.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurSupplierSource2.FN_BILLNO,
					new QueryParam(ScmPurSupplierSource2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmPurPrice.getBillNo()));
			map.put(ScmPurPrice2.FN_CONTROLUNITNO, new QueryParam(ScmPurSupplierSource2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
							param.getControlUnitNo()));
			List<ScmPurSupplierSource2> scmPurPriceList =this.findAll(map, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice = scmPurPriceList.get(0);
			}
		}
		
		if (purPrice != null) {
			this.setConvertMap(purPrice,param);
			if(!(purPrice.getStatus().equals("D") || purPrice.getStatus().equals("P"))){
				throw new AppException("iscm.purchasemanage.error.audit");
			}
			if(StringUtils.equals("R", opinion)) {
				scmBillPendingBiz.deletePendingBill(param.getUsrCode(),purPrice, param);
				commonEventHistoryBiz.updateInvalid(purPrice,"",param);
				
				CommonAuditOpinion commonAuditOpinionR = new CommonAuditOpinion();
				commonAuditOpinionR.setPreStepNo(purPrice.getStepNo());
				String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
				commonAuditOpinionR.setStepNo(stepNo);
				commonAuditOpinionR.setActiveType("A");
				commonAuditOpinionR.setOpinion(commonAuditParams.getOpinion());
				commonAuditOpinionR.setRemarks(commonAuditParams.getOpinionRemarks());
				commonEventHistoryBiz.addEventHistory(purPrice, commonAuditOpinionR, param);	
				
				//驳回则将单据变回新单状态
				purPrice.setStatus("I");
				purPrice.setChecker(null);
				purPrice.setCheckDate(null);
				return this.updateDirect(purPrice, param);
			}
			String processInstanceId = purPrice.getWorkFlowNo();
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
					purPrice.setStatus("A");
				} else {
					purPrice.setStatus("P");
				}
			} else {
				purPrice.setStatus("N");
			}
			scmBillPendingBiz.updatePendingBill(param.getUsrCode(), purPrice, param);
			purPrice.setChecker(param.getUsrCode());
			if ("agree".equals(opinion)) {
				String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
				if(StringUtils.isNotBlank(usrList)) {
					scmBillPendingBiz.addPendingBill(usrList, purPrice, param);
					PurPriceParams purPriceParams = new PurPriceParams();
					purPriceParams.setPmNo(purPrice.getBillNo());
					AuditMsgUtil.sendAuditMsg("PurSupplierSrc",purPrice,purPriceParams, usrList, param);
				}
			}
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setPreStepNo(purPrice.getStepNo());
			String stepNo = StringUtils.replace(UUID.randomUUID().toString(), "-", "");
			try {
				purPrice.setStepNo(stepNo);
				purPrice.setCheckDate(CalendarUtil.getDate(param));
				this.updateStatus(purPrice, param);
			} catch (Exception e) {
				throw e;
			}
			commonAuditOpinion.setStepNo(stepNo);
			commonAuditOpinion.setActiveType("A");
			commonAuditOpinion.setOpinion(commonAuditParams.getOpinion());
			commonAuditOpinion.setRemarks(commonAuditParams.getOpinionRemarks());
			commonEventHistoryBiz.addEventHistory(purPrice, commonAuditOpinion, param);
			if(StringUtils.contains("A,B", purPrice.getStatus())) {
				//通过或部分通过时检查是否自动下达
				BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "PurSupplierSrc", param);
				if(billType!=null && billType.isAutoRelease()) {
					this.doRelease(purPrice, param);
				}
			}
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return purPrice;
	}

	@Override
	public ScmPurSupplierSource2 doUnAudit(ScmPurSupplierSource2 scmPurSupplierSource, Param param)
			throws AppException {
		ScmPurSupplierSource2 purPrice = null;
		List<ScmPurSupplierSource2> scmPurPriceList = new ArrayList<> ();
		boolean isFirstTask = false;
		
		if(scmPurSupplierSource.getId()>0){
			purPrice = this.selectDirect(scmPurSupplierSource.getId(), param);
		}else{
			HashMap<String,Object> map=new HashMap<String,Object>();
			map.put(
					ScmPurSupplierSource2.FN_BILLNO,
					new QueryParam(ScmPurSupplierSource2.FN_BILLNO, QueryParam.QUERY_EQ,
							scmPurSupplierSource.getBillNo()));
			map.put(ScmPurPrice2.FN_CONTROLUNITNO, new QueryParam(ScmPurPrice2.FN_CONTROLUNITNO, QueryParam.QUERY_EQ,
					param.getControlUnitNo()));
			
			scmPurPriceList =this.findAll(map, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		
		if (purPrice != null) {
			if(!StringUtils.contains("A,B,N,P", purPrice.getStatus())){
				throw new AppException("iscm.purchasemanage.unaudit.error.status");
			}
			String processInstanceId = purPrice.getWorkFlowNo();
			List<ScmPurSupplierSourceEntry2> scmPurPriceEntryList = scmPurSupplierSourceEntryBiz.selectByBillId(purPrice.getId(), param);
			
			if(scmPurPriceEntryList == null || scmPurPriceEntryList.isEmpty()){
				//抛出异常处理
				throw new AppException("iscm.inventorymanage.AllocationApplication.service.Impl.ScmInvMaterialRequestBillBizImpl.generateMaterialReqBill.notExitMaterialRequestBillDetail");
			}
			
			if (processInstanceId != null && !"".equals(processInstanceId)) {
				//反审批当前已审节点
				CommonBean bean = new CommonBean();
				scmPurPriceList.add(purPrice);
				bean.setList(scmPurPriceList);
				bean.setList2(scmPurPriceEntryList);
				BillType2 billType = billTypeBiz.selectByOrgAndCode(purPrice.getFinOrgUnitNo(), "PurSupplierSrc", param);
				long billTypeId = 0;
				if (billType!=null && billType.isNeedAudit()) {
					billTypeId = billType.getId();
				}
				
				processInstanceId = ActivityUtil.antiAuditProcess(bean, billTypeId, processInstanceId, param.getUsrCode(), param);
				isFirstTask = ActivityUtil.isFirstTask(processInstanceId, param.getUsrCode(),  param);
				purPrice.setWorkFlowNo(processInstanceId);
				
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
			
			purPrice.setStatus(status);
			String tableName = ClassUtils.getFinalModelSimpleName(purPrice);
			CommonEventHistory commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,purPrice.getStepNo(),purPrice.getPK(),param);
			if(commonEventHistory!=null && StringUtils.isNotBlank(commonEventHistory.getPreStepNo())) {
				commonEventHistory = commonEventHistoryBiz.loadAuditEventHistory(tableName,commonEventHistory.getPreStepNo(),purPrice.getPK(),param);
			}
			if(commonEventHistory!=null) {
				purPrice.setChecker(commonEventHistory.getOper());
				purPrice.setCheckDate(commonEventHistory.getOperDate());
			}else {
				purPrice.setChecker(null);
				purPrice.setCheckDate(null);
			}
			this.updateStatus(purPrice, param);
			scmBillPendingBiz.cancelPendingBill(param.getUsrCode(),purPrice, param);
			commonEventHistoryBiz.updateInvalid(purPrice,purPrice.getStepNo(),param);
			CommonAuditOpinion commonAuditOpinion = new CommonAuditOpinion();
			commonAuditOpinion.setStepNo(purPrice.getStepNo());
			commonAuditOpinion.setActiveType("U");
			commonAuditOpinion.setOpinion("Y");
			commonEventHistoryBiz.addEventHistory(purPrice, commonAuditOpinion, param);
		} else {
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		
		return purPrice;
	}

	@Override
	public ScmPurSupplierSource2 doRelease(ScmPurSupplierSource2 scmPurSupplierSource, Param param)
			throws AppException {
		ScmPurSupplierSource2 purPrice = null;
		if(scmPurSupplierSource.getId()>0){
			purPrice = this.selectDirect(scmPurSupplierSource.getId(),param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurSupplierSource2.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurSupplierSource2.FN_BILLNO,new QueryParam(ScmPurSupplierSource2.FN_BILLNO, QueryParam.QUERY_EQ,scmPurSupplierSource.getBillNo()));
			
			List<ScmPurSupplierSource2> scmPurPriceList =this.findPage(page, param);
			if(scmPurPriceList!=null && !scmPurPriceList.isEmpty()){
				purPrice=scmPurPriceList.get(0);
			}
		}
		if(purPrice!=null){
			if(!purPrice.getStatus().equals("A")){
				throw new AppException("iscm.purchasemanage.error.release");
			}else if(purPrice.getStatus().equals("A")){
				purPrice.setStatus("E");
				try{
					this.updateStatus(purPrice, param);
					this.afterRelease(purPrice, param);
				}catch(AppException e){
					throw e;
				}
			}
			
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		
		return purPrice;
	}
	
	private void afterRelease(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException {
		if(scmPurSupplierSource != null){
			scmPurSupplyInfoBiz.generateBySupplierSource(scmPurSupplierSource,param);
		}
	}

	@Override
	public ScmPurSupplierSource2 queryPurSupplierSource(ScmPurSupplierSource2 scmPurSupplierSource, Param param)
			throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurSupplierSource2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurSupplierSource2.FN_BILLNO,new QueryParam(ScmPurSupplierSource2.FN_BILLNO, QueryParam.QUERY_EQ,scmPurSupplierSource.getBillNo()));
		
		List<ScmPurSupplierSource2> scmPurSupplierSourceList =this.findPage(page, param);
		ScmPurSupplierSource2 scmPurSupplierSource2 = new ScmPurSupplierSource2();
		if(scmPurSupplierSourceList!=null && !scmPurSupplierSourceList.isEmpty()){
			scmPurSupplierSource2 = scmPurSupplierSourceList.get(0);
			scmPurSupplierSource2.setScmPurSupplierSourceEntryList(scmPurSupplierSourceEntryBiz.selectByBillId(scmPurSupplierSource2.getId(), param));
			scmPurSupplierSource2.setScmPurSupplierSourceRecOrgList(scmPurSupplierSourceRecOrgBiz.selectByBillId(scmPurSupplierSource2.getId(), param));
			//待审接口增加待审人
			ScmBillPending2 scmBillPendingUsr = scmBillPendingBiz.selectPendingUsr(scmPurSupplierSource2.getId(), "PurSupplierSrc",param);
			if (scmBillPendingUsr != null) {
				scmPurSupplierSource2.setPendingUsr(scmBillPendingUsr.getUsrCodes());
			}
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurSupplierSource2;
	}
}
