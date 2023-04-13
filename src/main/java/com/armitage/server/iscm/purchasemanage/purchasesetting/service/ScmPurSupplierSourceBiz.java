
package com.armitage.server.iscm.purchasemanage.purchasesetting.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.CommonAuditParams;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSource2;

public interface ScmPurSupplierSourceBiz extends BaseBiz<ScmPurSupplierSource2> {
	public ScmPurSupplierSource2 doSubmit(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;
	public ScmPurSupplierSource2 doUnSubmit(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;
    public ScmPurSupplierSource2 doAudit(CommonAuditParams commonAuditParams, Param param) throws AppException;
    public ScmPurSupplierSource2 doUnAudit(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;
	public ScmPurSupplierSource2 doRelease(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;
	public ScmPurSupplierSource2 queryPurSupplierSource(ScmPurSupplierSource2 scmPurSupplierSource, Param param) throws AppException;
}
