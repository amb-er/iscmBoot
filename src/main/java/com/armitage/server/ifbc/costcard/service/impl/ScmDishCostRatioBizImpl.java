package com.armitage.server.ifbc.costcard.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbc.costcard.dao.ScmDishCostRatioDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import com.armitage.server.ifbc.costcard.model.ScmDishCostRatio2;
import com.armitage.server.ifbc.costcard.service.ScmDishCostRatioBiz;
import com.armitage.server.ifbm.model.FbmDishPrc;
import com.armitage.server.ifbm.model.FbmDishPrc2;
import com.armitage.server.ifbm.service.FbmDishPrcBiz;
import org.springframework.stereotype.Service;

@Service("scmDishCostRatioBiz")
public class ScmDishCostRatioBizImpl extends BaseBizImpl<ScmDishCostRatio2> implements ScmDishCostRatioBiz {

	private FbmDishPrcBiz fbmDishPrcBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	
	public void setFbmDishPrcBiz(FbmDishPrcBiz fbmDishPrcBiz) {
		this.fbmDishPrcBiz = fbmDishPrcBiz;
	}

	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
	}

	@Override
	public List<ScmDishCostRatio2> selectCostRatio(ScmCostCard2 scmCostCard, Param param) throws AppException {
		if(scmCostCard != null){
			ScmResOrgUnitMap scmResOrgUnitMap = scmResOrgUnitMapBiz.selectByResOrgUnit(scmCostCard.getResOrgUnitNo(), param);
			String fbmOrgUnitNo=param.getControlUnitNo();
			if(scmResOrgUnitMap!=null && !scmResOrgUnitMap.isShared())
				fbmOrgUnitNo=scmResOrgUnitMap.getFbmControlUnitNo();
			Page page = new Page();
			page.setModelClass(FbmDishPrc.class);
			page.setShowCount(Integer.MAX_VALUE);
			List<String> argList = new ArrayList();
			argList.add("dishId="+scmCostCard.getDishId());
			argList.add("orgUnitNo="+fbmOrgUnitNo);
			argList.add("removeDishSpecId="+scmCostCard.getDishSpecId());
			List<FbmDishPrc2> fbmDishPrcList = fbmDishPrcBiz.queryPage(page, argList, "selectByDishIdPage", param);
			List<ScmDishCostRatio2> list = this.selectByCardId(scmCostCard.getId(), param);
			if(list==null) {
				list = new ArrayList();
			}else{
				for(ScmDishCostRatio2 scmDishCostRatio:list) {
					FbmDishPrc2 fbmDishPrc = fbmDishPrcBiz.selectByDishAndSpecId(fbmOrgUnitNo,scmCostCard.getDishId(), scmDishCostRatio.getDishSpecId(),  param);
					if(fbmDishPrc != null){
						scmDishCostRatio.setDishSpecName(fbmDishPrc.getDishSpecName());
						scmDishCostRatio.setPrice(fbmDishPrc.getPrice());
					}
				}
			}
			if(fbmDishPrcList != null && !fbmDishPrcList.isEmpty()){
				for(FbmDishPrc2 fbmDishPrc : fbmDishPrcList){
					boolean exists = false;
					for(ScmDishCostRatio2 scmDishCostRatio:list) {
						if(scmDishCostRatio.getDishSpecId()==fbmDishPrc.getDishSpecId() || fbmDishPrc.getDishSpecId()==scmCostCard.getDishId()){
							exists=true;
							break;
						}
					}
					if(!exists) {
						ScmDishCostRatio2 scmDishCostRatio = new ScmDishCostRatio2(true);
						scmDishCostRatio.setCardId(scmCostCard.getId());
						scmDishCostRatio.setDishSpecId(fbmDishPrc.getDishSpecId());
						scmDishCostRatio.setDishSpecName(fbmDishPrc.getDishSpecName());
						scmDishCostRatio.setPrice(fbmDishPrc.getPrice());
						list.add(scmDishCostRatio);
					}
				}
				return list;
			}
		}
		return null;
	}

	@Override
	public List<ScmDishCostRatio2> saveCostRatio(List<ScmDishCostRatio2> scmDishCostRatioList, Param param)
			throws AppException {
		if(scmDishCostRatioList != null && !scmDishCostRatioList.isEmpty()){
			List<ScmDishCostRatio2> list = new ArrayList<>();
			int lineId = 1;
			for(ScmDishCostRatio2 scmDishCostRatio : scmDishCostRatioList){
				scmDishCostRatio.setLineId(lineId);
				if(scmDishCostRatio.getId() > 0){
					this.update(scmDishCostRatio, param);
				}else{
					this.add(scmDishCostRatio, param);
				}
				list.add(scmDishCostRatio);
				lineId++;
			}
			return list;
		}
		return null;
	}

	@Override
	public List<ScmDishCostRatio2> selectByCardId(long cardId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("cardId", cardId);
		return ((ScmDishCostRatioDAO)dao).selectByCardId(map);
	}

}
