
package com.armitage.server.iscm.purchasemanage.pricemanage.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPriceAssign2;

public interface ScmPurPriceAssignBiz extends BaseBiz<ScmPurPriceAssign2> {

	/**
	 * 根据pmId查询
	 * @param pmId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurPriceAssign2> selectByPmId(long pmId, Param param) throws AppException;
}
