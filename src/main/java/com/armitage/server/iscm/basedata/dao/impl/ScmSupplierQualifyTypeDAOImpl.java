package com.armitage.server.iscm.basedata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierQualifyTypeDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierQualifyTypeDAO")
public class ScmSupplierQualifyTypeDAOImpl extends BasicDAOImpl<ScmSupplierQualifyType> implements ScmSupplierQualifyTypeDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSupplierQualifyType selectByCode(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByCode",map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmIndustryGroupDAOImpl.error.selectByCode", e);
		}
	}
	@Override
	public List<ScmSupplierQualifyType> selectByVendor(HashMap<String, Object> map) throws DAOException {
		try {
            sqlSession.clearCache();
            return sqlSession.selectList(simpleName + ".selectByVendor", map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("com.armitage.server.iscm.purchasemanage.purchasebusiness.dao.impl.ScmPurRequireDAOImpl.error.selectByVendor");
        }
	}
}

