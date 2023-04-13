package com.armitage.server.iscm.basedata.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData2;

public interface ScmSupplierConfirmDataBiz extends BaseBiz<ScmSupplierConfirmData2> {
	public ScmSupplierConfirmData2 selectByBcId(long bcId,Param param) throws AppException;
	
	public ScmSupplierConfirmData2 selectByBillNoAndType(String billNo,String billType,Param param) throws AppException;

	public void saveConfirmAdj(ScmSupplierConfirmData2 scmSupplierConfirmData, Param param) throws AppException;
	
	public void deleteListByBillNoAndType(String billNo,String billType,Param param) throws AppException;

	public List<ScmSupplierConfirmData2> selectByBillNoAndPurPrice(String billNo, String billtype, Param param) throws AppException;
}
