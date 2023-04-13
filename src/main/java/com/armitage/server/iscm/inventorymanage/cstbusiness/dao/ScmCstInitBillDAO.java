package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCstInitBill2;

public interface ScmCstInitBillDAO extends BasicDAO<ScmCstInitBill> {
    public ScmCstInitBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;

	public List<Map<String, Object>> countUnPostBill(HashMap<String, Object> map) throws DAOException;
}

