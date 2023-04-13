package com.armitage.server.iscm.basedata.dao.impl;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmSupplierConfirmDataDAO;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData;
import com.armitage.server.iscm.basedata.model.ScmSupplierConfirmData2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmSupplierConfirmDataDAO")
public class ScmSupplierConfirmDataDAOImpl extends BasicDAOImpl<ScmSupplierConfirmData> implements ScmSupplierConfirmDataDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmSupplierConfirmData2 selectByBcId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByBcId",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmSupplierConfirmDataDAOImpl.error.selectByBcId", e);
		}
	}

	@Override
	public ScmSupplierConfirmData2 selectByBillNoAndType(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByBillNoAndType",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmSupplierConfirmDataDAOImpl.error.selectByBillNoAndType", e);
		}
	}

	@Override
	public void deleteListByBillNoAndType(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteListByBillNoAndType", map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmSupplierConfirmDataDAOImpl.error.deleteListByBillNoAndType", e);
		}
	}

	@Override
	public List<ScmSupplierConfirmData2> selectByBillNoAndPurPrice(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByBillNoAndPurPrice",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmSupplierConfirmDataDAOImpl.error.selectByBillNoAndType", e);
		}
	}

}

