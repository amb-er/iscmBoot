package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouseUsr2;

public interface ScmInvWareHouseUsrBiz extends BaseBiz<ScmInvWareHouseUsr> {
	List<ScmInvWareHouseUsr2> selectByWareHouseId(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException;

	List<ScmInvWareHouseUsr2> selectByUsrCode(String usrCode, Param param) throws AppException;
}
