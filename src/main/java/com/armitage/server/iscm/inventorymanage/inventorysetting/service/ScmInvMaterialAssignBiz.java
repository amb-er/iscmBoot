package com.armitage.server.iscm.inventorymanage.inventorysetting.service;


import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssign2;

public interface ScmInvMaterialAssignBiz extends BaseBiz<ScmInvMaterialAssign2> {
	public List<ScmInvMaterialAssign2> selectMaterialAssign(long wareHouseId, Param param) throws AppException;
	public boolean checkItemAssign(long wareHouseId, long itemId, String orgUnitNo, Param param) throws AppException;
}

