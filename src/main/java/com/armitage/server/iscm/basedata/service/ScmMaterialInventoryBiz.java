
package com.armitage.server.iscm.basedata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;

public interface ScmMaterialInventoryBiz extends BaseBiz<ScmMaterialInventory2> {

	/**
	 * 根据itemId和组织查询
	 * @param itemId
	 * @param orgUnitNo
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmMaterialInventory2 selectByItemIdAndOrgUnitNo(long itemId, String orgUnitNo,Param param) throws AppException;
	public ScmMaterialInventory2 updateByInventory(ScmMaterialInventory2 scmMaterialInventory,Param param) throws AppException;
	public ScmMaterialInventory2 selectByItemId(long itemId, String orgUnitNo, String controlUnitNo, Param param) throws AppException;
}
