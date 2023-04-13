package com.armitage.server.ifbc.rptdata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.rptdata.dao.ScmFbmSellCookDetailDAO;
import com.armitage.server.ifbc.rptdata.model.ScmFbmSellCookDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmFbmSellCookDetailDAO")
public class ScmFbmSellCookDetailDAOImpl extends BasicDAOImpl<ScmFbmSellCookDetail> implements ScmFbmSellCookDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public int batchAdd(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".batchAdd", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.ifbc.rptdata.dao.impl.ScmFbmSellDetailDAOImpl.error.batchAdd");
		}
	}

	@Override
	public void delRptData(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.delete(simpleName + ".delRptData", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException(
					"com.armitage.server.ifbc.rptdata.dao.impl.ScmFbmSellCookDetailDAOImpl.error.delRptData");
		}
	}

}
