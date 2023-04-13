package com.armitage.server.ifbc.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BaseDataDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.basedata.dao.ScmPriceUpdSetDAO;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet;
import com.armitage.server.ifbc.basedata.model.ScmPriceUpdSet2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPriceUpdSetDAO")
public class ScmPriceUpdSetDAOImpl extends BaseDataDAOImpl<ScmPriceUpdSet> implements ScmPriceUpdSetDAO {

    @Override
    @Autowired
    @Resource(name = "sqlSessionIscm")
    public void setSqlSession(SqlSessionTemplate sqlSession){
        super.setSqlSession(sqlSession);
    }


	@Override
	public List<ScmPriceUpdSet2> selectByCtrl(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByCtrl",map);
        } catch (Exception e) {
            throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmPriceUpdSetDAOImpl.error.selectByCtrl", e);
        }
	}

}