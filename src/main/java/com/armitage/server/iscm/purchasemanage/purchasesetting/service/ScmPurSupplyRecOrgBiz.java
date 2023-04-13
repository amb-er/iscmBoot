
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyInfo2;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg2;

public interface ScmPurSupplyRecOrgBiz extends BaseBiz<ScmPurSupplyRecOrg2> {

	public void updateBySupplyInfo(ScmPurSupplyInfo2 scmPurSupplyInfo,List<ScmPurSupplyRecOrg2> scmPurSupplyRecOrgList, Param param) throws AppException;
	public List<ScmPurSupplyRecOrg2> selectBySupplyInfoId(long supplyId, Param param) throws AppException;
	public void addBySupplierSource(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;
}
