package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;

public interface ScmInvSaleIssueBillEntryBiz extends BaseBiz<ScmInvSaleIssueBillEntry2> {
    
    public List<ScmInvSaleIssueBillEntry2> selectByOtId(long otId, Param param) throws AppException;
    
    public void deleteByOtId(long otId, Param param) throws AppException;

    public List<ScmInvSaleIssueBillEntry2> selectInvQty(long otId, Param param) throws AppException;

	public List<ScmInvSaleIssueBillEntry2> selectOutEffectRow(long otId, Param param) throws AppException;

	public List<ScmInvSaleIssueBillEntry2> selectSaleIssueByPurOut(long purOutDtlId,String costMethod, Param param) throws AppException;

	public List<ScmInvSaleIssueBillEntry2> selectCancelPostEffectRow(long otId, Param param) throws AppException;

}
