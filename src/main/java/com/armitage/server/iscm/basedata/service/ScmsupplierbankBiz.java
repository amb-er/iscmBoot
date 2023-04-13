package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.Scmsupplierbank;

public interface ScmsupplierbankBiz extends BaseBiz<Scmsupplierbank> {

	/**
	 * 根据vendorId和组织查询
	 * @param vendorId
	 * @param orgUnitNo
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<Scmsupplierbank> selectByVendorIdAndOrgUnitNo(long vendorId, String orgUnitNo, Param param) throws AppException;
	
	/**
	 * 根据vendorId删除
	 * @param vendorId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByVendorId(long vendorId, Param param) throws AppException;
}
