package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmCostCardDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostCardDetailDAO")
public class ScmCostCardDetailDAOImpl extends BasicDAOImpl<ScmCostCardDetail> implements ScmCostCardDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmCostCardDetail2> selectByCardId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCardId",map);
		} catch (Exception e) {
			throw new DAOException("ifbc.costcard.dao.impl.ScmCostCardDetailDAOImpl.error.selectByCardId", e);
		}
	}

	@Override
	public int replaceItemByCardIds(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".replaceItemByCardIds",map);
		} catch (Exception e) {
			throw new DAOException("ifbc.costcard.dao.impl.ScmCostCardDetailDAOImpl.error.replaceItemByCardIds", e);
		}
	}
	
	@Override
	public List<ScmCostCardDetail2> checkItemCostPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".checkItemCostPrice",map);
		} catch (Exception e) {
			throw new DAOException("ifbc.costcard.dao.impl.ScmCostCardDetailDAOImpl.error.checkItemCostPrice", e);
		}
	}

}
