package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenter2;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterEntry2;

public interface ScmInvCountingCostCenterBiz extends BaseBiz<ScmInvCountingCostCenter2> {
	public void deleteNotExist(long taskId, Param param) throws AppException;
	public List<ScmInvCountingCostCenter2> findDifference(long taskId, Param param) throws AppException;
	public List<ScmInvCountingCostCenterEntry2> queryCountCostTaskDiff(long taskId,boolean showSum, Param param) throws AppException;
	public List<ScmInvCountingCostCenter2> selectByTaskNo(String taskNo,String controlUnitNo,Param param) throws AppException;
	public ScmInvCountingCostCenter2 selectByTaskNoAndUseOrgUnitNo(String taskNo, String deptNo, String controlUnitNo, Param param) throws AppException;
}
