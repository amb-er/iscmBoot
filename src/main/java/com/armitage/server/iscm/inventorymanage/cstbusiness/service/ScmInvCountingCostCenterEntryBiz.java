package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;

public interface ScmInvCountingCostCenterEntryBiz extends BaseBiz<ScmInvCountingCostCenterEntry2> {
	/**
	 * 根据tableId查询
	 * @param tableId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvCountingCostCenterEntry2> selectByTableId(long tableId, Param param) throws AppException;
	
	/**
	 * 根据tableId删除
	 * @param tableId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByTableId(long tableId, Param param) throws AppException;
	
	public void addByItemIdList(long taskId, String itemIdList, boolean genAccQty, boolean genTableForZero, Param param) throws AppException;
	
	public List<ScmInvCountingCostCenterEntry2> selectByTaskId(long taskId, Param param) throws AppException;
	public List<ScmInvCountingCostCenterEntry2> queryCountCostTaskDiff(long taskId,boolean showSum, Param param) throws AppException;
	
	public int selectByTaskIdAndFinOrg(long taskId,String finOrgUnitNo, Param param) throws AppException;

	public void refreshAccount(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;

	public int checkAccount(long taskId, Param param) throws AppException;

	public List<ScmInvCountingCostCenterEntry2> selectByIdleCauseId(long id, Param param) throws AppException;
}
