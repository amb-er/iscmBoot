package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmCostBill;
public interface ScmCostBillDao extends BasicDAO<ScmCostBill>{

	List<ScmCostBill> selectPostAndDateByItem(HashMap<String, Object> map) throws DAOException;

}
