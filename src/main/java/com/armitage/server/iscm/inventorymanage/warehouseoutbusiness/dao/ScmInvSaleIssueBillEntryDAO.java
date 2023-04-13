package com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry;
import com.armitage.server.iscm.inventorymanage.warehouseoutbusiness.model.ScmInvSaleIssueBillEntry2;

public interface ScmInvSaleIssueBillEntryDAO extends BasicDAO<ScmInvSaleIssueBillEntry> {
    public List<ScmInvSaleIssueBillEntry2> selectByOtId(HashMap<String, Object> map) throws DAOException;
    public List<ScmInvSaleIssueBillEntry2> selectInvQty(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvSaleIssueBillEntry2> selectOutEffectRow(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvSaleIssueBillEntry2> selectSaleIssueByPurOut(HashMap<String, Object> map) throws DAOException;
	public List<ScmInvSaleIssueBillEntry2> selectCancelPostEffectRow(HashMap<String, Object> map) throws DAOException;
}
