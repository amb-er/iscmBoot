
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg2;

public interface ScmPurSupplierSourceRecOrgBiz extends BaseBiz<ScmPurSupplierSourceRecOrg2> {
	public List<ScmPurSupplierSourceRecOrg2> selectByBillId(long billId, Param param) throws AppException;
	public void deleteByBillId(long billId, Param param) throws AppException;

}
