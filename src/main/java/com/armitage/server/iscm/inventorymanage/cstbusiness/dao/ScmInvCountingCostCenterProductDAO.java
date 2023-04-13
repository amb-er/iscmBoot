
package com.armitage.server.iscm.inventorymanage.cstbusiness.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingCostCenterProduct2;

public interface ScmInvCountingCostCenterProductDAO extends BasicDAO<ScmInvCountingCostCenterProduct> {

	List<ScmInvCountingCostCenterProduct2> selectByTableId(HashMap<String, Object> map) throws DAOException;

	void deleteByTableId(HashMap<String, Object> map) throws DAOException;

}
