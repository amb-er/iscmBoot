package com.armitage.server.iscm.purchasemanage.pricemanage.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurPricePrepareEntry2;

public interface ScmPurPricePrepareEntryBiz extends BaseBiz<ScmPurPricePrepareEntry2> {
	/**
	 * 根据pmId查询
	 * @param pmId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmPurPricePrepareEntry2> selectByPmId(long pmId, Param param) throws AppException;
	
	/**
	 * 根据pmId删除
	 * @param pmId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByPmId(long pmId, Param param) throws AppException;
}

