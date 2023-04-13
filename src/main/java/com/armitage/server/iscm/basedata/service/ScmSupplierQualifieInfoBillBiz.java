package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBillAdvQuery;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;

public interface ScmSupplierQualifieInfoBillBiz extends BaseBiz<ScmSupplierQualifieInfoBill2> {

	public void UpdateByQualifieInfo(List<ScmQualifieInfo2> scmQualifieInfoList2, Param param) throws AppException;
	public ScmSupplierQualifieInfoBill2 doSubmitQualifieInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill, Param param) throws AppException;
	public ScmSupplierQualifieInfoBill2 undoSubmitQualifieInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill, Param param) throws AppException;
	public ScmSupplierQualifieInfoBill2 doAuditQualifieInfo(CommonAuditParams commonAuditParams,Param param) throws AppException ;
	public ScmSupplierQualifieInfoBill2 doUnAuditQualifieInfo(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,Param param) throws AppException ;
	public List<ScmSupplierQualifieInfoBill2> queryQualifieInfoBillList(ScmSupplierQualifieInfoBillAdvQuery scmSupplierQualifieInfoBillAdvQuery, int pageIndex, Param param) throws AppException ;
	public ScmSupplierQualifieInfoBill2 queryQualifieInfoBill(ScmSupplierQualifieInfoBill2 scmSupplierQualifieInfoBill,Param param) throws AppException ;
}
