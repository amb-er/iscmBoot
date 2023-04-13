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
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetailHistory;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.service.ScmCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardDetailHistoryBiz;
import com.armitage.server.ifbm.model.FbmDishPrc2;
import com.armitage.server.ifbm.model.FbmDishSpec;
import com.armitage.server.ifbm.service.FbmDishPrcBiz;
import com.armitage.server.ifbm.service.FbmDishSpecBiz;
import com.armitage.server.system.model.OrgResource2;
import com.armitage.server.system.service.OrgResourceBiz;
import org.springframework.stereotype.Service;

@Service("scmCostCardBiz")
public class ScmCostCardBizImpl extends BaseBizImpl<ScmCostCard2> implements ScmCostCardBiz {
	
	private FbmDishPrcBiz fbmDishPrcBiz;
	private ScmCostCardDetailBiz scmCostCardDetailBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	private ScmCostCardDetailHistoryBiz scmCostCardDetailHistoryBiz;
	private OrgResourceBiz orgResourceBiz;
	private FbmDishSpecBiz fbmDishSpecBiz;
	
	public void setFbmDishPrcBiz(FbmDishPrcBiz fbmDishPrcBiz) {
		this.fbmDishPrcBiz = fbmDishPrcBiz;
	}

	public void setScmCostCardDetailBiz(ScmCostCardDetailBiz scmCostCardDetailBiz) {
		this.scmCostCardDetailBiz = scmCostCardDetailBiz;
	}

	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	public void setScmCostCardDetailHistoryBiz(ScmCostCardDetailHistoryBiz scmCostCardDetailHistoryBiz) {
		this.scmCostCardDetailHistoryBiz = scmCostCardDetailHistoryBiz;
	}

	public void setOrgResourceBiz(OrgResourceBiz orgResourceBiz) {
		this.orgResourceBiz = orgResourceBiz;
	}
    
	public void setFbmDishSpecBiz(FbmDishSpecBiz fbmDishSpecBiz) {
		this.fbmDishSpecBiz = fbmDishSpecBiz;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		map.put("controlUnitNo", param.getControlUnitNo());
		map.put("orgUnitNo", param.getOrgUnitNo());
		return map;
	}

	@Override
	protected void beforeAdd(ScmCostCard2 entity, Param param) throws AppException {
		ScmCostCard2 scmCostCard = this.findRepeat(param.getOrgUnitNo(), entity);
		if(scmCostCard!=null) {
			throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmCostCard.error.settingrepet"));
		}
	}

	@Override
	protected void beforeUpdate(ScmCostCard2 oldEntity, ScmCostCard2 newEntity, Param param) throws AppException {
		if(newEntity!=null) {
			ScmCostCard2 scmCostCard = this.findRepeat(param.getOrgUnitNo(), newEntity);
			if(scmCostCard!=null && scmCostCard.getId()!=newEntity.getId()) {
				throw new AppException(Message.getMessage(param.getLang(),"iscm.ScmCostCard.error.settingrepet"));
			}
			newEntity.setApprovaled(false);
		}
	}

	@Override
	protected void beforeAdd(CommonBean bean, Param param) throws AppException {
		ScmCostCard2 scmCostCard = (ScmCostCard2) bean.getList().get(0);
		if(scmCostCard != null){
			BigDecimal amt= BigDecimal.ZERO;
			List<ScmCostCardDetail2> scmCostCardDetailList = bean.getList2();
			if(scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
				for(ScmCostCardDetail2 scmCostCardDetail : scmCostCardDetailList){
					if(scmCostCardDetail.getConvrate().compareTo(BigDecimal.ZERO)>0)
						amt = amt.add(scmCostCardDetail.getQty().multiply(scmCostCardDetail.getPrice()).divide(scmCostCardDetail.getConvrate(), 4, RoundingMode.HALF_UP));
				}
			}
			scmCostCard.setCostPrice(amt);
		}
	}

