
package com.armitage.server.iscm.purchasemanage.purchasesetting.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.armitage.server.common.base.biz.BaseBizImpl;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplierSourceRecOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.service.ScmPurSupplierSourceRecOrgBiz;
import com.armitage.server.system.model.OrgBaseUnit2;
import com.armitage.server.system.service.OrgBaseUnitBiz;
import org.springframework.stereotype.Service;

@Service("scmPurSupplierSourceRecOrgBiz")
public class ScmPurSupplierSourceRecOrgBizImpl extends BaseBizImpl<ScmPurSupplierSourceRecOrg2> implements ScmPurSupplierSourceRecOrgBiz {
	private OrgBaseUnitBiz orgBaseUnitBiz;

	public void setOrgBaseUnitBiz(OrgBaseUnitBiz orgBaseUnitBiz) {
		this.orgBaseUnitBiz = orgBaseUnitBiz;
	}

	@Override
	public List<ScmPurSupplierSourceRecOrg2> selectByBillId(long billId, Param param) throws AppException {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("billId", billId);
		List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList = ((ScmPurSupplierSourceRecOrgDAO)dao).selectByBillId(map);
		if(scmPurSupplierSourceRecOrgList!=null && !scmPurSupplierSourceRecOrgList.isEmpty()) {
			for(ScmPurSupplierSourceRecOrg2 scmPurSupplierSourceRecOrg:scmPurSupplierSourceRecOrgList) {
				if(StringUtils.isNotBlank(scmPurSupplierSourceRecOrg.getReceiveOrgUnitNo())) {
					OrgBaseUnit2 orgBaseUnit = orgBaseUnitBiz.selectbyOrgNo(scmPurSupplierSourceRecOrg.getReceiveOrgUnitNo(), param);
					if(orgBaseUnit!=null)
						scmPurSupplierSourceRecOrg.setOrgUnitName(orgBaseUnit.getOrgUnitName());
				}
			}
		}
		return scmPurSupplierSourceRecOrgList;
	}

	@Override
	public void deleteByBillId(long billId, Param param) throws AppException {
		if(billId > 0){
			List<ScmPurSupplierSourceRecOrg2> scmPurSupplierSourceRecOrgList = this.selectByBillId(billId,param);
			this.delete(scmPurSupplierSourceRecOrgList, param);
		}
	}

}
