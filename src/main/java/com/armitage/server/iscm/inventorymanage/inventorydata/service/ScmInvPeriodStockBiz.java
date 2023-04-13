package com.armitage.server.iscm.inventorymanage.inventorydata.service;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvPeriodStock;

public interface ScmInvPeriodStockBiz extends BaseBiz<ScmInvPeriodStock> {

	public void turnoffStock(String orgUnitNo, long periodId,boolean costCenter, Param param) throws AppException;

	public void turnbackStock(String orgUnitNo, long periodId,boolean costCenter, Param param) throws AppException;

}
