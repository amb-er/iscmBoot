package com.armitage.server.iscm.basedata.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;

public interface ScmSupplierQualifyTypeBiz extends BaseBiz<ScmSupplierQualifyType> {
	public ScmSupplierQualifyType selectByCode(String code, Param param) throws AppException;
	List<ScmSupplierQualifyType> selectByVendor(long id, Param createParam) throws AppException;

}

