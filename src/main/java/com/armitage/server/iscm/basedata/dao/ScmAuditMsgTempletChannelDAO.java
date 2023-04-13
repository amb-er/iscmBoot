
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmAuditMsgTempletChannel;

public interface ScmAuditMsgTempletChannelDAO extends BasicDAO<ScmAuditMsgTempletChannel> {

	public List<ScmAuditMsgTempletChannel> selectByTempetId(HashMap<String, Object> map) throws DAOException;

}
