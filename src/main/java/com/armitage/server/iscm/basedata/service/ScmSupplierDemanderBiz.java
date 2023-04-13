package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;

public interface ScmSupplierDemanderBiz extends BaseBiz<ScmSupplierDemander> {
	public ScmSupplierDemander selectByCtrl(String controlUnitNo,Param param) throws AppException;
	public ScmSupplierDemander selectByDemanderId(long demanderId,Param param) throws AppException;
	public ScmSupplierDemander addByIdAndCtrl(long demanderId,String controlUnitNo,Param param) throws AppException;
}

