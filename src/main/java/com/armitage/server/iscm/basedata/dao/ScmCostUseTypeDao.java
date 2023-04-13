/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:16:35
 *
 */
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;

public interface ScmCostUseTypeDao extends BasicDAO<ScmCostUseType>{
	public List<ScmCostUseType> selectAll(HashMap<String, Object> map)throws DAOException;

	public List<ScmCostUseType> queryByNameOrCode(HashMap<String, Object> map)throws DAOException;

}

