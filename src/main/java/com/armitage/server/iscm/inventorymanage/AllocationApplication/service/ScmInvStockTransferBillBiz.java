package com.armitage.server.iscm.inventorymanage.AllocationApplication.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBill2;
import com.armitage.server.iscm.inventorymanage.AllocationApplication.model.ScmInvStockTransferBillEntry2;

public interface ScmInvStockTransferBillBiz extends BaseBiz<ScmInvStockTransferBill2> {
	public ScmInvStockTransferBill2 updateStatus(ScmInvStockTransferBill2 scmInvStockTransferBill, List<ScmInvStockTransferBillEntry2> scmInvStockTransferBillEntryList, Param param) throws AppException;

    public void generateMoveIssue(ScmInvStockTransferBill2 scmInvStockTransferBill, Param param) throws AppException;

	public ScmInvStockTransferBill2 updatePrtCount(ScmInvStockTransferBill2 scmInvStockTransferBill, Param param) throws AppException;
}
