
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialUnitRelationDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation;
import com.armitage.server.iscm.basedata.model.ScmMaterialUnitRelation2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialUnitRelationDAO")
public class ScmMaterialUnitRelationDAOImpl extends BasicDAOImpl<ScmMaterialUnitRelation> implements ScmMaterialUnitRelationDAO {


	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmMaterialUnitRelation2> selectByItemOrUnit(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByItemOrUnit", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialUnitRelationDAOImpl.error.selectByItemOrUnit");
		}
	}
	
	@Override
	public ScmMaterialUnitRelation2 selectByItemAndUnit(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemAndUnit", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialUnitRelationDAOImpl.error.selectByItemAndUnit");
		}
	}

	@Override
	public ScmMaterialUnitRelation2 selectBaseUnitByItem(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectBaseUnitByItem", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialUnitRelationDAOImpl.error.selectBaseUnitByItem");
		}
	}

}
