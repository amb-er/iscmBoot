
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplyRecOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplyRecOrg2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmPurSupplyRecOrgDAO")
public class ScmPurSupplyRecOrgDAOImpl extends BasicDAOImpl<ScmPurSupplyRecOrg> implements ScmPurSupplyRecOrgDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurSupplyRecOrg2> selectBySupplyInfoId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectBySupplyInfoId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyRecOrgDAOImpl.error.selectBySupplyInfoId");
		}
	}

	@Override
	public int addBySupplierSource(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.insert(simpleName + ".addBySupplierSource", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplyRecOrgDAOImpl.error.addBySupplierSource", e);
		}
	}

	
}
