
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;

public interface ScmMaterialCompanyInfoBiz extends BaseBiz<ScmMaterialCompanyInfo> {

	/**
	 * 根据itemId和组织查询
	 * @param itemId
	 * @param resOrgUnitNo
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmMaterialCompanyInfo selectByItemIdAndOrgUnitNo(long itemId, String resOrgUnitNo,Param param) throws AppException;
	public ScmMaterialCompanyInfo updateByCompanyInfo(ScmMaterialCompanyInfo scmMaterialCompanyInfo,Param param) throws AppException;
}
