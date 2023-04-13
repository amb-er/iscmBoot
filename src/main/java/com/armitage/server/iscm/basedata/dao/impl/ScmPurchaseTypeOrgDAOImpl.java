
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmPurchaseTypeOrgDAO;
import com.armitage.server.iscm.basedata.model.ScmPurchaseTypeOrg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurchaseTypeOrgDAO")
public class ScmPurchaseTypeOrgDAOImpl extends BasicDAOImpl<ScmPurchaseTypeOrg> implements ScmPurchaseTypeOrgDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurchaseTypeOrg selectByTypeAndOrgNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTypeAndOrgNo", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmPurchaseTypeOrgDAOImpl.error.selectByTypeAndOrgNo");
		}
	}

}
