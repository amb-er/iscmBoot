
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo;
import com.armitage.server.iscm.basedata.model.ScmQualifieInfo2;

public interface ScmQualifieInfoDAO extends BasicDAO<ScmQualifieInfo> {
	public List<ScmQualifieInfo2> selectByVendorId(HashMap<String, Object> map) throws DAOException;
	public List<ScmQualifieInfo2> selectAttachByVendorId(HashMap<String, Object> map) throws DAOException;
	public void deleteQualifyByVendorId(HashMap<String, Object> map) throws DAOException;
	public int doUnAuditQualifieInfo(HashMap<String, Object> map) throws DAOException;
}
