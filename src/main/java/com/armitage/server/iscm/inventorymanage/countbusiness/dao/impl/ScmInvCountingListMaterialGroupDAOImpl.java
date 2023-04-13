
package com.armitage.server.iscm.inventorymanage.countbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.countbusiness.dao.ScmInvCountingListMaterialGroupDAO;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup;
import com.armitage.server.iscm.inventorymanage.countbusiness.model.ScmInvCountingListMaterialGroup2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingListMaterialGroupDAO")
public class ScmInvCountingListMaterialGroupDAOImpl extends BasicDAOImpl<ScmInvCountingListMaterialGroup> implements ScmInvCountingListMaterialGroupDAO {


	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.delete(simpleName + ".deleteByTaskId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.countbusiness.dao.impl.ScmInvCountingListMaterialGroupDAOImpl.error.deleteByTaskId", e);
		}
	}

	@Override
	public List<ScmInvCountingListMaterialGroup2> selectByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTaskId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.countbusiness.dao.impl.ScmInvCountingListMaterialGroupDAOImpl.error.selectByTaskId", e);
		}
	}

}
