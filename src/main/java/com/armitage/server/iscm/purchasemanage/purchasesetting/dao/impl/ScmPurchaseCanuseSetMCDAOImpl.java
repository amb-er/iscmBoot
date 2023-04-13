
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetMCDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetMC2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurchaseCanuseSetMCDAO")
public class ScmPurchaseCanuseSetMCDAOImpl extends BasicDAOImpl<ScmPurchaseCanuseSetMC> implements ScmPurchaseCanuseSetMCDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurchaseCanuseSetMC2> selectByPcsId(HashMap<String,Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPcsId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetMCDAOImpl.error.selectByPcsId", e);
		}
	}

}
