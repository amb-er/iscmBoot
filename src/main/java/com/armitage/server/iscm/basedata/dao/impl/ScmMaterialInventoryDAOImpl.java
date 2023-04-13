
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialInventoryDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory;
import com.armitage.server.iscm.basedata.model.ScmMaterialInventory2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialInventoryDAO")
public class ScmMaterialInventoryDAOImpl extends BasicDAOImpl<ScmMaterialInventory> implements ScmMaterialInventoryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmMaterialInventory2 selectByItemIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemIdAndOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmMaterialInventoryDAOImpl.error.selectByItemIdAndOrgUnitNo", e);
		}
	}

	@Override
	public ScmMaterialInventory2 selectByItemId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmMaterialInventoryDAOImpl.error.selectByItemId", e);
		}
	}
	
}
