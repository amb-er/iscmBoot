
package com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.purchasemanage.purchasesetting.dao.ScmPurSupplierSourceEntryDAO;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry;
import com.armitage.server.iscm.purchasemanage.purchasesetting.model.ScmPurSupplierSourceEntry2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurSupplierSourceEntryDAO")
public class ScmPurSupplierSourceEntryDAOImpl extends BasicDAOImpl<ScmPurSupplierSourceEntry> implements ScmPurSupplierSourceEntryDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmPurSupplierSourceEntry2> selectByBillId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBillId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasesetting.dao.impl.ScmPurSupplierSourceEntryDAOImpl.error.selectByBillId");
		}
	}

}
