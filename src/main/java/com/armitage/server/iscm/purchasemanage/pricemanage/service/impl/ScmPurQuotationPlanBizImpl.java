
package com.armitage.server.iscm.purchasemanage.pricemanage.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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
import com.armitage.server.iscm.basedata.util.ScmMaterialUtil;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvMoveReqBill;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPriceEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotation2;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlan;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanAdvQuery;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurQuotationPlanEntry2;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurMarketPriceBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationPlanBiz;
import com.armitage.server.iscm.purchasemanage.pricemanage.service.ScmPurQuotationPlanEntryBiz;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurOrderEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurOrderEntry2;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.service.ScmPurOrderBiz;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurInquiryGroup;
import com.armitage.server.system.model.BillType2;
import com.armitage.server.system.model.OrgCompany2;
import com.armitage.server.system.model.OrgPurchase2;
import com.armitage.server.system.service.BillTypeBiz;
import com.armitage.server.system.service.OrgPurchaseBiz;
import com.armitage.server.system.service.OrgUnitRelationBiz;
import com.armitage.server.system.util.CodeAutoCalUtil;
import com.armitage.server.system.util.OrgUnitRelationType;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmPurQuotationPlanBiz")
public class ScmPurQuotationPlanBizImpl extends BaseBizImpl<ScmPurQuotationPlan> implements ScmPurQuotationPlanBiz {
	private ScmPurQuotationPlanEntryBiz scmPurQuotationPlanEntryBiz;
	private UsrBiz usrBiz;
	private ScmPurMarketPriceBiz scmPurMarketPriceBiz;
	private ScmPurOrderBiz scmPurOrderBiz;
	private OrgUnitRelationBiz orgUnitRelationBiz;
	private OrgPurchaseBiz orgPurchaseBiz;
	private BillTypeBiz billTypeBiz;
	
