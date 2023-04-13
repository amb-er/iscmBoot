package com.armitage.server.iscm.basedata.dao;

import java.util.HashMap;
import java.util.List;

import com.armitage.server.common.base.dao.BasicDAO;
import com.armitage.server.common.exception.DAOException;
import com.armitage.server.iscm.basedata.model.ScmSupplierUnified;

public interface ScmSupplierUnifiedDAO extends BasicDAO<ScmSupplierUnified> {

	public List<ScmSupplierUnified> selectByVendorId(HashMap<String, Object> map) throws DAOException;
}
