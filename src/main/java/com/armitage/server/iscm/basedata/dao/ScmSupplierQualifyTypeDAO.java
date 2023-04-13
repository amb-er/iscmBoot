package com.armitage.server.iscm.basedata.dao;


import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierQualifyType;

public interface ScmSupplierQualifyTypeDAO extends BasicDAO<ScmSupplierQualifyType> {
	public ScmSupplierQualifyType selectByCode(HashMap<String, Object> map) throws DAOException;
	List<ScmSupplierQualifyType> selectByVendor(HashMap<String, Object> map) throws DAOException;

}

