package com.armitage.server.iscm.inventorymanage.countbusiness.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListWarehouse2;

public interface ScmInvCountingListWarehouseBiz extends BaseBiz<ScmInvCountingListWarehouse2> {
	public List<ScmInvCountingListWarehouse2> selectByTaskId(long taskId, Param param) throws AppException;
	public void deleteByTaskId(long taskId, Param param) throws AppException;
}

