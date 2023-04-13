
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmBillPendingToUsr;

public interface ScmBillPendingToUsrDAO extends BasicDAO<ScmBillPendingToUsr> {

	public void updateProcessed(HashMap<String, Object> map) throws DAOException;

	public void updateUsrProcessed(HashMap<String, Object> map) throws DAOException;

	public List<ScmBillPendingToUsr> selectByPendingId(HashMap<String, Object> map) throws DAOException;

	public void cancelProcessed(HashMap<String, Object> map) throws DAOException;

	public void cancelUsrProcessed(HashMap<String, Object> map) throws DAOException;

}
