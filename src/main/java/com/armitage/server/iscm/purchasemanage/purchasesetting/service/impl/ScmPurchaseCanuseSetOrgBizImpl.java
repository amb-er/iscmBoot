
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurchaseCanuseSetOrgBiz;
import com.armitage.server.system.model.OrgAdmin2;
import com.armitage.server.system.service.OrgAdminBiz;
import org.springframework.stereotype.Service;

@Service("scmPurchaseCanuseSetOrgBiz")
public class ScmPurchaseCanuseSetOrgBizImpl extends BaseBizImpl<ScmPurchaseCanuseSetOrg2> implements ScmPurchaseCanuseSetOrgBiz {
	private OrgAdminBiz orgAdminBiz;
	
	public void setOrgAdminBiz(OrgAdminBiz orgAdminBiz) {
		this.orgAdminBiz = orgAdminBiz;
	}

	@Override
	public List<ScmPurchaseCanuseSetOrg2> selectByPcsId(long pcsId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("pcsId", pcsId);
		List<ScmPurchaseCanuseSetOrg2> scmPurchaseCanuseSetOrgList = ((ScmPurchaseCanuseSetOrgDAO)dao).selectByPcsId(map);
		if(scmPurchaseCanuseSetOrgList!=null && !scmPurchaseCanuseSetOrgList.isEmpty()) {
			for(ScmPurchaseCanuseSetOrg2 scmPurchaseCanuseSetOrg:scmPurchaseCanuseSetOrgList) {
				OrgAdmin2 orgAdmin = orgAdminBiz.selectByOrgUnitNo(scmPurchaseCanuseSetOrg.getOrgUnitNo(), param);
				if(orgAdmin!=null)
					scmPurchaseCanuseSetOrg.setOrgUnitName(orgAdmin.getOrgUnitName());
			}
		}
		return scmPurchaseCanuseSetOrgList;
	}

	@Override
	public void deleteByPcsId(long pcsId, Param param) throws AppException {
		List<ScmPurchaseCanuseSetOrg2> scmPurchaseCanuseSetOrgList = this.selectByPcsId(pcsId, param);
		if(scmPurchaseCanuseSetOrgList!=null && !scmPurchaseCanuseSetOrgList.isEmpty()) {
			this.delete(scmPurchaseCanuseSetOrgList, param);
		}
	}

}
