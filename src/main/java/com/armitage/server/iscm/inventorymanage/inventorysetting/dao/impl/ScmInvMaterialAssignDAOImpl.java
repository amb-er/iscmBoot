package com.armitage.server.iscm.inventorymanage.inventorysetting.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.inventorysetting.dao.ScmInvMaterialAssignDAO;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssign;
import com.armitage.server.iscm.inventorymanage.inventorysetting.model.ScmInvMaterialAssign2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvMaterialAssignDAO")
public class ScmInvMaterialAssignDAOImpl extends BasicDAOImpl<ScmInvMaterialAssign> implements ScmInvMaterialAssignDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvMaterialAssign2> selectMaterialAssign(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectMaterialAssign",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvMaterialAssignDAOImpl.error.selectMaterialAssign", e);
		}
	}

	@Override
	public List<ScmInvMaterialAssign2> selectByOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.inventorymanage.inventorysetting.dao.ScmInvMaterialAssignDAOImpl.error.selectByOrgUnitNo", e);
		}
	}

}

