package com.armitage.server.ifbc.costcard.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.message.Message;
import com.armitage.server.common.util.CalendarUtil;
import com.armitage.server.ifbc.costcard.dao.ScmCookCostCardDAO;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardDetailHistoryBiz;
import com.armitage.server.system.model.OrgBaseUnit;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import com.armitage.server.system.service.OrgResourceBiz;
import org.springframework.stereotype.Service;

@Service("scmCookCostCardBiz")
public class ScmCookCostCardBizImpl extends BaseBizImpl<ScmCookCostCard2> implements ScmCookCostCardBiz {
	private OrgBaseUnitBiz orgBaseUnitBiz;
	private ScmCookCostCardDetailBiz scmCookCostCardDetailBiz;
	private OrgResourceBiz orgResourceBiz;
	private ScmCookCostCardDetailHistoryBiz scmCookCostCardDetailHistoryBiz;
	
	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	public void setScmCookCostCardDetailBiz(ScmCookCostCardDetailBiz scmCookCostCardDetailBiz) {
		this.scmCookCostCardDetailBiz = scmCookCostCardDetailBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}

	public void setScmCookCostCardDetailHistoryBiz(ScmCookCostCardDetailHistoryBiz scmCookCostCardDetailHistoryBiz) {
		this.scmCookCostCardDetailHistoryBiz = scmCookCostCardDetailHistoryBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("orgUnitNo", param.getOrgUnitNo());
		return map;
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmCookCostCard2 scmCookCostCard : (List<ScmCookCostCard2>)list){
				setConverMap(scmCookCostCard,param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmCookCostCard2 entity, Param param) throws AppException {
		if (entity != null){
			setConverMap(entity,param);
		}
	}
	
	private void setConverMap(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException {
		if(StringUtils.isNotBlank(scmCookCostCard.getOrgUnitNo())){
			//加工组织
			OrgBaseUnit orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmCookCostCard.getOrgUnitNo(), param);
			if (orgBaseUnit != null) {
				scmCookCostCard.setConvertMap(ScmCookCostCard.FN_ORGUNITNO, orgBaseUnit);
			}
		}
		if(StringUtils.isNotBlank(scmCookCostCard.getResOrgUnitNo())) {
			OrgResource2 orgResource = orgResourceBiz.selectByOrgUnitNo(scmCookCostCard.getResOrgUnitNo(), param);
			if(orgResource!=null)
				scmCookCostCard.setConvertMap(ScmCookCostCard.FN_RESORGUNITNO, orgResource);
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2) bean.getList().get(0);
		if(scmCookCostCard != null && scmCookCostCard.getId() > 0){
			bean.setList2(scmCookCostCardDetailBiz.selectByCardId(scmCookCostCard.getId(), param));
		}
	}
	
	
	@Override
	protected void beforeAdd(ScmCookCostCard2 entity, Param param) throws AppException {
		ScmCookCostCard2 scmCookCostCard = this.findRepeat(param.getOrgUnitNo(), entity);
		if(scmCookCostCard!=null) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmCookCostCard.error.settingrepet"));
		}
	}

