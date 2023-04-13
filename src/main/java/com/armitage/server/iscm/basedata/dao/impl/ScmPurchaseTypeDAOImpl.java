
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.AppException;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmPurchaseTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType;
import com.armitage.server.iscm.basedata.model.ScmPurchaseType2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmPurchaseTypeDAO")
public class ScmPurchaseTypeDAOImpl extends BasicDAOImpl<ScmPurchaseType> implements ScmPurchaseTypeDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmPurchaseType2 selectByCodeAncCtrl(HashMap<String, Object> map) throws AppException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCodeAncCtrl", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmPurchaseTypeDAOImpl.error.selectByCodeAncCtrl");
		}
	}

}
