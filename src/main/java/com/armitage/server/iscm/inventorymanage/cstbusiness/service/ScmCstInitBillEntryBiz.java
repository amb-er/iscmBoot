package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBillEntry2;

public interface ScmCstInitBillEntryBiz extends BaseBiz<ScmCstInitBillEntry2> {
    /**
     * 
     * @param initId
     * @param param
     * @return
     * @throws AppException
     */
    public List<ScmCstInitBillEntry2> selectByInitId(long initId, Param param) throws AppException;
    
    /**
     * 
     * @param initId
     * @param param
     * @throws AppException
     */
    public void deleteByInitId(long initId, Param param) throws AppException;

	public List<ScmCstInitBillEntry2> selectInvQty(long initId, Param param) throws AppException;

	public List<ScmCstInitBillEntry2> selectCancelPostEffectRow(long initId, Param param) throws AppException;
    
}