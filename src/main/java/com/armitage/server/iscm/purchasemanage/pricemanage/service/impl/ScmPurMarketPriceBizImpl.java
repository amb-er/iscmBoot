
package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceDetailParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceEditParams;
import com.armitage.server.api.business.purmarketprice.params.PurMarketPriceParams;
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
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.service.ScmBillPendingBiz;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurMarketPriceDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurInquiryGroupBiz;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.Code;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.CodeBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurMarketPriceBiz")
public class ScmPurMarketPriceBizImpl extends BaseBizImpl<ScmPurMarketPrice2> implements ScmPurMarketPriceBiz {
	private ScmPurMarketPriceEntryBiz scmPurMarketPriceEntryBiz;
	private UsrBiz usrBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private ScmPurInquiryGroupBiz scmPurInquiryGroupBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private CodeBiz codeBiz;
	private BillTypeBiz billTypeBiz;
	private ScmBillPendingBiz scmBillPendingBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	
	public void setScmPurMarketPriceEntryBiz(
			ScmPurMarketPriceEntryBiz scmPurMarketPriceEntryBiz) {
		this.scmPurMarketPriceEntryBiz = scmPurMarketPriceEntryBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}

	public void setScmPurInquiryGroupBiz(ScmPurInquiryGroupBiz scmPurInquiryGroupBiz) {
		this.scmPurInquiryGroupBiz = scmPurInquiryGroupBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public void setCodeBiz(CodeBiz codeBiz) {
		this.codeBiz = codeBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	public void setScmBillPendingBiz(ScmBillPendingBiz scmBillPendingBiz) {
		this.scmBillPendingBiz = scmBillPendingBiz;
	}

	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class) + "." + ScmPurMarketPrice2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class) + "." + ScmPurMarketPrice2.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class) + "." + ScmPurMarketPrice2.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class) + "." + ScmPurMarketPrice2.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}
	@Override
	protected void beforeDelete(ScmPurMarketPrice2 entity, Param param)
			throws AppException {
		ScmPurMarketPrice2 scmPurMarketPrice = this.selectDirect(entity.getId(), param);
		if(scmPurMarketPrice!=null && !StringUtils.equals(scmPurMarketPrice.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getPiNo()}));
		}
	}

	@Override
	protected void afterSelect(ScmPurMarketPrice2 entity, Param param) throws AppException {
		setConvertMap(entity, param);
	}
	
	@Override
	public void doAdvQuery(Page page, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurMarketPriceAdvQuery) {
				ScmPurMarketPriceAdvQuery scmPurMarketPriceAdvQuery = (ScmPurMarketPriceAdvQuery) page.getModel();
				if(StringUtils.isNotBlank(scmPurMarketPriceAdvQuery.getPiNo())){
					page.getParam().put(ScmPurMarketPrice2.FN_PINO, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class)+"."+ScmPurMarketPrice2.FN_PINO, QueryParam.QUERY_LIKE,"%"+scmPurMarketPriceAdvQuery.getPiNo()+"%"));
				}
				if(scmPurMarketPriceAdvQuery.getBizDateFrom()!=null && scmPurMarketPriceAdvQuery.getBizDateTo()!=null){
					page.getParam().put(ScmPurMarketPrice2.FN_PIDATE, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class)+"."+ScmPurMarketPrice2.FN_PIDATE, QueryParam.QUERY_BETWEEN,FormatUtils.fmtDate(scmPurMarketPriceAdvQuery.getBizDateFrom()),FormatUtils.fmtDate(scmPurMarketPriceAdvQuery.getBizDateTo())));
				}
				if(StringUtils.isNotBlank(scmPurMarketPriceAdvQuery.getEnquiryCode())){
					page.getParam().put(ScmPurMarketPrice2.FN_ENQUIRYCODE, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class)+"."+ScmPurMarketPrice2.FN_ENQUIRYCODE, QueryParam.QUERY_EQ,scmPurMarketPriceAdvQuery.getEnquiryCode()));
				}
				if(StringUtils.isNotBlank(scmPurMarketPriceAdvQuery.getStatus())){
					String status[] = StringUtils.split(scmPurMarketPriceAdvQuery.getStatus(), ",");
					StringBuffer statusBuffer = new StringBuffer("");
					for(String sta:status){
						if(StringUtils.isNotBlank(statusBuffer.toString()))
							statusBuffer.append(",");
						statusBuffer.append("'").append(sta).append("'");
					}
					if(StringUtils.isNotBlank(statusBuffer.toString())){
						page.getParam().put(ScmPurMarketPrice2.FN_STATUS, new QueryParam(ClassUtils.getFinalModelSimpleName(ScmPurMarketPrice2.class)+"."+ScmPurMarketPrice2.FN_STATUS, QueryParam.QUERY_IN, statusBuffer.toString()));
					}
				}
			}
		}
	}
	
	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page,HashMap<String, Object> map, Param param) {
		if (page.getModel() != null) {
			if (page.getModel() instanceof ScmPurMarketPriceAdvQuery) {
				ScmPurMarketPriceAdvQuery scmPurMarketPriceAdvQuery = (ScmPurMarketPriceAdvQuery) page.getModel();
				if(scmPurMarketPriceAdvQuery.isFromInterface())
					map.put("sortByDate", "Y");
			}
		}
		return map;
	}
	@Override
	protected void beforeAdd(ScmPurMarketPrice2 entity, Param param) throws AppException {
		if(entity != null && StringUtils.isBlank(entity.getPiNo())){
			entity.setCreateDate(CalendarUtil.getDate(param));
			entity.setCreator(param.getUsrCode());
			String code = CodeAutoCalUtil.getBillCode("PurMarketPrice", entity, param);
			entity.setPiNo(code);
			//获取财务组织以及币别
	        List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
	        if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
	        entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
	        entity.setCurrencyNo(StringUtils.isBlank(orgCompanyList.get(0).getBaseCurrency())?"RMB":orgCompanyList.get(0).getBaseCurrency());
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurMarketPrice scmPurMarketPrice = (ScmPurMarketPrice) bean.getList().get(0);
		if(scmPurMarketPrice != null && scmPurMarketPrice.getId() > 0){
			bean.setList2(scmPurMarketPriceEntryBiz.selectByPiId(scmPurMarketPrice.getId(), param));
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurMarketPrice scmPurMarketPrice = (ScmPurMarketPrice) bean.getList().get(0);
		if(scmPurMarketPrice != null && scmPurMarketPrice.getId() > 0){
			//新增询价任务明细
			List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = bean.getList2();
			if(scmPurMarketPriceEntryList != null && !scmPurMarketPriceEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurMarketPriceEntry2 scmPurMarketPriceEntry : scmPurMarketPriceEntryList){
					scmPurMarketPriceEntry.setLineId(lineId);
					scmPurMarketPriceEntry.setPiId(scmPurMarketPrice.getId());
					scmPurMarketPriceEntryBiz.add(scmPurMarketPriceEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurMarketPrice scmPurMarketPrice = (ScmPurMarketPrice) bean.getList().get(0);
		if(scmPurMarketPrice != null && scmPurMarketPrice.getId() > 0){
			//更新询价任务明细
			List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = bean.getList2();
			if(scmPurMarketPriceEntryList != null && !scmPurMarketPriceEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurMarketPriceEntry2 scmPurMarketPriceEntry : scmPurMarketPriceEntryList){
					if (StringUtils.equals("I", scmPurMarketPrice.getStatus())) {
						scmPurMarketPriceEntry.setLineId(lineId);
					}
					scmPurMarketPriceEntry.setPiId(scmPurMarketPrice.getId());
					lineId = lineId+1;
				}
				scmPurMarketPriceEntryBiz.update(scmPurMarketPrice, scmPurMarketPriceEntryList, ScmPurMarketPriceEntry2.FN_PIID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmPurMarketPrice2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除询价任务明细
			List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = scmPurMarketPriceEntryBiz.selectByPiId(entity.getId(), param);
			scmPurMarketPriceEntryBiz.delete(scmPurMarketPriceEntryList, param);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()){
			for(ScmPurMarketPrice2 scmPurMarketPrice:(List<ScmPurMarketPrice2>)list){
				setConvertMap(scmPurMarketPrice,param);
			}
		}
	}

	private void setConvertMap(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
		if(scmPurMarketPrice!=null){
			if (StringUtils.isNotBlank(scmPurMarketPrice.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmPurMarketPrice.getCreator(), param);
				if (usr != null) {
					scmPurMarketPrice.setCreatorName(usr.getName());
					scmPurMarketPrice.setConvertMap(ScmPurMarketPrice.FN_CREATOR, usr);
					if(scmPurMarketPrice.getDataDisplayMap()==null){
						scmPurMarketPrice.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurMarketPrice.getDataDisplayMap().put(ScmPurMarketPrice.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				}
			}
			if (StringUtils.isNotBlank(scmPurMarketPrice.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmPurMarketPrice.getEditor(), param);
				if (usr != null) {
					scmPurMarketPrice.setConvertMap(ScmPurMarketPrice.FN_EDITOR, usr);
					scmPurMarketPrice.setEditorName(usr.getName());
				}
			}
			if (StringUtils.isNotBlank(scmPurMarketPrice.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmPurMarketPrice.getChecker(), param);
				if (usr != null) {
					scmPurMarketPrice.setConvertMap(ScmPurMarketPrice.FN_CHECKER, usr);
					scmPurMarketPrice.setCheckerName(usr.getName());
				}
			}
			if(scmPurMarketPrice.getEnquiryGroupId()>0){
				ScmPurInquiryGroup scmPurInquiryGroup = scmPurInquiryGroupBiz.selectDirect(scmPurMarketPrice.getEnquiryGroupId(), param);
				if(scmPurInquiryGroup!=null){
					scmPurMarketPrice.setConvertMap(ScmPurMarketPrice.FN_ENQUIRYGROUPID, scmPurInquiryGroup);
					scmPurMarketPrice.setEnquiryGroupName(scmPurInquiryGroup.getGroupName());
				}
			}
			if (StringUtils.isNotBlank(scmPurMarketPrice.getEnquiryCode())){
				//市调负责人
				Usr usr = usrBiz.selectByCode(scmPurMarketPrice.getEnquiryCode(), param);
				if (usr != null) {
					scmPurMarketPrice.setConvertMap(ScmPurMarketPrice.FN_ENQUIRYCODE, usr);
					scmPurMarketPrice.setEnquiryName(usr.getName());
				}
			}
			if(StringUtils.isNotBlank(scmPurMarketPrice.getStatus())){
				Code code = codeBiz.selectByCategoryAndCode("quotationStatus", scmPurMarketPrice.getStatus());
				if(code!=null)
					scmPurMarketPrice.setStatusName(code.getName());
			}
			
		}
	}

	@Override
	public ScmPurMarketPrice2 submit(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
		ScmPurMarketPrice2 require = null;
		if(scmPurMarketPrice.getId()>0){
			require = this.selectDirect(scmPurMarketPrice.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurMarketPrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurMarketPrice.FN_PINO,
					new QueryParam(ScmPurMarketPrice.FN_PINO, QueryParam.QUERY_EQ,
							scmPurMarketPrice.getPiNo()));
			
			List<ScmPurMarketPrice2> scmPurMarketPriceList =this.findPage(page, param);
			if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
				require=scmPurMarketPriceList.get(0);
			}
		}
		if(require!=null){
			if(!StringUtils.equals("I",require.getStatus())) {
				throw new AppException("iscm.purchasemanage.error.commit");
			}else if(require.getStatus().equals("I")){
				List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, require.getOrgUnitNo(), false, null, param);
				if(orgCompanyList==null || orgCompanyList.isEmpty()){
					throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
				}			
				BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "PurMarketPrice", param);
				boolean needAudit = false;
				if(billType!=null && billType.isNeedAudit())
					needAudit = true;
				
				if(needAudit){
					//发起流程
					List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = scmPurMarketPriceEntryBiz.selectByPiId(require.getId(), param);
					List<ScmPurMarketPrice2> scmPurMarketPriceList = new ArrayList();
					scmPurMarketPriceList.add(require);
					CommonBean bean = new CommonBean();
					bean.setList(scmPurMarketPriceList);
					bean.setList2(scmPurMarketPriceEntryList);
					String processInstanceId = ActivityUtil.startProcessInstance(bean, billType.getId(), billType.getWorkFlowNo(), param);
					require.setWorkFlowNo(processInstanceId);
					ActivityUtil activityUtil = new ActivityUtil();
					//判断当前流程是否结束
					boolean isCompleteTask = activityUtil.queryProInstanceStateByProInstanceId(processInstanceId);
					if(isCompleteTask) {
						if(param.getUsrCode()!=null ){
							require.setChecker(param.getUsrCode());
							require.setSubmitter(param.getUsrCode());
						}
						require.setSubmitDate(CalendarUtil.getDate(param));
						require.setCheckDate(CalendarUtil.getDate(param));
						require.setStatus("A");
					}else {
						if(param.getUsrCode()!=null ){
							require.setSubmitter(param.getUsrCode());
						}
						require.setSubmitDate(CalendarUtil.getDate(param));
						require.setStatus("D");
						String usrList= ActivityUtil.findAssigneeByProcessInstanceId(processInstanceId,param);
						if(StringUtils.isNotBlank(usrList)) {
							scmBillPendingBiz.addPendingBill(usrList, require, param);
							PurMarketPriceParams purMarketPriceParams = new PurMarketPriceParams();
							purMarketPriceParams.setPiNo(require.getPiNo());
							AuditMsgUtil.sendAuditMsg(billType.getBillCode(), require, purMarketPriceParams, usrList, param);
						}
					}
				}else{
					if(param.getUsrCode()!=null ){
						require.setChecker(param.getUsrCode());
						require.setSubmitter(param.getUsrCode());
					}
					require.setSubmitDate(CalendarUtil.getDate(param));
					require.setCheckDate(CalendarUtil.getDate(param));
					require.setStatus("A");
				}
				
				try{
					this.updateStatus(require, param);
				}catch(AppException e){
					throw e;
				}
				if(StringUtils.contains("A,B", require.getStatus())) {
					//通过或部分通过时检查是否自动下达
					if(billType!=null && billType.isAutoRelease()) {
						this.release(require, param);
					}
				}
			}
		}else{
			throw new AppException("iscm.purchasemanage.purchasebusiness.find.ordernotexists");
		}
		return require;
	}

	@Override
	public ScmPurMarketPrice2 undoSubmit(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
		ScmPurMarketPrice2 require = null;
		if(scmPurMarketPrice.getId()>0){
			require = this.selectDirect(scmPurMarketPrice.getId(), param);
		}else{
			Page page=new Page();
			page.setModelClass(ScmPurMarketPrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(
					ScmPurMarketPrice.FN_PINO,
					new QueryParam(ScmPurMarketPrice.FN_PINO, QueryParam.QUERY_EQ,
							scmPurMarketPrice.getPiNo()));
			
			List<ScmPurMarketPrice2> scmPurMarketPriceList =this.findPage(page, param);
			if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
				require=scmPurMarketPriceList.get(0);
			}
		}
		if(require!=null){
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, require.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}			
			BillType2 billType = billTypeBiz.selectByOrgAndCode(orgCompanyList.get(0).getOrgUnitNo(), "PurMarketPrice", param);
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
				if(param.getUsrCode()!=null ){
					require.setChecker(null);
					require.setSubmitter(null);
				}
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
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return require;
	}

	@Override
	public ScmPurMarketPrice2 release(ScmPurMarketPrice2 scmPurMarketPrice,Param param) throws AppException {
		if(!StringUtils.equals("A",scmPurMarketPrice.getStatus()))
			throw new AppException("iscm.purchasemanage.error.release");
		scmPurMarketPrice.setStatus("E");
		return this.updateDirect(scmPurMarketPrice, param);
	}

	@Override
	public ScmPurMarketPrice2 undoRelease(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
		if(!StringUtils.equals("E",scmPurMarketPrice.getStatus()))
			throw new AppException("iscm.purchasemanage.error.cancelRelease");
		scmPurMarketPrice.setStatus("A");
		return this.updateDirect(scmPurMarketPrice, param);
	}

	@Override
	public List<ScmPurMarketPrice2> selectRecentPrice(long itemId, Date begDate,Date endDate, String enquiryGroupIds, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		map.put("enquiryGroupIds", enquiryGroupIds);
		return ((ScmPurMarketPriceDAO)dao).selectRecentPrice(map);
	}

	@Override
	public List<ScmPurMarketPrice2> queryPurMarketPriceList(ScmPurMarketPriceAdvQuery scmPurMarketPriceAdvQuery, int pageNum,Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurMarketPrice2.class);
		if (pageNum == -1) {
			page.setPagePos(1);
			page.setShowCount(Integer.MAX_VALUE);
		} else {
			page.setPagePos(pageNum);
			page.setShowCount(20);
		}
		page.setModel(scmPurMarketPriceAdvQuery);	//findPage时底层会触发doAdvQuery，增加查询条件
		
		return this.findPage(page, param);
	}

	@Override
	public ScmPurMarketPrice2 queryPurMarketPrice(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
		Page page=new Page();
		page.setModelClass(ScmPurMarketPrice2.class);
		page.setShowCount(Integer.MAX_VALUE);
		page.getParam().put(ScmPurMarketPrice2.FN_PINO,new QueryParam(ScmPurMarketPrice2.FN_PINO, QueryParam.QUERY_EQ,scmPurMarketPrice.getPiNo()));
		
		List<ScmPurMarketPrice2> scmPurMarketPriceList =this.findPage(page, param);
		ScmPurMarketPrice2 scmPurMarketPrice2 = new ScmPurMarketPrice2();
		if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
			scmPurMarketPrice2 = scmPurMarketPriceList.get(0);
			scmPurMarketPrice2.setScmPurMarketPriceEntryList(scmPurMarketPriceEntryBiz.selectByPiId(scmPurMarketPrice2.getId(), param));
		}else{
			throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
		}
		return scmPurMarketPrice2;
	}

	@Override
	public ScmPurMarketPrice2 doEditPurMarketPrice(PurMarketPriceEditParams purMarketPriceEditParams, Param param) throws AppException {
		if(purMarketPriceEditParams!=null){
			CommonBean bean = new CommonBean();
			
			Page page=new Page();
			page.setModelClass(ScmPurMarketPrice.class);
			page.setShowCount(Integer.MAX_VALUE);
			page.getParam().put(ScmPurMarketPrice.FN_PINO,new QueryParam(ScmPurMarketPrice.FN_PINO, QueryParam.QUERY_EQ,purMarketPriceEditParams.getPiNo()));
			List<ScmPurMarketPrice2> scmPurMarketPriceList =this.findPage(page, param);
			if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
				//更新主表数据
				ScmPurMarketPrice2 scmPurMarketPrice = scmPurMarketPriceList.get(0);
				BeanUtils.copyProperties(purMarketPriceEditParams, scmPurMarketPrice);
				if(scmPurMarketPrice.getPiDate() != null){
					scmPurMarketPrice.setPiDate(FormatUtils.parseDate(FormatUtils.fmtDate(scmPurMarketPrice.getPiDate())));
				}
				scmPurMarketPrice.setEditDate(CalendarUtil.getDate(param));
				scmPurMarketPrice.setEditor(param.getUsrCode());
				bean.setList(scmPurMarketPriceList);
				List<PurMarketPriceDetailParams> detailList = purMarketPriceEditParams.getDetailList();
				List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = scmPurMarketPriceEntryBiz.selectByPiId(scmPurMarketPrice.getId(), param);
				if(scmPurMarketPriceEntryList==null || scmPurMarketPriceEntryList.isEmpty()){
					scmPurMarketPriceEntryList = new ArrayList<>();
					StringBuffer itemNos = new StringBuffer("");
					for(PurMarketPriceDetailParams detailParams:detailList){
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
			        argList.add("orgUnitNo="+param.getOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
					for(PurMarketPriceDetailParams detailParams:detailList){
						ScmPurMarketPriceEntry2 scmPurMarketPriceEntry = new ScmPurMarketPriceEntry2(true);
						BeanUtils.copyProperties(detailParams, scmPurMarketPriceEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmPurMarketPriceEntry.setItemId(scmMaterial.getId());
								scmPurMarketPriceEntry.setPurUnit(scmMaterial.getPurUnitId());
								break;
							}
						}
						scmPurMarketPriceEntryList.add(scmPurMarketPriceEntry);
					}
				}else{
					//先删除不存在行的记录
					for(int i = scmPurMarketPriceEntryList.size()-1;i>=0;i--){
						ScmPurMarketPriceEntry2 scmPurMarketPriceEntry2 = scmPurMarketPriceEntryList.get(i);
						boolean exists = false;
						for(PurMarketPriceDetailParams detailParams:detailList){
							if(detailParams.getLineId()==scmPurMarketPriceEntry2.getLineId()){
								exists = true;
								break;
							}
						}
						if(!exists)
							scmPurMarketPriceEntryList.remove(i);
					}
					StringBuffer itemNos = new StringBuffer("");
					for(PurMarketPriceDetailParams detailParams:detailList){
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
			        argList.add("orgUnitNo="+param.getOrgUnitNo());
			        argList.add("controlUnitNo=" + param.getControlUnitNo());
			        List<ScmMaterial2> scmMaterialList = scmMaterialBiz.queryPage(page, argList, "findByPurAllPage", param);
					//更新时是根据行号进行更新的
					int lineId = 0;
					for(PurMarketPriceDetailParams detailParams:detailList){
						ScmPurMarketPriceEntry2 scmPurMarketPriceEntry = new ScmPurMarketPriceEntry2(true);
						boolean exists = false;
						for(ScmPurMarketPriceEntry2 scmPurMarketPriceEntry2:scmPurMarketPriceEntryList){
							if(detailParams.getLineId()==scmPurMarketPriceEntry2.getLineId() && detailParams.getLineId()!=0){
								scmPurMarketPriceEntry = scmPurMarketPriceEntry2;
								exists = true;
								break;
							}
						}
						BeanUtils.copyProperties(detailParams, scmPurMarketPriceEntry);
						for(ScmMaterial2 scmMaterial:scmMaterialList){
							if(StringUtils.equals(scmMaterial.getItemNo(), detailParams.getItemNo())){
								scmPurMarketPriceEntry.setItemId(scmMaterial.getId());
								scmPurMarketPriceEntry.setPurUnit(scmMaterial.getPurUnitId());
								break;
							}
						}
						if(!exists)
							scmPurMarketPriceEntryList.add(lineId, scmPurMarketPriceEntry);
						lineId = lineId +1;
					}
				}
				//更新子表数据
				bean.setList2(scmPurMarketPriceEntryList);
				this.update(bean, param);
				return scmPurMarketPrice;
			}else{
				throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
			}
		}else{
			throw new AppException("webservice.params.null");
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurMarketPrice2 entity = (ScmPurMarketPrice2) bean.getList().get(0);
		if(entity!=null){
			ScmPurMarketPrice2 scmPurMarketPrice = this.select(entity.getPK(), param);
			if(!StringUtils.equals(scmPurMarketPrice.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			//获取财务组织以及币别
	        List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
	        if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
	        entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		}
	}

    @Override
    public ScmPurMarketPrice2 finish(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
        ScmPurMarketPrice2 purMarketPrice = null;
        if(scmPurMarketPrice.getId()>0){
            purMarketPrice = this.selectDirect(scmPurMarketPrice.getId(),param);
        }else{
            Page page=new Page();
            page.setModelClass(ScmPurMarketPrice2.class);
            page.setShowCount(Integer.MAX_VALUE);
            page.getParam().put(ScmPurMarketPrice2.FN_PINO,new QueryParam(ScmPurMarketPrice2.FN_PINO, QueryParam.QUERY_EQ,scmPurMarketPrice.getPiNo()));
            
            List<ScmPurMarketPrice2> scmPurMarketPriceList =this.findPage(page, param);
            if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
                purMarketPrice=scmPurMarketPriceList.get(0);
            }
        }
        if(purMarketPrice!=null){
            if(!purMarketPrice.getStatus().equals("E")){
                throw new AppException("iscm.purchasemanage.error.finish");
            }else if(purMarketPrice.getStatus().equals("E")){
                purMarketPrice.setStatus("C");
                try{
                    this.updateStatus(purMarketPrice, param);
                }catch(AppException e){
                    throw e;
                }
            }
        }else{
            throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
        }
        return purMarketPrice;
    }

    @Override
    public ScmPurMarketPrice2 undoFinish(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
        ScmPurMarketPrice2 purMarketPrice = null;
        if(scmPurMarketPrice.getId()>0){
            purMarketPrice = this.selectDirect(scmPurMarketPrice.getId(),param);
        }else{
            Page page=new Page();
            page.setModelClass(ScmPurMarketPrice2.class);
            page.setShowCount(Integer.MAX_VALUE);
            page.getParam().put(ScmPurMarketPrice2.FN_PINO,new QueryParam(ScmPurMarketPrice2.FN_PINO, QueryParam.QUERY_EQ,scmPurMarketPrice.getPiNo()));
            
            List<ScmPurMarketPrice2> scmPurMarketPriceList =this.findPage(page, param);
            if(scmPurMarketPriceList!=null && !scmPurMarketPriceList.isEmpty()){
                purMarketPrice=scmPurMarketPriceList.get(0);
            }
        }
        if(purMarketPrice!=null){
            if(!purMarketPrice.getStatus().equals("C")){
                throw new AppException("iscm.purchasemanage.error.cancelFinish");
            }else if(purMarketPrice.getStatus().equals("C")){
                purMarketPrice.setStatus("E");
                try{
                    this.updateStatus(purMarketPrice, param);
                }catch(AppException e){
                    throw e;
                }
            }
        }else{
            throw new AppException("iscm.purchasemanage.pricemanage.find.ordernotexists");
        }
        return purMarketPrice;
    }
    
    public ScmPurMarketPrice2 updateStatus(ScmPurMarketPrice2 scmPurMarketPrice, Param param) throws AppException {
        if(scmPurMarketPrice != null){
            return this.updateDirect(scmPurMarketPrice, param);
        }
        return null;
    }

	@Override
	public List<ScmPurMarketPrice2> selectItemsRecentPrice(String itemIds, Date begDate, Date endDate, String enquiryGroupIds)
			throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("itemId", itemIds);
		map.put("begDate", FormatUtils.fmtDate(begDate));
		map.put("endDate", FormatUtils.fmtDate(endDate));
		map.put("enquiryGroupIds", enquiryGroupIds);
		return ((ScmPurMarketPriceDAO)dao).selectItemsRecentPrice(map);
	}

	@Override
	public ScmPurMarketPrice2 updatePrtCount(ScmPurMarketPrice2 scmPurMarketPrice, Param param)
			throws AppException {
		if(scmPurMarketPrice.getId()>0){
			ScmPurMarketPrice2 bill = this.selectDirect(scmPurMarketPrice.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurMarketPrice;
	}

}
