package com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.inventorymanage.cstbusiness.dao.ScmInvCountingListUserOrgDAO;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg;
import com.armitage.server.iscm.inventorymanage.cstbusiness.model.ScmInvCountingListUserOrg2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmInvCountingListUserOrgDAO")
public class ScmInvCountingListUserOrgDAOImpl extends BasicDAOImpl<ScmInvCountingListUserOrg> implements ScmInvCountingListUserOrgDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmInvCountingListUserOrg2> selectByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTaskId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingListUserOrgDAOImpl.error.selectByTaskId", e);
		}
	}

	@Override
	public void deleteByTaskId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByTaskId", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.inventorymanage.cstbusiness.dao.impl.ScmInvCountingListUserOrgDAOImpl.error.deleteByTaskId", e);
		}
	}

}
