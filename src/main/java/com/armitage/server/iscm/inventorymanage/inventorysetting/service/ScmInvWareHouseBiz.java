package com.armitage.server.iscm.inventorymanage.inventorysetting.service;

import java.util.List;

import com.armitage.server.common.base.biz.BaseBiz;
import com.armitage.server.common.base.model.CommonBean;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvWareHouse;

public interface ScmInvWareHouseBiz extends BaseBiz<ScmInvWareHouse> {

	/**
	 * 启用仓库
	 * @param scmInvWareHouse
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public ScmInvWareHouse enableWareHouse(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException;
	
	/**
	 * 查询仓库设置
	 * @param scmInvWareHouseList
	 * @param param
	 * @return
	 * @throws AppException
	 */
	public List<ScmInvAccreditWh2> selectWareHouseSet(List<ScmInvWareHouse> scmInvWareHouseList, Param param) throws AppException;
	
	public List<ScmInvWareHouse> selectByOrgUnitNo(String orgUnitNo, String fromWhNo, String toWhNo, Param param) throws AppException;
	
	public ScmInvWareHouse selectByWhNo(String orgUnitNo, String whNo, Param param) throws AppException;

	public List<ScmInvWareHouse> queryWareHouseList(ScmInvWareHouse wareHouse,int pageNum,Param param) throws AppException;
	
	public List<ScmInvWareHouse> selectByWhName(ScmInvWareHouse scmInvWareHouse,Param param) throws AppException;

	public CommonBean selectWareHouseUsr(ScmInvWareHouse scmInvWareHouse, Param param) throws AppException;

	public void saveWareHouseUsr(CommonBean bean, Param param) throws AppException;

}
