package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.Scmsupplierpurchaseinfo2;

public interface ScmsupplierpurchaseinfoBiz extends BaseBiz<Scmsupplierpurchaseinfo2> {

	/**
	 * 根据vendorId和组织查询
	 * @param vendorId
	 * @param resOrgUnitNo
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Scmsupplierpurchaseinfo2 selectByVendorIdAndOrgUnitNo(long vendorId, String resOrgUnitNo,Param param) throws AppException;

	public Scmsupplierpurchaseinfo2 updateByPurchase(Scmsupplierpurchaseinfo2 scmsupplierpurchaseinfo, Param param) throws AppException;
}