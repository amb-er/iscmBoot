
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill;

public interface ScmQualifieInfoBiz extends BaseBiz<ScmQualifieInfo2> {
	public List<ScmQualifieInfo2> selectByVendorId(long vendorId, Param param) throws AppException;
	public List<ScmQualifieInfo2> selectAttachByVendorId(long vendorId, Param param) throws AppException;
	public void auditQualify(ScmQualifieInfo2 scmQualifieInfo, String vendorOrQualifieInfo, Param param) throws AppException;
	public void deleteQualifyByVendorId(long vendorId, Param param) throws AppException;
	public void doUnAuditQualifieInfo(ScmSupplierQualifieInfoBill require,String status, Param param) throws AppException;
}
