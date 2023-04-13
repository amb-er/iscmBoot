package com.armitage.server.ifbm.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Page;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.ifbc.basedata.model.ScmResOrgUnitMap;
import com.armitage.server.ifbc.basedata.service.ScmResOrgUnitMapBiz;
import com.armitage.server.ifbm.model.FbmStat;
import com.armitage.server.ifbm.service.FbmStatBiz;
import org.springframework.stereotype.Service;

@Service("fbmStatBiz")
public class FbmStatBizImpl extends BaseBizImpl<FbmStat> implements FbmStatBiz {
	private ScmResOrgUnitMapBiz scmResOrgUnitMapBiz;
	
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

}
