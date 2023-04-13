package com.armitage.server.ifbc.costcard.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCard;
import com.armitage.server.ifbc.costcard.model.ScmDishReplaceCostCardDetail;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCostCardDetailBiz;
import com.armitage.server.ifbc.costcard.service.ScmDishReplaceCostCardBiz;
import org.springframework.stereotype.Service;

@Service("scmDishReplaceCostCardBiz")
public class ScmDishReplaceCostCardBizImpl extends BaseBizImpl<ScmDishReplaceCostCard> implements ScmDishReplaceCostCardBiz {
	private ScmCostCardBiz scmCostCardBiz;
	private ScmCookCostCardBiz scmCookCostCardBiz;
	private ScmCostCardDetailBiz scmCostCardDetailBiz;
	private ScmCookCostCardDetailBiz scmCookCostCardDetailBiz;
	
	public void setScmCostCardBiz(ScmCostCardBiz scmCostCardBiz) {
		this.scmCostCardBiz = scmCostCardBiz;
	}

	public void setScmCookCostCardBiz(ScmCookCostCardBiz scmCookCostCardBiz) {
		this.scmCookCostCardBiz = scmCookCostCardBiz;
	}

	public void setScmCostCardDetailBiz(ScmCostCardDetailBiz scmCostCardDetailBiz) {
		this.scmCostCardDetailBiz = scmCostCardDetailBiz;
	}

	public void setScmCookCostCardDetailBiz(ScmCookCostCardDetailBiz scmCookCostCardDetailBiz) {
		this.scmCookCostCardDetailBiz = scmCookCostCardDetailBiz;
	}

	@Override
	public CommonBean getDataForReplace(ScmDishReplaceCostCard scmDishReplaceCostCard, Param param)
			throws AppException {
		CommonBean bean = new CommonBean();
		if(scmDishReplaceCostCard != null){
			List<ScmDishReplaceCostCardDetail> dtlList = new ArrayList();
			List<ScmCostCard2> scmCostCardList = scmCostCardBiz.getDataForReplace(scmDishReplaceCostCard, param);
			if(scmCostCardList!=null) {
				for(ScmCostCard2 scmCostCard:scmCostCardList) {
					ScmDishReplaceCostCardDetail scmDishReplaceCostCardDetail = new ScmDishReplaceCostCardDetail(true);
					scmDishReplaceCostCardDetail.setCook(false);
					scmDishReplaceCostCardDetail.setCardId(scmCostCard.getId());
					scmDishReplaceCostCardDetail.setCode(scmCostCard.getDishCode());
					scmDishReplaceCostCardDetail.setName(scmCostCard.getDishName());
					scmDishReplaceCostCardDetail.setQty(scmCostCard.getQty());
					scmDishReplaceCostCardDetail.setLocked(scmCostCard.isLocked());
					dtlList.add(scmDishReplaceCostCardDetail);
				}
			}
			if(scmDishReplaceCostCard.isIncludeCook()) {
				List<ScmCookCostCard2> scmCookCostCardList = scmCookCostCardBiz.getDataForReplace(scmDishReplaceCostCard, param);
				if(scmCookCostCardList!=null) {
					for(ScmCookCostCard2 scmCookCostCard:scmCookCostCardList) {
						ScmDishReplaceCostCardDetail scmDishReplaceCostCardDetail = new ScmDishReplaceCostCardDetail(true);
						scmDishReplaceCostCardDetail.setCook(true);
						scmDishReplaceCostCardDetail.setCardId(scmCookCostCard.getId());
						scmDishReplaceCostCardDetail.setCode(scmCookCostCard.getCookCode());
						scmDishReplaceCostCardDetail.setName(scmCookCostCard.getCookName());
						scmDishReplaceCostCardDetail.setQty(scmCookCostCard.getQty());
						scmDishReplaceCostCardDetail.setLocked(scmCookCostCard.isLocked());
						dtlList.add(scmDishReplaceCostCardDetail);
					}
				}
			}
			bean.setList(dtlList);
		}
		return bean;
	}

	@Override
	public void replaceItem(ScmDishReplaceCostCard scmDishReplaceCostCard,List<ScmDishReplaceCostCardDetail> scmDishReplaceCostCardDetailList,  Param param) throws AppException {
		if(scmDishReplaceCostCard != null && scmDishReplaceCostCardDetailList != null && !scmDishReplaceCostCardDetailList.isEmpty()){
			for(ScmDishReplaceCostCardDetail scmDishReplaceCostCardDetail : scmDishReplaceCostCardDetailList){
				if(scmDishReplaceCostCardDetail.isCook()) {
					ScmCookCostCard2 scmCookCostCard = scmCookCostCardBiz.selectDirect(scmDishReplaceCostCardDetail.getCardId(), param);
					if(scmCookCostCard!=null) {
						scmCookCostCard.setApprovaled(false);
						scmCookCostCardBiz.updateDirect(scmCookCostCard, param);
						scmCostCardDetailBiz.replaceItemByCardIds(String.valueOf(scmDishReplaceCostCardDetail.getCardId()), scmDishReplaceCostCard, param);
					}
				}else {
					ScmCostCard2 scmCostCard = scmCostCardBiz.selectDirect(scmDishReplaceCostCardDetail.getCardId(), param);
					if(scmCostCard!=null) {
						scmCostCard.setApprovaled(false);
						scmCostCardBiz.updateDirect(scmCostCard, param);
						scmCostCardDetailBiz.replaceItemByCardIds(String.valueOf(scmDishReplaceCostCardDetail.getCardId()), scmDishReplaceCostCard, param);
					}
				}
			}
		}
	}

}
