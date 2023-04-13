package com.armitage.server.iscm.inventorymanage.countbusiness.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTable2;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingTableEntry2;

public interface ScmInvCountingTableBiz extends BaseBiz<ScmInvCountingTable2> {
	public void deleteNotExist(long taskId, Param param) throws AppException;
	public List<ScmInvCountingTable2> findDifference(long taskId, Param param) throws AppException;
	public List<ScmInvCountingTableEntry2> queryCountInvTaskDiff(long taskId,boolean showSum, Param param) throws AppException;
	public List<ScmInvCountingTable2> selectByTaskNo(String taskNo,String controlUnitNo,Param param) throws AppException;
	public ScmInvCountingTable2 selectByTaskNoAndWhNo(String taskNo,String whNo,String controlUnitNo,Param param) throws AppException;
}
