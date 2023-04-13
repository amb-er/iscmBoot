package com.armitage.server.iscm.basedata.service;

import java.util.Date;
import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillEntry2;

public interface ScmSupplierQualifieInfoBillEntryBiz extends BaseBiz<ScmSupplierQualifieInfoBillEntry2> {
	public List<ScmSupplierQualifieInfoBillEntry2> selectByBillId(long billId,Param param) throws AppException;

	public void updateRowStatusByBillId(long billId, String status,String checker,Date checkDate, Param param) throws AppException;
}
