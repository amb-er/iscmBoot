
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmMaterialCompanyInfoDAO;
import com.armitage.server.iscm.basedata.model.ScmMaterialCompanyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmMaterialCompanyInfoDAO")
public class ScmMaterialCompanyInfoDAOImpl extends BasicDAOImpl<ScmMaterialCompanyInfo> implements ScmMaterialCompanyInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmMaterialCompanyInfo selectByItemIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByItemIdAndOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmMaterialCompanyInfoDAOImpl.error.selectByItemIdAndOrgUnitNo", e);
		}
	}
	
}
