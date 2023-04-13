package com.armitage.server.iscm.inventorymanage.inventorysetting.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvAccreditWh2;

public interface ScmInvAccreditWhDAO extends BasicDAO<ScmInvAccreditWh> {
	public List<ScmInvAccreditWh2> selectByWareHouseIdList(HashMap<String, Object> map) throws DAOException;

	/**
	 * 结束初始化
	 * @param map
	 * @throws DAOException
	 */
    public void updateEndInit(HashMap<String, Object> map) throws DAOException;
    
    /**
     * 反初始化, 检查是否存在业务单据
     * @param map
     * @throws DAOException
     */
    public int selectCount(HashMap<String, Object> map) throws DAOException;
    
    /**
     * 反初始化, 更新状态
     * @param map
     * @return
     * @throws DAOException
     */
    public void updateReverseInit(HashMap<String, Object> map) throws DAOException;

	public ScmInvAccreditWh selectByWareHouseId(HashMap<String, Object> map) throws DAOException;
}

