
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry2;

public interface ScmPurSupplierSourceEntryBiz extends BaseBiz<ScmPurSupplierSourceEntry2> {

	public void deleteByBillId(long billId, Param param) throws AppException;
	public List<ScmPurSupplierSourceEntry2> selectByBillId(long billId, Param param) throws AppException;
}
