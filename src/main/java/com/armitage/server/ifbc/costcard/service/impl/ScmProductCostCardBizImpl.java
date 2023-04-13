
package com.armitage.server.ifbc.costcard.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.base.model.QueryParam;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.common.util.ClassUtils;
import com.armitage.server.common.util.FormatUtils;
import com.armitage.server.ifbc.costcard.dao.ScmProductCostCardDAO;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.service.ScmProductCostCardDetailHistoryBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMaterialBiz;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvSupplyRule;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurRequire2;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.service.OrgResourceBiz;
import org.springframework.stereotype.Service;

@Service("scmProductCostCardBiz")
public class ScmProductCostCardBizImpl extends BaseBizImpl<ScmProductCostCard2> implements ScmProductCostCardBiz {

	private ScmProductCostCardDetailBiz scmProductCostCardDetailBiz;
	private ScmMaterialBiz scmMaterialBiz;
	private OrgResourceBiz orgResourceBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	private ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz;
	
	public ScmMeasureUnitBiz getScmMeasureUnitBiz() {
		return scmMeasureUnitBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	public OrgResourceBiz getOrgResourceBiz() {
		return orgResourceBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	public ScmMaterialBiz getScmMaterialBiz() {
		return scmMaterialBiz;
	}

	public void setScmMaterialBiz(ScmMaterialBiz scmMaterialBiz) {
		this.scmMaterialBiz = scmMaterialBiz;
	}

	public ScmProductCostCardDetailBiz getScmProductCostCardDetailBiz() {
		return scmProductCostCardDetailBiz;
	}

	public void setScmProductCostCardDetailBiz(ScmProductCostCardDetailBiz scmProductCostCardDetailBiz) {
		this.scmProductCostCardDetailBiz = scmProductCostCardDetailBiz;
	}
	
	public void setScmProductCostCardDetailHistoryBiz(
			ScmProductCostCardDetailHistoryBiz scmProductCostCardDetailHistoryBiz) {
		this.scmProductCostCardDetailHistoryBiz = scmProductCostCardDetailHistoryBiz;
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			List<ScmProductCostCard2> scmProductCostCards = list;
			if(scmProductCostCards != null && !scmProductCostCards.isEmpty()){
				for(ScmProductCostCard2 scmProductCostCard : scmProductCostCards){
					setConvertMap(scmProductCostCard,param);
				}
			}
		}
	}

	@Override
	protected void afterSelect(ScmProductCostCard2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	@Override
	protected void beforeFindPage(Page page, Param param) throws AppException {
		List<OrgResource2> findChild = orgResourceBiz.findChild(param.getOrgUnitNo(), param);
		if (findChild != null && !findChild.isEmpty()) {
			StringBuffer orgunitList = new StringBuffer("");
			for (OrgResource2 orgResource : findChild) {
				if(StringUtils.isNotBlank(orgunitList.toString()))
					orgunitList.append(",");
				orgunitList.append("'").append(orgResource.getOrgUnitNo()).append("'");
			}
			page.getParam().put((ClassUtils.getFinalModelSimpleName(ScmProductCostCard.class) + "." + ScmProductCostCard.FN_ORGUNITNO), 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmProductCostCard.class) + "." + ScmProductCostCard.FN_ORGUNITNO), QueryParam.QUERY_IN, orgunitList.toString()));
		}else{
			page.getParam().put(ScmProductCostCard.FN_ORGUNITNO, 
					new QueryParam((ClassUtils.getFinalModelSimpleName(ScmProductCostCard.class)+ "." + ScmProductCostCard.FN_ORGUNITNO),QueryParam.QUERY_EQ,param.getOrgUnitNo()));
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			ScmProductCostCard scmProductCostCard = (ScmProductCostCard) bean.getList().get(0);
			HashMap<String,Object> map = new HashMap();
			map.put("orgUnitNo", scmProductCostCard.getOrgUnitNo());
			map.put("productId", scmProductCostCard.getProductId());
			ScmProductCostCard select = ((ScmProductCostCardDAO)dao).selectUnique(map);
			if(select != null ) {
				throw new AppException("iscm.server.ifbc.costcard.service.impl.ScmProductCostCardBizImpl.alreadyExists");
			}
			List<ScmProductCostCardDetail2> scmProductCostCardDetail2s = bean.getList2();
			BigDecimal costPrice = BigDecimal.ZERO;
			if (scmProductCostCardDetail2s != null && !scmProductCostCardDetail2s.isEmpty()) {
				for (ScmProductCostCardDetail2 scmProductCostCardDetail2 : scmProductCostCardDetail2s) {
					BigDecimal multiply = BigDecimal.ZERO;
					if (!(BigDecimal.ZERO.compareTo(scmProductCostCardDetail2.getConvrate())==0)) {
						multiply = scmProductCostCardDetail2.getPrice().multiply(scmProductCostCardDetail2.getQty().divide(scmProductCostCardDetail2.getConvrate(), 4, RoundingMode.HALF_UP));
					}
					costPrice = costPrice.add(multiply);
				}
			}
			scmProductCostCard.setCostPrice(costPrice);
		}
	}
	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			ScmProductCostCard scmProductCostCard = (ScmProductCostCard) bean.getList().get(0);
			scmProductCostCard.setApprovaled(false);
			HashMap<String,Object> map = new HashMap();
			map.put("orgUnitNo", scmProductCostCard.getOrgUnitNo());
			map.put("productId", scmProductCostCard.getProductId());
			ScmProductCostCard select = ((ScmProductCostCardDAO)dao).selectUnique(map);
			if(select != null && scmProductCostCard.getId() != select.getId()) {
				throw new AppException("iscm.server.ifbc.costcard.service.impl.ScmProductCostCardBizImpl.alreadyExists");
			}
			List<ScmProductCostCardDetail2> scmProductCostCardDetail2s = bean.getList2();
			BigDecimal costPrice = BigDecimal.ZERO;
			if (scmProductCostCardDetail2s != null && !scmProductCostCardDetail2s.isEmpty()) {
				for (ScmProductCostCardDetail2 scmProductCostCardDetail2 : scmProductCostCardDetail2s) {
					BigDecimal multiply = BigDecimal.ZERO;
					if (!(BigDecimal.ZERO.compareTo(scmProductCostCardDetail2.getConvrate())==0)) {
						multiply = scmProductCostCardDetail2.getPrice().multiply(scmProductCostCardDetail2.getQty().divide(scmProductCostCardDetail2.getConvrate(), 4, RoundingMode.HALF_UP));
					}
					costPrice = costPrice.add(multiply);
				}
			}
			scmProductCostCard.setCostPrice(costPrice);
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			ScmProductCostCard2 scmProductCostCard = (ScmProductCostCard2) bean.getList().get(0);
			setConvertMap(scmProductCostCard,param);
			List<ScmProductCostCardDetail2> scmProductCostCardDetails = scmProductCostCardDetailBiz.selectByCardId(scmProductCostCard.getId(),param);
			if(scmProductCostCardDetails != null && !scmProductCostCardDetails.isEmpty()) {
				bean.setList2(scmProductCostCardDetails);
			}
		}
	}
	
	

	@Override
	protected void afterDelete(ScmProductCostCard2 entity, Param param) throws AppException {
		scmProductCostCardDetailBiz.deleteByCardId(entity.getId());// 删除明细
		// 删除当天开始的数据
		scmProductCostCardDetailHistoryBiz.deleteByEffectDay(entity.getId(), CalendarUtil.getDate(param), param);
		// 更新仍然生效的数据的截止日期为当天
		scmProductCostCardDetailHistoryBiz.updateByAuditCostCard(entity, CalendarUtil.getDate(param), param);
	}

	private void setConvertMap(ScmProductCostCard2 scmProductCostCard,Param param) {
		if(scmProductCostCard.getProductId() > 0) {
			ScmMaterial2 scmMaterial = scmMaterialBiz.select(scmProductCostCard.getProductId(), param);
			if(scmMaterial!=null) {
				scmProductCostCard.setConvertMap(ScmProductCostCard.FN_PRODUCTID, scmMaterial); 
				scmProductCostCard.setItemName(scmMaterial.getItemName());
			}
		}
		if(StringUtils.isNotBlank(scmProductCostCard.getOrgUnitNo())) {
			//资源组织
			OrgResource2  orgResource = orgResourceBiz.selectByOrgUnitNo(scmProductCostCard.getOrgUnitNo(), param);
			if (orgResource != null) {
				scmProductCostCard.setConvertMap(ScmProductCostCard.FN_ORGUNITNO, orgResource);
			}
		}
		if(scmProductCostCard.getProductUnit() > 0) {
			//单位
			ScmMeasureUnit select = scmMeasureUnitBiz.select(scmProductCostCard.getProductUnit(), param);
			if (select != null) {
				scmProductCostCard.setConvertMap(ScmProductCostCard.FN_PRODUCTUNIT, select);
			}
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			ScmProductCostCard scmProductCostCard = (ScmProductCostCard) bean.getList().get(0);
			if(bean.getList2() != null && !bean.getList2().isEmpty()) {
				List<ScmProductCostCardDetail2> scmProductCostCardDetails = bean.getList2();
				for(ScmProductCostCardDetail2 scmProductCostCardDetail : scmProductCostCardDetails ) {
					scmProductCostCardDetail.setCardId(scmProductCostCard.getId());
					scmProductCostCardDetail.setCreator(param.getUsrCode());
					scmProductCostCardDetailBiz.add(scmProductCostCardDetail, param);
				}
			}
		}
	}
	
	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		if(bean.getList() != null && !bean.getList().isEmpty()) {
			ScmProductCostCard scmProductCostCard = (ScmProductCostCard) bean.getList().get(0);
			if(bean.getList2() != null && !bean.getList2().isEmpty()) {
				List<ScmProductCostCardDetail2> scmProductCostCardDetails = bean.getList2();
				for(ScmProductCostCardDetail2 scmProductCostCardDetail : scmProductCostCardDetails ) {
					scmProductCostCardDetail.setCardId(scmProductCostCard.getId());
					scmProductCostCardDetail.setCreator(param.getUsrCode());
					scmProductCostCardDetail.setChecker("");
				}
				scmProductCostCardDetailBiz.update(scmProductCostCard, scmProductCostCardDetails, ScmProductCostCardDetail2.FN_CARDID, param);
			}
		}
	}

	@Override
	public ScmProductCostCard2 auditCostCard(ScmProductCostCard2 scmProductCostCard2, Param param) throws AppException {
		if (scmProductCostCard2 != null) {
			Date effectiveDate =scmProductCostCard2.getNewEffectiveDate();
			scmProductCostCard2 = this.select(scmProductCostCard2.getId(), param);
			if (scmProductCostCard2.getApprovaled()) {
				throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmProductCostCard.auditCostCard.error.alreadyApproved",new String[] {scmProductCostCard2.getItemName()}));
			}
			if(effectiveDate!=null) {
				scmProductCostCard2.setEffectiveDate(effectiveDate);
			}
			scmProductCostCard2.setApprovaled(true);
			this.updateDirect(scmProductCostCard2, param);
			List<ScmProductCostCardDetail2> scmProductCostCardDetail2s = scmProductCostCardDetailBiz.selectByCardId(scmProductCostCard2.getId(), param);
			if (scmProductCostCardDetail2s != null && !scmProductCostCardDetail2s.isEmpty()) {
				for(int i = scmProductCostCardDetail2s.size() - 1; i >= 0; i--) {
					ScmProductCostCardDetail2 scmProductCostCardDetail2 = scmProductCostCardDetail2s.get(i);
					if (StringUtils.isNotBlank(scmProductCostCardDetail2.getChecker())) {
						scmProductCostCardDetail2s.remove(i);
					}else {
						scmProductCostCardDetail2.setChecker(param.getUsrCode());
					}
				}
			}
			if (scmProductCostCardDetail2s != null && !scmProductCostCardDetail2s.isEmpty()) {
				scmProductCostCardDetailBiz.update(scmProductCostCardDetail2s, param);
			}
			scmProductCostCardDetailHistoryBiz.updateByAuditCostCard(scmProductCostCard2,scmProductCostCard2.getEffectiveDate(),param);
		}
		return null;
	}
}
