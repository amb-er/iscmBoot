package com.armitage.server.ifbc.costcard.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.ifbc.costcard.dao.ScmItemCostPriceLogDetailDAO;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail;
import com.armitage.server.ifbc.costcard.model.ScmItemCostPriceLogDetail2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmItemCostPriceLogDetailDAO")
public class ScmItemCostPriceLogDetailDAOImpl extends BasicDAOImpl<ScmItemCostPriceLogDetail> implements ScmItemCostPriceLogDetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void batchAddItemPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".batchAddItemPrice",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.basedata.dao.impl.ScmItemCostPriceLogDetailDAOImpl.error.batchAddItemPrice", e);
		}
	}

	@Override
	public List<ScmItemCostPriceLogDetail2> getItemPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getItemPrice",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmItemCostPriceLogDetailDAOImpl.error.getItemPrice", e);
		}
	}

	@Override
	public void batchAdd(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".batchAdd",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmItemCostPriceLogDetailDAOImpl.error.batchAdd", e);
		}
	}

	@Override
	public List<ScmItemCostPriceLogDetail2> getItemPriceByInvStock(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".getItemPriceByInvStock",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.ifbc.costcard.dao.impl.ScmItemCostPriceLogDetailDAOImpl.error.getItemPriceByInvStock", e);
		}
	}

}

