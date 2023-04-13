
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.base.model.Param;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialPurchaseDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase;
import com.armitage.server.iscm.basedata.model.ScmMaterialPurchase2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialPurchaseDAO")
public class ScmMaterialPurchaseDAOImpl extends BasicDAOImpl<ScmMaterialPurchase> implements ScmMaterialPurchaseDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmMaterialPurchase2 selectByItemIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemIdAndOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmMaterialPurchaseDAOImpl.error.selectByItemIdAndOrgUnitNo", e);
		}
	}

	@Override
	public void addOrUpdateMaterialPurchase(ScmMaterialPurchase scmMaterialPurchase2, Param param) throws DAOException {
		try {
			sqlSession.insert(simpleName + ".addOrUpdateMaterialPurchase",scmMaterialPurchase2);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmMaterialPurchaseDAOImpl.error.addOrUpdateMaterialPurchase", e);
		}
	}

}
