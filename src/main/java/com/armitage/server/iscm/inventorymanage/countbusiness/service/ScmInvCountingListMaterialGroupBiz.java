
package com.armitage.server.iscm.inventorymanage.countbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;

public interface ScmInvCountingListMaterialGroupBiz extends BaseBiz<ScmInvCountingListMaterialGroup2> {

	public void deleteByTaskId(long taskId, Param param) throws AppException;

	public List<ScmInvCountingListMaterialGroup2> selectByTaskId(long taskId, Param param) throws AppException;

}
