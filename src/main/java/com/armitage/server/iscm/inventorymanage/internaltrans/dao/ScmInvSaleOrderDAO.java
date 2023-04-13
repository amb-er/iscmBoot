package com.armitage.server.iscm.inventorymanage.internaltrans.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder;
import com.armitage.server.iscm.inventorymanage.internaltrans.model.ScmInvSaleOrder2;

public interface ScmInvSaleOrderDAO extends BasicDAO<ScmInvSaleOrder> {
    public ScmInvSaleOrder selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleOrder2> selectByPoId(HashMap<String, Object> map) throws DAOException;

	public List<ScmInvSaleOrder2> selectBySaleIssue(HashMap<String, Object> map) throws DAOException;
}
