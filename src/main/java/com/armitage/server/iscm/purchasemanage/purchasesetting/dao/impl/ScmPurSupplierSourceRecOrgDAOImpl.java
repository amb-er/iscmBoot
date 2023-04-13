
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplierSourceRecOrgDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceRecOrg2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurSupplierSourceRecOrgDAO")
public class ScmPurSupplierSourceRecOrgDAOImpl extends BasicDAOImpl<ScmPurSupplierSourceRecOrg> implements ScmPurSupplierSourceRecOrgDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurSupplierSourceRecOrg2> selectByBillId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBillId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplierSourceRecOrgDAOImpl.error.selectByBillId");
		}
	}

}
