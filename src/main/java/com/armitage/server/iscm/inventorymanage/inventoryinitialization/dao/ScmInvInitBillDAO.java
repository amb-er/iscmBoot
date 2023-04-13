package com.armitage.server.iscm.inventorymanage.inventoryinitialization.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill;
import com.armitage.server.iscm.inventorymanage.inventoryinitialization.model.ScmInvInitBill2;

public interface ScmInvInitBillDAO extends BasicDAO<ScmInvInitBill>{
	public ScmInvInitBill2 selectMaxIdByDate(HashMap<String, Object> map) throws DAOException;
	
	/**
	 * 查询指定库存组织和仓库的未过账的期初结存数目
	 * @param map
	 * @return
	 * @throws DAOException
	 */
	public List<ScmInvInitBill2> selectNotPost(HashMap<String, Object> map) throws DAOException;

	public int updatePostedStatus(HashMap<String, Object> map) throws DAOException;
}
