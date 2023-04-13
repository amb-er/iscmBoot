
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterial;
import com.armitage.server.iscm.basedata.model.ScmMaterial2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialDAO")
public class ScmMaterialDAOImpl extends BasicDAOImpl<ScmMaterial> implements ScmMaterialDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmMaterial2 selectMaxId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.selectMaxId");
		}
	}
	
	@Override
	public ScmMaterial2 selectByItemId(long itemId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemId", itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.selectByItemId");
		}
	}

	@Override
	public List<ScmMaterial2> selectByOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.selectByOrgUnitNo", e);
		}
	}

	@Override
	public List<ScmMaterial2> findCountingMaterial(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findCountingMaterial",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findCountingMaterial", e);
		}
	}

	@Override
	public ScmMaterial2 selectByItemNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.selectByItemNo", e);
		}
	}

	@Override
	public List<ScmMaterial2> selectByCostOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCostOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.selectByCostOrgUnitNo", e);
		}
	}

	@Override
	public int checkUse(long itemId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkUse", itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.checkUse");
		}
	}
	@Override
	public int checkAllUse(long itemId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".checkAllUse", itemId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.checkAllUse");
		}
	}

	@Override
	public ScmMaterial2 findByInvItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".findByInvItemId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByInvItemId");
		}
	}

	@Override
	public ScmMaterial2 findByPurItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".findByPurItemId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByPurItemId");
		}
	}

	@Override
	public ScmMaterial2 selectByStock(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByStock",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.selectByStock");
		}
	}

	@Override
	public ScmMaterial2 findSameNameMaterial(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".findSameNameMaterial",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findSameNameMaterial");
		}
	}

	@Override
	public List<ScmMaterial2> findByFinAllList(HashMap<String, Object> map)  throws DAOException{
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findByFinAllList",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByFinAllList",e);
		}
	}

	@Override
	public ScmMaterial2 findByFinItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".findByFinItemId",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByFinItemId");
		}
	}

	@Override
	public List<ScmMaterial2> findByPurItemIds(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findByPurItemIds",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByPurItemIds");
		}
	}
	
	@Override
	public List<ScmMaterial2> findByInvItemIds(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".findByInvItemIds",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.findByInvItemIds");
		}
	}

	@Override
	public List<ScmMaterial2> checkUnitRelation(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkUnitRelation",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmMaterialDAOImpl.error.checkUnitRelation", e);
		}
	}
}
