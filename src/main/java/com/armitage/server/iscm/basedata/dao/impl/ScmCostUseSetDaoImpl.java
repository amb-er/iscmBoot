/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午6:01:00
 *
 */
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmCostUseSetDao;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet;
import com.armitage.server.iscm.basedata.model.ScmCostUseSet2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostUseSetDao")
public class ScmCostUseSetDaoImpl extends BasicDAOImpl<ScmCostUseSet> implements ScmCostUseSetDao{

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmCostUseSet2 selectByRowMD(String md) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName+".selectByRowMD",md);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmCostUseSetDaoImpl.error.selectByRowMD",e);
		}
	}

	@Override
	public List<ScmCostUseSet2> getScmCostUseSetByItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".getScmCostUseSetByItemId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmCostUseSetDaoImpl.error.getScmCostUseSetByItemId",e);
		}
	}

}

