
package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierRegInvitation;

public interface ScmSupplierRegInvitationDAO extends BasicDAO<ScmSupplierRegInvitation> {
	public ScmSupplierRegInvitation selectByVendorIdAndCtrl(HashMap<String, Object> map) throws DAOException;
	public ScmSupplierRegInvitation selectByCtrl(HashMap<String, Object> map) throws DAOException;
	public ScmSupplierRegInvitation selectByPlatVendorIdAndCtrl(HashMap<String, Object> map) throws DAOException;
	public List<ScmSupplierRegInvitation> selectBindedByCtrl(HashMap<String, Object> map) throws DAOException;
	public List<ScmSupplierRegInvitation> selectBindedByCtrlAndVendor(HashMap<String, Object> map) throws DAOException;

}
