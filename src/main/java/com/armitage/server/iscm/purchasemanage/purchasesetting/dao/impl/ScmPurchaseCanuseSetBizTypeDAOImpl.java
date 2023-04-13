
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetBizTypeDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetBizType;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurchaseCanuseSetBizType2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurchaseCanuseSetBizTypeDAO")
public class ScmPurchaseCanuseSetBizTypeDAOImpl extends BasicDAOImpl<ScmPurchaseCanuseSetBizType> implements ScmPurchaseCanuseSetBizTypeDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurchaseCanuseSetBizType2> selectByPcsId(HashMap<String,Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByPcsId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.purchasemanage.purchasesetting.dao.ScmPurchaseCanuseSetBizTypeDAOImpl.error.selectByPcsId", e);
		}
	}

}
