package com.armitage.server.ifbc.costcard.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.service.ScmCostCardDetailBiz;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import com.armitage.server.iscm.basedata.service.ScmMeasureUnitBiz;
import com.armitage.server.user.model.Usr;
import com.armitage.server.user.service.UsrBiz;
import org.springframework.stereotype.Service;

@Service("scmCostCardDetailBiz")
public class ScmCostCardDetailBizImpl extends BaseBizImpl<ScmCostCardDetail2> implements ScmCostCardDetailBiz {
	
	private UsrBiz usrBiz;
	private ScmMeasureUnitBiz scmMeasureUnitBiz;
	
	public void setUsrBiz(UsrBiz usrBiz) {
		this.usrBiz = usrBiz;
	}

	public void setScmMeasureUnitBiz(ScmMeasureUnitBiz scmMeasureUnitBiz) {
		this.scmMeasureUnitBiz = scmMeasureUnitBiz;
	}

	@Override
	public List<ScmCostCardDetail2> selectByCardId(long cardId, Param param) throws AppException {
		if(cardId > 0){
			HashMap<String, Object> map = new HashMap<>();
			map.put("cardId", cardId);
			List<ScmCostCardDetail2> scmCostCardDetailList = ((ScmCostCardDetailDAO) dao).selectByCardId(map);
			if(scmCostCardDetailList!=null && !scmCostCardDetailList.isEmpty()){
				for(ScmCostCardDetail2 scmCostCardDetail:scmCostCardDetailList){
					setConverMap(scmCostCardDetail,param);
				}
			}
			return scmCostCardDetailList;
		}
		return null;
	}

	@Override
	public void deleteByCardId(long cardId, Param param) throws AppException {
		if(cardId > 0){
			List<ScmCostCardDetail2> scmCostCardDetailList = this.selectByCardId(cardId,param);
			this.delete(scmCostCardDetailList, param);
		}
	}
	@Override
	protected void afterSelect(ScmCostCardDetail2 entity, Param param) throws AppException {
		setConverMap(entity,param);
	}
	
	private void setConverMap(ScmCostCardDetail2 entity, Param param) throws AppException {
		if(entity != null && entity.getCardId() > 0){
			if(entity.getCstUnit()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(entity.getCstUnit(), param);
				if(scmMeasureUnit != null){
					entity.setCstUnitName(scmMeasureUnit.getUnitName());
				}
			}
			if(entity.getInvUnit()>0) {
				ScmMeasureUnit scmMeasureUnit = scmMeasureUnitBiz.selectDirect(entity.getInvUnit(), param);
				if(scmMeasureUnit != null){
					ScmMaterialUnitRelation2 scmMaterialUnitRelation = new ScmMaterialUnitRelation2(true);
					scmMaterialUnitRelation.setTargetUnitId(scmMeasureUnit.getId());
					scmMaterialUnitRelation.setUnitName(scmMeasureUnit.getUnitName());
					entity.setConvertMap(ScmCostCardDetail2.FN_INVUNIT, scmMaterialUnitRelation);
				}
			}
			if (StringUtils.isNotBlank(entity.getCreator())){
				//设置人
				Usr usr = usrBiz.selectByCode(entity.getCreator(), param);
				if (usr != null) {
					entity.setConvertMap(ScmCostCardDetail2.FN_CREATOR, usr);
				}
			}
			if (StringUtils.isNotBlank(entity.getChecker())){
				//审核人
				Usr usr = usrBiz.selectByCode(entity.getChecker(), param);
				if (usr != null) {
					entity.setConvertMap(ScmCostCardDetail2.FN_CHECKER, usr);
				}
			}
		}
	}
	
	@Override
	public void replaceItemByCardIds(String cardIds, ScmDishReplaceCostCard scmDishReplaceCostCard, Param param)
			throws AppException {
		if(StringUtils.isNotBlank(cardIds) && scmDishReplaceCostCard != null){
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("cardIds", cardIds);
			map.put("itemId", scmDishReplaceCostCard.getItemId());
			map.put("replaceItemId", scmDishReplaceCostCard.getReplaceItemId());
			map.put("replaceItemNo", scmDishReplaceCostCard.getReplaceItemNo());
			map.put("replaceItemName", scmDishReplaceCostCard.getReplaceItemName());
			map.put("replaceCstUnit", scmDishReplaceCostCard.getReplaceCstUnit());
			map.put("replaceinvUnit", scmDishReplaceCostCard.getReplaceInvUnit());
			map.put("replaceConvrate", scmDishReplaceCostCard.getReplaceConvrate());
			map.put("replacePrice", scmDishReplaceCostCard.getReplacePrice());
			((ScmCostCardDetailDAO)dao).replaceItemByCardIds(map);
		}
	}

	@Override
	public List<ScmCostCardDetail2> checkItemCostPrice(ScmPriceUpdSet scmPriceUpdSet, Param param) throws AppException {
		if(scmPriceUpdSet != null){
			HashMap<String,Object> map = new HashMap<String,Object>();
			map.put("orgUnitNo",scmPriceUpdSet.getOrgUnitNo());
			map.put("accDate",scmPriceUpdSet.getBaseCostUpd());
			return ((ScmCostCardDetailDAO)dao).checkItemCostPrice(map);
		}
		return null;
	}
}
