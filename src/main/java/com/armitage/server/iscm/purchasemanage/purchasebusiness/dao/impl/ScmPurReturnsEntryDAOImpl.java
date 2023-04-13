
package com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.ScmPurReturnsEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry;
import com.armitage.server.iscm.purchasemanage.purchasebusiness.model.ScmPurReturnsEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurReturnsEntryDAO")
public class ScmPurReturnsEntryDAOImpl extends BasicDAOImpl<ScmPurReturnsEntry> implements ScmPurReturnsEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurReturnsEntry2> selectByRtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByRtId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurReturnsEntryDAOImpl.error.selectByRtId", e);
		}
	}

	@Override
	public void updateRowStatusByRtId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.update(simpleName + ".updateRowStatusByRtId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasebusiness.dao.ScmPurReturnsEntryDAOImpl.error.updateRowStatusByRtId", e);
		}
	}

}
