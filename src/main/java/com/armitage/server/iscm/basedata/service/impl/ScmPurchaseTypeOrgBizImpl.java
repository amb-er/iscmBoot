
package com.armitage.server.iscm.basedata.service.impl;

import java.util.HashMap;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.dao.ScmPurchaseTypeOrgDAO;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import com.armitage.server.iscm.basedata.model.ScmPurchaseTypeOrg;
import com.armitage.server.iscm.basedata.service.ScmPurchaseTypeOrgBiz;
import org.springframework.stereotype.Service;

@Service("scmPurchaseTypeOrgBiz")
public class ScmPurchaseTypeOrgBizImpl extends BaseBizImpl<ScmPurchaseTypeOrg> implements ScmPurchaseTypeOrgBiz {

	@Override
	public void updateByPurchaseType(ScmPurchaseType2 scmPurchaseType, Param param) throws AppException {
		ScmPurchaseTypeOrg scmPurchaseTypeOrg = this.selectByTypeAndOrgNo(scmPurchaseType.getId(),param.getOrgUnitNo(),param);
		if(scmPurchaseTypeOrg==null) {
			scmPurchaseTypeOrg = new ScmPurchaseTypeOrg(true);
			scmPurchaseTypeOrg.setTypeId(scmPurchaseType.getId());
			scmPurchaseTypeOrg.setOrgUnitNo(param.getOrgUnitNo());
			scmPurchaseTypeOrg.setFlag(scmPurchaseType.isFlag());
			scmPurchaseTypeOrg.setSort(scmPurchaseType.getSort());
			this.add(scmPurchaseTypeOrg,param);
		}else {
			scmPurchaseTypeOrg.setFlag(scmPurchaseType.isFlag());
			scmPurchaseTypeOrg.setSort(scmPurchaseType.getSort());
			this.update(scmPurchaseTypeOrg, param);
		}
	}

	@Override
	public ScmPurchaseTypeOrg selectByTypeAndOrgNo(long typeId, String orgUnitNo, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("typeId", typeId);
		map.put("orgUnitNo", orgUnitNo);
		return ((ScmPurchaseTypeOrgDAO)dao).selectByTypeAndOrgNo(map);
	}

}
