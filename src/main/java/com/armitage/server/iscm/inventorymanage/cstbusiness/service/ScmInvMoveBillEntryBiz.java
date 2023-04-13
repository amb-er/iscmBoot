package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBillEntry2;

public interface ScmInvMoveBillEntryBiz extends BaseBiz<ScmInvMoveBillEntry2> {
    public List<ScmInvMoveBillEntry2> selectByWtId(long wtId, Param param) throws AppException;
    
    public void deleteByWtId(long wtId, Param param) throws AppException;

	public List<ScmInvMoveBillEntry2> selectOutEffectRow(long wtId, Param param) throws AppException;

	public List<ScmInvMoveBillEntry2> selectCancelEffectRow(long wtId, Param param) throws AppException;

}
