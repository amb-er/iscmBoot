package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBillEntry2;

public interface ScmInvMaterialRequestBillEntryDAO extends BasicDAO<ScmInvMaterialRequestBillEntry> {
	public List<ScmInvMaterialRequestBillEntry2> selectByReqId(HashMap<String, Object> map) throws DAOException;
	public void deleteByReqId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByReqId(HashMap<String, Object> map) throws DAOException;
	public void updateRowStatusByLineId(HashMap<String, Object> map) throws DAOException;
}
