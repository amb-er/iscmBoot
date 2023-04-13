package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.Scmsupplierlinkman;

public interface ScmsupplierlinkmanBiz extends BaseBiz<Scmsupplierlinkman> {

	/**
	 * 根据vendorId查询
	 * @param vendorId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<Scmsupplierlinkman> selectByVendorId(long vendorId, Param param) throws AppException;
	
	/**
	 * 根据vendorId删除
	 * @param vendorId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByVendorId(long vendorId, Param param) throws AppException;
}
