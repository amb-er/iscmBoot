package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBill2;

public interface ScmInvSaleIssueBillDAO extends BasicDAO<ScmInvSaleIssueBill> {

    public ScmInvSaleIssueBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleIssueBill2> SelectBySaleOrder(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleIssueBill2> checkUnPostBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleIssueBill2> checkPostedBill(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleIssueBill2> selectByPurInwareHouse(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleIssueBill2> checkWareHouseFree(long otId) throws DAOException;
    public int checkMaterialFree(HashMap<String, Object> map) throws DAOException;
    
    public List<Map<String,Object>> countUnPostBill(HashMap<String, Object> map);

	public List<ScmInvSaleIssueBill2> checkOrgFree(long otId) throws DAOException;
}
