package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;

public interface ScmInvAccreditWhBiz extends BaseBiz<ScmInvAccreditWh2> {
	public List<ScmInvAccreditWh2> selectByWareHouseIdList(String wareHouseIdList, Param param) throws AppException;
	
	/**
	 * 结束初始化
	 * @param scmInvAccreditWh
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Object initEnd(ScmInvAccreditWh2 scmInvAccreditWh, Param param) throws AppException;
	
	/**
	 * 反初始化
	 * @param scmInvAccreditWh
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public Object reverseInit(ScmInvAccreditWh2 scmInvAccreditWh, Param param) throws AppException;

	public ScmInvAccreditWh selectByWareHouseId(long wareHouseId,String invOrgUnitNo,Param param) throws AppException;
}