	@Override
	protected void beforeUpdate(CommonBean bean, Param param) throws AppException {
		ScmCostCard2 scmCostCard = (ScmCostCard2) bean.getList().get(0);
		if(scmCostCard != null){
			BigDecimal amt= BigDecimal.ZERO;
			List<ScmCostCardDetail2> scmCostCardDetailList = bean.getList2();
			if(scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
				for(ScmCostCardDetail2 scmCostCardDetail : scmCostCardDetailList){
					if(scmCostCardDetail.getConvrate().compareTo(BigDecimal.ZERO)>0)
						amt = amt.add(scmCostCardDetail.getQty().multiply(scmCostCardDetail.getPrice()).divide(scmCostCardDetail.getConvrate(), 4, RoundingMode.HALF_UP));
				}
			}
			scmCostCard.setCostPrice(amt);
		}
	}

	@Override
	protected void afterFindPage(List list, Page page, Param param)
			throws AppException {
		if(list != null && !list.isEmpty()){
			for(ScmCostCard2 scmCostCard : (List<ScmCostCard2>)list){
				setConvertMap(scmCostCard,param);
			}
		}
	}

	@Override
	protected void afterSelect(ScmCostCard2 entity, Param param) throws AppException {
		if (entity != null){
			setConvertMap(entity,param);
		}
	}
	
	private void setConvertMap(ScmCostCard2 scmCostCard, Param param) throws AppException {
		if(StringUtils.isNotBlank(scmCostCard.getOrgUnitNo())){
			if(scmCostCard.getDishSpecId()>0) {
				Page page = new Page();
				page.setModelClass(ScmResOrgUnitMap.class);
				page.setShowCount(Integer.MAX_VALUE);
				List<ScmResOrgUnitMap> scmResOrgUnitMapList = scmResOrgUnitMapBiz.findPage(page, param);
				String orgUnitNo=param.getControlUnitNo();
				if(scmResOrgUnitMapList!=null && !scmResOrgUnitMapList.isEmpty() && !scmResOrgUnitMapList.get(0).isShared()) {
					orgUnitNo = scmResOrgUnitMapList.get(0).getFbmControlUnitNo();
					FbmDishPrc2 fbmDishPrc = fbmDishPrcBiz.selectByDishAndSpecId(orgUnitNo, scmCostCard.getDishId(), scmCostCard.getDishSpecId(), param);
					if(fbmDishPrc!=null) {
						scmCostCard.setConvertMap(ScmCostCard2.FN_DISHSPECID, fbmDishPrc);
					}
				}
			}
			scmCostCard.setProfit(scmCostCard.getSalePrice().subtract(scmCostCard.getCostPrice()));
			if(scmCostCard.getSalePrice().compareTo(BigDecimal.ZERO) == 0){
				scmCostCard.setProfitRate(BigDecimal.ZERO);
			}else{
				scmCostCard.setProfitRate((scmCostCard.getProfit().multiply(new BigDecimal("100"))).divide(scmCostCard.getSalePrice(), 2, RoundingMode.HALF_UP));
			}
		}
		if(StringUtils.isNotBlank(scmCostCard.getResOrgUnitNo())) {
			OrgResource2 orgResource = orgResourceBiz.selectByOrgUnitNo(scmCostCard.getResOrgUnitNo(), param);
			if(orgResource!=null)
				scmCostCard.setConvertMap(ScmCostCard2.FN_RESORGUNITNO, orgResource);
		}
		if(scmCostCard.getDishSpecId()>0) {
			FbmDishSpec fbmDishSpec =  fbmDishSpecBiz.select(scmCostCard.getDishSpecId(), param);
			scmCostCard.setConvertMap("DishSpecName", fbmDishSpec);
			
		}
	}
	
	@Override
	protected void afterSelect(CommonBean bean, Param param) throws AppException {
		ScmCostCard2 scmCostCard = (ScmCostCard2) bean.getList().get(0);
		if(scmCostCard != null && scmCostCard.getId() > 0){
			bean.setList2(scmCostCardDetailBiz.selectByCardId(scmCostCard.getId(), param));
		}
	}
	
