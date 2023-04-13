package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroup2;

public interface ScmsuppliergroupBiz extends BaseBiz<Scmsuppliergroup2> {
    public List<Scmsuppliergroup2> findChild(long venderClassId, Param param) throws AppException;
    public Scmsuppliergroup2 selectByClassCode(long standardId, String classCode, Param param) throws AppException;
	public Scmsuppliergroup2 selectByVendorId(long vendorId, Param param) throws AppException;
}
