/**
 * 广州万迅电脑软件有限公司(c)
 * @author Promise
 * 2020年12月16日 下午5:17:40
 *
 */
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmCostUseTypeDao;
import com.armitage.server.iscm.basedata.model.ScmCostUseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostUseTypeDao")
public class ScmCostUseTypeDaoImpl extends BasicDAOImpl<ScmCostUseType> implements ScmCostUseTypeDao{

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmCostUseType> selectAll(HashMap<String, Object> map) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectAll",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmCostUseTypeDaoImpl.error.selectAll",e);
		}
	}

	@Override
	public List<ScmCostUseType> queryByNameOrCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".queryByNameOrCode",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmCostUseTypeDaoImpl.error.queryByNameOrCode",e);
		}
	}

}

