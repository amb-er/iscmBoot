package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialDrillResult;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvMaterialRequestBill2;

public interface ScmInvMaterialRequestBillDAO extends BasicDAO<ScmInvMaterialRequestBill> {
	public ScmInvMaterialRequestBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMaterialDrillResult> selectDrillBills(HashMap<String, Object> map) throws DAOException;
	
}

