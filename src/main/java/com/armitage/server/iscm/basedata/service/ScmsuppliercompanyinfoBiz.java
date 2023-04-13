package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;

public interface ScmsuppliercompanyinfoBiz extends BaseBiz<Scmsuppliercompanyinfo2> {

	/**
	 * 根据vendorId和组织查询
	 * @param vendorId
	 * @param resOrgUnitNo
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Scmsuppliercompanyinfo2 selectByVendorIdAndOrgUnitNo(long vendorId, String resOrgUnitNo,Param param) throws AppException;

	public Scmsuppliercompanyinfo2 updateByCompanyInfo(Scmsuppliercompanyinfo2 scmsuppliercompanyinfo, Param param) throws AppException;	
}