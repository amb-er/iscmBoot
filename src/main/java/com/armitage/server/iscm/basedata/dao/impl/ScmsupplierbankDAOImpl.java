package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmsupplierbankDAO;
import com.armitage.server.iscm.basedata.model.Scmsupplierbank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmsupplierbankDAO")
public class ScmsupplierbankDAOImpl extends BasicDAOImpl<Scmsupplierbank> implements ScmsupplierbankDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<Scmsupplierbank> selectByVendorIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByVendorIdAndOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmsupplierbankDAOImpl.error.selectByVendorIdAndOrgUnitNo", e);
		}
	}

	@Override
	public void deleteByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteByVendorId", map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmsupplierbankDAOImpl.error.deleteByVendorId", e);
		}
	}
}