	@Override
	protected void afterAdd(CommonBean bean, Param param) throws AppException {
		ScmCostCard2 scmCostCard = (ScmCostCard2) bean.getList().get(0);
		if(scmCostCard != null && scmCostCard.getId() > 0){
			//新增菜品成本卡明细
			List<ScmCostCardDetail2> scmCostCardDetailList = bean.getList2();
			if(scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
				for(ScmCostCardDetail2 scmCostCardDetail : scmCostCardDetailList){
					scmCostCardDetail.setCardId(scmCostCard.getId());
					scmCostCardDetail.setCreator(param.getUsrCode());
					scmCostCardDetailBiz.add(scmCostCardDetail, param);
				}
			}
		}
	}
	

	@Override
	protected void afterUpdate(CommonBean bean, Param param) throws AppException {
		ScmCostCard2 scmCostCard = (ScmCostCard2) bean.getList().get(0);
		if(scmCostCard != null && scmCostCard.getId() > 0){
			//更新菜品成本卡明细
			List<ScmCostCardDetail2> scmCostCardDetailList = bean.getList2();
			if(scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
				for(ScmCostCardDetail2 scmCostCardDetail : scmCostCardDetailList){
					scmCostCardDetail.setCardId(scmCostCard.getId());
					scmCostCardDetail.setCreator(param.getUsrCode());
					scmCostCardDetail.setChecker("");
				}
				scmCostCardDetailBiz.update(scmCostCard, scmCostCardDetailList, ScmCostCardDetail2.FN_CARDID, param);
			}
		}
	}
	
	@Override
	protected void beforeDelete(ScmCostCard2 entity, Param param) throws AppException {
		if(entity != null && entity.getId() > 0){
			//删除菜品成本卡明细
			scmCostCardDetailBiz.deleteByCardId(entity.getId(), param);
			scmCostCardDetailHistoryBiz.deleteByEffectDay(entity.getId(),new Date(), param);	//删除当天开始的数据
			scmCostCardDetailHistoryBiz.updateLastByCostCard2(entity.getId(),param);	//更新最后一次的历史数据的日期
		}
		List<ScmCostCardDetailHistory> scmCostCardDetailHistoryList = scmCostCardDetailHistoryBiz.selectByEffectDay(entity.getId(),new Date(), param);
	}

	
	@Override
	public ScmCostCard2 lock(ScmCostCard2 scmCostCard, Param param) throws AppException {
		if(scmCostCard != null){
			if(!scmCostCard.isLocked()){
				scmCostCard.setLocked(true);
				this.updateDirect(scmCostCard, param);
				return scmCostCard;
			}
		}
		return null;
	}

	@Override
	public ScmCostCard2 unlock(ScmCostCard2 scmCostCard, Param param) throws AppException {
		if(scmCostCard != null){
			if(scmCostCard.isLocked()){
				scmCostCard.setLocked(false);
				this.updateDirect(scmCostCard, param);
				return scmCostCard;
			}
		}
		return null;
	}
	