	public void setScmPurQuotationPlanEntryBiz(
			ScmPurQuotationPlanEntryBiz scmPurQuotationPlanEntryBiz) {
		this.scmPurQuotationPlanEntryBiz = scmPurQuotationPlanEntryBiz;
	}

	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}
	
	public void setScmPurMarketPriceBiz(ScmPurMarketPriceBiz scmPurMarketPriceBiz) {
		this.scmPurMarketPriceBiz = scmPurMarketPriceBiz;
	}

	public void setScmPurOrderBiz(ScmPurOrderBiz scmPurOrderBiz) {
		this.scmPurOrderBiz = scmPurOrderBiz;
	}

	public void setOrgUnitRelationBiz(OrgUnitRelationBiz orgUnitRelationBiz) {
		this.orgUnitRelationBiz = orgUnitRelationBiz;
	}
	
	public void setOrgPurchaseBiz(OrgPurchaseBiz orgPurchaseBiz) {
		this.orgPurchaseBiz = orgPurchaseBiz;
	}

	public void setBillTypeBiz(BillTypeBiz billTypeBiz) {
		this.billTypeBiz = billTypeBiz;
	}

	@Override
	protected void afterSelect(ScmPurQuotationPlan entity, Param param) throws AppException {
		setConvertMap(entity, param);
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
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurQuotationPlan.class) + "." + ScmPurQuotationPlan.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurQuotationPlan.class) + "." + ScmPurQuotationPlan.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmPurQuotationPlan.class) + "." + ScmPurQuotationPlan.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmPurQuotationPlan.class) + "." + ScmPurQuotationPlan.FN_ORGUNITNO), QueryParam.QUERY_EQ, "0"));
		}
	}

	@Override
	protected void beforeDelete(ScmPurQuotationPlan entity, Param param) throws AppException {
		ScmPurQuotationPlan scmPurQuotationPlan = this.selectDirect(entity.getId(), param);
		if(scmPurQuotationPlan!=null && !StringUtils.equals(scmPurQuotationPlan.getStatus(),"I")){
			throw new AppException(Message.getMessage(param.getLang(), "iscm.inventorymanage.delete.error", new String[]{entity.getPlanNo()}));
		}
	}
	@Override
	protected void beforeAdd(ScmPurQuotationPlan entity, Param param) throws AppException {
		if(entity != null && StringUtils.isBlank(entity.getPlanNo())){
			//获取财务组织
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
			entity.setCreateDate(CalendarUtil.getDate(param));
			entity.setCreator(param.getUsrCode());
			String code = CodeAutoCalUtil.getBillCode("PurQuotationPlan", entity, param);
			entity.setPlanNo(code);
			//获取财务组织以及期间、币别
		}
	}

	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmPurQuotationPlan scmPurQuotationPlan = (ScmPurQuotationPlan) bean.getList().get(0);
		if(scmPurQuotationPlan != null && scmPurQuotationPlan.getId() > 0){
			bean.setList2(scmPurQuotationPlanEntryBiz.selectByPlanId(scmPurQuotationPlan.getId(), param));
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmPurQuotationPlan scmPurQuotationPlan = (ScmPurQuotationPlan) bean.getList().get(0);
		if(scmPurQuotationPlan != null && scmPurQuotationPlan.getId() > 0){
			//新增询价任务明细
			List<ScmPurQuotationPlanEntry2> scmPurQuotationPlanEntryList = bean.getList2();
			if(scmPurQuotationPlanEntryList != null && !scmPurQuotationPlanEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurQuotationPlanEntry2 scmPurQuotationPlanEntry : scmPurQuotationPlanEntryList){
					scmPurQuotationPlanEntry.setLineId(lineId);
					scmPurQuotationPlanEntry.setPlanId(scmPurQuotationPlan.getId());
					scmPurQuotationPlanEntryBiz.add(scmPurQuotationPlanEntry, param);
					lineId = lineId+1;
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurQuotationPlan scmPurQuotationPlan = (ScmPurQuotationPlan) bean.getList().get(0);
		if(scmPurQuotationPlan != null && scmPurQuotationPlan.getId() > 0){
			//更新询价任务明细
			List<ScmPurQuotationPlanEntry2> scmPurQuotationPlanEntryList = bean.getList2();
			if(scmPurQuotationPlanEntryList != null && !scmPurQuotationPlanEntryList.isEmpty()){
				int lineId = 1;
				for(ScmPurQuotationPlanEntry2 scmPurQuotationPlanEntry : scmPurQuotationPlanEntryList){
					if (StringUtils.equals("I", scmPurQuotationPlan.getStatus())) {
						scmPurQuotationPlanEntry.setLineId(lineId);
					}
					scmPurQuotationPlanEntry.setPlanId(scmPurQuotationPlan.getId());
					lineId = lineId+1;
				}
				scmPurQuotationPlanEntryBiz.update(scmPurQuotationPlan, scmPurQuotationPlanEntryList, ScmPurQuotationPlanEntry2.FN_PLANID, param);
			}
		}
	}
	
	@Override
	protected void afterDelete(ScmPurQuotationPlan entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除询价任务明细
			List<ScmPurQuotationPlanEntry2> scmPurQuotationPlanEntryList = scmPurQuotationPlanEntryBiz.selectByPlanId(entity.getId(), param);
			scmPurQuotationPlanEntryBiz.delete(scmPurQuotationPlanEntryList, param);
		}
	}

	private void setConvertMap(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException {
		if(scmPurQuotationPlan!=null){
			if (StringUtils.isNotBlank(scmPurQuotationPlan.getCreator())){
				//制单人
				Usr usr = usrBiz.selectByCode(scmPurQuotationPlan.getCreator(), param);
				if (usr != null) {
					scmPurQuotationPlan.setConvertMap(ScmPurQuotationPlan.FN_CREATOR, usr);
					if(scmPurQuotationPlan.getDataDisplayMap()==null){
						scmPurQuotationPlan.setDataDisplayMap(new HashMap<String,SimpleDataDisplay>());
					}
					scmPurQuotationPlan.getDataDisplayMap().put(ScmPurQuotationPlan.FN_CREATOR, SimpleDataDisplayUtil.convert(usr));
				}
			}
			if (StringUtils.isNotBlank(scmPurQuotationPlan.getEditor())){
				//修改人
				Usr usr = usrBiz.selectByCode(scmPurQuotationPlan.getEditor(), param);
				if (usr != null) {
					scmPurQuotationPlan.setConvertMap(ScmPurQuotationPlan.FN_EDITOR, usr);
				}
			}
			if (StringUtils.isNotBlank(scmPurQuotationPlan.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(scmPurQuotationPlan.getChecker(), param);
				if (usr != null) {
					scmPurQuotationPlan.setConvertMap(ScmPurQuotationPlan.FN_CHECKER, usr);
				}
			}
		}
	}

	@Override
	public ScmPurQuotationPlan submit(ScmPurQuotationPlan scmPurQuotationPlan,Param param) throws AppException {
		if(!StringUtils.equals("I",scmPurQuotationPlan.getStatus()))
			throw new AppException("iscm.purchasemanage.error.commit");
		scmPurQuotationPlan.setSubmitter(param.getUsrCode());
		scmPurQuotationPlan.setSubmitDate(CalendarUtil.getDate(param));
		scmPurQuotationPlan.setStatus("A");
		scmPurQuotationPlan.setChecker(param.getUsrCode());
		scmPurQuotationPlan.setCheckDate(CalendarUtil.getDate(param));
		scmPurQuotationPlan = this.updateDirect(scmPurQuotationPlan, param);
		if(StringUtils.contains("A,B", scmPurQuotationPlan.getStatus())) {
			//通过或部分通过时检查是否自动下达
			BillType2 billType = billTypeBiz.selectByOrgAndCode(scmPurQuotationPlan.getFinOrgUnitNo(), "PurQuotationPlan", param);
			if(billType!=null && billType.isAutoRelease()) {
				this.release(scmPurQuotationPlan, param);
			}
		}
		return scmPurQuotationPlan;
	}

	@Override
	public ScmPurQuotationPlan undoSubmit(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException {
		if(!StringUtils.equals("A",scmPurQuotationPlan.getStatus()))
			throw new AppException("iscm.purchasemanage.error.cancelCommit");
		scmPurQuotationPlan.setSubmitter(null);
		scmPurQuotationPlan.setSubmitDate(null);
		scmPurQuotationPlan.setStatus("I");
		scmPurQuotationPlan.setChecker(null);
		scmPurQuotationPlan.setCheckDate(null);
		return this.updateDirect(scmPurQuotationPlan, param);
	}

	@Override
	public ScmPurQuotationPlan release(ScmPurQuotationPlan scmPurQuotationPlan,Param param) throws AppException {
		if(!StringUtils.equals("A",scmPurQuotationPlan.getStatus()))
			throw new AppException("iscm.purchasemanage.error.release");
		scmPurQuotationPlan.setStatus("E");
		return this.updateDirect(scmPurQuotationPlan, param);
	}

	@Override
	public ScmPurQuotationPlan undoRelease(ScmPurQuotationPlan scmPurQuotationPlan, Param param) throws AppException {
		if(!StringUtils.equals("E",scmPurQuotationPlan.getStatus()))
			throw new AppException("iscm.purchasemanage.error.cancelRelease");
		scmPurQuotationPlan.setStatus("A");
		return this.updateDirect(scmPurQuotationPlan, param);
	}

	@Override
	public void distribute(List<ScmPurQuotationPlan> scmPurQuotationPlanList,List<ScmPurInquiryGroup> scmPurInquiryGroupList, Param param) {
		if(scmPurQuotationPlanList!=null && !scmPurQuotationPlanList.isEmpty() && scmPurInquiryGroupList!=null && !scmPurInquiryGroupList.isEmpty()){
			for(ScmPurQuotationPlan scmPurQuotationPlan:scmPurQuotationPlanList){
				if(!StringUtils.equals(scmPurQuotationPlan.getStatus(), "E")){
					throw new AppException("iscm.ScmPurQuotationPlan.distribute.serror.statusisnotrelease");
				}
				for(ScmPurInquiryGroup scmPurInquiryGroup:scmPurInquiryGroupList){
					generatePiOrder(scmPurQuotationPlan,scmPurInquiryGroup,param);
				}
			}
		}
	}

	public void generatePiOrder(ScmPurQuotationPlan scmPurQuotationPlan,ScmPurInquiryGroup scmPurInquiryGroup, Param param)	throws AppException {
		List<ScmPurQuotationPlanEntry2> scmPurQuotationPlanEntryList = scmPurQuotationPlanEntryBiz.selectByPlanId(scmPurQuotationPlan.getId(), param);
		if(scmPurQuotationPlanEntryList==null || scmPurQuotationPlanEntryList.isEmpty())
			return;
		scmPurQuotationPlan.setStatus("C");		//关闭
		this.updateDirect(scmPurQuotationPlan, param);
		//生成市调单
		CommonBean bean = new CommonBean();
		List<ScmPurMarketPrice2> scmPurMarketPriceList = new ArrayList<>();
		List<ScmPurMarketPriceEntry2> scmPurMarketPriceEntryList = new ArrayList<>();
		ScmPurMarketPrice2 scmPurMarketPrice = new ScmPurMarketPrice2(true);
		scmPurMarketPrice.setStatus("I");
		scmPurMarketPrice.setOrgUnitNo(scmPurQuotationPlan.getOrgUnitNo());	//采购组织
		scmPurMarketPrice.setPiDate(FormatUtils.parseDate(FormatUtils.fmtDate(CalendarUtil.getDate(param))));
		scmPurMarketPrice.setEnquiryGroupId(scmPurInquiryGroup.getId());
		scmPurMarketPrice.setEnquiryCode(scmPurInquiryGroup.getChargeBy());
		scmPurMarketPrice.setExchangeRate(BigDecimal.ONE);
		scmPurMarketPrice.setPlanId(scmPurQuotationPlan.getId());
		scmPurMarketPrice.setCreator(param.getUsrCode());
		scmPurMarketPrice.setCreateDate(CalendarUtil.getDate(param));
		scmPurMarketPrice.setControlUnitNo(param.getControlUnitNo());
		scmPurMarketPriceList.add(scmPurMarketPrice);
        bean.setList(scmPurMarketPriceList);

		for (ScmPurQuotationPlanEntry2 scmPurQuotationPlanEntry : scmPurQuotationPlanEntryList) {
			ScmPurMarketPriceEntry2 scmPurMarketPriceEntry = new ScmPurMarketPriceEntry2(true);
			BeanUtil.copyProperties(scmPurMarketPriceEntry, scmPurQuotationPlanEntry);
			ScmPurOrderEntry2 scmPurOrderEntry = scmPurOrderBiz.getPreUseQty(scmPurMarketPrice.getOrgUnitNo(), scmPurQuotationPlanEntry.getItemId(), param);
			if(scmPurOrderEntry!=null){
				BigDecimal purConvRate = ScmMaterialUtil.getConvRate(scmPurQuotationPlanEntry.getItemId(), scmPurQuotationPlanEntry.getPurUnit(), param);//采购单位转换系数
				scmPurMarketPriceEntry.setPreUseQty(scmPurOrderEntry.getReceiveQty().divide(purConvRate,4,BigDecimal.ROUND_HALF_UP));
			}
			scmPurMarketPriceEntryList.add(scmPurMarketPriceEntry);
		}
		bean.setList2(scmPurMarketPriceEntryList);
		scmPurMarketPriceBiz.add(bean, param);
	}
	
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmPurQuotationPlan entity = (ScmPurQuotationPlan) bean.getList().get(0);
		if(entity!=null) {
			ScmPurQuotationPlan scmPurQuotationPlan = this.select(entity.getPK(), param);
			if(!StringUtils.equals(scmPurQuotationPlan.getStatus(),"I")){
				throw new AppException(Message.getMessage(param.getLang(), "iscm.commom.edit.error.notnewstatus"));
			}
			//获取财务组织
			List<OrgCompany2> orgCompanyList = orgUnitRelationBiz.findToOrgUnitByType(OrgUnitRelationType.PURTOFIN, entity.getOrgUnitNo(), false, null, param);
			if(orgCompanyList==null || orgCompanyList.isEmpty()){
				throw new AppException("iscm.purchasemanage.purchasebusiness.ScmPurRequireBizImpl.error.notfin");
			}
			entity.setFinOrgUnitNo(orgCompanyList.get(0).getOrgUnitNo());
		}
	
	}

	@Override
	public CommonBean getDataForLeadInto(ScmPurQuotationPlanAdvQuery scmPurQuotationPlanAdvQuery, Param param) {
		CommonBean bean = new CommonBean();
		if(scmPurQuotationPlanAdvQuery != null){
			List<ScmPurQuotationPlanEntry2> list = scmPurQuotationPlanEntryBiz.selectQurChasIngQuery(scmPurQuotationPlanAdvQuery, param);
			if(list != null && !list.isEmpty()){
				bean.setList(list);
			}
		}
		return bean;
	}

	@Override
	public ScmPurQuotationPlan updatePrtCount(ScmPurQuotationPlan scmPurQuotationPlan, Param param)
			throws AppException {
		if(scmPurQuotationPlan.getId()>0){
			ScmPurQuotationPlan bill = this.selectDirect(scmPurQuotationPlan.getId(), param);
			if(bill != null){
				int prtCount = bill.getPrtcount();
				bill.setPrtcount((prtCount+1));
				this.updateDirect(bill, param);
				return bill;
			}
		}
		return scmPurQuotationPlan;
	}

}
