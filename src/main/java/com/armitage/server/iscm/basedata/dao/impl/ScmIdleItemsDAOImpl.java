
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmIdleItemsDAO;
import com.armitage.server.iscm.basedata.model.ScmIdleItems;
import com.armitage.server.iscm.basedata.model.ScmIdleItems2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmIdleItemsDAO")
public class ScmIdleItemsDAOImpl extends BasicDAOImpl<ScmIdleItems> implements ScmIdleItemsDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmIdleItems> selectByCostCenterOrg(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCostCenterOrg", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIdleItemsDAOImpl.error.selectByCostCenterOrg");
		}
	}

	@Override
	public List<ScmIdleItems2> selectIdleItemsByItems(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectIdleItemsByItems", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIdleItemsDAOImpl.error.selectIdleItemsByItems");
		}
	}

	@Override
	public List<ScmIdleItems2> selectIdleDrillData(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectIdleDrillData", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIdleItemsDAOImpl.error.selectIdleDrillData");
		}
	}

	@Override
	public List<ScmIdleItems2> selectByIdleCauseId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByIdleCauseId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIdleItemsDAOImpl.error.selectByIdleCauseId");
		}
	}

	@Override
	public int deleteZeroQty(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.delete(simpleName + ".deleteZeroQty", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIdleItemsDAOImpl.error.deleteZeroQty");
		}
	}

}
