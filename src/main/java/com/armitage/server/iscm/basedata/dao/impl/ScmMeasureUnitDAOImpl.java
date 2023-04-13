
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMeasureUnitDAO;
import com.armitage.server.iscm.basedata.model.ScmMeasureUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMeasureUnitDAO")
public class ScmMeasureUnitDAOImpl extends BasicDAOImpl<ScmMeasureUnit> implements ScmMeasureUnitDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmMeasureUnit selectByUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMeasureUnitDAOImpl.error.selectByUnitNo", e);
		}
	}

}
