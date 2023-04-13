package com.armitage.server.iscm.inventorymanage.warehouseinbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorydata.model.ScmInvStock2;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseinbusiness.model.ScmInvPurInWarehsBillEntry2;

public interface ScmInvPurInWarehsBillEntryDAO extends BasicDAO<ScmInvPurInWarehsBillEntry> {
	public List<ScmInvPurInWarehsBillEntry2> selectByWrId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBillEntry2> checkStock(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBillEntry2> selectCancelOutEffectRow(HashMap<String, Object> map) throws DAOException;
	public int updateBuildAPStatus(ScmInvPurInWarehsBillEntry2 entity) throws DAOException;
	public ScmInvPurInWarehsBillEntry getInvPrice(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvPurInWarehsBillEntry2> getInvPriceList(HashMap<String, Object> map) throws DAOException;
}
