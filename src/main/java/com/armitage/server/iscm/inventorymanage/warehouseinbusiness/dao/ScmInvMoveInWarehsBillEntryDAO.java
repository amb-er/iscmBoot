package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvMoveInWarehsBillEntry2;

public interface ScmInvMoveInWarehsBillEntryDAO extends BasicDAO<ScmInvMoveInWarehsBillEntry> {

    void deleteByWrId(HashMap<String, Object> map) throws DAOException;

    List<ScmInvMoveInWarehsBillEntry2> selectByWrId(HashMap<String, Object> map) throws DAOException;

	List<ScmInvMoveInWarehsBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException;

	List<ScmInvMoveInWarehsBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;

}

