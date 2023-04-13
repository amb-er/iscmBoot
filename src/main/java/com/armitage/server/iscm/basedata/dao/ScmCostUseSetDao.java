/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:59:58
 *
 */
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;



public interface ScmCostUseSetDao extends BasicDAO<ScmCostUseSet>{

	public ScmCostUseSet2 selectByRowMD(String md5);

	public List<ScmCostUseSet2> getScmCostUseSetByItemId(HashMap<String, Object> map) throws DAOException;

}

