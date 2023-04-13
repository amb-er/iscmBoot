package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.service;


import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBill2;

public interface ScmDeptPurInWarehsBillBiz extends BaseBiz<ScmInvPurInWarehsBill2> {

	public ScmInvPurInWarehsBill2 updatePrtCount(ScmInvPurInWarehsBill2 scmInvPurInWarehsBill, Param param)throws AppException;

}

