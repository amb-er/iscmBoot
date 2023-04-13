package com.armitage.server.ifbm.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCardPrice;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardBiz;
import com.armitage.server.ifbc.costcard.service.ScmCookCostCardPriceBiz;
import com.armitage.server.ifbm.dao.FbmCookDAO;
import com.armitage.server.ifbm.model.FbmCook2;
import com.armitage.server.ifbm.service.FbmCookBiz;
import org.springframework.stereotype.Service;

@Service("fbmCookBiz")
public class FbmCookBizImpl extends BaseBizImpl<FbmCook2> implements FbmCookBiz {
	private ScmCookCostCardPriceBiz scmCookCostCardPriceBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	private ScmCookCostCardBiz scmCookCostCardBiz;
	
	public void setScmCookCostCardPriceBiz(ScmCookCostCardPriceBiz scmCookCostCardPriceBiz) {
		this.scmCookCostCardPriceBiz = scmCookCostCardPriceBiz;
	}

	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	public void setScmCookCostCardBiz(ScmCookCostCardBiz scmCookCostCardBiz) {
		this.scmCookCostCardBiz = scmCookCostCardBiz;
	}

	@Override
	public List<FbmCook2> selectByDishId(ScmCostCard2 scmCostCard, Param param) throws AppException {
		String orgUnitNo=param.getOrgUnitNo();
		String controlUnitNo=scmCostCard.getControlUnitNo();
		ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(orgUnitNo,param);
		if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
			orgUnitNo = scmResOrgUnitMap.getFbmResOrgUnitNo();
			controlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("dishId", scmCostCard.getDishId());
		map.put("orgUnitNo", orgUnitNo);
		List<FbmCook2> fbmCookList = ((FbmCookDAO)dao).selectByDishId(map);
		if(fbmCookList==null || fbmCookList.isEmpty()) {
			map.put("orgUnitNo", controlUnitNo);
			fbmCookList = ((FbmCookDAO)dao).selectByDishId(map);
		}
		if(fbmCookList!=null && !fbmCookList.isEmpty()) {
			for(FbmCook2 fbmCook:fbmCookList) {
				ScmCookCostCardPrice scmCookCostCardPrice = scmCookCostCardPriceBiz.selectCurrPriceByCookId(fbmCook.getId(),param);
				if(scmCookCostCardPrice!=null) {
					fbmCook.setCostRatio(scmCookCostCardPrice.getCostPrice());
				}else {
					fbmCook.setCostRatio(BigDecimal.ZERO);
				}
				ScmCookCostCard2 scmCookCostCard = scmCookCostCardBiz.selectByCookId(fbmCook.getId(),param);
				if(scmCookCostCard!=null)
					fbmCook.setCardId(scmCookCostCard.getId());
			}
			
		}
		return fbmCookList;
	}

	@Override
	protected HashMap<String, Object> addFindAllPageParam(Page page, HashMap<String, Object> map, Param param) {
		String orgUnitNo=param.getOrgUnitNo();
		String controlUnitNo = param.getControlUnitNo();
		ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(param.getOrgUnitNo(), param);
		if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared()) {
			orgUnitNo = scmResOrgUnitMap.getFbmResOrgUnitNo();
			controlUnitNo = scmResOrgUnitMap.getFbmControlUnitNo();
		}
		map.put("controlUnitNo", controlUnitNo);
		map.put("orgUnitNo", orgUnitNo);
		return map;
	}

}
