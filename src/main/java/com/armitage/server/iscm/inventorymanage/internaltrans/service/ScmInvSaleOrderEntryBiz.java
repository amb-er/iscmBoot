package com.armitage.server.iscm.inventorymanage.internaltrans.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrderEntry2;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;

public interface ScmInvSaleOrderEntryBiz extends BaseBiz<ScmInvSaleOrderEntry2> {

    public List<ScmInvSaleOrderEntry2> selectByOtId(long otId, Param param) throws AppException;
    
    public void deleteByOtId(long otId, Param param) throws AppException;

	public void writeBackBySaleIssue(ScmInvSaleIssueBillEntry2 oldEntity,ScmInvSaleIssueBillEntry2 newEntity, Param param) throws AppException;

    /**
     * 未出库的记录
     * @param otId
     * @param param
     * @return
     * @throws AppException
     */
    public List<ScmInvSaleOrderEntry2> selectNotOut(long otId, Param param) throws AppException;
}