	@Override
	public ScmCostCard2 auditCostCard(ScmCostCard2 scmCostCard, Param param) throws AppException {
		if(scmCostCard != null){
			Date effectiveDate =scmCostCard.getNewEffectiveDate();
			scmCostCard = this.selectDirect(scmCostCard.getId(), param);
			if (scmCostCard.isLocked())
				throw new AppException(Message.getMessage(param.getLang(),"iscm.CostCard.approve.error.alreadyLocked",new String[] {scmCostCard.getDishName()}));
			if(scmCostCard.isApprovaled())
				throw new AppException(Message.getMessage(param.getLang(),"iscm.CostCard.approve.error.alreadyApproved",new String[] {scmCostCard.getDishName()}));
			if(effectiveDate!=null) {
				scmCostCard.setEffectiveDate(effectiveDate);
			}
			scmCostCard.setApprovaled(true);
			this.updateDirect(scmCostCard, param);
			List<ScmCostCardDetail2> scmCostCardDetailList = scmCostCardDetailBiz.selectByCardId(scmCostCard.getId(), param);
			if(scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
				for(int i = scmCostCardDetailList.size() - 1; i >= 0; i--) {
					ScmCostCardDetail2 scmCostCardDetail = scmCostCardDetailList.get(i);
			        if(StringUtils.isNotBlank(scmCostCardDetail.getChecker())){
			        	scmCostCardDetailList.remove(i);
			        }else{
			        	scmCostCardDetail.setChecker(param.getUsrCode());
			        }
			    }
				if(scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
					scmCostCardDetailBiz.update(scmCostCardDetailList, param);
				}
			}
			scmCostCard.setEffectDay(scmCostCard.getEffectiveDate()==null?CalendarUtil.getDate(param):scmCostCard.getEffectiveDate());
			scmCostCardDetailHistoryBiz.updateByAuditCostCard(scmCostCard.getId(),scmCostCard.getEffectDay(),param);
		}
		return null;
	}
	
	@Override
	public List<ScmCostCard2> getDataForReplace(ScmDishReplaceCostCard scmDishReplaceCostCard, Param param)
			throws AppException {
		if(scmDishReplaceCostCard != null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", scmDishReplaceCostCard.getItemId());
			if(StringUtils.equals(scmDishReplaceCostCard.getTypeScope(),"C")){
				map.put("fbmItemCode", scmDishReplaceCostCard.getFbmItemCode());
			}
			return ((ScmCostCardDAO)dao).selectReplaceScmCostCard(map);
		}
		return null;
	}

	@Override
	public void replaceItem(List<ScmCostCard2> scmCostCardList, ScmDishReplaceCostCard scmDishReplaceCostCard, Param param) throws AppException {
		if(scmDishReplaceCostCard != null && scmCostCardList != null && !scmCostCardList.isEmpty()){
			StringBuffer cardIdstr = new StringBuffer("");
			for(ScmCostCard2 scmCostCard : scmCostCardList){
				if (StringUtils.isEmpty(cardIdstr.toString())){
					cardIdstr.append(scmCostCard.getId());
				}else{
					cardIdstr.append(",").append(scmCostCard.getId());
				}
			}
			scmCostCardDetailBiz.replaceItemByCardIds(cardIdstr.toString(), scmDishReplaceCostCard, param);
		}
	}

	@Override
	public void copyCostCard(ScmCostCard2 scmCostCard, List<ScmCostCardDetail2> scmCostCardDetailList,
			Param param) throws AppException {
		if(scmCostCard != null && scmCostCardDetailList != null && !scmCostCardDetailList.isEmpty()){
			CommonBean bean = new CommonBean();
			List<ScmCostCard2> scmCostCardList = new ArrayList();
            scmCostCardList.add(scmCostCard);
            bean.setList(scmCostCardList);
            bean.setList2(scmCostCardDetailList);
			if(scmCostCard.getId() <= 0){
				//新增
                this.add(bean, param);
			}else{
				//更新前先删除原有明细
				scmCostCardDetailBiz.deleteByCardId(scmCostCard.getId(), param);
				for(ScmCostCardDetail2 scmCostCardDetail : scmCostCardDetailList){
					scmCostCardDetail.setCardId(scmCostCard.getId());
				}
				scmCostCardDetailBiz.add(scmCostCardDetailList, param);
			}
		}
	}
	
	private ScmCostCard2 findRepeat(String orgUnitNo, ScmCostCard2 scmCostCard) throws AppException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("orgUnitNo", orgUnitNo);
		map.put("dishId", scmCostCard.getDishId());
		List<ScmCostCard2> scmCostCardList = ((ScmCostCardDAO) dao).findRepeat(map);
		return (scmCostCardList == null || scmCostCardList.isEmpty()) ? null
				: scmCostCardList.get(0);
	}
}
