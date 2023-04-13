package com.armitage.server.ifbm.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbm.model.FbmDish2;
import com.armitage.server.ifbm.model.FbmStat;
import com.armitage.server.ifbm.service.FbmDishBiz;
import com.armitage.server.ifbm.service.FbmStatBiz;
import org.springframework.stereotype.Service;

@Service("fbmDishBiz")
public class FbmDishBizImpl extends BaseBizImpl<FbmDish2> implements FbmDishBiz {
	private FbmStatBiz fbmStatBiz;
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	
	public void setFbmStatBiz(FbmStatBiz fbmStatBiz) {
		this.fbmStatBiz = fbmStatBiz;
	}

	public void setScmResOrgUnitMapBiz(ScmResOrgUnitMapBiz scmResOrgUnitMapBiz) {
		this.scmResOrgUnitMapBiz = scmResOrgUnitMapBiz;
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

	@Override
	protected void afterFindPage(List list, Page page, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(FbmDish2 fbmDish:(List<FbmDish2>)list) {
				this.setConverMap(fbmDish, param);
			}
		}
	}

	@Override
	protected void afterQueryPage(List list, Page page, String xmlId, Param param) throws AppException {
		if(list!=null && !list.isEmpty()) {
			for(FbmDish2 fbmDish:(List<FbmDish2>)list) {
				this.setConverMap(fbmDish, param);
			}
		}
	}

	private void setConverMap(FbmDish2 fbmDish, Param param) throws AppException {
		if(fbmDish!=null) {
			if(fbmDish.getStatId()>0) {
				FbmStat fbmStat = fbmStatBiz.selectDirect(fbmDish.getStatId(), param);
				if(fbmStat!=null) {
					fbmDish.setStatCode(fbmStat.getCode());
					fbmDish.setStatName(fbmStat.getName());
				}
			}
		}
	}
}
