
package com.armitage.server.iscm.basedata.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;

public interface ScmMaterialPurchaseBiz extends BaseBiz<ScmMaterialPurchase2> {
	
	/**
	 * 根据itemId和组织查询
	 * @param itemId
	 * @param resOrgUnitNo
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmMaterialPurchase2 selectByItemIdAndOrgUnitNo(long itemId, String resOrgUnitNo,Param param) throws AppException;
	public ScmMaterialPurchase2 updateByPurchase(ScmMaterialPurchase2 scmMaterialPurchase,Param param) throws AppException;
	/**
	 * 批量修改比率
	 * @param scmMaterial2
	 * @param createParam
	 * @return
	 * @throws AppException
	 */
	public CommonBean batchRatioSet(List<ScmMaterial2> scmMaterial2, Param createParam) throws AppException;
}
