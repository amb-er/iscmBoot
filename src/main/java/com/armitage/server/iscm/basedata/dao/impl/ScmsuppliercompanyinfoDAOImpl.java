package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmsuppliercompanyinfoDAO;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo;
import com.armitage.server.iscm.basedata.model.Scmsuppliercompanyinfo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmsuppliercompanyinfoDAO")
public class ScmsuppliercompanyinfoDAOImpl extends BasicDAOImpl<Scmsuppliercompanyinfo> implements ScmsuppliercompanyinfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public Scmsuppliercompanyinfo2 selectByVendorIdAndOrgUnitNo(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByVendorIdAndOrgUnitNo",map);
		} catch (Exception e) {
			throw new DAOException("iscm.basedata.dao.ScmsuppliercompanyinfoDAOImpl.error.selectByVendorIdAndOrgUnitNo", e);
		}
	}
}

