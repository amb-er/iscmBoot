
package com.armitage.server.iscm.basedata.dao.impl;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAOImpl;
import com.armitage.server.common.base.dao.SqlSessionTemplate;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.dao.ScmAuditMsgTempletChannelDAO;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("scmAuditMsgTempletChannelDAO")
public class ScmAuditMsgTempletChannelDAOImpl extends BasicDAOImpl<ScmAuditMsgTempletChannel> implements ScmAuditMsgTempletChannelDAO {

	@Override
	@Autowired
	@Resource(name = "sqlSessionIscm")
	public void setSqlSession(SqlSessionTemplate sqlSession){
		super.setSqlSession(sqlSession);
	}

	@Override
	public List<ScmAuditMsgTempletChannel> selectByTempetId(HashMap<String, Object> map) throws DAOException {
		try {
			sqlSession.clearCache();
			return sqlSession.selectList(simpleName + ".selectByTempetId", map);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DAOException("com.armitage.server.iscm.basedata.dao.impl.ScmAuditMsgTempletChannelDAOImpl.error.selectByTempetId");
		}
	}

}
