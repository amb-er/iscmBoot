package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurBuyerOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurBuyerOrg2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmPurBuyerOrgDAO")
public class ScmPurBuyerOrgDAOImpl extends BasicDAOImpl<ScmPurBuyerOrg> implements ScmPurBuyerOrgDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurBuyerOrg2> selectByBuyerId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBuyerId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmPurBuyerOrgDAOImpl.error.selectByBuyerId");
		}
	}

}
