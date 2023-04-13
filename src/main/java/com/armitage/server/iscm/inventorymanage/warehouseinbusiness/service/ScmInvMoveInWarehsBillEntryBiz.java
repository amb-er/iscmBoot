package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;

public interface ScmInvMoveInWarehsBillEntryBiz extends BaseBiz<ScmInvMoveInWarehsBillEntry2> {

    void deleteByWrId(long wrId, Param param) throws AppException;

    List<ScmInvMoveInWarehsBillEntry2> selectByWrId(long wrId, Param param) throws AppException;

	List<ScmInvMoveInWarehsBillEntry2> selectInvQty(long wrId, Param param) throws AppException;

	List<ScmInvMoveInWarehsBillEntry2> selectOutEffectRow(long wrId, Param param) throws AppException;

}

