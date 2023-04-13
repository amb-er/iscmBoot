package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg2;

public interface ScmInvCountingListUserOrgBiz extends BaseBiz<ScmInvCountingListUserOrg2> {
	public List<ScmInvCountingListUserOrg2> selectByTaskId(long taskId, Param param) throws AppException;
	public void deleteByTaskId(long taskId, Param param) throws AppException;
}
