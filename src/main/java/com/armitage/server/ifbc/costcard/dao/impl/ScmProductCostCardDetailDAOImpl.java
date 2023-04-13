
package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmProductCostCardDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail;
import com.armitage.server.ifbc.costcard.model.ScmProductCostCardDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmProductCostCardDetailDAO")
public class ScmProductCostCardDetailDAOImpl extends BasicDAOImpl<ScmProductCostCardDetail> implements ScmProductCostCardDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmProductCostCardDetail2> selectByCardId(long id) {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByCardId", id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailDAOImpl.error.selectByCardId",e);
		}
	}

	@Override
	public void deleteByCardId(HashMap map) {
		try {
			sqlSession.clearCache();
			sqlSession.delete(simpleName + ".deleteByCardId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmProductCostCardDetailDAOImpl.error.deleteByCardId",e);
		}
	}

}
