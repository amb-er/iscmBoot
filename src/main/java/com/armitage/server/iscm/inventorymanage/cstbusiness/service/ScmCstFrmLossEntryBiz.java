package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstFrmLossEntry2;

public interface ScmCstFrmLossEntryBiz extends BaseBiz<ScmCstFrmLossEntry2> {
	public List<ScmCstFrmLossEntry2> selectByBillId(long billId, Param param) throws AppException;
    public void deleteByBillId(long billId, Param param) throws AppException;
    public List<ScmCstFrmLossEntry2> selectOutEffectRow(long billId, Param param) throws AppException;
}
