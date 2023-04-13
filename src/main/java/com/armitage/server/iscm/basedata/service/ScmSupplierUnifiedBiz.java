package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierUnified;
import com.armitage.server.iscm.basedata.model.Scmsupplier2;

public interface ScmSupplierUnifiedBiz extends BaseBiz<ScmSupplierUnified> {

	public void saveUnified(Scmsupplier2 scmsupplier,List<ScmSupplierUnified> scmSupplierUnifiedList,Param param) throws AppException;

	public List<ScmSupplierUnified> selectByVendorId(long vendorId, Param param) throws AppException;
}
