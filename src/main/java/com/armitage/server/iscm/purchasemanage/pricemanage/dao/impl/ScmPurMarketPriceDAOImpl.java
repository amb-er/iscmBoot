
package com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.pricemanage.dao.ScmPurMarketPriceDAO;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice;
import com.armitage.server.iscm.purchasemanage.pricemanage.model.ScmPurMarketPrice2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurMarketPriceDAO")
public class ScmPurMarketPriceDAOImpl extends BasicDAOImpl<ScmPurMarketPrice> implements ScmPurMarketPriceDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}


	@Override
	public ScmPurMarketPrice selectMaxIdByDate(HashMap<String,Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectMaxIdByDate", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurMarketPriceDAOImpl.error.selectMaxIdByDate");
		}
	}

	@Override
	public List<ScmPurMarketPrice2> selectRecentPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectRecentPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurMarketPriceDAOImpl.error.selectRecentPrice");
		}
	}

	@Override
	public List<ScmPurMarketPrice2> selectItemsRecentPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectItemsRecentPrice", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.pricemanage.dao.impl.ScmPurMarketPriceDAOImpl.error.selectItemsRecentPrice");
		}
	}

}
