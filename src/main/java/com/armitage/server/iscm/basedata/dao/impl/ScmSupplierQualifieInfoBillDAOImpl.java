package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierQualifieInfoBillDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifieInfoBill2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierQualifieInfoBillDAO")
public class ScmSupplierQualifieInfoBillDAOImpl extends BasicDAOImpl<ScmSupplierQualifieInfoBill> implements ScmSupplierQualifieInfoBillDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSupplierQualifieInfoBill2 selectByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByVendorId", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierQualifieInfoBillDAOImpl.error.selectByVendorId", e);
		}

	}

}
