package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialReqBillEntry2;

public interface ScmInvMaterialReqBillEntryDAO extends BasicDAO<ScmInvMaterialReqBillEntry> {
	public List<ScmInvMaterialReqBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> checkStockByOutWareHouse(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> checkStockByReturnWareHouse(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> selectReturnEffectRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectDeptRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> selectCancelOutEffectWareHsRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvMaterialReqBillEntry2> deleteByOtId(HashMap<String, Object> map) throws DAOException;
}
