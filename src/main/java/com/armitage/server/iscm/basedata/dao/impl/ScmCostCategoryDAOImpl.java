
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmCostCategoryDAO;
import com.armitage.server.iscm.basedata.model.ScmCostCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostCategoryDAO")
public class ScmCostCategoryDAOImpl extends BasicDAOImpl<ScmCostCategory> implements ScmCostCategoryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmCostCategory selectByCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCode",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmCostCategoryDAOImpl.error.selectByCode");
		}
	}

}
