package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmsuppliergroupdetailDAO;
import com.armitage.server.iscm.basedata.model.Scmsuppliergroupdetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmsuppliergroupdetailDAO")
public class ScmsuppliergroupdetailDAOImpl extends BasicDAOImpl<Scmsuppliergroupdetail> implements ScmsuppliergroupdetailDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public void deleteByVendorIdOrGroupId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			sqlSession.delete(simpleName + ".deleteByVendorIdOrGroupId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.ScmsuppliergroupdetailDAOImpl.error.deleteByVendorIdOrGroupId", e);
		}
		
	}
}
