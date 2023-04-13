
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmQualifieInfoDAO;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmQualifieInfoDAO")
public class ScmQualifieInfoDAOImpl extends BasicDAOImpl<ScmQualifieInfo> implements ScmQualifieInfoDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmQualifieInfo2> selectByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByVendorId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmQualifieInfoDAOImpl.error.selectByVendorId", e);
		}
	}

	@Override
	public List<ScmQualifieInfo2> selectAttachByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectAttachByVendorId",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmQualifieInfoDAOImpl.error.selectAttachByVendorId", e);
		}
	}

	@Override
	public void deleteQualifyByVendorId(HashMap<String, Object> map) throws DAOException {
		try {
			 sqlSession.clearCache();
			 sqlSession.delete(simpleName + ".deleteQualifyByVendorId", map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmQualifieInfoDAOImpl.error.deleteQualifyByVendorId", e);
		}
	}

	@Override
	public int doUnAuditQualifieInfo(HashMap<String, Object> map) throws DAOException {
		try {
			return sqlSession.update(simpleName + ".doUnAuditQualifieInfo",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmQualifieInfoDAOImpl.error.doUnAuditQualifieInfo", e);
		}
	}

}
