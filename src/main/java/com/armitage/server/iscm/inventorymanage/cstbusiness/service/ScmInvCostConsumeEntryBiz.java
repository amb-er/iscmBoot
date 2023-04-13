package com.armitage.server.iscm.inventorymanage.cstbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCostConsumeEntry2;

public interface ScmInvCostConsumeEntryBiz extends BaseBiz<ScmInvCostConsumeEntry2> {
    /**
     * 
     * @param dcId
     * @param param
     * @return
     * @throws AppException
     */
    public List<ScmInvCostConsumeEntry2> selectByDcId(long dcId, Param param) throws AppException;
    
    /**
     * 
     * @param dcId
     * @param param
     * @throws AppException
     */
    public void deleteByDcId(long dcId, Param param) throws AppException;
    
    public List<ScmInvCostConsumeEntry2> checkStockByReturnWareHouse(long dcId, Param param) throws AppException;

	public List<ScmInvCostConsumeEntry2> selectOutEffectRow(long dcId, Param param) throws AppException;
   
}
