
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmBillPending;
import com.armitage.server.iscm.basedata.model.ScmBillPending2;

public interface ScmBillPendingDAO extends BasicDAO<ScmBillPending> {

	public int checkExistPendingBill(HashMap<String, Object> map) throws DAOException;

	public ScmBillPending selectByUsrCode(HashMap<String, Object> map) throws DAOException;

	public ScmBillPending selectLastUsrPended(HashMap<String, Object> map) throws DAOException;

	public ScmBillPending selectLastUsrPending(HashMap<String, Object> map) throws DAOException;

	public ScmBillPending2 selectPendingUsr(HashMap<String, Object> map) throws DAOException;

	public void deletePendingBill(HashMap<String, Object> map) throws DAOException;

}
