
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTemplet2;

public interface ScmAuditMsgTempletDAO extends BasicDAO<ScmAuditMsgTemplet> {

	public ScmAuditMsgTemplet2 selectByTempetType(HashMap<String, Object> map) throws DAOException;

}
