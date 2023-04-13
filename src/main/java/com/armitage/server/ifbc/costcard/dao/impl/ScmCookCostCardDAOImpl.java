package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmCookCostCardDAO;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard;
import com.armitage.server.ifbc.costcard.model.ScmCookCostCard2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCookCostCardDAO")
public class ScmCookCostCardDAOImpl extends BasicDAOImpl<ScmCookCostCard> implements ScmCookCostCardDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmCookCostCard2> selectReplaceScmCookCostCard(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectReplaceScmCookCostCard", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDAOImpl.error.selectReplaceScmCookCostCard");
        }
	}

	@Override
	public List<ScmCookCostCard2> selectByCookIds(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByCookIds", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDAOImpl.error.selectByCookIds");
        }
	}

	@Override
	public List<ScmCookCostCard2> findRepeat(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findRepeat", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCookCostCardDAOImpl.error.findRepeat");
        }
	}

}