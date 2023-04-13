package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupdetail;

public interface ScmsuppliergroupdetailBiz extends BaseBiz<Scmsuppliergroupdetail> {

	/**
	 * 根据vendorId或groupId删除
	 * @param vendorId
	 * @param groupId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByVendorIdOrGroupId(long vendorId, long groupId,Param param) throws AppException;
}
