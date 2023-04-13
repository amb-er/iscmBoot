
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmAuditMsgTempletDAO;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("scmAuditMsgTempletDAO")
public class ScmAuditMsgTempletDAOImpl extends BasicDAOImpl<ScmAuditMsgTemplet> implements ScmAuditMsgTempletDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public ScmAuditMsgTemplet2 selectByTempetType(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectOne(simpleName + ".selectByTempetType",map);
		} catch (Exception e) {
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmAuditMsgTempletDAOImpl.error.selectByTempetType", e);
		}
	}

}
