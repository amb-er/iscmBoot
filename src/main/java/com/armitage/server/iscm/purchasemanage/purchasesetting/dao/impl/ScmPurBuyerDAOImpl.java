
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurBuyerDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyer2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurBuyerDAO")
public class ScmPurBuyerDAOImpl extends BasicDAOImpl<ScmPurBuyer> implements ScmPurBuyerDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurBuyer> selectByParentId(long parentId) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByParentId", parentId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmPurBuyerDAOImpl.error.selectByParentId");
		}
	}

	@Override
	public ScmPurBuyer2 selectByCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCode", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmPurBuyerDAOImpl.error.selectByCode");
		}
	}

}
