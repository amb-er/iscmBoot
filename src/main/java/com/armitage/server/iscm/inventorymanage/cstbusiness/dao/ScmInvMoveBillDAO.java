package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvMoveBill2;

public interface ScmInvMoveBillDAO extends BasicDAO<ScmInvMoveBill> {
    public ScmInvMoveBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMoveBill2> selectInvQty(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvMoveBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

    public List<ScmInvMoveBill2> checkWareHouseFree(HashMap<String, Object> map) throws DAOException;

    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
	
	public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);
}
