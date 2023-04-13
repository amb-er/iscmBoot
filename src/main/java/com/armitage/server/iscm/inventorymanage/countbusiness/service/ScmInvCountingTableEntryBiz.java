package com.armitage.server.iscm.inventorymanage.countbusiness.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingTask2;

public interface ScmInvCountingTableEntryBiz extends BaseBiz<ScmInvCountingTableEntry2> {
	/**
	 * 根据tableId查询
	 * @param tableId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvCountingTableEntry2> selectByTableId(long tableId, Param param) throws AppException;
	
	/**
	 * 根据tableId删除
	 * @param tableId
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public void deleteByTableId(long tableId, Param param) throws AppException;
	
	public void addByItemIdList(long taskId, String itemIdList, boolean genAccQty, boolean genTableForZero, Param param) throws AppException;
	
	public List<ScmInvCountingTableEntry2> selectByTaskId(long taskId, Param param) throws AppException;
	
	public int checkAccount(long taskId, Param param) throws AppException;

	public List<ScmInvCountingTableEntry2> queryCountInvTaskDiff(long taskId,boolean showSum, Param param) throws AppException;
	
	public List<ScmInvCountingTableEntry2> selectForOtherInWh(long taskId, Param param) throws AppException;
	
	public List<ScmInvCountingTableEntry2> selectForOtherOutWh(long taskId, Param param) throws AppException;
	
	public void updateLot(long taskId, Param param) throws AppException;
	
	public List<ScmInvCountingTableEntry2> findAccount(long taskId, String orgUnitNo, long wareHouseId, Param param) throws AppException;

	public void refreshAccount(ScmInvCountingTask2 scmInvCountingTask, Param param) throws AppException;
}

