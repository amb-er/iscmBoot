package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMoveIssueBillEntry2;
import java.util.List;
import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;

public interface ScmInvMoveIssueBillEntryBiz extends BaseBiz<ScmInvMoveIssueBillEntry2> {
	/**
	 * 根据otId查询
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvMoveIssueBillEntry2> selectByOtId(long otId, Param param) throws AppException;
	
	/**
	 * 根据otId删除
	 * @param otId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByOtId(long otId, Param param) throws AppException;

    public void deleteById(long id, Param param) throws AppException;

    public List<ScmInvMoveIssueBillEntry2> selectInvQty(long otId, Param param) throws AppException;

	public List<ScmInvMoveIssueBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException;
}
