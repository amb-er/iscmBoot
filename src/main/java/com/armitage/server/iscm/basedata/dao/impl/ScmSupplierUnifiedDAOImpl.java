package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierUnifiedDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierUnified;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierUnifiedDAO")
public class ScmSupplierUnifiedDAOImpl extends BasicDAOImpl<ScmSupplierUnified> implements ScmSupplierUnifiedDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmSupplierUnified> selectByVendorId(HashMap<String, Object> map) throws DAOException {
		try{
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName+".selectByVendorId",map);
		}catch(Exception e){
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmSupplierUnifiedDAOImpl.error.selectByVendorId");
		}
	}

}