	@Override
	protected void beforeUpdate(ScmCookCostCard2 oldEntity, ScmCookCostCard2 newEntity, Param param) throws AppException {
		if(newEntity!=null) {
			ScmCookCostCard2 scmCookCostCard = this.findRepeat(param.getOrgUnitNo(), newEntity);
			if(scmCookCostCard!=null && scmCookCostCard.getId()!=newEntity.getId()) {
				throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmCookCostCard.error.settingrepet"));
			}
			newEntity.setApprovaled(false);
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2) bean.getList().get(0);
		if(scmCookCostCard != null){
			BigDecimal amt= BigDecimal.ZERO;
			List<ScmCookCostCardDetail2> scmCookCostCardDetailList = bean.getList2();
			if(scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
				for(ScmCookCostCardDetail2 scmCookCostCardDetail : scmCookCostCardDetailList){
					if(scmCookCostCardDetail.getConvrate().compareTo(BigDecimal.ZERO)>0)
						amt = amt.add(scmCookCostCardDetail.getQty().multiply(scmCookCostCardDetail.getPrice()).divide(scmCookCostCardDetail.getConvrate(), 4, RoundingMode.HALF_UP));
				}
			}
			scmCookCostCard.setCostPrice(amt);
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2) bean.getList().get(0);
		if(scmCookCostCard != null){
			BigDecimal amt= BigDecimal.ZERO;
			List<ScmCookCostCardDetail2> scmCookCostCardDetailList = bean.getList2();
			if(scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
				for(ScmCookCostCardDetail2 scmCookCostCardDetail : scmCookCostCardDetailList){
					if(scmCookCostCardDetail.getConvrate().compareTo(BigDecimal.ZERO)>0)
						amt = amt.add(scmCookCostCardDetail.getQty().multiply(scmCookCostCardDetail.getPrice()).divide(scmCookCostCardDetail.getConvrate(), 4, RoundingMode.HALF_UP));
				}
			}
			scmCookCostCard.setCostPrice(amt);
		}
	}

	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2) bean.getList().get(0);
		if(scmCookCostCard != null && scmCookCostCard.getId() > 0){
			//新增制法成本卡明细
			List<ScmCookCostCardDetail2> scmCookCostCardDetailList = bean.getList2();
			if(scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
				for(ScmCookCostCardDetail2 scmCookCostCardDetail : scmCookCostCardDetailList){
					scmCookCostCardDetail.setCardId(scmCookCostCard.getId());
					scmCookCostCardDetail.setCreator(param.getUsrCode());
					scmCookCostCardDetailBiz.add(scmCookCostCardDetail, param);
				}
			}
		}
	}
	

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmCookCostCard2 scmCookCostCard = (ScmCookCostCard2) bean.getList().get(0);
		if(scmCookCostCard != null && scmCookCostCard.getId() > 0){
			//更新制法成本卡明细
			List<ScmCookCostCardDetail2> scmCookCostCardDetailList = bean.getList2();
			if(scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
				for(ScmCookCostCardDetail2 scmCookCostCardDetail : scmCookCostCardDetailList){
					scmCookCostCardDetail.setCardId(scmCookCostCard.getId());
					scmCookCostCardDetail.setCreator(param.getUsrCode());
					scmCookCostCardDetail.setChecker("");
				}
				scmCookCostCardDetailBiz.update(scmCookCostCard, scmCookCostCardDetailList, ScmCookCostCardDetail2.FN_CARDID, param);
			}
		}
	}
	
	@Override
	protected void beforeDelete(ScmCookCostCard2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除制法成本卡明细
			scmCookCostCardDetailBiz.deleteByCardId(entity.getId(), param);
			scmCookCostCardDetailHistoryBiz.deleteByEffectDay(entity.getId(),new Date(), param);	//删除当天开始的数据
			scmCookCostCardDetailHistoryBiz.updateLastByCostCard2(entity.getId(),param);	//更新最后一次的历史数据的日期
		}
	}

	@Override
	public ScmCookCostCard2 lock(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException {
		if(scmCookCostCard != null){
			if(!scmCookCostCard.isLocked()){
				scmCookCostCard.setLocked(true);
				this.updateDirect(scmCookCostCard, param);
				return scmCookCostCard;
			}
		}
		return null;
	}

	@Override
	public ScmCookCostCard2 unlock(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException {
		if(scmCookCostCard != null){
			if(scmCookCostCard.isLocked()){
				scmCookCostCard.setLocked(false);
				this.updateDirect(scmCookCostCard, param);
				return scmCookCostCard;
			}
		}
		return null;
	}

	@Override
	public List<ScmCookCostCard2> getDataForReplace(ScmDishReplaceCostCard scmDishReplaceCostCard, Param param)
			throws AppException {
		if(scmDishReplaceCostCard != null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", scmDishReplaceCostCard.getItemId());
			return ((ScmCookCostCardDAO)dao).selectReplaceScmCookCostCard(map);
		}
		return null;
	}

	@Override
	public void copyCostCard(ScmCookCostCard2 scmCookCostCard, List<ScmCookCostCardDetail2> scmCookCostCardDetailList,
			Param param) throws AppException {
		if(scmCookCostCard != null && scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
			CommonBean bean = new CommonBean();
			List<ScmCookCostCard2> scmCookCostCardList = new ArrayList();
            scmCookCostCardList.add(scmCookCostCard);
            bean.setList(scmCookCostCardList);
            bean.setList2(scmCookCostCardDetailList);
			if(scmCookCostCard.getId() <= 0){
				//新增
                this.add(bean, param);
			}else{
				//更新前先删除原有明细
				scmCookCostCardDetailBiz.deleteByCardId(scmCookCostCard.getId(), param);
				for(ScmCookCostCardDetail2 scmCookCostCardDetail : scmCookCostCardDetailList){
					scmCookCostCardDetail.setCardId(scmCookCostCard.getId());
				}
				scmCookCostCardDetailBiz.add(scmCookCostCardDetailList, param);
			}
		}
	}

	@Override
	public ScmCookCostCard2 auditCostCard(ScmCookCostCard2 scmCookCostCard, Param param) throws AppException {
		if(scmCookCostCard != null){
			Date effectiveDate =scmCookCostCard.getNewEffectiveDate();
			scmCookCostCard = this.selectDirect(scmCookCostCard.getId(), param);
			if(scmCookCostCard.isApprovaled())
				throw new AppException(Message.getMessage(param.getLang(),"iscm.CostCard.approve.error.alreadyApproved",new String[] {scmCookCostCard.getCookName()}));
			if(effectiveDate!=null) {
				scmCookCostCard.setEffectiveDate(effectiveDate);
			}
			scmCookCostCard.setApprovaled(true);
			this.updateDirect(scmCookCostCard, param);
			List<ScmCookCostCardDetail2> scmCookCostCardDetailList = scmCookCostCardDetailBiz.selectByCardId(scmCookCostCard.getId(), param);
			if(scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
				for(int i = scmCookCostCardDetailList.size() - 1; i >= 0; i--) {
					ScmCookCostCardDetail2 scmCookCostCardDetail = scmCookCostCardDetailList.get(i);
			        if(StringUtils.isNotBlank(scmCookCostCardDetail.getChecker())){
			        	scmCookCostCardDetailList.remove(i);
			        }else{
			        	scmCookCostCardDetail.setChecker(param.getUsrCode());
			        }
			    }
				if(scmCookCostCardDetailList != null && !scmCookCostCardDetailList.isEmpty()){
					scmCookCostCardDetailBiz.update(scmCookCostCardDetailList, param);
				}
			}
			Date effectDate = (scmCookCostCard.getEffectiveDate()==null?CalendarUtil.getDate(param):scmCookCostCard.getEffectiveDate());
			scmCookCostCardDetailHistoryBiz.updateByAuditCostCard(scmCookCostCard.getId(),effectDate,param);
		}
		return null;
	}

	private ScmCookCostCard2 findRepeat(String orgUnitNo, ScmCookCostCard scmCookCostCard) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("cookId", scmCookCostCard.getCookId());
		List<ScmCookCostCard2> scmCookCostCardList = ((ScmCookCostCardDAO) dao).findRepeat(map);
		return (scmCookCostCardList == null || scmCookCostCardList.isEmpty()) ? null
				: scmCookCostCardList.get(0);
	}

	@Override
	public ScmCookCostCard2 selectByCookId(long cookId, Param param) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("cookId", cookId);
		List<ScmCookCostCard2> scmCookCostCardList = this.findAll(map, param);
		if(scmCookCostCardList!=null && !scmCookCostCardList.isEmpty())
			return scmCookCostCardList.get(0);
		return null;
	}

}
