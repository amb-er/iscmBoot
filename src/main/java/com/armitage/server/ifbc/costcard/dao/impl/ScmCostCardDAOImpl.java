package com.armitage.server.ifbc.costcard.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmCostCardDAO;
import com.armitage.server.ifbc.costcard.model.ScmCostCard;
import com.armitage.server.ifbc.costcard.model.ScmCostCard2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmCostCardDAO")
public class ScmCostCardDAOImpl extends BasicDAOImpl<ScmCostCard> implements ScmCostCardDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }

	@Override
	public List<ScmCostCard2> selectReplaceScmCostCard(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectReplaceScmCostCard", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCostCardDAOImpl.error.selectReplaceScmCostCard");
        }
	}

	@Override
	public List<ScmCostCard2> findRepeat(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".findRepeat", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmCostCardDAOImpl.error.findRepeat");
        }
	}

}