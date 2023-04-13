package com.armitage.server.iscm.basedata.dao;


import java.util.HashMap;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierDemander;

public interface ScmSupplierDemanderDAO extends BasicDAO<ScmSupplierDemander> {
	public ScmSupplierDemander selectByCtrl(HashMap<String, Object> map) throws DAOException;
	public ScmSupplierDemander selectByDemanderId(HashMap<String, Object> map) throws DAOException;
}

