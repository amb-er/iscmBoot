
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurBuyerOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurBuyerOrgBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmPurBuyerOrgBiz")
public class ScmPurBuyerOrgBizImpl extends BaseBizImpl<ScmPurBuyerOrg> implements ScmPurBuyerOrgBiz {
	private OrgBaseUnitBiz orgBaseUnitBiz;
	
	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	@Override
	public List<ScmPurBuyerOrg2> selectByBuyerId(long buyerId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("buyerId", buyerId);
		List<ScmPurBuyerOrg2> scmPurBuyerOrgList = ((ScmPurBuyerOrgDAO)dao).selectByBuyerId(map);
		if(scmPurBuyerOrgList != null && !scmPurBuyerOrgList.isEmpty()){
			for(ScmPurBuyerOrg2 scmPurBuyerOrg : scmPurBuyerOrgList){
				OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurBuyerOrg.getOrgUnitNo(), param);
				if(orgBaseUnit != null){
					scmPurBuyerOrg.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
		}
		return scmPurBuyerOrgList;
	}
	
}
