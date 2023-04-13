
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmIdleItems;
import com.armitage.server.iscm.basedata.model.ScmIdleItems2;

public interface ScmIdleItemsDAO extends BasicDAO<ScmIdleItems> {

	public List<ScmIdleItems> selectByCostCenterOrg(HashMap<String, Object> map) throws DAOException;

	public List<ScmIdleItems2> selectIdleItemsByItems(HashMap<String, Object> map) throws DAOException;

	public List<ScmIdleItems2> selectIdleDrillData(HashMap<String, Object> map) throws DAOException;

	public List<ScmIdleItems2> selectByIdleCauseId(HashMap<String, Object> map) throws DAOException;

	public int deleteZeroQty(HashMap<String, Object> map) throws DAOException;

}
